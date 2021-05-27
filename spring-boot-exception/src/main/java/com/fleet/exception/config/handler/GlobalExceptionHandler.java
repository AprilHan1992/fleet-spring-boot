package com.fleet.exception.config.handler;

import com.fleet.exception.config.exception.BaseException;
import com.fleet.exception.json.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.sql.SQLException;

/**
 * @author April Han
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public R handleBaseException(BaseException e) {
        return R.error(e.getCode(), e.getMsg());
    }

    /**
     * 全局异常捕捉处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public R handleException(Exception e) {
        if (e != null) {
            if (e instanceof RuntimeException) {
                if (e instanceof NullPointerException) {
                    logger.error("空指针引用异常：" + e.getMessage(), e);
                } else if (e instanceof ClassCastException) {
                    logger.error("类型强制转换异常：" + e.getMessage(), e);
                } else if (e instanceof IllegalArgumentException) {
                    if (e instanceof NumberFormatException) {
                        logger.error("数字格式异常：" + e.getMessage(), e);
                    } else {
                        logger.error("传递非法参数异常：" + e.getMessage(), e);
                    }
                } else if (e instanceof ArithmeticException) {
                    logger.error("算术运算异常：" + e.getMessage(), e);
                } else if (e instanceof ArrayStoreException) {
                    logger.error("向数组中存放与声明类型不兼容对象异常：" + e.getMessage(), e);
                } else if (e instanceof IndexOutOfBoundsException) {
                    logger.error("下标越界异常：" + e.getMessage(), e);
                } else if (e instanceof NegativeArraySizeException) {
                    logger.error("创建一个大小为负数的数组错误异常：" + e.getMessage(), e);
                } else if (e instanceof SecurityException) {
                    logger.error("安全异常：" + e.getMessage(), e);
                } else if (e instanceof UnsupportedOperationException) {
                    logger.error("不支持的操作异常：" + e.getMessage(), e);
                } else if (e instanceof TypeMismatchException) {
                    logger.error("参数类型不匹配异常：" + e.getMessage(), e);
                } else {
                    logger.error("其它RuntimeException异常：" + e.getMessage(), e);
                }
            } else if (e instanceof IOException) {
                if (e instanceof EOFException) {
                    logger.error("文件已结束异常：" + e.getMessage(), e);
                } else if (e instanceof FileNotFoundException) {
                    logger.error("文件未找到异常：" + e.getMessage(), e);
                } else if (e instanceof InterruptedIOException) {
                    logger.error("I/O操作被意外终止异常：" + e.getMessage(), e);
                } else {
                    logger.error("其它IOException异常：" + e.getMessage(), e);
                }
            } else if (e instanceof SQLException) {
                logger.error("数据库操作异常：" + e.getMessage(), e);
            } else if (e instanceof ServletException) {
                if (e instanceof NoHandlerFoundException) {
                    logger.error("未找到异常：" + e.getMessage(), e);
                } else if (e instanceof HttpRequestMethodNotSupportedException) {
                    logger.error("不支持的方法：" + e.getMessage(), e);
                } else if (e instanceof MissingServletRequestParameterException) {
                    logger.error("缺少参数异常：" + e.getMessage(), e);
                } else {
                    logger.error("其它ServletException异常：" + e.getMessage(), e);
                }
            } else {
                if (e instanceof NoSuchMethodException) {
                    logger.error("方法未找到异常：" + e.getMessage(), e);
                } else {
                    logger.error("其它异常：" + e.getMessage(), e);
                }
            }
        }
        return R.error();
    }
}
