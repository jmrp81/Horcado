
package Horcado;

/**
 * <p>Juego del horcado</p>
 * @version 1.0
 * @author Juan Manuel Rodriguez Perez
 */

import java.util.TreeSet;
import java.util.Iterator;
import java.io.*;
import javax.swing.JOptionPane;
import sun.audio.*;
import java.awt.event.KeyEvent;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

import javax.help.*;
import java.net.*;

public class AplicacionAhorcado extends javax.swing.JFrame {
    //Atributos
    private Fondo f;
    private TreeSet<Palabras> listaPalabras;
    private TreeSet<Record> listaRecords;
    private Iterator elemento;
    private InputMap map;
    private String trozos[] = null;
    private String listadoPremiados="";
    private String listaPremiadosGuardar="";
    private String jugador;
    private String palabraElegida;
    private String nombreRecord;
    private int puntuacionMax=-1;
    private int puntuacionMin=-1;
    private int puntRecord;
    private int clavesSelecionadas[];
    private int contadorErrores=0;
    private int contadorAciertos=0;
    private int puntuacion=0;
    private int finPalabras=0;
    private char[] contenidoPalabra;
    private char[] contenidoPalabraMostrado;
    private HelpBroker hb;

    
    /**
     * Metodo de la aplicacionAhorcado
     */
    public AplicacionAhorcado() {
        listaPalabras=new TreeSet<Palabras>(); 
        listaRecords=new TreeSet<Record>();
        cargarPalabras();
        cargarRecors();
        obtenerPuntuacionMin();
        initComponents();
        cargaAyuda();
        map =new InputMap();
        incluirBotonesMap();
        setLocationRelativeTo(null);
        f = new Fondo();
        f.setSize(this.getSize());
        this.add(f);
        posicionInicial();
        deshabilitarBotones();

    }

    /**
     * Metodo para añadir botones al map para accionarlos con el intro
     */
    private void incluirBotonesMap(){
    map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
    map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");   
    botonA.setInputMap(0, map);
    botonB.setInputMap(0, map);
    botonC.setInputMap(0, map);
    botonD.setInputMap(0, map);
    botonE.setInputMap(0, map);
    botonF.setInputMap(0, map);
    botonG.setInputMap(0, map);
    botonH.setInputMap(0, map);
    botonI.setInputMap(0, map);
    botonJ.setInputMap(0, map);
    botonK.setInputMap(0, map);
    botonL.setInputMap(0, map);
    botonM.setInputMap(0, map);
    botonN.setInputMap(0, map);
    botonO.setInputMap(0, map);
    botonP.setInputMap(0, map);
    botonQ.setInputMap(0, map);
    botonR.setInputMap(0, map);
    botonS.setInputMap(0, map);
    botonT.setInputMap(0, map);
    botonU.setInputMap(0, map);
    botonV.setInputMap(0, map);
    botonW.setInputMap(0, map);
    botonX.setInputMap(0, map);
    botonY.setInputMap(0, map);
    botonZ.setInputMap(0, map);
    botonÑ.setInputMap(0, map);          
    }
        
    /**
     * Metodo para deshabilitar opciones
     */
    private void posicionInicial(){
        labelMaximaPuntuacionLograda.setText(""+obtenerPuntuacionMax());
        labelContieneErrores.setText("Ninguno");
        tamañoPalabra.setText("Ninguna");
        textPalabra.setText("");
        labelImagenAhorcado.setIcon(null);
        labelNombreJugador.setText("Ninguno");
    }
     
    /**
     * Metodo devuelve la puntuacion maxima
     * @return 
     */
    private int obtenerPuntuacionMax(){
        elemento = listaRecords.iterator();
        while(elemento.hasNext()){
            Record rec=(Record) elemento.next();
            if(rec.getPuntuacion()>puntuacionMax){
                puntuacionMax=rec.getPuntuacion();
            }
        }
        return puntuacionMax;
    }
    
    private int obtenerPuntuacionMin(){
         elemento = listaRecords.iterator();
        while(elemento.hasNext()){
            Record rec=(Record) elemento.next();
            if(puntuacionMin==-1){
                puntuacionMin=rec.getPuntuacion();
            }else if(rec.getPuntuacion()<puntuacionMin){
                puntuacionMin=rec.getPuntuacion();
            }
        }
        return puntuacionMin;       
    }
    /**
     * Metodo para deshabilitar botones del abecedario
     */
    private void deshabilitarBotones(){
    botonA.setEnabled(false);
    botonB.setEnabled(false);
    botonC.setEnabled(false);
    botonD.setEnabled(false);
    botonE.setEnabled(false);
    botonF.setEnabled(false);
    botonG.setEnabled(false);
    botonH.setEnabled(false);
    botonI.setEnabled(false);
    botonJ.setEnabled(false);
    botonK.setEnabled(false);
    botonL.setEnabled(false);
    botonM.setEnabled(false);
    botonN.setEnabled(false);
    botonO.setEnabled(false);
    botonP.setEnabled(false);
    botonQ.setEnabled(false);
    botonR.setEnabled(false);
    botonS.setEnabled(false);
    botonT.setEnabled(false);
    botonU.setEnabled(false);
    botonV.setEnabled(false);
    botonW.setEnabled(false);
    botonX.setEnabled(false);
    botonY.setEnabled(false);
    botonZ.setEnabled(false);
    botonÑ.setEnabled(false);       
    }
    
        
    /**
     * Metodo para leer de un archivo las palabras y almacenarlas en la coleccion
     */
    private void cargarPalabras(){ 
        String linea;
        try {
        BufferedReader fichero = new BufferedReader(new FileReader("palabras.txt"));
        while (fichero.ready()) {
        linea = fichero.readLine();
        trozos = linea.split("@");
        }
        }
        catch (FileNotFoundException fnfe){
         JOptionPane.showMessageDialog(this, "Archivo no encontrado"+fnfe, "Error lectura archivo palabras", 0);
        }
        catch(IOException ioe){
         JOptionPane.showMessageDialog(this, "Error de entrada/salida" + ioe, "Error lectura archivo palabras" , 0);
        }
        
        for(int i=0;i<trozos.length;i++){
            if(trozos[i]!=null){
            Palabras pal=new Palabras(trozos[i]);
            listaPalabras.add(pal);
            }
        }
        clavesSelecionadas = new int[listaPalabras.size()];
        for(int i=0;i<clavesSelecionadas.length;i++){
            clavesSelecionadas[i]=-1;
        }
    }
    
