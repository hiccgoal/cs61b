import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /** 测试次数 */
    private int testNum = 100;

    @Test
    public void test() {
        StudentArrayDeque<Integer> a = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> b = new ArrayDequeSolution<>();
        StringBuilder msg = new StringBuilder();

        int elemNum = 0;
        for (int i = 0; i < testNum; i++) {
            double randomNum = StdRandom.uniform();
            if (randomNum < 0.25) {
                a.addFirst(i);
                b.addFirst(i);
                elemNum ++;
                msg.append("addFirst(" + i + ")\n");
                assertEquals(msg.toString(), a.get(0), b.get(0));
            } else if (randomNum < 0.5) {
                a.addLast(i);
                b.addLast(i);
                elemNum ++;
                msg.append("addLast(" + i + ")\n");
                assertEquals(msg.toString(), a.get(elemNum - 1), b.get(elemNum - 1));
            } else if (randomNum < 0.75) {
                if (a.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), b.isEmpty());
                    continue;
                }
                Integer A = a.removeFirst();
                Integer B = b.removeFirst();
                elemNum --;
                msg.append("RemoveFirst()\n");
                assertEquals(msg.toString(), A, B);
            } else {
                if (a.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), b.isEmpty());
                    continue;
                }
                Integer A = a.removeLast();
                Integer B = b.removeLast();
                elemNum --;
                msg.append("RemoveLast()\n");
                assertEquals(msg.toString(), A, B);
            }
        }
    }
}
