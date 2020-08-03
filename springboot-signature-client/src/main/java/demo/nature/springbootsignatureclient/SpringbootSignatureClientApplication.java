package demo.nature.springbootsignatureclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringbootSignatureClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSignatureClientApplication.class, args);
    }

}
