package com.atguigu.ggkt.vod.service;

import com.atguigu.ggkt.model.vod.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author mz_Andy
 * @Create 2023/02/18/16:09
 * @Description
 */
public interface SubjectService extends IService<Subject> {

    List<Subject> selectSubjectList(Long id);

    void exportData(HttpServletResponse response);

    void importDictData(MultipartFile file);
}
