/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.ArbolBinario;
import view.SeccionFormulario;

/**
 *
 * @author David Santistevan
 */
public class VentanaNuevoIngreso {
    private VBox root;
    private final ArbolBinario tree;
    private final SeccionFormulario sf;
    private final Button btn;
    private final Button salir;
    
    public VentanaNuevoIngreso(ArbolBinario a){
        tree=a;
        sf=new SeccionFormulario("Mi pregunta fue: "+tree.getPrevio()+
                "\nCual fue su respuesta?");
        
        btn = new Button();
        btn.setText("Responder");
        
        root=new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(sf.getRoot(),btn);
        activarBoton();
        salir=new Button("Salir");
    }
    
    private void activarBoton(){
        btn.setOnAction((e) ->{
            String animal=sf.getValor();
            if(!animal.equals("")){
                sf.clear();
                sf.setNombre("Como puedo diferenciar entre "+tree.getActual()+" y "+animal+"?"+
                    "\nIngrese una pregunta:");
                activarPregunta(animal);
            }
        });
    }
    
    private void activarPregunta(String animal){
        btn.setOnAction((ev) ->{
            String pregunta=sf.getValor();
            if(!pregunta.equals("")){
                sf.clear();
                sf.setNombre("La respuesta para " + animal+ " es? (Si/No)");
                activarRespuesta(animal,pregunta);
            }
        });
    }
    
    private void activarRespuesta(String animal,String pregunta){
        btn.setOnAction((eve) ->{
            String resp=sf.getValor();
            if((resp.equalsIgnoreCase("Si") || resp.equalsIgnoreCase("No"))){
                tree.asignar(resp, pregunta, animal);
                root.getChildren().clear();
                root.getChildren().addAll(new Label("Se ha agregado " + animal+ " a las respuestas."),salir);
            }
        });
    }
    
    public VBox getRoot(){
        return root;
    }
    
    public Button getSalir(){
        return salir;
    }
}
