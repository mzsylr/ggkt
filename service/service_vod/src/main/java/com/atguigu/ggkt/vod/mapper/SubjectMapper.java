package com.atguigu.ggkt.vod.mapper;

import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author mz_Andy
 * @Create 2023/02/18/16:13
 * @Description
 */
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {
    List<Subject> myselectList(QueryWrapper<Subject> wrapper);

   // Integer selectCount(QueryWrapper<Subject> wrapper);
}
