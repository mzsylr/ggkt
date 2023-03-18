package com.atguigu.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.exception.GgktException;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.listener.SubjectListener;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import com.atguigu.ggkt.vod.service.SubjectService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-02-18
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public List<Subject> selectSubjectList(Long id) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);
        //向list集合每个Subject对象中设置hasChildren
        for (Subject subject:subjectList) {
            Long subjectId = subject.getId();
            boolean isChild = this.isChildren(subjectId);
            subject.setHasChildren(isChild);
        }
        return subjectList;
    }
    private boolean isChildren(Long id) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        return count>0;
    }

//    //这里我的优化思路是利用stream流结合sql语句进行优化
//    //1.首先还是查询出list集合
//    List<ProjectUser> projectList = projectUserDao.selectByProjectId(projectId);
//    //2.然后用stream流对这个集合的关键字段进行转换筛选处理,这里收集所有的userId，待会让sqlfor循环。
//    List<String> userIdList = projectList.stream().map(ProjectUser::getUserId).collect(Collectors.toList());
//    //3.写一条sql语句让sql做循环（效果比你for循环里查询数据库好）
//    List<UserInfoDTO> userInfoDTOList = projectUserDao.selectByUserIds(userIdList);
//    //4.拿到list集合利用stream流把他转换成map类型
//    Map<String, UserInfoDTO> userInfoMap = userInfoDTOList.stream().collect(Collectors.toMap(UserInfoDTO::getUserId, Function.identity()))
//    //5.接下来就是填充数据了，
//    for(
//    String userId:userIdList)
//
//    {
//        UserInfo userinfo = new UserInfo();
//        UserInfoDTO userInfoDTO = userInfoMap.get(userId);
//        userinfo.setUserName(userInfoDTO.getUserName());
//        userinfo.setUserId(userInfoDTO.getId());
//        userinfo.setImageUrl(userInfoDTO.getImageUrl());
//        userInfoList.add(userinfo);
//    }
//    userInfoVO.setProjectId(projectId);
//    userInfoVO.setUserInfoList(userInfoList);



    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            List<Subject> dictList = baseMapper.selectList(null);
            List<SubjectEeVo> dictVoList = new ArrayList<>(dictList.size());
            for(Subject dict : dictList) {
                SubjectEeVo dictVo = new SubjectEeVo();
                BeanUtils.copyProperties(dict,dictVo);
                dictVoList.add(dictVo);
            }
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(dictVoList);
        } catch (IOException e) {
            throw new GgktException(20001,"导出失败");
        }
    }

    @Autowired
    private SubjectListener subjectListener;

    @Override
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                    SubjectEeVo.class,subjectListener).sheet().doRead();
        } catch (IOException e) {
            throw new GgktException(20001,"导入失败");
        }
    }
}
