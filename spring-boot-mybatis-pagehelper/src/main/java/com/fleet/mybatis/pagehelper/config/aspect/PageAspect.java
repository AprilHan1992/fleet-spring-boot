package com.fleet.mybatis.pagehelper.config.aspect;

import com.fleet.mybatis.pagehelper.page.PageUtil;
import com.fleet.mybatis.pagehelper.page.entity.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 自动分页AOP拦截器（service方法名要带后缀Page，参数中要带Page page，否则会按照正常处理）
 */
@Aspect
@Component
public class PageAspect {

    @Around("execution(* com.fleet.mybatis.pagehelper.service.*.*Page(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取连接点方法运行时的入参列表
        Object[] args = pjp.getArgs();
        Page page = null;
        if (args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof Page) {
                    page = (Page) arg;
                    break;
                }
            }
        }

        if (page != null) {
            PageHelper.startPage(page.getPageIndex(), page.getPageRows());
            Object object = pjp.proceed();
            if (object instanceof PageUtil<?>) {
                PageUtil<?> pageUtil = (PageUtil<?>) object;
                PageInfo<?> pageInfo = new PageInfo<>(pageUtil.getList());
                page.setTotalRows((int) pageInfo.getTotal());
                pageUtil.setPage(page);
                return pageUtil;
            } else {
                return object;
            }
        } else {
            return pjp.proceed();
        }
    }
}
