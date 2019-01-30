package site.keyu.askme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.keyu.askme.pojo.Question;
import site.keyu.askme.pojo.User;
import site.keyu.askme.pojo.ViewObject;
import site.keyu.askme.service.QuestionService;
import site.keyu.askme.service.SearchService;
import site.keyu.askme.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Keyu
 */
@Controller
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    SearchService searchService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @RequestMapping(path = {"/search"},method = {RequestMethod.GET})
    public String searchQuestion(Model model,
                                 @RequestParam("text") String keyword,
                                 @RequestParam(value = "offset", defaultValue = "0") int offset,
                                 @RequestParam(value = "count", defaultValue = "10") int count){
        try {
            List<Question> questionList = searchService.searchQuestion(keyword,offset,count,"<strong>","</strong>");

            List<ViewObject> vos = new ArrayList<>();
            for (Question question:questionList){
                Question question1 = questionService.findQuestionById(question.getId());
                question.setCreatedDate(question1.getCreatedDate());
                User user = userService.getUser(question1.getUserId());

                ViewObject vo = new ViewObject();
                vo.setQuestion(question);
                vo.setUser(user);

                vos.add(vo);
            }
            model.addAttribute("vos",vos);

        }catch (Exception e){
            logger.error("搜索失败"+ e.getMessage());
        }

        return "search";

    }
}
