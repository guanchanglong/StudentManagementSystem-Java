package com.xupt.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author：小关同学爱吃汉堡
 * @date: 2020/12/16 22:15
 */
//切面注解
@Aspect
//开启组件扫描
@Component
public class LogAspect {

    //创建一个日志对象
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //拦截controller包下的所有方法
    @Pointcut("execution(* com.xupt.demo.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    //JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象
    public void logBefore(JoinPoint joinPoint){
        //RequestContextHolder：持有上下文的Request容器
        //通过RequestContextHolder的静态方法可以随时随地取到当前请求的request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //这里设置一个断言
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        //获取url、ip、classMethod和args方法参数
        String url = request.getRequestURI();
        String ip = getRemoteIP(request);
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("Request : {}",requestLog);
    }

    //该方法在log()这个切面之后执行
    @After("log()")
    public void doAfter(){
        //logger.info("-------doAfter---------");
    }

    //returning = "result"为返回的内容，pointcut为切面内容
    @AfterReturning(returning = "result",pointcut = "log()")
    //定义一个Object类型来接受返回的类型
    public void doAfterReturn(Object result){
        logger.info("Result：{}",result);
    }

    //获取客户端的IP地址的方法是：request.getRemoteAddr()，这种方法在大部分情况下都是有效的。
    //但是在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址了。
    //如果使用了反向代理软件，用request.getRemoteAddr()方法获取的IP地址是：127.0.0.1或192.168.1.110，而并不是客户端的真实IP。
    //经过代理以后，由于在客户端和服务之间增加了中间层，因此服务器无法直接拿到客户端的IP，服务器端应用也无法直接通过转发请求的地址返回给客户端。
    //但是在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。
    //用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址。
    //当我们访问index.jsp/时，其实并不是我们浏览器真正访问到了服务器上的index.jsp文件，而是先由代理服务器去访问index.jsp ，
    //代理服务器再将访问到的结果返回给我们的浏览器，
    //因为是代理服务器去访问index.jsp的，所以index.jsp中通过request.getRemoteAddr()的方法获取的IP实际上是代理服务器的地址，并不是客户端的IP地址。
    //可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
    //答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。如：
    //X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
    //用户真实IP为： 192.168.1.110
    //于是可得出获得客户端真实IP地址的方法一：
    public String getRemoteIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    //定义一个内部类来方便接受请求url、访问者的ip、调用的方法classMethod和传递的参数args
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        //因为传递的参数可能会有点多，所以我们使用对象数组来储存请求的参数
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
