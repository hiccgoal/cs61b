import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    private int testNum = 300;
    @Test
    public void test() {
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> stdArray = new ArrayDequeSolution<>();
        StringBuilder msg = new StringBuilder();
        int elemNum = 0;

        for (int i = 0; i < testNum; i++) {
//            if (i % 5 == 0) {
//                msg.append("size()\n");
//                assertEquals(msg.toString(), stdArray.size(), testArray.size());
//            }
            double flag = StdRandom.uniform();
            if (flag < 0.25) {
                testArray.addFirst(i);
                stdArray.addFirst(i);
                msg.append("addFirst(" + i + ")\n");
                elemNum ++;
                assertEquals(msg.toString(), stdArray.get(0), testArray.get(0));
            } else if (flag < 0.5) {
                testArray.addLast(i);
                stdArray.addLast(i);
                msg.append("addLast(" + i + ")\n");
                elemNum ++;
                assertEquals(msg.toString(), stdArray.get(elemNum - 1), testArray.get(elemNum - 1));
            } else if (flag < 0.75) {
                if (stdArray.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), testArray.isEmpty());
                    continue;
                }
                Integer testElem = testArray.removeFirst();
                Integer stdElem = stdArray.removeFirst();
                msg.append("removeFirst()\n");
                elemNum --;
                assertEquals(msg.toString(), stdElem, testElem);
            } else {
                if (stdArray.isEmpty()) {
                    msg.append("isEmpty()\n");
                    assertTrue(msg.toString(), testArray.isEmpty());
                    continue;
                }
                Integer testElem = testArray.removeLast();
                Integer stdElem = stdArray.removeLast();
                msg.append("removeLast()\n");
                elemNum --;
                assertEquals(msg.toString(), stdElem, testElem);
            }
        }
    }
}