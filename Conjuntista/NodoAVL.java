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
public class NodoAVL {

    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable elem, NodoAVL izq, NodoAVL der) {
        this.elem = elem;
        izquierdo = izq;
        derecho = der;
    }

    public Comparable getElem() {
        return this.elem;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        int alturaIzq = -1, alturaDer = -1;
        if (this.izquierdo != null) {
            alturaIzq = this.izquierdo.getAltura();
        }
        if (this.derecho!= null) {
            alturaDer = this.derecho.getAltura();
        }
        if (alturaIzq > alturaDer) {
            this.altura = alturaIzq + 1;
        } else {
            this.altura = alturaDer + 1;
        }
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public void setIzquierdo(NodoAVL nodo) {
        izquierdo = nodo;
    }

    public void setDerecho(NodoAVL nodo) {
        derecho = nodo;
    }
}
