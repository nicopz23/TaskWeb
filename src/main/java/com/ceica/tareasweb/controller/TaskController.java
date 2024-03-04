package com.ceica.tareasweb.controller;

import com.ceica.tareasweb.models.Admin;
import com.ceica.tareasweb.models.Rol;
import com.ceica.tareasweb.models.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private List<Rol> rolList;
    private List<Admin> listnew;
    private List<Admin> adminList;
    private List<Task> taskList;
    public Admin userlogged;

    public TaskController() {
        taskList = new ArrayList<>();
        adminList = new ArrayList<>();
        listnew = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        Admin user = new Admin();
        userlogged = user.login(username, password);
        if (userlogged != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAdmin() {
        return userlogged.getIdrol().getIdrol() == 1;
    }


    //Opciones de admin
    public boolean newUser(String username, String password, int idrol) {
        Admin admin = new Admin();
        return (admin.insertar("(username,password,idrol) values(?,?,?)", username, password, idrol));
    }

    public boolean deleteUser(int iduser) {
        Admin users = new Admin();
        return (users.borrar("iduser = ?", iduser));
    }

    public boolean newPassword(String username, String nuevacontraseña) {
        Admin users = new Admin();
        return (users.actualizar("password = ? where username = ? ", nuevacontraseña, username));
    }

    public boolean changeRol(int idrol, String username) {
        Admin admin = new Admin();
        return (admin.actualizar("idrol = ? where username = ?", idrol, username));
    }

    public boolean newRol(String name) {
        Rol rol = new Rol();
        return (rol.insertar("(name) values(?)", name));
    }

    public List<Admin> getAllUser() {
        Admin admin = new Admin();
        return admin.getall();
    }

    public List<Task> getTaskByuser() {
        Task task = new Task();
        return task.gettaskByUser(userlogged.getIduser());
    }


    //Opciones de usuarios
    public List<Task> getalltask() {
        Task task = new Task();
        return task.getallTasks();
    }

    public boolean newTask(String titulo, String descripcion, LocalDate deadline) {
        Task task = new Task();
        return (task.insertar("(title,description,deadline,iduser) values(?,?,?,?)", titulo, descripcion, deadline, userlogged.getIduser()));
    }

    public boolean deleteTask(int idtask) {
        Task task = new Task();
        return (task.borrar("idtask = ? and iduser = ?", idtask, userlogged.getIduser()));
    }

    public boolean newDescription(int idtask, String nuevadescripcion) {
        Task task = new Task();
        return (task.actualizar("description = ? where idtask = ? ", nuevadescripcion, idtask));
    }

    public boolean newTitle(int idtask, String nuevotitulo) {
        Task task = new Task();
        return (task.actualizar("title = ? where idtask = ? ", nuevotitulo, idtask));
    }

    public boolean newDeadline(int idtask, LocalDate nuevadeadline) {
        Task task = new Task();
        return (task.actualizar("deadline = ? where idtask = ?", nuevadeadline, idtask));
    }

    public boolean completeTask(int idtarea) {
        Task task = new Task();
        return (task.actualizar("status = true where idtask = ? ", idtarea));
    }

    public List<Rol> getAllRol() {
        Rol rol = new Rol();
        return rol.getAll();
    }
}
