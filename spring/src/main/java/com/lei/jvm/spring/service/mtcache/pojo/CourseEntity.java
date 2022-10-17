package com.lei.jvm.spring.service.mtcache.pojo;

import lombok.Data;

import java.util.Date;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/10/17 9:49
 */
@Data
public class CourseEntity {

    private String courseName;

    private Integer status;

    private Integer type;

    private String tags;

    private Date publishTime;


}
