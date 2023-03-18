package com.atguigu.ggkt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author mz_Andy
 * @Create 2023/02/16/10:25
 * @Description
 */
@Data
@AllArgsConstructor
public class GgktException extends RuntimeException{
    private Integer code;
    private String msg;
}
