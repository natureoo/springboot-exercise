package demo.nature.springbootsignatureserver.ctrl;

import demo.nature.springbootsignatureserver.data.request.Contact;
import demo.nature.springbootsignatureserver.data.request.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nature
 * @date 26/7/2020 4:37 下午
 * @email 924943578@qq.com
 */
@RestController
@RequestMapping(value = "test")
@Slf4j
public class TestController {


    @RequestMapping(value = "contact")
    public void modifyContact(@RequestBody RequestHolder<Contact> requestContact){
        Contact contact = requestContact.getRequest().getData();
        log.info("contact[{}]", contact);
    }
}
