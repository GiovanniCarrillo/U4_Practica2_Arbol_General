/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yova_
 */
public class ArbolGeneral {
    NodoGeneral raiz;
    
    public ArbolGeneral(){
        raiz = null;
    }
    
    public boolean insertar(char dato, String path){//F , /A/B/E
        if(raiz==null){
            raiz = new NodoGeneral(dato);
            return true;
        }
        
        if(path.isEmpty()) return false;
        
        NodoGeneral padre = buscarNodo(path);
        if(padre == null) return false;
        //Revisar si existe un hijo con la misma letra que se desea insertar
        NodoGeneral hijoYaExiste = buscarNodo(path+"/"+dato);
        //Si existe el hijo entonces return false;
        if(hijoYaExiste!= null) return false;
        NodoGeneral nuevo = new NodoGeneral(dato);
        return padre.enlazar(nuevo);
    }
    /*
    protected NodoGeneral buscarNodo(String path) {
       path = path.substring(1);//Le quitamos el primer "/" a el path. A/B/E
       String vector[] = path.split("/"); //    A/B/E ---> [A][B][E]
       
   
       if(vector[0].charAt(0)== raiz.dato){  //SI EL PRIMER DATO DEL VECTOR SEA = RAIZ.
           if(vector.length==1) return raiz; //SI EL VECTOR TIENE SOLO 1 DATO SE RETORNA ESE DATO (RAIZ).
           NodoGeneral padre = raiz; //SE CREA UN VECTOR NUEVO LLAMADO PADRE CON EL VALOR DE LA RAIZ
           
            for(int i = 1; i<vector.length; i++){ //[B][E] 
                padre = padre.obtenerHijo(vector[i].charAt(0));
                if(padre == null) return null;
            }
           return padre;
       }
       
       return null;// no coincidió celdilla 0 con raiz;
        
    }*/
    private NodoGeneral buscarNodo(NodoGeneral padre, String[] vector, int x){
        if(x<vector.length){      
            padre = padre.obtenerHijo(vector[x].charAt(0));
            if(padre == null) return null;
            x=x+1;
            padre = buscarNodo(padre, vector, x);
        }
        return padre;
    }
    
    protected NodoGeneral buscarNodo(String path){
        path = path.substring(1);
        String vector[]= path.split("/");
        for(int i =0; i<vector.length; i++){
            vector[i] = vector[i].toUpperCase();
        }
        int x = 1;
        if(vector[0].charAt(0) == raiz.dato){
            if(vector.length==1)return raiz;
            NodoGeneral padre =raiz;
            return buscarNodo(padre, vector, x);
        }
        return null;
    }
    
    public boolean eliminar(String path){
        if(raiz==null)return false;
        NodoGeneral hijo=buscarNodo(path);
        if(hijo==null)return false;
        if(!hijo.esHoja())return false;
        if(hijo==raiz){raiz=null;return true;}

        String pathPadre=obtenerPathPadre(path);
        NodoGeneral padre=buscarNodo(pathPadre);
        return padre.desenlazar(hijo);
    }

    private String obtenerPathPadre(String path) {
        int posicionANTESUltimaDiagonal=path.lastIndexOf("/")-1;
        return path.substring(0,posicionANTESUltimaDiagonal);

    }
}
