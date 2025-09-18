/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author nquin
 */
public class PrendaIndividual extends PrendaComponent {
    private String ref, tipo, color, marca, talla;
    private double valorAlquiler;

    public PrendaIndividual(String ref, String tipo, String color, String marca, String talla, double valorAlquiler){
        this.ref=ref;this.tipo=tipo;this.color=color;this.marca=marca;this.talla=talla;this.valorAlquiler=valorAlquiler;
    }
    @Override public String getRef(){ return ref;}
    @Override public String getTipo(){ return tipo;}
    public String getColor(){ return color;}
    public String getMarca(){ return marca;}
    public String getTalla(){ return talla;}
    @Override public double getValorAlquiler(){ return valorAlquiler;}
    @Override public String getInfo(){ return tipo+"["+ref+"] talla:"+talla+" valor:"+valorAlquiler; }
}
