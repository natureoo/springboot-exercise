package demo.nature.springbootjpa.service;

import demo.nature.springbootjpa.dao.ArticleRepository;
import demo.nature.springbootjpa.dao.CommentRepository;
import demo.nature.springbootjpa.entity.Article;
import demo.nature.springbootjpa.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author nature
 * @date 27/6/2020 12:08 下午
 * @email 924943578@qq.com
 */
@Slf4j
@Service
public class CommentService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void postComment(Long articleId, String content) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (!articleOptional.isPresent()) {
            throw new RuntimeException("没有对应的文章");
        }
        Article article = articleOptional.get();

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setContent(content);
        commentRepository.save(comment);

        article.setCommentCount(article.getCommentCount() + 1);
        articleRepository.save(article);
    }
}
