package patternmatch;

/**
 * Time Complexity: O(n)
 */
public class KnuthMorrisPratt {

    int getOccurrences(String text, String pattern) {
        return this.getOccurrences(text.toCharArray(), pattern.toCharArray());
    }

    int getOccurrences(char[] text, char[] pattern) {
        int[] longestPrefixSuffix = this.getLongestPrefixSuffix(pattern);
        int i = 0, j = 0, count = 0;
        while (i < text.length) {
            if (text[i] == pattern[j]) {
                j++;
                i++;
            }
            if (j == pattern.length) {
                count++;
                j = longestPrefixSuffix[j-1];
            } else if (i < text.length && text[i] != pattern[j]) {
                if (j != 0) {
                    j = longestPrefixSuffix[j-1];
                } else {
                    i++;
                }
            }
        }
        return count;
    }

    int[] getLongestPrefixSuffix(char[] pattern) {
        int[] longestPrefixSuffix = new int[pattern.length];
        int previousLongestPrefixLength = 0;
        int i = 1;
        while (i < pattern.length) {
            if (pattern[i] == pattern[previousLongestPrefixLength]) {
                previousLongestPrefixLength++;
                longestPrefixSuffix[i] = previousLongestPrefixLength;
                i++;
            } else {
                if (previousLongestPrefixLength != 0) {
                    previousLongestPrefixLength = longestPrefixSuffix[previousLongestPrefixLength - 1];
                } else {
                    longestPrefixSuffix[i] = previousLongestPrefixLength;
                    i++;
                }
            }
        }
        return longestPrefixSuffix;
    }

    public static void main(String[] args) {
        System.out.println(
                new KnuthMorrisPratt().getOccurrences("AAAAABAAABA", "AAAA")
        );
    }

}
