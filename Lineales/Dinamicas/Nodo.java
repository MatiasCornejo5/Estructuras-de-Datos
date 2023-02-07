/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lineales.Dinamicas;

/**
 *
 * @author Cornejo Matias FAI-1919
 * @author Petenatti Francisco FAI-2347
 */
public class Nodo {

    private Object elemento;
    private Nodo enlace;

    //Constructor 
    public Nodo(Object elemento, Nodo enlace) {
        this.elemento = elemento;
        this.enlace = enlace;
    }

    //Modificadores
    public void setElemento(Object nuevoElemento) {
        this.elemento = nuevoElemento;
    }

    public void setEnlace(Nodo nuevoEnlace) {
        this.enlace = nuevoEnlace;
    }

    //Observadores
    public Object getElemento() {
        return this.elemento;
    }

    public Nodo getEnlace() {
        return this.enlace;
    }
}
