class MaxHeap{  
    constructor(){
        this.data = [];
        this.count = 0;
    }
    size() {
        return this.count;
    }

    isEmpty(){
        return this.count === 0;
    }

    insert(item){
        this.data[this.count+1] = item;
        this.count++;
        this.shiftup(this.count);
    }

    extracMax(){
        let ret = this.data[1];
        [this.data[1], this.data[this.count]] = [this.data[this.count], this.data[1]];
        this.count--;
        this.shiftdown(1);
        return ret;
    }

    shiftup(k){
        k = parseInt(k);
        while(k > 1 && this.data[Math.floor(k/2)] < this.data[Math.floor(k)]){
            let item = this.data[Math.floor(k/2)];
            this.data[Math.floor(k/2)] = this.data[Math.floor(k)];
            this.data[Math.floor(k)] = item; 
            k = Math.floor(k/2);
        }
    }
    
    shiftdown(k){
        // 优化版本
       k = parseInt(k)
       let key = this.data[k] 
       while( 2*k <= this.count){
           let j = 2*k; //在此轮循环中, data[k]和data[j]交换位置
           if(j + 1 <= this.count && this.data[j+1] > this.data[j])
              j += 1;
           if(key >= this.data[j])
              break;
           this.data[k] = this.data[j];
           k = parseInt(j); 
       }
       [key, this.data[k]] = [this.data[k], key];
    //    while( 2*k <= this.count){
    //        let j = 2*k; //在此轮循环中, data[k]和data[j]交换位置
    //        if(j + 1 <= this.count && this.data[j+1] > this.data[j])
    //           j += 1;
    //        if(this.data[k] >= this.data[j])
    //           break;
    //        [this.data[k], this.data[j]] = [this.data[j], this.data[k]];
    //        k = parseInt(j); 
    //    }
    }


    print(){
        for(let i = 1; i < this.data.length; i++){
            console.log(this.data[i]);
        }
    }
    
}

var maxHeap  = new MaxHeap();
for(let i = 0; i < 100; i++){
    maxHeap.insert(Math.floor(Math.random() * 100))
}

while( !maxHeap.isEmpty()){
    console.log(maxHeap.extracMax());
}


