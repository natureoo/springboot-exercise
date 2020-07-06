package demo.nature.springbootdubboprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import demo.nature.springbootdubbo.UserService;
import org.springframework.stereotype.Component;

/**
 * @author nature
 * @date 6/7/2020 6:38 下午
 * @email 924943578@qq.com
 */
@Component
@Service(interfaceClass = UserService.class)
public class UserServiceProvider implements UserService {
    @Override
    public String sayHi(String name) {
        return "hello " + name +", welcome to springboot dubbo";
    }
}
