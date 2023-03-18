package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author mz_Andy
 * @Create 2023/02/18/16:07
 * @Description
 */
@Api(tags = "课程分类管理")
@RestController
@RequestMapping(value="/admin/vod/subject")
//@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //查询下一层课程分类
    //根据parent_id
    @ApiOperation("课程分类列表")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectSubjectList(id);
        return Result.ok(list);
    }

    @ApiOperation(value="课程分类导出")
    @GetMapping(value = "exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    @ApiOperation(value = "课程分类导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        subjectService.importDictData(file);
        return Result.ok(null);
    }
}