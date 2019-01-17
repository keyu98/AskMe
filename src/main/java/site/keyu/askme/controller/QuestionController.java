package site.keyu.askme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author:Keyu
 */
@Controller
public class QuestionController {

    @GetMapping("/qst")
    public String goToQuestion(Model model){
        //model.addAttribute("user","123");

        return  "question";
    }
}
