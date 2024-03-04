package com.ceica.tareasweb.servlets;

import com.ceica.tareasweb.controller.TaskController;
import com.ceica.tareasweb.models.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Admin user = (Admin) request.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (user.getIdrol().getName().equals("admin")) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("user");
            }
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TaskController taskController = new TaskController();
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        try {
            if (taskController.login(user, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", taskController.userlogged);
                if (taskController.isAdmin()) {
                    response.sendRedirect("admin");
                } else {
                    response.sendRedirect("user");
                }
            } else {
                request.setAttribute("mensaje", "Usuario o Contraseña incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }catch (Exception e){
            request.setAttribute("mensaje", "Usuario o Contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
