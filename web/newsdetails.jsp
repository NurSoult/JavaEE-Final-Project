<%@ page import="models.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Comments" %>
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
                News news = (News) request.getAttribute("news");
                if (news != null) {
            %>
            <div class="p-5 mb-3" style="background-color: #da1f34">
                <h3><%=news.getTitle()%>
                </h3>
                <p><%=news.getContent()%>
                </p>
                <p>
                    Posted by <strong><%=news.getUser().getFullName()%>
                </strong>
                    at <strong><%=news.getPost_date()%>
                </strong>
                    category <strong><%=news.getCategory().getName()%>
                </strong>
                </p>
                <%
                    if (currentUser != null && currentUser.getId() == news.getUser().getId()) {
                %>
                <p>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editnews">
                        EDIT
                    </button>

                    <!-- Modal -->
                <div class="modal fade" id="editnews" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <form action="/save-news" method="post">
                                <input type="hidden" name="id" value="<%=news.getId()%>">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Edit News</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-12">
                                            <label>
                                                Title :
                                            </label>
                                        </div>
                                    </div>
                                    <div class="row mt-2">
                                        <div class="col-12">
                                            <label>
                                                <input type="text" class="form-control" name="title" required
                                                       placeholder="insert title" value="<%=news.getTitle()%>">
                                            </label>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-12">
                                            <label>
                                                Content :
                                            </label>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-12">
                                            <label>
                                                <textarea class="form-control" name="content"
                                                          placeholder="Insert Content" required
                                                          rows="10"><%=news.getContent()%></textarea>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel
                                    </button>
                                    <button type="submit" class="btn btn-success">Update</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                </p>
                <%
                    if (currentUser != null) {
                %>
                <div>
                    <form action="/add-comment" method="post">
                        <input type="hidden" name="news_id" value="<%=news.getId()%>">
                        <div class="row">
                            <div class="col-12">
                                <textarea class="form-control" name="comment"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button type="submit" class="btn btn-success">Add</button>
                            </div>
                        </div>
                    </form>
                </div>
                <%
                    }
                %>
                <div class="row">
                    <div class="col-12">
                        <%
                            ArrayList<Comments> comments = (ArrayList<Comments>) request.getAttribute("comments");
                            if (comments != null) {
                                for (Comments com : comments) {
                        %>
                        <div class="list-group">
                            <a href="JavaScript:void(0)" class="list-group-item list-group-item-action">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1"><%=com.getUser().getFullName()%>
                                    </h5>
                                    <small class="text-body-secondary"><%=com.getPost_date()%>></small>
                                </div>
                                <p class="mb-1"><%=com.getComment()%>
                                </p>

                            </a>
                        </div>
                        <%
                                }
                            }
                        %>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
