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
public class Node<E> {
    E valor;
    Node<E> left;
    Node<E> right;

    public Node(E element){
        valor=element;
    }


    public boolean isFull(){
        return left!=null && right != null;
    }

    public boolean isEmpty(){
        return left==null && right==null;
    }




    public boolean equalsTree(Object obj) {
        if (this == obj) {
            return true;
        }
        if(!(obj instanceof Node))
            return false;
        final Node<?> other = (Node<?>) obj;
        if(!this.valor.equals(other.valor))
            return false;
        if(this.isEmpty()&&other.isEmpty())
            return true;
        else if (this.isEmpty()||other.isEmpty())
            return false;
        return this.left.equalsTree(other.left) && this.right.equalsTree(other.right);

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(!(obj instanceof Node))
            return false;
        final Node<?> other = (Node<?>) obj;
        return this.valor.equals(other.valor);

    }

    public E getValor() {
        return valor;
    }


    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }
    
    
}
