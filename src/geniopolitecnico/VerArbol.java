/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnico;

import data.GenioData;
import excepciones.ArchivoIncorrectoException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.ArbolBinario;
import view.BTView;

/**
 *
 * @author David Santistevan
 */
public class VerArbol extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ArbolBinario<String> tree=null;
        try {
            tree = GenioData.cargarArbolInicial();
        } catch (IOException | ArchivoIncorrectoException ex) {
            Logger.getLogger(VerArbol.class.getName()).log(Level.SEVERE, null, ex);
        }
        BTView btv=new BTView(tree);
        btv.mostrarArbol();
        System.out.println(btv.getTree().isEmpty());
        Scene scene = new Scene(btv, 300, 250);
        
        primaryStage.setTitle("Ver Arbol de Preguntas y Respuestas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
