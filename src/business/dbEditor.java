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
import models.Editor;

/**
 *
 * @author Azadeh
 */
public class dbEditor {

    private final dbConnection connection;

    public dbEditor() {
        this.connection = new dbConnection();
    }

    public Editor Find(int Id) {

        Editor editor = null;

        if (connection.dbConnect()) {

            try {

                String sql = "SELECT * FROM WorkerZ WHERE Id = " + Id + ";";
                ResultSet rs = connection.dbExecuteQuery(sql);

                if (rs.next()) {

                    editor = new Editor();

                    editor.setId(rs.getInt("Id"));
                    editor.setFirstName(rs.getString("FirstName"));
                    editor.setLastName(rs.getString("LastName"));
                    editor.setEmail(rs.getString("Email"));
                    editor.setUsername(rs.getString("Username"));
                    editor.setPassword(rs.getString("Password"));
                    editor.setRecruitmentDate(rs.getDate("RecruitmentDate"));
                    editor.setPriorExperience(rs.getInt("PriorExperience"));
                    editor.setSalary(rs.getInt("Salary"));
                }

                connection.dbClose();

            } catch (SQLException e) {
                System.out.println("Find Journalist: " + e.getMessage());
            }
        }

        return editor;
    }

    public boolean Add(Editor editor) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "INSERT INTO WorkerZ(FirstName, LastName, Email, Username, Password, RecruitmentDate, PriorExperience, Salary, Role) Values('"
                    + editor.getFirstName() + "', '"
                    + editor.getLastName() + "', '"
                    + editor.getEmail() + "', '"
                    + editor.getUsername() + "', '"
                    + editor.getPassword() + "', '"
                    + df.format(editor.getRecruitmentDate()) + "', "
                    + editor.getPriorExperience() + ", "
                    + editor.getSalary() + ", "
                    + editor.getRole()
                    + ")";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public boolean Update(Editor editor) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "UPDATE WorkerZ SET "
                    + "FirstName = '" + editor.getFirstName() + "', "
                    + "LastName = '" + editor.getLastName() + "', "
                    + "Email = '" + editor.getEmail() + "', "
                    + "Username = '" + editor.getUsername() + "', "
                    + "Password = '" + editor.getPassword() + "', "
                    + "RecruitmentDate = '" + df.format(editor.getRecruitmentDate()) + "', "
                    + "PriorExperience = " + editor.getPriorExperience() + ", "
                    + "Salary = " + editor.getSalary() + ", "
                    + "Role = " + editor.getRole() + " "
                    + "WHERE (Id = " + editor.getId() + ");";

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

    public ArrayList<Editor> GetEditorList() {

        ArrayList<Editor> editors = new ArrayList<>();

        if (connection.dbConnect()) {

            try {

                String sql = "SELECT * FROM WorkerZ WHERE Role = 2;";
                ResultSet rs = connection.dbExecuteQuery(sql);

                while (rs.next()) {

                    Editor item = new Editor();

                    item.setId(rs.getInt("Id"));
                    item.setFirstName(rs.getString("FirstName"));
                    item.setLastName(rs.getString("LastName"));
                    item.setEmail(rs.getString("Email"));
                    item.setUsername(rs.getString("Username"));
                    item.setPassword(rs.getString("Password"));
                    item.setRecruitmentDate(rs.getDate("RecruitmentDate"));
                    item.setPriorExperience(rs.getInt("PriorExperience"));
                    item.setSalary(rs.getInt("Salary"));

                    editors.add(item);
                }

                connection.dbClose();

            } catch (SQLException e) {
                System.out.println("GetEditorList: " + e.getMessage());
            }
        }

        return editors;
    }
}
