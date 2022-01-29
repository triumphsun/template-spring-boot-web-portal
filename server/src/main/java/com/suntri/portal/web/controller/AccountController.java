package com.suntri.portal.web.controller;

import com.suntri.portal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        path = {"/account/roles"}
    )
    public ModelAndView listRoles(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("roles", this.accountService.listRoles());
        mav.setViewName("listRoles");
        return mav;
    }
}
