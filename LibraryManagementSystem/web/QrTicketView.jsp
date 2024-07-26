<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <title>Ticket Qr View</title>
    </head>
    <body style="background: #F0E5D3;">
        <div class="d-flex px-4" style="margin-top: 50px; ">
            <div class="col-md-8 px-4">
                <div class="form-control p-5 shadow mb-5" style="background: white; border-radius: 10px; border: none;">
                    <div class="d-flex">
                        <div class="col-md-5" style="overflow: hidden; height: 430px; border-radius: 8px;">
                            <img src="${pageContext.request.contextPath}/assets/Books/Image.jpg" alt="alt"/>
                        </div>
                        <div class="col-md-7 px-3">
                            <% 
                        String BookTitle = (String) request.getAttribute("BookTitle");
                        String BookDes = (String) request.getAttribute("BookDes");
                        String BookAuthor = (String) request.getAttribute("BookAuthor");
                            %>
                            <h2 class="p-0 m-0"><%= BookTitle %></h2>
                            <p class="p-0 m-0"><%= BookDes %></p>
                            <p class="p-0 m-0 mt-3 form-text text-dark">Author : <%= BookAuthor %></p>
                            <div class="mt-5">
                                <p class="p-0 m-0  form-text">Books are an important part of our life.</p>
                                <p class="p-0 m-0 form-text">We also have story books that we read for leisure and fun. We can get them from the school library. We can also buy them from bookshops. Books are printed on paper, and can have pictures too that make them interesting. We can read books on the computer as well.</p>
                                <p class="p-0 m-0 form-text">I love reading children’s stories published in colourful books, as they are beautifully presented. I have a nice collection of story books at home.</p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-md-4 justify-content-between">
                <div class="p-0 m-0 my-2 d-flex justify-content-between" style="width: 400px;">
                    <div>
                        <form action="loadHomePage" method="post">
                            <button class="btn btn-success">Go Back</button>
                        </form>
                    </div>
                    <div>
                        <form action="LogOutServlet" method="post">
                            <button class="btn btn-outline-primary" type="submit">LOG OUT</button>
                        </form>
                    </div>
                </div>
                <% String username = (String) session.getAttribute("username"); %>
                <% if (username != null) { %>
                <div class="form-control" style="width: 400px; height: 400px; border-radius: 10px; border: none;">
                    <div>
                        <img src="${pageContext.request.contextPath}/assets/QrImage/JD.png" alt="alt" class="img-fluid"/>
                    </div>
                </div>
                <div class="text-center" style="width: 400px;">
                    <h2 class="p-0 m-0 mt-2 px-2 text-center">Hello, <%= username %>!</h2>
                    <p class="p-0 m-0 text-center form-text px-5">This is your Token to Get Read Book!</p>
                </div>
                <% } else { %>
                <div class="form-control" style="width: 400px; height: 400px; border-radius: 10px; border: none;">
                    <div>
                        <img src="" alt="" class="img-fluid"/>
                    </div>
                </div>
                <div class="text-center" style="width: 400px;">
                    <h4 class="p-0 m-0 mt-2">You are not logged in.</h4>
                    <p class="form-text p-0 m-0 ">Please Log in Again.</p>
                </div>
                <div class="text-center mt-1">
                    <a href="index.jsp" class="btn btn-outline-primary text-center">Log In</a>
                </div>
                <% } %>
            </div>
        </div>

    </body>
</html>
