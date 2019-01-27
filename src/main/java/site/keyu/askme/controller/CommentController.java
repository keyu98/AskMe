package site.keyu.askme.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.keyu.askme.async.EventModel;
import site.keyu.askme.async.EventProducer;
import site.keyu.askme.async.EventType;
import site.keyu.askme.pojo.Comment;
import site.keyu.askme.pojo.HostHolder;
import site.keyu.askme.service.CommentService;
import site.keyu.askme.service.QuestionService;

import java.util.Date;

/**
 * @Author:Keyu
 */
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    QuestionService questionService;

    @Autowired
    EventProducer eventProducer;


    @RequestMapping(path = "/user/submitcmt", method = {RequestMethod.POST})
    public String addComment(RedirectAttributes redirectAttributes,
                             @RequestParam("id") int id,
                             @RequestParam("content") String content
    ) {

        Comment comment = new Comment();
        comment.setEntityId(id);
        comment.setEntityType(0);
        comment.setContent(content);
        comment.setCreatedDate(new Date());
        comment.setUserId(hostHolder.getUser().getId());
        commentService.addComment(comment);

        eventProducer.fireEvent(new EventModel(EventType.COMMENT)
                .setActorId(hostHolder.getUser().getId()).setEntityId(id)
                .setEntityType(0).setEntityOwnerId(comment.getUserId())
                .setExt("questionId", String.valueOf(id)));

        int count = commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
        questionService.updateCommentCount(comment.getEntityId(), count);

        redirectAttributes.addAttribute("id", id);
        return "redirect:/qst";
    }

}


