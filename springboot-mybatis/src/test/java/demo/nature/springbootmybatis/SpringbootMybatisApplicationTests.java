package demo.nature.springbootmybatis;

import com.alibaba.fastjson.JSON;
import demo.nature.springbootmybatis.entity.Article;
import demo.nature.springbootmybatis.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest(classes = SpringbootMybatisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class SpringbootMybatisApplicationTests {


	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ArticleService articleService;

	@LocalServerPort
	private int port;


	@Test
	public void testArticleService()  {
		String url = "http://localhost:"+port + "/test/art/1";
//		ExecutorService executorService = Executors.newFixedThreadPool(10, r -> {
//			Thread t = new Thread("test -redis-thread-" + threadIndex.getAndIncrement());
//			return t;
//		});

//		ExecutorService executorService = new ThreadPoolExecutor(1, 20,
//				100000, TimeUnit.MILLISECONDS,
//				new SynchronousQueue<Runnable>(),r -> {
//			Thread t = new Thread("test -redis-thread-" + threadIndex.getAndIncrement());
//			return t;
//		});

//		ExecutorService executorService = new ThreadPoolExecutor(1, 100,
//				100000, TimeUnit.MILLISECONDS,
//				new SynchronousQueue<Runnable>());

		ExecutorService executorService = Executors.newFixedThreadPool(16);

		for(int i = 0; i < 1000; i++) {
			final int index = i;
			executorService.execute(() -> {
				Article article = testRestTemplate.getForObject(url, Article.class);
//				Article article = articleService.getArticle(1);
				log.info("index:"+index +",article:" + JSON.toJSONString(article));

			});
		}

		executorService.shutdown();

		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


}
