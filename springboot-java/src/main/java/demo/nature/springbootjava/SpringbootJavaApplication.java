package demo.nature.springbootjava;

import demo.nature.springbootjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * output:
 * init str:Hello, Springboot-Java
 * main str:Hello, Springboot-Java
 * destroy
 */
@SpringBootApplication
public class SpringbootJavaApplication {

	@Autowired
	private  UserService userService;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootJavaApplication.class, args);
		UserService userService = context.getBean("userService", UserService.class);
		String str = userService.say();
		System.out.println("main str:" +str);


		//static can not inject, I think spring bean init has not finished
//		String str = userService.say();
//		System.out.println("str:" +str);

	}

	//init method, invoke after bean init
	@PostConstruct
	private void init(){
		String str = userService.say();
		System.out.println("init str:" +str);
	}

	@PreDestroy
	private void destroy(){
		System.out.println("destroy" );
	}

}
