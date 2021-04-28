package es.ceu.gisi.modcomp.webcrawler.jflex;

import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.Token;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Esta clase encapsula toda la lógica de interacción con el parser HTML.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class JFlexScraper {

    HTMLParser analizador;
    Stack <String> lecturasDePilas;
    public JFlexScraper(File fichero) throws FileNotFoundException {
        Reader reader = new BufferedReader(new FileReader(fichero));
        analizador = new HTMLParser(reader);
        
        int state = 0;
        boolean etiquetaA = false; 
        boolean etiquetaImg =  false;
        Token tokenLeer= analizador.nextToken();
        while (tokenLeer != null){
            switch (state){
                case 0 :
                    if (tokenLeer.getTipo() == Tipo.OPEN){
                        {
                            state = 1;
                          
                                 
                        }
                        break; 
                 case 1 : // palabra "HEAD 
                    if (tokenLeer.getTipo() ==Tipo.PALABRA);
                    lecturasDePila.add (tokenLeer.getValor());
                    if (tokenleer.getValor().equalsIgnoreCase("a")){
                        etiquetaA = true;
                        
                    }
                    state = 2;
                    }
                
            }
        }
        
                
    }

    // Esta clase debe contener tu automata programado...
    public ArrayList<String> obtenerHiperenlaces() {
        // Habrá que programarlo..
        return new ArrayList<String>();
    }

    public ArrayList<String> obtenerHiperenlacesImagenes() {
        // Habrá que programarlo..
        return new ArrayList<String>();
    }

    public boolean esDocumentoHTMLBienBalanceado() {
        // Habrá que programarlo..
        return false;
    }
}
