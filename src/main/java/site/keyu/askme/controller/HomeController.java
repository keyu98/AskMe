package site.keyu.askme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import site.keyu.askme.pojo.*;
import site.keyu.askme.service.CommentService;
import site.keyu.askme.service.LastedViewService;
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

    @Autowired
    LastedViewService lastedViewService;

    @RequestMapping(path = {"/","/index"},method = {RequestMethod.GET,RequestMethod.POST})
    public String index(Model model,ModelAndView modelAndView,
                        @RequestParam(value = "pop",defaultValue = "0") int pop){

        model.addAttribute("cvos",lastedViewService.getLastedComment(0,5));

        model.addAttribute("vos",lastedViewService.getLastedQuestion(0,10));


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
