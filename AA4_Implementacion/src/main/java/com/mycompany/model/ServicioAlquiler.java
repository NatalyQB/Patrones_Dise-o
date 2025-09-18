/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author nquin
 */


public class ServicioAlquiler {
    private long id;
    private LocalDateTime fechaSolicitud;
    private LocalDate fechaAlquiler;
    private String empleadoId;
    private String clienteId;
    private List<String> prendaRefs = new ArrayList<>();

    public ServicioAlquiler(long id, LocalDateTime fechaSolicitud, LocalDate fechaAlquiler, String empleadoId, String clienteId){
        this.id=id;this.fechaSolicitud=fechaSolicitud;this.fechaAlquiler=fechaAlquiler;this.empleadoId=empleadoId;this.clienteId=clienteId;
    }
    public long getId(){ return id; }
    public void addPrendaRef(String ref){ prendaRefs.add(ref); }
    public List<String> getPrendaRefs(){ return prendaRefs; }
    @Override public String toString(){
        return "Servicio#"+id+" fechaAlq:"+fechaAlquiler+" cliente:"+clienteId+" empleado:"+empleadoId+" prendas:"+prendaRefs;
    }

public LocalDateTime getFechaSolicitud() {
    return fechaSolicitud;
}

public LocalDate getFechaAlquiler() {
    return fechaAlquiler;
}

public String getEmpleadoId() {
    return empleadoId;
}

public String getClienteId() {
    return clienteId;
}
}
