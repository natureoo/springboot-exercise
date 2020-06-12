package demo.nature.springbootjava;

import demo.nature.springbootjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * output:
 * main before run
 * PostConstruct str:Hello, Springboot-Java
 * Started SpringbootJavaApplication in 2.811 seconds
 * CommandLineRunner run str:Hello, Springboot-Java
 * main after run
 * main str:Hello, Springboot-Java
 * PreDestroy
 */
@SpringBootApplication
public class SpringbootJavaApplication implements CommandLineRunner {

	@Autowired
	private  UserService userService;

	public static void main(String[] args) {
		System.out.println("main before run");
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootJavaApplication.class, args);
		System.out.println("main after run");
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
		System.out.println("PostConstruct str:" +str);
	}

	@PreDestroy
	private void destroy(){
		System.out.println("PreDestroy" );
	}

	@Override
	public void run(String... args) throws Exception {
		String str = userService.say();
		System.out.println("CommandLineRunner run str:" +str);
	}
}
