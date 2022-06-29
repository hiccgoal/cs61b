public class FlikTest {
    public static void main(String[] args) {
        for(int i=0;i<150;i++){
            Integer a=i;
            Integer b=i;
            System.out.println(a+" "+b+" "+System.identityHashCode(a)+" "+System.identityHashCode(b));
        }
    }
}
