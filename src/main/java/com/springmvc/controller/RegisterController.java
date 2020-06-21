package com.springmvc.controller;

import com.springmvc.model.UserInformation;
import com.springmvc.dao.RegisterDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class RegisterController {
    PrintWriter out=null;
    RegisterDao rDao=null;
    UserInformation user=null;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registrationProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        user = new UserInformation(name, email, password);
        RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
        rDao = new RegisterDao();
        int result = rDao.insertUser(user);
        out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        if (result == 0)
            out.println("alert('registration not submitted');");
        else{
            response.sendRedirect("login.jsp");
            //out.println("alert('registration successful...!!!! ');");
        }
        out.println("</script>");
        rd.include(request,response);
    }
}
