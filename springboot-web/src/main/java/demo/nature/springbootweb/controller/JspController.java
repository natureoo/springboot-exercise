package demo.nature.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author nature
 * @date 12/6/2020 11:32 下午
 * @email 924943578@qq.com
 */
@Controller
@RequestMapping("jsp")
public class JspController {

    @RequestMapping("config")
    @ResponseBody
    public String config(){
        return "config";
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(){
        System.out.println("in login");
        return "login";
    }
}
