/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Lineales;

import Lineales.Estaticas.Cola;

/**
 *
 * @author matir
 */
public class testColaPropio {
    
    public static void main(String[] args) {
        
        //crear una cola nueva
        Cola cola = new Cola();
        
        for (int i = 1; i < 11; i++) {
            cola.poner(i);
        }
        
        System.out.println("Cola \n" + cola.toString());
        
        //clonamos la cola
        
        Cola clon = cola.clone();
        
        System.out.println("Clon \n" + clon.toString());
        
        //sacamos elementos de la cola original
        cola.sacar();
        cola.sacar();
        
        System.out.println("Cola \n" + cola.toString());
        
        //clonamos nuevamente
        clon = cola.clone();
        
        System.out.println("Clon \n" + clon.toString());
    }
}
