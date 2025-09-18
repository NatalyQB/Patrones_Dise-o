/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author nquin
 */
public class Empleado {
    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String cargo;

    public Empleado(String id, String nombre, String direccion, String telefono, String cargo) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cargo = cargo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCargo() { return cargo; }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + cargo + ")";
    }
}