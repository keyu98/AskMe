package site.keyu.askme.service;

import site.keyu.askme.dao.LoginTicketDao;
import site.keyu.askme.dao.UserDao;
import site.keyu.askme.pojo.LoginTicket;
import site.keyu.askme.pojo.User;
import site.keyu.askme.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.zip.DataFormatException;

/**
 * @Author:Keyu
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginTicketDao loginTicketDao;
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
        userDao.insertUser(user);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     */
    public Map<String,Object> login(String username, String password){

        Map<String,Object> map = new HashMap<>();
        //验证环节
        User user = userDao.selectByName(username);

        //验证密码
        if(Md5Util.MD5(password+user.getSalt()).equals( user.getPassword())){
            System.out.println("用户登录成功");
        }
        else {
            System.out.println("密码错误"+Md5Util.MD5(password+user.getSalt()) + "!=" + user.getPassword());
            return map;
        }

        String ticket = addLoginTicket(user.getId());
        map.put("ticket",ticket);

        return map;
    }

    public String addLoginTicket(int userId){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        Date now = new Date();

        now.setTime(3600*24*1000 + now.getTime());
        loginTicket.setExpired(now);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDao.addTicket(loginTicket);

        return  loginTicket.getTicket();


    }

}
