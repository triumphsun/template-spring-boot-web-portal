package com.suntri.portal.service;

import com.suntri.portal.entity.Role;
import com.suntri.portal.entity.User;
import com.suntri.portal.repository.RoleRepository;
import com.suntri.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserListDto> listUsers(){
        List<User> users = this.userRepository.findAll();
        List<UserListDto> dtos = users.stream().map(UserListDto::withUser).collect(Collectors.toList());
        return dtos;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RoleListDto> listRoles(){
        List<Role> roles = this.roleRepository.findAll();
        List<RoleListDto> dtos = roles.stream().map(RoleListDto::withRole).collect(Collectors.toList());
        return dtos;
    }
}
