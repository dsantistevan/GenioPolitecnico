/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author David Santistevan
 */
public class ArchivoIncorrectoException extends Exception {

    public ArchivoIncorrectoException() {
        super("El archivo se encuentra dañado, verifique el formato del archivo de texto");
    }
    
}
