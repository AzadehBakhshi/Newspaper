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
import java.util.Date;
import models.Article;
import models.Editor;
import models.Journalist;

/**
 *
 * @author Azadeh
 */
public class dbArticle {

    private final dbConnection connection;

    public dbArticle() {
        this.connection = new dbConnection();
    }

    public Article Find(int Id) {

        Article article = null;

        if (connection.dbConnect()) {

            try {

                String sql = "SELECT * FROM ArticleZ WHERE Id = " + Id + ";";
                ResultSet rs = connection.dbExecuteQuery(sql);

                if (rs.next()) {

                    article = new Article();

                    article.setId(rs.getInt("Id"));
                    article.setRegisterTime(rs.getDate("RegisterTime"));
                    article.setLastEditTime(rs.getDate("LastEditTime"));
                    article.setTitle(rs.getString("Title"));
                    article.setSummary(rs.getString("Summary"));
                    article.setKeywords(rs.getString("Keywords"));
                    article.setMainText(rs.getString("MainText"));
                    article.setComment(rs.getString("Comment"));
                    article.setStatus(rs.getInt("Status"));

                    Journalist author = new dbJournalist().Find(rs.getInt("AuthorId"));
                    Editor approval = new dbEditor().Find(rs.getInt("ApprovalId"));

                    article.setAuthor(author);
                    article.setApproval(approval);
                }

                connection.dbClose();

            } catch (SQLException e) {
                System.out.println("Find Journalist: " + e.getMessage());
            }
        }

        return article;
    }

    public boolean Add(Article article) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "INSERT INTO ArticleZ(Title, Summary, Keywords, MainText, Comment, AuthorId, ApprovalId, RegisterTime, LastEditTime, Status) Values('"
                    + article.getTitle() + "', '"
                    + article.getSummary() + "', '"
                    + article.getKeywords() + "', '"
                    + article.getMainText() + "', '"
                    + article.getComment() + "', "
                    + article.getAuthor().getId() + ", "
                    + (article.getApproval() != null ? article.getApproval().getId() : "0") + ", '"
                    + df.format(article.getRegisterTime()) + "', '"
                    + df.format(article.getLastEditTime()) + "', "
                    + article.getStatus()
                    + ")";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public boolean Update(Article article) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "UPDATE ArticleZ SET "
                    + "Title = '" + article.getTitle() + "', "
                    + "Summary = '" + article.getSummary() + "', "
                    + "Keywords = '" + article.getKeywords() + "', "
                    + "MainText = '" + article.getMainText() + "', "
                    + "Comment = '" + article.getComment() + "', "
                    + "AuthorId = " + article.getAuthor().getId() + ", "
                    + "ApprovalId = " + (article.getApproval() != null ? article.getApproval().getId() : "0") + ", "
                    + "RegisterTime = '" + df.format(article.getRegisterTime()) + "', "
                    + "LastEditTime = '" + df.format(article.getLastEditTime()) + "', "
                    + "Status = " + article.getStatus() + " "
                    + "WHERE (Id = " + article.getId() + ");";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public boolean Delete(int id) {

        if (connection.dbConnect()) {

            String sql = "DELETE FROM ArticleZ WHERE (Id = " + id + ");";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public ArrayList<Article> GetArticleList() {

        ArrayList<Article> articles = new ArrayList<>();

        if (connection.dbConnect()) {

            try {

                String sql = "SELECT * FROM ArticleZ;";
                ResultSet rs = connection.dbExecuteQuery(sql);

                while (rs.next()) {

                    Article item = new Article();

                    item.setId(rs.getInt("Id"));
                    item.setRegisterTime(rs.getDate("RegisterTime"));
                    item.setLastEditTime(rs.getDate("LastEditTime"));
                    item.setTitle(rs.getString("Title"));
                    item.setSummary(rs.getString("Summary"));
                    item.setKeywords(rs.getString("Keywords"));
                    item.setMainText(rs.getString("MainText"));
                    item.setComment(rs.getString("Comment"));
                    item.setStatus(rs.getInt("Status"));

                    Journalist author = new dbJournalist().Find(rs.getInt("AuthorId"));
                    Editor approval = new dbEditor().Find(rs.getInt("ApprovalId"));

                    item.setAuthor(author);
                    item.setApproval(approval);

                    articles.add(item);
                }

                connection.dbClose();

            } catch (SQLException e) {
                System.out.println("GetArticleList: " + e.getMessage());
            }
        }

        return articles;
    }

    public ArrayList<Article> GetArticlesOfJournalist(int JID) {

        ArrayList<Article> articles = new ArrayList<>();

        if (connection.dbConnect()) {

            try {

                String sql = "SELECT * FROM ArticleZ WHERE AuthorId = " + JID + ";";
                ResultSet rs = connection.dbExecuteQuery(sql);

                while (rs.next()) {

                    Article item = new Article();

                    item.setId(rs.getInt("Id"));
                    item.setRegisterTime(rs.getDate("RegisterTime"));
                    item.setLastEditTime(rs.getDate("LastEditTime"));
                    item.setTitle(rs.getString("Title"));
                    item.setSummary(rs.getString("Summary"));
                    item.setKeywords(rs.getString("Keywords"));
                    item.setMainText(rs.getString("MainText"));
                    item.setComment(rs.getString("Comment"));
                    item.setStatus(rs.getInt("Status"));

                    Journalist author = new dbJournalist().Find(rs.getInt("AuthorId"));
                    Editor approval = new dbEditor().Find(rs.getInt("ApprovalId"));

                    item.setAuthor(author);
                    item.setApproval(approval);

                    articles.add(item);
                }

                connection.dbClose();

            } catch (SQLException e) {
                System.out.println("GetArticleList: " + e.getMessage());
            }
        }

        return articles;
    }

    public boolean Approve(int RecId, int EditorId, String comment) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "UPDATE ArticleZ SET "
                    + "Comment = '" + comment + "', "
                    + "ApprovalId = " + EditorId + ", "
                    + "LastEditTime = '" + df.format(new Date()) + "', "
                    + "Status = 1 "
                    + "WHERE Id = " + RecId + ";";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

    public boolean Reject(int RecId, int EditorId, String comment) {

        if (connection.dbConnect()) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql = "UPDATE ArticleZ SET "
                    + "Comment = '" + comment + "', "
                    + "ApprovalId = " + EditorId + ", "
                    + "LastEditTime = '" + df.format(new Date()) + "', "
                    + "Status = 2 "
                    + "WHERE Id = " + RecId + ";";

            int res = connection.dbExecuteUpdate(sql);
            connection.dbClose();

            return res > 0;
        }

        return false;
    }

}
