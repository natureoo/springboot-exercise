package demo.nature.springbootsecurity.dao;

import demo.nature.springbootsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nature
 * @date 12/7/2020 1:40 下午
 * @email 924943578@qq.com
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
