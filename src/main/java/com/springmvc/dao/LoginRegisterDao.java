package com.springmvc.dao;

import com.springmvc.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Calendar;

@Repository
public class LoginRegisterDao implements IConnectionProvider{

    /**
     * loading jdbc driver
     */
    public void loadDriver(){
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * getting jdbc connection
     * @return connection object
     */
    public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * insert register user details in database
     * @param user object of User class
     * @return status
     */
    public int insertUser(User user){
        int status = 0;
        loadDriver();
        Connection con = getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("insert into mysql.registerdata(NAME,EMAIL_ID,PASSWORD,RDATE) values(?,?,?,?) ");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail().toLowerCase());
            ps.setString(3, user.getPassword());
            ps.setString(4, String.valueOf(Calendar.getInstance().getTime()));
            status = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * method for check login user details in database or not and store in user object
     * @param userName of login user
     * @param password of login user
     * @return User object
     */
    public User checkUser(String userName, String password) {
        User user = new User();
        loadDriver();
        Connection con = getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select  * from mysql.registerdata where NAME=? and PASSWORD=?");
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setDate(rs.getString(5));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
