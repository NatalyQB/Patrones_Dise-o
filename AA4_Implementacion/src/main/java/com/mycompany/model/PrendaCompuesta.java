/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nquin
 */


public class PrendaCompuesta extends PrendaComponent {
    private String ref;
    private List<PrendaComponent> partes = new ArrayList<>();
    public PrendaCompuesta(String ref){ this.ref = ref; }
    public void add(PrendaComponent p){ partes.add(p); }
    @Override public String getRef(){ return ref; }
    @Override public String getTipo(){ return "COMPUESTA"; }
    @Override public double getValorAlquiler(){
        return partes.stream().mapToDouble(PrendaComponent::getValorAlquiler).sum();
    }
    @Override public String getInfo(){
        StringBuilder sb = new StringBuilder("Compuesta "+ref);
        partes.forEach(p -> sb.append("\n  - ").append(p.getInfo()));
        return sb.toString();
    }
    public List<PrendaComponent> getPartes(){ return partes; }
}

