/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Conjuntista;

import Conjuntista.ArbolBB;
import Lineales.Dinamicas.Pila;

/**
 *
 * @author matir
 */
public class TestABB {

    public static void main(String[] args) {
        ArbolBB a = new ArbolBB();
        a.insertar(50);
        a.insertar(32);
        a.insertar(91);
        a.insertar(21);
        a.insertar(44);
        a.insertar(67);
        a.insertar(131);
        a.insertar(58);
        a.insertar(73);
        a.insertar(63);
        System.out.println(a.toString());

        System.out.println("suma posorden: " + a.sumarPosOrdenDesde(35, 110));
        System.out.println("suma preorden: "+ a.sumarPreOrdenDesde(35, 60));
        System.out.println("suma inorden: "+ a.sumarInOrdenDesde(15, 30));
        Pila pila = new Pila();
        pila.apilar(91);
        pila.apilar(131);
        pila.apilar(158);
        System.out.println(pila.toString());
        System.out.println("es recorrido valido: "+ a.verifiarCamino(pila));
        
    }
}
