/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.db.ConexionDB;
import com.mycompany.model.Cliente;
import java.sql.*;

/**
 *
 * @author nquin
 */
public class ClienteDAO {
    public boolean insertar(Cliente c){
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("INSERT INTO cliente(id,nombre,direccion,telefono,email) VALUES (?,?,?,?,?)")){
            ps.setString(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getEmail());
            return ps.executeUpdate()>0;
        }  catch (SQLException e){
            return false;
        }
    }
    
    public boolean exists (String id){
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("SELECT id FROM cliente WHERE id=?")){
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) { return false; }
       }
    }