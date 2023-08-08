package sort;

import java.util.stream.IntStream;

// Time:  O(nk) θ(nk) Ω(nk)
// Space: O(n + k)
public class RadixSort {

    public void sort(int[] A) {
        int max = IntStream.of(A).max().getAsInt(), len = A.length;
        for (int exponent = 1; max / exponent > 0; exponent *= 10)
            countingSort(A, len, exponent);
    }

    private void countingSort(int[] A, int length, int exponent) {
        int output[] = new int[length], count[] = new int[10], i;
        for (i = 0; i < length; i++)
            count[(A[i] / exponent) % 10]++;
        for (i = 1; i < 10; i++)
            count[i] += count[i-1];
        for (i = length - 1; i >= 0; i--) {
            output[count[(A[i] / exponent) % 10] - 1] = A[i];
            count[(A[i] / exponent) % 10]--;
        }
        for (i = 0; i < length; i++)
            A[i] = output[i];
    }

    public static void main(String[] args) {
        int[] A = { 5, 7, 3, 5, 10, 820, 2 };
        new RadixSort().sort(A);
        IntStream.of(A).forEach(System.out::println);
    }
}
