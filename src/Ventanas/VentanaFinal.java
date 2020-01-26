
package Ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class VentanaFinal {
    private VBox root;
    Button btNuevoJuego = new Button("Nuevo Juego");
    
    public VentanaFinal() {
        crearVentana();
    }
    
    public void crearVentana(){
        
        ImageView img = new ImageView("imagenes/adivino.png");
        img.setFitWidth(300);
        img.setFitHeight(298);
        Label l=new Label("Adivine");
        l.setFont(Font.font("Verdana",FontWeight.BOLD, 25));
        root = new VBox();
        root.getChildren().addAll(img,l, btNuevoJuego);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(0, 0, 20, 0));
        
        //BackgroundImage bI = new BackgroundImage(new Image("imagenes/fondo1.jpg", 480, 324, false, true),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    }

    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }  
    

    public Button getBtNuevoJuego() {
        return btNuevoJuego;
    }

    public void setBtNuevoJuego(Button btNuevoJuego) {
        this.btNuevoJuego = btNuevoJuego;
    }
    
    
}
