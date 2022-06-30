public class LinkedListDeque<T> implements Deque<T> {
    private StuffNode sentinel;
    private int size;

    private class StuffNode {
        private StuffNode prev;
        private T item;
        private StuffNode next;

        public StuffNode(T i, StuffNode n1, StuffNode n2) {
            item = i;
            prev = n1;
            next = n2;
        }
    }

    public LinkedListDeque() {
//        (T) new Object()
        sentinel = new StuffNode((T) new Object(), null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

//    public LinkedListDeque(T x) {
//        sentinel = new StuffNode((T) new Object(), null ,null);
//        sentinel.next = new StuffNode(x, sentinel, sentinel);
//        sentinel.prev = sentinel.next;
//        size = 1;
//    }

    @Override
    public void addFirst(T item) {
        size += 1;
        sentinel.next.prev = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next = sentinel.next.prev;
    }

    @Override
    public void addLast(T item) {
        size += 1;
        sentinel.prev.next = new StuffNode(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p != sentinel) {
            if (p.next == sentinel) {
                System.out.print(p.item);
                break;
            }
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return res;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return res;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        StuffNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (size <= index) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(StuffNode node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }
}

