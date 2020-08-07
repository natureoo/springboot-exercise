package demo.nature.springbootsignatureclient.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.nature.data.request.RequestHolder;
import demo.nature.data.request.RequestPayload;
import demo.nature.util.SignatureTool;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @author nature
 * @date 1/8/2020 11:29 下午
 * @email 924943578@qq.com
 */
@Component
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("RequestInterceptor method [{}]", requestTemplate.method());
        final Request.Body body = requestTemplate.requestBody();
        try {
            RequestPayload requestPayload = objectMapper.readValue(body.asBytes(), RequestPayload.class);
            String payload = objectMapper.writeValueAsString(requestPayload);
            String signature = SignatureTool.getInstance().sign(payload);
            RequestHolder requestHolder = new RequestHolder();
            requestHolder.setRequest(requestPayload);
            requestHolder.setSignature(signature);
            log.info("RequestInterceptor body [{}]", requestHolder);

            byte[] bytes = objectMapper.writeValueAsBytes(requestHolder);
            Request.Body newBody = Request.Body.encoded(bytes, StandardCharsets.UTF_8);
            requestTemplate.body(newBody);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }


    }
}
