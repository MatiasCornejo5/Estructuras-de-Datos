package Lineales.Estaticas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author matir
 */
public class Pila {

    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;//sujeta a cambios esta variable

    //constructor
    public Pila() {
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    public boolean apilar(int nuevoE) {
        //Pone el nuevo elemento en el tope de la pila. Si apila devuelve true, caso contrario devuelve false
        boolean exito;
        if (this.tope + 1 >= Pila.TAMANIO) {
            //error: pila llena      
            exito = false;
        } else {
            //pone el elemento en el tope de la pila e incrementa el tope
            this.tope++;
            this.arreglo[tope] = nuevoE;

            exito = true;
        }

        return exito;
    }

    public boolean desapilar() {
        //Desapila el tope de la pila. Si desapila devuelve true, caso contrario devuelve false
        boolean exito;
        if (this.tope == -1) {
            //esta vacia la pila
            exito = false;
        } else {
            this.arreglo[tope] = null;
            this.tope--;
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        //Devuelve el elemento en el tope de la pila. Precondicion: La pila no debe estar vacia
        Object elemento = null;
        if (this.tope != -1) {
            elemento = arreglo[this.tope];
        }
        return elemento;
    }

    public boolean esVacia() {
        //Devuelve true si la pila esta vacia, false en caso contrario
        return this.tope == -1;
    }

    public void vaciar() {
        //Vacia por completo la pila
        for (int i = 0; i < this.tope + 1; i++) {
            this.arreglo[i] = null;
        }
        this.tope = -1;
    }

    public Pila clone() {
        Pila clon = new Pila();
        clon.tope = this.tope;
        clon.arreglo = this.arreglo.clone();

        return clon;
    }

    public String toString() {
        String cadena = "";
        for (int i = this.tope; i > -1; i--) {
            cadena += "" + this.arreglo[i].toString() + "";
        }
        return "PILA: " + cadena + "\n TOPE: " + this.tope + "\n TAMAÑO: " + Pila.TAMANIO;
    }
}
