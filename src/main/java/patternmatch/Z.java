package patternmatch;

import java.util.ArrayList;
import java.util.List;

public class Z {

    public List<Integer> getOccurrences(String text, String pattern) {
        return this.getOccurrences(text.toCharArray(), pattern.toCharArray());
    }

    private List<Integer> getOccurrences(char[] text, char[] pattern) {
        List<Integer> occurrences = new ArrayList<>();
        char[] concat = this.concat(text, pattern);
        int[] Z = this.getZ(concat);
        for (int i = 0; i < Z.length; i++) {
            if (Z[i] == pattern.length) {
                occurrences.add(i);
            }
        }
        return occurrences;
    }

    private int[] getZ(char[] input) {
        int[] Z = new int[input.length];
        int L = 0, R = 0;
        for (int i = 1; i < input.length; i++) {
            if (i > R) {
                L = R = i;
                while (R < input.length && input[R-L] == input[R]) R++;
                Z[i] = R-L; R--;
            } else {
                int k = i-L;
                if (Z[k] < R-i+1) Z[i] = Z[k];
                else {
                    L = i;
                    while (R < input.length && input[R-L] == input[R]) R++;
                    Z[i] = R-L; R--;
                }
            }
        }
        return Z;
    }

    private char[] concat(char[] a, char[] b) {
        char[] c = new char[a.length + 1 + b.length];
        int i;
        for (i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        c[i++] = '$';
        for (; i < c.length; i++) {
            c[i] = b[i - a.length - 1];
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(
                new Z().getOccurrences("AAAAABAAAABAAAA", "AAAA")
        );
    }

}
