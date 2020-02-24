<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>My First CRUD Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: Green">
        <div>
            <a class="navbar-brand"> CRUD App </a>
            <jsp:include page="/menu.jsp"></jsp:include>
        </div>

    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
                <form action="/index" method="POST">

                 <fieldset class="form-group">
                        <label>User Login</label> <input type="text" class="form-control" name="login" value="">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Password</label> <input type="password" class="form-control" name="password" value="" >
                    </fieldset>



                        <div class="container text-left">
                            <button type="submit" class="btn btn-success">SignIn</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">SignUp</a>
                        </div>
                </form>
        </div>
    </div>
</div>
</body>

</html>