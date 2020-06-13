package demo.nature.springbootweb.config;

import demo.nature.springbootweb.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
                "/**"
        };

        String[] excludePatterns = {
                "/jsp/config",
                "/index"
        };

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(pathPatterns).excludePathPatterns(excludePatterns);

    }


//    @Bean
//    public FilterRegistrationBean filterConfig(){
//        FilterRegistrationBean registration = new FilterRegistrationBean(new LoginFilter());
//        registration.addUrlPatterns("/jsp/login");
//        return registration;
//    }
}
