<%@ page import="models.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>


    <head>
        <%@include file="head.jsp"%>
        <title>TENGRI NEWS</title>
    </head>

    <body>

    <%@include file="navbar.jsp"%>

    <div class="container">

        <div class="mt-4">
            <img src="https://tengrinews.kz/icon/logo_long.svg" width="1295" height="400">
            <div class="mt-4">
                <div class="col-12">
                    <form action="/" method="get">

                        <div class="row mt-3">
                            <div class="col-9">
                                <input type="text" class="form-control" name="key" placeholder="Search">
                            </div>
                        </div>

                        <div class="col-3">
                            <button class="btn btn-success">Search</button>
                        </div>

                    </form>

                    <%
                        ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
                        if (news != null) {
                            for (News n: news) {
                    %>
                    <div class="p-5 mb-3" style="background-color: #dee1df;">
                        <a href="/news-details?id=<%=n.getId()%>"></a>
                        <h3><%=n.getTitle()%></h3>
                        <p><%=n.getContent()%></p>
                        <p>
                            Posted by <strong><%=n.getUser().getFullName()%>
                        </strong>
                            at <strong><%=n.getPost_date()%>
                        </strong>
                        </p>
                    </div>

                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>

    </div>

    <%@include file="footer.jsp"%>
    
    </body>


</html>