package demo.nature.springbootdubboprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import demo.nature.springbootdubbo.entity.Article;
import demo.nature.springbootdubbo.service.ArticleService;
import demo.nature.springbootdubboprovider.dao.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author nature
 * @date 10/7/2020 2:08 下午
 * @email 924943578@qq.com
 */
@Component
@Service(interfaceClass = ArticleService.class)
@Slf4j
public class ArticleServiceProvider implements ArticleService{

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
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
