package site.keyu.askme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Keyu
 */
@Controller
public class IndexController {

    @GetMapping("test")
    @ResponseBody
    public Map<String,Object> test(){
        Map<String,Object> m = new HashMap();
        m.put("code","test");
        return m;
    }


//    @RequestMapping(path = {"/","/index"},method = {RequestMethod.GET})
//    @ResponseBody
//    public String returnhome(Model model){
//        //model.addAttribute("user","123");
//
//        return  "Welcome to Homepage!";
//    }

}
