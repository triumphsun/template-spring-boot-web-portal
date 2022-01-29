package com.suntri.portal.service;

import com.suntri.portal.entity.User;

public class UserListDto {

    private String username;
    private String email;

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public static UserListDto withUser(User user){
        UserListDto dto = new UserListDto();
        dto.username = user.getUsername();
        dto.email = user.getEmail();
        return dto;
    }
}
