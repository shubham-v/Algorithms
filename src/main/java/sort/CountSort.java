package sort;

import java.util.Arrays;

// Time : O(n + k) θ(n + k) Ω(n + k)
// Space: O(k)
public class CountSort {

    public void sort(int[] A) {
        int min = A[0], max = A[0], i;
        for (i = 1; i < A.length; ++i) {
            min = Math.min(A[i], min);
            max = Math.max(A[i], max);
        }

        int[] f = new int[max - min + 1];

        for (int a : A)
            f[a - min]++;

        int ai = 0;
        for (i = 0; i < f.length; ++i)
            while (f[i]-- > 0)
                A[ai++] = i + min;
    }

    public static void main(String[] args) {
        int[] A = new int[] {-1, 1, 5, 2, 1, 3, -1000, 0, 10, 100, 1000, 101, 110};
        new CountSort().sort(A);
        Arrays.stream(A).forEach(e -> System.out.print(e + " "));
    }

}
