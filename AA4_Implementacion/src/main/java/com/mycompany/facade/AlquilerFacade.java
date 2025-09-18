/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facade;

import com.mycompany.dao.*;
import com.mycompany.factory.PrendaFactory;
import com.mycompany.lavanderia.LavanderiaAdapter;
import com.mycompany.model.*;
import com.mycompany.db.ConexionDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nquin
 */

public class AlquilerFacade {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private final PrendaDAO prendaDAO = new PrendaDAO();
    private final ServicioAlquilerDAO servicioDAO = new ServicioAlquilerDAO();
    private final LavanderiaDAO lavDAO = new LavanderiaDAO();

    // registrar Clientes,Empleados,Prendas
    public boolean registrarCliente(Cliente c) {
        return clienteDAO.insertar(c);
    }

    public boolean registrarEmpleado(Empleado e) {
        return empleadoDAO.insertar(e);
    }

    public boolean registrarPrenda(PrendaIndividual p) {
        return prendaDAO.insertar(p);
    }

    // registrar servicio
    public long registrarServicioAlquiler(String clienteId, String empleadoId, List<String> prendaRefs, LocalDate fechaAlquiler) throws Exception {
        Connection cn = ConexionDB.getInstancia().getConnection();
        try {
            cn.setAutoCommit(false);
            if (!clienteDAO.exists(clienteId)) {
                throw new IllegalArgumentException("Cliente no registrado");
            }
            if (!empleadoDAO.exists(empleadoId)) {
                throw new IllegalArgumentException("Empleado no registrado");
            }
            // validar que exista
            for (String ref : prendaRefs) {
                if (!prendaDAO.findByRef(ref).isPresent()) {
                    throw new IllegalArgumentException("Prenda no registrada: " + ref);
                }
                if (!servicioDAO.isPrendaDisponible(ref, Date.valueOf(fechaAlquiler))) {
                    throw new IllegalArgumentException("Prenda no disponible: " + ref);
                }
            }
            long id = servicioDAO.insertServicio(fechaAlquiler, empleadoId, clienteId);
            for (String ref : prendaRefs) {
                servicioDAO.insertServicioPrenda(id, ref);
            }
            cn.commit();
            return id;
        } catch (Exception ex) {
            cn.rollback();
            throw ex;
        } finally {
            cn.setAutoCommit(true);
        }
    }

    public ServicioAlquiler consultarPorId(long id) {
        return servicioDAO.findById(id).orElse(null);
    }

    public List<ServicioAlquiler> consultarPorCliente(String clienteId) {
        return servicioDAO.listByCliente(clienteId);
    }

    public List<ServicioAlquiler> consultarPorFecha(LocalDate fecha) {
        return servicioDAO.listByFecha(Date.valueOf(fecha));
    }

    public List<String> listarReferenciasPrenda() {
        return prendaDAO.listarTodasReferencias();
    }

    // lavanderia
    public boolean registrarPrendaLavanderia(String ref, boolean prioridad) {
        return lavDAO.registrarEnCola(ref, prioridad);
    }

    public List<String> verListaLavanderia() {
        return lavDAO.listarNoEnviadosOrdenados(100);
    }

    public boolean enviarPrendasLavanderia(int cantidad) {
        List<String> refs = lavDAO.listarNoEnviadosOrdenados(cantidad);
        if (refs.isEmpty()) {
            return false;
        }
        boolean ok = LavanderiaAdapter.enviarBatch(refs);
        if (ok) {
            lavDAO.marcarEnviados(refs);
        }
        return ok;
    }
}
