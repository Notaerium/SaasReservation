package com.reservation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory(
                "jdbc:mysql://localhost:3306/materialenterprise", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.setAutoCommit(false);
        return connection; 
    }

    // Récupération du Dao Item
    public ItemDao getItemDao() {
        return new ItemDaoImpl(this);
    }
    
    // Récupération du Dao Employee
    public EmployeeDao getEmployeeDao() {
        return new EmployeeDaoImpl(this);
    }
    
    // Récupération du Dao Borrowed
    public BorrowingDao getBorrowingDao() {
        return new BorrowingDaoImpl(this);
    }
}