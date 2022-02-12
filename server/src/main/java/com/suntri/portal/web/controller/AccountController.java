package com.suntri.portal.web.controller;

import com.suntri.portal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(
        path = {"/account/users"}
    )
    public ModelAndView listUsers(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", this.accountService.listUsers());
        mav.setViewName("listUsers");
        return mav;
    }

    @RequestMapping(
            path = {"/account/users/{id}"}
    )
    public ModelAndView getUser(@PathVariable Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", this.accountService.getUser(id).get());
        mav.setViewName("getUser");
        return mav;
    }

    @RequestMapping(
        path = {"/account/roles"}
    )
    public ModelAndView listRoles(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("roles", this.accountService.listRoles());
        mav.setViewName("listRoles");
        return mav;
    }

    @RequestMapping(
            path = {"/account/roles/{id}"}
    )
    public ModelAndView getRole(@PathVariable Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("role", this.accountService.getRole(id).get());
        mav.setViewName("getRole");
        return mav;
    }
}
