package b_note;

public class CircleQueue2<E> {
    public static int DEFAULT_CAPACITY=10;

    private int size;
    private int capacity;
    private int[] data;
    private int head;
    private int tail;

    public CircleQueue2(int k){
        if(k <= 0) k = DEFAULT_CAPACITY;
        this.data = new int[k];
//        this.size = 0;
        capacity = k;
        this.head = -1;
        this.tail = -1;
    }

    public boolean insertFront(int value){
        if(isFull()) return false;
        if(head -1 <0){
            head = capacity -1;
        }else{
            head--;
        }
        data[head] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value){
        if(isFull()) return false;
        data[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    public boolean deleteFront(){
        if(isEmpty()) return false;
        head = (head +1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast(){
        if(isEmpty()) return false;
        if(tail - 1 < 0){
            tail = capacity -1;
        }else{
            tail--;
        }
        size--;
        return true;
    }

    public int getFront(){
        if(size ==0)
            return -1;
        return data[head];
    }

    public int getReat(){
        if(size ==0) return -1;
        if(tail -1 < 0){
            return data[capacity -1];
        }
        return data[tail -1];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == data.length;
    }

}


