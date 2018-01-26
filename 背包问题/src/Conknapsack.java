public class Conknapsack {

    public static void main(String[] args) {
        double w[] = { 50, 80, 30, 40, 20, 60, 10 ,1};//物体的重量

        double v[] = { 500, 400, 210, 240, 60, 480, 900,100 };//物体的价值

        double W = 170;// 背包所能容纳的重量

        double[] x = new double[w.length];
        greedy(w, v, W, w.length, x);
        double maxValue = 0;
        for(int i = 0; i < w.length; i++){
            maxValue += x[i]*v[i];
        }
        System.out.println("最大价值为"+ maxValue);
    }

    public static  void greedy(double[] w, double[] v,double W, int n, double[] x){
        bubblesort(w, v, n);
        for(int i = 0; i < n; i++){
            x[i] = 0;
        }
        double weight = 0;
            while (weight < W){
              for (int i = 0; i < n; i++){
               if (weight + w[i] < W){
                  x[i] = 1;
                  weight = weight + w[i];
                 }else {
                   x[i] = (W - weight)/w[i];
                   weight = W;
            }
          }
        }
    }

    /**
     * 冒泡排序
     * @param w
     * @param v
     * @param n
     */

    public static void bubblesort(double[] w, double[] v, int n) {
        System.out.println("开始冒泡排序");
        if (n == 1) {
            return;
        }
        double[] arr = new double[n];
        for(int i = 0; i < n; i++){
            arr[i] = v[i]/w[i];
        }
        for (int i = 0; i < n - 1; i++) {
            double tmp = 0;
            double wtmp = 0;
            double vtmp = 0;
            for (int j = 0; j <  n - i - 1; j++) {
                    if (arr[j] <= arr[j+1]) {
                        tmp = arr[j+1];
                        arr[j+1] = arr[j];
                        arr[j] = tmp;

                        wtmp = w[j+1];
                        w[j+1] = w[j];
                        w[j] = wtmp;

                        vtmp = v[j+1];
                        v[j+1] = v[j];
                        v[j] = vtmp;
                }
            }
        }
//        System.out.println("v/m排序");
//        for(int i = 0; i < n; i++){
//            System.out.println(arr[i]);
//        }
//        System.out.println("w排序");
//        for(int i = 0; i < n; i++){
//            System.out.println(w[i]);
//        }
//        System.out.println("v排序");
//        for(int i = 0; i < n; i++){
//            System.out.println(v[i]);
//        }
    }

}
