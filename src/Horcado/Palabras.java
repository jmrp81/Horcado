
package Horcado;

/**
 * <p>Juego del horcado</p>
 * @version 1.0
 * @author Juan Manuel Rodriguez Perez
 */
public class Palabras implements Comparable<Palabras>{
    //Atributos
    private static int contadorClave=0;
    private int clave;
    private String palabra;

    /**
     * Cosntructor de la clase Palabras
     * @param palabra 
     */
    public Palabras(String palabra) {
        this.palabra = palabra;
        clave=contadorClave;
        contadorClave++;
    }

    /**
     * Metodo devuelve la clave
     * @return 
     */
    public int getClave() {
        return clave;
    }

    /**
     * Metodo devuelve la palabra
     * @return 
     */
    public String getPalabra() {
        return palabra;
    }
    
    
    /**
     * Metodo compareTo para ir comparando las palabras
     * @param pal
     * @return 
     */
    @Override
    public int compareTo(Palabras pal) {
        if(clave<pal.getClave()){
            return -1;
        }else if(clave==pal.getClave()){
            return 0;
        }else{
            return 1;
        }
    }
   
}
