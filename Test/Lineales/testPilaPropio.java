/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Lineales;

import Lineales.Estaticas.Pila;

/**
 *
 * @author matir
 */
public class testPilaPropio {

    static String sV = "VERDADERO", sF = "FALSO";

//<editor-fold defaultstate="collapsed" desc="COLORES PARA PRINT">
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
//</editor-fold>

    public static void main(String[] args) {
        //Crear pila
        Pila pila = new Pila();
        //Apilar elementos
        for (int i = 0; i < 6; i++) {
            pila.apilar((int) (Math.random() * 10));
        }
        //Mostrar pila
        System.out.println(pila.toString());
        //Desapilar un elemento
        pila.desapilar();
        //Mostrar pila 
        System.out.println(pila.toString());
        //Clonar
        Pila clon = pila.clone();
        //Mostrar clon
        System.out.println("CLON: " + clon.toString());
        //Preguntar si es vacia
        System.out.println("Pila vacia? " + ANSI_RED + (pila.esVacia() ? sV : sF) + ANSI_RESET);

    }
}
