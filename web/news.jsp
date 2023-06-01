<%@ page import="java.util.ArrayList" %>
<%@ page import="models.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>TENGRINEWS</title>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container">
    <div class="mt-4">
        <div class="col-12">
            <%
                ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
                if (news != null) {
                    for (News n : news) {
            %>
            <div class="p-5 mb-3" style="background-color: #dee1df;">
                <a href="/news-details?id=<%=n.getId()%>"></a>
                <h3><%=n.getTitle()%>
                </h3>
                <p><%=n.getContent()%>
                </p>
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
<%@include file="footer.jsp" %>
</body>
</html>
