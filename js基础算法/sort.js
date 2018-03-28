/**
 * 选择排序
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