<%@ page import="com.ceica.tareasweb.models.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ceica.tareasweb.controller.TaskController" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28/02/2024
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="assets/js/user.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="assets/css/user.css">
</head>
<body>
<div class="header-container">
    <header class="header">
        <p>${name}</p>
        <a href="?logout">salir</a>
    </header>
</div>
<h2>Tabla de Tareas</h2>
<table border="1">
    <thead>
    <tr>
        <th>Num Tarea</th>
        <th>Titulo Tarea</th>
        <th>Descripción</th>
        <th>Fecha de Creación</th>
        <th>Fecha de Finalización</th>
        <th>Estado</th>
        <th>Editar o Borrar</th>
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
        <td><i onclick="editar(<%=tarea.getIdtask()%>)" class="fa-solid fa-pen-to-square"></i>
            <i onclick="borrar(<%=tarea.getIdtask()%>)" class="fa-solid fa-trash"></i>
            </td>
    </tr>
    <%  }
    } %>
    </tbody>
</table>
<h3>New Task</h3>
<form action="" method="post">
    <div id="datosTarea" class="grid-container">
        <div class="grid-item">
            <label for="titulo">Titulo</label>
            <input type="text" name="titulo" id="titulo" placeholder="Título Tarea">
        </div>
        <div class="grid-item">
            <label for="descripcion">Descripcion</label>
            <input type="text" name="descripcion" id="descripcion" placeholder="Descripción">
        </div>
        <div class="grid-item">
            <label for="fechaFinalizacion">FechaFinalización</label>
            <input type="date" name="fechaFinalizacion" id="fechaFinalizacion" placeholder="FechaFinalizacion">
        </div>
        <div>
            <input type="submit" id="botonCrear" name="crear">
        </div>
    </div>
</form>
</body>
</html>

