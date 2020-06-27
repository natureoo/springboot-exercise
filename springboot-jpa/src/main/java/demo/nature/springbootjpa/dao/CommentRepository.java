package demo.nature.springbootjpa.dao;

import demo.nature.springbootjpa.entity.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * @author nature
 * @date 27/6/2020 12:02 下午
 * @email 924943578@qq.com
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
