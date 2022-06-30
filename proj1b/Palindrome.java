public class Palindrome {
    //    A class for palindrome operations.
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }
// solution1:
//    public boolean isPalindrome(String word) {
//        if (word.length() == 0 || word.length() == 1) {
//            return true;
//        }
//        int j = word.length() - 1;
//        for (int i = 0; i < j; i++, j--) {
//            if (word.charAt(i) != word.charAt(j)) {
//                return false;
//            }
//        }
//        return true;
//    }

// solution2:
    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
//        if (wordDeque.isEmpty() || wordDeque.size() == 1) {
//            return true;
//        }
        while (wordDeque.size() > 1) {
            char i = wordDeque.removeFirst();
            char j = wordDeque.removeLast();
            if (i != j) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() > 1) {
            char i = wordDeque.removeFirst();
            char j = wordDeque.removeLast();
            if (!cc.equalChars(i, j)) {
                return false;
            }
        }
        return true;
    }
}
