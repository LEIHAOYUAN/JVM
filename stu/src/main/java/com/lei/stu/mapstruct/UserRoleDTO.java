package com.lei.stu.mapstruct;

import lombok.Data;

@Data
public class UserRoleDTO {

    private Long userId;
    private String userName;
    private String userAddr;
    private String roleName;
}