package com.mycompany.dao;

import com.mycompany.db.ConexionDB;
import com.mycompany.model.PrendaIndividual;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrendaDAO {
    public boolean insertar(PrendaIndividual p) {
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection()
                .prepareStatement("INSERT INTO prenda(ref,tipo,color,marca,talla,valor_alquiler) VALUES (?,?,?,?,?,?)")) {
            ps.setString(1, p.getRef());
            ps.setString(2, p.getTipo());
            ps.setString(3, p.getColor());
            ps.setString(4, p.getMarca());
            ps.setString(5, p.getTalla());
            ps.setDouble(6, p.getValorAlquiler());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public Optional<PrendaIndividual> findByRef(String ref) {
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("SELECT * FROM prenda WHERE ref=?")) {
            ps.setString(1, ref);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PrendaIndividual p = new PrendaIndividual(
                        rs.getString("ref"),
                        rs.getString("tipo"),
                        rs.getString("color"),
                        rs.getString("marca"),
                        rs.getString("talla"),
                        rs.getDouble("valor_alquiler")
                );
                return Optional.of(p);
            }
        } catch (SQLException e) {
        }
        return Optional.empty();
    }

    public List<PrendaIndividual> listarPorTalla(String talla) {
        List<PrendaIndividual> res = new ArrayList<>();
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("SELECT * FROM prenda WHERE talla=? ORDER BY tipo")) {
            ps.setString(1, talla);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new PrendaIndividual(
                        rs.getString("ref"),
                        rs.getString("tipo"),
                        rs.getString("color"),
                        rs.getString("marca"),
                        rs.getString("talla"),
                        rs.getDouble("valor_alquiler")
                ));
            }
        } catch (SQLException e) {
        }
        return res;
    }

   
    public List<String> listarTodasReferencias() {
        List<String> referencias = new ArrayList<>();
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement(
                "SELECT ref FROM prenda ORDER BY tipo, ref")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                referencias.add(rs.getString("ref"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return referencias;
    }
}
