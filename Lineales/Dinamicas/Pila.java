/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lineales.Dinamicas;

/**
 *
 * @author matir
 */
public class Pila {

    private Nodo tope;

    //Constructor
    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        //crea el nodo nuevo delante de la antigua cabecera
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        //actualiza el tope para que apunte al nodo nuevo
        this.tope = nuevo;
        //siempre es true por que nunca hay error de pila llena
        return true;
    }

    public boolean desapilar() {
        //Desapila el tope de la pila. Si desapila devuelve true, caso contrario devuelve false
        boolean exito;
        if (this.tope == null) {
            exito = false;
        } else {
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    public boolean esVacia() {
        //Devuelve true si la pila esta vacia, false en caso contrario
        boolean resultado = false;
        if (this.tope == null) {
            resultado = true;
        }
        return resultado;
    }

    public void vaciar() {
        //Vacia por completo la pila
        this.tope = null;
    }

    public Object obtenerTope() {
        //Devuelve el elemento en el tope de la pila. Precondicion: La pila no debe estar vacia
        Object elemento = null;
        if (this.tope != null) {//Si tope es distinto de null devuelve el elemento que se encuentra almacenado
            elemento = this.tope.getElemento();
        }
        return elemento;
    }

    public Pila clone() {
        Pila clon = new Pila();
        Nodo aux, aux2;
        aux = this.tope;
        clon.tope = new Nodo(aux.getElemento(), null);
        aux = aux.getEnlace();
        aux2 = clon.tope;
        while (aux != null) {
            aux2.setEnlace(new Nodo(aux.getElemento(), null));
            aux = aux.getEnlace();
            aux2 = aux2.getEnlace();
        }
        return clon;
    }

    public String toString() {
        String cadena = "";
        if (this.tope == null) {
            cadena = "Pila Vacia";
        } else {
            //se ubica para recorrer la pila
            Nodo aux = this.tope;
            cadena = "[";
            while (aux != null) {
                //agrega el elemento y avanza
                cadena += aux.getElemento().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    cadena += ",";
                }
            }
            cadena += "]";
        }
        return cadena;
    }
}
