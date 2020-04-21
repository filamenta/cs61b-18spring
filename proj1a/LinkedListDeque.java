public class LinkedListDeque<T> {
    private class Node {
        Node prev;
        Node next;
        T item;

        public Node(Node p, T i, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    private int size;
    private Node sentinel;
    public LinkedListDeque(T i) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, i, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    /**add and remove only take constant time,
     * no loop, no recursion*/
    public void addFirst(T i) {
        Node temp = sentinel.next;
        sentinel.next = new Node(sentinel, i, temp);
        temp.prev = sentinel.next;
        size += 1;
    }
    public void addLast(T i){
        Node temp = sentinel.prev;
        sentinel.prev = new Node(temp, i, sentinel);
        temp.next = sentinel.prev;
        size += 1;
    }
    public boolean isEmpty(){
        return size==0;
    }
    /**size take constant time*/
    public int size() {
        return size;
    }
    public void printDeque() {
        int i = 0;
        while (i < size){
            sentinel = sentinel.next;
            System.out.print(sentinel.item);
            System.out.print(" ");
            i += 1;
        }
        sentinel = sentinel.next;
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return ret;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel.next;
        size -= 1;
        return ret;
    }
    /**get use iteration, not recursion*/
    public T get(int index) {
        if (index > size) {
            return null;
        }
        Node curr = sentinel;
        while (index > 0) {
            curr = curr.next;
            index -= 1;
        }
        return curr.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel, index);
    }

    private T getRecursiveHelper(Node curr, int index) {
        if (index == 0) {
            return curr.item;
        } else {
            curr = curr.next;
            return getRecursiveHelper(curr, index - 1);
        }
    }
}
