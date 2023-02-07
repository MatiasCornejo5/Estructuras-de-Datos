package Lineales.Estaticas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Cornejo Matias FAI-1919
 * @author Petenatti Francisco FAI-2347
 */
public class Cola {

    private int tamanio = 10;
    private Object[] arreglo;
    private int frente;
    private int fin;

    public Cola() {
        this.arreglo = new Object[tamanio];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(Object nuevoElem) {
        //Pone el elemento al final de la cola. 
        //Devuelve verdadero si el elemento se pudo agregar en la estructura y falso en caso contrario
        boolean exito = false;
        if ((this.fin + 1) % this.tamanio != this.frente) {
            this.arreglo[this.fin] = nuevoElem;
            this.fin = (this.fin + 1) % this.tamanio;
            exito = true;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito = true;
        if (esVacia()) {
            //la cola esta vacia, reporta el error
            exito = false;
        } else {
            //existe al menos un elemento, avanza frente
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.tamanio;
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elemento = null;
        if (!esVacia()) {
            elemento = this.arreglo[this.frente];
        }
        return elemento;
    }

    public boolean esVacia() {
        return this.frente == this.fin;
    }

    public void vaciar() {
        //Anotacion: indistinto si usamos el frente o el fin para vaciar
        while (this.frente != this.fin) {
            this.arreglo[this.fin] = null;
            this.fin = (this.fin + 1) % this.tamanio;
        }
    }

    public Cola clone() {
        Cola clon = new Cola();
        clon.arreglo = this.arreglo.clone();
        clon.frente = this.frente;
        clon.fin = this.fin;
        return clon;
    }

    public String toString() {
        String res;
        int aux = this.frente;
        if (this.fin == this.frente) {
            res = "Cola vacia";
        } else {
            res = "[";
            while (aux != fin) {
                res += this.arreglo[aux];
                aux = (aux + 1) % this.tamanio;
                if (aux != fin) {
                    res += ", ";
                }
            }
            res += "]";
        }

        return res;
    }
}
