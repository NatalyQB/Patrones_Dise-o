/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.command;
import com.mycompany.model.PrendaComponent;
import com.mycompany.model.ServicioAlquiler;
/**
 *
 * @author nquin
 */




public class AlquilerReceiver {

    public void registrarAlquiler(ServicioAlquiler alquiler, PrendaComponent prenda) {
        prenda.setEstado("alquilada");
        System.out.println("[Receiver] Alquiler registrado: " 
                + prenda.getRef() + " -> estado = " + prenda.getEstado());
        // Aquí podrías guardar el alquiler en BD con DAO si lo tienes
    }

    public void registrarDevolucion(ServicioAlquiler alquiler, PrendaComponent prenda, boolean manchada) {
        if (manchada) {
            prenda.setEstado("lavado_prioritario");
        } else {
            prenda.setEstado("disponible");
        }
        System.out.println("[Receptor] Devolucion registrada: " 
                + prenda.getRef() + " -> estado = " + prenda.getEstado());
        // Aquí podrías actualizar en BD
    }

    public void enviarALavanderia(PrendaComponent prenda, boolean prioritaria) {
        if (prioritaria) {
            prenda.setEstado("lavado_prioritario");
        } else {
            prenda.setEstado("en_lavado");
        }
        System.out.println("[Receiver] Prenda enviada a lavandería: " 
                + prenda.getRef() + " -> estado = " + prenda.getEstado());
        // Aquí se puede usar el Adapter de lavandería
    }
}