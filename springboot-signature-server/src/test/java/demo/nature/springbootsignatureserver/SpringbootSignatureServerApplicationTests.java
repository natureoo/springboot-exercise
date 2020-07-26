package demo.nature.springbootsignatureserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.nature.springbootsignatureserver.data.request.Contact;
import demo.nature.springbootsignatureserver.data.request.RequestHeader;
import demo.nature.springbootsignatureserver.data.request.RequestHolder;
import demo.nature.springbootsignatureserver.data.request.RequestPayload;
import demo.nature.springbootsignatureserver.util.SignatureTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.LocalDateTime;

@SpringBootTest(classes = SpringbootSignatureServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class SpringbootSignatureServerApplicationTests {

    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     */
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private SignatureTool signatureTool;

    @Test
    void testModifyContact() throws IOException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, SignatureException, InvalidKeyException {
        String url = "http://localhost:"+port + "/test/contact";

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

        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(requestPayload);
        String signature = signatureTool.sign(payload);
        requestHolder.setSignature(signature);

        String result = testRestTemplate.postForObject(url, requestHolder, String.class);
        log.info("post result[{}]", result);
    }



}
