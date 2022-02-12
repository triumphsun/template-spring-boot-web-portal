package com.suntri.portal.service;

import com.suntri.portal.entity.Role;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RoleDtoElaborated {

    private static DateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private Long id;
    private String name;
    private String createdAt;
    private String updatedAt;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public static RoleDtoElaborated withRole(Role role){
        RoleDtoElaborated dto = new RoleDtoElaborated();
        dto.id = role.getId();
        dto.name = role.getName();
        dto.createdAt = dateFormatter.format(role.getCreatedAt());
        dto.updatedAt = role.getUpdatedAt()!=null ? dateFormatter.format(role.getUpdatedAt()) : "Not Available.";
        return dto;
    }
}
