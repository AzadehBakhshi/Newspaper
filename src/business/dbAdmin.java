/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import database.dbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.Admin;

/**
 *
 * @author Azadeh
 */
public class dbAdmin {

    private final dbConnection connection;

    public dbAdmin() {
        this.connection = new dbConnection();
    }

    public Admin Find(int Id) {

        Admin admin = null;

        if (connection.dbConnect()) {

            try {

                String sql = "SELECT * FROM WorkerZ WHERE Id = " + Id + ";";
                ResultSet rs = connection.dbExecuteQuery(sql);

                if (rs.next()) {

                    admin = new Admin();

                    admin.setId(rs.getInt("Id"));
                    admin.setFirstName(rs.getString("FirstName"));
                    admin.setLastName(rs.getString("LastName"));
                    admin.setEmail(rs.getString("Email"));
                    admin.setUsername(rs.getString("Username"));
                    admin.setPassword(rs.getString("Password"));
                    admin.setRecruitmentDate(rs.getDate("RecruitmentDate"));
                    admin.setPriorExperience(rs.getInt("PriorExperience"));
                    admin.setSalary(rs.getInt("Salary"));
                }

                connection.dbClose();

            } catch (SQLException e) {
                System.out.println("Find Journalist: " + e.getMessage());
            }
        }

        return admin;
    }

    public boolean Add(Admin admin) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "INSERT INTO WorkerZ(FirstName, LastName, Email, Username, Password, RecruitmentDate, PriorExperience, Salary, Role) Values('"
                    + admin.getFirstName() + "', '"
                    + admin.getLastName() + "', '"
                    + admin.getEmail() + "', '"
                    + admin.getUsername() + "', '"
                    + admin.getPassword() + "', '"
                    + df.format(admin.getRecruitmentDate()) + "', "
                    + admin.getPriorExperience() + ", "
                    + admin.getSalary() + ", "
                    + admin.getRole()
                    + ")";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public boolean Update(Admin admin) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "UPDATE WorkerZ SET "
                    + "FirstName = '" + admin.getFirstName() + "', "
                    + "LastName = '" + admin.getLastName() + "', "
                    + "Email = '" + admin.getEmail() + "', "
                    + "Username = '" + admin.getUsername() + "', "
                    + "Password = '" + admin.getPassword() + "', "
                    + "RecruitmentDate = '" + df.format(admin.getRecruitmentDate()) + "', "
                    + "PriorExperience = " + admin.getPriorExperience() + ", "
                    + "Salary = " + admin.getSalary() + ", "
                    + "Role = " + admin.getRole() + " "
                    + "WHERE (Id = " + admin.getId() + ");";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public boolean Delete(int id) {

        if (connection.dbConnect()) {

            String sql = "DELETE FROM WorkerZ WHERE (Id = " + id + ");";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public ArrayList<Admin> GetAdminList() {

        ArrayList<Admin> admins = new ArrayList<>();

        if (connection.dbConnect()) {

            try {

                String sql = "SELECT * FROM WorkerZ WHERE Role = 1;";
                ResultSet rs = connection.dbExecuteQuery(sql);

                while (rs.next()) {

                    Admin item = new Admin();

                    item.setId(rs.getInt("Id"));
                    item.setFirstName(rs.getString("FirstName"));
                    item.setLastName(rs.getString("LastName"));
                    item.setEmail(rs.getString("Email"));
                    item.setUsername(rs.getString("Username"));
                    item.setPassword(rs.getString("Password"));
                    item.setRecruitmentDate(rs.getDate("RecruitmentDate"));
                    item.setPriorExperience(rs.getInt("PriorExperience"));
                    item.setSalary(rs.getInt("Salary"));

                    admins.add(item);
                }

                connection.dbClose();

            } catch (SQLException e) {
                System.out.println("GetAdminList: " + e.getMessage());
            }
        }

        return admins;
    }
}
