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

public class RegistrarDevolucionCommand implements ICommand {
    private AlquilerReceiver receiver;
    private ServicioAlquiler alquiler;
    private PrendaComponent prenda;
    private boolean manchada;

    public RegistrarDevolucionCommand(AlquilerReceiver receiver, ServicioAlquiler alquiler, PrendaComponent prenda, boolean manchada) {
        this.receiver = receiver;
        this.alquiler = alquiler;
        this.prenda = prenda;
        this.manchada = manchada;
    }

    @Override
    public void execute() {
        receiver.registrarDevolucion(alquiler, prenda, manchada);
    }
}
