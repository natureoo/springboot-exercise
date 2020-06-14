package demo.nature.springbootweb.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author nature
 * @date 14/6/2020 12:12 下午
 * @email 924943578@qq.com
 *
 *
 * output:
 * LoginInterceptor preHandle
 * 2020-06-14 17:01:36.746  INFO 90429 --- [nio-8080-exec-1] d.n.springbootweb.aop.ServiceAspect      : ServiceAspect doBefore======================incoming API is >>>> uuid <<<<, request parameter is :2====================
 * in randUUID before process!
 * in randUUID finally!
 * 2020-06-14 17:01:37.768  INFO 90429 --- [nio-8080-exec-1] d.n.springbootweb.aop.ServiceAspect      : doAfter! args: [2]
 * 2020-06-14 17:01:37.768  INFO 90429 --- [nio-8080-exec-1] d.n.springbootweb.aop.ServiceAspect      : ServiceAspect afterReturning======================response is :"8e5fd988-8632-45ef-91fb-8ec9c180422f|2" ====================
 * 2020-06-14 17:01:37.770  INFO 90429 --- [nio-8080-exec-1] d.n.springbootweb.aop.ServiceAspect      : ServiceAspect afterReturning======================total time  is :1069 ms====================
 * LoginInterceptor postHandle
 * LoginInterceptor afterCompletion
 */
@Aspect
@Slf4j
@Component
public class ServiceAspect {

    private ThreadLocal<Long> timeThead = new ThreadLocal<Long>();


    /**
     *
     * 例子：
     *
     * execution(* com.jiuxian..service.*.*(..))
     *
     * execution 表达式的主体
     * 第一个* 代表任意的返回值
     * com.jiuxian aop所横切的包名
     * 包后面.. 表示当前包及其子包
     * 第二个* 表示类名，代表所有类
     * .*(..) 表示任何方法,括号代表参数 .. 表示任意参数
     *
     *
     * 匹配所有public方法：
     * execution(public * *(..))
     * 匹配名字以set开头的方法：
     * execution(* set*(..))
     * 匹配cn.larry.aop.service.AccountService接口中的任意方法：
     * execution(* cn.larry.aop.service.AccountService.*(..))
     * 匹配cn.larry.aop.service包中的任意方法：
     * execution(* cn.larry.aop.service.*.*(..))
     * 匹配cn.larry.aop.service包及其子包中的任意方法【service后面两个点】：
     * execution(* cn.larry.aop.service..*.*(..))
     */

    @Pointcut("execution(* demo.nature.springbootweb.controller.*.*(..))")
    public void point() {
    }

//    @Before("execution(public * demo.nature.springbootaop.service.*.*(*))")
    @Before("point()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object arg1 = null;
        if (ArrayUtils.isNotEmpty(args)) {
            arg1 = args[0];
        }
        timeThead.set(System.currentTimeMillis());
        String name = joinPoint.getSignature().getName();
        log.info("ServiceAspect doBefore======================incoming API is >>>> "+name+" <<<<, request parameter is :"+ JSON.toJSONString(arg1)+"====================");
    }

    @After("point()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("doAfter! args: " + JSON.toJSONString(joinPoint.getArgs()));

    }

    @AfterReturning(value = "point()", returning = "response")
    public void afterReturning (JoinPoint joinPoint,Object response) throws Exception {
        Long startTime = timeThead.get();
        long endTime = System.currentTimeMillis();
        log.info("ServiceAspect afterReturning======================response is :{} ====================",JSON.toJSONString(response));

        log.info("ServiceAspect afterReturning======================total time  is :{} ms====================", (endTime - startTime));
    }


    @AfterThrowing("point()")
    public void afterThrowing() {
        log.info("afterThrowing afterThrowing  rollback");
    }


}