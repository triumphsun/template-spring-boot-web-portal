package com.suntri.portal.service;

import com.suntri.portal.entity.Role;
import com.suntri.portal.entity.User;
import com.suntri.portal.repository.RoleRepository;
import com.suntri.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDtoSummarized> listUsers(){
        List<User> users = this.userRepository.findAll();
        List<UserDtoSummarized> dtos = users.stream().map(UserDtoSummarized::withUser).collect(Collectors.toList());
        return dtos;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Optional<UserDtoElaborated> getUser(Long id){
        Optional<User> optUser = this.userRepository.findById(id);
        if(optUser.isPresent()){
            return Optional.of(UserDtoElaborated.withUser(optUser.get()));
        } else {
            return Optional.empty();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RoleDtoSummarized> listRoles(){
        List<Role> roles = this.roleRepository.findAll();
        List<RoleDtoSummarized> dtos = roles.stream().map(RoleDtoSummarized::withRole).collect(Collectors.toList());
        return dtos;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Optional<RoleDtoElaborated> getRole(Long id){
        Optional<Role> optRole = this.roleRepository.findById(id);
        if(optRole.isPresent()){
            return Optional.of(RoleDtoElaborated.withRole(optRole.get()));
        } else {
            return Optional.empty();
        }
    }

}
