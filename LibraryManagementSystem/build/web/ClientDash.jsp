
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controller.LoadBooks" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.BoolList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <title>DashBoard</title>
    </head>
    <body>
        <div class="p-5">
            <div class="form-control p-4 py-5" style="border-radius: 10px;">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h2 class="p-0 m-0">Hello <%= (String) session.getAttribute("username") %>!</h2>
                        <p class="form-text p-0 m-0">Welcome <%= (String) session.getAttribute("username") %>, for our web page. What's are you buy now on our web page.. Looks books and grow your knowledge.</p>
                    </div>
                    <div class="h-100">
                        <form class="h-100" action="LogOutServlet" method="post">
                            <button class="btn btn-outline-primary h-100" type="submit">Log Out</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="justify-content-center px-4 pb-4">
            <div class="px-4 mb-4">
                <h2 class="p-0 m-0">Give Your Book On Here</h2>
                <p class="p-0 m-0 form-text">If you buy a book and we are create a qr code for your book and store</p>
            </div
            <% List<BoolList> books = (List<BoolList>) request.getAttribute("books"); %>
            <div class="container-fluid bg-trasparent my-4 p-3 w-100 px-4" style="position: relative">
                <div class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-5 g-3 p-2">
                    <% if (books != null && !books.isEmpty()) { %>
                    <% for (BoolList bk : books) { %>
                    <form class="px-1" action="GenerateQrCodeSevlet" method="post">
                        <div class="card shadow" style="width: 15rem; height: 150px; border-radius: 10px; overflow: hidden; border: none;">
                            <div class="card-body">
                                <h5 class="card-title p-0 m-0" style="overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 1; /* number of lines to show */line-clamp: 1;-webkit-box-orient: vertical;"><%= bk.getBookTitle() %></h5>
                                <p class="p-0 m-0 mt-1 form-text" style="line-height: 16px;overflow: hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 2; /* number of lines to show */line-clamp: 2;-webkit-box-orient: vertical;"><%= bk.getBookDescription() %></p>
                                <div class="d-flex justify-content-between align-items-center mt-2">
                                    <div>
                                        <p class="form-text p-0 m-0"><%= bk.getBookAuthor() %></p>
                                    </div>
                                        <input type="hidden" value="<%= bk.getBookId() %>" name="bookId"/>
                                        <input type="hidden" value="<%= bk.getBookTitle() %>" name="bookTitle"/>
                                        <input type="hidden" value="<%= bk.getBookDescription() %>" name="bookdescription"/>
                                        <input type="hidden" value="<%= bk.getBookAuthor() %>" name="bookAuthor"/>
                                        <input type="hidden" value="<%= bk.getBookregDateTime() %>" name="bookReg"/>
                                        <input type="hidden" value="<%= (int) session.getAttribute("userId") %>" name="userId"/>
                                    <div class="text-end">
                                        <button type="submit" class="btn btn-primary mt-2 text-center" style="width: 80px;">Get</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <% } %>
                    <% } else { %>
                    <div class="d-gird text-center">
                        <p class="p-0 m-0">No books available.</p>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </body>
</html>
