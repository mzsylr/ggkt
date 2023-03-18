package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-02-19
 */
@Api(tags = "课程管理接口")
@RestController
@RequestMapping(value = "/admin/vod/course")
//@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    //添加课程基本信息
    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);
    }

    @ApiOperation(value = "点播课程列表")
    @GetMapping("{page}/{limit}")
    public Result cosrseList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseVo", value = "查询对象", required = false)
                    CourseQueryVo courseQueryVo) {
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String,Object> map = courseService.findPage(pageParam, courseQueryVo);
        return Result.ok(map);
    }

    @ApiOperation("根据id获取课程发布信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){

        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.ok(coursePublishVo);
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publishCourseById/{id}")
    public Result publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){

        boolean result = courseService.publishCourseById(id);
        return Result.ok(result);
    }
}

