package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mz_Andy
 * @Create 2023/02/18/15:47
 * @Description
 */
public class TestWrite {
    public static void main(String[] args) {
      String fileName = "F:\\gugu.xlsx";
        EasyExcel.write(fileName,User.class)
                .sheet("写操作")
                .doWrite(data());
    }

    public static List<User> data(){
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setId(i);
            data.setName("gugu" + i);
            list.add(data);
        }
        return list;
    }
}
