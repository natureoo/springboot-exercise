package demo.nature.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nature
 * @date 12/6/2020 11:32 下午
 * @email 924943578@qq.com
 */
@Controller
@RequestMapping("jsp")
public class JspController {

    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("name", "JSP");
        return "index";
    }
}
