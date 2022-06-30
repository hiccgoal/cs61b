public class OffByOne implements CharacterComparator {
    //    A class for off-by-1 comparators.

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == 1 || diff == -1;
    }
}
