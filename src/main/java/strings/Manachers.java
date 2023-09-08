package strings;

public class Manachers {
    private final String s;

    int start = 0, end = 0;

    public Manachers(String s) {
        this.s = s;
    }

    int lps(String s) {
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (i <= end)
            dp[i] = expand(s, i, i);
        }
        return 1;
    }

    int expand(String s, int l, int r) {
        while (l > -1 && r < s.length()) {
            l--;
            r++;
        }

        l++;
        r--;

        start = l;
        end = r;

        return r - l + 1;
    }
}
