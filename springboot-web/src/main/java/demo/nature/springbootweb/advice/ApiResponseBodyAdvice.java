package demo.nature.springbootweb.advice;

import demo.nature.springbootweb.resp.AppResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

/**
 * 使用 @ControllerAdvice & ResponseBodyAdvice 拦截Controller方法默认返回参数，统一处理返回值/响应体
 * @author nature
 * @date 20/6/2020 5:11 下午
 * @email 924943578@qq.com
 */
@RestControllerAdvice(basePackages  =  "demo.nature.springbootweb.controller")
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Class[] annos = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class
    };
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        AnnotatedElement element = methodParameter.getAnnotatedElement();
        return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
    }

    /**
     * response AppResponse
     * @param response
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public AppResponse beforeBodyWrite(Object response, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        AppResponse appResponse = null;
        if(response instanceof AppResponse){
            appResponse = (AppResponse)response;
            appResponse.setSignature("My signature");
        }else{
            appResponse = new AppResponse("200", "invoke method succ", response, "My signature");
        }
        return appResponse;
    }
}
