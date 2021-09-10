package com.stu.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRoleMapper {

    /**
     * 获取该类自动生成的实现类的实例
     */
    UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

    /**
     * 使用单一对象
     *
     * @param user user
     * @return UserRoleDto
     */

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "name", target = "userName"),
            @Mapping(source = "addr", target = "userAddr"),
            @Mapping(source = "role.name", target = "roleName"),
    })
    UserRoleDTO toUserRoleDto(User user);


    /**
     * 使用两个对象
     *
     * @param user user
     * @param role role
     * @return UserRoleDto
     */
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.name", target = "userName"),
            @Mapping(source = "user.addr", target = "userAddr"),
            @Mapping(source = "role.name", target = "roleName"),
    })
    UserRoleDTO toUserRoleDto(User user, Role role);
}