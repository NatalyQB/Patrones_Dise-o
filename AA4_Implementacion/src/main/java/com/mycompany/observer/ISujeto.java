/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.observer;

import com.mycompany.observer.IObservador;

/**
 *
 * @author nquin
 */
public interface ISujeto {
    void agregarObservador(IObservador obs);
    void removerObservador(IObservador obs);
    void notificarObservadores(String evento);
}
