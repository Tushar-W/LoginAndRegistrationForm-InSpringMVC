package com.springmvc.services;

import com.springmvc.dao.LoginRegisterDao;
import com.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class LoginService {

    @Autowired
    private LoginRegisterDao loginRegisterDao;

    public void loginProcess(String userName, String password) {
        /**
         * check login user is registered and get user data
         */
        User user = loginRegisterDao.checkUser(userName, password);

        try {
            if (user.getName()==null && user.getPassword()==null) {
                req.getRequestDispatcher("login.jsp").include(req, resp);
            }else {
                HttpSession session= req.getSession();
                session.setAttribute("username",user.getName());
                session.setAttribute("email",user.getEmail());
                session.setAttribute("date",user.getDate());
                req.getRequestDispatcher("welcome.jsp").forward(req, resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
