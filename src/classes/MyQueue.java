package classes;

import java.util.NoSuchElementException;

public class MyQueue<T> {
    private Node<T> first;
    private Node<T> last;
    private int n;

    private static class Node<T> {
        private T data;
        private Node<T> next;
    }

    public MyQueue() {
        this.first = null;
        this.last  = null;
        this.n = 0;
    }

    public void add(T data) {
        Node<T> previous = this.last;
        this.last = new Node<>();
        this.last.data = data;
        this.last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else{
            previous.next = last;
        }
        n++;
    }

    public void remove(T data){
        if (isEmpty()) throw new NoSuchElementException();
        Node<T> iterated = this.first;
        while (iterated != this.last){
            if(iterated.data == data){
                this.first = this.first.next;
                this.n--;
                if (isEmpty()) {
                    last = null;
                }
                break;
            }else {
                iterated = iterated.next;
            }
        }
    }

    public void clear(){
        this.first = null;
        this.last = null;
        this.n = 0;
    }

    public int size() {
        return this.n;
    }

    public T peek() {
        if (isEmpty()) throw new IndexOutOfBoundsException();
        return this.first.data;
    }


    public T poll() {
        if (isEmpty()) throw new NoSuchElementException();
        T data = this.first.data;
        this.first = this.first.next;
        this.n--;
        if (isEmpty()) {
            last = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

}
