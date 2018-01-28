import java.util.Random;

public class FeedBack {
    public static void main(String[] args) {
        int num = 4;
        Queue queue1 = new Queue(8,1, 200);
        Queue queue2 = new Queue(8,2, 300);
        Queue queue3 = new Queue(8,3, 400);
        Queue queue4 = new Queue(8,4, 999999);
        int position = 0;// 记录当前运行的进程位置，从进程0开始
        int currentTime = 2000;
        int processRemaining = num;
        ProcessBlock[] processBlocks = buildProcessBlockArray(num);
        System.out.println("-------------------");
        while(true){
            if(position < num){
                if (processBlocks[position].getArriveTime() <= currentTime) {              // 取出数组第一个进程的到达时间与当前时间进行比较,若等于或者小于当前时间，说明有程序到达并进入第一队列
                    queue1.add(processBlocks[position]);
                    if (position == 0) {
                        currentTime = processBlocks[position].getArriveTime();
                    }
                    System.out.println("进程" + processBlocks[position].getNumber() + "加入1级队列时间" + currentTime);
                    processBlocks[position].setQueue(queue1);
                    position++;
                }
            }else {
                if (ProcessBlock.ifAllCompleted()) { // 如果所有进程完毕则退出循环
                    System.out.println("所有进程完毕");
                    break;
                }
            }
            if(!queue1.empty()){   // 若一级队列非空，则优先执行一级队列
                ProcessBlock p = queue1.peek();
                if (p.getRemainTime() <= queue1.getTimeSlice()){   // 如果队头进程服务时间小于等于该队列时间片。
                    p.Finished();  //进程结束自动出队
                    processRemaining--;
                }else {
                    queue1.poll();
                    p.setExcutedTime(p.getExcutedTime() + queue1.getTimeSlice());
                    p.setQueue(queue2);
                    queue2.add(p);
                }

                // 更新时间 判断是否完成所有进程
                currentTime += queue1.getTimeSlice();
                System.out.println("当前时间为"+ currentTime);
                if (ProcessBlock.ifAllCompleted()) { // 如果所有进程完毕则退出循环
                    System.out.println("所有进程完毕");
                    break;
                }
            }else if (!queue2.empty()){
                ProcessBlock p = queue2.peek();
                int timeRemaining = p.getRemainTime() - p.getExcutedTime();
                if (timeRemaining <= queue2.getTimeSlice()){
                     p.Finished();
                     processRemaining --;

                     currentTime += timeRemaining;
                    System.out.println("执行完毕退出2级队列时间" + currentTime);
                    if (ProcessBlock.ifAllCompleted()) { // 如果所有进程完毕则退出循环
                        System.out.println("所有进程完毕");
                        break;
                    }
                }else {
                    queue2.poll();
                    p.setExcutedTime(p.getExcutedTime() + queue2.getTimeSlice());
                    p.setQueue(queue3);
                    queue3.add(p);

                    currentTime += queue2.getTimeSlice();
                    System.out.println("未完成退出2级队列时间:" + currentTime);
                    if (ProcessBlock.ifAllCompleted()) { // 如果所有进程完毕则退出循环
                        System.out.println("所有进程完毕");
                        break;
                    }
                }
            }else if (!queue3.empty()){
                ProcessBlock p = queue3.peek();
                int timeRemain = p.getRemainTime() - p.getExcutedTime();
                if(timeRemain <= queue3.getTimeSlice()){
                    p.Finished();
                    processRemaining -= 1;

                    currentTime += timeRemain;
                    System.out.println("未完成退出3级队列时间" + currentTime);
                    if (ProcessBlock.ifAllCompleted()) { // 如果所有进程完毕则退出循环
                        System.out.println("所有进程完毕");
                        break;
                    }
                }else {
                    queue3.poll();
                    p.setExcutedTime(p.getExcutedTime() + queue3.getTimeSlice());
                    p.setQueue(queue4);
                    queue4.add(p);
                    currentTime += queue3.getTimeSlice();
                    System.out.println("未完成退出3级队列时间" + currentTime);
                    if (ProcessBlock.ifAllCompleted()) { // 如果所有进程完毕则退出循环
                        System.out.println("所有进程完毕");
                        break;
                    }
                }
            }else if (!queue4.empty()){
                ProcessBlock p = queue4.peek();
                int timeRemain = p.getRemainTime() - p.getExcutedTime();
                ProcessBlock.ifAllCompleted();
                p.Finished();
                processRemaining--;
                currentTime += timeRemain;
                if (ProcessBlock.ifAllCompleted()) { // 如果所有进程完毕则退出循环
                    System.out.println("所有进程完毕");
                    break;
                }
            }
            System.out.println("-------------------");
        }
    }


    public static ProcessBlock[] buildProcessBlockArray(int num){
        ProcessBlock[] processBlocks = new ProcessBlock[num];
        for(int i = 0; i < num; i++){
            processBlocks[i] = new ProcessBlock((new Random().nextInt(9) + 3) * 100,( new Random().nextInt(9) + 3) * 100, i, true);
            System.out.println("生成进程" + i + "到达的时间为：" + processBlocks[i].getArriveTime()+"作业时间为"+processBlocks[i].getRemainTime());
        }
        ProcessBlock[] newProcessBlocks = quicksort(processBlocks, 0, processBlocks.length - 1);
        for(ProcessBlock p : newProcessBlocks){
            System.out.println("进程"+ p.getNumber() + "到达的时间为：" + p.getArriveTime()+"作业时间为" + p.getRemainTime());
        }
        return newProcessBlocks;
    }

    public  static ProcessBlock[] quicksort(ProcessBlock[] arr, int lo, int hi){
        if(lo >= hi){
            return arr;
        }
        int index = scfspartition(arr, lo, hi);
        quicksort(arr,lo,index - 1);
        quicksort(arr,index+1, hi);
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
     * 两数交换
     * @param a
     * @param b
     */

    public static void swap(ProcessBlock a,ProcessBlock b){
        ProcessBlock temp=a;
        a=b;
        b=temp;
    }
}
