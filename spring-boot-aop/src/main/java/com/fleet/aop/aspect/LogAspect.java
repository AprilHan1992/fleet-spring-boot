package com.fleet.aop.aspect;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author April Han
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.fleet..*.controller..*.*(..)) || @annotation(com.fleet.aop.aspect.annotation.Log)")
    public void doPointcut() {
    }

    @Before("doPointcut()")
    public void doBefore() {
        System.out.println("执行 Before");
    }

    @Around("doPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("执行 Around");
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        logger.info("【请求URL】：{}", request.getRequestURL());
        logger.info("【请求IP】：{}", request.getRemoteAddr());
        logger.info("【请求方式】：{}", request.getMethod());
        logger.info("【请求方法名】：{}", signature.getDeclaringTypeName() + "." + signature.getName() + "()");

        // 参数名称
        String[] argNames = signature.getParameterNames();
        // 参数值
        Object[] argValues = pjp.getArgs();
        List<String> paramList = new ArrayList<>();
        if (argNames != null) {
            for (int i = 0; i < argNames.length; i++) {
                paramList.add(argNames[i] + ": " + JSON.toJSONString(argValues[i]));
            }
        }
        logger.info("【请求参数】：{}", StringUtils.join(paramList, ", "));

        return pjp.proceed();
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
