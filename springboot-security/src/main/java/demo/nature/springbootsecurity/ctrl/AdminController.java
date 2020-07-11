package demo.nature.springbootsecurity.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nature
 * @date 11/7/2020 7:27 下午
 * @email 924943578@qq.com
 */
@RestController
@RequestMapping(value = "admin")
public class AdminController {

    @RequestMapping(value = "demo")
    public String demo(){
        return "demo";
    }
}
