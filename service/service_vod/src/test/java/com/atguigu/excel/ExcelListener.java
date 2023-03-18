package com.atguigu.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Author mz_Andy
 * @Create 2023/02/18/15:56
 * @Description
 */
public class ExcelListener extends AnalysisEventListener<User> {

    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        System.out.println(user);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
