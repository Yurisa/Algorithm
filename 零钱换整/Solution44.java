import java.util.Scanner;

public class Solution44 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] changes = {1, 5, 10, 25, 50};
        while (in.hasNext()) {
            int x = in.nextInt();
            System.out.println(countWays(changes, changes.length, x));
        }
    }
    public static long countWays(int[] changes, int n, int x) {
        // write code here
        long[] memo = new long[x + 1];

        memo[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = changes[i]; j <= x; j++) {
                memo[j] += memo[j - changes[i]];
            }
        }
        return memo[x];
    }
}
