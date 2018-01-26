import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Yurisa on 2017/9/16.
 */
public class Sort {
    private static long starttime;
    private static long endtime;
    private static int contrastCount = 0;//对比次数
    private static int swapCount = 0;//交换次数

    public static void main(String args[]){
         File file = new File("random.txt");
         String[] str = txt2String(file).split("\\,");
         int[] number = transformtoInt(str);
//         heapsort(number,true);//堆排序
//        hillsort(number,true);//希尔排序
//        insertSort(number, true); //插入排序
//        selectionsort(number, true);//选择排序
        bubblesort(number, true); //冒泡排序
//        System.out.println("开始快速排序");
//        starttime = System.currentTimeMillis();
//        int[] arr = quicksort(number,0, number.length-1, true );  //快速排序
//          mergesort(number,0, number.length-1, true );//归并排序
//        endtime = System.currentTimeMillis();
//        System.out.print("一共发生了 "+contrastCount+" 次对比\t");
//        System.out.print("一共发生了 "+swapCount+" 次交换\t");
//        System.out.println("执行时间为"+(endtime-starttime)+"毫秒");
//        StringBuffer sb = new StringBuffer();
//        for(int i = 0;i<arr.length;i++){
//            sb.append(arr[i]+",");
//        }
//        ForFile ff = new ForFile();
//        ff.createFile("quicksort",sb.toString());
//         System.out.println(number.length);
//         System.out.println(number[3]);
     }

    /**
     * 插入排序
     * @param arr
     * @param isasc true 代表从小到大
     */

