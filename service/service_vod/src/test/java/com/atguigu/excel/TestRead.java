package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @Author mz_Andy
 * @Create 2023/02/18/15:59
 * @Description
 */
public class TestRead {
    public static void main(String[] args) {
        String fileName = "F:\\gugu.xlsx";
        EasyExcel.read(fileName,User.class,new ExcelListener()).sheet().doRead();
    }
}
