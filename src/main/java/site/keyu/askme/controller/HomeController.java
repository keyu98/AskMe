package site.keyu.askme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.keyu.askme.pojo.*;
import site.keyu.askme.service.CommentService;
import site.keyu.askme.service.QuestionService;
import site.keyu.askme.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Keyu
 */
@Controller
public class HomeController {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    HostHolder hostHolder;

    /**
     * 查找问题Model
     * @param offset
     * @param limit
     * @return
     */
    private List<ViewObject> getQuestion(int offset, int limit){
        List<Question> questionList = questionService.findLatestQuestion(offset, limit);
        List<ViewObject> vos = new ArrayList<>();
        for (Question question:questionList){
            ViewObject vo = new ViewObject();
            vo.setQuestion(question);
            vo.setUser(userService.getUser(question.getUserId()));
            vos.add(vo);
        }
        return  vos;
    }

    /**
     * 查找问题回答
     * @param offset
     * @param limit
     * @return
     */
    private List<CommentViewObject> getComment(int offset, int limit){
        List<Comment> commentList = commentService.getLatestComment(0,10);
        List<CommentViewObject> cvos = new ArrayList<>();
        for (Comment comment:commentList){
            CommentViewObject cvo = new CommentViewObject();
            cvo.setComment(comment);
            cvo.setUser(userService.getUser(comment.getUserId()));
            cvos.add(cvo);
        }

        return cvos;
    }

    @RequestMapping(path = {"/","/index"},method = {RequestMethod.GET,RequestMethod.POST})
    public String index(Model model,ModelAndView modelAndView,
                        @RequestParam(value = "pop",defaultValue = "0") int pop){

        model.addAttribute("cvos",getComment(0,10));

        model.addAttribute("vos",getQuestion(0,5));


        return "home";
    }

    /**
     * 跳转登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(){

        return "login";
    }


}
