/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimulacrosParcial;

import Jerarquicas.ArbolBin;
// import Lineales.Dinamicas.Lista;
// import Lineales.Dinamicas.Cola;
// import Lineales.Dinamicas.Pila;

/**
 *
 * @author matir
 */
public class SimulacroParcial {

    public static void main(String[] args) {
        /*Lista lista = new Lista();
        lista.insertar("A", 1);
        lista.insertar("A", 2);
        lista.insertar("C", 3);
        lista.insertar("D", 4);

        System.out.println(lista.obtenerMultiplos(2).toString());

        lista.eliminarAparicion("A");

        System.out.println(lista.toString());*/ //SIMULACRO A Y B

        //Cola c1 = new Cola();
        /*c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');
        
        Cola aux = c1.generarCola(c1);
        System.out.println(aux.toString());*/

 /* //ejercicio balanceo
        c1.poner('{');
        c1.poner('5');
        c1.poner('+');
        c1.poner('[');
        c1.poner('8');
        c1.poner('*');
        c1.poner('9');
        c1.poner('-');
        c1.poner('(');
        c1.poner('4');
        c1.poner('/');
        c1.poner('2');
        c1.poner(')');
        c1.poner('+');
        c1.poner('7');
        c1.poner(']');
        c1.poner('-');
        c1.poner('1');
        c1.poner('}');
        
        boolean resp = c1.verificarBalanceo(c1);
        System.out.println(resp);
         */
 /* ArbolBin arbol = new ArbolBin();

        arbol.insertar(1, 1, 'D');
        arbol.insertar(2, 1, 'I');
        arbol.insertar(5, 1, 'D');
        arbol.insertar(3, 2, 'I');
        arbol.insertar(4, 2, 'D');
        arbol.insertar(6, 5, 'I');
        System.out.println(arbol.toString());

        Lista patron = new Lista();
        patron.insertar(1, 1);
        patron.insertar(2, 2);
        patron.insertar(3, 3);
        System.out.println(patron.toString());

        System.out.println(arbol.verificarPatron(patron));
        
        System.out.println(arbol.clonarInvertido().toString());*/
 /*Lista listaNueva = new Lista();
        listaNueva.insertar(1, 1);
        listaNueva.insertar(7, 2);
        listaNueva.insertar(6, 3);
        listaNueva.insertar(1, 4);
        listaNueva.insertar(4, 5);
        listaNueva.insertar(1, 6);
        listaNueva.insertar(9, 7);

        System.out.println(listaNueva.toString());

        listaNueva.insertarPosterior(1, 3);

        System.out.println(listaNueva.toString());*/
        /*Cola cola1 = new Cola();

        cola1.poner('A');
        cola1.poner('B');
        cola1.poner('#');
        cola1.poner('C');
        cola1.poner('A');
        cola1.poner('E');
        cola1.poner('#');
        cola1.poner('D');
        cola1.poner('E');
        cola1.poner('F');
        cola1.poner('#');
        cola1.poner('E');
        cola1.poner('B');
        cola1.poner('D');
        
        Lista lista = cola1.generarLista(cola1);
        
        System.out.println(lista.toString());*/
        
        ArbolBin arbol = new ArbolBin();
        arbol.insertar('F','F' , 'D');
        arbol.insertar('A','F' , 'I');
        arbol.insertar('T','F' , 'D');
        arbol.insertar('P','T' , 'D');
        arbol.insertar('H','A' , 'I');
        arbol.insertar('F','A' , 'D');
        arbol.insertar('X','P' , 'I');
        System.out.println(arbol.toString());
        
        arbol.cambiarHijos('Z', 'T', 'Q');
        System.out.println(arbol.toString());
    }
}
