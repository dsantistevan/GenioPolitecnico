/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import data.GenioData;
import excepciones.AdivinadoException;
import excepciones.RespuestaIncorrectaException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author David Santistevan
 * @param <E>
 */
public class ArbolBinario<E> {
    
    private Node root;
    private Node nodoActual;
    private Node nodoPrevio;
    
    public ArbolBinario(){
        this(null);
    }
    
    public ArbolBinario(Node root){
        this.root=root;
        nodoActual=this.root;
    }
    
    public Node<E> getRoot(){
        return root;
    }
    
    public boolean isEmpty(){
        return root==null;
    }
    
    public boolean setRoot(E element){
        if(isEmpty() && element!=null){
            root=new Node(element);
            return true;
        }
        return false;
    }
    
    
    public int height(){
        return height(root);
    }
    
    /**
     *
     * @param n
     * @return
     */
    private int height(Node n){
        if(n==null)
            return 0;
        return 1+Math.max(height(n.getLeft()), height(n.getRight()));
    }
    
    private int contarHojas(Node n){
        if(n==null)
            return 0;
        else if(n.isEmpty())
            return 1;
        return contarHojas(n.getLeft())+contarHojas(n.getRight());
    }
    
    public int contarHojas(){
        return contarHojas(root);
    }

    
    private Node searchNode(E data,Node n){
        if(data==null||n==null||n.isEmpty())
            return null;
        if(data.equals(n.valor))
            return n;
        Node<E> nuevo=searchNode(data,n.getLeft());
        return nuevo==null ? searchNode(data,n.getRight()) : nuevo;   
    }
    
    private boolean esLleno(Node n){
        if(n==null)
            return true;
        else if(!n.isFull()&&!n.isEmpty())
            return false;
        else
            return esLleno(n.getLeft())&&esLleno(n.getRight())&&
                    height(n.getLeft())==height(n.getRight());
            
    }
    
    public boolean esLleno(){
        return esLleno(root);
    }
    
    public String posOrden(){
        return posOrden(root);
    }
    
    private String posOrden(Node n){
        if(n!=null){
            StringBuilder sb=new StringBuilder();
            sb.append(posOrden(n.getLeft()));
            sb.append(posOrden(n.getRight()));
            String prefijo = n.esRespuesta() ? "#R " : "#P ";
            sb.append(prefijo).append(n.valor.toString()).append("\n");
            return sb.toString();
        }
        return "";
    }
       
    public String preOrden(){
        return preOrden(root);
    }
    
    private String preOrden(Node n){
        if(n!=null){
            StringBuilder sb=new StringBuilder();
            sb.append(n.valor.toString());
            sb.append(preOrden(n.getLeft()));
            sb.append(preOrden(n.getRight()));
            return sb.toString();
        }
        return "";
    }
    
 
    
    public E getPrevio(){
        return (E) nodoPrevio.getValor();
    }
   
    
    public void respuesta(String resp) throws AdivinadoException, RespuestaIncorrectaException{
        if(resp.equalsIgnoreCase("Si")&&!nodoActual.esRespuesta()){
            nodoPrevio=nodoActual;
            nodoActual=nodoActual.getLeft();
        }
        else if(resp.equalsIgnoreCase("No") && !nodoActual.esRespuesta()){
            nodoPrevio=nodoActual;
            nodoActual=nodoActual.getRight();
            
        }else if(resp.equalsIgnoreCase("Si")){
            throw new AdivinadoException();
        }else{
            throw new RespuestaIncorrectaException();
            //agregar(sc,nodoPrevio,nodoActual);
        }
    }
    
    public void reiniciar(){
        nodoActual=root;
        nodoPrevio=null;
    }
    
    
    
    private void asignar(Node padre,Node hijo,String respuesta,String pregunta,String animal){
        Node nodo=new Node(pregunta);
        if(respuesta.equalsIgnoreCase("Si")){
            nodo.setLeft(new Node(animal));
            nodo.setRight(hijo);
        }else{
            nodo.setRight(new Node(animal));
            nodo.setLeft(hijo);
        }
        if(padre.getLeft().equals(hijo))
                padre.setLeft(nodo);
            else
                padre.setRight(nodo);
        GenioData.guardarArbol(this);
    }
    
    public void asignar(String respuesta,String pregunta,String animal){
        asignar(nodoPrevio,nodoActual,respuesta, pregunta,animal);
    }
    
    public void guardarArbol(BufferedWriter bw) throws IOException{
        guardarArbol(bw,root);
    }
    
    private void guardarArbol(BufferedWriter bw,Node<E> n) throws IOException{
        if(n!=null){
            bw.write(posOrden(n.getLeft()));
            bw.write(posOrden(n.getRight()));
            String prefijo = n.esRespuesta() ? "#R " : "#P ";
            bw.write(prefijo + n.valor.toString());
            bw.newLine();
        }
    }
    
    public E getActual(){
        return (E) nodoActual.getValor();
    }
    
}
