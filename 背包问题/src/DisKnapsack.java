import java.util.PriorityQueue;

public class DisKnapsack {
    static int w[] = { 50, 80, 30, 40, 20, 60, 10 ,1};//物体的重量

    static int v[] = { 500, 400, 210, 240, 60, 480, 900,100 };//物体的价值

    static int W = 170;// 背包所能容纳的重量

    static int n = w.length;

    static int x[] = new int[n];

    static int weight = 0;
    static int value = 0;

    static double maxValue = 0;

    public static void main(String[] args) {

//     force();
//       greedy();
      //dynamicPlaning();
       backTrack(0);
 //       approximate();
//        new DisKnapsack().getMaxValue();
        System.out.println("输出放入背包的物品的最大价值:"+maxValue);
    }
    public static void conversion(int m,int[] b)
    {
        for(int i=0;i<n;i++)
        {
            b[i] = m%2;
            m = m/2;
            if(m==0)break;
        }
    }

    /**
     * 蛮力法
     */
    public static void force() {
        int maxValue = 0;
        int[] temp = new int[n];
        for(int i = 0; i < Math.pow(2, n); i++){
            for(int j = 0; j < n; j++){
                x[j] = 0;
            }
            conversion(i,x);
            int weight = 0;
            int value = 0;
            for (int j = 0; j < n; j++){
                if (x[j] == 1){
                    weight += w[j];
                    value += v[j];
                }
                if((weight <= W)&&(value > maxValue)){
                    for(int k = 0; k < n; k++){
                        temp[k] = 0;
                    }
                    maxValue = value;
                    for (int k = 0; k < n; k++){
                        temp[k] = x[k];
                    }
                }
            }
        }
        System.out.println("输出放入背包的物品的最大价值:"+maxValue);
    }

