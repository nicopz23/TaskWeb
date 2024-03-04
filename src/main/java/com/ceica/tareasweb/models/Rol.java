package com.ceica.tareasweb.models;

import com.ceica.tareasweb.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Rol extends ModeloBase{
    private int idrol;
    private String name;

    public Rol() {
    }

    public Rol(int idrol, String name) {
        super();
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    protected String getNombreTabla() {
        return "rol";
    }

    public List<Rol> getAll() {
        List<Rol> rolList = new ArrayList<>();
        Connection conn = Conexion.conetar();
        String sql = "Select * from rol";
        try {
            Statement stm = conn.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()) {
                Rol rol=new Rol();
                rol.setIdrol(respuesta.getInt("idrol"));
                rol.setName(respuesta.getString("name"));
                rolList.add(rol);
            }
        } catch (SQLException e) {
            return rolList;
        }
        try {
            conn.close();
        } catch (SQLException e) {
        }
        return rolList;
    }
}
