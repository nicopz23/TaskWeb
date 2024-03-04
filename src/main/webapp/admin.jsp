<%@ page import="com.ceica.tareasweb.models.Admin" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ceica.tareasweb.models.Task" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 27/02/2024
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador</title>
    <link rel="stylesheet" href="assets/css/admin.css">
</head>
<body>
<header class="header">
    <p>${name}</p>
    <a href="?logout">salir</a>
</header>
<div class="column">
    <h2>Tabla de Usuarios</h2>
    <table border="1" id="tablaUsuarios">
        <thead>
        <tr>
            <th>ID Usuario</th>
            <th>Nombre</th>
            <th>Rol</th>
            <th>Nombre de Rol</th>
        </tr>
        </thead>
        <tbody id="tablaUsuariosBody">
        <% List<Admin> usuarios = (List<Admin>) request.getAttribute("usuarios");
            if (usuarios != null) {
                for (Admin usuario : usuarios) { %>
        <tr>
            <td><%= usuario.getIduser() %></td>
            <td><%= usuario.getUsername() %></td>
            <td><%= usuario.getIdrol().getIdrol() %></td>
            <td><%= usuario.getIdrol().getName() %></td>
        </tr>
        <%  }
        } %>
        </tbody>
    </table>

    <input type="text" name="username" id="username" placeholder="Nombre de Usuario">
    <input type="password" name="password" id="password" placeholder="Contraseña">
    <input type="text" name="rol" id="rol" placeholder="Rol">
    <button type="submit">Agregar Usuario</button>

</div>

<div class="column">
    <h2>Tabla de Tareas</h2>
    <table border="1">
        <thead>
        <tr>
            <th>ID Tarea</th>
            <th>Titulo Tarea</th>
            <th>Descripción</th>
            <th>Fecha de Creación</th>
            <th>Fecha de Finalización</th>
            <th>Estado</th>
        </tr>
        </thead>
        <tbody id="tablaTareas">
        <% List<Task> tareas = (List<Task>) request.getAttribute("tareas");
            if (tareas != null) {
                for (Task tarea : tareas) { %>
        <tr>
            <td><%= tarea.getIdtask() %></td>
            <td><%= tarea.getTitle() %></td>
            <td><%= tarea.getDescription() %></td>
            <td><%= tarea.getDatetime() %></td>
            <td><%= tarea.getDeadline() %></td>
            <td><%= tarea.getStatus() %></td>
        </tr>
        <%  }
        } %>
        </tbody>
    </table>
</div>
<input type="hidden" id="userId">
<script src="assets/js/admin.js"></script>
</body>
</html>