package sort;

class QuickSort {

    void ascending(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            ascending(arr, low, partitionIndex - 1);
            ascending(arr, partitionIndex + 1, high);
        }
    }

    int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                if (i < j)
                    swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);
        return i + 1;
    }

    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}