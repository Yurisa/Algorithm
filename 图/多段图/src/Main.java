import java.math.BigInteger;

public class Main {
    private static int n = 16;
    private static int[][]  arr = {
            {0,5,3,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {5,0,0,1,3,6,0,0,0,0,0,0,0,0,0,0},
            {3,0,0,0,8,7,6,0,0,0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0,6,8,0,0,0,0,0,0,0},
            {0,3,8,0,0,0,0,3,5,0,0,0,0,0,0,0},
            {0,6,7,0,0,0,0,0,3,3,0,0,0,0,0,0},
            {0,0,6,0,0,0,0,0,8,4,0,0,0,0,0,0},
            {0,0,0,6,3,0,0,0,0,0,2,2,0,0,0,0},
            {0,0,0,8,5,3,8,0,0,0,0,1,2,0,0,0},
            {0,0,0,0,0,3,4,0,0,0,0,3,3,0,0,0},
            {0,0,0,0,0,0,0,2,0,0,0,0,0,3,5,0},
            {0,0,0,0,0,0,0,2,1,3,0,0,0,5,2,0},
            {0,0,0,0,0,0,0,0,2,3,0,0,0,6,6,0},
            {0,0,0,0,0,0,0,0,0,0,3,5,6,0,0,4},
            {0,0,0,0,0,0,0,0,0,0,5,2,6,0,0,3},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,4,3,0},
    };
    public static void main(String[] args) {
          shortestPath(arr);
    }

    public static void shortestPath(int[][] arr){
        int sum = 0;
        int temp = 0;
        int[] p = new int[n];
        int[] len = new int[n];
        for(int i = 1; i < n ; i++){
            int total = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++){
                if(arr[j][i] != 0){
                    if (total > arr[j][i] + len[j]){
                        total = arr[j][i] + len[j];
                        temp = j;
                    }
                    len[i] = total;     //记录到i点的最短路径
                    sum = total;
                    p[i] = temp;        //记录到i点取得最短路径的前一个点
                }
            }
        }
        int[] path = new int[7];
        path[0] = 0;
        path[6] = 15;
        for (int k = 5;k >= 1; k--){
            path[k] = p[path[k+1]];
        }
        System.out.println("最短路径为:");
        for(int m = 0; m < 6; m++){
            System.out.print(path[m]+"->");
        }
        System.out.println(15);
        System.out.println("最小代价为:" + sum);
    }
}
