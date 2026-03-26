import java.util.Arrays;

class Client {
    String id;
    int riskScore;
    double accountBalance;

    Client(String id, int riskScore, double accountBalance) {
        this.id = id;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return id + ":" + riskScore + " ($" + accountBalance + ")";
    }
}

public class ClientRiskScoreRanking {
    public static void main(String[] args) {
        Client[] clients = {
            new Client("clientC", 80, 5000.0),
            new Client("clientA", 20, 1000.0),
            new Client("clientB", 50, 2500.0),
            new Client("clientD", 50, 3000.0)
        };

        System.out.println("Input: " + Arrays.toString(clients));

        // Problem 2: Client Risk Score Ranking
        bubbleSortAsc(Arrays.copyOf(clients, clients.length));

        Client[] sortedDesc = insertionSortDesc(Arrays.copyOf(clients, clients.length));
        
        identifyTopRisks(sortedDesc, 3);
    }

    // Bubble Sort: ASC by riskScore
    public static void bubbleSortAsc(Client[] arr) {
        int n = arr.length;
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.println("Bubble (asc): " + Arrays.toString(arr) + " // Swaps: " + swaps);
    }

    // Insertion Sort: DESC by riskScore + accountBalance
    public static Client[] insertionSortDesc(Client[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            // Sort by riskScore descending. If equal, sort by accountBalance ascending (secondary).
            while (j >= 0 && compareClientsDesc(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        System.out.println("Insertion (desc): " + Arrays.toString(arr));
        return arr;
    }

    private static int compareClientsDesc(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            // High risk score comes first
            return Integer.compare(c2.riskScore, c1.riskScore); 
        }
        // If equal risk, lower balance first (example criteria)
        return Double.compare(c1.accountBalance, c2.accountBalance);
    }

    public static void identifyTopRisks(Client[] sortedArr, int count) {
        System.out.print("Top " + count + " risks: ");
        for (int i = 0; i < Math.min(count, sortedArr.length); i++) {
            System.out.print(sortedArr[i].id + "(" + sortedArr[i].riskScore + ")" + (i == Math.min(count, sortedArr.length) - 1 ? "" : ", "));
        }
        System.out.println();
    }
}
