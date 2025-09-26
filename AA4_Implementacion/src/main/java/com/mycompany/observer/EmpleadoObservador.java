/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.observer;
import com.mycompany.model.PrendaComponent;

/**
 
 * @author nquin
 */

public class EmpleadoObservador implements IObservador {
    @Override
    public void actualizar(ISujeto sujeto, String evento) {
        if (sujeto instanceof PrendaComponent) {
            PrendaComponent p = (PrendaComponent) sujeto;
            System.out.println("[Empleado] Notificacion sobre: prenda " 
                + p.getRef() + " -> estado: " + p.getEstado());
                    }
    }
}