package site.keyu.askme.service;

import site.keyu.askme.dao.UserDao;
import site.keyu.askme.pojo.User;
import site.keyu.askme.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户注册
     * @param username
     * @param password
     */
    public void register(String username,String password){
        //验证环节
        User user;
        //写入数据库
        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadUrl(head);
        user.setPassword(Md5Util.MD5(password+user.getSalt()));
        userDao.addUser(user);
    }

}
