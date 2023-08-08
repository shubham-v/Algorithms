package patternmatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Time Complexity: Best Case: O(n+m) | Worst Case: O(nm)
 */
public class RabinKarp {

    private static final int COUNT_OF_CHARACTERS = 256;

    List<Integer> getOccurrences(String text, String pattern, int prime) {
        return this.getOccurrences(text.toCharArray(), pattern.toCharArray(), prime);
    }

    List<Integer> getOccurrences(char[] text, char[] pattern, int prime) {
        List<Integer> occurrences = new ArrayList<>();
        int patternHash = 0;
        int textHash = 0;
        int h = 1;

        for (int i = 0; i < pattern.length - 1; i++) {
            h = (h * COUNT_OF_CHARACTERS) % prime;
        }

        for (int i = 0; i < pattern.length; i++) {
            textHash = (COUNT_OF_CHARACTERS * textHash + text[i]) % prime;
            patternHash = (COUNT_OF_CHARACTERS * patternHash + pattern[i]) % prime;
        }

        int i, j;
        for (i = 0; i <= text.length - pattern.length; i++) {
            if (textHash == patternHash) {
                for (j = 0; j < pattern.length; j++) {
                    if (text[i + j] != pattern[j]) {
                        break;
                    }
                }
                if (j == pattern.length) {
                    occurrences.add(i);
                }
            }

            if (i < text.length - pattern.length) {
                textHash = (COUNT_OF_CHARACTERS * (textHash - text[i] * h) + text[i + pattern.length]) % prime;
                if (textHash < 0) {
                    textHash = (textHash + prime);
                }
            }
        }

        return occurrences;
    }

    public static void main(String[] args) {
        System.out.println(
                new FiniteAutomata().getOccurrences("AAAAABAAAABA", "AAAA")
        );
    }

}
