import java.util.Scanner;

public class Solution20 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(LongestPalindrome(str));
    }
    public static int LongestPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int max = 0;
        int c = 0;
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; (i - j >= 0) && (i + j < len) ; j++) {
                if (chars[i - j] != chars[i +j]) {
                    break;
                }
                c = 2*j + 1;
            }
            if (c > max) {
                max = c;
            }
            for (int j = 0; (i - j >= 0) && (i + j + 1 < len) ; j++) {
                if (chars[i - j] != chars[i +j + 1]) {
                    break;
                }
                c = 2*j + 2;
            }
            if (c > max) {
                max = c;
            }
        }
        return max;
    }
}
