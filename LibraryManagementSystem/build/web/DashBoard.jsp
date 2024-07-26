<%-- 
    Document   : DashBoard
    Created on : 17 Jul 2024, 10:47:40
    Author     : Avindu Aloka
--%>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controller.LoadBooks" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <title>DashBoard Page</title>
    </head>
    <body class="p-3">
        <div class="d-flex">
            <div class="col-md-4 p-2">
                <form class="p-4 form-control" style="border-radius: 10px;" action="CreateBookServlet" method="post">
                    <div>
                        <div>
                            <h2 class="p-0 m-0">New Book</h2>
                            <p class="p-0 m-0 form-text mt-2">Paragraph on Books Are Our Best Friends in 100 Words​​ Books are the hub of all information and knowledge.</p>
                        </div>
                    </div>
                    <div class="mt-2">
                        <label>Book Title</label>
                        <input class="form-control" autocomplete="on" autofocus="true" type="text" placeholder="Book Title" name="booktitle"/>
                    </div>
                    <div class="mt-2">
                        <label>Book Description</label>
                        <textarea class="form-control" rows="6" autocomplete="on" autofocus="true" type="text" placeholder="Description Here" name="bookDescription"></textarea>
                    </div>
                    <div class="mt-2">
                        <label>Author Name</label>
                        <input class="form-control" autocomplete="on" autofocus="true" type="text" placeholder="Author Name" name="author" />
                    </div>
                    <p class="p-0 m-0 mt-2 text-end">Above your book upload your store and it publish for the all clients.</p>
                    <div class="text-end">
                        <button class="btn btn-primary mt-2" type="submit">Upload</button>
                    </div>
                    <p style="color: red;" class="form-text">
                        <%= (request.getAttribute("errorMessage") == null)? "" : request.getAttribute("errorMessage") %>
                    </p>
                    <input type="hidden" value="<%= (String) session.getAttribute("username") %>" name="useName"/>
                </form>
            </div>
            <div class="col-md-8 px-4">
                <div class="d-flex justify-content-between align-items-center mt-4">
                    <div class="">
                        <h2 class="p-0 m-0">Your Uploading Books</h2>
                        <p class="p-0 m-0 form-text">You could control your books store here and you could watch your uploads.</p>
                    </div>
                    <div class="text-end">
                        <form action="LogOutServlet" method="post">
                            <button class="btn btn-outline-primary" type="submit">Log Out</button>
                        </form>
                    </div>
                </div>
                <%@ page import="java.util.List" %>
                <%@ page import="Model.BoolList" %>
                <% List<BoolList> books = (List<BoolList>) request.getAttribute("books"); %>
                <% if (books != null && !books.isEmpty()) { %>
                <div class="container-fluid bg-trasparent my-4 p-3" style="position: relative">
                    <div class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-3 g-3 p-2">
                        
                        <% for (BoolList bk : books) { %>
                        <div class="px-1">
                            <div class="card shadow" style="width: 16rem; height: 150px; border-radius: 10px; overflow: hidden; border: none;">
                                <div class="card-body">
                                    <h5 class="card-title p-0 m-0" style="overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 1; /* number of lines to show */line-clamp: 1;-webkit-box-orient: vertical;"><%= bk.getBookTitle() %></h5>
                                    <p class="p-0 m-0 mt-1 form-text" style="line-height: 16px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 2; /* number of lines to show */line-clamp: 2;-webkit-box-orient: vertical;"><%= bk.getBookDescription() %></p>
                                    <div class="d-flex justify-content-between align-items-center mt-2">
                                        <div>
                                            <p class="form-text p-0 m-0"><%= bk.getBookAuthor() %></p>
                                        </div>
                                        <div class="text-end">
                                            <a href="#" class="btn btn-primary mt-2 text-center" style="width: 80px;">Edit</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% } %>
                        <% } else { %>
                        <p class="p-0 m-0">No books available.</p>
                        <p class="form-text p-0 m-0">Please Log in Again.</p>
                        <a href="index.jsp" class="btn btn-outline-primary">Log In</a>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