     /**
     * Metodo para leer de un archivo los records
     */
    private void cargarRecors(){ 
        String linea;
        String lineaB;
        String[] trozosB;
        try {
        BufferedReader fichero = new BufferedReader(new FileReader("records.txt"));
        while (fichero.ready()) {
            linea = fichero.readLine();
            String[] records = linea.split("@@");
            for(int i=0;i<records.length;i++){
                lineaB=records[i];
                trozosB = lineaB.split("@");   
                for(int j=0;j<trozosB.length-1;j++){
                    nombreRecord=trozosB[j];
                    puntRecord=Integer.parseInt(trozosB[j+1]);
                    Record rec = new Record(puntRecord,nombreRecord);
                    listadoPremiados+=nombreRecord+" "+puntRecord+"\n";
                    listaRecords.add(rec);
                    break;
                }
            }
        }
        }
        catch (FileNotFoundException fnfe){
        JOptionPane.showMessageDialog(this, "Archivo no encontrado", "Error lectura archivo records", 0);
        }
        catch(IOException ioe){
        JOptionPane.showMessageDialog(this, "Error de entrada/salida", "Error lectura archivo records" , 0);
        }
        
        
    }
    
     /**
     * Metodo para guardar en un archivo los records
     */
    private void guardarRecors(){ 
    String nombreFich = "records.txt";
    elemento = listaRecords.iterator();
    while(elemento.hasNext()){
        Record rec = (Record) elemento.next();
        listaPremiadosGuardar+=rec.getNombre()+"@"+rec.getPuntuacion()+"@@";
    }
    
    try{
      BufferedWriter fichero = new BufferedWriter(new FileWriter(nombreFich));
      fichero.write(listaPremiadosGuardar);
      fichero.newLine();
      fichero.close();
    }

    catch(FileNotFoundException fnfe){
        JOptionPane.showMessageDialog(this, "El archivo no se ha podido guardar.Clase GestorClinica/escribeFichero", "Error guardando records", 0);
    } catch(IOException ioe){
        JOptionPane.showMessageDialog(this, "Error de entrada/salida.Clase GestorClinica/escribeFichero", "Error guardando records", 0);
    }    
    }
    
    /**
     * Metodo para reproducir sonido
     */
    private void reproducirSonido(String ruta){
        try{
        InputStream in=new FileInputStream(ruta);
        AudioStream audio = new AudioStream(in);
        AudioPlayer.player.start(audio);
        }catch(Exception exc){
            System.out.println("Error lectura sonido");
        }
    }

    
    /**
     * Metodo para habilitar botones del abecedario
     */
    private void habilitarBotones(){
    botonA.setEnabled(true);
    botonB.setEnabled(true);
    botonC.setEnabled(true);
    botonD.setEnabled(true);
    botonE.setEnabled(true);
    botonF.setEnabled(true);
    botonG.setEnabled(true);
    botonH.setEnabled(true);
    botonI.setEnabled(true);
    botonJ.setEnabled(true);
    botonK.setEnabled(true);
    botonL.setEnabled(true);
    botonM.setEnabled(true);
    botonN.setEnabled(true);
    botonO.setEnabled(true);
    botonP.setEnabled(true);
    botonQ.setEnabled(true);
    botonR.setEnabled(true);
    botonS.setEnabled(true);
    botonT.setEnabled(true);
    botonU.setEnabled(true);
    botonV.setEnabled(true);
    botonW.setEnabled(true);
    botonX.setEnabled(true);
    botonY.setEnabled(true);
    botonZ.setEnabled(true);
    botonÑ.setEnabled(true);        
    } 
 
     /**
     * Metodo para resetear botones del abecedario
     */
    private void resetearBotones(){
    botonA.setSelected(false);
    botonB.setSelected(false);
    botonC.setSelected(false);
    botonD.setSelected(false);
    botonE.setSelected(false);
    botonF.setSelected(false);
    botonG.setSelected(false);
    botonH.setSelected(false);
    botonI.setSelected(false);
    botonJ.setSelected(false);
    botonK.setSelected(false);
    botonL.setSelected(false);
    botonM.setSelected(false);
    botonN.setSelected(false);
    botonO.setSelected(false);
    botonP.setSelected(false);
    botonQ.setSelected(false);
    botonR.setSelected(false);
    botonS.setSelected(false);
    botonT.setSelected(false);
    botonU.setSelected(false);
    botonV.setSelected(false);
    botonW.setSelected(false);
    botonX.setSelected(false);
    botonY.setSelected(false);
    botonZ.setSelected(false);
    botonÑ.setSelected(false);        
    } 
    
    
    /**
     * Metodo dar valor al jugador
     * @param jugador 
     */
        public void setJugador(String jugador) {
        this.jugador = jugador;
       
    }
    
        
    /**
     * Metodo comprobar si una letra esta en la palabra
     * @param letra 
     */
    private void comprobarLetra(char letra){
      boolean letraEncontrada=false;
      String palabra="";
      for(int i=0;i<contenidoPalabra.length;i++){
          if(contenidoPalabra[i]==letra){
              letraEncontrada=true;
              contenidoPalabraMostrado[i]=letra;
              contadorAciertos++;
              puntuacion+=25;
              labelPuntuacionLograda.setText(""+puntuacion);
          }
      }  
      
      for(int j=0;j<contenidoPalabraMostrado.length;j++){
          palabra+=contenidoPalabraMostrado[j];
      }
      
      textPalabra.setText(palabra);
      
      if(!letraEncontrada){
          contadorErrores++;
          labelContieneErrores.setText(""+contadorErrores);
          puntuacion-=10;
          labelPuntuacionLograda.setText(""+puntuacion);
          reproducirSonido("WAV/errorLetra.wav");
          mostrarError();
      }
      
      if(contadorErrores==5){
         reproducirSonido("WAV/otros.wav");
         JOptionPane.showMessageDialog(this, "Juego finalizado por errores, la palabra correcta era: "+palabraElegida, "Errores completados", 2);
         comprobarMaxPuntuacion();
         posicionInicial();
         labelPuntuacionLograda.setText("ninguna");
         resetearBotones();
         deshabilitarBotones();
         opcionNuevo.setEnabled(true);
         contadorErrores=0;
         contadorAciertos=0;
      }
      
      if(contadorAciertos>=palabraElegida.length()){
         reproducirSonido("WAV/aciertoPalabra.wav");
         JOptionPane.showMessageDialog(this, "Felicidades Palabra completada", "Palabra completada", 1);
         resetearBotones();
         labelMaximaPuntuacionLograda.setText(""+obtenerPuntuacionMax());
         labelContieneErrores.setText("Ninguno");
         tamañoPalabra.setText("Ninguna");
         textPalabra.setText("");
         labelImagenAhorcado.setIcon(null);
         contadorErrores=0;
         contadorAciertos=0;
         nuevaPalabra();
         mostrarPalabra();
      }
    }
    
