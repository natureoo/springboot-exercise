package demo.nature.springbootmybatis.service;

import demo.nature.springbootmybatis.dao.AuthUserMapper;
import demo.nature.springbootmybatis.entity.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
