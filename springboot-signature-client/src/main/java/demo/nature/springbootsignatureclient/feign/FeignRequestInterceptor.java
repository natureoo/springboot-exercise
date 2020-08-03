package demo.nature.springbootsignatureclient.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author nature
 * @date 1/8/2020 11:29 下午
 * @email 924943578@qq.com
 */
@Component
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("RequestInterceptor method [{}]", requestTemplate.method());
        log.info("RequestInterceptor body [{}]", requestTemplate.requestBody());

    }
}
