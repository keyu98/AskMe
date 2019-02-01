package site.keyu.askme.async.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.keyu.askme.async.EventHandler;
import site.keyu.askme.async.EventModel;
import site.keyu.askme.async.EventType;
import site.keyu.askme.service.SearchService;

import java.util.Arrays;
import java.util.List;


/**
 * @Author:Keyu
 */
@Component
public class AddIndexHandler implements EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(AddIndexHandler.class);

    @Autowired
    SearchService searchService;

    @Override
    public void doHandle(EventModel model){
        try {
            searchService.addQuestionIndex(model.getEntityId(),model.getExt("title"),model.getExt("content"));
        } catch (Exception e){
            logger.error("问题索引添加失败");
        }
    }

    @Override
    public List<EventType> getSupportEventTypes(){
        return Arrays.asList(EventType.ADDINDEX);
    }

}
