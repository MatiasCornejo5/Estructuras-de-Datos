/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conjuntista;

/**
 *
 * @author matir
 */
public class NodoBB {
    
    private Comparable elem;
    private NodoBB izquierdo;
    private NodoBB derecho;
    
    
    public NodoBB (Comparable elemento, NodoBB izq, NodoBB der){
        this.elem=elemento;
        this.izquierdo=izq;
        this.derecho=der;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public NodoBB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoBB iquierdo) {
        this.izquierdo = iquierdo;
    }

    public NodoBB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoBB derecho) {
        this.derecho = derecho;
    }
    
    
}


