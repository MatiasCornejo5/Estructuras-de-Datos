/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Lineales;

import Lineales.Dinamicas.Lista;

/**
 *
 * @author matir
 */
public class testLista {
    
    public static void main(String[] args) {
        Lista lista = new Lista ();
        
        lista.insertar('s', 1);
        lista.insertar('s', 2);
        lista.insertar('q', 3);
        lista.insertar('x', 4);
        lista.insertar('s', 5);
        lista.insertar('h', 6);
        lista.insertar('e', 7);
        lista.insertar('s', 8);
        lista.insertar('s', 9);
        lista.insertar('r', 10);
        
        System.out.println(lista.toString());
        
        lista.elimnarAnterior('s');
        
        System.out.println(lista.toString());
    }
}
