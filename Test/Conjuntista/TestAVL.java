/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Conjuntista;

import Conjuntista.ArbolAVL;

/**
 *
 * @author matir
 */
public class TestAVL {
    
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        
        arbol.insertar(8);
        arbol.insertar(6);
        arbol.insertar(4);
        
        System.out.println(arbol.toString());
    }
}
