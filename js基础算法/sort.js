/**
 * 选择排序
 * @param {*} arr 
 */
function selectionSort(arr){
    for(let i = 0; i < arr.length; i++){
        minIndex = i;
        for(let j = i + 1; j < arr.length; j++){
            if(arr[j] <= arr[minIndex]){
                minIndex = j;
            }
        }
        [arr[i], arr[minIndex]] = [arr[minIndex], arr[i]]
    }
}

/**
 * 冒泡排序
 * @param {*} arr 
 */
function bubbleSort(arr){
    for(let i = 0; i < arr.length - 1; i++){
        for(let j = 0; j < arr.length - i - 1;j++){
            if(arr[j] >= arr[j+1]){
                [arr[j+1], arr[j]] = [arr[j], arr[j+1]]
            }
        }
    }
}

/**
 * 插入排序
 * @param {*} arr 
 */
function insertionSort(arr){
    for(let i = 1; i < arr.length; i++){
        //寻找arr[i]合适的插入位置
        for(let j = i; j > 0; j--){
            if(arr[j] < arr[j-1]){
                [arr[j], arr[j-1]] = [arr[j-1], arr[j]];
            }else{
                break;
            }
        }
    }
}

/**
 * 优化插入排序
 * @param {*} arr 
 */
function insertionSort2(arr){
    for(let i = 1; i < arr.length; i++){
        let e = arr[i]
        let j;
        for(j = i; j > 0 && arr[j-1] > e; j--){
           arr[j] = arr[j-1]
        }
        arr[j] = e
    }
}

/**
 * 对特定区间的插入排序
 * @param {*} arr 
 */
/**
 * 对特定区间的插入排序
 * @param {*} arr 
 */
function insertionSort3(arr, l, r){
    for(let i = l+1; i <= r; i++){
        let e = arr[i]; 
        let j;
        for(j = i; j > l && arr[j-1] > e; j--){
            arr[j] = arr[j-1];
        }
        arr[j] = e;
    }
    return;
}
/**
 * 归并排序
 * @param {*} arr 
 */
function mergeSort(arr){
    __mergeSort(arr, 0, arr.length - 1) 
}

/**
 * 递归使用归并排序, 对arr[l...r]的范围进行排序
 * @param {*} arr 
 * @param {*} l 
 * @param {*} r 
 */
function __mergeSort(arr, l, r){
    // if(l >= r)
    //   return;
    if(r - l <= 15){
        insertionSort3(arr, l, r)
        return;
    }
    let mid = parseInt((l+r)/2);
    __mergeSort(arr, l, mid);
    __mergeSort(arr, mid+1, r);
    __merge(arr, l, mid, r);
}

/**
 * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
 * @param {*} arr 
 * @param {*} l 
 * @param {*} mid 
 * @param {*} r 
 */
function __merge(arr, l, mid, r){
   let aux = [];
   for(let i = l; i <= r; i++){
       aux[i-l] = arr[i];
   }

   let i = l;
   let j = mid  + 1;
   for(let k = l; k <= r; k++){
       if(i > mid){
           arr[k] = aux[j-l];
           j++;
       }else if(j > r){
           arr[k] = aux[i-l];
           i++;
       }else if(aux[i-l] < aux[j-l]){
           arr[k] = aux[i-l];
           i++;
       }else{
           arr[k] = aux[j-l];
           j++;
       }
   }
}


/**
 * 自底向上的归并排序
 * @param {*} arr 
 */
function mergeSortBU(arr){
    let n = arr.length;
    for(let sz = 1; sz <= n; sz += sz){
        for(let i = 0; i + sz < n; i += sz + sz){
            //对arr[i...i+sz-1]和arr[i+sz...i+2*sz-1]进行归并
            __merge(arr, i, i + sz -1, Math.min(i + sz + sz -1, n - 1))
        }
    }
}


/**
 * 快速排序
 * @param {*} arr 
 */
function quickSort(arr){
   __quickSort(arr, 0, arr.length - 1)
}

/**
 * 对arr[l...r]部分进行快速排序
 * @param {*} arr 
 * @param {*} l 
 * @param {*} r 
 */
function __quickSort(arr, l, r){
    //   if(l >= r)
    //     return;
     if(r - l <= 15){
            insertionSort3(arr, l, r)
            return;
      }
      let p = __partition(arr, l, r);
      __quickSort(arr, l, p - 1);
      __quickSort(arr, p + 1, r)
}

/**
 * 对arr[l...r]部分进行partition操作
 * 返回p,使得arr[l...p-1] < arr[p];arr[p+1..r] > arr[p]
 * @param {*} arr 
 * @param {*} l 
 * @param {*} r 
 */
function __partition(arr, l, r){
    let v = arr[l];

    //arr[l+1...j] < v; arr[j+1...i] > v
    let j = l;
    for(let i = l+1; i <= r; i++){
        if(arr[i] < v){
            [arr[j+1], arr[i]] = [arr[i], arr[j+1]];
            j++;
        }
    }
    [arr[l], arr[j]] = [arr[j], arr[l]];
    return j;
}

function __partition2(arr, l, r){
    let v = arr[l];
    let i = l+1; 
    let j = r;
    while(true){
        while(i < r && arr[i] < v) i++;
        while(j >= l+1 && arr[j] > v) j--;
        if(i > j) break;
        [arr[i], arr[j]] = [arr[j], arr[i]];
        i++;
        j--;
    }

    [arr[l], arr[j]] = [arr[j], arr[l]]
}

/**
 * 三路快排
 * @param {*} arr 
 */
function quickSort3Ways(arr){
    __quickSort3Ways(arr, 0, arr.length - 1 );
}

/**
 * 三路快速排序处理 arr[l...r]
 * 将arr[l..r] 分为 <v ; =v; >v 三部分
 * 之后递归对 < v ; > v两部分继续进行三路快排
 * @param {*} arr 
 * @param {*} l 
 * @param {*} r 
 */
function __quickSort3Ways(arr, l, r){
    if(l - r <= 15){
        insertionSort3(arr, l, r);
        return;
    }
    
    //partition
    let v = arr[l];
    let lt = l; //arr[l+1...lt] < v
    let gt = r+1;// arr[gt...r] > v
    let i = l+1; //arr[lt+1...i] == v
    while(i < gt){
        if(arr[i] < v){
            [arr[i], arr[lt+1]] = [arr[lt+1], arr[i]];
            lt++;
            i++;
        }else if(arr[i] > v){
            [arr[i], arr[gt-1]] = [arr[gt-1], arr[i]]
            gt--;
        }else{ //arr[i] == v
            i++;
        }        
    }
    [arr[l], arr[lt]] = [arr[lt], arr[l]];
    __quickSort3Ways(arr, l, lt - 1);
    __quickSort3Ways(arr, gt, r)    

}

var MaxHeap = require('./heap.js');
let arr = [88, 55, 56, 1, 62, 85, 48, 55, 65, 12, 37, 99]
heapSort2(arr);
console.log(...arr);
function heapSort1(arr){
    let maxHeap = new MaxHeap();
    for(let i = 0; i < arr.length; i++){
        maxHeap.insert(arr[i]);
    }
    for(let i = arr.length - 1; i >=0; i--){
        arr[i] = maxHeap.extracMax();
    }
}


function heapSort2(arr){
    let maxHeap = new MaxHeap(arr);
    for(let i = arr.length - 1; i >=0; i--){
        arr[i] = maxHeap.extracMax();
    }
}