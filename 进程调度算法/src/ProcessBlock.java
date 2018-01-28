public class ProcessBlock {
    static int amountOfProcess = 0; // 所有进程总数

    private int remainTime;//进程剩余时间

    private int arriveTime;//进程到达时间

    private int Tp;//进入就绪队列的时间

    private int Tc;//进入执行队列的时间

    private int To;//进程执行结束的时间

    private int number;//进程编号

    private int priority;

    protected int excutedTime = 0; // 进程已经执行时间

    private ProcessBlock next;
    private Queue queue;

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getTp() {
        return Tp;
    }

    public void setTp(int tp) {
        Tp = tp;
    }

    public int getTc() {
        return Tc;
    }

    public void setTc(int tc) {
        Tc = tc;
    }

    public int getTo() {
        return To;
    }

    public void setTo(int to) {
        To = to;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ProcessBlock(int remainTime, int arriveTime, int tp, int tc, int to, int number, ProcessBlock next) {
        this.remainTime = remainTime;
        this.arriveTime = arriveTime;
        Tp = tp;
        Tc = tc;
        To = to;
        this.number = number;
        this.next = next;
    }

    public ProcessBlock getNext() {

        return next;
    }

    public void setNext(ProcessBlock next) {
        this.next = next;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return "ProcessBlock{" +
                "remainTime=" + remainTime +
                ", arriveTime=" + arriveTime +
                ", Tp=" + Tp +
                ", Tc=" + Tc +
                ", To=" + To +
                ", number=" + number +
                ", priority=" + priority +
                ", excutedTime=" + excutedTime +
                ", next=" + next +
                ", queue=" + queue +
                '}';
    }

    public ProcessBlock(int remainTime, int number, ProcessBlock next) {
        this.remainTime = remainTime;
        this.number = number;
        this.next = next;
    }

    public ProcessBlock(int remainTime, int number, int priority) {
        this.remainTime = remainTime;
        this.number = number;
        this.priority = priority;
    }

    public ProcessBlock(int remainTime, int arriveTime, int number, int priority) {
        this.remainTime = remainTime;
        this.arriveTime = arriveTime;
        this.number = number;
        this.priority = priority;
    }

    public ProcessBlock(int arriveTime, int remainTime,int number,boolean flag) {
        this.remainTime = remainTime;
        this.arriveTime = arriveTime;
        this.number = number;
        amountOfProcess += 1;// 每创建一个进程对象，进程总数+1
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public int getExcutedTime() {
        return excutedTime;
    }

    public void setExcutedTime(int excutedTime) {
        this.excutedTime = excutedTime;
    }
    public void Finished() { // 进程结束
        // 执行出队操作
        queue.poll();
        System.out.println("-------------------");
        System.out.println("进程" + this.number + "执行完毕,结束时位于第" + this.queue.getLevelOfQueue() + "队列。");
        amountOfProcess -= 1;// 每完成一个进程对象，进程总数-1
    }

    public static boolean ifAllCompleted() {
        if (amountOfProcess == 0) {
            System.out.println("当前没有进程正在运行");
            return true;
        } else {
            System.out.println("当前有" + amountOfProcess + "个进程等待运行。");
            return false;
        }

    }
}
