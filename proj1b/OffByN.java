public class OffByN implements CharacterComparator {
    //    A class for off-by-N comparators.
    private int goal;

    public OffByN(int i) {
        goal = i;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == goal || diff == -goal;
    }
}
