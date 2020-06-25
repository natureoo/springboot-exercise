package demo.nature.springbootmybatis.ctrl;

import demo.nature.springbootmybatis.entity.AuthUser;
import demo.nature.springbootmybatis.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(value = "update")
    public void updateAuthUser(){
        AuthUser authUser = new AuthUser();
        authUser.setId(1L);
        authUser.setAccount("linghc");
        authUser.setName("令狐冲");
        authUser.setPwd("123456");
        authUserService.updateAuthUser(authUser);
    }

    @RequestMapping(value = "update/exp")
    public void updateAuthUserExp(){
        List<AuthUser> authUserList = getTestAuthUserList();
        authUserService.updateAuthUser(authUserList.get(0), authUserList.get(1));
    }

    @RequestMapping(value = "update/trans")
    public void updateAuthUserTrans(){
        List<AuthUser> authUserList = getTestAuthUserList();
        authUserService.updateAuthUserTrans(authUserList.get(0), authUserList.get(1));
    }

    private List<AuthUser> getTestAuthUserList(){
        ArrayList<AuthUser> authUsers = new ArrayList<>();
        AuthUser authUser1 = new AuthUser();
        authUser1.setId(1L);
        authUser1.setAccount("linghc");
        authUser1.setName("令狐冲3");
        authUser1.setPwd("123456");

        AuthUser authUser2 = new AuthUser();
        authUser2.setId(2L);
        authUser2.setAccount("renyy");
        authUser2.setName("任盈盈3");
        authUser2.setPwd("123456");
        authUsers.add(authUser1);
        authUsers.add(authUser2);
        return authUsers;
    }
}
