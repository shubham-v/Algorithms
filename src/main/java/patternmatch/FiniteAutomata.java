package patternmatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Time Complexity: O(n)
 */
public class FiniteAutomata {

    private static final int COUNT_OF_CHARACTERS = 256;

    List<Integer> getOccurrences(String text, String pattern) {
        return this.getOccurrences(text.toCharArray(), pattern.toCharArray());
    }

    List<Integer> getOccurrences(char[] text, char[] pattern) {
        int[][] finiteAutomataTable = this.getFiniteAutomataTable(pattern);

        List<Integer> occurrences = new ArrayList<>();
        int state = 0;
        for (int i = 0; i < text.length; i++) {
            state = finiteAutomataTable[state][text[i]];
            if (state == pattern.length) {
                occurrences.add(i - pattern.length + 1);
            }
        }
        return occurrences;
    }

    int[][] getFiniteAutomataTable(char[] pattern) {
        int[][] finiteAutomata = new int[pattern.length + 1][COUNT_OF_CHARACTERS];
        for (int state = 0; state <= pattern.length; state++) {
            for (int c = 0; c < COUNT_OF_CHARACTERS; c++) {
                finiteAutomata[state][c] = this.getNextState(pattern, state, c);
            }
        }
        return finiteAutomata;
    }

    int getNextState(char[] pattern, int state, int c) {
        if (state < pattern.length && c == pattern[state]) {
            return state + 1;
        }
        for (int ns = state; ns > 0; ns--) {
            if (pattern[ns - 1] == c) {
                for (int i = 0; i < ns - 1; i++) {
                    if (pattern[i] != pattern[state - ns + 1]) {
                        break;
                    }
                    if (i == ns - 1) {
                        return ns;
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(
                new FiniteAutomata().getOccurrences("AAAAABAAAABA", "AAAA")
        );
    }

}
