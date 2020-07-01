package com.springmvc.controller;

import com.springmvc.model.UserInformation;
import com.springmvc.dao.RegisterDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class LoginController {
    PrintWriter out;
    RegisterDao dao = new RegisterDao();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        /**
         * get request parameter for username and password
         */
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        /**
         * get registered user
         */
        UserInformation user = dao.checkUser(username, password);
        if (user.getName()==null && user.getPassword()==null) {
            out = resp.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('username or password is wrong');");
            out.println("</script>");
            req.getRequestDispatcher("login.jsp").include(req, resp);
        }else {
            HttpSession session= req.getSession();
            session.setAttribute("username",user.getName());
            session.setAttribute("email",user.getEmail());
            session.setAttribute("date",user.getDate());
            out = resp.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('login successful');");
            out.println("</script>");
           req.getRequestDispatcher("welcome.jsp").forward(req, resp);
        }
    }
}
