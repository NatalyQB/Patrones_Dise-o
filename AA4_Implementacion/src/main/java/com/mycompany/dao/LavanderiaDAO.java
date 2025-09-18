/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.db.ConexionDB;
import com.mycompany.model.PrendaComponent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nquin
 */


public class LavanderiaDAO {
    public boolean registrarEnCola(String ref, boolean prioridad) {
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection()
                .prepareStatement("INSERT INTO lavanderia_queue(prenda_ref,llegada,prioridad,enviado) VALUES (?,?,?,false)")) {
            ps.setString(1, ref);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setBoolean(3, prioridad);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public List<String> listarNoEnviadosOrdenados(int limit) {
        List<String> res = new ArrayList<>();
        String sql = "SELECT prenda_ref FROM lavanderia_queue WHERE enviado = FALSE ORDER BY prioridad DESC, llegada ASC";
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(rs.getString(1));
                if (res.size()>=limit) break;
            }
        } catch(SQLException e){}
        return res;
    }

    public void marcarEnviados(List<String> refs) {
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("UPDATE lavanderia_queue SET enviado = TRUE WHERE prenda_ref = ?")) {
            for (String r : refs) {
                ps.setString(1, r);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch(SQLException e){}
    }
}

