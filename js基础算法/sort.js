/**
 * 选择排序
 * @param {*} arr 
 */
function selectionSort(arr){
    for(let i = 0; i < arr.length; i++){
        minIndex = i;
        for(let j = i + 1;j < arr.length;j++){
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
    for(let i = 0; i < arr.length - 1;i++){
        for(let j = 0; j < arr.length - i - 1;j++){
            if(arr[j] >= arr[j+1]){
                [arr[j+1], arr[j]] = [arr[j], arr[j+1]]
            }
        }
    }
}