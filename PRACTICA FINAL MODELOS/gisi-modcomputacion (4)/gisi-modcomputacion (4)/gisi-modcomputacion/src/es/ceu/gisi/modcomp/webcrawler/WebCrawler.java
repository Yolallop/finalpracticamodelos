package es.ceu.gisi.modcomp.webcrawler;

import static es.ceu.gisi.modcomp.webcrawler.jflex.JFlexScraper.enlacesA;
import static es.ceu.gisi.modcomp.webcrawler.jflex.JFlexScraper.enlacesImg;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Esta aplicación contiene el programa principal que ejecuta ambas partes del
 * proyecto de programación.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class WebCrawler {
    public static void main(String[] args){
        String PATH_PRUEBAS = new java.io.File("").getAbsolutePath()
            + "/test/es/ceu/gisi/modcomp/webcrawler/jflex/test/";
        
    }
       public ArrayList<String> obtenerHiperenlaces() throws IOException {
        File crearEnlacesA = new File ("obtenerHiperenlacesA.txt");
        FileWriter fileEnlacesA =  new FileWriter ("obtenerHiperenalcesA.txt"); // se escribe 
        BufferedWriter escribirEnlacesA = new BufferedWriter(fileEnlacesA); // PASO AL FICHERO ARRAY LIST 
       

        for ( int i=0;i<enlacesA.size();i++){
        escribirEnlacesA.write(enlacesA.get(i));
        escribirEnlacesA.newLine();
        }
        escribirEnlacesA.close();
        return enlacesA;  
    }
        
       
  

    public ArrayList<String> obtenerHiperenlacesImagenes() throws IOException {
        File crearEnlacesA = new File ("obtenerHiperenlacesIMG.txt");
        FileWriter fileEnlacesA =  new FileWriter ("obtenerHiperenalcesIMG.txt"); // se escribe 
        BufferedWriter escribirEnlacesA = new BufferedWriter(fileEnlacesA); // PASO AL FICHERO ARRAY LIST 
       

        for ( int i=0;i<enlacesImg.size();i++){
        escribirEnlacesA.write(enlacesImg.get(i));
        escribirEnlacesA.newLine();
        }
        escribirEnlacesA.close();
        return enlacesImg;  

        
        
    }

        // Deberá inicializar JFlexScraper con el fichero HTML a analizar
        // Y creará un fichero con todos los hiperenlaces que encuentre.
        // También deberá indicar, mediante un mensaje en pantalla que
        // el fichero HTML que se ha pasado está bien balanceado.

        // Deberá inicializar JsoupScraper con la DIRECCIÓN HTTP de una página
        // web a analizar. Creará un fichero con todos los hiperenlaces que
        // encuentre en la página web. También obtendrá estadísticas de uso 
        // de las etiquetas HTML más comunes: a, br, div, li, ul, p, span, table, td, tr
    }

