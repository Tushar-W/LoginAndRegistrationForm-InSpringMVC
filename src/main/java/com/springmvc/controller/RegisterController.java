package com.springmvc.controller;

import com.springmvc.model.UserInformation;
import com.springmvc.dao.RegisterDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class RegisterController {
    private static final String NAME_PATTERN = "^[A-Z][a-z]{3,}$";
    private static final String EMAIL_PATTERN =  "^[0-9a-zA-Z]+([._+-][a-zA-Z]+)?@[0-9a-zA-Z]+[.][a-z]{2,4}([.][a-z]{2})?$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-zA-Z0-9@#!$%^&*_-]{8,})[a-zA-Z0-9]+[@#!$%^&*_-][a-zA-Z0-9]+$";
    private String name;
    private String email;
    private String password;
    private PrintWriter out;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registrationProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        name = request.getParameter("name");
        email = request.getParameter("email");
        password = request.getParameter("password");
        UserInformation user = new UserInformation(name, email, password);
        out = response.getWriter();
        if (isAllFieldsEmpty() && isNameCorrect() && isEmailIdCorrect() && isPasswordMatch()) {
            RegisterDao rDao = new RegisterDao();
            int result = rDao.insertUser(user);
            out.println("<script type=\"text/javascript\">");
            if (result == 0) {
                out.println("alert('registration not submitted');");
                out.println("</script>");
            } else {
                out.println("alert('registration successful...!!!! ');");
                out.println("</script>");
                response.sendRedirect("login.jsp");
            }
        }
        request.getRequestDispatcher("register.jsp").include(request, response);
    }

    private boolean isPasswordMatch () {
        if (password.matches(PASSWORD_PATTERN))
            return true;
        else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('invalid password " +
                    " note: password minimum 8 characters " +
                    " password have at least 1 uppercase " +
                    " password have at least 1 numeric " +
                    " password have exactly 1 special character');");
            out.println("</script>");
            return false;
        }
    }

    private boolean isEmailIdCorrect () {
        if (email.matches(EMAIL_PATTERN))
            return true;
        else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('invalid email-id');");
            out.println("</script>");
            return false;
        }
    }

    private boolean isNameCorrect () {
        if (name.matches(NAME_PATTERN))
            return true;
        else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('invalid name " +
                    " note : first letter of name must have capital');");
            out.println("</script>");
            return false;
        }
    }

    private boolean isAllFieldsEmpty () {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            out.println("<script type=\"text/javascript\">");
            out.println("alert('please fill all fields');");
            out.println("</script>");
            return false;
        } else {
            return true;
        }
    }
}
