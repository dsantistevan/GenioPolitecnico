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
public class RespuestaIncorrectaException extends Exception {
    
    public RespuestaIncorrectaException(){
        super("El Genio no ha adivinado");
    }
}
