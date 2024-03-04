package com.ceica.tareasweb.models;

import com.ceica.tareasweb.bbdd.Conexion;

import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task extends ModeloBase {
    private int idtask;
    private Admin iduser;
    public String title;
    public String description;
    public LocalDate datetime;
    public LocalDate deadline;
    public boolean status;

    public Task() {
    }

    public Task(LocalDate datetime, String title, String description, LocalDate deadline, boolean status) {
        super();
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    public Admin getIduser() {
        return iduser;
    }

    public void setIduser(Admin iduser) {
        this.iduser = iduser;
    }

    public int getIdtask() {
        return idtask;
    }

    public void setIdtask(int idtask) {
        this.idtask = idtask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Task> getallTasks() {
        List<Task> taskList = new ArrayList<>();
        Connection conn = Conexion.conetar();
        String sql = "SELECT idtask,title,T0.description,datetime,deadline,status," +
                "T1.iduser,username,T2.idrol,T2.name " +
                "from task T0 " +
                "left join user as T1 on T0.iduser=T1.iduser " +
                "left join rol as T2 on T1.idrol=T2.idrol";
        try {
            Statement pst = conn.createStatement();
            ResultSet resultSet = pst.executeQuery(sql);
            taskList = readResulSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    public List<Task> gettaskByUser(int iduser)  {
        List<Task> taskList = new ArrayList<>();
        Connection conn = Conexion.conetar();
        String sql = "SELECT idtask,title,T0.description,datetime,deadline,status, " +
                "T1.iduser,username,T2.idrol,T2.name " +
                "from task T0 " +
                "left join user as T1 on T0.iduser=T1.iduser " +
                "left join rol as T2 on T1.idrol=T2.idrol where T1.iduser=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, iduser);
            ResultSet resultSet = pst.executeQuery();
            taskList = readResulSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    private  List<Task> readResulSet(ResultSet resultSet) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        while (resultSet.next()) {
            Task task = new Task();
            Admin user = new Admin();
            Rol rol = new Rol();
            task.setIdtask(resultSet.getInt("idtask"));
            task.setTitle(resultSet.getString("title"));
            task.setDescription(resultSet.getString("description"));
            task.setDatetime(resultSet.getDate("datetime").toLocalDate());
            task.setDeadline(resultSet.getDate("deadline").toLocalDate());
            task.setStatus(resultSet.getBoolean("status"));
            user.setIduser(resultSet.getInt("iduser"));
            user.setUsername(resultSet.getString("username"));
            rol.setIdrol(resultSet.getInt("idrol"));
            rol.setName(resultSet.getString("name"));
            user.setIdrol(rol);
            task.setIduser(user);
            taskList.add(task);
        }
        return taskList;
    }

    @Override
    public String toString() {
        return "Task{ " +
                "idtask = " + idtask +
                "," + iduser +
                ", title = " + title +
                ", description = " + description +
                ", datetime = " + datetime +
                ", deadline = " + deadline +
                ", status = " + status +"\n" +
                " ";
    }

    @Override
    protected String getNombreTabla() {
        return "task";
    }
}
