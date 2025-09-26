/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app;

import com.mycompany.command.*;
import com.mycompany.model.*;
import com.mycompany.observer.*;

public class MainDemo {
    public static void main(String[] args) {
        // Crear una prenda de prueba
        PrendaComponent prenda = new PrendaIndividual("REF001", "Vestido de gala", "Rojo", "Zara", "M", 200.0);

        // Crear un servicio de alquiler simulado
        ServicioAlquiler alquiler = new ServicioAlquiler(
        1L, // id manual o contador
        java.time.LocalDateTime.now(), // fechaSolicitud
        java.time.LocalDate.now().plusDays(2), // fechaAlquiler
        "EMP001",
        "CLI001"
);


        // Registrar observadores
        prenda.agregarObservador(new AdministradorObservador());
        prenda.agregarObservador(new EmpleadoObservador());

        // Crear Receiver y Command
        AlquilerReceiver receiver = new AlquilerReceiver();
        ICommand comandoDevolucion = new RegistrarDevolucionCommand(receiver, alquiler, prenda, true);

        // Usar Invoker
        Invoker invoker = new Invoker();
        invoker.setCommand(comandoDevolucion);

        System.out.println("== Simulacionn: Devolucion de prenda ==");
        invoker.executeCommand();
    }
}
