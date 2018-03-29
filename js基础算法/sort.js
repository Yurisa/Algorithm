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
        var e = arr[i]
        for(let j = i; j > 0&& arr[j-1] > e; j--){
           arr[j] = arr[j-1]
        }
        arr[j] = e
    }
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
    if(l >= r)
      return;
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