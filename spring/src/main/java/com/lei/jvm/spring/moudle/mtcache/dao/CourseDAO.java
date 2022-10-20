package com.lei.jvm.spring.moudle.mtcache.dao;

import com.lei.jvm.spring.moudle.mtcache.pojo.CourseDTO;
import com.lei.jvm.spring.moudle.mtcache.pojo.CourseEntity;
import org.springframework.stereotype.Component;

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
