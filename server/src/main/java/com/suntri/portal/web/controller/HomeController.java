package com.suntri.portal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(
        path = {"/"},
        method = {RequestMethod.GET}
    )
    public ModelAndView welcome(){
        return new ModelAndView("welcome");
    }

    @RequestMapping(
        path = {"/home"},
        method = {RequestMethod.GET}
    )
    public ModelAndView home(){
        return new ModelAndView("home");
    }

}
