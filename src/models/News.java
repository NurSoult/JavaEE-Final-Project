package models;

import java.sql.Timestamp;

public class News {

    private int id;
    private Timestamp post_date;
    private News_categories category;
    private String title;
    private String content;
    private Users user;


    public News() {
    }

    public News(int id, Timestamp post_date, News_categories category, String title, String content, Users user) {
        this.id = id;
        this.post_date = post_date;
        this.category = category;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }

    public News_categories getCategory() {
        return category;
    }

    public void setCategory(News_categories category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
