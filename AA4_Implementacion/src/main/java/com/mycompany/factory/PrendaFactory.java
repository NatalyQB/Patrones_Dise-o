/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.factory;
import com.mycompany.model.PrendaIndividual;
import com.mycompany.model.PrendaComponent;
import com.mycompany.model.PrendaCompuesta;
import java.util.Map;

/**
 *
 * @author nquin
 */


public class PrendaFactory {

    public static PrendaIndividual crearPrendaIndividual(String tipo, String ref, String color, String marca, String talla, double valor, Map<String,Object> extras){
        // extras se ignoran en este ejemplo simple, se podrían usar para pedreria, tipoTraje, etc.
        return new PrendaIndividual(ref, tipo, color, marca, talla, valor);
    }

    public static PrendaCompuesta crearCompuesta(String ref){
        return new PrendaCompuesta(ref);
    }
}
