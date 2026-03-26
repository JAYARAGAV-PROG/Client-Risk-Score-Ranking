import java.util.Arrays;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "% (v:" + volatility + ")";
    }
}

public class PortfolioReturnSorting {
    public static void main(String[] args) {
        Asset[] assets = {
            new Asset("AAPL", 12.0, 0.5),
            new Asset("TSLA", 8.0, 1.2),
            new Asset("GOOG", 15.0, 0.4),
            new Asset("MSFT", 12.0, 0.3) // Same return rate as AAPL, lower volatility
        };

        System.out.println("Input: " + Arrays.toString(assets));

        // Problem 4: Portfolio Return Sorting
        // Merge Sort by returnRate (Stable)
        Asset[] mergeSorted = mergeSort(Arrays.copyOf(assets, assets.length));
        System.out.println("Merge Sort (asc): " + Arrays.toString(mergeSorted));

        // Quick Sort by returnRate DESC + volatility ASC
        Asset[] quickSorted = Arrays.copyOf(assets, assets.length);
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("Quick Sort (desc): " + Arrays.toString(quickSorted));
    }

    // Merge Sort (Stable, Ascending)
    public static Asset[] mergeSort(Asset[] arr) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        Asset[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        Asset[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    private static Asset[] merge(Asset[] left, Asset[] right) {
        Asset[] result = new Asset[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            // Stable merge: if rates equal, take from left (original order preserved)
            if (left[i].returnRate <= right[j].returnRate) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }

    // Quick Sort (Return DESC, Volatility ASC)
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // DESC returnRate, ASC volatility
            if (compareAssetsQuick(arr[j], pivot) < 0) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    private static int compareAssetsQuick(Asset a1, Asset a2) {
        if (a1.returnRate != a2.returnRate) {
            // Higher return rate first (return negative to indicate it's "smaller" in terms of ranking)
            return Double.compare(a2.returnRate, a1.returnRate);
        }
        // Lower volatility first
        return Double.compare(a1.volatility, a2.volatility);
    }
}
