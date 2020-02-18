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
            <jsp:include page="/admin/menu.jsp"></jsp:include>
        </div>

  <%--      <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
        </ul>  --%>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="insert" method="post">
                    </c:if>


                    <fieldset class="form-group">
                        <label>User Login</label> <input type="text" value="<c:out value='${user.login}' />" class="form-control" name="login">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Password</label> <input type="text" value="<c:out value='${user.password}' />" class="form-control" name="password">
                    </fieldset>

                        <div class="container text-left">
                            <a href="<%=request.getContextPath()%>/admin" class="btn btn-success">SignIn</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">SignUp</a>
                        </div>
                </form>
        </div>
    </div>
</div>
</body>

</html>