package es.ceu.gisi.modcomp.webcrawler.jflex;

import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.Tipo;
import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.Token;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Esta clase encapsula toda la lógica de interacción con el parser HTML.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class JFlexScraper {
    public static ArrayList<String> enlacesA = new ArrayList<String>();
    public static ArrayList<String> enlacesImg = new ArrayList<String>();
    Stack<String> leerPila = new Stack(); 
    boolean malBalanceado = false;
    HTMLParser analizador;
    public JFlexScraper(File fichero) throws FileNotFoundException, IOException {
        Reader reader = new BufferedReader(new FileReader(fichero));
        analizador = new HTMLParser(reader);
        
        int estado = 0;
        boolean etiquetaA = false; 
        boolean etiquetaIMG =  false;
        boolean valorEsHREF = false;
        boolean valorEsSRC = false;
        Token tokenLeer= analizador.nextToken(); 
        
        while (tokenLeer != null){
            switch (estado){
                case 0 :
                    if (tokenLeer.getTipo() == Tipo.OPEN){
                        {
                            estado = 1;
                    
                        }
                    }
                        break;
                case 1:
                    if (tokenLeer.getTipo() == Tipo.PALABRA){
                        estado = 2;
                        leerPila.add (tokenLeer.getValor()); 
                          if (tokenLeer.getValor().equalsIgnoreCase("a")){
                              etiquetaA = true;
                            }else if (tokenLeer.getValor().equalsIgnoreCase("img")){
                              etiquetaIMG = true; } 
                    }
                    else if ( tokenLeer.getTipo()==Tipo.SLASH){
                        estado = 6;
                    }
                break;
                case 2 :
                    if (tokenLeer.getTipo() == Tipo.PALABRA ){
                        estado=3;
                        if(etiquetaA){
                            if (tokenLeer.getValor().equalsIgnoreCase("href")){
                                valorEsHREF = true;
                        }
                    }
                    }else if (etiquetaIMG){
                         if (tokenLeer.getValor().equalsIgnoreCase("src")){
                             valorEsSRC = true;
                        }
                    }
                    else if (tokenLeer.getTipo() == Tipo.SLASH){
                        leerPila.pop();
                         estado= 5;
                    }
                    
                    else if (tokenLeer.getTipo() == Tipo.CLOSE){
                         estado = 0;
                         
                        }
                    
                    
                    break;
                case 3:
                     if (tokenLeer.getTipo() == Tipo.IGUAL){
                        estado= 4;
                    }
                      break;
               case 4: 
                    if (tokenLeer.getTipo() == Tipo.VALOR){
                         estado = 2;
                    
         
                        if (valorEsHREF){
                        enlacesA.add(tokenLeer.getValor());
                        }
                        else if (valorEsSRC){
                        enlacesImg.add(tokenLeer.getValor());
                         }
                    }
                    break;
                              
                    case 5:
                    if (tokenLeer.getTipo() == Tipo.CLOSE){
                        estado = 0;
                        
                    }
                    break;
                case 6:
                     if (tokenLeer.getTipo() == Tipo.PALABRA){
                         
                             estado =7;
                            
                         if (tokenLeer.getValor().equalsIgnoreCase(leerPila.peek())){
                                 leerPila.pop();
                         } else{
                             malBalanceado = true;
                             
                         }     
                    }
                      break;
                      
                case 7:
                         if (tokenLeer.getTipo() == Tipo.CLOSE){
                         estado =0;}
                         
                     
                    
                    
                    
                    
              
            }
        tokenLeer = analizador.nextToken();
        
        }
        
                
    }

    // Esta clase debe contener tu automata programado...
    public ArrayList<String> obtenerHiperenlaces() {
        return enlacesA;
     
    }

    public ArrayList<String> obtenerHiperenlacesImagenes() {
        // Habrá que programarlo..
        return new ArrayList<String>();
    }

    public boolean esDocumentoHTMLBienBalanceado() {
   
        return !(malBalanceado || enlacesA.isEmpty());
    }
}
