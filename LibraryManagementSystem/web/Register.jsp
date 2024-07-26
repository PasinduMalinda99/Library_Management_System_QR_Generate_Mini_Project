<%-- 
    Document   : Register
    Created on : 17 Jul 2024, 10:27:13
    Author     : Avindu Aloka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <title>Register Page</title>
    </head>
    <body style="background: url(${pageContext.request.contextPath}/assets/Images/LibraryBackground.jpg); background-position: center; background-repeat: no-repeat; background-size: cover;"">
        <div class="d-flex justify-content-between align-items-center" style="height: 100vh;">
            <div></div>
            <div>
                <form class="form-control shadow" style="padding: 40px; border-radius: 10px; border: none;" action="RegisterServlet" method="post">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="" style="width: 350px;">
                            <img src="${pageContext.request.contextPath}/assets/Images/LibraryHole.jpg" alt="" class="img-fluid">
                        </div>
                        <div style="width: 300px;">
                            <h2 class="p-0 m-0">Sign Up</h2>
                            <p class="p-0 m-0 form-text">Register us with on Orizon Library...</p>
                            <div class="mt-2">
                                <label>Username</label>
                                <input class="form-control" autocomplete="on" autofocus="true" type="text" placeholder="Username" name="username" />
                            </div>
                            <div class="mt-1">
                                <label>Password</label>
                                <input class="form-control" autocomplete="on" autofocus="true" type="password" placeholder="Password" name="password" />
                            </div>
                            <div class="mt-1">
                                <label>Confirm Password</label>
                                <input class="form-control" autocomplete="on" autofocus="true" type="password" placeholder="Confirm Password" name="conpassword" />
                            </div>
                            <p style="color: red;" class="form-text">
                                <%= (request.getAttribute("errorMessage") == null)? "" : request.getAttribute("errorMessage") %>
                            </p>
                            <p class="p-0 m-0 mt-2 form-text">If you have an account, You could <a href="index.jsp">Log In</a></p>
                            <div class="mt-2">
                                <button class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div></div>
        </div>
    </body>
</html>
