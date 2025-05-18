package com.fanqie.blog.config;

import com.fanqie.blog.config.exception.BusinessException;
import com.fanqie.blog.utils.Result; // 假设你有一个统一的 Result 封装类
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// @ControllerAdvice 标注这是一个全局异常处理类，可以指定作用的包范围或注解范围
// 例如：@ControllerAdvice("com.fanqie.blog.controller") 只处理 com.fanqie.blog.controller 包下的异常
@ControllerAdvice
@ResponseBody // 如果你的接口是 RESTful API，通常需要这个注解来将返回值序列化为 JSON 等格式
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理运行时异常
     * @param e 运行时异常对象
     * @return 统一的错误结果
     */
    @ExceptionHandler(RuntimeException.class) // 标注处理 RuntimeException 类型的异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 设置响应状态码为 500
    public Result<?> handleRuntimeException(RuntimeException e) {
        logger.error("发生运行时异常：", e);
        // 返回统一的错误结果
        return Result.error("发生运行时异常，请联系管理员");
    }

    /**
     * 处理所有未被其他 @ExceptionHandler 处理的异常
     * @param e 其他异常对象
     * @return 统一的错误结果
     */
    @ExceptionHandler(Exception.class) // 标注处理所有 Exception 类型的异常 (通常作为最后的兜底处理)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 设置响应状态码为 500
    public Result<?> handleOtherException(Exception e) {
        logger.error("发生未知异常：", e);
        // 返回统一的错误结果
        return Result.error("未知错误，请联系管理员");
    }

    /**
     * 处理自定义的业务异常
     * @param e 业务异常对象
     * @return 统一的错误结果
     */
    @ExceptionHandler(BusinessException.class) // 标注处理 BusinessException 类型的异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 设置响应状态码为 500 Internal Server Error
    public Result<?> handleBusinessException(BusinessException e) {
        logger.error("发生业务异常：", e);
        // 返回统一的错误结果，包含错误码和错误信息
        return Result.error(e.getMessage());
    }

}
