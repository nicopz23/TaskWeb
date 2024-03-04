package com.ceica.tareasweb.servlets;

import com.ceica.tareasweb.controller.TaskController;
import com.ceica.tareasweb.models.Admin;
import com.ceica.tareasweb.models.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "UserServlet",value = "/user")
public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String logoutParam = request.getParameter("logout");
        // Si el parámetro "logout" tiene el valor "salir"
        if ("salir".equals(logoutParam)) {
            // Obtener la sesión actual (creará una nueva si no existe)
            HttpSession session = request.getSession(false);
            if (session != null) {
                // Invalidar la sesión
                session.invalidate();
                // Redireccionar a la página de inicio de sesión, o donde corresponda
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }else {
            Admin usuario = (Admin) request.getSession().getAttribute("user");
            if (usuario != null) {
                if (usuario.getIdrol().getIdrol() == 2) {
                    TaskController taskController = new TaskController();
                    taskController.userlogged=usuario;
                    List<Task> tareas = taskController.getTaskByuser();
                    request.setAttribute("tareas", tareas);
                    request.setAttribute("name", usuario.getUsername());
                    request.getRequestDispatcher("user.jsp").forward(request, response);
                } else {
                    response.sendRedirect("admin");
                }
            } else {
                response.sendRedirect("login");
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Admin usuario = (Admin) request.getSession().getAttribute("user");
        if (usuario == null) {
            response.sendRedirect("login");
        }else {
            TaskController taskController = new TaskController();
            taskController.userlogged = usuario;
            String title = request.getParameter("titulo");
            String description = request.getParameter("descripcion");
            LocalDate deadline = LocalDate.parse(request.getParameter("fechaFinalizacion"));
            taskController.newTask(title,description,deadline);
            response.sendRedirect("user");
        }
    }
}
