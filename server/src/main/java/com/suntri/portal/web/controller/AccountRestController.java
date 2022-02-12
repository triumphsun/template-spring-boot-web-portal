package com.suntri.portal.web.controller;

import com.suntri.portal.service.AccountService;
import com.suntri.portal.service.RoleDtoSummarized;
import com.suntri.portal.service.UserDtoSummarized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(
    path = "/rest",
    produces = MediaType.APPLICATION_JSON_VALUE
)
@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(
        path = { "/account/users" },
        method = { RequestMethod.GET }
    )
    public List<UserDtoSummarized> restListUsers(){
        return this.accountService.listUsers();
    }

    @RequestMapping(
            path = { "/account/roles" },
            method = { RequestMethod.GET }
    )
    public List<RoleDtoSummarized> restListRoles(){
        return this.accountService.listRoles();
    }
}
