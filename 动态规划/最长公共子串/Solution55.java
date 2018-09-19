import java.util.Scanner;

public class Solution55 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s1 = sc.next();
            String s2 = sc.next();

            getcommenstrLength(s1,s2);
        }
    }

    private static void getcommenstrLength(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] dp = new int[c1.length + 1][c2.length + 1];
        int max = 0;
        int num = 0;
        StringBuffer sb = new StringBuffer();
        //第一步，得到动态序列
        for (int i = 1; i < c1.length + 1; i++) {
            for (int j = 1; j < c2.length + 1; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    // if(dp[i][j]> max )
                    //     max = dp[i][j];             //此处不同，不必返回动态序列情况，我们想要得到的只是整个动态序列
                    //    num=i;
                }
            }
        }
        //根据题中要求，在得到dp后，根据dp定位字符串位置
        if (c1.length < c2.length)
            for (int i = 0; i < c1.length; i++)
                for (int j = 0; j < c2.length; j++) {
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        num = i;
                    }
                }
        else {
            for (int i = 0; i < c2.length; i++)
                for (int j = 0; j < c1.length; j++) {
                    if (dp[j][i] > max) {
                        max = dp[j][i];
                        num = j;
                    }
                }
        }
        for (int i = num - max; i < num; i++) {
            sb.append(c1[i]);
        }
        System.out.println(sb.toString());
    }
}
