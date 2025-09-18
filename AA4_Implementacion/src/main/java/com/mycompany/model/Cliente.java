/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author nquin
 */
public class Cliente {
    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;

    public Cliente(String id, String nombre, String direccion, String telefono, String email) {
        this.id = id; this.nombre = nombre; this.direccion = direccion; this.telefono = telefono; this.email = email;
    }
    public String getId(){return id;}
    public String getNombre(){return nombre;}
    public String getDireccion(){return direccion;}
    public String getTelefono(){return telefono;}
    public String getEmail(){return email;}
    @Override public String toString(){ return id+" - "+nombre; }
}

