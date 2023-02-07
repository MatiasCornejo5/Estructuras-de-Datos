/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Jerarquicas;

import Jerarquicas.ArbolGen;
// import Lineales.Dinamicas.Lista;

/**
 *
 * @author matir
 */
public class testRapidoGenerico {

    public static void main(String[] args) {

        ArbolGen arbol = new ArbolGen();

        /*arbol.insertar(20, 20);
        arbol.insertar(13, 20);
        arbol.insertar(54, 20);
        arbol.insertar(15, 13);
        arbol.insertar(12, 13);
        arbol.insertar(11, 54);
        arbol.insertar(27, 54);
        arbol.insertar(4, 54);
        arbol.insertar(17, 27);
        
        Lista lista = new Lista();
        lista.insertar(20, 1);
        lista.insertar(17, 2);
        /*lista.insertar(12, 3);
        lista.insertar(45, 4);
        
        boolean resp = arbol.verificarCamino(lista);
        System.out.println(resp);*/
        arbol.insertar(91, 91);
        arbol.insertar(52, 91);
        arbol.insertar(65, 91);
        arbol.insertar(92, 91);
        arbol.insertar(71, 52);
        arbol.insertar(91, 52);
        arbol.insertar(14, 65);
        arbol.insertar(23, 92);
        arbol.insertar(31, 92);
        arbol.insertar(88, 23);
        
        System.out.println(arbol.toString());
        /*System.out.println(arbol.esHijoDe("A", "U"));
        System.out.println(arbol.esPadreDe("Ñ", "M"));
        System.out.println(arbol.esHermanoPosterior("P", "M"));*/
        System.out.println(arbol.insertarSobrino(92, 65, 89));
        System.out.println(arbol.toString());
        
    }
}
