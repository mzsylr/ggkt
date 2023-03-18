package com.atguigu.ggkt.exception;

import com.atguigu.ggkt.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author mz_Andy
 * @Create 2023/02/16/10:12
 * @Description
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        System.out.println("全局...");
        e.printStackTrace();
        return Result.fail(null).message("执行全局异常处理！");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        System.out.println("特定...");
        e.printStackTrace();
        return Result.fail(null).message("执行ArithmeticException 异常处理！");
    }

    @ExceptionHandler(GgktException.class)
    @ResponseBody
    public Result error(GgktException e){
        System.out.println("特定...");
        e.printStackTrace();
        return Result.fail(null).code(e.getCode()).message(e.getMsg());
    }

}
