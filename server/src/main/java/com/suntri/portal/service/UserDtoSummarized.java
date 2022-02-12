package com.suntri.portal.service;

import com.suntri.portal.entity.User;

public class UserDtoSummarized {

    private Long id;
    private String username;
    private String email;

    public Long getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public static UserDtoSummarized withUser(User user){
        UserDtoSummarized dto = new UserDtoSummarized();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.email = user.getEmail();
        return dto;
    }
}
