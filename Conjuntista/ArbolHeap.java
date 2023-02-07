package Conjuntista;

/**
 *
 * @author matir
 */
public class ArbolHeap {
    
    private static final int tam = 20;
    private Comparable [] heap;
    private int ultimo;

    public ArbolHeap() {
        // crea un árbol sin elementos.
        ultimo = 0;
        heap = new Comparable[tam];
    }
    
    public boolean insertar(Comparable elemento){
        // recibe un elemento y lo inserta en el árbol según el algoritmo que se explicará en la siguiente sección.
        //Si la operación termina con éxito devuelve verdadero y falso en caso contrario.
        //Nota: Los árboles heap aceptan elementos repetidos.
        boolean exito = true;
  
        if (this.ultimo+1 <= tam) {
            heap[ultimo+1] = elemento;
            this.ultimo++;
            hacerSubir(this.ultimo);
        } else {
            exito = false;
        }
        return exito;
    }
    
     private void hacerSubir(int posHijo) {
        boolean salir = false;
        int posPadre;
        Comparable aux = this.heap[posHijo];
        while (!salir) {
            posPadre = posHijo / 2;
            if (posPadre > 0) {

                // no agregarle esta condicion no cambia nada el metodo
                if (this.ultimo == 1) {
                    this.heap[1] = aux;

                }
                if (this.heap[posHijo].compareTo(this.heap[posPadre]) < 0) {

                    this.heap[posHijo] = this.heap[posPadre];
                    this.heap[posPadre] = aux;
                    posHijo = posPadre;
                } else {
                    //si el padre es menor que su hijo
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }
    
    public boolean eliminarCima (){
        // elimina el elemento de la raíz (o cima del montículo) según el algoritmo que se explicará en la
        //siguiente sección. Si la operación termina con éxito devuelve verdadero y falso si el árbol estaba vacío.
        boolean exito;
        if(this.ultimo==0){
            exito=false;
        }else{
            this.heap[1]=this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito=true;
        }
        return exito;
    }
    
    private void hacerBajar(int posicion){
        int hijoMenor;
        Comparable tmp=this.heap[posicion];
        boolean var=false;
        while(!var){
            hijoMenor=2*posicion;
            if(hijoMenor<=this.ultimo){
                if(hijoMenor<ultimo){
                    if((this.heap[hijoMenor+1].compareTo(this.heap[hijoMenor]))< 0){
                        hijoMenor++;
                    }
                }
                if(this.heap[hijoMenor].compareTo(tmp)<0){
                    this.heap[posicion]=this.heap[hijoMenor];
                    this.heap[hijoMenor]=tmp;
                    posicion=hijoMenor;
                }else{
                    var=true;
                }
            }else{
                var=true;
            }
        }
        
    }
    
    public Comparable recuperarCima(){
        // devuelve el elemento que está en la raíz (cima del montículo). Precondición: el árbol no está vacío
        //(si está vacío no se puede asegurar el funcionamiento de la operación).
        Comparable elem;
        elem=heap[1];
        return elem;
    }
    
    public boolean esVacio(){
        // devuelve falso si hay al menos un elemento cargado en la tabla y verdadero en caso contrario.
        boolean exito=false;
        if(ultimo==0){
            exito=true;
        }
        return exito;
    }
    
    public void vaciar(){
        for(int i=1;i<=ultimo;i++){
            heap[i]=null;
        }
        ultimo=0;
    }
    
    public ArbolHeap clone(){
        ArbolHeap clon=new ArbolHeap();
        for (int i=0; i<=this.ultimo; i++) {
                clon.heap[i]=this.heap[i];
        }
        return clon;
    }
    
    public String toString (){
        String cadena="";
        int i;
        for (i=1; i < ultimo; i++) {
            
                cadena+="padre: "+heap[i]+" HI:"+heap[2*i]+" HD:"+heap[(2*i)+1]+"\n"; 

        }
        
        return cadena;
    }
}