    /**
     * Metodo para mostrar puntuacion lograda y comprobar con la maximaPuntuacion
     */
    private void comprobarMaxPuntuacion(){
        if(puntuacion<obtenerPuntuacionMin()){
            puntuacionMin=puntuacion;
            JOptionPane.showMessageDialog(this, "Tu puntuacion es: "+puntuacion,"Partida Finalizada" , 1); 
        }else if(puntuacion>=obtenerPuntuacionMax()){
            puntuacionMax=puntuacion;
            labelMaximaPuntuacionLograda.setText(jugador+" "+puntuacionMax);
            JOptionPane.showMessageDialog(this, "Lograda maxima puntuacion: "+puntuacionMax,"Partida Finalizada" , 1); 
        }
        Record rec=new Record(puntuacion,jugador);
        puntuacion=0;
        listaRecords.add(rec);
        guardarRecors();
    }
    
    /**
     * Metodo para mostrar dibujo del ahorcado segun errores
     */
    private void mostrarError(){
        try{
        switch (contadorErrores) {
            case 0:              
                break;
            case 1: 
                labelImagenAhorcado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Horcado/Base.gif")));
                labelContieneErrores.setText("1");
                break;
            case 2:
                labelImagenAhorcado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Horcado/medioTronco.gif")));
                labelContieneErrores.setText("2");
                break;
            case 3: 
                 labelImagenAhorcado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Horcado/troncoEntero.gif")));
                labelContieneErrores.setText("3");
                break;
            case 4:  
                labelImagenAhorcado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Horcado/soga.gif")));
                labelContieneErrores.setText("4");
                break;
            case 5:  
                labelImagenAhorcado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Horcado/Ahorcado.gif")));
                labelContieneErrores.setText("5");
                break;
        }
        }catch(Exception exc){
            System.out.println("Error mostrando imagenes ahorcado");
        }
    }
    
    /**
     * Metodo para seleccionar una nueva palabra obteniendo aleatoriamente la clave
     */
    private void nuevaPalabra(){
        if(finPalabras<listaPalabras.size()){
        int clavePalabra = (int) Math.floor(Math.random()*listaPalabras.size()); 
        if(!encontrada(clavePalabra)){
            for(int i=0;i<clavesSelecionadas.length;i++){
                if(clavesSelecionadas[i]==-1){
                   clavesSelecionadas[i]=clavePalabra;
                   break;
                }
            }
            elemento=listaPalabras.iterator();
            while(elemento.hasNext()){
                Palabras pal=(Palabras) elemento.next();
                if(pal.getClave()==clavePalabra){
                    palabraElegida=pal.getPalabra();
                    finPalabras++;
                }
            }
        }else{
            nuevaPalabra();
        } 
        }else{
         reproducirSonido("WAV/otros.wav");
         JOptionPane.showMessageDialog(this, "Juego finalizado, ha completado todas las palabras.Tu puntuacion: "+puntuacion, "Fin de Juego", 2);
         comprobarMaxPuntuacion();
         posicionInicial();
         labelPuntuacionLograda.setText("ninguna");
         resetearBotones();
         deshabilitarBotones();
         opcionNuevo.setEnabled(false);
         contadorErrores=0;
         contadorAciertos=0;
        }
    }
    
    /**
     * Metodo para mostrar inicialmente los huecos de la palabra y su longitud
     */
    private void mostrarPalabra(){
        contenidoPalabra=new char[palabraElegida.length()];
        contenidoPalabraMostrado=new char[contenidoPalabra.length];
        String palabra="";
        for(int i=0;i<contenidoPalabra.length;i++){
            contenidoPalabra[i]=palabraElegida.charAt(i);
        }
        for(int j=0;j<contenidoPalabraMostrado.length;j++){
            contenidoPalabraMostrado[j]='-';
            palabra+='-';
        }
        tamañoPalabra.setText(""+palabraElegida.length());
        textPalabra.setText(palabra);
    }
    
    /**
     * Metodo buscar claves en el vector de claves
     * @return 
     */
    private boolean encontrada(int clav){
        boolean encontrado=false;
        for(int i=0;i<clavesSelecionadas.length;i++){
            if(clavesSelecionadas[i]!=-1){
                if(clavesSelecionadas[i]==clav){
                    encontrado=true;
                }
            } 
        }
        return encontrado;
    }    
    
