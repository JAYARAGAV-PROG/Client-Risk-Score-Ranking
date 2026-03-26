import java.util.Arrays;

/**
 * Problem 6: Risk Threshold Binary Lookup
 * Scenario: Binary search optimal risk bands in sorted client risk table.
 */
public class RiskThresholdBinaryLookup {

    public static void main(String[] args) {
        int[] sortedRisks = {10, 25, 50, 100};
        int threshold = 30;

        System.out.println("Sorted risks: " + Arrays.toString(sortedRisks));
        System.out.println("Target threshold: " + threshold);

        // 1. Linear Search (unsorted/generic)
        int linearResult = linearSearch(sortedRisks, threshold);
        System.out.println("Linear Search: " + (linearResult != -1 ? "Found at index " + linearResult : "Not found"));

        // 2. Binary Search for Floor and Ceiling
        int floor = findFloor(sortedRisks, threshold);
        int ceiling = findCeiling(sortedRisks, threshold);

        System.out.println("Binary floor(" + threshold + "): " + (floor != -1 ? floor : "None"));
        System.out.println("Binary ceiling(" + threshold + "): " + (ceiling != -1 ? ceiling : "None"));

        // 3. Binary Search for Insertion Point (lower_bound style)
        int insertionPoint = findInsertionPoint(sortedRisks, threshold);
        System.out.println("Binary insertion point for " + threshold + ": Index " + insertionPoint);
    }

    /**
     * Linear Search: Simple O(n) scan.
     */
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Search comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Linear Search comparisons: " + comparisons);
        return -1;
    }

    /**
     * Finds the largest value <= target.
     * Floor: Max(x in arr | x <= target)
     */
    public static int findFloor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int floor = -1;
        int comps = 0;

        while (low <= high) {
            comps++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                System.out.println("Binary floor comps: " + comps);
                return arr[mid];
            } else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Binary floor comps: " + comps);
        return floor;
    }

    /**
     * Finds the smallest value >= target.
     * Ceiling: Min(x in arr | x >= target)
     */
    public static int findCeiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ceiling = -1;
        int comps = 0;

        while (low <= high) {
            comps++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                System.out.println("Binary ceiling comps: " + comps);
                return arr[mid];
            } else if (arr[mid] > target) {
                ceiling = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println("Binary ceiling comps: " + comps);
        return ceiling;
    }

    /**
     * Finds the index where target should be inserted to maintain order.
     * Equivalent to lower_bound in C++ (first element >= target).
     */
    public static int findInsertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
