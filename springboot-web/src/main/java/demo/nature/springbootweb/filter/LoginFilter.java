package demo.nature.springbootweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author nature
 * @date 13/6/2020 11:20 下午
 * @email 924943578@qq.com
 *
 * output:
 * before LoginFilter doFilter
 * LoginInterceptor preHandle
 * in login
 * LoginInterceptor postHandle
 * LoginInterceptor afterCompletion
 * after LoginFilter doFilter
 */

@WebFilter(urlPatterns="/jsp/login") //urlPatterns not working
//@WebFilter
public class LoginFilter implements Filter {

    //servlet容器启动时执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("before LoginFilter doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("after LoginFilter doFilter");
    }

    @Override
    public void destroy() {
        System.out.println(" LoginFilter destroy");
    }
}
