package com.fleet.mybatis.pagehelper.config.aspect;

import com.fleet.mybatis.pagehelper.page.PagerUtil;
import com.fleet.mybatis.pagehelper.page.entity.Pager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 自动分页AOP拦截器（service方法名要带后缀Pager，参数中要带Pager pager，否则会按照正常处理）
 */
@Aspect
@Component
public class PagerAspect {

    @Around("execution(* com.fleet.mybatis.pagehelper.service.*.*Pager(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取连接点方法运行时的入参列表
        Object[] args = pjp.getArgs();
        Pager pager = null;
        if (args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof Pager) {
                    pager = (Pager) arg;
                    break;
                }
            }
        }

        if (pager != null) {
            PageHelper.startPage(pager.getPageIndex(), pager.getPageRows());
            PagerUtil<?> pagerUtil = (PagerUtil<?>) pjp.proceed();
            PageInfo<?> pageInfo = new PageInfo<>(pagerUtil.getList());
            pager.setTotalRows((int) pageInfo.getTotal());
            pagerUtil.setPager(pager);
            return pagerUtil;
        } else {
            return pjp.proceed();
        }
    }
}
