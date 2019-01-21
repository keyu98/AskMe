package site.keyu.askme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import site.keyu.askme.pojo.*;
import site.keyu.askme.service.CommentService;
import site.keyu.askme.service.LastedViewService;
import site.keyu.askme.service.QuestionService;
import site.keyu.askme.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:Keyu
 */
@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    LastedViewService lastedViewService;

    @Autowired
    HostHolder hostHolder;
    /**
     * 问题页
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(path = "/qst",method = {RequestMethod.GET})
    public String goToQuestion(Model model,
                               @RequestParam("id") int id){
        Question question = questionService.findQuestionById(id);
        List<Comment> commentList = commentService.getCommentsByEntity(id,0);
        model.addAttribute("qst",question);
        model.addAttribute("cmts",lastedViewService.getCommentsById(id));
        model.addAttribute("cvos",lastedViewService.getLastedComment(0,5));
        return  "question";
    }

    @RequestMapping(path = "/user/submitqst" ,method = {RequestMethod.POST})
    public String submitQuestion(@RequestParam("title") String title,
                                 @RequestParam("content") String content
                                 ){

        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setCreatedDate(new Date());
        question.setUserId(hostHolder.getUser().getId());

        questionService.postQuestion(question);

        return "redirect:/";
    }

    @RequestMapping(path = "/user/adopt",method = {RequestMethod.POST})
    public String adoptComment(RedirectAttributes redirectAttributes,
                               @RequestParam("commentId") int commentId,
                               @RequestParam("questionId") int qustionId,
                               @RequestParam("questionUserId") int questionUserId){
        redirectAttributes.addAttribute("id",qustionId);

        if (hostHolder.getUser().getId() == questionUserId){
            questionService.updateStatus(qustionId,commentId);

            return "redirect:/qst";
        }
        return "redirect:/qst";
    }



}
