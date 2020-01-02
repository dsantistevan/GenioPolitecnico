/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnico;

import Ventanas.VentanaInicio;
import data.GenioData;
import excepciones.ArchivoIncorrectoException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.ArbolBinario;

/**
 *
 * @author David Santistevan
 */
public class GenioPolitecnico extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VentanaInicio ventanaInicio = new VentanaInicio();
        Scene sceneInicio = new Scene(ventanaInicio.getRoot(), 480, 324);
        
        
        ventanaInicio.getBtJugar().setOnAction(e -> cargarJuego(primaryStage));
        ventanaInicio.getBtSalir().setOnAction(e -> Platform.exit());
        
        primaryStage.setTitle("AKINATOR");
        primaryStage.setScene(sceneInicio);
        primaryStage.show();
    }
    
    public void cargarJuego(Stage primaryStage){
        ArbolBinario<String> arbol = null;
        try {
            arbol=GenioData.cargarArbolInicial();
        } catch (IOException | ArchivoIncorrectoException ex) {
            Logger.getLogger(GenioPolitecnico.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final ArbolBinario<String> tree=arbol;
        
        TextField tf=new TextField();
        Label l=new Label(arbol==null ? "" : arbol.getActual());
        Button btn = new Button();
        btn.setText("Responder");
        btn.setOnAction((ActionEvent event) -> {
            String s=tf.getText();
            if((s.equalsIgnoreCase("Si") || s.equalsIgnoreCase("No")) && tree!=null){
                tree.respuesta(s);
                l.setText(tree.getActual());
            }
        });
        
        StackPane root = new StackPane();
        VBox vb=new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(l,tf,btn);
        root.getChildren().add(vb);
        
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("AKINATOR");
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
