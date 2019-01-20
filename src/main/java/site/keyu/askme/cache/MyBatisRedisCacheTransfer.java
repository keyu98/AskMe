package site.keyu.askme.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.keyu.askme.utils.RedisCacheUtil;

/**
 * @Author:Keyu
 */
@Component
public class MyBatisRedisCacheTransfer {

    @Autowired
    public void setRedisCatheUtil(RedisCacheUtil redisCatheUtil){
        MyBatisRedisCache.setRedisCacheUtil(redisCatheUtil);
    }

}
