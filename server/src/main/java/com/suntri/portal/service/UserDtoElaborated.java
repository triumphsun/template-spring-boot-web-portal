package com.suntri.portal.service;

import com.suntri.portal.entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserDtoElaborated {

    private static DateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private Long id;
    private String username;
    private String password;
    private String email;
    private String createdAt;
    private String updatedAt;

    public Long getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public static UserDtoElaborated withUser(User user){
        UserDtoElaborated dto = new UserDtoElaborated();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.email = user.getEmail();
        dto.createdAt = dateFormatter.format(user.getCreatedAt());
        dto.updatedAt = user.getUpdatedAt()!=null ? dateFormatter.format(user.getUpdatedAt()) : "Not Available.";
        return dto;
    }

}
