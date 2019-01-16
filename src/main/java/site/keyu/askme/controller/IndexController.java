package site.keyu.askme.controller;

import org.springframework.stereotype.Controller;
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


    @RequestMapping(path = "/",method = {RequestMethod.GET})
    public String returnhome(){
        return  "home";
    }
}
