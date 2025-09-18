/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lavanderia;
import java.util.List;
/**
 *
 * @author nquin
 */

public class LavanderiaAdapter {
    // Simula una integración externa (API)
    public static boolean enviarBatch(List<String> prendaRefs) {
        System.out.println("== Simulación envío a lavandería ==");
        prendaRefs.forEach(r -> System.out.println(" Enviando: " + r));
        System.out.println("== Envío OK ==");
        return true;
    }
}

