package com.ceica.tareasweb.models;



import com.ceica.tareasweb.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Admin extends ModeloBase {
    private int iduser;
    private String username;
    private String password;
    private Rol rol;

    public Admin() {
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getIdrol() {
        return rol;
    }

    public void setIdrol(Rol idrol) {
        this.rol = idrol;
    }


    @Override
    protected String getNombreTabla() {
        return "user";
    }

    public Admin login(String usuario, String password) {
        Admin admin = new Admin();
        Rol rol = new Rol();
        Connection conn = Conexion.conetar();
            String sql = "Select u.iduser,u.username,r.idrol,r.name from user as u " +
                    "join rol as r on u.idrol=r.idrol " +
                    "where username = ? and password = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, password);
            ResultSet respuesta = pst.executeQuery();
            if (respuesta.next()) {
                admin.iduser = respuesta.getInt("iduser");
                admin.username = respuesta.getString("username");
                rol.setIdrol(respuesta.getInt("idrol"));
                rol.setName(respuesta.getString("name"));
                admin.rol = rol;
                return admin;
            } else {
                return admin;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn!=null)
                    conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public List<Admin> getall() {
        List<Admin> adminList = new ArrayList<>();
        Connection conn = Conexion.conetar();
        String sql = "Select u.iduser,u.username,r.idrol,r.name " +
                "from user as u " +
                "join rol as r on r.idrol=u.idrol";
        try {
            Statement stm = conn.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()) {
                Admin admin = new Admin();
                Rol rol = new Rol();
                admin.setIduser(respuesta.getInt("u.iduser"));
                admin.setUsername(respuesta.getString("u.username"));
                rol.setIdrol(respuesta.getInt("r.idrol"));
                rol.setName(respuesta.getString("r.name"));
                admin.setIdrol(rol);
                adminList.add(admin);
            }
        } catch (SQLException e) {
            return adminList;
        }
        try {
            conn.close();
        } catch (SQLException e) {
        }
        return adminList;
    }

    @Override
    public String toString() {
        return " iduser = " + iduser +
                ", username = " + username +
                ", password = " + password +
                ", rol" + rol;
    }


}
