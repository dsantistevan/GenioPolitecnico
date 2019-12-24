/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import excepciones.ArchivoIncorrectoException;
import RECURSOS.CONSTANTES;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ArbolBinario;
import modelo.Node;

/**
 *
 * @author David Santistevan
 */
public class GenioData {
    /**
     * Carga del documento de preguntas y respuestas
     * @return ArbolBinario usando el documento .txt
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws excepciones.ArchivoIncorrectoException
     */
    public static ArbolBinario cargarArbolInicial() throws FileNotFoundException, IOException, ArchivoIncorrectoException{
        File f = new File(CONSTANTES.PATH_DATA+"datos-1.txt");
        Deque<Node> pila=new LinkedList<>();
        Node<String> n;
        try(BufferedReader br=new BufferedReader(new FileReader(f))){
            String linea=br.readLine();
            while(linea!=null){
                if(!(linea.startsWith("#P") || linea.startsWith("#R")))
                    throw new ArchivoIncorrectoException();
                n=new Node(linea.substring(3));
                if(linea.startsWith("#P")){
                    n.setRight(pila.poll());
                    n.setLeft(pila.poll());
                }
                pila.push(n);
                linea=br.readLine();
            }
            
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (IOException | ArchivoIncorrectoException ex) {
            throw ex;
        }
        return new ArbolBinario(pila.poll());
    }
    
    public static void guardarArbol(ArbolBinario arbol){
        File f=new File(CONSTANTES.PATH_DATA+"datos-1.txt");
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(f))){
            arbol.guardarArbol(bw);
        } catch (IOException ex) {
            Logger.getLogger(GenioData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
