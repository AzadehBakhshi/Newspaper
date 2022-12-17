/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;


/**
 *
 * @author Azadeh
 */
public class Worker {

    private int Id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Username;
    private String Password;
    private Date RecruitmentDate;
    private int PriorExperience;
    private int Salary;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Date getRecruitmentDate() {
        return RecruitmentDate;
    }

    public void setRecruitmentDate(Date RecruitmentDate) {
        this.RecruitmentDate = RecruitmentDate;
    }

    public int getPriorExperience() {
        return PriorExperience;
    }

    public void setPriorExperience(int PriorExperience) {
        this.PriorExperience = PriorExperience;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
    }

}
