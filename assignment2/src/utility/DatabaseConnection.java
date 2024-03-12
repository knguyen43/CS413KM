package com.bankfoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/bank_foo_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "YOUR PASSWORD HERE";

    // SQL queries for Customer and Account entities
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO Customer (name, email) VALUES (?, ?)";
    private static final String SELECT_CUSTOMER_BY_ID_SQL = "SELECT * FROM Customer WHERE customerId = ?";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE Customer SET name = ?, email = ? WHERE customerId = ?";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM Customer WHERE customerId = ?";
    
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO Account (accountNumber, customerId, balance) VALUES (?, ?, ?)";
    private static final String SELECT_ACCOUNT_BY_ID_SQL = "SELECT * FROM Account WHERE accountNumber = ?";
    private static final String UPDATE_ACCOUNT_SQL = "UPDATE Account SET balance = ? WHERE accountNumber = ?";
    private static final String DELETE_ACCOUNT_SQL = "DELETE FROM Account WHERE accountNumber = ?";

    public DatabaseConnection() { } // Empty constructor if needed
    
    // Method to establish database connection
    public static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Getters for SQL query strings
    public static String getInsertCustomerSql() {
        return INSERT_CUSTOMER_SQL;
    }

    public static String getSelectCustomerByIdSql() {
        return SELECT_CUSTOMER_BY_ID_SQL;
    }

    public static String getUpdateCustomerSql() {
        return UPDATE_CUSTOMER_SQL;
    }

    public static String getDeleteCustomerSql() {
        return DELETE_CUSTOMER_SQL;
    }
    
    public static String getInsertAccountSql() {
        return INSERT_ACCOUNT_SQL;
    }

    public static String getSelectAccountByIdSql() {
        return SELECT_ACCOUNT_BY_ID_SQL;
    }

    public static String getUpdateAccountSql() {
        return UPDATE_ACCOUNT_SQL;
    }

    public static String getDeleteAccountSql() {
        return DELETE_ACCOUNT_SQL;
    }
    
    // Additional methods for URL, USERNAME, and PASSWORD getters if needed
    public static String getURL() {
        return URL;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
