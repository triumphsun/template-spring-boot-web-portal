package com.suntri.portal.service;

import com.suntri.portal.entity.Role;

public class RoleDtoSummarized {

    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public static RoleDtoSummarized withRole(Role role){
        RoleDtoSummarized dto = new RoleDtoSummarized();
        dto.id = role.getId();
        dto.name = role.getName();
        return dto;
    }

}
