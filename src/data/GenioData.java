/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import excepciones.ArchivoIncorrectoException;
import RECURSOS.CONSTANTES;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import modelo.ArbolBinario;
import modelo.Node;

/**
 *
 * @author David Santistevan
 */
public class GenioData {
    /**
     * Carga del documento de preguntas y respuestas
     * @return 
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws excepciones.ArchivoIncorrectoException
     */
    public static ArbolBinario cargarOficinasInicial() throws FileNotFoundException, IOException, ArchivoIncorrectoException{
        File f = new File(CONSTANTES.PATH_DATA+"datos-1.txt");
        Deque<Node> pila=new LinkedList<>();
        Node<String> n = null;
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
                pila.offer(n);
            }
            
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (IOException | ArchivoIncorrectoException ex) {
            throw ex;
        }
        return new ArbolBinario(n);
    }
}
