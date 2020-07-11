//package demo.nature.springbootsession.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//
///**
// * @author nature
// * @date 10/7/2020 11:29 下午
// * @email 924943578@qq.com
// */
//@Configuration
//public class SessionConfig {
//
//    /**
//     * 支持同一个一级域名下session共享
//     * @return
//     */
//    @Bean
//    public DefaultCookieSerializer defaultCookieSerializer(){
//        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
//        defaultCookieSerializer.setCookiePath("/");
//        defaultCookieSerializer.setDomainName("demo.com");
//        return defaultCookieSerializer;
//    }
//}
