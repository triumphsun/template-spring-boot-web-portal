package com.suntri.portal.service;

import com.suntri.portal.entity.Role;

public class RoleListDto {

    private String name;

    public String getName(){
        return this.name;
    }

    public static RoleListDto withRole(Role role){
        RoleListDto dto = new RoleListDto();
        dto.name = role.getName();
        return dto;
    }

}
