/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.db;

import java.sql.*;

public class ConexionDB {
    private static ConexionDB instancia;
    private Connection conn;

    private ConexionDB() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./data/mycompanyDB;MODE=MySQL", "sa", "");
            initDB();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static synchronized ConexionDB getInstancia() {
        if (instancia == null) instancia = new ConexionDB();
        return instancia;
    }

    public Connection getConnection() {
        return conn;
    }

    private void initDB() throws SQLException {
        Statement st = conn.createStatement();

        // Clientes
        st.execute("CREATE TABLE IF NOT EXISTS cliente (" +
                "id VARCHAR(50) PRIMARY KEY, " +
                "nombre VARCHAR(150), " +
                "direccion VARCHAR(255), " +
                "telefono VARCHAR(50), " +
                "email VARCHAR(150));");

        // Empleados
        st.execute("CREATE TABLE IF NOT EXISTS empleado (" +
                "id VARCHAR(50) PRIMARY KEY, " +
                "nombre VARCHAR(150), " +
                "direccion VARCHAR(255), " +
                "telefono VARCHAR(50), " +
                "cargo VARCHAR(100));");

        // Prendas individuales
        st.execute("CREATE TABLE IF NOT EXISTS prenda (" +
                "ref VARCHAR(50) PRIMARY KEY, " +
                "tipo VARCHAR(50), " +
                "color VARCHAR(50), " +
                "marca VARCHAR(100), " +
                "talla VARCHAR(20), " +
                "valor_alquiler DOUBLE);");

        // Prendas compuestas 
        st.execute("CREATE TABLE IF NOT EXISTS prenda_compuesta_detalle (" +
                "compuesta_ref VARCHAR(50), " +
                "componente_ref VARCHAR(50), " +
                "PRIMARY KEY (compuesta_ref, componente_ref), " +
                "FOREIGN KEY (compuesta_ref) REFERENCES prenda(ref), " +
                "FOREIGN KEY (componente_ref) REFERENCES prenda(ref));");

        // Servicios de alquiler
        st.execute("CREATE TABLE IF NOT EXISTS servicio_alquiler (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "fecha_solicitud TIMESTAMP, " +
                "fecha_alquiler DATE, " +
                "empleado_id VARCHAR(50), " +
                "cliente_id VARCHAR(50));");

        // Relación servicio - prenda
        st.execute("CREATE TABLE IF NOT EXISTS servicio_prenda (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "servicio_id BIGINT, " +
                "prenda_ref VARCHAR(50));");

        // Cola de lavandería
        st.execute("CREATE TABLE IF NOT EXISTS lavanderia_queue (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "prenda_ref VARCHAR(50), " +
                "llegada TIMESTAMP, " +
                "prioridad BOOLEAN, " +
                "enviado BOOLEAN DEFAULT FALSE);");

        st.close();
    }
}
