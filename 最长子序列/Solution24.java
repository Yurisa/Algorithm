import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution24 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i]=in.nextInt();
            }
            List<Integer> result = MaxSubArray(arr);
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if (i + 1 < result.size()) {
                    System.out.print(" ");
                }
            }
        }
    }

    public static List<Integer> MaxSubArray(int[] arr) {
        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = -1;
        int curSum = 0;
        int maxSum = arr[0];
        while (start < arr.length) {
            if (end + 1 < arr.length && arr[end + 1] <= curSum + arr[end + 1]) {
                curSum += arr[++end];
            } else {
                curSum -= arr[start++];
            }
            if (curSum > maxSum) {
                result.clear();
                maxSum = curSum;
                result.add(maxSum);
                result.add(arr[start]);
                result.add(arr[end]);
            }

        }
        return result;
    }
}
