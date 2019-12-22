/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author David Santistevan
 * @param <E>
 */
public class ArbolBinario<E> {
    
    private Node root;
    
    public ArbolBinario(){
        this.root=null;
    }
    
    public ArbolBinario(Node root){
        this.root=root;
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
    
    public void posOrden(){
        posOrden(root);
    }
    
    private String posOrden(Node n){
        if(n!=null){
            StringBuilder sb=new StringBuilder();
            sb.append(posOrden(n.getLeft()));
            sb.append(posOrden(n.getRight()));
            sb.append(n.valor.toString());
            return sb.toString();
        }
        return "";
    }
    
    public void preOrden(){
        preOrden(root);
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
    
    
}
