import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /** 测试次数 */
    private int testNum = 500;


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
//                assertEquals(msg.toString(), b.get(0), a.get(0));
            } else if (randomNum < 0.5) {
                a.addLast(i);
                b.addLast(i);
                elemNum ++;
                msg.append("addLast(" + i + ")\n");
//                assertEquals(msg.toString(), b.get(elemNum - 1), a.get(elemNum - 1));
            } else if (randomNum < 0.75) {
                if (b.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), a.isEmpty());
                    continue;
                }
                Integer A = a.removeFirst();
                Integer B = b.removeFirst();
                elemNum --;
                msg.append("RemoveFirst()\n");
                assertEquals(msg.toString(), B, A);
            } else {
                if (b.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), a.isEmpty());
                    continue;
                }
                Integer A = a.removeLast();
                Integer B = b.removeLast();
                elemNum --;
                msg.append("RemoveLast()\n");
                assertEquals(msg.toString(), B, A);
            }
        }
    }
}
