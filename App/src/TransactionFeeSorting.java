import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s:%.1f@%s]", id, fee, timestamp);
    }
}

public class TransactionFeeSorting {
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Input transactions: " + transactions);

        // Problem 1: Transaction Fee Sorting for Audit Compliance
        // Bubble Sort for small batches (<= 100)
        bubbleSort(new ArrayList<>(transactions));

        // Insertion Sort for medium batches (100-1,000) - Sorting by fee + timestamp
        insertionSort(new ArrayList<>(transactions));

        // Flags high-fee outliers (> $50)
        identifyOutliers(transactions);
    }

    // Bubble Sort: adjacent swaps, early termination.
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int swaps = 0;
        int passes = 0;
        for (int i = 0; i < n - 1; i++) {
            passes++;
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // Swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break; // Early termination
        }
        System.out.println("BubbleSort (fees): " + list + " // " + passes + " passes, " + swaps + " swaps");
    }

    // Insertion Sort: building sorted subarray, shift operations.
    // Requirement: Sort by fee + timestamp
    public static void insertionSort(List<Transaction> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Sort by fee ascending. If fees are equal, sort by timestamp ascending.
            while (j >= 0 && compareTransactions(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        System.out.println("InsertionSort (fee+ts): " + list);
    }

    private static int compareTransactions(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    public static void identifyOutliers(List<Transaction> list) {
        boolean found = false;
        StringBuilder outliers = new StringBuilder("High-fee outliers: ");
        for (Transaction t : list) {
            if (t.fee > 50.0) {
                outliers.append(t.id).append(" ");
                found = true;
            }
        }
        if (!found) outliers.append("none");
        System.out.println(outliers);
    }
}
