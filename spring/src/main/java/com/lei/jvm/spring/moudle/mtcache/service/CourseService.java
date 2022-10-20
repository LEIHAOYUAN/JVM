package com.lei.jvm.spring.moudle.mtcache.service;

import com.exe.mtcache.core.annotation.MTUseCache;
import com.exe.mtcache.core.annotation.MTWipeCache;
import com.lei.jvm.spring.moudle.mtcache.dao.CourseDAO;
import com.lei.jvm.spring.moudle.mtcache.pojo.CourseDTO;
import com.lei.jvm.spring.moudle.mtcache.pojo.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  职能描述：mtcache使用demo
 *  @author leihaoyuan
 *  @version 2022/10/14 18:39
 */
@Service
public class CourseService {

    @Autowired
    private CourseDAO courseDAO;

    @MTUseCache(domain = "course", keys = {"#param.courseId"}, ttl = 3600L, clazz = CourseEntity.class)
    public CourseEntity findCourse(CourseDTO param) {
        // 参数校验
        validParam(param);
        // 从DB查询数据
        return courseDAO.findCourse(param);
    }


    @MTWipeCache(domain = "", keys = {"#param.courseId"})
    @Transactional(rollbackFor = Exception.class)
    public void updateCourse(CourseDTO param) {
        // 参数校验
        validParam(param);
        // 更新DB数据
        courseDAO.updateCourse(param);
    }

    private void validParam(CourseDTO param) {

    }


}
