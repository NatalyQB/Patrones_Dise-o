/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.db.ConexionDB;
import com.mycompany.model.ServicioAlquiler;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author nquin
 */



public class ServicioAlquilerDAO {
    public long insertServicio(LocalDate fechaAlquiler, String empleadoId, String clienteId) throws SQLException {
        String sql = "INSERT INTO servicio_alquiler(fecha_solicitud, fecha_alquiler, empleado_id, cliente_id) VALUES (?,?,?,?)";
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, Timestamp.valueOf(java.time.LocalDateTime.now()));
            ps.setDate(2, Date.valueOf(fechaAlquiler));
            ps.setString(3, empleadoId);
            ps.setString(4, clienteId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getLong(1);
            else throw new SQLException("No se generó id de servicio");
        }
    }

    public void insertServicioPrenda(long servicioId, String prendaRef) throws SQLException {
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("INSERT INTO servicio_prenda(servicio_id, prenda_ref) VALUES (?,?)")) {
            ps.setLong(1, servicioId); ps.setString(2, prendaRef); ps.executeUpdate();
        }
    }

    public boolean isPrendaDisponible(String ref, Date fecha) throws SQLException {
        String sql = "SELECT s.id FROM servicio_alquiler s JOIN servicio_prenda sp ON s.id = sp.servicio_id WHERE sp.prenda_ref = ? AND s.fecha_alquiler = ?";
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement(sql)) {
            ps.setString(1, ref);
            ps.setDate(2, fecha);
            ResultSet rs = ps.executeQuery();
            return !rs.next();
        }
    }

    public Optional<ServicioAlquiler> findById(long id) {
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("SELECT * FROM servicio_alquiler WHERE id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ServicioAlquiler s = new ServicioAlquiler(rs.getLong("id"), rs.getTimestamp("fecha_solicitud").toLocalDateTime(),
                        rs.getDate("fecha_alquiler").toLocalDate(), rs.getString("empleado_id"), rs.getString("cliente_id"));
                // cargar prenda referencia
                PreparedStatement ps2 = ConexionDB.getInstancia().getConnection().prepareStatement("SELECT prenda_ref FROM servicio_prenda WHERE servicio_id=?");
                ps2.setLong(1, id);
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()) s.addPrendaRef(rs2.getString("prenda_ref"));
                return Optional.of(s);
            }
        } catch (SQLException e) {}
        return Optional.empty();
    }

    public List<ServicioAlquiler> listByCliente(String clienteId) {
        List<ServicioAlquiler> res = new ArrayList<>();
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("SELECT * FROM servicio_alquiler WHERE cliente_id = ? ORDER BY fecha_alquiler")) {
            ps.setString(1, clienteId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ServicioAlquiler s = new ServicioAlquiler(rs.getLong("id"), rs.getTimestamp("fecha_solicitud").toLocalDateTime(),
                        rs.getDate("fecha_alquiler").toLocalDate(), rs.getString("empleado_id"), rs.getString("cliente_id"));
                res.add(s);
            }
        } catch(SQLException e){}
        return res;
    }

    public List<ServicioAlquiler> listByFecha(Date fecha){
        List<ServicioAlquiler> res = new ArrayList<>();
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("SELECT * FROM servicio_alquiler WHERE fecha_alquiler = ?")) {
            ps.setDate(1, fecha);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ServicioAlquiler s = new ServicioAlquiler(rs.getLong("id"), rs.getTimestamp("fecha_solicitud").toLocalDateTime(),
                        rs.getDate("fecha_alquiler").toLocalDate(), rs.getString("empleado_id"), rs.getString("cliente_id"));
                res.add(s);
            }
        } catch(SQLException e){}
        return res;
    }
}

