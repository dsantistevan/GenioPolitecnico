/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
    
    private Node searchNode(E data){
        return searchNode(data,root);
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
    
    private void resolver(Scanner sc,Node<E> nodo,Node<E> padre){
        System.out.println(nodo.getValor());
        String resp=sc.nextLine();
        while(!(resp.equalsIgnoreCase("Si") || resp.equalsIgnoreCase("No"))){
            System.out.println("Ingrese por favor un valorvalido");
            resp=sc.nextLine();
        }
        if(resp.equalsIgnoreCase("Si")&&!nodo.esRespuesta())
            resolver(sc,nodo.getLeft(),nodo);
        else if(resp.equalsIgnoreCase("No") && !nodo.esRespuesta())
            resolver(sc,nodo.getRight(),nodo);
        else if(resp.equalsIgnoreCase("Si")){
            System.out.println("He adivinado");
        }else{
            agregar(sc,padre,nodo);
        }
    }
    
    public void resolver(Scanner sc){
        resolver(sc,root,null);
    }

    private void agregar(Scanner sc,Node<E> padre,Node<E> hijo) {
        System.out.println("Mi pregunta fue: "+padre.getValor()+
                "\nCual fue su respuesta?");
        String animal=sc.nextLine();
        System.out.println("Como puedo diferenciar entre "+hijo.getValor()+" y "+animal+"?"+
                "\nIngrese una pregunta:");
        String pregunta=sc.nextLine();
        System.out.println("La respuesta para " + animal+ " es? (Si/No)");
        String resp=sc.nextLine();
        while(!(resp.equalsIgnoreCase("Si") || resp.equalsIgnoreCase("No"))){
            System.out.println("Ingrese por favor un valorvalido");
            resp=sc.nextLine();
        }
        asignar(padre,hijo,resp,pregunta,animal);
    }
    
    public void respuesta(String resp){
        if(resp.equalsIgnoreCase("Si")&&!nodoActual.esRespuesta()){
            nodoPrevio=nodoActual;
            nodoActual=nodoActual.getLeft();
        }
        else if(resp.equalsIgnoreCase("No") && !nodoActual.esRespuesta()){
            nodoPrevio=nodoActual;
            nodoActual=nodoActual.getRight();
            
        }else if(resp.equalsIgnoreCase("Si")){
            System.out.println("He adivinado");
        }else{
            //agregar(sc,nodoPrevio,nodoActual);
        }
    }
    
    public void reiniciar(){
        nodoActual=root;
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
