package demo.nature.springbootweb.config;

import demo.nature.springbootweb.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author nature
 * @date 13/6/2020 10:11 下午
 * @email 924943578@qq.com
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {



    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        String[] pathPatterns = {
                "/jsp/login"
        };

        String[] excludePatterns = {
//                "/jsp/config",
//                "/index",
//                "/swagger-resources/**",
//                "/swagger-ui.html",
        };

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(pathPatterns).excludePathPatterns(excludePatterns);

    }


    /**
     * 如果不加这个，会报异常
     * [nio-9090-exec-3] d.n.s.advice.GlobalExceptionHandler      : demo.nature.springbootweb.resp.AppResponse cannot be cast to java.lang.String
     *
     * java.lang.ClassCastException: demo.nature.springbootweb.resp.AppResponse cannot be cast to java.lang.String
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(0, new MappingJackson2HttpMessageConverter());
    }


//    @Bean
//    public FilterRegistrationBean filterConfig(){
//        FilterRegistrationBean registration = new FilterRegistrationBean(new LoginFilter());
//        registration.addUrlPatterns("/jsp/login");
//        return registration;
//    }
}
