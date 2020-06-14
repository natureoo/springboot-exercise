package demo.nature.springbootweb.controller;

import demo.nature.springbootweb.service.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private UUIDService uuidService;

    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("name", "李四");
        return "index";
    }

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

    @RequestMapping("uuid/{index}")
    @ResponseBody
    public String uuid(@PathVariable("index") int index){
        return uuidService.randUUID(index) ;
    }
}
