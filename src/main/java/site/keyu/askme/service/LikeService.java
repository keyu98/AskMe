package site.keyu.askme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.keyu.askme.utils.JedisAdapter;
import site.keyu.askme.utils.RedisKeyUtil;

/**
 * @Author:Keyu
 */
@Service
public class LikeService {

    @Autowired
    JedisAdapter jedisAdapter;

    public long getLikeCount(int entityType,int entityId){

        return jedisAdapter.scard(RedisKeyUtil.likeKey(entityType,entityId));
    }


    public long getDislikeCount(int entityType,int entityId){

        return jedisAdapter.scard(RedisKeyUtil.dislikeKey(entityType,entityId));
    }

    public boolean hasLike(int userId,int entityType,int entityId){

        return jedisAdapter.sismember(RedisKeyUtil.likeKey(entityType,entityId),String.valueOf(userId));
    }

    public boolean hasDislike(int userId,int entityType,int entityId){

        return jedisAdapter.sismember(RedisKeyUtil.dislikeKey(entityType,entityId),String.valueOf(userId));
    }

    public long likeIt(int userId,int entityType,int entityId){
        String likekey = RedisKeyUtil.likeKey(entityType,entityId);

        jedisAdapter.sadd(likekey,String.valueOf(userId));

        return jedisAdapter.scard(likekey);
    }

    public long dislikeIt(int userId,int entityType,int entityId){
        String dislikekey = RedisKeyUtil.dislikeKey(entityType,entityId);

        jedisAdapter.sadd(dislikekey,String.valueOf(userId));

        return jedisAdapter.scard(dislikekey);
    }
}
