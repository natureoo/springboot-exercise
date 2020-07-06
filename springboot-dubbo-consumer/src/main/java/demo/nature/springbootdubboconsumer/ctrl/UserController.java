package demo.nature.springbootdubboconsumer.ctrl;

import com.alibaba.dubbo.config.annotation.Reference;
import demo.nature.springbootdubbo.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nature
 * @date 6/7/2020 7:13 下午
 * @email 924943578@qq.com
 */
@RequestMapping(value="dubbo")
@RestController
public class UserController {

    @Reference(interfaceClass = UserService.class)
    private UserService userService;

    @GetMapping(value = "user/{name}")
    public String sayHi(@PathVariable("name") String name){
        return userService.sayHi(name);
    }
}
