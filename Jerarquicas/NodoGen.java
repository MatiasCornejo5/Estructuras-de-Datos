/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jerarquicas;

/**
 *
 * @author matir
 */
public class NodoGen {

    private Object elem;
    private NodoGen hijoIzq;
    private NodoGen hermanoDer;

    public NodoGen(Object elem, NodoGen hijoIzqu, NodoGen hermanoD) {
        this.elem = elem;
        this.hijoIzq = hijoIzqu;
        this.hermanoDer = hermanoD;
    }

    public NodoGen(Object elem) {
        this.elem = elem;
        this.hijoIzq = null;
        this.hermanoDer = null;
    }

    public Object getElem() {
        return elem;
    }

    public NodoGen getHIzquierdo() {
        return this.hijoIzq;
    }

    public NodoGen getHermanoD() {
        return this.hermanoDer;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setHIzquierdo(NodoGen nuevoHIzquierdo) {
        this.hijoIzq = nuevoHIzquierdo;
    }

    public void setHermanoD(NodoGen nuevoDerecho) {
        this.hermanoDer = nuevoDerecho;
    }
}
