import java.util.Arrays;

public class AccountIdLookup {
    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        System.out.println("Logs: " + Arrays.toString(logs));

        // Problem 5: Account ID Lookup in Transaction Logs
        // Linear Search
        linearSearch(logs, "accB");

        // Binary Search requires sorted input
        Arrays.sort(logs);
        System.out.println("Sorted logs: " + Arrays.toString(logs));
        binarySearch(logs, "accB");
    }

    public static void linearSearch(String[] arr, String target) {
        int firstIndex = -1;
        int lastIndex = -1;
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (firstIndex == -1) firstIndex = i;
                lastIndex = i;
            }
        }
        System.out.println("Linear first " + target + ": index " + firstIndex + " (" + comparisons + " comparisons)");
    }

    public static void binarySearch(String[] arr, String target) {
        int low = 0;
        int high = arr.length - 1;
        int index = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;
            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) {
                index = mid;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Count occurrences
        int count = 0;
        if (index != -1) {
            // Count left
            for (int i = index; i >= 0 && arr[i].equals(target); i--) count++;
            // Count right
            for (int i = index + 1; i < arr.length && arr[i].equals(target); i++) count++;
        }

        System.out.println("Binary " + target + ": index " + index + " (" + comparisons + " comparisons), count=" + count);
    }
}
