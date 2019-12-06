import java.util.LinkedList;

public class LinkedListSet<E> implements Set<E>{
    private LinkedList<E> list;

    public LinkedListSet(){list = new LinkedList<>(); }

    @Override
    public void add(E e) {
        if(!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public void remove(E e) {
        list.remove(e);
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
