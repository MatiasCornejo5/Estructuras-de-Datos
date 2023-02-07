/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jerarquicas;

/**
 *
 * @author Cornejo Matias FAI-1919
 * @author Petenatti Francisco FAI-2347
 */
public class NodoArbol {
    
    private Object elemento;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    public NodoArbol (Object elem, NodoArbol izq, NodoArbol der){
        this.elemento = elem;
        this.izquierdo = izq;
        this.derecho = der;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }

}
