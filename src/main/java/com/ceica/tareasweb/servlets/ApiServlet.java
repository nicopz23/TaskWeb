package com.ceica.tareasweb.servlets;

import com.ceica.tareasweb.controller.TaskController;
import com.ceica.tareasweb.models.Admin;
import com.ceica.tareasweb.models.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "ApiServlet", value = "/api")
public class ApiServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idtask = Integer.parseInt(request.getParameter("idtask"));
        Admin user = (Admin) request.getSession().getAttribute("user");

// Si el usuario no ha iniciado sesión, devuelve 303 See Other
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_SEE_OTHER);
            response.setHeader("Location", "/login"); // Indica al cliente que debe ir a /login
        } else {
            TaskController taskController = new TaskController();
            taskController.userlogged = user;
            taskController.deleteTask(idtask);
            List<Task> taskList = taskController.getTaskByuser();
            StringBuilder tbody = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            for (Task task : taskList) {
                tbody.append("<tr><td>").append(task.getIdtask()).append("</td>")
                        .append("<td>").append(task.getTitle()).append("</td>")
                        .append("<td>").append(task.getDescription()).append("</td>")
                        .append("<td>").append(sdf.format(task.getDatetime())).append("</td>")
                        .append("<td>").append(sdf.format(task.getDeadline())).append("</td>")
                        .append("<td>").append("<i class=\"fa-solid fa-pen-to-square\"></i>")
                        .append("<i class=\"fa-solid fa-trash\" onClick=\"borrar(").append(task.getIdtask()).append(")\"></i>")
                        .append("</td></tr>")
                ;

            }
            if (taskList.isEmpty()) {
                tbody.append("<td>No hay tareas</td>");
            }
            PrintWriter out = response.getWriter();
            out.write(tbody.toString());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


    }
}
