/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author David Santistevan
 */
public class SeccionFormulario {
    private VBox vbox;
    private HBox root;
    private Label error;
    private TextField campo;
    private Label nombre;
    private ImageView img = new ImageView("imagenes/respuestaSi.png");; 
    
    public SeccionFormulario(String nom){
        vbox=new VBox();
        root = new HBox();
        
        img.setFitWidth(200);
        img.setFitHeight(307);
        
        nombre=new Label(capitalizeString(nom)+":");
        campo=new TextField();
        error=new Label("");
        error.setTextFill(Color.RED);
        
        vbox.setSpacing(7);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(nombre, campo, error);
        vbox.setAlignment(Pos.CENTER);
        
        //BackgroundImage bI = new BackgroundImage(new Image("imagenes/fondo.jpg", 480, 300, false, true),
        //BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        
        //root.setBackground(new Background(bI));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.getChildren().addAll(img, vbox);
    }
    

    public HBox getRoot() {
        return root;
    }

    public void setRoot(HBox root) {
        this.root = root;
    }

    public Label getError() {
        return error;
    }

    public void setError(Label error) {
        this.error = error;
    }

    public TextField getCampo() {
        return campo;
    }

    public void setCampo(TextField campo) {
        this.campo = campo;
    }

    public Label getNombre() {
        return nombre;
    }

    public void setNombre(Label nombre) {
        this.nombre = nombre;
    }
    
    public void setNombre(String s){
        nombre.setText(capitalizeString(s));
    }

    public ImageView getImg() {
        return img;
    }

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public void activarError(String err){
        error.setText(err);
    }
    
    private static String capitalizeString(String s){
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }
    
    public void clear() {
        campo.setText("");
    }
    
    public String getValor(){
        return campo.getText();
    }
    
    public void setValor(String s){
        campo.setText(s);
    }
}
