/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data.GenioData;
import excepciones.AdivinadoException;
import excepciones.ArchivoIncorrectoException;
import excepciones.RespuestaIncorrectaException;
import geniopolitecnico.GenioPolitecnico;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.ArbolBinario;

/**
 *
 * @author David Santistevan
 */
public class SeccionJuego {

    private final BorderPane root;
    private final ArbolBinario<String> tree;
    private final SeccionFormulario sf;
    private final Button btn;
    
    public SeccionJuego() {
        ArbolBinario<String> arbol = null;
        try {
            arbol=GenioData.cargarArbolInicial();
        } catch (IOException | ArchivoIncorrectoException ex) {
            Logger.getLogger(GenioPolitecnico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tree=arbol;
        
        sf=new SeccionFormulario(arbol!=null ? arbol.getActual() : "");
        
        btn = new Button();
        btn.setText("Responder");
        
        sf.getVbox().getChildren().add(btn);        
        root=new BorderPane();
        root.setCenter(sf.getRoot());
        root.setOnKeyPressed((e) ->{
            if(e.getCode().equals(KeyCode.ENTER))
                btn.fire();
        });
    }

    public BorderPane getRoot() {
        return root;
    }
    
    public void responder() throws AdivinadoException, RespuestaIncorrectaException{
        String s=sf.getValor();
        ImageView img = new ImageView("imagenes/respuestaSi.png");
        if((s.equalsIgnoreCase("Si") || s.equalsIgnoreCase("No")) && tree!=null){
            sf.getImg().setImage((s.equalsIgnoreCase("Si"))?new Image("imagenes/respuestaSi.png"):
                    new Image("imagenes/respuestaNo.png"));
            try {
                tree.respuesta(s);
            } catch (AdivinadoException | RespuestaIncorrectaException ex) {
                throw ex;
            }finally{
                sf.setNombre(tree.getActual());
            }
        }else
            sf.activarError("Responda Si o No por favor.");
        sf.clear();
    }

    public Button getBtn() {
        return btn;
    }
    
    public ArbolBinario getTree(){
        return tree;
    }
    
    
}
