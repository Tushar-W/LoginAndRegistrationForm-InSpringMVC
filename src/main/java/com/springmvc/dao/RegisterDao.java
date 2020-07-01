package com.springmvc.dao;

import com.springmvc.model.UserInformation;
import java.sql.*;
import java.util.Calendar;

public class RegisterDao implements IConnectionProvider{
    /**
     * method for loading jdbc driver
     */
    public void loadDriver(){
        try {
            Class.forName(dbdriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * method for getting jdbc connection
     * @return connection object
     */
    public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(dburl, dbuname, dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    /**
     * method for insert register user details in database
     * @param user object of UserInformation class
     * @return status
     */
    public int insertUser(UserInformation user){
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
     * @param username of login user
     * @param password of login user
     * @return UserInformation object
     */
    public UserInformation checkUser(String username, String password) {
        UserInformation user = new UserInformation();
        loadDriver();
        Connection con = getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select  * from mysql.registerdata where NAME=? and PASSWORD=?");
            ps.setString(1, username);
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
