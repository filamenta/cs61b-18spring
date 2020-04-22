public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int beginPoint;
    private int endPoint;
    private final int INIT = 8;

    public ArrayDeque() {
        items = (T[]) new Object[INIT];
        size = 0;
        beginPoint = 0;
        endPoint = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int minus(int index) {
        return Math.floorMod(index - 1, items.length);
    }


    private int plus(int index) {
        return Math.floorMod(index + 1, items.length);
    }

    private int plus(int index, int length) {
        return Math.floorMod(index + 1, length);
    }

    private void resize() {
        if (size == items.length) {
            expand();
        } else if (size < items.length / 4 && items.length > 8) {
            shrink();
        }
    }

    private void expand() {
        resizeHelper(items.length * 2);
    }

    private void shrink() {
        resizeHelper(items.length / 2);
    }

    private void resizeHelper(int resize) {
        T[] temp = items;
        items = (T[]) new Object[resize];
        int newBeginPoint = 0;
        int newEndPoint = 0;
        for (int i = beginPoint, flag = 0; flag != temp.length; i = plus(i, temp.length), flag++) {
            items[newEndPoint] = temp[i];
            newEndPoint++;
        }
        beginPoint = newBeginPoint;
        endPoint = newEndPoint;
    }

    /**
     *  invariants:
     *      通过minusOne()方法确定nextFirst，(nextFirst-1)%items.length
     *      即nextFirst的下一个位置
     *      eg. (0 - 1) % 8 = 7
     *
     * */
    public void addFirst(T item) {
        resize();
        beginPoint = minus(beginPoint);
        items[beginPoint] = item;
        size++;
    }

    private T getFirst() {
        return items[beginPoint];
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        resize();
        T res = getFirst();
        items[beginPoint] = null;
        beginPoint = plus(beginPoint);
        size--;
        return res;
    }

    public void addLast(T item) {
        resize();
        items[endPoint] = item;
        endPoint = plus(endPoint);
        size++;
    }

    private T getLast() {
        return items[minus(endPoint)];
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        resize();
        T res = getLast();
        endPoint = minus(endPoint);
        items[endPoint] = null;
        size--;
        return res;
    }

    public void printDeque() {
        for (int i = beginPoint; i != endPoint; i = plus(i)) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
    }

    public T get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        i = Math.floorMod(beginPoint + i, items.length);
        return items[i];
    }
}
