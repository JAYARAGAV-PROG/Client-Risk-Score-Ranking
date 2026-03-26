import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class HistoricalTradeVolumeAnalysis {
    public static void main(String[] args) {
        Trade[] trades = {
            new Trade("trade3", 500),
            new Trade("trade1", 100),
            new Trade("trade2", 300)
        };

        System.out.println("Input: " + Arrays.toString(trades));

        // Problem 3: Historical Trade Volume Analysis
        // Merge Sort (Ascending)
        Trade[] mergeSorted = mergeSort(Arrays.copyOf(trades, trades.length));
        System.out.println("MergeSort (asc): " + Arrays.toString(mergeSorted));

        // Quick Sort (Descending)
        Trade[] quickSorted = Arrays.copyOf(trades, trades.length);
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("QuickSort (desc): " + Arrays.toString(quickSorted));

        // Merge two sorted trade lists
        Trade[] morningSession = {new Trade("T4", 200), new Trade("T5", 400)};
        Trade[] afternoonSession = {new Trade("T6", 300), new Trade("T7", 500)};
        // For merging, inputs must be sorted. Let's sort them first.
        morningSession = mergeSort(morningSession);
        afternoonSession = mergeSort(afternoonSession);
        
        Trade[] merged = mergeLists(morningSession, afternoonSession);
        System.out.println("Merged Sessions: " + Arrays.toString(merged));

        // Compute total volume
        int totalVolume = 0;
        for (Trade t : merged) totalVolume += t.volume;
        System.out.println("Total volume post-sort: " + totalVolume);
    }

    // Merge Sort implementation
    public static Trade[] mergeSort(Trade[] arr) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        Trade[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        Trade[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    private static Trade[] merge(Trade[] left, Trade[] right) {
        Trade[] result = new Trade[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].volume <= right[j].volume) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }

    // Quick Sort implementation (Descending)
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        Trade pivot = arr[high]; // Lomuto partition
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Descending order: swap if volume > pivot volume
            if (arr[j].volume > pivot.volume) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Merge two sorted trade lists
    public static Trade[] mergeLists(Trade[] l1, Trade[] l2) {
        return merge(l1, l2);
    }
}
