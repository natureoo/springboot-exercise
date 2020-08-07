package demo.nature.springbootsignatureclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.nature.data.request.Contact;
import demo.nature.data.request.RequestHeader;
import demo.nature.data.request.RequestHolder;
import demo.nature.data.request.RequestPayload;
import demo.nature.springbootsignatureclient.feign.MyFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.LocalDateTime;

@SpringBootTest(classes = SpringbootSignatureClientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class SpringbootSignatureClientApplicationTests {


    @Autowired
    private MyFeignClient myFeignClient;

    private ObjectMapper objectMapper = new ObjectMapper();



    @Test
    void testModifyContact() throws IOException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, SignatureException, InvalidKeyException {

        RequestHolder<Contact> requestHolder = new RequestHolder<>();
        RequestPayload<Contact> requestPayload = new RequestPayload<>();
        RequestHeader header = new RequestHeader();
        header.setClientId("Test-001");
        header.setMsgId("Msg-001");
        header.setRequestTime(LocalDateTime.now());
        requestPayload.setHeader(header);

        Contact contact = new Contact();
        contact.setName("zhangsan");
        contact.setPhone("999");
        requestPayload.setData(contact);

        requestHolder.setRequest(requestPayload);



        Contact result = myFeignClient.modifyContact(requestPayload);
        log.info("get result[{}]", objectMapper.writeValueAsString(result));
    }

}
