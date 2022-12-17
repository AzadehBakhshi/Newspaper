/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Azadeh
 */
public class dbConnection {

    private String dbName = "";
    private Connection connection;

    public boolean dbConnect() {

        this.dbName = "MyNewspaper";
        return Connect();
    }

    public boolean Connect() {

        String Username = "root";
        String Password = "99Ownerss!@";
        String connectionString = "jdbc:mysql://localhost:3306/" + dbName + "?allowPublicKeyRetrieval=true&useSSL=false";

        try {
            connection = DriverManager.getConnection(connectionString, Username, Password);
            System.out.println("dbConnection: database connected successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("dbConnection: " + e.getMessage());
        }

        return false;
    }

    public Connection GetConnection() {
        return connection;
    }

    public ResultSet dbExecuteQuery(String sql) {

        try {
            Statement st = connection.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("dbExecuteQuery: " + e.getMessage());
        }

        return null;
    }

    public int dbExecuteUpdate(String sql) {

        try {
            Statement st = connection.createStatement();
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("dbExecuteUpdate: " + e.getMessage());
        }

        return -1;
    }

    public void dbClose() {
        try {
            connection.close();
            System.out.println("dbClose: database closed successfully.");
        } catch (SQLException e) {
            System.out.println("dbClose: " + e.getMessage());
        }
    }

}
