
package Ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class VentanaInicio {
    private HBox root = new HBox();
    private Button btJugar = new Button("   Jugar   ");
    private Button btSalir = new  Button("    Salir    ");

    public VentanaInicio() {
        crearVentana();
    }
    
    public void crearVentana(){
        btJugar.setStyle("-fx-background-color: #FD9900; -fx-font-weight: bold;");
        btSalir.setStyle("-fx-background-color: #FD9900; -fx-font-weight: bold;");
        
        ImageView img = new ImageView("imagenes/akinator.png");
        img.setFitWidth(300);
        img.setFitHeight(298);
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(btJugar, btSalir);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);
        
        BackgroundImage bI = new BackgroundImage(new Image("imagenes/fondo1.jpg", 480, 324, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        
        root.setBackground(new Background(bI));
        root.getChildren().addAll(img,vbox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(30);
        
    }

    public HBox getRoot() {
        return root;
    }

    public void setRoot(HBox root) {
        this.root = root;
    }

    public Button getBtJugar() {
        return btJugar;
    }

    public void setBtJugar(Button btJugar) {
        this.btJugar = btJugar;
    }

    public Button getBtSalir() {
        return btSalir;
    }

    public void setBtSalir(Button btSalir) {
        this.btSalir = btSalir;
    }
    
    
}
