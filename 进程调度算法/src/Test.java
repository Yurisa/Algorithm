import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static final int TIME_FRAGMENT = 500;//时间片大小

    public static final int TIME_SWITCH = 10;

    /**
     * 时间片轮转调度算法
     *
     * @param count 进程的个数
     */
    private void roundRobin(int count) {

        Queue queue1 = new Queue(9999);
        Queue queue2 = new Queue(9999);
        System.out.println("本程序时间片长度为" + TIME_FRAGMENT);
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            System.out.println("\n请输入进程" + i + "所需运行时间、到达时间");
            int remainTime = in.nextInt();
            int arriveTime = in.nextInt();
            queue1.add(new ProcessBlock(remainTime,arriveTime, i,0));
            queue2.add(new ProcessBlock(remainTime,arriveTime, i,0));
        }

        roundRobin(queue1,500,count);

        roundRobin(queue2,200,count);
    }

    private void roundRobin(Queue queue,int timeFragment,int count) {
        System.out.println("\n===============开始执行时间片长度为"+timeFragment+"的时间片轮转算法"+"===============");
        int switchTime = 0;
        int total = 0;
        int timeAllTouched = 0;
        for (; ; ) {
            if (queue.empty()) {
                System.out.println("所有进程执行完成！");
                System.out.println("使用长度为"+timeFragment+"的时间片,共花费"+total+"ms，共切换"+switchTime+"次，首次遍历完所有进程的时间："+timeAllTouched+"");
                break;
            }
            //先将队首的元素取出
            ProcessBlock processBlock = queue.poll();
            try {

                if (processBlock.getRemainTime() > 0) {
                    System.out.println("");
                    System.out.println("进程" + processBlock.getNumber() + "开始执行，当前所需时间为："
                            + processBlock.getRemainTime());
                    if (processBlock.getRemainTime() > timeFragment) {
                        //需要的时间大于时间片段，只能先运行一次，后放入队列
                        Thread.sleep(timeFragment+TIME_SWITCH);
                        total+=timeFragment+TIME_SWITCH;
                        switchTime++;
                        if(count == switchTime){
                            timeAllTouched = total;
                        }
                        processBlock.setRemainTime(processBlock.getRemainTime() - timeFragment);
                        queue.add(processBlock);
                        System.out.println("进程" + processBlock.getNumber() + "未执行完成,还需要"
                                + processBlock.getRemainTime() + "放入就绪队列");

                    } else {//不足500 直接执行完
                        Thread.sleep(processBlock.getRemainTime());
                        total += processBlock.getRemainTime()+TIME_SWITCH;
                        switchTime++;
                        if(count == switchTime){
                            timeAllTouched = total;
                        }
                        processBlock.setRemainTime(-1);
                        System.out.println("进程" + processBlock.getNumber() + "已经执行完成，不再放入队列");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 优先级调度算法 优先执行 “已到达作业”中“优先级"最高的进行执行
     */
    private void psa(int count) {
        ProcessBlock[] pbs = new ProcessBlock[count];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            System.out.println("\n请输入进程" + i + "所需运行时间、到达时间、优先级");
            int remainTime = in.nextInt();
            int arriveTime = in.nextInt();
            int priority = in.nextInt();
            ProcessBlock processBlock = new ProcessBlock(remainTime, arriveTime, i, priority);
            pbs[i] = processBlock;
        }

        new Thread(() -> {
            //记录剩余的进程数量
            int remainPb = count;
            //记录当前的时刻
            int startTime = (int) new Date().getTime();
            for (; ; ) {
                if (remainPb <= 0) {
                    System.out.println("所有进程执行完成！");
                    break;
                }
                //寻找当前到达作业中 优先级最高的

                //记录一次遍历后 发现的已经到达的进程数
                int arrivedPb = 0;
                //记录最高权限进程的序号
                int number = -1;
                //记录当前最高权限值;
                int maxPriority = -1;
                int currentTime = (int) new Date().getTime();
                for (int i = 0; i < count; i++) {
                    ProcessBlock processBlock = pbs[i];
                    if ((currentTime - startTime) < processBlock.getArriveTime() ||
                            processBlock.getRemainTime() <= 0) {//时间还早，此进程还没到达、或者此进程已经执行完毕
                        continue;
                    }
                    arrivedPb++;
                    if (processBlock.getPriority() > maxPriority) {
                        maxPriority = processBlock.getPriority();
                        number = i;
                    }
                }
                if (number == -1) {
                    continue;
                }
                System.out.println("\n共找到" + arrivedPb + "个已到达的进程，其中优先级最高的是进程" + number +
                        "，需要执行" + pbs[number].getRemainTime());
                try {
                    Thread.sleep(pbs[number].getRemainTime());
                    System.out.println("进程" + number + "已执行完成");
                    pbs[number].setRemainTime(0);
                    remainPb--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public static void main(String args[]) {
//        new Test().roundRobin(5);
        new Test().psa(5);
    }
}



