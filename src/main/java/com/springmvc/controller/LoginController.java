package com.springmvc.controller;

import com.springmvc.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public void loginProcess(HttpServletRequest request, HttpServletResponse response) {
        /**
         * get request parameter for username and password
         */
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        /**
         * pass userName and password to login service
         */
        loginService.loginProcess(userName, password);
    }

}
