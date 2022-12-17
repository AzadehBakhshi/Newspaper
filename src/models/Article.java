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
public class Article {

    private int Id;
    private Date RegisterTime;
    private Date LastEditTime;
    private String Title;
    private String Summary;
    private String MainText;
    private String Keywords;

    private Journalist Author;

    private Editor Approval;
    private String Comment;
    private int Status;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Date getRegisterTime() {
        return RegisterTime;
    }

    public void setRegisterTime(Date RegisterTime) {
        this.RegisterTime = RegisterTime;
    }

    public Date getLastEditTime() {
        return LastEditTime;
    }

    public void setLastEditTime(Date LastEditTime) {
        this.LastEditTime = LastEditTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String Summary) {
        this.Summary = Summary;
    }

    public String getMainText() {
        return MainText;
    }

    public void setMainText(String MainText) {
        this.MainText = MainText;
    }

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String Keywords) {
        this.Keywords = Keywords;
    }

    public Journalist getAuthor() {
        return Author;
    }

    public void setAuthor(Journalist Author) {
        this.Author = Author;
    }

    public Editor getApproval() {
        return Approval;
    }

    public void setApproval(Editor Approval) {
        this.Approval = Approval;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return getApproval().getFirstName() + " " + getApproval().getLastName();
    }
}
