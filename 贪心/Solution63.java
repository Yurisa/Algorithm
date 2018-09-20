public class Solution63 {
    public boolean canJump(int[] A) {
        int n = A.length;
        int curFast = 0;
        for (int i = 0; i < n; i++) {
            if (i <= curFast) {
                curFast = Math.max(curFast, i + A[i]);
            }
        }
        if (curFast >= n - 1) {
            return true;
        }
        return false;
    }
}
