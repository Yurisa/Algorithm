public class Queue {
    private ProcessBlock[] data = null;
    private int maxSize; //队列容量
    private int front;  //队列头，允许删除
    private int rear;   //队列尾，允许插入
    private int levelOfQueue;
    private int timeSlice;
    private int acount = 0;


    public Queue(int initialSize,int levelOfQueue, int timeSlice) {
        this.levelOfQueue = levelOfQueue;
        this.timeSlice = timeSlice;
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new ProcessBlock[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("初始化大小不能小于0：" + initialSize);
        }
    }

    //构造函数
    public Queue() {
        this(10);
    }

    public Queue(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new ProcessBlock[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("初始化大小不能小于0：" + initialSize);
        }
    }

    //判空
    public boolean empty() {
        return rear == front ? true : false;
    }

    //插入
    public boolean add(ProcessBlock processBlock) {
        if (rear == maxSize) {
            throw new RuntimeException("队列已满，无法插入新的元素！");
        } else {
            data[rear++] = processBlock;
            return true;
        }
    }

    //返回队首元素，但不删除
    public ProcessBlock peek() {
        if (empty()) {
            throw new RuntimeException("空队列异常！");
        } else {
            return data[front];
        }
    }

    //返回队尾元素
    public ProcessBlock returnEnd(){
        if (empty()) {
            throw new RuntimeException("空队列异常！");
        } else {
             return data[rear];
        }
    }

    //出队
    public ProcessBlock poll() {
        if (empty()) {
            throw new RuntimeException("空队列异常！");
        } else {
            ProcessBlock value = data[front];  //保留队列的front端的元素的值
            data[front++] = null;     //释放队列的front端的元素
            return value;
        }
    }

    //队列长度
    public int length() {
        return rear - front;
    }

    public ProcessBlock[] getData() {
        return data;
    }

    public void setData(ProcessBlock[] data) {
        this.data = data;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public int getLevelOfQueue() {
        return levelOfQueue;
    }

    public void setLevelOfQueue(int levelOfQueue) {
        this.levelOfQueue = levelOfQueue;
    }

    public int getTimeSlice() {
        return timeSlice;
    }

    public void setTimeSlice(int timeSlice) {
        this.timeSlice = timeSlice;
    }

    public int getAcount() {
        return acount;
    }

    public void setAcount(int acount) {
        this.acount = acount;
    }
}
