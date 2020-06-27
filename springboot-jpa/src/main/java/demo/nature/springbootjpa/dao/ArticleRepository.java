package demo.nature.springbootjpa.dao;

import demo.nature.springbootjpa.entity.Article;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * @author nature
 * @date 27/6/2020 12:01 下午
 * @email 924943578@qq.com
 */
public interface ArticleRepository extends CrudRepository<Article, Long> {

    /**
     * 1、PESSIMISTIC_READ：只要事务读实体，实体管理器就锁定实体，直到事务完成锁才会解开，当你想使用重复读语义查询数据时使用这种锁模式，换句话说就是，当你想确保数据在连续读期间不被修改，这种锁模式不会阻碍其它事务读取数据。 
     * 2、PESSIMISTIC_WRITE：只要事务更新实体，实体管理器就会锁定实体，这种锁模式强制尝试修改实体数据的事务串行化，当多个并发更新事务出现更新失败几率较高时使用这种锁模式。 
     * 3、PESSIMISTIC_FORCE_INCREMENT：当事务读实体时，实体管理器就锁定实体，当事务结束时会增加实体的版本属性，即使实体没有修改。 
     *
     * 最后一种是NONE,也就是无锁。
     * @param var1
     * @return
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Override
    Optional<Article> findById(Long var1);

}
