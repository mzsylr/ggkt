package com.atguigu.ggkt.result;

import lombok.Data;

/**
 * @Author mz_Andy
 * @Create 2023/02/15/20:01
 * @Description
 */
@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public Result(){}

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public static<T> Result<T> ok(T data){

        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        result.setCode(20000);
        result.setMessage("success!");
        return result;
    }

    public static<T> Result<T> fail(T data){

        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        result.setCode(20001);
        result.setMessage("fail!");
        return result;
    }

}
