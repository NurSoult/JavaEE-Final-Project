package servlets;

import db.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Comments;
import models.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        News news = DBConnection.getNewsById(id);
        req.setAttribute("news",news);
        if (news != null){
            ArrayList<Comments> comments = DBConnection.getComments(news.getId());
            req.setAttribute("comments",comments);
        }
        req.getRequestDispatcher("/newsdetails.jsp").forward(req,resp);
    }
}