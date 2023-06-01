package servlets;

import db.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Comments;
import models.News;
import models.Users;

import java.io.IOException;

@WebServlet(value = "/add-comment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Users user = (Users) request.getSession().getAttribute("currentUser");

        if(user != null) {
            String comment = request.getParameter("comment");
            int newsId = Integer.parseInt(request.getParameter("news_id"));

            News news = DBConnection.getNewsById(newsId);
            if(news != null) {
                Comments comments = new Comments();
                comments.setNews(news);
                comments.setUser(user);
                comments.setComment(comment);
                DBConnection.addComment(comments);
            }
            response.sendRedirect("/news-details?id="+newsId);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}