    /**
     * 贪心算法
     */
    public static  void greedy(){
        bubblesort();
        for(int i = 0; i < n; i++){
            x[i] = 0;
        }
        double weight = 0;
        for (int i = 0; i < n; i++){
            if (weight + w[i] <=W){
                x[i] = 1;
                weight = weight + w[i];
            }else {
                x[i] = 0;
            }
        }
        for(int i = 0; i < n; i++){
            maxValue += x[i]*v[i];
        }
        System.out.println("输出放入背包的物品的最大价值"+maxValue);
    }
    /**
     * 冒泡排序
     */
    public static void bubblesort() {
        System.out.println("开始冒泡排序");
        if (n == 1) {
            return;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = v[i] / w[i];
        }
        for (int i = 0; i < n - 1; i++) {
            int tmp = 0;
            int wtmp = 0;
            int vtmp = 0;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] <= arr[j + 1]) {
                    tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;

                    wtmp = w[j + 1];
                    w[j + 1] = w[j];
                    w[j] = wtmp;

                    vtmp = v[j + 1];
                    v[j + 1] = v[j];
                    v[j] = vtmp;
                }
            }
        }
    }

    /**
     * 动态规划
     */
    public static void dynamicPlaning(){
        int[][] c = new int[n+1][W+1];
        for(int i = 0; i < n + 1; i++){
            c[i][0] = 0;
        }
        for (int j = 0; j < W + 1; j++) {
            c[0][j] = 0;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < W + 1; j++) {
                if(w[i - 1] <= j){
                    if(c[i - 1][j] < (c[i - 1][j - w[i - 1]] + v[i - 1])){
                        c[i][j] = c[i - 1][j - w[i - 1]] + v[i - 1];
                    }else{
                        c[i][j] = c[i - 1][j];
                    }
                }else {
                      c[i][j] = c[i - 1][j];
                }

            }
        }
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=W; j++) {
                System.out.print(c[i][j]+"\t");
                if(j == W){
                    System.out.println();
                }
            }
        }
        maxValue = c[n][W];
        System.out.println("输出放入背包的物品的最大价值"+maxValue);
    }

    /**
     * 回溯法
     */

    public static void backTrack(int t){
        int[] way = new int[n];
        int[] bestway = new int[n];

        //搜索到根节点
        if (t > n - 1){
            if (value > maxValue){
                maxValue = value;
                for (int i = 0; i < n; i++) {
                    bestway[i] = way[i];
                }
            }
            return;
        }

        //搜索左边节点
        if (weight + w[t] <= W){
            weight += w[t];
            value += v[t];
            way[t] = 1;
            backTrack(t+1);
            weight -= w[t];
            value -= v[t];
            way[t] = 0;
        }

        if(bound(t + 1) >= maxValue){
            backTrack(t + 1);
        }
    }
    //用于计算剩余物品的最高价值上界
    public static double bound(int k){
        double maxLeft = value;
        int leftWeight = W - weight;
        while(k <= n-1 && leftWeight > w[k]){
            leftWeight -= w[k];
            maxLeft += v[k];
            k++;
        }
        //不能装时，用下一个物品单位重量价值折算到剩余空间
        if(k <= n - 1){
            maxLeft += v[k]/w[k]*leftWeight;
        }
        return maxLeft;
    }

    /**
     * 分支限界法
     */
    public void getMaxValue(){
        int[] bestWay = new int[n];
        PriorityQueue<thingNode> pq = new PriorityQueue<>();
        thingNode initial = new thingNode();
        initial.level = -1;
        initial.upprofit = 26;
        pq.add(initial);
        while (!pq.isEmpty()){
            thingNode fatherNode = pq.poll();
            if (fatherNode.value > maxValue){
                maxValue = (int)fatherNode.Left;
                for (int i = n - 1; i >= 0; i--) {
                    bestWay[i] = fatherNode.Left;
                    fatherNode = fatherNode.father;
                }
            }else {
                //先统计其左节点信息，判断是否加入队列。
                if(w[fatherNode.level+1]+fatherNode.weight <= W){
                    thingNode newNode = new thingNode();
                    newNode.level = fatherNode.level+1;
                    newNode.value = fatherNode.value + v[fatherNode.level+1];
                    newNode.weight = w[fatherNode.level+1]+fatherNode.weight;
                    newNode.upprofit = Bound(newNode);
                    newNode.father = fatherNode;
                    newNode.Left = 1;
                    if(newNode.upprofit > maxValue)
                        pq.add(newNode);
                }
            }
            //向右节点搜索，其能够取到的价值上界通过父亲节点的上界减去本层物品的价值。
            if((fatherNode.upprofit - v[fatherNode.level+1])> maxValue){
                thingNode newNode2 = new thingNode();
                newNode2.level = fatherNode.level+1;
                newNode2.value = fatherNode.value;
                newNode2.weight = fatherNode.weight;
                newNode2.father = fatherNode;
                newNode2.upprofit = fatherNode.upprofit - v[fatherNode.level+1];
                newNode2.Left = 0;
                pq.add(newNode2);
            }
        }
    }
    public double Bound(thingNode no){
        double maxLeft = no.value;
        int leftWeight = W - no.weight;
        int templevel = no.level;
        //尽力依照单位重量价值次序装剩余的物品
        while(templevel <= n-1 && leftWeight > w[templevel] ){
            leftWeight -= w[templevel];
            maxLeft += v[templevel];
            templevel++;
        }
        //不能装时，用下一个物品的单位重量价值折算到剩余空间。
        if( templevel <= n-1){
            maxLeft += v[templevel]/w[templevel]*leftWeight;
        }
        return maxLeft;
    }
    //定义节点中的参数以及优先级设置的对象
    class thingNode implements Comparable<thingNode>{
        int weight;//该节点目前背包中的重量
        double value;//该节点目前背包中的总价值
        double upprofit;//该节点能够达到的价值上界
        int Left;   //该节点是否属于左节点（用于最终构造最优解）
        int level;  //该节点是第几个物品的选择
        thingNode father; //该节点的父节点
        public int compareTo(thingNode node){
            if(this.upprofit<node.upprofit)
                return 1;
            else if(this.upprofit == node.upprofit)
                return 0;
            else
                return -1;
        }
    }

    public static void approximate(){
        bubblesort();
        double weight = 0;
        for (int i = 0; i < n; i++){
            if (weight + w[i] <=W){
                weight = weight + w[i];
                maxValue = maxValue + v[i];
            }
        }
    }
}
