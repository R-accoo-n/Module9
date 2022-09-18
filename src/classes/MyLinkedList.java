package classes;

import java.util.NoSuchElementException;

public class MyLinkedList <T> {
    Node head = null;

    public MyLinkedList<T> add(T data)
    {
        Node newNode = new Node(data);

        if (this.head == null) {
            this.head = newNode;
        }
        else {
            Node last = this.head;
            while (last.next != null) {
                last = last.next;
            }
            newNode.previous = last;
            last.next = newNode;
        }

        return this;
    }

    public  MyLinkedList<T> remove(int index)
    {
        Node currNode = this.head;

        if (index == 0 && currNode != null) {
            this.head = currNode.next;

            return this;
        }

        int iter = 0;

        while (currNode != null) {

            if (iter == index) {
                currNode.previous = currNode.next;
                break;
            }
            else {
                currNode = currNode.next;
                iter++;
            }
        }

        if (currNode == null) {
            throw new IndexOutOfBoundsException();
        }

        return this;
    }

    public void clear(){
        this.head.next = null;
    }

    public int size(){
        Node currNode = this.head;
        int iter = 0;

        while (currNode != null) {
            iter++;
            currNode = currNode.next;
        }

        return iter;
    }

    public T get(int index){
        Node currNode = this.head;
        int iter = 0;

        while (currNode != null) {
            if (iter == index) {
                break;
            }
            else {
                currNode = currNode.next;
                iter++;
            }
        }

        if(currNode == null){
            throw new IndexOutOfBoundsException();
        }else{
            return currNode.data;
        }


    }

    public void printList()
    {
        Node currNode = this.head;

        System.out.print("LinkedList: ");

        while (currNode != null) {
            System.out.print(currNode.data + " <-> ");

            currNode = currNode.next;
        }

        System.out.println();
    }

    public MyLinkedList<T> remove(T entry) {                               //used in MyHashMap realisation
        Node currNode = this.head;

        while (currNode != null) {

            if (entry == currNode.data) {
                currNode.previous = currNode.next;
                break;
            }
            else {
                currNode = currNode.next;
            }
        }

        if (currNode == null) {
            throw new NoSuchElementException();
        }

        return this;
    }

    private class Node {
        T data;
        Node next;
        Node previous;

        Node(T d) { data = d; }
    }
}
