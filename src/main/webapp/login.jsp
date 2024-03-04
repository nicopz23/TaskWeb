<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 27/02/2024
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="assets/css/login.css">
</head>
<body>
<form action="" method="post">
    <p>Ingresa usuario y contraseña para</p>
    <p>iniciar sesión en TareasApp</p>
    <label for="user">Usuario</label>
    <input id="user" type="text" name="user" placeholder="Usuario" required>
    <label for="password">Password</label>
    <input id="password" type="password" name="password" placeholder="Contraseña" required>
    <input type="submit" name="Login">
    <p id ="mensaje">${mensaje}</p>
</form>
</body>
</html>
