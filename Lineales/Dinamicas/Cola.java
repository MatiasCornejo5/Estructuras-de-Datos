/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lineales.Dinamicas;

/**
 *
 * @author Cornejo Matias FAI-1919
 * @author Petenatti Francisco FAI-2347
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object nuevoElem) {
        boolean exito = true;
        Nodo aux = new Nodo(nuevoElem, null);
        if (this.frente == null && this.fin == null) { //la cola esta vacia por lo tanto pone el primer elemento
            this.frente = aux;
            this.fin = this.frente;
        } else { //pone el elemento al final de la cola
            this.fin.setEnlace(aux);
            this.fin = aux;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {// error de cola vacia
            exito = false;
        } else {//quita el primer elemento de la cola. actualiza el fin en caso de que haya un solo elemento
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }

        return exito;
    }

    public Object obtenerFrente() {
        Object elemento = null;
        if (this.frente != null) {
            elemento = this.frente.getElemento();
        }
        return elemento;
    }

    public boolean esVacia() {
        return this.frente == null && this.fin == null;
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    public Cola clone() {
        Nodo aux1, aux2;
        Cola clon = new Cola();
        aux1 = this.frente;
        clon.frente = new Nodo(aux1.getElemento(), null);
        aux1 = aux1.getEnlace();
        aux2 = clon.frente;
        while (aux1.getEnlace() != null) {
            aux2.setEnlace(new Nodo(aux1.getElemento(), null));
            aux2 = aux2.getEnlace();
            aux1 = aux1.getEnlace();
        }
        aux2.setEnlace(new Nodo(aux1.getElemento(), null));
        clon.fin = aux2.getEnlace();
        return clon;
    }

    public String toString() {
        String cadena = "";

        if (this.frente == null) {
            cadena = "cola vacia";
        } else {
            cadena = "[";
            Nodo aux = this.frente;
            while (aux != null) {
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

    //SIMULACRO 
    /*c) En una clase TestCadenas, que utilice los TDA Lista, Pila y Cola vistos en la materia, para guardar elementos
    de tipo CHAR, implementar el método generar (Cola c1) que recibe por parámetro una estructura cola c1
    que tiene el siguiente formato: a1#a2#a3#….#an, donde cada ai en una sucesión de letras mayúsculas y a
    partir de c1 debe generar como salida otra Cola de la forma: a1a1´a1#a2a2´a2#….#anan´an donde cada ai´ es la
    secuencia de letras mayúsculas ai pero invertida. Ejemplo.: Si c1 es : AB#C#DEF , entonces la operación
    generar devolverá una Cola con el siguiente formato: ABBAAB#CCC#DEFFEDDEF*/
    public Cola generarCola(Cola c1) {
        Cola c2 = c1.clone();
        Cola c3 = new Cola();
        Cola aux = new Cola();
        Pila p1 = new Pila();
        char elemento;
        while (!c2.esVacia()) {
            while (!c2.esVacia() && (char) c2.obtenerFrente() != '#') {
                elemento = (char) c2.obtenerFrente();
                c3.poner(elemento);
                aux.poner(elemento);
                p1.apilar(elemento);
                c2.sacar();
            }
            while (!p1.esVacia()) {
                c3.poner(p1.obtenerTope());
                p1.desapilar();
            }
            while (!aux.esVacia()) {
                c3.poner(aux.obtenerFrente());
                aux.sacar();
            }
            if (!c2.esVacia()) {
                c3.poner('#');
                c2.sacar();
            }
        }

        return c3;
    }

    /*d) En la clase Matematica, que utiliza a los TDA Lista, Pila y Cola vistos en la materia para guardar elementos
    tipo CHAR que representan una expresión matemática, desarrollar el método verificarBalanceo (Cola q)
    que recibe por parámetro una cola con una expresión matemática y verifique que los paréntesis, corchetes y
    llaves estén correctamente balanceados. Debe usar como estructura auxiliar alguno de los TDA lineales
    vistos, el que considere más apropiado.
    Ejemplos: Si q es ← { 5 + [ 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver TRUE
    Si q es ← { 5 + 8 * 9 -( 4 / 2 ) + 7 ] -1 } ← el método debe devolver FALSE
     */
    public boolean verificarBalanceo(Cola q) {
        boolean respuesta = true;
        Cola c1 = q.clone();
        Pila p1 = new Pila();
        Object aux = c1.obtenerFrente();
        while (!c1.esVacia() && respuesta) {
            while (aux.equals('(') || aux.equals('[') || aux.equals('{')) {
                p1.apilar(aux);
                c1.sacar();
                aux = c1.obtenerFrente();
            }
            if (aux.equals(')')) {
                if (!p1.esVacia() && p1.obtenerTope().equals('(')) {
                    p1.desapilar();
                    respuesta = true;
                } else {
                    respuesta = false;
                }
            } else {
                if (aux.equals(']')) {
                    if (!p1.esVacia() && p1.obtenerTope().equals('[')) {
                        p1.desapilar();
                        respuesta = true;
                    } else {
                        respuesta = false;
                    }
                } else {
                    if (aux.equals('}')) {
                        if (!p1.esVacia() && p1.obtenerTope().equals('{')) {
                            p1.desapilar();
                            respuesta = true;
                        } else {
                            respuesta = false;
                        }
                    }
                }
            }

            c1.sacar();
            aux = c1.obtenerFrente();
        }
        if (!p1.esVacia()) {
            respuesta = false;
        }

        return respuesta;
    }

    /*En una clase TestLineales, que utiliza los TDA Pila, Cola y Lista vistos en la materia, 
    implementar el método generarLista(Cola cola1) que recibe por parámetro una estructura cola cola1 que tiene el siguiente formato: 
    c1#c2#c3#….#cn, donde cada ci es una sucesión de elementos de la cola, se debe generar como salida una lista con todas las secuencias pares invertidas y las impares igual que en la original. 

    Ejemplo.: Si cola1 es  AB#CAE#DEF#EBD , entonces la operación generarLista devolverá una Lista con el siguiente formato: 
    AB#EAC#DEF#DBE.  */
    public Lista generarLista(Cola cola1) {
        Lista lista = new Lista();
        Cola clon = cola1.clone();
        Pila pila = new Pila();
        Object elemento;
        int /*i = lista.longitud(,*/ contador = 1;
        while (!clon.esVacia()) {
            while (!clon.esVacia() && (char) clon.obtenerFrente() != '#') {
                if (contador % 2 != 0) {
                    elemento = clon.obtenerFrente();
                    lista.insertar(elemento, lista.longitud()+1);                    
                    clon.sacar();
                } else {
                    pila.apilar(clon.obtenerFrente());
                    clon.sacar();
                }
            }
            contador = contador + 1;
            while (!pila.esVacia()) {
                lista.insertar(pila.obtenerTope(), lista.longitud() + 1);
                pila.desapilar();
            }
            if (!clon.esVacia()) {
                lista.insertar(clon.obtenerFrente(), lista.longitud() + 1);
                clon.sacar();
            }

        }

        return lista;
    }

}
