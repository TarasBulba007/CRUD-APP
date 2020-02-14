<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>My First CRUD Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<br>
<h1>Вход в систему</h1>
<form action="Registration" method="post">
    Пользователь: <input type="text" name="user" size="10"><br>
    Пароль: <input type="password" name="password" size="10"><br>
    <p>
    <table>
        <tr>
            <th><small>
                <input type="submit" name="login" value="SignIn">
            </small>
            <th><small>
                <input type="submit" name="registration" value="SignUp">
            </small>
    </table>
</form>
<br>
</body>
</html>