    public static void insertSort(int[] arr,boolean isasc){
        System.out.println("开始插入排序");
        starttime = System.currentTimeMillis();
        if(arr.length == 1){
            return;
        }
        int tmp = 0;
        for(int i = 1; i< arr.length;i++){
            tmp = arr[i];
//            System.out.print(arr[i]+",");
            int n = i - 1;
            for(; n >= 0; n--){
                if(isasc){
                    contrastCount++;
                    if(arr[n] >= tmp){
                        swapCount++;
                        arr[n+1] = arr[n];
                    }else {
                        break;
                    }
                }else{
                    contrastCount++;
                    if(arr[n] <= tmp){
                        swapCount++;
                        arr[n+1] = arr[n];
                    }else {
                        break;
                    }
                }
            }
            arr[n+1] = tmp;
        }
        endtime = System.currentTimeMillis();
        System.out.print("一共发生了 "+contrastCount+" 次对比\t");
        System.out.print("一共发生了 "+swapCount+" 次交换\t");
        System.out.println("执行时间为"+(endtime-starttime)+"毫秒");
//        for(int i = 0; i< arr.length; i++){
//            System.out.print(arr[i]+",");
//        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<arr.length;i++){
            sb.append(arr[i]+",");
        }
        ForFile ff = new ForFile();
        ff.createFile("insersort",sb.toString());
    }



    /**
     * 冒泡排序
     * @param arr
     * @param isasc
     */

    public static void bubblesort(int[] arr, boolean isasc) {
        System.out.println("开始冒泡排序");
        starttime = System.currentTimeMillis();
        if (arr.length == 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int tmp = 0;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (isasc) {  //从小到大(ture)
                    contrastCount++;
                    if (arr[j] >= arr[j+1]) {
                        swapCount++;
                        tmp = arr[j];
                        arr[j] = arr[j +1 ];
                        arr[j+1] = tmp;
                    }
                } else {    //从大到小(false)
                    contrastCount++;
                    if (arr[j] <= arr[j+1]) {
                        swapCount++;
                        tmp = arr[j+1];
                        arr[j+1] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
        }
        endtime = System.currentTimeMillis();
        System.out.print("一共发生了 "+contrastCount+" 次对比\t");
        System.out.print("一共发生了 "+swapCount+" 次交换\t");
        System.out.println("执行时间为"+(endtime-starttime)+"毫秒");
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<arr.length;i++){
            sb.append(arr[i]+",");
        }
        ForFile ff = new ForFile();
        ff.createFile("bubblesort",sb.toString());

    }

    /**
     * 选择排序
     * @param arr
     * @param isasc
     */

    public  static void selectionsort(int[] arr, boolean isasc){
        System.out.println("开始选择排序");
        starttime = System.currentTimeMillis();
        if (arr.length == 1) {
            return;
        }
        for(int i = 0;i < arr.length - 1; i++){
            int tmp = arr[i];
            int index = i;
            for(int j = i; j< arr.length;j++){
                if(isasc){
                    contrastCount++;
                    if(tmp >= arr[j]){
                        swapCount++;
                        tmp = arr[j];
                        index = j;
                    }
                }else {
                    contrastCount++;
                    if(tmp <= arr[j]){
                        swapCount++;
                        tmp = arr[j];
                        index = j;
                    }
                }
            }
            swapCount++;
            arr[index] = arr[i];
            arr[i] = tmp;
        }
        endtime = System.currentTimeMillis();
        System.out.print("一共发生了 "+contrastCount+" 次对比\t");
        System.out.print("一共发生了 "+swapCount+" 次交换\t");
        System.out.println("执行时间为"+(endtime-starttime)+"毫秒");
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<arr.length;i++){
            sb.append(arr[i]+",");
        }
        ForFile ff = new ForFile();
        ff.createFile("selectionsort",sb.toString());
    }

    /**
     * 希尔排序
     * @param arr
     * @param isasc
     */

    public static void hillsort(int[] arr, boolean isasc) {
        System.out.println("开始希尔排序");
        starttime = System.currentTimeMillis();
        if (arr.length == 1) {
            return;
        }
        for (int d = arr.length / 2; d >= 1; d = d / 2) { //组大小
            for (int k = 0; k < d; k++) { //组数
                for (int n = d + k; n < arr.length; n = n + d) { //同一组
                    int cur = n;
                    while (cur - d >= 0) { //插入排序
                        int tmp = 0;
                        if (isasc) {
                            contrastCount++;
                            if (arr[cur] <= arr[cur - d]) {
                                swapCount++;
                                tmp = arr[cur];
                                arr[cur] = arr[cur - d];
                                arr[cur - d] = tmp;
                            }
                        } else {
                            contrastCount++;
                            if (arr[cur] >= arr[cur - d]) {
                                swapCount++;
                                tmp = arr[cur];
                                arr[cur] = arr[cur - d];
                                arr[cur - d] = tmp;
                            }
                        }
                        cur = cur - d;
                    }
                }
            }
        }
        endtime = System.currentTimeMillis();
        System.out.print("一共发生了 "+contrastCount+" 次对比\t");
        System.out.print("一共发生了 "+swapCount+" 次交换\t");
        System.out.println("执行时间为"+(endtime-starttime)+"毫秒");
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<arr.length;i++){
            sb.append(arr[i]+",");
        }
        ForFile ff = new ForFile();
        ff.createFile("hillsort",sb.toString());
    }


    /**
     * 快速排序
     * @param arr
     * @param lo
     * @param hi
     * @param isasc
     * @return
     */
    public  static int[] quicksort(int[] arr, int lo, int hi, boolean isasc){
        if(lo >= hi){
           return arr;
        }
        int index = partition(arr, lo, hi, isasc);
        quicksort(arr,lo,index - 1,isasc);
        quicksort(arr,index+1, hi, isasc );
        return arr;
    }

    /**
     * 将待排序片段调整顺序,获得"中间数据"的正确索引
     * @param arr
     * @param lo
     * @param hi
     * @param isasc
     * @return
     */

    public static int partition(int[] arr, int lo, int hi, boolean isasc){
        //三数取中
        int mid = lo + (hi-lo)/2;
        if(arr[mid] > arr[hi]){
            swap(arr[mid],arr[hi]);
        }
        if(arr[lo] > arr[hi]){
            swap(arr[lo],arr[hi]);
        }
        if(arr[mid] > arr[lo]){
            swap(arr[mid],arr[lo]);
        }
        int key = arr[lo];
        while (lo < hi){
            if(isasc){
                while (arr[hi] >= key && hi > lo){
                    contrastCount++;
                    hi--;
                }
                swapCount++;
                arr[lo] = arr[hi];
                while (arr[lo] <= key && hi > lo){
                    lo++;
                }
                arr[hi] = arr[lo];
            }else{
                while (arr[hi] <= key && hi > lo){
                    contrastCount++;
                    hi--;
                }
                swapCount++;
                arr[lo] = arr[hi];
                while (arr[lo] >= key && hi > lo){
                    lo++;
                }
                arr[hi] = arr[lo];
            }
        }
        arr[lo] = key;
        return lo;

    }

    /**
     * 两数交换
     * @param a
     * @param b
     */

    public static void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
    }

    /**
     * 归并排序
     * @param arr
     * @param lo
     * @param hi
     * @param isasc
     */

    public static int[] mergesort(int[] arr, int lo, int hi, boolean isasc){
        if(lo >= hi){
            return arr;
        }
        int mid = (lo + hi)/2;
        mergesort(arr, lo, mid, isasc);
        mergesort(arr, mid+1, hi, isasc);
        merge(arr, lo, mid, hi, isasc);
        return arr;
    }

    /**
     * 合并已经排好序的两段
     * @param arr
     * @param lo
     * @param mid
     * @param hi
     * @param isasc
     */

    public static void merge(int[] arr, int lo, int mid, int hi, boolean isasc){
        int[] tmp = new int[hi - lo + 1];
        int i = lo;
        int j = mid+1;
        int n = 0;
        while(i <= mid && j <= hi ){
            if(isasc){
                contrastCount++;
                if(arr[i] <= arr[j]){
                    swapCount++;
                    tmp[n++] = arr[i++];
                }else {
                    tmp[n++] = arr[j++];
                }
            }else {
                if(arr[i] <= arr[j]){
                    tmp[n++] = arr[j++];
                }else {
                    tmp[n++] = arr[i++];
                }
            }
        }
        while (i <= mid){
            swapCount++;
            tmp[n++] = arr[i++];
        }
        while (j <= hi){
            swapCount++;
            tmp[n++] = arr[j++];
        }
        for(int k = 0; k<tmp.length;k++){
            arr[lo + k] = tmp[k];
        }
    }

    /**
     * 堆排序
     * @param data    要排序的数组
     * @param isasc 从大到小(false)还是从小到大(ture)
     */
    public static void heapsort(int[] data, boolean isasc) {
        System.out.println("开始堆排序");
        starttime = System.currentTimeMillis();
        if (data.length == 1) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            //建堆
            buildHeap(data, 0, data.length -1 - i, isasc);
            swapCount++;
            int tmp = data[0];
            data[0] = data[data.length - 1 - i];
            data[data.length - 1 - i] = tmp;
        }
        endtime = System.currentTimeMillis();
        System.out.print("一共发生了 "+contrastCount+" 次对比\t");
        System.out.print("一共发生了 "+swapCount+" 次交换\t");
        System.out.println("执行时间为"+(endtime-starttime)+"毫秒");
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<data.length;i++){
            sb.append(data[i]+",");
        }
        ForFile ff = new ForFile();
        ff.createFile("heapsort",sb.toString());
    }

    /**
     * 将指定开始和结束段的数据建堆
     * @param data
     * @param beginIndex
     * @param endIndex
     * @param isasc
     */
    public static void buildHeap(int[] data, int beginIndex, int endIndex, boolean isasc) {
        if (beginIndex >= endIndex) {
            return;
        }
        for (int i = (endIndex + beginIndex - 1) / 2; i >= beginIndex; i--) {
            int cur = i;
            if (isasc) {   //从小到大排序
                while (2 * cur + 1 <= endIndex) {
                    contrastCount++;
                    int biggerChildIndex = 2 * cur + 1;
                    contrastCount++;
                    if (biggerChildIndex + 1 <= endIndex) {
                        contrastCount++;
                        if (data[biggerChildIndex] < data[biggerChildIndex + 1]) {
                            biggerChildIndex = biggerChildIndex + 1;
                        }
                    }
                    //找到最大子节点,如果比当前节点大,就交换
                    if (data[i] < data[biggerChildIndex]) {
                        swapCount++;
                        int tmp = data[i];
                        data[i] = data[biggerChildIndex];
                        data[biggerChildIndex] = tmp;
                        //准备检查孙子节点
                        cur = biggerChildIndex;
                    } else {
                        break;
                    }
                }
            } else {    //小顶堆,用来从大到小排序
                //发生交换之后需要检查孙子节点,重孙子节点...
                while (2 * cur + 1 <= endIndex) {
                    int samllerChildIndex = 2 * i + 1;
                    if (samllerChildIndex + 1 <= endIndex) {
                        if (data[samllerChildIndex] > data[samllerChildIndex + 1]) {
                            samllerChildIndex = samllerChildIndex + 1;
                        }
                    }
                    //找到最小子节点,如果比当前节点小,就交换
                    if (data[i] > data[samllerChildIndex]) {
                        int tmp = data[i];
                        data[i] = data[samllerChildIndex];
                        data[samllerChildIndex] = tmp;
                        cur = samllerChildIndex;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 字符串数组转换为整型数组
     * @param arr
     * @return
     */

    public  static int[] transformtoInt(String[] arr){
        int[] intlist = new int[arr.length];
        for(int i = 0;i<arr.length;i++){
            intlist[i] = Integer.parseInt(arr[i].trim());
        }
        return intlist;
    }
    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }



}


