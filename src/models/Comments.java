package models;


import java.sql.Timestamp;

public class Comments {

    private int id;
    private String comment;
    private Timestamp post_date;
    private News news;
    private Users user;

    public Comments() {
    }

    public Comments(int id, String comment, Timestamp post_date, News news, Users user) {
        this.id = id;
        this.comment = comment;
        this.post_date = post_date;
        this.news = news;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
