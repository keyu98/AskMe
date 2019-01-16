package site.keyu.askme.pojo;

import org.springframework.stereotype.Component;

/**
 * @Author:Keyu
 * 上下文存储用户状态
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser(){
        return users.get();
    }

    public void setUser(User user){
        users.set(user);
    }
    public void clear(){
        users.remove();
    }
}

