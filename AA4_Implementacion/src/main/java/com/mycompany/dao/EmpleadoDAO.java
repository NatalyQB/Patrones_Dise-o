/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.db.ConexionDB;
import com.mycompany.model.Empleado;
import java.sql.*;

/**
 *
 * @author nquin
 */
public class EmpleadoDAO {
    public boolean insertar(Empleado e){
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("INSERT INTO empleado(id,nombre,cargo) VALUES (?,?,?)")){
            ps.setString(1, e.getId());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getCargo());
            return ps.executeUpdate()>0;
        }  catch (SQLException ex){
            return false;
        }
    }
    
    public boolean exists (String id){
        try (PreparedStatement ps = ConexionDB.getInstancia().getConnection().prepareStatement("SELECT id FROM empleado WHERE id=?")){
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) { return false; }
       }
}
