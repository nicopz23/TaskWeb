package com.ceica.tareasweb.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

    public abstract class ModeloBase {
        private static final String CONFIG_FILE = "src/main/resources/config.properties";
        protected static String URL;
        protected static String USUARIO;
        protected static String PASSWORD;

        static {
            cargarConfiguracion();
        }

        private static void cargarConfiguracion() {
            Properties propiedades = new Properties();
            try (FileInputStream entrada = new FileInputStream(CONFIG_FILE)) {
                propiedades.load(entrada);
                URL = propiedades.getProperty("db.url");
                USUARIO = propiedades.getProperty("db.usuario");
                PASSWORD = propiedades.getProperty("db.password");
            } catch (IOException e) {
                URL = "jdbc:mysql://localhost:3306/tareagestion";
                USUARIO = "root";
                PASSWORD = "1234";
                e.printStackTrace();
            }
        }

        // Método abstracto para obtener el nombre de la tabla
        protected abstract String getNombreTabla();

        // Métodos para CRUD

        public boolean insertar(String sql, Object... parametros) {
            sql = "insert into " + getNombreTabla() + " " + sql;
            return ejecutarQuery(sql, parametros);

        }

        public boolean actualizar(String sql, Object... parametros) {
            sql = "update " + getNombreTabla() + " set " + sql;
            return ejecutarQuery(sql, parametros);
        }

        public boolean borrar(String sql, Object... parametros) {
            sql = "delete from " + getNombreTabla() + " where " + sql;
            return ejecutarQuery(sql, parametros);

        }

        // Método genérico para ejecutar consultas SQL
        private boolean ejecutarQuery(String sql, Object... parametros) {
            try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                 PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {

                // Establecer los valores de los parámetros
                for (int i = 0; i < parametros.length; i++) {
                    preparedStatement.setObject(i + 1, parametros[i]);
                }

                // Ejecutar la consulta
                if (preparedStatement.executeUpdate() > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;

            }
        }
    }
