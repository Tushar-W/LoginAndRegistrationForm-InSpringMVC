package com.springmvc.dao;

public interface IConnectionProvider {
    String dbUrl ="jdbc:mysql://localhost:3306/mysql";
    String dbUName = "root";
    String dbPassword = "mysql";
    String dbDriver = "com.mysql.cj.jdbc.Driver";
}