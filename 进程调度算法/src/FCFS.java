import java.util.Random;

public class FCFS {
    public static void execute(Queue queue){
        int currentTime = 0;
        while (true){
      if (queue.empty()){
          System.out.println("所有进程都执行完了");
          System.exit(1);
      }
      try {
              Thread.sleep(queue.peek().getRemainTime());
              ProcessBlock processBlock = queue.peek();
              currentTime += processBlock.getArriveTime();
              System.out.println("进程"+processBlock.getNumber()+"执行完毕用时"+processBlock.getRemainTime());
          System.out.println("当前时间为" + currentTime);
          queue.poll();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
    }



    public  static ProcessBlock[] quicksort(ProcessBlock[] arr, int lo, int hi,String flag){
        if(lo >= hi){
            return arr;
        }
        if (flag.equals("fcfs")){
            int index = scfspartition(arr, lo, hi);
            quicksort(arr,lo,index - 1,"fcfs");
            quicksort(arr,index+1, hi, "fcfs");
        }else {
            int index = sjfpartition(arr, lo, hi);
            quicksort(arr,lo,index - 1,"sjf");
            quicksort(arr,index+1, hi, "sjf");
        }

        return arr;
    }

    public static int scfspartition(ProcessBlock[] arr, int lo, int hi){
        //三数取中
        int mid = lo + (hi-lo)/2;
        if(arr[mid].getArriveTime() > arr[hi].getArriveTime()){
            swap(arr[mid],arr[hi]);
        }
        if(arr[lo].getArriveTime() > arr[hi].getArriveTime()){
            swap(arr[lo],arr[hi]);
        }
        if(arr[mid].getArriveTime() > arr[lo].getArriveTime()){
            swap(arr[mid],arr[lo]);
        }
        ProcessBlock key = arr[lo];
        while (lo < hi){
            while (arr[hi].getArriveTime() >= key.getArriveTime() && hi > lo){
                hi--;
            }
            arr[lo] = arr[hi];
            while (arr[lo].getArriveTime() <= key.getArriveTime() && hi > lo){
                lo++;
            }
            arr[hi] = arr[lo];
        }
        arr[lo] = key;
        return lo;

    }

    /**
     * 将待排序片段调整顺序,获得"中间数据"的正确索引
     * @param arr
     * @param lo
     * @param hi
     * @param
     * @return
     */

    public static int sjfpartition(ProcessBlock[] arr, int lo, int hi){
        //三数取中
        int mid = lo + (hi-lo)/2;
        if(arr[mid].getRemainTime() > arr[hi].getRemainTime()){
            swap(arr[mid],arr[hi]);
        }
        if(arr[lo].getRemainTime() > arr[hi].getRemainTime()){
            swap(arr[lo],arr[hi]);
        }
        if(arr[mid].getRemainTime() > arr[lo].getRemainTime()){
            swap(arr[mid],arr[lo]);
        }
        ProcessBlock key = arr[lo];
        while (lo < hi){
                while (arr[hi].getRemainTime() >= key.getRemainTime() && hi > lo){
                    hi--;
                }
                arr[lo] = arr[hi];
                while (arr[lo].getRemainTime() <= key.getRemainTime() && hi > lo){
                    lo++;
                }
                arr[hi] = arr[lo];
        }
        arr[lo] = key;
        return lo;

    }

    /**
     * 两数交换
     * @param a
     * @param b
     */

    public static void swap(ProcessBlock a,ProcessBlock b){
        ProcessBlock temp=a;
        a=b;
        b=temp;
    }


    public static void main(String[] args){
        int num = 8;
        Queue queue = new Queue(num);
        ProcessBlock[] processBlocks = new ProcessBlock[num];
        for(int i = 0; i < num; i++){
            processBlocks[i] = new ProcessBlock((new Random().nextInt(9) + 3) * 100,( new Random().nextInt(9) + 3) * 100, i, true);
            System.out.println("生成进程" + i + "到达的时间为：" + processBlocks[i].getArriveTime()+"作业时间为"+processBlocks[i].getRemainTime());
        }
        ProcessBlock[] newProcessBlocks = quicksort(processBlocks, 0, processBlocks.length - 1, "sjf");
        for(ProcessBlock p : newProcessBlocks){
//            System.out.println("进程"+p.getNumber()+"的到达时间为"+p.getArriveTime());
            System.out.println("进程"+p.getNumber()+"的到达时间为"+p.getArriveTime()+"的作业时间为"+p.getRemainTime());
            queue.add(p);
        }
        execute(queue);
    }
}
