package com.stu.mapstruct;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/2/27 14:20
 * @Version 1.0
 * @Description
 * https://www.cnblogs.com/mmzs/p/12735212.html
 */
@Slf4j
public class Test {

    public static void main(String[] args) {

        Role role = new Role(2L, "超级管理员", "后台管理");
        User user = new User(1L, "张三", "中国香港", role);

        UserRoleDTO userRoleDto = UserRoleMapper.INSTANCE.toUserRoleDto(user, role);
        log.info("转换结果：{}", JSON.toJSONString(userRoleDto));

    }

}
