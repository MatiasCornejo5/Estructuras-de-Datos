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
public class Lista {

    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public int longitud() {
        // Devuelve la cantidad de elementos de la lista.
        int cantidad = 0;
        Nodo aux = this.cabecera;
        while (aux != null) {
            cantidad++;
            aux = aux.getEnlace();
        }
        return cantidad;
    }

    public boolean insertar(Object nuevoElem, int pos) {
        //inserta elemento en la lista
        boolean exito = true;

        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) {//crea un nuevo nodo y se enlaza a la cabecera
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
            } else {//avanza hasta el elemento en la pos-1
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                //crea el nodo y lo enlaza
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }

        //nunca hay error de lista llena, por lo tanto retorna true
        return exito;
    }

    public boolean eliminar(int pos) {
        //elimina un elemento segun la posicion recibida
        boolean exito = true;
        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        }
        return exito;
    }

    public Object recuperar(int pos) {
        //recupera un elemento segun la posicion recibida
        Object elemento = null;
        if (pos > 0 && pos <= this.longitud()) {
            if (pos == 1) {
                elemento = this.cabecera.getElemento();
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos) {
                    aux = aux.getEnlace();
                    i++;
                }
                elemento = aux.getElemento();
            }
        }
        return elemento;
    }

    public int localizar(Object elemento) {
        //recupera la posicion de un elemento dado
        int posicion = -1, i = 1;
        boolean encontrado = false;
        Nodo aux = this.cabecera;
        while (i <= this.longitud() && encontrado == false) {
            if (aux.getElemento().equals(elemento)) {
                encontrado = true;
                posicion = i;
            }
            i++;
            aux = aux.getEnlace();
        }
        return posicion;
    }

    public boolean esVacia() {
        //devuelve true si es vacia
        return this.cabecera == null;
    }

    public void vaciar() {
        //vacia la lista completa
        this.cabecera = null;
    }

    public Lista clone() {
        //clona la lista
        Lista clone = new Lista();
        Nodo aux1, aux2;
        aux1 = this.cabecera;
        clone.cabecera = new Nodo(aux1.getElemento(), null);
        aux1 = aux1.getEnlace();
        aux2 = clone.cabecera;
        while (aux1 != null) {
            aux2.setEnlace(new Nodo(aux1.getElemento(), null));
            aux1 = aux1.getEnlace();
            aux2 = aux2.getEnlace();
        }
        return clone;
    }

    public String toString() {
        //Devuelve una cadena con la lista ordenada
        String cadena = "";
        if (this.cabecera == null) {
            cadena = "lista vacia";
        } else {
            cadena = "[";
            Nodo aux = this.cabecera;
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

    //SIMULACRO PRIMER PARCIAL 
    /*
    a) Agregar al TDA Lista la operación obtenerMultiplos(int num) que recibe un número y devuelve una lista nueva
    que contiene todos los elementos de las posiciones múltiplos de num, en el mismo orden encontrado,
    haciendo un único recorrido de las estructuras original y copia; y sin usar otras operaciones del TDA.
    Ejemplo: si se invoca con la lista <A,B,C,D,E,F,G,H,I,J> y num=3, el método debe devolver la lista <C,F,I>
     */
    public Lista obtenerMultiplos(int num) {
        int posicion = 1;
        Lista multiplos = new Lista();
        obtenerMultiplosAux(multiplos, this.cabecera, num, posicion);
        return multiplos;
    }

    private void obtenerMultiplosAux(Lista multiplos, Nodo nodo, int num, int posicion) {

        if (nodo != null) {
            obtenerMultiplosAux(multiplos, nodo.getEnlace(), num, posicion + 1);
            if (posicion % num == 0) {
                //multiplos.insertar(nodo.getElemento(), multiplos.longitud()+1);
                Nodo aux = new Nodo(nodo.getElemento(), null);
                aux.setEnlace(multiplos.cabecera);
                multiplos.cabecera = aux;
            }
            //obtenerMultiplosAux(multiplos, nodo.getEnlace(), num, posicion + 1);
        }

    }

    /* b) Agregar al TDA Lista la operación eliminarApariciones(TipoElemento x) que elimine todas las apariciones de
    elementos iguales a x, haciendo un único recorrido de la estructura y sin usar otras operaciones del TDA.*/
    public void eliminarAparicion(Object elemento) {
        Nodo nodoActual = this.cabecera;
        Nodo auxiliar = new Nodo(null, null);

        while (nodoActual != null) {
            if (nodoActual.getElemento().equals(elemento)) {
                if (nodoActual.equals(this.cabecera)) {
                    this.cabecera = this.cabecera.getEnlace();
                } else {
                    auxiliar.setEnlace(nodoActual.getEnlace());
                }
            }
            auxiliar = nodoActual;
            nodoActual = nodoActual.getEnlace();
        }
    }

    public void elimnarAnterior(Object elemento) {
        Nodo nodoActual = this.cabecera;
        Nodo auxiliar = new Nodo(null, null);
        while (nodoActual != null) {
            if (!nodoActual.getElemento().equals(elemento)) {
                auxiliar = nodoActual.getEnlace();
                if ( auxiliar!= null && auxiliar.getElemento().equals(elemento)) {
                    nodoActual.setElemento(auxiliar.getElemento());
                    nodoActual.setEnlace(auxiliar.getEnlace());
                }
            }/* else {
                nodoActual = nodoActual.getEnlace();
            }*/
            nodoActual = nodoActual.getEnlace();
        }
    }

    //Ejercicio de parcial 2021
    /*Agregar al TDA Lista la operación insertarPosterior(valor1, valor2) que busca todas las apariciones de valor1 en la lista, 
    e inserta un nodo con valor2 en la posición posterior. Si valor1 está en posición 1, debe insertar a valor2 antes y después de valor1.*/
    public void insertarPosterior(Object valor1, Object valor2) {
        Nodo nodoActual = this.cabecera, auxiliar = new Nodo(valor2, null);
        while (nodoActual != null) {
            if (nodoActual.getElemento().equals(valor1)) {
                if (nodoActual.equals(this.cabecera)) {
                    auxiliar.setEnlace(nodoActual);
                    this.cabecera = auxiliar;
                    nodoActual = auxiliar;
                } else {
                    Nodo auxiliar2 = new Nodo(valor2, null);
                    auxiliar2.setEnlace(nodoActual.getEnlace());
                    nodoActual.setEnlace(auxiliar2);
                }
            }
            nodoActual = nodoActual.getEnlace();
        }
    }
}
