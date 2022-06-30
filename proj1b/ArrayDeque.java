public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int left = 0;
    private int right = 0;
    private int capacity = 8;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        left = right = 0;
    }

//    public ArrayDeque(ArrayDeque other) {
//        capacity = other.capacity;
//        System.arraycopy(other.items, 0, items, 0, capacity);
//        left = other.left;
//        right = other.right;
//    }
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(capacity * 2);
        }
        left = (left - 1 + capacity) % capacity;
        items[left] = item;
    }

    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(capacity * 2);
        }
        items[right] = item;
        right = (right + 1) % capacity;

    }
    @Override
    public boolean isEmpty() {
        return left == right;
    }

    @Override
    public int size() {
        return (right - left + capacity) % capacity;
    }

    @Override
    public void printDeque() {
        if (right > left) {
            for (int i = left; i < right; i++) {
                System.out.print(items[i] + " ");
            }
        } else if (left > right) {
            for (int i = left; i < capacity; i++) {
                System.out.print(items[i] + " ");
            }
            for (int j = 0; j < right; j++) {
                System.out.print(items[j] + " ");
            }
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = items[left];
        items[left] = null;
        left = (left + 1) % capacity;
        if (isLowUsageRate()) {
            resize(capacity / 2);
        }
        return res;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        right = (right - 1 + capacity) % capacity;
        T res = items[right];
        items[right] = null;
        if (isLowUsageRate()) {
            resize(capacity / 2);
        }
        return res;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        if (isEmpty()) {
            return null;
        } else if (right > left) {
            return items[left + index];
        } else {
            if (index + left < capacity) {
                return items[index + left];
            } else {
                return items[(index + left) % capacity];
            }
        }
    }

    private boolean isFull() {
        return capacity - 1 == size();
    }

    private void resize(int newSize) {
        int size = size();
        T[] newArray = (T[]) new Object[newSize];
        if (right > left) {
            System.arraycopy(items, left, newArray, 0, size);
        } else if (left > right) {
            System.arraycopy(items, left, newArray, 0, capacity - left);
            System.arraycopy(items, 0, newArray, capacity - left, size + left - capacity);
        }
        left = 0;
        right = size;
        items = newArray;
        capacity = newSize;
    }

    private boolean isLowUsageRate() {
        return capacity >= 16 && (double) size() / capacity < 0.25;
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> a = new  ArrayDeque<Integer>();
//        System.out.println(a.isEmpty());
//        a.addFirst(1); //096151754
//        a.addLast(5);
//        a.addFirst(6);
//        a.addLast(1);
//        a.addLast(7);
//        a.addFirst(9);
//        a.addLast(5);
//        a.addLast(4);
//        a.addFirst(0);
////        a.printDeque();
//        int test = a.get(5);
////        System.out.print(test);
//        int b, c, d, e, f, g;
//        b = a.removeLast();//09615175
//        c = a.removeFirst();//9615175
//        d = a.removeLast();//961517
//        e = a.removeLast();//96151
//        f = a.removeFirst();//6151
//        g = a.removeFirst();//151
//        System.out.println(a.isEmpty());
//        a.printDeque();
//    }
}