    /**
     * Metodo para cambiar tipo de letra y color de fondo de diferentes componentes
     * segun el fondo de aplicacion que se elija
     */
    private void cambioFormatoElementos(){
        panelSuperior.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Palabra Actual del Juego:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        panelInferior.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Teclado del Juego:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        panelInformativo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de la partida:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));
        labelContieneErrores.setOpaque(false);
        labelJugadorActual.setOpaque(false);
        labelMaximaPuntuacion.setOpaque(false);
        labelMaximaPuntuacionLograda.setOpaque(false);
        labelNombreJugador.setOpaque(false);
        labelNumErrores.setOpaque(false);
        labelPuntuacion.setOpaque(false);
        labelPuntuacionLograda.setOpaque(false);
        labelTamañoPalabra.setOpaque(false);
        laberlmaxErrores.setOpaque(false);
        tamañoPalabra.setOpaque(false);       
    }
    
      /**
       * Metodo para cargar la ayuda en la aplicacion
       */
      private void cargaAyuda(){
        try{
        File fichero = new File("help/mi_helpSet.hs");
        URL hsURL = fichero.toURI().toURL();
        HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
        hb = helpset.createHelpBroker();
        hb.enableHelpOnButton(menuAyuda2, "introduccion", helpset);
        hb.enableHelpKey(getRootPane(), "introduccion", helpset);
        }catch(Exception exc){
        JOptionPane.showMessageDialog(this, "Fallo al cargar la ayuda " + exc, "Erro en la Ayuda", 1);
        }
    }
    
    //Apartir de aqui metodos autogenerados
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSuperior = new javax.swing.JPanel();
        labelTamañoPalabra = new javax.swing.JLabel();
        tamañoPalabra = new javax.swing.JLabel();
        textPalabra = new javax.swing.JTextField();
        panelInferior = new javax.swing.JPanel();
        botonA = new javax.swing.JToggleButton();
        botonB = new javax.swing.JToggleButton();
        botonC = new javax.swing.JToggleButton();
        botonF = new javax.swing.JToggleButton();
        botonE = new javax.swing.JToggleButton();
        botonD = new javax.swing.JToggleButton();
        botonI = new javax.swing.JToggleButton();
        botonH = new javax.swing.JToggleButton();
        botonG = new javax.swing.JToggleButton();
        botonJ = new javax.swing.JToggleButton();
        botonK = new javax.swing.JToggleButton();
        botonL = new javax.swing.JToggleButton();
        botonM = new javax.swing.JToggleButton();
        botonN = new javax.swing.JToggleButton();
        botonÑ = new javax.swing.JToggleButton();
        botonO = new javax.swing.JToggleButton();
        botonP = new javax.swing.JToggleButton();
        botonQ = new javax.swing.JToggleButton();
        botonR = new javax.swing.JToggleButton();
        botonS = new javax.swing.JToggleButton();
        botonT = new javax.swing.JToggleButton();
        botonU = new javax.swing.JToggleButton();
        botonV = new javax.swing.JToggleButton();
        botonW = new javax.swing.JToggleButton();
        botonY = new javax.swing.JToggleButton();
        botonZ = new javax.swing.JToggleButton();
        botonX = new javax.swing.JToggleButton();
        labelImagenAhorcado = new javax.swing.JLabel();
        panelInformativo = new javax.swing.JPanel();
        laberlmaxErrores = new javax.swing.JLabel();
        labelNumErrores = new javax.swing.JLabel();
        labelContieneErrores = new javax.swing.JLabel();
        labelMaximaPuntuacion = new javax.swing.JLabel();
        labelMaximaPuntuacionLograda = new javax.swing.JLabel();
        labelJugadorActual = new javax.swing.JLabel();
        labelNombreJugador = new javax.swing.JLabel();
        labelPuntuacion = new javax.swing.JLabel();
        labelPuntuacionLograda = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menuJuego = new javax.swing.JMenu();
        opcionNuevo = new javax.swing.JMenuItem();
        opcionClasificacion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        opcionTerminar = new javax.swing.JMenuItem();
        separadorMenuJuego = new javax.swing.JPopupMenu.Separator();
        opcionSalir = new javax.swing.JMenuItem();
        menuOpciones = new javax.swing.JMenu();
        menuFondo = new javax.swing.JMenu();
        menuFondo1 = new javax.swing.JMenuItem();
        menuFondo2 = new javax.swing.JMenuItem();
        menuFondo3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuNeutro2 = new javax.swing.JMenuItem();
        menuNeutro3 = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        opcionacercaDe = new javax.swing.JMenuItem();
        menuAyuda2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("El Ahorcado");
        setMaximumSize(new java.awt.Dimension(1024, 680));
        setMinimumSize(new java.awt.Dimension(1024, 680));
        setPreferredSize(new java.awt.Dimension(1024, 680));
        setResizable(false);

        panelSuperior.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Palabra Actual del juego:", 0, 0, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N
        panelSuperior.setToolTipText("panel mostrar datos del juego");
        panelSuperior.setOpaque(false);

        labelTamañoPalabra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTamañoPalabra.setText("Numero de letras de la palabra:");
        labelTamañoPalabra.setFocusable(false);
        labelTamañoPalabra.setOpaque(true);

        tamañoPalabra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tamañoPalabra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tamañoPalabra.setText("ninguna");
        tamañoPalabra.setFocusable(false);
        tamañoPalabra.setOpaque(true);

        textPalabra.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        textPalabra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textPalabra.setFocusable(false);

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelSuperiorLayout.createSequentialGroup()
                        .addComponent(labelTamañoPalabra)
                        .addGap(18, 18, 18)
                        .addComponent(tamañoPalabra)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTamañoPalabra)
                    .addComponent(tamañoPalabra))
                .addGap(33, 33, 33)
                .addComponent(textPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelInferior.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Teclado del Juego:", 0, 0, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N
        panelInferior.setToolTipText("Panle contiene teclado");
        panelInferior.setOpaque(false);

        botonA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonA.setText("A");
        botonA.setNextFocusableComponent(botonB);
        botonA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAActionPerformed(evt);
            }
        });

        botonB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonB.setText("B");
        botonB.setNextFocusableComponent(botonC);
        botonB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBActionPerformed(evt);
            }
        });

        botonC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonC.setText("C");
        botonC.setName(""); // NOI18N
        botonC.setNextFocusableComponent(botonD);
        botonC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCActionPerformed(evt);
            }
        });

        botonF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonF.setText("F");
        botonF.setNextFocusableComponent(botonG);
        botonF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFActionPerformed(evt);
            }
        });

        botonE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonE.setText("E");
        botonE.setNextFocusableComponent(botonF);
        botonE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEActionPerformed(evt);
            }
        });

        botonD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonD.setText("D");
        botonD.setNextFocusableComponent(botonE);
        botonD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDActionPerformed(evt);
            }
        });

        botonI.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonI.setText("I");
        botonI.setNextFocusableComponent(botonJ);
        botonI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIActionPerformed(evt);
            }
        });

        botonH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonH.setText("H");
        botonH.setNextFocusableComponent(botonI);
        botonH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonHActionPerformed(evt);
            }
        });

        botonG.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonG.setText("G");
        botonG.setNextFocusableComponent(botonH);
        botonG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGActionPerformed(evt);
            }
        });

        botonJ.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonJ.setText("J");
        botonJ.setNextFocusableComponent(botonK);
        botonJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonJActionPerformed(evt);
            }
        });

        botonK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonK.setText("K");
        botonK.setNextFocusableComponent(botonL);
        botonK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonKActionPerformed(evt);
            }
        });

        botonL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonL.setText("L");
        botonL.setNextFocusableComponent(botonM);
        botonL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLActionPerformed(evt);
            }
        });

        botonM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonM.setText("M");
        botonM.setNextFocusableComponent(botonN);
        botonM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMActionPerformed(evt);
            }
        });

        botonN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonN.setText("N");
        botonN.setNextFocusableComponent(botonÑ);
        botonN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNActionPerformed(evt);
            }
        });

        botonÑ.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonÑ.setText("Ñ");
        botonÑ.setNextFocusableComponent(botonO);
        botonÑ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonÑActionPerformed(evt);
            }
        });

        botonO.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonO.setText("O");
        botonO.setNextFocusableComponent(botonP);
        botonO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOActionPerformed(evt);
            }
        });

        botonP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonP.setText("P");
        botonP.setNextFocusableComponent(botonQ);
        botonP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPActionPerformed(evt);
            }
        });

        botonQ.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonQ.setText("Q");
        botonQ.setNextFocusableComponent(botonR);
        botonQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonQActionPerformed(evt);
            }
        });

        botonR.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonR.setText("R");
        botonR.setNextFocusableComponent(botonS);
        botonR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRActionPerformed(evt);
            }
        });

        botonS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonS.setText("S");
        botonS.setNextFocusableComponent(botonT);
        botonS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSActionPerformed(evt);
            }
        });

        botonT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonT.setText("T");
        botonT.setNextFocusableComponent(botonU);
        botonT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTActionPerformed(evt);
            }
        });

        botonU.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonU.setText("U");
        botonU.setNextFocusableComponent(botonV);
        botonU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonUActionPerformed(evt);
            }
        });

        botonV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonV.setText("V");
        botonV.setNextFocusableComponent(botonW);
        botonV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVActionPerformed(evt);
            }
        });

        botonW.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonW.setText("W");
        botonW.setNextFocusableComponent(botonX);
        botonW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonWActionPerformed(evt);
            }
        });

        botonY.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonY.setText("Y");
        botonY.setNextFocusableComponent(botonZ);
        botonY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonYActionPerformed(evt);
            }
        });

        botonZ.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonZ.setText("Z");
        botonZ.setNextFocusableComponent(botonA);
        botonZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonZActionPerformed(evt);
            }
        });

        botonX.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botonX.setText("X");
        botonX.setNextFocusableComponent(botonY);
        botonX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInferiorLayout = new javax.swing.GroupLayout(panelInferior);
        panelInferior.setLayout(panelInferiorLayout);
        panelInferiorLayout.setHorizontalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInferiorLayout.createSequentialGroup()
                .addGroup(panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInferiorLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(botonA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonJ))
                    .addGroup(panelInferiorLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(botonS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonW)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonY)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonZ))
                    .addGroup(panelInferiorLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(botonK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonÑ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonQ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonR)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInferiorLayout.setVerticalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInferiorLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonA)
                    .addComponent(botonB)
                    .addComponent(botonC)
                    .addComponent(botonD)
                    .addComponent(botonE)
                    .addComponent(botonF)
                    .addComponent(botonG)
                    .addComponent(botonH)
                    .addComponent(botonI)
                    .addComponent(botonJ))
                .addGap(18, 18, 18)
                .addGroup(panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonK)
                    .addComponent(botonL)
                    .addComponent(botonM)
                    .addComponent(botonN)
                    .addComponent(botonÑ)
                    .addComponent(botonO)
                    .addComponent(botonP)
                    .addComponent(botonQ)
                    .addComponent(botonR))
                .addGap(18, 18, 18)
                .addGroup(panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonS)
                    .addComponent(botonT)
                    .addComponent(botonU)
                    .addComponent(botonV)
                    .addComponent(botonW)
                    .addComponent(botonY)
                    .addComponent(botonZ)
                    .addComponent(botonX))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        labelImagenAhorcado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelImagenAhorcado.setFocusable(false);

        panelInformativo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de la partida:", 0, 0, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 204, 204))); // NOI18N
        panelInformativo.setToolTipText("Panle informativo del juego");
        panelInformativo.setOpaque(false);

        laberlmaxErrores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        laberlmaxErrores.setText(" Maximos errores permitidos:                        5");
        laberlmaxErrores.setToolTipText("Contiene el numero de errores maximos");
        laberlmaxErrores.setFocusable(false);
        laberlmaxErrores.setOpaque(true);

        labelNumErrores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelNumErrores.setText("Errores Actuales:");
        labelNumErrores.setToolTipText("Etiqueta errores actuales");
        labelNumErrores.setFocusable(false);
        labelNumErrores.setOpaque(true);

        labelContieneErrores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelContieneErrores.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelContieneErrores.setText("-");
        labelContieneErrores.setToolTipText("Numero errores del jugador");
        labelContieneErrores.setFocusable(false);
        labelContieneErrores.setOpaque(true);

        labelMaximaPuntuacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelMaximaPuntuacion.setText("Maxima Puntuacion:");
        labelMaximaPuntuacion.setFocusable(false);
        labelMaximaPuntuacion.setOpaque(true);

        labelMaximaPuntuacionLograda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelMaximaPuntuacionLograda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelMaximaPuntuacionLograda.setText("Ninguna");
        labelMaximaPuntuacionLograda.setFocusable(false);
        labelMaximaPuntuacionLograda.setOpaque(true);

        labelJugadorActual.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelJugadorActual.setText("Jugador Actual:");
        labelJugadorActual.setFocusable(false);
        labelJugadorActual.setOpaque(true);

        labelNombreJugador.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelNombreJugador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNombreJugador.setText("Ninguno");
        labelNombreJugador.setFocusable(false);
        labelNombreJugador.setOpaque(true);

        labelPuntuacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelPuntuacion.setText("Puntuacion Actual:");
        labelPuntuacion.setFocusable(false);
        labelPuntuacion.setOpaque(true);

        labelPuntuacionLograda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelPuntuacionLograda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelPuntuacionLograda.setText("Ninguna");
        labelPuntuacionLograda.setFocusable(false);
        labelPuntuacionLograda.setOpaque(true);

        javax.swing.GroupLayout panelInformativoLayout = new javax.swing.GroupLayout(panelInformativo);
        panelInformativo.setLayout(panelInformativoLayout);
        panelInformativoLayout.setHorizontalGroup(
            panelInformativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformativoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInformativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(laberlmaxErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformativoLayout.createSequentialGroup()
                        .addGroup(panelInformativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInformativoLayout.createSequentialGroup()
                                .addComponent(labelMaximaPuntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(labelNumErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInformativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelContieneErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelMaximaPuntuacionLograda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelInformativoLayout.createSequentialGroup()
                        .addComponent(labelJugadorActual, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(labelNombreJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelInformativoLayout.createSequentialGroup()
                        .addComponent(labelPuntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(labelPuntuacionLograda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInformativoLayout.setVerticalGroup(
            panelInformativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformativoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(laberlmaxErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInformativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumErrores)
                    .addComponent(labelContieneErrores))
                .addGap(18, 18, 18)
                .addGroup(panelInformativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMaximaPuntuacion)
                    .addComponent(labelMaximaPuntuacionLograda))
                .addGap(18, 18, 18)
                .addGroup(panelInformativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelJugadorActual)
                    .addComponent(labelNombreJugador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInformativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPuntuacion)
                    .addComponent(labelPuntuacionLograda))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        menuJuego.setMnemonic('J');
        menuJuego.setText("Juego");
        menuJuego.setToolTipText("Menu opciones juego");
        menuJuego.setFocusable(false);

        opcionNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        opcionNuevo.setMnemonic('N');
        opcionNuevo.setText("Nuevo Juego");
        opcionNuevo.setToolTipText("Opcion nuevo juego");
        opcionNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionNuevoActionPerformed(evt);
            }
        });
        menuJuego.add(opcionNuevo);

        opcionClasificacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        opcionClasificacion.setMnemonic('v');
        opcionClasificacion.setText("Ver Clasificacion");
        opcionClasificacion.setToolTipText("Opcion menu juego ver la clasificacion");
        opcionClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionClasificacionActionPerformed(evt);
            }
        });
        menuJuego.add(opcionClasificacion);
        menuJuego.add(jSeparator1);

        opcionTerminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        opcionTerminar.setMnemonic('t');
        opcionTerminar.setText("Terminar");
        opcionTerminar.setToolTipText("opcion para terminar de jugar");
        opcionTerminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionTerminarActionPerformed(evt);
            }
        });
        menuJuego.add(opcionTerminar);
        menuJuego.add(separadorMenuJuego);

        opcionSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        opcionSalir.setMnemonic('S');
        opcionSalir.setText("Salir");
        opcionSalir.setToolTipText("Opcion para salir del juego");
        opcionSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionSalirActionPerformed(evt);
            }
        });
        menuJuego.add(opcionSalir);

        barraMenu.add(menuJuego);

        menuOpciones.setMnemonic('O');
        menuOpciones.setText("Opciones");
        menuOpciones.setToolTipText("Opciones de la aplicacion");
        menuOpciones.setFocusable(false);

        menuFondo.setMnemonic('f');
        menuFondo.setText("Fondo");
        menuFondo.setToolTipText("opcion menu fondo escoger fondos");

        menuFondo1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        menuFondo1.setText("fondo1");
        menuFondo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFondo1ActionPerformed(evt);
            }
        });
        menuFondo.add(menuFondo1);

        menuFondo2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        menuFondo2.setText("fondo2");
        menuFondo2.setToolTipText("opcion menu fondo 2");
        menuFondo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFondo2ActionPerformed(evt);
            }
        });
        menuFondo.add(menuFondo2);

        menuFondo3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        menuFondo3.setText("fondo3");
        menuFondo3.setToolTipText("opcion menu elegir fondo3");
        menuFondo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFondo3ActionPerformed(evt);
            }
        });
        menuFondo.add(menuFondo3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setMnemonic('g');
        jMenuItem1.setText("neutroGris");
        jMenuItem1.setToolTipText("opcion fondo neutro");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuFondo.add(jMenuItem1);

        menuNeutro2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuNeutro2.setMnemonic('r');
        menuNeutro2.setText("neutroRosa");
        menuNeutro2.setToolTipText("opcion menu color neutro rosa");
        menuNeutro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNeutro2ActionPerformed(evt);
            }
        });
        menuFondo.add(menuNeutro2);

        menuNeutro3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuNeutro3.setMnemonic('a');
        menuNeutro3.setText("neutroAzul");
        menuNeutro3.setToolTipText("opcionmenu neutro azul");
        menuNeutro3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNeutro3ActionPerformed(evt);
            }
        });
        menuFondo.add(menuNeutro3);

        menuOpciones.add(menuFondo);

        barraMenu.add(menuOpciones);

        menuAyuda.setMnemonic('A');
        menuAyuda.setText("Ayuda");
        menuAyuda.setToolTipText("Menu abrir informacion de la aplicacion");
        menuAyuda.setFocusable(false);

        opcionacercaDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        opcionacercaDe.setMnemonic('c');
        opcionacercaDe.setText("Acerca De");
        opcionacercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionacercaDeActionPerformed(evt);
            }
        });
        menuAyuda.add(opcionacercaDe);

        menuAyuda2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        menuAyuda2.setText("Ayuda");
        menuAyuda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAyuda2ActionPerformed(evt);
            }
        });
        menuAyuda.add(menuAyuda2);

        barraMenu.add(menuAyuda);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelImagenAhorcado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInformativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelInformativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(labelImagenAhorcado, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo del boton salir del menu para cerrar la aplicacion
     * @param evt 
     */
    private void opcionSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionSalirActionPerformed
        // TODO add your handling code here:
        reproducirSonido("WAV/cerrar.wav");
        System.exit(0);
    }//GEN-LAST:event_opcionSalirActionPerformed

    private void botonCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCActionPerformed
        // TODO add your handling code here:
        char letra = botonC.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonCActionPerformed

    private void botonFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFActionPerformed
        // TODO add your handling code here:
        char letra = botonF.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonFActionPerformed

    private void botonIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIActionPerformed
        // TODO add your handling code here:
        char letra = botonI.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonIActionPerformed

    private void botonJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonJActionPerformed
        // TODO add your handling code here:
        char letra = botonJ.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonJActionPerformed

    private void botonMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMActionPerformed
        // TODO add your handling code here:
        char letra = botonM.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonMActionPerformed

    private void botonOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOActionPerformed
        // TODO add your handling code here:
        char letra = botonO.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonOActionPerformed

    private void botonRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRActionPerformed
        // TODO add your handling code here:
        char letra = botonR.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonRActionPerformed

    private void botonUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonUActionPerformed
        // TODO add your handling code here:
        char letra = botonU.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonUActionPerformed

    private void botonYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonYActionPerformed
        // TODO add your handling code here:
        char letra = botonY.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonYActionPerformed

    private void botonKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonKActionPerformed
        // TODO add your handling code here:
        char letra = botonK.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonKActionPerformed

    /**
     * Metodo del boton del menu nuevo jugador
     * @param evt 
     */
    private void opcionNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionNuevoActionPerformed
        // TODO add your handling code here:
        posicionInicial();
        habilitarBotones();
        NuevoJugador nuevoJugador = new NuevoJugador(this,true);
        nuevoJugador.setVisible(true);
        reproducirSonido("WAV/nuevoJugador.wav");
        labelNombreJugador.setText(jugador);
        nuevaPalabra();
        mostrarPalabra();
        opcionNuevo.setEnabled(false);
        
    }//GEN-LAST:event_opcionNuevoActionPerformed

   
    /**
     * Metodo del boton del menu ayuda del acerca de
     * @param evt 
     */
    private void opcionacercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionacercaDeActionPerformed
        // TODO add your handling code here:
     AcercaDe acerc = new   AcercaDe(this, true);
     acerc.setVisible(true);
    }//GEN-LAST:event_opcionacercaDeActionPerformed

    /**
     * Metodo de la opcion del menu terminar
     * @param evt 
     */
    private void opcionTerminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionTerminarActionPerformed
        // TODO add your handling code here:
         comprobarMaxPuntuacion();
         posicionInicial();
         labelPuntuacionLograda.setText("ninguna");
         resetearBotones();
         deshabilitarBotones();
         contadorErrores=0;
         contadorAciertos=0;
         opcionNuevo.setEnabled(true);   
    }//GEN-LAST:event_opcionTerminarActionPerformed

    private void botonAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAActionPerformed
        // TODO add your handling code here:
        char letra = botonA.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonAActionPerformed

    private void botonBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBActionPerformed
        // TODO add your handling code here:
        char letra = botonB.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonBActionPerformed

    private void botonDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDActionPerformed
        // TODO add your handling code here:
        char letra = botonD.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonDActionPerformed

    private void botonEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEActionPerformed
        // TODO add your handling code here:
        char letra = botonE.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonEActionPerformed

    private void botonGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGActionPerformed
        // TODO add your handling code here:
        char letra = botonG.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonGActionPerformed

    private void botonHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonHActionPerformed
        // TODO add your handling code here:
        char letra = botonH.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonHActionPerformed

    private void botonLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLActionPerformed
        // TODO add your handling code here:
        char letra = botonL.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonLActionPerformed

    private void botonNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNActionPerformed
        // TODO add your handling code here:
        char letra = botonN.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonNActionPerformed

    private void botonÑActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonÑActionPerformed
        // TODO add your handling code here:
        char letra = botonÑ.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonÑActionPerformed

    private void botonPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPActionPerformed
        // TODO add your handling code here:
        char letra = botonP.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonPActionPerformed

    private void botonQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonQActionPerformed
        // TODO add your handling code here:
        char letra = botonQ.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonQActionPerformed

    private void botonSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSActionPerformed
        // TODO add your handling code here:
        char letra = botonS.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonSActionPerformed

    private void botonTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTActionPerformed
        // TODO add your handling code here:
        char letra = botonT.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonTActionPerformed

    private void botonVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVActionPerformed
        // TODO add your handling code here:
        char letra = botonV.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonVActionPerformed

    private void botonWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonWActionPerformed
        // TODO add your handling code here:
        char letra = botonW.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonWActionPerformed

    private void botonXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonXActionPerformed
        // TODO add your handling code here:
        char letra = botonX.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonXActionPerformed

    private void botonZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonZActionPerformed
        // TODO add your handling code here:
        char letra = botonZ.getText().toLowerCase().charAt(0);
        comprobarLetra(letra);
    }//GEN-LAST:event_botonZActionPerformed

    private void opcionClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionClasificacionActionPerformed
        // TODO add your handling code here:
        elemento=listaRecords.iterator();
        listadoPremiados="";
        while(elemento.hasNext()){
            Record rec=(Record) elemento.next();
            listadoPremiados+=rec.getNombre()+" "+rec.getPuntuacion()+"\n";
        }
        JOptionPane.showMessageDialog(this, listadoPremiados, "Mejores Puntuaciones", 2);
    }//GEN-LAST:event_opcionClasificacionActionPerformed

    private void menuFondo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFondo1ActionPerformed
        // TODO add your handling code here:
        f.cambiarFondo(1);
        panelSuperior.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Palabra Actual del Juego:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(204, 204, 204)));
        panelInferior.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Teclado del Juego:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(204, 204, 204)));
        panelInformativo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion de la partida:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(204, 204, 204)));
        
    }//GEN-LAST:event_menuFondo1ActionPerformed


    private void menuFondo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFondo2ActionPerformed
        // TODO add your handling code here:
        f.cambiarFondo(2);
        cambioFormatoElementos();
    }//GEN-LAST:event_menuFondo2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        f.cambiarFondo(3);
        cambioFormatoElementos();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuFondo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFondo3ActionPerformed
        // TODO add your handling code here:
        f.cambiarFondo(4);
        cambioFormatoElementos();
    }//GEN-LAST:event_menuFondo3ActionPerformed

    private void menuNeutro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNeutro2ActionPerformed
        // TODO add your handling code here:
        f.cambiarFondo(5);
        cambioFormatoElementos();
    }//GEN-LAST:event_menuNeutro2ActionPerformed

    private void menuNeutro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNeutro3ActionPerformed
        // TODO add your handling code here:
        f.cambiarFondo(6);
        cambioFormatoElementos();
    }//GEN-LAST:event_menuNeutro3ActionPerformed

    private void menuAyuda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAyuda2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuAyuda2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplicacionAhorcado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplicacionAhorcado().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JToggleButton botonA;
    private javax.swing.JToggleButton botonB;
    private javax.swing.JToggleButton botonC;
    private javax.swing.JToggleButton botonD;
    private javax.swing.JToggleButton botonE;
    private javax.swing.JToggleButton botonF;
    private javax.swing.JToggleButton botonG;
    private javax.swing.JToggleButton botonH;
    private javax.swing.JToggleButton botonI;
    private javax.swing.JToggleButton botonJ;
    private javax.swing.JToggleButton botonK;
    private javax.swing.JToggleButton botonL;
    private javax.swing.JToggleButton botonM;
    private javax.swing.JToggleButton botonN;
    private javax.swing.JToggleButton botonO;
    private javax.swing.JToggleButton botonP;
    private javax.swing.JToggleButton botonQ;
    private javax.swing.JToggleButton botonR;
    private javax.swing.JToggleButton botonS;
    private javax.swing.JToggleButton botonT;
    private javax.swing.JToggleButton botonU;
    private javax.swing.JToggleButton botonV;
    private javax.swing.JToggleButton botonW;
    private javax.swing.JToggleButton botonX;
    private javax.swing.JToggleButton botonY;
    private javax.swing.JToggleButton botonZ;
    private javax.swing.JToggleButton botonÑ;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel labelContieneErrores;
    private javax.swing.JLabel labelImagenAhorcado;
    private javax.swing.JLabel labelJugadorActual;
    private javax.swing.JLabel labelMaximaPuntuacion;
    private javax.swing.JLabel labelMaximaPuntuacionLograda;
    private javax.swing.JLabel labelNombreJugador;
    private javax.swing.JLabel labelNumErrores;
    private javax.swing.JLabel labelPuntuacion;
    private javax.swing.JLabel labelPuntuacionLograda;
    private javax.swing.JLabel labelTamañoPalabra;
    private javax.swing.JLabel laberlmaxErrores;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuItem menuAyuda2;
    private javax.swing.JMenu menuFondo;
    private javax.swing.JMenuItem menuFondo1;
    private javax.swing.JMenuItem menuFondo2;
    private javax.swing.JMenuItem menuFondo3;
    private javax.swing.JMenu menuJuego;
    private javax.swing.JMenuItem menuNeutro2;
    private javax.swing.JMenuItem menuNeutro3;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenuItem opcionClasificacion;
    private javax.swing.JMenuItem opcionNuevo;
    private javax.swing.JMenuItem opcionSalir;
    private javax.swing.JMenuItem opcionTerminar;
    private javax.swing.JMenuItem opcionacercaDe;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelInformativo;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPopupMenu.Separator separadorMenuJuego;
    private javax.swing.JLabel tamañoPalabra;
    private javax.swing.JTextField textPalabra;
    // End of variables declaration//GEN-END:variables
}
