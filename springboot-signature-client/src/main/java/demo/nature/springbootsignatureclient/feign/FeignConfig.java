package demo.nature.springbootsignatureclient.feign;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nature
 * @date 1/8/2020 11:32 下午
 * @email 924943578@qq.com
 */
@Configuration
public class FeignConfig {

    @Autowired
    public FeignRequestInterceptor feignRequestInterceptor;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public MyFeignClient myFeignClient(){
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(feignLoggerLevel())
                .contract(new SpringMvcContract())
//                .options(new Request.Options(10000, 20000))
                .requestInterceptor(feignRequestInterceptor)
                .target(MyFeignClient.class, "http://127.0.0.1:8085");
    }
}
