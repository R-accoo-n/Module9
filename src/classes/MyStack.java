package classes;

import java.util.NoSuchElementException;

public class MyStack<T> {
    private Node<T> first;
    private int n;

    private static class Node<T> {
        private T data;
        private Node<T> next;
    }

    public MyStack() {
        this.first = null;
        this.n = 0;
    }

    public void push(T item) {
        Node<T> previous = this.first;
        this.first = new Node<>();
        this.first.data = item;
        this.first.next = previous;
        this.n++;
    }

    public void remove (T data){
        if (isEmpty()) throw new NoSuchElementException();
        Node<T> iterated = this.first;
        while (iterated != null){
            if(iterated.data == data){
                this.first = this.first.next;
                this.n--;
                break;
            }else {
                iterated = iterated.next;
            }
        }
    }

    public void clear(){
        this.first = null;
        this.n = 0;
    }

    public int size() {
        return this.n;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.first.data;
    }

    public T pop() {
        if (isEmpty()) throw new NoSuchElementException();
        T item = this.first.data;
        this.first = this.first.next;
        this.n--;
        return item;
    }

}
