import java.util.Scanner;

public class Solution43 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int sum = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(calcWays(arr, n, sum));
        }
    }

    public static long calcWays(int[] arr, int n, int sum) {
        long[] memo = new long[sum + 1];
        memo[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0 ; j--) {
                if (j >= arr[i]) {
                    memo[j] += memo[j - arr[i]];
                }
            }
        }
        return memo[sum];
    }
}
