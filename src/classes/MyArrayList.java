package classes;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList <T> {
    private static final int DEF_SIZE = 10;
    private T[] array;
    private int size = DEF_SIZE;

    public MyArrayList(){
        this.array = (T[]) new Object[DEF_SIZE];
    }

    public MyArrayList(Collection<? extends T> c) {
        if (!c.isEmpty()) {
            this.array = (T[]) c.toArray();
            this.size = this.array.length;
        }
    }

    public void add(T data){
        if(this.array.length-this.size < 5){
            increaseSize();
        }
        this.array[size++] = data;
    }

    public T remove(int index){
        if(index < this.size){
            T data = this.array[index];
            this.array[index] = null;
            int tmp = index;
            while(tmp < this.size){
                this.array[tmp] = this.array[tmp+1];
                this.array[tmp+1] = null;
                tmp++;
            }
            this.size--;
            return data;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void clear(){
        Arrays.fill(this.array, null);
        this.size = 0;

    }

    public int size(){
        return this.size;
    }

    public T get(int index){
        if(index < this.size){
            return this.array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void increaseSize(){
        this.array = Arrays.copyOf(this.array, this.array.length*2);
    }

}
