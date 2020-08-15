package demo.nature.springbootsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringbootSecurityApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testBCryptPasswordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode("123456");
//		assertTrue(encoder.matches("myPassword", result));
		boolean flags = encoder.matches("123456", result);
		System.out.println("result:"+result);
		System.out.println("flags:"+flags);
	}

}
