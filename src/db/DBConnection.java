package db;

import models.Comments;
import models.News;
import models.News_categories;
import models.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/final_task", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Users getUser(String email) {
        Users user = new Users();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users email=?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
               user.setId(resultSet.getInt("id"));
               user.setRole_id(resultSet.getInt("role_id"));
               user.setFullName(resultSet.getString("full_name"));
               user.setEmail(resultSet.getString("email"));
               user.setPassword(resultSet.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Users createUser(Users user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, password, role_id, full_name)" +
                                                                          "VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            statement.setString(4, user.getFullName());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void addNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO news (post_date, category_id, title, content, user_id)" +
                    "VALUES (NOW(), ?, ?, ?, ?)");
            statement.setInt(1, news.getCategory().getId());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());
            statement.setInt(4, news.getUser().getId());

            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<News> getNews() {
        ArrayList<News> newslist = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.user_id, n.category_id" +
                    "FROM news AS n" +
                    "INNER JOIN users u ON u.id = n.user_id" +
                    "INNER JOIN news_categories ncat ON ncat.id = n.category_id" +
                    "ORDER BY n.post_date DESC");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = new News();

                news.setId(resultSet.getInt("id"));
                news.setContent(resultSet.getString("content"));
                news.setTitle(resultSet.getString("title"));
                news.setPost_date(resultSet.getTimestamp("post_date"));
                news.setCategory(new News_categories(resultSet.getInt("category_id"), resultSet.getString("name")));
                news.setUser(new Users(resultSet.getInt("user_id"), resultSet.getString("full_name")));

                newslist.add(news);

                statement.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newslist;
    }

    public static News getNewsById(int id) {
        News news = null;

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.user_id, n.category_id" +
                    "FROM news AS n" +
                    "INNER JOIN users u ON u.id = n.user_id" +
                    "INNER JOIN news_categories ncat ON ncat.id = n.category_id" +
                    "WHERE n.id = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                news = new News();
                news.setId(resultSet.getInt("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setPost_date(resultSet.getTimestamp("post_date"));
                news.setUser(new Users(resultSet.getInt("id"), resultSet.getString("full_name")));
                news.setCategory(new News_categories(resultSet.getInt("category_id"), resultSet.getString("name")));

                statement.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static void updateNews(News news) {

        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE news " +
                    "SET title = ?, content = ?"+
                    "WHERE id = ?");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setInt(3, news.getCategory().getId());
            statement.executeUpdate();
            statement.close();


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addComment(Comments comments) {

        try {

            PreparedStatement statement = connection.prepareStatement(""+
                    "INSERT INTO comments (comment, post_date, user_id, news_id)"+
                    "VALUES (?, NOW(), ?, ?)");

            statement.setString(1, comments.getComment());
            statement.setInt(2, comments.getUser().getId());
            statement.setInt(3, comments.getNews().getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Comments> getComments(int newsId){

        ArrayList<Comments> commentslist = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(""+
                    "SELECT com.id, com.comment, com.post_date, com.news_id, com.user_id, u.full_name"+
                    "FROM comments com"+
                    "INNER JOIN users u on u.id = com.user_id"+
                    "WHERE com.news_id = ?"+
                    "ORDER BY com.post_date DESC");

            statement.setInt(1, newsId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comments comments = new Comments();
                comments.setId(resultSet.getInt("id"));
                comments.setComment(resultSet.getString("comment"));
                comments.setPost_date(resultSet.getTimestamp("post_date"));

                Users user = new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));

                comments.setUser(user);

                News news = new News();
                news.setId(resultSet.getInt("news_id"));

                comments.setNews(news);

                commentslist.add(comments);
            }
            statement.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return commentslist;
    }


}
