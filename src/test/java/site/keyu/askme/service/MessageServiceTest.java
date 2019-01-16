package site.keyu.askme.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.keyu.askme.pojo.Message;

import java.util.Date;

/**
 * @Author:Keyu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Test
    public void addMessageTest(){

        Message message = new Message();
        message.setCreatedDate(new Date());
        message.setFromId(1);
        message.setToId(2);
        message.setContent("你好");
        message.setHasRead(0);
        message.setConversationId("1_2");

        messageService.addMessage(message);

    }

    @Test
    public void getMessageTest(){


        System.out.println(messageService.getConversationDetail("1_2",0,10));
    }
}
