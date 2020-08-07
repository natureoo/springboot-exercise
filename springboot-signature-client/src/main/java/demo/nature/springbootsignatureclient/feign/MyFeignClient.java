package demo.nature.springbootsignatureclient.feign;

import demo.nature.data.request.Contact;
import demo.nature.data.request.RequestPayload;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author nature
 * @date 1/8/2020 10:30 下午
 * @email 924943578@qq.com
 */
@FeignClient(value = "myfeignclient", fallbackFactory = HystrixClientFallbackFactory.class)
public interface MyFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/test/contact")
    public Contact modifyContact(@RequestBody RequestPayload<Contact> requestContact);
}

@Component
class HystrixClientFallbackFactory implements FallbackFactory<MyFeignClient> {
    @Override
    public MyFeignClient create(Throwable cause) {
        return new MyFeignClient() {
            @Override
            public Contact modifyContact(RequestPayload<Contact> requestContact) {
                return new Contact("fallback; reason was: " + cause.toString(), "999");
            }
        };
    }
}
