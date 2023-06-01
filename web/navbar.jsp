<%@ page import="models.Users" %>

<%
    Users currentUser = (Users) session.getAttribute("currentUser");
%>

<div class="container">

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-3">

        <div class="container-fluid">
            <a class="navbar-brand" href="/">TENGRI NEWS</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class=" collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav ms-auto ">
                    <li class="nav-item">
                        <a class="nav-link mx-2" href="/news">All News</a>
                    </li>
                    <%
                        if (currentUser != null) {
                    %>
                        <%
                                if(currentUser.getRole_id() == 1) {
                        %>
                            <li class="nav-item">
                                <a class="nav-link mx-2" href="/add-news-page">Add News</a>
                            </li>
                        <%
                            }
                        %>

                    <li class="nav-item">
                        <a class="nav-link mx-2" href="/profile">Profile</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link mx-2 dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <%=currentUser.getFullName()%>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="#">Blog</a></li>
                            <li><a class="dropdown-item" href="#">About Us</a></li>
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        </ul>
                    </li>

                    <%
                    }  else {
                    %>
                        <li class="nav-item">
                            <a class="nav-link mx-2" href="/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link mx-2" href="/register">Register</a>
                        </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </nav>
</div>

