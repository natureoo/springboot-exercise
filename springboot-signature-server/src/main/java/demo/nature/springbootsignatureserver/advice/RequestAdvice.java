package demo.nature.springbootsignatureserver.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.nature.springbootsignatureserver.data.request.RequestHolder;
import demo.nature.springbootsignatureserver.data.request.RequestPayload;
import demo.nature.springbootsignatureserver.util.SignatureTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.CertificateException;

/**
 * @author nature
 * @date 26/7/2020 12:47 下午
 * @email 924943578@qq.com
 */
@RestControllerAdvice
@Slf4j
public class RequestAdvice extends RequestBodyAdviceAdapter {

    @Autowired
    private SignatureTool signatureTool;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        final HttpHeaders headers = inputMessage.getHeaders();
        log.info("RequestAdvice beforeBodyRead headers[{}]", headers);
        byte[] body = new byte[inputMessage.getBody().available()];
        inputMessage.getBody().read(body);
        final ObjectMapper objectMapper = new ObjectMapper();
        RequestHolder requestHolder = objectMapper.readValue(body, RequestHolder.class);
        log.info("RequestAdvice beforeBodyRead body[{}]", objectMapper.writeValueAsString(requestHolder));
        final String expectedSignature = requestHolder.getSignature();
        RequestPayload request = requestHolder.getRequest();
        String payLoad = objectMapper.writeValueAsString(request);
        boolean flags = false;
        try {
            flags = signatureTool.verify(payLoad, expectedSignature);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        log.info("signature verify flags[{}]", flags);
        return inputMessage;
    }
}
