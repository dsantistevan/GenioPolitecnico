/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnico;

import Ventanas.VentanaInicio;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author David Santistevan
 */
public class GenioPolitecnico extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VentanaInicio ventanaInicio = new VentanaInicio();
        Scene sceneInicio = new Scene(ventanaInicio.getRoot(), 480, 370);
        
        
        ventanaInicio.getBtSalir().setOnAction(e -> Platform.exit());
        
        primaryStage.setTitle("AKINATOR");
        primaryStage.setScene(sceneInicio);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
