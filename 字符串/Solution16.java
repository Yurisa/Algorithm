import java.util.Scanner;

public class Solution16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(reverse(str));
    }

    public static String reverse(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        int from = 0;
        int to = len - 1;
        while (from < to) {
            char t = chars[from];
            chars[from++] = chars[to];
            chars[to--] = t;
        }
        StringBuilder sb = new StringBuilder(len);
        for (char c: chars) {
            sb.append(c);
        }
        return sb.toString();
    }
}
