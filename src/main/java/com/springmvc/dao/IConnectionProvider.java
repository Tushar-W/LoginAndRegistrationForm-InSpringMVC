package com.springmvc.dao;

public interface IConnectionProvider {
    String dburl ="jdbc:mysql://localhost:3306/mysql";
    String dbuname = "root";
    String dbpassword = "mysql";
    String dbdriver = "com.mysql.cj.jdbc.Driver";
}