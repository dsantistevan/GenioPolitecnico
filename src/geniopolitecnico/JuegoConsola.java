/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnico;

import data.GenioData;
import excepciones.ArchivoIncorrectoException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ArbolBinario;

/**
 *
 * @author David Santistevan
 */
public class JuegoConsola {
    public static void main(String[] args){
        ArbolBinario arbol = null;
        try {
            arbol=GenioData.cargarArbolInicial();
        } catch (IOException | ArchivoIncorrectoException ex) {
            Logger.getLogger(JuegoConsola.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(arbol==null){
            System.out.println("No valio el archivo");
        }else{
            
            Scanner sc=new Scanner(System.in);
            arbol.resolver(sc);
        }
        GenioData.guardarArbol(arbol);
    }
}
