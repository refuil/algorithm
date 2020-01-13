package b_note;

public class CircleQueue<E> {
    private int[] data;
    private int size;
    private int head;
    private int tail;

    public CircleQueue(int k){
        this.data = new int[k];
        this.size = 0;
        this.head = -1;
        this.tail = -1;
    }

    //坐标加1，再对数组长度取余。这是数组能够环形的关键
    public boolean enQueue(int value){
        if(size == data.length){
            return false;
        }
        tail = (tail+1)% data.length;
        data[tail] = value;
        if(size == 0){
            head = tail;
        }
        size ++;
        return true;
    }

    public boolean deQueue(){
        if(size == 0){
            return false;
        }
        head = (head + 1) % data.length;
        size --;
        return true;
    }

    public int Front(){
        if(size ==0)
            return -1;
        return data[head];
    }

    public int Rear(){
        if(size ==0) return -1;
        return data[tail];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == data.length;
    }

}


