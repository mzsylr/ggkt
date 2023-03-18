package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vod.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-02-19
 */
@RestController
@RequestMapping(value = "/admin/vod/chapter")
//@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @ApiOperation("大纲列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getNestedTreeList( @ApiParam(value = "课程ID",required = true) @PathVariable Long courseId){

        List<ChapterVo> chapterVoList = chapterService.getNestedTreeList(courseId);
        return Result.ok(chapterVoList);
    }

    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter){

        chapterService.save(chapter);
        return Result.ok(null);
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }

    @PostMapping("update")
    public Result update(@RequestBody Chapter chapter){

        chapterService.updateById(chapter);
        return Result.ok(null);
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        chapterService.removeById(id);
        return Result.ok(null);
    }



}

