<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TENGRINEWS</title>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container mt-3 text-center">
    <div class="row">
        <div class="mt-2">
            <div class="col-12 mx-auto">
                <form action="/add-news" method="post">
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
                                       placeholder="insert title">
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
                                <textarea class="form-control" name="content" placeholder="Insert Content" required rows="10"></textarea>
                            </label>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-12">
                            <button class="btn btn-success">Add</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
