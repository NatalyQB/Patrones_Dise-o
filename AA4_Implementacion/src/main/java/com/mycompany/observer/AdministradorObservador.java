/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.observer;
import com.mycompany.model.PrendaComponent;

public class AdministradorObservador implements IObservador {
    @Override
    public void actualizar(ISujeto sujeto, String evento) {
        if (sujeto instanceof PrendaComponent) {
            PrendaComponent p = (PrendaComponent) sujeto;

            if ("lavado_prioritario".equals(p.getEstado())) {
                System.out.println("[Admin] Generar orden de lavanderiaa PRIORITARIA para: " + p.getRef());
            } else {
                System.out.println("[Admin] La prenda " + p.getRef() + " cambio de estado a: " + p.getEstado());
            }
        }
    }
}