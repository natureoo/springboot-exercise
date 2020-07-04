package demo.nature.springbootmybatis.service;

import demo.nature.springbootmybatis.dao.ArticleMapper;
import demo.nature.springbootmybatis.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author nature
 * @date 27/6/2020 11:47 下午
 * @email 924943578@qq.com
 */
@Service
@Slf4j
public class ArticleService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private ArticleMapper articleMapper;

    public Article getArticle(int id){

        String key = "article-" + id;

        Article article = (Article) redisTemplate.opsForValue().get(key);

        if(article == null){

            //加synchronized防止缓存穿透
            synchronized (this){
                article = (Article) redisTemplate.opsForValue().get(key);

                if(article == null) {
                    log.info("select in db");
                    article = articleMapper.selectByPrimaryKey(Long.valueOf(id));
                    redisTemplate.opsForValue().set(key, article);
                }else
                    log.info("select in redis");
            }
        }else
            log.info("select in redis");

        return article;
    }
}
