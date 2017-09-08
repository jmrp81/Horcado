
package Horcado;

/**
 * <p>Juego del horcado</p>
 * @version 1.0
 * @author Juan Manuel Rodriguez Perez
 */
public class Record implements Comparable {
    //Atributos
    private int puntuacion;
    private String nombre;

    /**
     * Metodo constructor de la clase Record
     * @param puntuacion
     * @param nombre 
     */
    public Record(int puntuacion, String nombre) {
        this.puntuacion = puntuacion;
        this.nombre = nombre;
    }

    /**
     * Metodo obtener puntuacion
     * @return 
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Metodo dar valor puntuacion
     * @param puntuacion 
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Metodo obtener nombre
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo dar valor al nombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /**
     * Metrodo sobrescrito compareTo devuelve un entero segun la comparacion entre objetos
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Object o){
        Record record = (Record) o;
        if(this.getPuntuacion()>record.getPuntuacion()){
            return -1;
        }else if(this.getPuntuacion()==record.getPuntuacion()){
            if(this.getNombre().compareToIgnoreCase(record.getNombre())<0){
                return -1;
            }else if(this.getNombre().compareToIgnoreCase(record.getNombre())==0){
                return 0;
            }else{
                return 1;
            }
            
        }else{
            return 1;
        }
    }
    
}
