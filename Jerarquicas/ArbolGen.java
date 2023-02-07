/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jerarquicas;

import Lineales.Dinamicas.Cola;
import Lineales.Dinamicas.Lista;

/**
 *
 * @author matir
 */
public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        //Crea un árbol genérico vacío

        this.raiz = null;
    }

    public boolean insertar(Object elem, Object padre) {
        //Dado un elemento elemNuevo y un elemento elemPadre, agrega elemNuevo como hijo 
        //de la primer aparición de elemPadre. Para que la operación termine con éxito debe 
        //existir un nodo en el árbol con elemento = elemPadre. 
        //No se establece ninguna preferencia respecto a la posición del hijo respecto a sus posibles 
        //hermanos.Esta operación devuelve verdadero cuando se pudo agregar elemNuevo a la estructura
        //y falso en caso contrario.
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoGen(elem);
        } else {
            NodoGen nodoPadre = obtenerNodo(this.raiz, padre);
            if (nodoPadre != null) {
                if (nodoPadre.getHIzquierdo() == null) {
                    nodoPadre.setHIzquierdo(new NodoGen(elem));
                } else {
                    NodoGen aux = nodoPadre.getHIzquierdo();
                    while (aux.getHermanoD() != null) {
                        aux = aux.getHermanoD();
                    }
                    aux.setHermanoD(new NodoGen(elem));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen arbol, Object buscado) {
        NodoGen resultado = null;
        if (arbol != null) {
            if (arbol.getElem().equals(buscado)) {
                resultado = arbol;
            } else {
                NodoGen aux = arbol.getHIzquierdo();
                while (aux != null && resultado == null) {
                    resultado = obtenerNodo(aux, buscado);
                    aux = aux.getHermanoD();
                }
            }
        }
        return resultado;
    }

    public boolean esVacio() {
        //Devuelve falso si hay al menos un elemento cargado en el árbol y verdadero en caso contrario.
        boolean exito = false;
        if (this.raiz == null) {
            exito = true;
        }
        return exito;
    }

    public boolean pertenece(Object elem) {
        // Devuelve verdadero si el elemento pasado por parámetro está en el árbol, y falso 
        //en caso contrario.
        boolean exito;
        if (this.raiz != null) {
            exito = perteneceAux(this.raiz, elem);
        } else {
            exito = false;
        }
        return exito;
    }

    private boolean perteneceAux(NodoGen nodo, Object elem) {
        boolean res = false;
        if (nodo.getElem().equals(elem)) {
            res = true;
        } else {
            NodoGen aux = nodo.getHIzquierdo();
            while (aux != null && !res) {
                res = perteneceAux(aux, elem);
                aux = aux.getHermanoD();
            }
        }
        return res;
    }

    public Object padre(Object hijo) {
        // Dado un elemento devuelve el valor almacenado en su nodo padre 
        //(busca la primera aparición de elemento).
        Object resultado = null;
        NodoGen buscadoPadre;
        if (this.raiz != null) {
            if (this.raiz.getElem().equals(hijo) == false) {
                buscadoPadre = auxPadre(this.raiz, hijo);
                if (buscadoPadre != null) {
                    resultado = buscadoPadre.getElem();
                }
            }
        }
        return resultado;
    }

    private NodoGen auxPadre(NodoGen nodo, Object elem) {
        NodoGen resultado = null;
        NodoGen aux;
        if (nodo != null) {
            if (nodo.getHIzquierdo() != null && nodo.getHIzquierdo().getElem().equals(elem)) {
                resultado = nodo;
            } else {
                if (nodo.getHIzquierdo() != null) {
                    aux = nodo.getHIzquierdo();
                    while (aux.getHermanoD() != null && resultado == null) {
                        if (aux.getHermanoD().getElem().equals(elem)) {
                            resultado = nodo;
                        } else {
                            aux = aux.getHermanoD();
                        }
                    }
                }
                if (resultado == null) {
                    aux = nodo.getHIzquierdo();
                    while (aux != null && resultado == null) {
                        resultado = auxPadre(aux, elem);
                        aux = aux.getHermanoD();
                    }
                }
            }
        }
        return resultado;
    }

    public Lista ancestros(Object elemento) {
        // Si el elemento se encuentra en el árbol, devuelve una lista con el camino desde 
        //la raíz hasta dicho elemento(es decir, con los ancestros del elemento).
        //Si el elemento no está en el árbol devuelve la lista vacía.
        Lista susAncestros = new Lista();
        ancestrosAux(susAncestros, elemento, this.raiz);
        return susAncestros;
    }

    private boolean ancestrosAux(Lista lista, Object elemento, NodoGen nodo) {
        boolean respuesta = false;
        if (nodo != null && !respuesta) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            if (nodo.getHIzquierdo() != null) {
                respuesta = ancestrosAux(lista, elemento, nodo.getHIzquierdo());
            }
            if (nodo.getElem().equals(elemento)) {
                lista.eliminar(lista.longitud());
                respuesta = true;
            } else {
                if (!respuesta) {
                    lista.eliminar(lista.longitud());
                    if (nodo.getHermanoD() != null && !respuesta) {
                        respuesta = ancestrosAux(lista, elemento, nodo.getHermanoD());
                    }
                }
            }
        }
        return respuesta;
    }

    public int altura() {
        // Devuelve la altura del árbol, es decir la longitud del camino más largo desde 
        //la raíz hasta una hoja (Nota:un árbol vacío tiene altura -1 y una hoja tiene altura 0).
        int cont = 0, max = 0;
        max = auxAltura(this.raiz, cont, max);
        return max;
    }

    private int auxAltura(NodoGen nodo, int aux, int max) {
        if (nodo != null) {
            if (nodo.getHIzquierdo() != null) {
                max = (auxAltura(nodo.getHIzquierdo(), aux + 1, max));
            }
            if (nodo.getHermanoD() != null) {
                max = (auxAltura(nodo.getHermanoD(), aux, max));
            }
            if (aux > max) {
                max = aux;
            }
        }
        return max;
    }

    public int nivel(Object elem) {
        // Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en el árbol devuelve -1
        int nroNvl = -1;
        if (this.raiz != null) {
            nroNvl = auxNivel(this.raiz, elem, nroNvl);
        }
        return nroNvl;
    }

    private int auxNivel(NodoGen nodo, Object elem, int nroN) {
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                nroN = 0;
            } else {
                if (nodo.getHIzquierdo() != null && nroN == -1) {
                    nroN = auxNivel(nodo.getHIzquierdo(), elem, nroN);
                    if (nroN != -1) {
                        nroN++;
                    }
                }
                if (nodo.getHermanoD() != null && nroN == -1) {
                    nroN = auxNivel(nodo.getHermanoD(), elem, nroN);
                }
            }
        }
        return nroN;
    }

    public void vaciar() {
        // Quita todos los elementos de la estructura. El manejo de memoria es similar al explicado anteriormente
        //para estructuras lineales dinámicas.
        this.raiz = null;
    }

    public ArbolGen clone() {
        // Genera y devuelve un árbol genérico que es equivalente (igual estructura y contenido de los nodos) que el
        //árbol original.
        ArbolGen clon = new ArbolGen();
        clon.raiz = clonarAux(this.raiz);
        return clon;

    }

    private NodoGen clonarAux(NodoGen n) {
        NodoGen aux;
        if (n != null) {
            aux = new NodoGen(n.getElem(), clonarAux(n.getHIzquierdo()), clonarAux(n.getHermanoD()));
        } else {
            aux = null;
        }
        return aux;
    }

    public Lista listarPreorden() {
        // Devuelve una lista con los elementos del árbol en el recorrido en preorden
        Lista resultado = new Lista();
        auxListarPre(this.raiz, resultado);
        return resultado;
    }

    private void auxListarPre(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            auxListarPre(nodo.getHIzquierdo(), lista);
            NodoGen aux = nodo.getHIzquierdo();
            while (aux != null) {
                auxListarPre(aux.getHermanoD(), lista);
                aux = aux.getHermanoD();
            }
        }
    }

    public Lista listarInorden() {
        // Devuelve una lista con los elementos del árbol en el recorrido en inorden
        Lista resultado = new Lista();
        auxListaIn(this.raiz, resultado);
        return resultado;
    }

    private void auxListaIn(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            auxListaIn(nodo.getHIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            NodoGen aux = nodo.getHIzquierdo();
            while (aux != null && aux.getHermanoD() != null) {
                auxListaIn(aux.getHermanoD(), lista);
                aux = aux.getHermanoD();
            }
        }
    }

    public Lista listarPosorden() {
        // Devuelve una lista con los elementos del árbol en el recorrido en posorden
        Lista resultado = new Lista();
        auxListaPos(this.raiz, resultado);
        return resultado;
    }

    private void auxListaPos(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            auxListaPos(nodo.getHIzquierdo(), lista);
            NodoGen aux = nodo.getHIzquierdo();
            while (aux != null && aux.getHermanoD() != null) {
                auxListaPos(aux.getHermanoD(), lista);
                aux = aux.getHermanoD();
            }
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        // Devuelve una lista con los elementos del árbol en el recorrido por niveles
        Lista lista = new Lista();
        Cola colaAux = new Cola();
        colaAux.poner(this.raiz);
        NodoGen aux;
        while (!colaAux.esVacia()) {
            aux = (NodoGen) colaAux.obtenerFrente();
            colaAux.sacar();
            lista.insertar(aux.getElem(), lista.longitud() + 1);
            if (aux.getHIzquierdo() != null) {
                colaAux.poner(aux.getHIzquierdo());
            }
            NodoGen hijo = aux.getHIzquierdo();
            while (hijo != null && hijo.getHermanoD() != null) {
                colaAux.poner(hijo.getHermanoD());
                hijo = hijo.getHermanoD();
            }
        }
        return lista;
    }

    public String toString() {
        // Genera y devuelve una cadena de caracteres que indica cuál es la raíz del árbol y quienes son los hijos de
        //cada nodo.
        return auxToString(this.raiz);
    }

    private String auxToString(NodoGen nodo) {
        String resultado = "";
        if (nodo != null) {
            resultado += nodo.getElem().toString() + " --> ";
            NodoGen hijo = nodo.getHIzquierdo();
            while (hijo != null) {
                resultado += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoD();
            }
            hijo = nodo.getHIzquierdo();
            while (hijo != null) {
                resultado += "\n" + auxToString(hijo);
                hijo = hijo.getHermanoD();
            }
        }
        return resultado;
    }

    public int grado() {
        /*
        Retorna la cantidad mayor de hijos que tiene un subarbol desde el nodo de partida
         */
        int grado = -1;
        grado = gradoAux(this.raiz);
        return grado;
    }

    public int gradoSubarbol(Object elemento) {
        /*
        Retorna la cantidad mayor de hijos que tiene un subarbol desde el nodo de partida
         */
        NodoGen elementoBuscado = obtenerNodo(this.raiz, elemento);
        int grado = -1;
        if (elementoBuscado != null) {
            grado = gradoAux(elementoBuscado);
        }
        return grado;
    }

    //Auxiliares
    private int gradoAux(NodoGen padre) {
        /*
        Realiza un recorrido en preorden en busca del subarbol con mayor grado
         */
        int cantHijosMayor = -1, cantHijosRef;
        NodoGen hijo;

        if (padre != null) {
            cantHijosMayor++;
            //Cuenta sus hijos
            hijo = padre.getHIzquierdo();
            while (hijo != null) {
                cantHijosMayor++;
                hijo = hijo.getHermanoD();
            }
            //Compara con sus hijos
            hijo = padre.getHIzquierdo();
            while (hijo != null) {
                cantHijosRef = gradoAux(hijo);
                if (cantHijosMayor < cantHijosRef) {
                    cantHijosMayor = cantHijosRef;
                }
                hijo = hijo.getHermanoD();
            }
        }
        return cantHijosMayor;
    }

    //Simulacro parcial
    public int misterioso2() {
        return misteriosoAux(this.raiz);
    }

    private int misteriosoAux(NodoGen n) {
        int res = -1;
        int aux = -1;

        if (n != null) {
            NodoGen h = n.getHIzquierdo();

            while (h != null) {
                res = misteriosoAux(h);

                if (aux < res) {
                    aux = res;
                }

                h = h.getHermanoD();
            }

            aux = aux + 1;
        }

        return aux;

    }

    //Ejercicio Simulacro 2do Parcial
    public boolean verificarCamino(Lista lista) {
        boolean resp = false;
        Lista clone = lista.clone();
        if (this.raiz != null && !lista.esVacia()) {
            resp = verificarCaminoAux(this.raiz, clone);
        }
        return resp;
    }

    private boolean verificarCaminoAux(NodoGen nodo, Lista lista) {
        boolean retorno = false;
        NodoGen aux;
        if (nodo != null) {
            if (!lista.esVacia()) {
                if (nodo.getElem().equals(lista.recuperar(1))) {
                    lista.eliminar(1);
                    aux = nodo.getHIzquierdo();
                    retorno = verificarCaminoAux(aux, lista);
                    while (aux != null && !retorno) {
                        retorno = verificarCaminoAux(aux.getHermanoD(), lista);
                        aux = aux.getHermanoD();
                    }
                }
            } else {
                retorno = true;
            }
        }
        return retorno;
    }

    public Lista listarEntreNiveles(int nivel1, int nivel2) {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarEntreNivelesAux(this.raiz, nivel1, nivel2, lista);
        }
        return lista;
    }

    private void listarEntreNivelesAux(NodoGen nodo, int nivel1, int nivel2, Lista lista) {

    }

    //---------> EJERCICIO DE 2DO PARCIAL.2021
    //PARCIAL MATI
    /*Implementar esHijoDe(obj a, Obj b) que verifica si en el arbol el elemento 'a' es hijo de 'b' y devuelve true/false*/
    public boolean esHijoDe(Object a, Object b) {
        return esHijoDeAux(a, b, this.raiz);
    }

    private boolean esHijoDeAux(Object a, Object b, NodoGen padre) {
        boolean respuesta = false;
        NodoGen hijo;
        if (padre != null) {
            if (padre.getElem().equals(b)) {
                hijo = padre.getHIzquierdo();
                while (hijo != null && !respuesta) {
                    if (hijo.getElem().equals(a)) {
                        respuesta = true;
                    } else {
                        hijo = hijo.getHermanoD();
                    }
                }
            }
            if (respuesta == false) {
                hijo = padre.getHIzquierdo();
                while (hijo != null && !respuesta) {
                    respuesta = esHijoDeAux(a, b, hijo);
                    if (respuesta == false) {
                        hijo = hijo.getHermanoD();
                    }
                }
            }
        }
        return respuesta;
    }

    //PARCIAL KEVIN
    /*Implementar esPadreDe(Obj a, Obj b) que verifica si en el arbol el elemento 'a' es padre de 'b' y devuelve true/false*/
    public boolean esPadreDe(Object a, Object b) {
        return esPadreAux(a, b, this.raiz);
    }

    private boolean esPadreAux(Object a, Object b, NodoGen padre) {
        boolean esPadre = false;
        NodoGen hijo;
        if (padre != null) {
            if (padre.getElem().equals(a)) {
                hijo = padre.getHIzquierdo();
                while (hijo != null && !esPadre) {
                    if (hijo.getElem().equals(b)) {
                        esPadre = true;
                    } else {
                        hijo = hijo.getHermanoD();
                    }
                }
            }
            if (esPadre == false) {
                hijo = padre.getHIzquierdo();
                while (hijo != null && !esPadre) {
                    esPadre = esPadreAux(a, b, hijo);
                    if (esPadre == false) {
                        hijo = hijo.getHermanoD();
                    }
                }
            }
        }
        return esPadre;
    }

    //PARCIAL FRAN
    /*Implementar esHermanoPosterior(Obj a, Obj b) que verifica si en el arbol el elemento 'a' esta desp que 'b' 
    en la lista de hijos del mismo padre y devuelve true/false*/
    public boolean esHermanoPosterior(Object a, Object b) {
        boolean res = false;
        if (this.raiz != null) {
            res = esHermanoPosteriorAux(a, b, this.raiz);
        }
        return res;
    }

    private boolean esHermanoPosteriorAux(Object a, Object b, NodoGen n) {
        boolean res = false;
        NodoGen hijo, hermano;
        if (n != null) {
            if (n.getElem().equals(b)) {
                if (n.getHermanoD() != null) {
                    hermano = n.getHermanoD();
                    //busco si 'a' es hermano derecho
                    while (hermano != null && !res) {
                        if (hermano.getElem().equals(a)) {
                            res = true;
                        } else {
                            hermano = hermano.getHermanoD();
                        }
                    }
                }
            }
            hijo = n.getHIzquierdo();
            while (hijo != null && !res) {
                res = esHermanoPosteriorAux(a, b, hijo);
                hijo = hijo.getHermanoD();
            }
        }
        return res;
    }

    public boolean insertarSobrino(Object t, Object h, Object s) {
        return insertarSobrinoAux(this.raiz, t, h, s);
    }

    private boolean insertarSobrinoAux(NodoGen nodo, Object t, Object h, Object s) {
        boolean respuesta = false, bandera = false;
        NodoGen hermano, hijo;
        if (nodo != null) {
            if (nodo.getElem().equals(t)) {
                hermano = nodo.getHermanoD();
                if (hermano != null && hermano.equals(h)) {
                    if (hermano.getHIzquierdo() == null) {
                        hermano.setHIzquierdo(new NodoGen(s, null, null));
                        respuesta = true;
                    } else {
                        NodoGen aux = hermano.getHIzquierdo();
                        while (aux.getHermanoD() != null) {
                            aux = aux.getHermanoD();
                        }
                        aux.setHermanoD(new NodoGen(s, null, null));
                        respuesta = true;
                    }
                }
            }
            if (nodo.getElem().equals(h)) {
                hermano = nodo.getHermanoD();
                while (hermano != null && !bandera) {
                    if (hermano.equals(t)) {
                        bandera = true;
                    } else {
                        hermano = hermano.getHermanoD();
                    }
                }
                if (bandera) {
                    if (nodo.getHIzquierdo() == null) {
                        nodo.setHIzquierdo(new NodoGen(s, null, null));
                    } else {
                        NodoGen aux = nodo.getHIzquierdo();
                        while (aux.getHermanoD() != null) {
                            aux = aux.getHermanoD();
                        }
                        aux.setHermanoD(new NodoGen(s, null, null));
                        respuesta = true;
                    }
                }
            }
            hijo = nodo.getHIzquierdo();
            while (hijo != null && !respuesta) {
                respuesta = insertarSobrinoAux(hijo, t, h, s);
                hijo = hijo.getHermanoD();
            }
        }
        return respuesta;
    }
}
