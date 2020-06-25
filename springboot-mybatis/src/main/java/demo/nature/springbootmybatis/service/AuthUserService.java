package demo.nature.springbootmybatis.service;

import demo.nature.springbootmybatis.dao.AuthUserMapper;
import demo.nature.springbootmybatis.entity.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nature
 * @date 25/6/2020 1:24 下午
 * @email 924943578@qq.com
 */
@Service
public class AuthUserService {

    @Autowired
    private AuthUserMapper authUserMapper;

    public void addAuthUser(AuthUser authUser){
        authUserMapper.insert(authUser);
    }

    public void updateAuthUser(AuthUser authUser){
        int row = authUserMapper.updateByPrimaryKey(authUser);
        System.out.println("row = " + row);
    }


    /**
     * 如果不加@Transactional, 前面updateAuthUser成功， 后面10 / 0抛异常，前面updateAuthUser成功也不会回滚
     * @param authUser
     */
    public void updateAuthUserExp(AuthUser authUser){
        updateAuthUser(authUser);
        int i = 10 / 0;
    }

    public void updateAuthUser(AuthUser authUser1, AuthUser authUser2){
        updateAuthUser(authUser1);
        updateAuthUserExp(authUser2);
    }

    /**
     * 与@EnableTransactionManagement一起使用，使整个service方法一齐提交或者一齐回滚
     * @param authUser1
     * @param authUser2
     */
    @Transactional
    public void updateAuthUserTrans(AuthUser authUser1, AuthUser authUser2){
        updateAuthUser(authUser1);
        updateAuthUserExp(authUser2);
    }
}
