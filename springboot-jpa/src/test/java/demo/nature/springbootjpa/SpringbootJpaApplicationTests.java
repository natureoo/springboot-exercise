package demo.nature.springbootjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * 其中，classes属性指定启动类，SpringBootTest.WebEnvironment.RANDOM_PORT经常和测试类中@LocalServerPort一起在注入属性时使用。会随机生成一个端口号。
 */
@SpringBootTest(classes = SpringbootJpaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootJpaApplicationTests {


	/**
	 * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
	 */
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void concurrentComment() {
		String url = "http://localhost:"+port + "/jpa/comment";
		for (int i = 0; i < 100; i++) {
			int finalI = i;
			new Thread(() -> {
				MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
				params.add("articleId", "1");
				params.add("content", "测试内容" + finalI);
				String result = testRestTemplate.postForObject(url, params, String.class);
			}).start();
		}

	}

}
