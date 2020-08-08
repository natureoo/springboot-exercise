package demo.nature.springbootsignatureclient.feign;

import feign.*;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nature
 * @date 1/8/2020 11:32 下午
 * @email 924943578@qq.com
 */
@Configuration
@Slf4j
public class FeignConfig {

    @Value("${myfeignclient.feign.retry.period}")
    private long period;

    @Value("${myfeignclient.feign.retry.maxPeriod}")
    private long maxPeriod;

    @Value("${myfeignclient.feign.retry.maxAttempts}")
    private int maxAttempts;

    @Autowired
    public FeignRequestInterceptor feignRequestInterceptor;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer.Default defaultRetryer(){
        return new Retryer.Default(period, maxPeriod, maxAttempts);
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new ErrorDecoder() {
            @Override
            public Exception decode(String s, Response response) {
                log.info("response.status: [{}]", response.status() );
                if (response.status() == 503) {
                    throw new RetryableException(
                            response.status(),
                            "Service Unavailable",
                            response.request().httpMethod(),
                            null);
                } else {
                    return new RuntimeException("error");
                }
            }
        };


    }



    @Bean
    public MyFeignClient myFeignClient(){
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(feignLoggerLevel())
                .contract(new SpringMvcContract())
                .retryer(defaultRetryer())
//                .errorDecoder(errorDecoder())
//                .options(new Request.Options(10000, 20000))
                .requestInterceptor(feignRequestInterceptor)
                .target(MyFeignClient.class, "http://127.0.0.1:8085");
    }
}
