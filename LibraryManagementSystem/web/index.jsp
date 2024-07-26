<%-- 
    Document   : index
    Created on : 17 Jul 2024, 06:57:41
    Author     : Avindu Aloka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body style="background: url(${pageContext.request.contextPath}/assets/Images/LibraryBackground.jpg); background-position: center; background-repeat: no-repeat; background-size: cover;"">
        <div class="d-flex justify-content-between align-items-center" style="height: 100vh;">
            <div></div>
            <div>
                <form class="form-control shadow" style="padding: 40px; border-radius: 10px; border: none;" action="LoginServlet" method="post">
                    <div class="d-flex justify-content-between">
                        <div class="" style="width: 350px;">
                            <img src="${pageContext.request.contextPath}/assets/Images/LibraryHole.jpg" alt="" class="img-fluid">
                        </div>
                        <div style="width: 300px;">
                            <h2 class="p-0 m-0">Login</h2>
                            <p class="p-0 m-0 form-text">Welcome to our webpage on Orizon Library...</p>
                            <div class="mt-2">
                                <label>Username</label>
                                <input class="form-control" autocomplete="on" autofocus="true" type="text" placeholder="Username" name="username" />
                            </div>
                            <div class="mt-1">
                                <label>Password</label>
                                <input class="form-control" autocomplete="on" autofocus="true" type="password" placeholder="Password" name="password" />
                            </div>
                            <p style="color: red;" class="form-text">
                                <%= (request.getAttribute("errorMessage") == null)? "" : request.getAttribute("errorMessage") %>
                            </p>
                            <div class="mt-2 text-center">
                                <button class="btn btn-primary" type="submit">Log In</button>
                            </div>
                            <p class="p-0 m-0 form-text mt-1 text-center">If you haven't an account, You could create account. <a href="Register.jsp" class="" >Sign Up</a> now.</p>
                        </div>
                    </div>
                </form>
            </div>
            <div></div>
        </div>
    </body>
</html>
