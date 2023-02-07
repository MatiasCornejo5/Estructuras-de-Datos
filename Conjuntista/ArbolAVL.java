/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conjuntista;

import Lineales.Dinamicas.Lista;

/**
 *
 * @author matir
 */
public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable elemento) {
        //Devuelve true si el elemento ingresado por parametro se encuentra en el arbol y false en caso contrario
        boolean var = false;
        if (this.raiz != null) {
            var = perteneceAux(this.raiz, elemento);
        }
        return var;
    }


    private boolean perteneceAux(NodoAVL nodo, Comparable elemento) {
        boolean var = false;
        if (elemento.compareTo(nodo.getElem()) == 0) {
            var = true;
        } else {
            if (elemento.compareTo(nodo.getElem()) < 0) {
                if (nodo.getIzquierdo() != null) {
                    var = perteneceAux(nodo.getIzquierdo(), elemento);
                }
            } else {
                if (nodo.getDerecho() != null) {
                    var = perteneceAux(nodo.getDerecho(), elemento);
                }
            }
        }
        return var;
    }

    public boolean insertar(Comparable elemento) {
        /*Recibe un elemento y lo agrega en el arbol de manera ordenada.
        Si el elemento ya se encuntra en el arbol no se realiza la insercion 
        Devuelve true, si el elemento se agrega a la estructura y false en caso contrario*/
        boolean var = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elemento, null, null);
        } else {
            var = insertarAux(this.raiz, elemento);
        }
        return var;
    }

    private boolean insertarAux(NodoAVL nodo, Comparable elemento) {
        boolean var = true;
        if (elemento.compareTo(nodo.getElem()) == 0) {
            //ERROR=Elemento repetido
            var = false;
        } else {
            if (elemento.compareTo(nodo.getElem()) < 0) {
                //Cuando elemento es menor que el elemento del nodo
                //Si tiene hijo izq baja, sino agrega el elemento como hijo izq
                if (nodo.getIzquierdo() != null) {
                    var = insertarAux(nodo.getIzquierdo(), elemento);
                    nodo.recalcularAltura();
                    if (balance(nodo) > 1 || balance(nodo) < -1) {
                        balancear(nodo, nodo.getIzquierdo());
                    }
                } else {
                    nodo.setIzquierdo(new NodoAVL(elemento, null, null));
                    nodo.recalcularAltura();
                }
            } else {
                //Cuando elemento es mayor que el elemento del nodo
                //Si tiene hijo dere baja, sino agrega el elemento como hijo der
                if (nodo.getDerecho() != null) {
                    var = insertarAux(nodo.getDerecho(), elemento);
                    nodo.recalcularAltura();
                    if (balance(nodo) > 1 || balance(nodo) < -1) {
                       balancear(nodo, nodo.getDerecho());
                    }
                } else {
                    nodo.setDerecho(new NodoAVL(elemento, null, null));
                    nodo.recalcularAltura();
                }
            }
        }
        return var;
    }

    public int balance(NodoAVL nodo) {
        int balance = -1, alturaIzq, alturaDer;

        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                alturaIzq = nodo.getIzquierdo().getAltura();
            } else {
                alturaIzq = -1;
            }
            if (nodo.getDerecho() != null) {
                alturaDer = nodo.getDerecho().getAltura();
            } else {
                alturaDer = -1;
            }
            balance = alturaIzq - alturaDer;//positivo inclinado hacia izq, negativo derecha
        }
        return balance;
    }

    public void balancear(NodoAVL nodoPadre, NodoAVL nodoHijo) {
        if (balance(nodoPadre) == 2) {
            if (balance(nodoHijo) == 1 || balance(nodoHijo) == 0) {
                nodoPadre = rotacionDerecha(nodoPadre);
                nodoPadre.recalcularAltura();
            } else if (balance(nodoHijo) == -1) {
                nodoPadre = rotacionIzquierdaDerecha(nodoPadre);
                nodoPadre.recalcularAltura();
            }
        } else if (balance(nodoPadre) == -2) {
            if (balance(nodoHijo) == -1 || balance(nodoHijo) == 0) {
                nodoPadre = rotacionIzquierda(nodoPadre);
                nodoPadre.recalcularAltura();
            } else if (balance(nodoHijo) == 1) {
                nodoPadre = rotacionDerechaIzquierda(nodoPadre);
                nodoPadre.recalcularAltura();
            }
        }
    }

    public NodoAVL rotacionDerecha(NodoAVL r) {
        NodoAVL h, temp;
        h = r.getIzquierdo();
        temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        //RECALCULAMOS LAS ALTURAS
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    public NodoAVL rotacionIzquierda(NodoAVL r) {
        NodoAVL h, temp;
        h = r.getDerecho();
        temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
        //RECALCULAMOS LAS ALTURAS
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    public NodoAVL rotacionIzquierdaDerecha(NodoAVL nodo) {
        NodoAVL auxiliar = rotacionIzquierda(nodo.getIzquierdo());
        nodo.setIzquierdo(auxiliar);
        NodoAVL h = rotacionDerecha(nodo);
        return h;
    }

    public NodoAVL rotacionDerechaIzquierda(NodoAVL nodo) {
        NodoAVL auxiliar = rotacionDerecha(nodo.getDerecho());
        nodo.setIzquierdo(auxiliar);
        NodoAVL h = rotacionIzquierda(nodo);
        return h;
    }

    public boolean esVacio() {
        //Devuelve false si hay al menos un elemento en el arbol, y true en caso contrario
        return this.raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Lista listar() {
        //Recorre el arbol completo y devuelve una lista ordenada con los elementos que se encuentran almacenados en el arbol
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarAux(NodoAVL nodo, Lista lista) {
        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarRango(Comparable minimo, Comparable maximo) {
        //Recorre una parte del arbol y devuelve una lista ordenada con los elementos que se encuentran entre [minimo,maximo]
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarRangoAux(this.raiz, lista, minimo, maximo);
        }
        return lista;
    }

    private Lista listarRangoAux(NodoAVL nodo, Lista lista, Comparable min, Comparable max) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null && min.compareTo(nodo.getElem()) <= 0) {
                listarRangoAux(nodo.getIzquierdo(), lista, min, max);
            }
            if (nodo.getElem().compareTo(min) >= 0 && nodo.getElem().compareTo(max) <= 0) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            if (nodo.getDerecho() != null && max.compareTo(nodo.getElem()) >= 0) {
                listarRangoAux(nodo.getDerecho(), lista, min, max);
            }
        }

        return lista;
    }

    //MISMO METODO QUE EL clone DE ARBOL BINARIO!!
    public ArbolAVL clone() {
        //generea y devuelve un arbol binario que es equivalente al original 
        ArbolAVL clon = new ArbolAVL();
        clon.raiz = clonarAux(this.raiz);
        return clon;
    }

    private NodoAVL clonarAux(NodoAVL n) {
        NodoAVL aux;
        if (n != null) {
            aux = new NodoAVL(n.getElem(), clonarAux(n.getIzquierdo()), clonarAux(n.getDerecho()));
        } else {
            aux = null;
        }
        return aux;
    }

    //MISMO METODO QUE EL toString DE ARBOL BINARIO!!
    public String toString() {
        //genera y devuelve una cadena de caracteres que indica cual es la raiz del arbol y quienes son ls hijos de cada nodo
        String cadena;
        cadena = toStringAux(this.raiz);
        return cadena;
    }

    private String toStringAux(NodoAVL nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena = "\t\t" + nodo.getElem();
            if (nodo.getIzquierdo() != null) {
                cadena = cadena + " HI: " + nodo.getIzquierdo().getElem() + " ";
            } else {
                cadena = cadena + " HI: - ";
            }
            if (nodo.getDerecho() != null) {
                cadena = cadena + "HD: " + nodo.getDerecho().getElem() + " \n";
            } else {
                cadena = cadena + "HD: - \n";
            }

            cadena = cadena + "" + toStringAux(nodo.getIzquierdo());
            cadena = cadena + "" + toStringAux(nodo.getDerecho());
        }

        return cadena;
    }

    private NodoAVL obtenerNodo(NodoAVL nodo, Comparable elem) {
        NodoAVL resul = null;

        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                resul = nodo;
            } else {
                resul = obtenerNodo(nodo.getIzquierdo(), elem);
                if (resul == null) {
                    resul = obtenerNodo(nodo.getDerecho(), elem);
                }
            }
        }
        return resul;
    }
}
