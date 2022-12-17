/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Azadeh
 */
public class dbMaker {

    private dbConnection connection;

    public boolean CheckAndCreate() {

        String sql;
        int res1;
        ResultSet res2;
        connection = new dbConnection();

        // Connect to MySQL Server.
        if (!connection.Connect()) {
            return false;
        }

        try {

            // Check if database is exsit.
            sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME='MyNewspaper';";
            res2 = connection.dbExecuteQuery(sql);
            if (res2.next()) {
                connection.dbClose();
                return true;
            }

            // Create database.
            res1 = connection.dbExecuteUpdate("CREATE DATABASE MyNewspaper;");
            if (res1 <= 0) {
                connection.dbClose();
                return false;
            }

            // Close connection and connect to database.
            connection.dbClose();
            if (!connection.dbConnect()) {
                return false;
            }

            // Create Workers Table.
            sql = """
                CREATE TABLE WorkerZ (
                    Id int unsigned auto_increment not null,
                    FirstName varchar(30) NOT NULL DEFAULT 'Unknown',
                    LastName varchar(30) NOT NULL DEFAULT 'Unknown',
                    Email varchar(30) NOT NULL,
                    Username varchar(30) NOT NULL,
                    Password varchar(30) NOT NULL,
                    RecruitmentDate datetime NOT NULL,
                    PriorExperience int(4) NOT NULL,
                    Salary int(9) DEFAULT NULL,
                    Role int unsigned DEFAULT 0, 
                    PRIMARY KEY (Id)                
                );""";
            res1 = connection.dbExecuteUpdate(sql);
            if (res1 < 0) {
                connection.dbClose();
                return false;
            }

            // Create Articles Table.
            sql = """
                CREATE TABLE ArticleZ (
                    Id int unsigned auto_increment not null,
                    Title varchar(128) NOT NULL DEFAULT 'Unknown',
                    Summary text,
                    Keywords text,
                    MainText text,
                    Comment text,
                    AuthorId int unsigned NULL,
                    ApprovalId int unsigned NULL,
                    RegisterTime datetime NOT NULL,
                    LastEditTime datetime NOT NULL,
                    Status int unsigned DEFAULT 0,
                  PRIMARY KEY (Id)                
                );""";
            res1 = connection.dbExecuteUpdate(sql);
            if (res1 < 0) {
                connection.dbClose();
                return false;
            }

            // Create Admin User.
            sql = """
                INSERT INTO WorkerZ(FirstName, LastName, Email, Username, Password, RecruitmentDate, PriorExperience, Salary, Role) 
                Values('Admin', 'Admin', 'Admin@Newspaper.com', 'admin', 'admin', '2020/10/10 10:10:10', 10, 0, 1);
                """;
            res1 = connection.dbExecuteUpdate(sql);
            if (res1 < 0) {
                connection.dbClose();
                return false;
            }

            return true;

        } catch (SQLException e) {
        }

        return false;
    }

}
