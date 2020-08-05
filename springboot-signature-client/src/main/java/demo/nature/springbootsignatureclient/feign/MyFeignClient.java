package demo.nature.springbootsignatureclient.feign;

import demo.nature.data.request.Contact;
import demo.nature.data.request.RequestHolder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author nature
 * @date 1/8/2020 10:30 下午
 * @email 924943578@qq.com
 */
@FeignClient(value = "myfeignclient")
public interface MyFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/test/contact")
    public Contact modifyContact(@RequestBody RequestHolder<Contact> requestContact);
//    public Contact modifyContact(@RequestBody RequestPayload<Contact> requestContact);
}
