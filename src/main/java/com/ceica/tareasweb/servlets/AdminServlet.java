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
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "AdminServlet",value = "/admin")
public class AdminServlet extends HttpServlet {
    TaskController taskController = new TaskController();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Admin usuario = (Admin) request.getSession().getAttribute("user");
        if (usuario != null) {
            if (usuario.getIduser() == 1) {
                taskController.userlogged=usuario;
                List<Admin> usuarios = taskController.getAllUser();
                List<Task> tareas = taskController.getalltask();
                request.setAttribute("usuarios", usuarios);
                request.setAttribute("tareas", tareas);
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else {
                response.sendRedirect("user");
            }
        } else {
            response.sendRedirect("login");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}
