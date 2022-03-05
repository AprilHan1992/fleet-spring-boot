package com.fleet.submit.aspect;

import com.fleet.submit.config.handler.BaseException;
import com.fleet.submit.util.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

;

/**
 * @author April Han
 */
@Aspect
@Component
public class NoRepeatSubmitAspect {

    @Resource
    RedisUtil redisUtil;

    @Pointcut("@annotation(com.fleet.submit.aspect.annotation.NoRepeatSubmit)")
    public void doPointcut() {
    }

    @Before("doPointcut()")
    public void doBefore() {
        System.out.println("执行 Before");
    }

    @Around("doPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("执行 Around");
        ServletRequestAttributes attributes = (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = attributes.getRequest();
        String sessionId = attributes.getSessionId();
        String servletPath = request.getServletPath();
        String key = sessionId + "-" + servletPath;
        if (redisUtil.get(key) == null) {
            redisUtil.setEx(key, 0, 2, TimeUnit.SECONDS);
            return pjp.proceed();
        } else {
            throw new BaseException("重复提交");
        }
    }

    @AfterThrowing(pointcut = "doPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint jp, Throwable e) {
        System.out.println("执行 AfterThrowing");
    }

    @After("doPointcut()")
    public void doAfter(JoinPoint pjp) {
        System.out.println("执行 After");
    }

    @AfterReturning(pointcut = "doPointcut()", returning = "o")
    public void doAfterReturning(JoinPoint pjp, Object o) {
        System.out.println("执行 AfterReturning");
    }
}
