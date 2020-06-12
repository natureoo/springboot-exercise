package demo.nature.springboothelloworld.controller;

import demo.nature.springboothelloworld.config.PayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nature
 * @date 10/6/2020 12:11 下午
 * @email 924943578@qq.com
 */
@RestController
@RequestMapping("boot")
public class HelloController {

    @Value("${pay.url}")
    private String payUrl;

    @Value("${pay.interval}")
    private String payInterval;

    @Autowired
    private PayConfig payConfig;

    @RequestMapping("hello")
    public String hello(){
        return "Hello, Spring Boot!";
    }


    @RequestMapping("config")
    public String config(){
        System.out.println("热部署插件devtools测试----------");
        return payUrl +"-----" +payInterval + ">>  " + payConfig.getUrl() + "-----"+payConfig.getInterval();
    }
}
