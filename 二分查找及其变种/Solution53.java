public class Solution53 {
    public int findElement(int[] A, int n, int x) {
        if(A == null || n == 0){
            return -1;
        }
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == x) {
                return mid;
            } else if (A[mid] > x) {
                if (A[mid] > A [left] && x < A[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (A[mid] < A[left] && x > A[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
