package site.keyu.askme.async.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.keyu.askme.async.EventHandler;
import site.keyu.askme.async.EventModel;
import site.keyu.askme.async.EventType;
import site.keyu.askme.pojo.Message;
import site.keyu.askme.pojo.User;
import site.keyu.askme.service.MessageService;
import site.keyu.askme.service.UserService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author:Keyu
 */
@Component
public class AdoptHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        message.setFromId(0);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        message.setConversationId("system");
        User user = userService.getUser(model.getActorId());
        String questionId = model.getExt("questionId");
        message.setContent("用户" + user.getName() + "采纳了你的评论，http：//localhost:8080/qst?id="+ questionId);
        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {

        return Arrays.asList(EventType.ADOPT);
    }


}
