package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.exception.GgktException;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-02-15
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping(value = "/admin/vod/teacher")
//@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean removeByIds = teacherService.removeByIds(idList);
        if (removeByIds){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        teacherService.updateById(teacher);
        return Result.ok(null);
    }

    @ApiOperation(value = "获取")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    @ApiOperation(value = "新增")
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }
    }

    //条件查询分页列表
    @ApiOperation(value = "条件查询分页列表")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result index(@PathVariable long current,
                        @PathVariable long limit,
                        @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //创建page对象，传递当前页和每页记录数
        Page<Teacher> pageParam = new Page<>(current, limit);
        if (teacherQueryVo == null){
                IPage<Teacher> teacherPage = teacherService.page(pageParam, null);
                return Result.ok(teacherPage);
        }else {
            //获取条件值
            String name = teacherQueryVo.getName();//讲师名称
            Integer level = teacherQueryVo.getLevel();//讲师级别
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();//开始时间
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();//结束时间
            //封装条件
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(name)) {
                wrapper.like("name",name);
            }
            if(!StringUtils.isEmpty(level)) {
                wrapper.eq("level",level);
            }
            if(!StringUtils.isEmpty(joinDateBegin)) {
                wrapper.ge("join_date",joinDateBegin);
            }
            if(!StringUtils.isEmpty(joinDateEnd)) {
                wrapper.le("join_date",joinDateEnd);
            }
            //调用方法得到分页查询结果
            IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);
            return Result.ok(pageModel);
        }
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true)
                                @PathVariable long id){
        boolean removeById = teacherService.removeById(id);
        if (removeById){
            return Result.ok(null);
        }else {
            return Result.fail(null);
        }

    }

    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher(){
//        try {
//            int i = 10/0;
//        } catch (Exception e) {
//            throw new GgktException(201,"执行自定义处理异常GgktException");
//        }
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询数据成功！");
    }


    //    @ApiOperation("逻辑删除讲师")
//    @DeleteMapping("remove/{id}")
//    public boolean removeTeacher(@PathVariable long id){
//        boolean removeById = teacherService.removeById(id);
//        return removeById;
//    }

//    @ApiOperation("所有讲师列表")
//    @GetMapping("findAll")
//    public List<Teacher> findAllTeacher(){
//        List<Teacher> list = teacherService.list();
//        return list;
//    }

}

