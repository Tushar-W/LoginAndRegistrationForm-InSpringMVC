package com.springmvc.services;

import com.springmvc.dao.LoginRegisterDao;
import com.springmvc.model.User;
import org.springframework.stereotype.Service;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class RegisterService {
    private static final String NAME_PATTERN = "^[A-Z][a-z]{3,}$";
    private static final String EMAIL_PATTERN =  "^[0-9a-zA-Z]+([._+-][a-zA-Z]+)?@[0-9a-zA-Z]+[.][a-z]{2,4}([.][a-z]{2})?$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-zA-Z0-9@#!$%^&*_-]{8,})[a-zA-Z0-9]+[@#!$%^&*_-][a-zA-Z0-9]+$";
    private String name;
    private String email;
    private String password;
    private PrintWriter out;

    public void register(HttpServletRequest request, HttpServletResponse response) {
        name = request.getParameter("name");
        email = request.getParameter("email");
        password = request.getParameter("password");
        User user = new User(name, email, password);
        try {
            out = response.getWriter();
            if (isAllFieldsEmpty() && isNameCorrect() && isEmailIdCorrect() && isPasswordMatch()) {
                LoginRegisterDao rDao = new LoginRegisterDao();
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
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
