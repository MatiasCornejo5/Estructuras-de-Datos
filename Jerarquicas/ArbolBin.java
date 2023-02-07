package Jerarquicas;

import Lineales.Dinamicas.Cola;
import Lineales.Dinamicas.Lista;

/**
 *
 * @author Cornejo Matias FAI-1919
 * @author Petenatti Francisco FAI-2347
 */
public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        //Inserta elemNuevo como hijo del primer nodo encontrado en preorden
        //igual a elemPadre, como hijo izquierdo (I) o derecho (D), segun
        //lo indique el parametro lugar
        boolean exito = true;

        if (this.raiz == null) {
            //si el arbol esta vacio, ponemos el elem nuevo en la raiz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            //si no esta vacio, se busca al padre
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if (lugar == 'I' && nodoPadre.getIzquierdo() == null) {
                    //si el padre existe y no tiene hijo izquierdo se lo agrega
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else {
                    if (lugar == 'D' && nodoPadre.getDerecho() == null) {
                        //si el padre existe y no tiene hijo derecho se lo agrega
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                    } else {
                        //si el padre no existe o ya tiene ese hijo, da error
                        exito = false;
                    }
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        //metodo PRIVADO que busca un elemento y devuelve el nodo que 
        //lo contiene. Si no se encuentra buscado devuelve null
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElemento().equals(buscado)) {
                //si el buscado es n, lo devuelve
                resultado = n;
            } else {
                //no es el buscado: busca primero en el hijo izq
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                //si no lo encuentra en el hijo izq, busca en el hijo der
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }

            }
        }
        return resultado;
    }

    public boolean esVacio() {
        //Devuelve falso si hay al menos un elemento cargado en el arbol y verdadero en caso contrario
        return this.raiz == null;
    }

    public int altura() {
        //Devuelve la altura del arbol, es decir la longitud del camino mas largo desde la raiz 
        //hasta una hoja (NOTA: UN ARBOL VACIO TIENE ALTURA -1 Y UNA HOJA TIENE ALTURA 0)
        int aux;
        aux = obtenerAltura(this.raiz);

        return aux;
    }

    private int obtenerAltura(NodoArbol nodo) {
        //Metodo recursivo
        int alturaIzquierdo = 0, alturaDerecho = 0;//Devuelve 0 si es hoja

        if (nodo != null) {

            alturaIzquierdo += obtenerAltura(nodo.getIzquierdo()) + 1;

            alturaDerecho += obtenerAltura(nodo.getDerecho()) + 1;

            if (alturaIzquierdo >= alturaDerecho) {
                alturaDerecho = alturaIzquierdo;
            }
        } else {
            alturaDerecho = -1;  //devuelve - si el arbol es vacio
        }
        return alturaDerecho;
    }

    /*  public int nivel(Object elemento) {
        int aux = -1;
        if (this.raiz != null) {
            if (this.raiz.getElemento() == elemento) {
                aux = 0;
            } else {
                aux = obtenerNivel(this.raiz, elemento, aux);
            }
        }

        return aux;
    }

    private int obtenerNivel(NodoArbol nodo, Object elem, int nroNvl) {
        Lista listaAux = this.listarPreorden();
        int posicion = listaAux.localizar(elem);
        if (posicion > -1) {
            if ((nodo.getIzquierdo() != null && nodo.getIzquierdo().getElemento() == elem) || (nodo.getDerecho() != null && nodo.getDerecho().getElemento() == elem)) {
                nroNvl = 1;
            } else {
                if (nodo.getIzquierdo() != null) {
                    nroNvl = obtenerNivel(nodo.getIzquierdo(), elem, nroNvl);
                }
                if (nroNvl == 0) {
                    if (nodo.getDerecho() != null) {
                        nroNvl = obtenerNivel(nodo.getDerecho(), elem, nroNvl);
                        if (nroNvl != 0) {
                            nroNvl++;
                        }
                    }
                } else {
                    nroNvl++;
                }
            }

        } else {
            nroNvl = -1;
        }
        return nroNvl;
    }*/
    public int nivel(Object elemento) {
        /*
         Devuelve el nivel de un elemento en el árbol. Si el elemento no existe 
        en el árbol devuelve -1
         */
        int nivel = -1;
        //Lista listaAux = this.listarPreorden();
        //int posicion = listaAux.localizar(elemento);
        //if (posicion != -1) {
        nivel = nivelAux(this.raiz, elemento);
        //}
        return nivel;
    }

    private int nivelAux(NodoArbol nodo, Object elemento) {
        //Realiza la bsuqueda del nivel del elemento (parametro), retorna su nivel
        int nivel = -1;

        if (nodo != null) {
            if (nodo.getElemento() == elemento) {
                //Elemento encontrado
                nivel = 0;
            } else {
                //Recorre hijo izquierdo
                nivel = nivelAux(nodo.getIzquierdo(), elemento);
                if (nivel == -1) {
                    //Recorre hijo derecho
                    nivel = nivelAux(nodo.getDerecho(), elemento);
                }
                if (nivel != -1) {
                    nivel++;
                }
            }
        }
        return nivel;
    }

    public Object padre(Object elemento) {
        //Dado un elemento devuelve el valor almacenado en su nodo Padre (busca la primera aparicion del elemento)
        Object var = null;
        if (this.raiz != null) {
            NodoArbol aux = buscaPadre(this.raiz, elemento);
            if (aux != null) {
                var = aux.getElemento();
            }
        }
        return var;
    }

    private NodoArbol buscaPadre(NodoArbol nodo, Object elem) {
        NodoArbol resultado = null;
        if (nodo != null && !nodo.getElemento().equals(elem)) {
            if ((nodo.getIzquierdo() != null && (nodo.getIzquierdo().getElemento() == elem)) || (nodo.getDerecho() != null && nodo.getDerecho().getElemento() == elem)) {
                resultado = nodo;
            } else {
                if (nodo.getIzquierdo() != null) {
                    resultado = buscaPadre(nodo.getIzquierdo(), elem);
                    if (resultado == null && nodo.getDerecho() != null) {
                        resultado = buscaPadre(nodo.getDerecho(), elem);
                    }
                }
            }
        }

        return resultado;
    }

    public Lista listarPreorden() {
        //devuelve una lista con los elementos del arbol binario en el recorrido preorden
        Lista listaAux = new Lista();
        preordenAux(this.raiz, listaAux);
        return listaAux;
    }

    private void preordenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            //carga el elemento en la lista vacia, actualizando la longitud
            lista.insertar(nodo.getElemento(), lista.longitud() + 1);
            preordenAux(nodo.getIzquierdo(), lista);
            preordenAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarInorden() {
        //devuelve una lista con los elementos del arbol binario en el recorrido inorden
        Lista listaAux = new Lista();
        inordenAux(this.raiz, listaAux);
        return listaAux;
    }

    private void inordenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            inordenAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElemento(), lista.longitud() + 1);
            inordenAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarPosorden() {
        //devuelve una lista con los elementos del arbol binario en el recorrido posorden
        Lista listaAux = new Lista();
        posordenAux(this.raiz, listaAux);
        return listaAux;
    }

    private void posordenAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            posordenAux(nodo.getIzquierdo(), lista);
            posordenAux(nodo.getDerecho(), lista);
            lista.insertar(nodo.getElemento(), lista.longitud() + 1);
        }
    }

    public void vaciar() {
        //quita todos los elementos de la estructura.
        this.raiz = null;
    }

    public Lista listarPorNiveles() {
        //devuelve una lista con los elementos del arbol binario en recorrido por niveles
        Cola cola = new Cola();
        Lista lista = new Lista();
        if (this.raiz != null) {
            cola.poner(this.raiz);
            NodoArbol nodo;
            while (!cola.esVacia()) {
                nodo = (NodoArbol) cola.obtenerFrente();
                cola.sacar();
                lista.insertar(nodo.getElemento(), lista.longitud() + 1);
                if (nodo.getIzquierdo() != null) {
                    cola.poner(nodo.getIzquierdo());
                }
                if (nodo.getDerecho() != null) {
                    cola.poner(nodo.getDerecho());
                }
            }
        }

        return lista;
    }

    public ArbolBin clone() {
        //generea y devuelve un arbol binario que es equivalente al original 
        ArbolBin clon = new ArbolBin();
        clon.raiz = clonarAux(this.raiz);
        return clon;
    }

    private NodoArbol clonarAux(NodoArbol n) {
        NodoArbol aux;
        if (n != null) {
            aux = new NodoArbol(n.getElemento(), clonarAux(n.getIzquierdo()), clonarAux(n.getDerecho()));
        } else {
            aux = null;
        }
        return aux;
    }

    public String toString() {
        //genera y devuelve una cadena de caracteres que indica cual es la raiz del arbol y quienes son ls hijos de cada nodo
        String cadena;
        cadena = toStringAux(this.raiz);
        return cadena;
    }

    private String toStringAux(NodoArbol nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena = "" + nodo.getElemento();
            if (nodo.getIzquierdo() != null) {
                cadena = cadena + " HI: " + nodo.getIzquierdo().getElemento() + " ";
            } else {
                cadena = cadena + " HI: - ";
            }
            if (nodo.getDerecho() != null) {
                cadena = cadena + "HD: " + nodo.getDerecho().getElemento() + " \n";
            } else {
                cadena = cadena + "HD: - \n";
            }

            cadena = cadena + "" + toStringAux(nodo.getIzquierdo());
            cadena = cadena + "" + toStringAux(nodo.getDerecho());
        }

        return cadena;
    }

    public boolean equals(ArbolBin otro) {
        boolean resp;
        resp = auxEquals(this.raiz, otro.raiz);
        return resp;
    }

    private boolean auxEquals(NodoArbol nodo, NodoArbol otro) {

        boolean resp = false;

        if (nodo != null && otro != null) {

            if (nodo.getElemento().equals(otro.getElemento())) {

                if (nodo.getIzquierdo() != null && otro.getIzquierdo() != null) {
                    // compara hijos izquierdos
                    resp = auxEquals(nodo.getIzquierdo(), otro.getIzquierdo());

                    if (resp) {
                        if (nodo.getDerecho() != null && otro.getDerecho() != null) {
                            // compara hijos derechos
                            resp = auxEquals(nodo.getDerecho(), otro.getDerecho());
                        } else {
                            if (nodo.getDerecho() == null && otro.getDerecho() == null) {
                                resp = true;
                            }
                        }
                    }
                } else {
                    if (nodo.getIzquierdo() == null && otro.getIzquierdo() == null) {
                        resp = true;
                    }
                }
            }
        }
        return resp;
    }

    public Lista frontera() {
        Lista frontera = new Lista();
        fronteraAux(this.raiz, frontera);
        return frontera;
    }

    private void fronteraAux(NodoArbol nodo, Lista lista) {
        if (nodo != null) {
            if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {
                lista.insertar(nodo.getElemento(), lista.longitud() + 1);
            }
            fronteraAux(nodo.getIzquierdo(), lista);
            fronteraAux(nodo.getDerecho(), lista);
        }

    }

    /*e) Implementar la operación boolean verificarPatron(Lista patron), que recibe por parámetro una lista patron
    y determine si coincide exactamente con al menos un camino del árbol que comience en la raíz y termine en
    una hoja. El método debe ser eficiente, es decir, recorrer el árbol lo estrictamente necesario*/
    public boolean verificarPatron(Lista patron) {
        boolean respuesta;
        Lista patron1 = patron.clone();
        respuesta = verificarPatronAux(patron1, this.raiz);
        return respuesta;
    }

    private boolean verificarPatronAux(Lista patron, NodoArbol nodo) {
        boolean resp = true;
        if (patron.longitud() != 0 && resp) {
            if (nodo != null && nodo.getElemento().equals(patron.recuperar(1))) {
                patron.eliminar(1);
                resp = verificarPatronAux(patron, nodo.getIzquierdo());
                if (!resp) {
                    resp = verificarPatronAux(patron, nodo.getDerecho());
                }
            } else {
                resp = false;
            }
        }
        return resp;
    }

    /*) Implementar la operación clonarInvertido() que devuelve un nuevo árbol, que es una copia del árbol original
    (this) pero donde los hijos están cambiados de lugar. Atención: el método devuelve un nuevo árbol, sin
    modificar el árbol original*/
    public ArbolBin clonarInvertido() {
        ArbolBin clon = new ArbolBin();
        clon.raiz = new NodoArbol(this.raiz.getElemento(), null, null);
        clonarInvertidoAux(this.raiz, clon.raiz);
        return clon;
    }

    private void clonarInvertidoAux(NodoArbol nodoOriginal, NodoArbol nodoCopia) {
        if (nodoOriginal.getIzquierdo() != null) {
            nodoCopia.setDerecho(new NodoArbol(nodoOriginal.getIzquierdo().getElemento(), null, null));
            clonarInvertidoAux(nodoOriginal.getIzquierdo(), nodoCopia.getDerecho());
        }
        if (nodoOriginal.getDerecho() != null) {
            nodoCopia.setIzquierdo(new NodoArbol(nodoOriginal.getDerecho().getElemento(), null, null));
            clonarInvertidoAux(nodoOriginal.getDerecho(), nodoCopia.getIzquierdo());
        }
    }
    
    /*Agregar en la clase del TDA que implementa el árbol binario, 
    la operación cambiarHijos(Object izq, Object p, Object der) que recibe 
    3 elementos del mismo tipo que los cargados en el árbol y debe modificar el árbol de la siguiente manera: 
    Si p (padre) está en el árbol, izq quedará como su hijo izquierdo y der como su hijo derecho. 
    Si el padre no tiene hijo de ese lado, debe agregar un nodo para ponerlo y, si ya lo tiene, debe modificar el elemento de dicho nodo.*/
    
    public void cambiarHijos(Object izq, Object p, Object der){
        NodoArbol auxiliar = this.obtenerNodo(raiz, p);
        
        if (auxiliar != null) {
            if (auxiliar.getIzquierdo() != null) {
                auxiliar.getIzquierdo().setElemento(izq);
            }else{
                auxiliar.setIzquierdo(new NodoArbol(izq,null,null));
            }
            if (auxiliar.getDerecho() != null) {
                auxiliar.getDerecho().setElemento(der);
            }else{
                auxiliar.setDerecho(new NodoArbol(der,null,null));
            }
        }
        
    }
    
   
}
