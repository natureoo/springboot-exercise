package demo.nature.springbootmybatis.ctrl;

import demo.nature.springbootmybatis.entity.AuthUser;
import demo.nature.springbootmybatis.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nature
 * @date 25/6/2020 1:31 下午
 * @email 924943578@qq.com
 */
@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private AuthUserService authUserService;

    @RequestMapping(value = "add")
    public void addAuthUser(){
        AuthUser authUser = new AuthUser();
//        authUser.setId(6L);
        authUser.setAccount("fengz");
        authUser.setName("方正");
        authUser.setPwd("123456");
        authUserService.addAuthUser(authUser);
    }
}
