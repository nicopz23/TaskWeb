package com.ceica.tareasweb.bbdd;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {
    public static Connection conetar() {
        String url;
        String usuario;
        String password;
        Properties propiedades = new Properties();
        try (FileInputStream entrada = new FileInputStream("src/main/resources/config.properties")){
            propiedades.load(entrada);
            url = propiedades.getProperty("db.url");
            usuario = propiedades.getProperty("db.usuario");
            password = propiedades.getProperty("db.password");
        } catch (Exception e) {
            url = "jdbc:mysql://localhost:3306/tareagestion";
            usuario = "root";
            password = "1234";
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,usuario,password);
            return conexion;
        }catch (Exception e){
            return null;
        }
    }
}
