package com.lei.jvm.spring.service.mtcache.dao;

import com.lei.jvm.spring.service.mtcache.pojo.CourseDTO;
import com.lei.jvm.spring.service.mtcache.pojo.CourseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/10/17 9:29
 */
@Component
public interface CourseDAO {

    CourseEntity findCourse(CourseDTO param);

    void updateCourse(CourseDTO param);
}
