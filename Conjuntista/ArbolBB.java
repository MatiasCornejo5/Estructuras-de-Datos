/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conjuntista;

import Lineales.Dinamicas.Lista;
import Lineales.Dinamicas.Pila;

/**
 *
 * @author matir
 */
public class ArbolBB {
    
    private NodoBB raiz;
    
    public ArbolBB() {
        //crea un arbol vacio
        this.raiz = null;
    }
    
    public boolean insertar(Comparable elemento) {
        /*Recibe un elemento y lo agrega en el arbol de manera ordenada.
        Si el elemento ya se encuntra en el arbol no se realiza la insercion 
        Devuelve true, si el elemento se agrega a la estructura y false en caso contrario*/
        boolean var = true;
        if (this.raiz == null) {
            this.raiz = new NodoBB(elemento, null, null);
        } else {
            var = insertarAux(this.raiz, elemento);
        }
        return var;
    }
    
    private boolean insertarAux(NodoBB nodo, Comparable elemento) {
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
                } else {
                    nodo.setIzquierdo(new NodoBB(elemento, null, null));
                }
            } else {
                //Cuando elemento es mayor que el elemento del nodo
                //Si tiene hijo dere baja, sino agrega el elemento como hijo der
                if (nodo.getDerecho() != null) {
                    var = insertarAux(nodo.getDerecho(), elemento);
                } else {
                    nodo.setDerecho(new NodoBB(elemento, null, null));
                }
            }
        }
        return var;
    }
    
    public boolean eliminar(Comparable buscado) {
        //Recibe un elemento y procede a eleminarlo del arbol
        //Si no encuentra el elemento no puede realizar la eliminacion
        //Devuelve true, si el elemento se elemina correctamente y false en caso contrario
        boolean exito = false;
        NodoBB aux = this.raiz;
        if (aux != null) {
            exito = eliminarAux(aux, buscado);
        }
        return exito;
    }
    
    private boolean eliminarAux(NodoBB nodo, Comparable elim) {
        boolean exito = false;
        NodoBB padreElim = null, nodoElim = null;
        //Nodo eliminar
        if (nodo.getElem().compareTo(elim) == 0) {
            nodoElim = nodo;
        } else {
            padreElim = obtenerPadre(nodo, elim);
            if (padreElim != null) {
                if (padreElim.getDerecho() != null && padreElim.getDerecho().getElem().compareTo(elim) == 0) {
                    nodoElim = padreElim.getDerecho();
                } else {
                    nodoElim = padreElim.getIzquierdo();
                }
            }
        }
        if (nodoElim != null) {
            if (nodoElim.getDerecho() == null && nodoElim.getIzquierdo() == null) {//caso 1
                exito = caso1(nodoElim, padreElim);
            } else {
                if (nodoElim.getDerecho() != null && nodoElim.getIzquierdo() == null) {//caso 2 d
                    exito = caso2d(nodoElim, padreElim);
                } else {
                    if (nodoElim.getDerecho() == null && nodoElim.getIzquierdo() != null) {//caso 2 i
                        exito = caso2i(nodoElim, padreElim);
                    } else {
                        NodoBB candidato = nodoElim.getDerecho();//caso 3
                        NodoBB padreCandidato = nodoElim;
                        exito = caso3(candidato, padreCandidato, nodoElim);
                    }
                }
            }
        }
        return exito;
    }
    
    private boolean caso1(NodoBB elem, NodoBB padre) {
        boolean exito = false;
        
        if (padre != null) {//Si es la raiz padre elim es null
            if (padre.getDerecho() == elem) {
                padre.setDerecho(null);
            } else {
                padre.setIzquierdo(null);
            }
        } else {
            this.raiz = null;
        }
        exito = true;
        
        return exito;
    }
    
    private boolean caso2d(NodoBB elem, NodoBB padre) {
        boolean exito;
        
        if (padre != null) {
            if (padre.getDerecho() == elem) {
                padre.setDerecho(elem.getDerecho());
            } else {
                padre.setIzquierdo(elem.getDerecho());
            }
        } else {
            this.raiz = elem.getDerecho();
        }
        exito = true;
        
        return exito;
    }
    
    private boolean caso2i(NodoBB elem, NodoBB padre) {
        boolean exito;
        if (padre != null) {
            if (padre.getDerecho() == elem) {
                padre.setDerecho(elem.getIzquierdo());
            } else {
                padre.setIzquierdo(elem.getIzquierdo());
            }
        } else {
            this.raiz = elem.getIzquierdo();
        }
        exito = true;
        return exito;
    }
    
    private boolean caso3(NodoBB candidato, NodoBB padreCandidato, NodoBB elem) {
        boolean exito;
        if (candidato.getIzquierdo() != null) {
            while (candidato.getIzquierdo().getIzquierdo() != null) {
                padreCandidato = candidato;
                candidato = candidato.getIzquierdo();
            }
            elem.setElem(candidato.getIzquierdo().getElem());
            padreCandidato = candidato;
            candidato = candidato.getIzquierdo();
        } else {
            elem.setElem(candidato.getElem());
        }
        if (candidato.getDerecho() == null) {
            if (padreCandidato.getDerecho() == candidato) {
                padreCandidato.setDerecho(null);
            } else {
                padreCandidato.setIzquierdo(null);
            }
        } else {
            if (padreCandidato.getDerecho() == candidato) {
                padreCandidato.setDerecho(candidato.getDerecho());
            } else {
                padreCandidato.setIzquierdo(candidato.getDerecho());
            }
        }
        exito = true;
        return exito;
    }
    
    private NodoBB obtenerPadre(NodoBB arbol, Comparable buscado) {
        NodoBB resultado = null;
        if ((arbol.getDerecho() != null && buscado.compareTo(arbol.getDerecho().getElem()) == 0) || (arbol.getIzquierdo() != null && buscado.compareTo(arbol.getIzquierdo().getElem()) == 0)) {
            resultado = arbol;
        } else {
            if ((buscado.compareTo(arbol.getElem())) < 0 && (arbol.getIzquierdo() != null)) {
                resultado = obtenerPadre(arbol.getIzquierdo(), buscado);
            } else {
                if ((buscado.compareTo(arbol.getElem()) > 0) && (arbol.getDerecho() != null)) {
                    resultado = obtenerPadre(arbol.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }
    
    public boolean pertenece(Comparable elemento) {
        //Devuelve true si el elemento ingresado por parametro se encuentra en el arbol y false en caso contrario
        boolean var = false;
        if (this.raiz != null) {
            var = perteneceAux(this.raiz, elemento);
        }
        return var;
    }
    
    private boolean perteneceAux(NodoBB nodo, Comparable elemento) {
        boolean var = true;
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
    
    private void listarAux(NodoBB nodo, Lista lista) {
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
    
    private Lista listarRangoAux(NodoBB nodo, Lista lista, Comparable min, Comparable max) {
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
    
    public Comparable minimoElem() {
        //Recorre la rama correspondiente y devuelve el elemento mas pequenio almacenado en el arbol
        Comparable valor;
        NodoBB aux = this.raiz;
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        valor = aux.getElem();
        return valor;
    }
    
    public Comparable maximoElem() {
        //Recorre la rama correspondiente y devuelve el elementos mas grande almacenado en el arbol
        Comparable valor = null;
        NodoBB aux = this.raiz;
        while (aux.getDerecho() != null) {
            aux = aux.getDerecho();
        }
        valor = aux.getElem();
        return valor;
    }

    //MISMO METODO QUE EL clone DE ARBOL BINARIO!!
    public ArbolBB clone() {
        //generea y devuelve un arbol binario que es equivalente al original 
        ArbolBB clon = new ArbolBB();
        clon.raiz = clonarAux(this.raiz);
        return clon;
    }
    
    private NodoBB clonarAux(NodoBB n) {
        NodoBB aux;
        if (n != null) {
            aux = new NodoBB(n.getElem(), clonarAux(n.getIzquierdo()), clonarAux(n.getDerecho()));
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
    
    private String toStringAux(NodoBB nodo) {
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

//------------------------------------------------------------------------------------------------------------
    //EJERCICIO SIMULACRO DE SEGUNDO PARCIAL
    //ELIMINAR MINIMO ELEMENTO DEL ARBOL
    public boolean eliminarMin() {
        boolean var;
        var = eliminarMinAux(this.raiz);
        return var;
    }
    
    private boolean eliminarMinAux(NodoBB nodo) {
        boolean var = true;
//Busca siempre por la rama izquierda el elemento mas chico del arbol
        if (nodo.getIzquierdo() != null) {
            if (nodo.getIzquierdo().getIzquierdo() != null) {
                eliminarMinAux(nodo.getIzquierdo());
            } else {
//En caso de que el nodo mas chico tenga un hijo derecho 
                if (nodo.getIzquierdo().getDerecho() != null) {
                    nodo.setIzquierdo(nodo.getIzquierdo().getDerecho());
                } else {
                    nodo.setIzquierdo(null);
                }
            }
        } else {
//En caso de que el arbol solo tenga un nodo y este sea la raiz
            if (nodo.getDerecho() != null) {
                this.raiz = nodo.getDerecho();
            } else {
                this.raiz = null;
            }
        }
        
        return var;
    }

    //CLONAR INVERTIDO
    public ArbolBB clonarInvertido(Comparable elem) {
        ArbolBB clon = new ArbolBB();
        NodoBB aux = obtenerPadre(this.raiz, elem);
        if (this.raiz != null && aux != null) {
            clon.raiz = new NodoBB(elem, null, null);
            clonarInvertidoAux(aux, clon.raiz);
        }
        
        return clon;
    }
    
    private void clonarInvertidoAux(NodoBB nodo, NodoBB clon) {
        //Recorre la rama izquierda del arbol clonadola en espejo en el arbol clon
        if (nodo.getIzquierdo() != null) {
            clon.setDerecho(new NodoBB(nodo.getIzquierdo().getElem(), null, null));
            clonarInvertidoAux(nodo.getIzquierdo(), clon.getDerecho());
        }
        //Recorre todos los nodos de la rama derecha
        if (nodo.getDerecho() != null) {
            clon.setIzquierdo(new NodoBB(nodo.getDerecho().getElem(), null, null));
            clonarInvertidoAux(nodo.getDerecho(), clon.getIzquierdo());
        }
    }

//EJERCICIO PARCIAL 2
    //DIFERENCIA DE CANDIDATOS
    public Comparable diferenciaCandidatos(Comparable elem) {
        Comparable dif = 0;
        NodoBB aux;
        aux = obtenerNodo(this.raiz, elem);
        if (aux != null) {
            if (aux.getIzquierdo() != null || aux.getDerecho() != null) {
                dif = diferenciaAux(aux);
            } else {
                dif = -2;
            }
        } else {
            dif = -1;
        }
        
        return dif;
    }
    
    private Comparable diferenciaAux(NodoBB nodo) {
        Comparable resultado = 0;
        Comparable auxI, auxD;
        
        if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
            auxI = buscarMaxIzq(nodo.getIzquierdo());
            auxD = buscarMinDer(nodo.getDerecho());
            resultado = (int) auxD - (int) auxI;
        }
        
        return resultado;
    }
    
    private Comparable buscarMaxIzq(NodoBB nodo) {
        Comparable resul;
        NodoBB aux = nodo;
        while (aux.getDerecho() != null) {
            aux = aux.getDerecho();
        }
        resul = aux.getElem();
        return resul;
    }
    
    private Comparable buscarMinDer(NodoBB nodo) {
        Comparable resul;
        NodoBB aux = nodo;
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        resul = aux.getElem();
        
        return resul;
    }
    
    public NodoBB obtenerNodo(NodoBB nodo, Comparable elem) {
        NodoBB n = null;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                n = nodo;
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    n = obtenerNodo(nodo.getIzquierdo(), elem);
                } else {
                    n = obtenerNodo(nodo.getDerecho(), elem);
                }
            }
        }
        return n;
    }

    //EJERCICIO PARCIAL 2. 2021
    public int sumarPosOrdenDesde(int dato, int z) {
        int resultado = 0;
        NodoBB nodo = obtenerNodo(this.raiz, dato);
        if (nodo != null) {
            resultado = sumarPosOrden(nodo, z, 0);
            if (resultado < z) {
                resultado = resultado * (-1);
            }
        }
        return resultado;
    }
    
    private int sumarPosOrden(NodoBB dato, int z, int suma) {
        int resultado = 0;
        if (dato != null) {
            suma = sumarPosOrden(dato.getIzquierdo(), z, suma);
            //suma += resultado;
            if (suma < z) {
                suma = sumarPosOrden(dato.getDerecho(), z, suma);
                //suma += resultado;
            }
            if (suma < z) {
                suma = suma + (int) dato.getElem();
                //suma+=resultado;
            }
        }
        return suma;
    }

    //Prueba de sumar en otro orden (inorden/preorden)
    public int sumarPreOrdenDesde(int dato, int z) {
        int resultado = 0;
        NodoBB nodo = obtenerNodo(this.raiz, dato);
        if (nodo != null) {
            resultado = sumarPreOrden(nodo, z, 0);
            if (resultado < z) {
                resultado = resultado * (-1);
            }
        }
        return resultado;
    }
    
    private int sumarPreOrden(NodoBB dato, int z, int suma) {
        if (dato != null) {
            if (suma < z) {
                suma = suma + (int) dato.getElem();
            }
            suma = sumarPreOrden(dato.getIzquierdo(), z, suma);
            if (suma < z) {
                suma = sumarPreOrden(dato.getDerecho(), z, suma);
            }
        }
        return suma;
    }
    
    public int sumarInOrdenDesde(int dato, int z) {
        int resultado = 0;
        NodoBB nodo = obtenerNodo(this.raiz, dato);
        if (nodo != null) {
            resultado = sumarInOrden(nodo, z, 0);
            if (resultado < z) {
                resultado = resultado * (-1);
            }
        }
        return resultado;
    }
    
    private int sumarInOrden(NodoBB dato, int z, int suma) {
        if (dato != null) {
            suma = sumarInOrden(dato.getIzquierdo(), z, suma);
            if (suma < z) {
                suma = suma + (int) dato.getElem();
            }
            if (suma < z) {
                suma = sumarInOrden(dato.getDerecho(), z, suma);
            }
        }
        return suma;
    }
    
    public boolean verifiarCamino(Pila pila) {
        boolean respuesta;
        Pila clon = pila.clone();
        return respuesta = verificarCaminoAux(this.raiz, clon);
    }
    
    private boolean verificarCaminoAux(NodoBB nodo, Pila pila) {
        boolean resp = false;
        if (nodo != null && !pila.esVacia()) {
            if (nodo.getElem().compareTo(pila.obtenerTope()) == 0) {
                resp = true;
                pila.desapilar();
            }
            if (!pila.esVacia()) {
                if ((nodo.getDerecho().getElem().equals(pila.obtenerTope())
                        || nodo.getIzquierdo().getElem().equals(pila.obtenerTope())))  {
                    if (nodo.getElem().compareTo(pila.obtenerTope()) > 0) {
                        resp = verificarCaminoAux(nodo.getIzquierdo(), pila);
                    } else {
                        resp = verificarCaminoAux(nodo.getDerecho(), pila);
                    }
                }else{
                    resp = false;
                }
                
            }
        }
        
        return resp;
    }
    
}
