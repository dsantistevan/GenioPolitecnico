
package Ventanas;

import excepciones.AdivinadoException;
import excepciones.RespuestaIncorrectaException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.SeccionJuego;


public final class VentanaInicio {
    private BorderPane root;
    private HBox hb = new HBox();
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
        
        hb.setBackground(new Background(bI));
        hb.getChildren().addAll(img,vbox);
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(10));
        hb.setSpacing(30);
        root=new BorderPane();
        root.setCenter(hb);
        activarJuego();
    }

    public HBox getHb() {
        return hb;
    }

    public void setHb(HBox hb) {
        this.hb = hb;
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
    
    public BorderPane getRoot(){
        return root;
    }
    
    private void activarJuego(){
        btJugar.setOnAction((e) ->{
            SeccionJuego sj=new SeccionJuego();
            root.setCenter(sj.getRoot());
            sj.getBtn().setOnAction((ev) ->{
                try {
                    sj.responder();
                } catch (AdivinadoException ex) {
                    System.out.println("Adivine");
                    root.setCenter(hb);
                } catch (RespuestaIncorrectaException ex) {
                    VentanaNuevoIngreso vni=(new VentanaNuevoIngreso(sj.getTree()));
                    root.setCenter(vni.getRoot());
                    vni.getSalir().setOnAction((eve) ->{
                        root.setCenter(hb);
                    });
                }
            });
        });
    }
}
