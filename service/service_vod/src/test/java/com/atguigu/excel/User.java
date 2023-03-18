package com.atguigu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author mz_Andy
 * @Create 2023/02/18/15:45
 * @Description
 */
@Data
public class User {

    @ExcelProperty(value = "学生编号", index = 0)
    private Integer id;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String name;

}
