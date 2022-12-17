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
import models.Journalist;

/**
 *
 * @author Azadeh
 */
public class dbJournalist {

    private final dbConnection connection;

    public dbJournalist() {
        this.connection = new dbConnection();
    }

    public Journalist Find(int Id) {

        Journalist journalist = null;

        if (connection.dbConnect()) {

            try {

                String sql = "SELECT * FROM WorkerZ WHERE Id = " + Id + ";";
                ResultSet rs = connection.dbExecuteQuery(sql);

                if (rs.next()) {

                    journalist = new Journalist();

                    journalist.setId(rs.getInt("Id"));
                    journalist.setFirstName(rs.getString("FirstName"));
                    journalist.setLastName(rs.getString("LastName"));
                    journalist.setEmail(rs.getString("Email"));
                    journalist.setUsername(rs.getString("Username"));
                    journalist.setPassword(rs.getString("Password"));
                    journalist.setRecruitmentDate(rs.getTimestamp("RecruitmentDate"));
                    journalist.setPriorExperience(rs.getInt("PriorExperience"));
                    journalist.setSalary(rs.getInt("Salary"));
                }

                connection.dbClose();

            } catch (SQLException e) {
                System.out.println("Find Journalist: " + e.getMessage());
            }
        }

        return journalist;
    }

    public boolean Add(Journalist journalist) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "INSERT INTO WorkerZ(FirstName, LastName, Email, Username, Password, RecruitmentDate, PriorExperience, Salary, Role) Values('"
                    + journalist.getFirstName() + "', '"
                    + journalist.getLastName() + "', '"
                    + journalist.getEmail() + "', '"
                    + journalist.getUsername() + "', '"
                    + journalist.getPassword() + "', '"
                    + df.format(journalist.getRecruitmentDate()) + "', "
                    + journalist.getPriorExperience() + ", "
                    + journalist.getSalary() + ", "
                    + journalist.getRole()
                    + ")";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public boolean Update(Journalist journalist) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "UPDATE WorkerZ SET "
                    + "FirstName = '" + journalist.getFirstName() + "', "
                    + "LastName = '" + journalist.getLastName() + "', "
                    + "Email = '" + journalist.getEmail() + "', "
                    + "Username = '" + journalist.getUsername() + "', "
                    + "Password = '" + journalist.getPassword() + "', "
                    + "RecruitmentDate = '" + df.format(journalist.getRecruitmentDate()) + "', "
                    + "PriorExperience = " + journalist.getPriorExperience() + ", "
                    + "Salary = " + journalist.getSalary() + ", "
                    + "Role = " + journalist.getRole() + " "
                    + "WHERE (Id = " + journalist.getId() + ");";

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
            
            sql = "DELETE FROM ArticleZ WHERE (AuthorId = " + id + ");";
            connection.dbExecuteUpdate(sql);
            
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public ArrayList<Journalist> GetJournalistList() {

        ArrayList<Journalist> journalists = new ArrayList<>();

        if (connection.dbConnect()) {

            try {

                String sql = "SELECT * FROM WorkerZ WHERE Role = 3;";
                ResultSet rs = connection.dbExecuteQuery(sql);

                while (rs.next()) {

                    Journalist item = new Journalist();

                    item.setId(rs.getInt("Id"));
                    item.setFirstName(rs.getString("FirstName"));
                    item.setLastName(rs.getString("LastName"));
                    item.setEmail(rs.getString("Email"));
                    item.setUsername(rs.getString("Username"));
                    item.setPassword(rs.getString("Password"));
                    item.setRecruitmentDate(rs.getTimestamp("RecruitmentDate"));
                    item.setPriorExperience(rs.getInt("PriorExperience"));
                    item.setSalary(rs.getInt("Salary"));

                    journalists.add(item);
                }

                connection.dbClose();

            } catch (SQLException e) {
                System.out.println("GetJournalistList: " + e.getMessage());
            }
        }

        return journalists;
    }
}
