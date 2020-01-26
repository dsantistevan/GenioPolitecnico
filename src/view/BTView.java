/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import modelo.Node;
import modelo.ArbolBinario;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author David Santistevan
 * @param <E>
 */
public final class BTView<E> extends Pane {
    private final ArbolBinario<E> tree;
    private final double radioY = 15; // Radio Y del nodo
    private final double radioX = 250; // Radio X del nodo
    private final double vGap = 50; //Separacion vertical
    private double grosor;
    private double altura;
    public BTView(ArbolBinario<E> tree) {
        this.tree = tree;
        setStatus("El arbol esta vacio.");
    }
    
    public void setStatus(String msg) {
        Label l=new Label(msg);
        l.setLayoutX(20);
        l.setLayoutY(20);
        getChildren().add(l);
    }
    
    public void mostrarArbol() {
        this.getChildren().clear(); // Elimina los elementos
        if (!tree.isEmpty()) {
            // Muestra el arbol de forma recursiva
            if(getWidth()/Math.pow(2,tree.getRoot().getAltura())>radioX*35)
                grosor=getWidth();
            else
                grosor=radioX*Math.pow(2,tree.getRoot().getAltura())*35;
            mostrarArbol(tree.getRoot(), grosor/2, 0+vGap, grosor/4);
            altura=tree.getRoot().getAltura()*(vGap+2*radioY);
        }
        else setStatus("El arbol esta vacio.");
    }

    public double getGrosor(){
        return grosor;
    }
    
    public double getAltura(){
        return altura;
    }
    
    /* Muestra el subarbol en la posicion (x, y) */
    private void mostrarArbol(Node<E> nodo, double x, double y,double hGap) {
        if (nodo.hasLeft()) {
            // Dibuja una linea hacia el nodo izquierdo
            Line line=new Line(x - hGap, y + vGap, x, y);
            line.setStroke(Color.DARKBLUE);
            getChildren().add(line);
            // Dibuja el arbol izquierdo de forma recursiva
            mostrarArbol(nodo.getLeft(), x - hGap, y + vGap, hGap / 2);
        }
        if (nodo.hasRight()) {
            // Dibuja una linea hacia el nodo derecho
            Line line=new Line(x + hGap, y + vGap, x, y);
            line.setStroke(Color.DARKBLUE);
            getChildren().add(line);
            // Dibuja el arbol derecho de forma recursiva
            mostrarArbol(nodo.getRight(), x + hGap, y + vGap, hGap / 2);
        }
        String s=nodo.getValor().toString();
        // Dibuja el nodo respectivo
        Ellipse circle = new Ellipse(x, y, s.length()*5 ,radioY);
        circle.setFill(Color.MEDIUMSLATEBLUE);
        circle.setStroke(Color.DARKBLUE);
        
        int i= s.length()>2 ? 2+s.length() : 2;
        getChildren().addAll(circle, new Text(x - i*2, y + 4, s));
    }
    
    public ArbolBinario<E> getTree(){
        return tree;
    }

}