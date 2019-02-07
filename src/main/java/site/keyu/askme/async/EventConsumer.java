package site.keyu.askme.async;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import site.keyu.askme.utils.JedisAdapter;
import site.keyu.askme.utils.RedisKeyUtil;

import java.util.*;

/**
 * @Author:Keyu
 */
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    private Map<EventType, List<EventHandler>> config = new HashMap<EventType, List<EventHandler>>();
    private ApplicationContext applicationContext;

    @Autowired
    JedisAdapter jedisAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if(beans != null){
            for (Map.Entry<String,EventHandler> entry:beans.entrySet()){
                List<EventType> eventTypes = entry.getValue().getSupportEventTypes();

                for (EventType type:eventTypes){
                    if (!config.containsKey(type)){
                        config.put(type,new ArrayList<EventHandler>());
                    }
                    config.get(type).add(entry.getValue());
                }
            }
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    String key = RedisKeyUtil.eventQueueKey();
                    List<String> events = jedisAdapter.brpop(0,key);

                    //若上次有未处理事件
                    if (jedisAdapter.exists(RedisKeyUtil.eventDoingKey())){
                        //取出失败事件
                        Set<String> undoingevents = jedisAdapter.smembers(RedisKeyUtil.eventDoingKey());

                        for (String message:undoingevents){
                            //将失败事件放进队列尾
                            jedisAdapter.lpush(key,message);
                        }
                    }


                    for (String message:events){
                        if(message.equals(key)){
                            continue;
                        }
                        //将消息放进EVENT_DOING中存储起来
                        //EVENT_DOING存储正在进行的事件
                        jedisAdapter.sadd(RedisKeyUtil.eventDoingKey(),message);

                        EventModel eventModel = JSON.parseObject(message,EventModel.class);
                        if(!config.containsKey(eventModel.getType())){
                            logger.error("事件无法识别：+"+eventModel.getType());
                            continue;
                        }

                        //目前一个任务只有一个消费者消费，暂时不考虑回滚
                        try{
                            for (EventHandler handler:config.get(eventModel.getType())){
                                //开始处理事件
                                handler.doHandle(eventModel);
                            }
                            //处理结束，将消息移除
                            jedisAdapter.srem(RedisKeyUtil.eventDoingKey(),message);
                        }catch (Exception e){
                            logger.error("事件处理失败");
                        }


                    }
                }
            }
        });

        thread.start();;
    }

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
