function binarySearch(arr, target){
    let l = 0;
    let r = arr.length - 1;
    while(l <= r){
      let mid = parseInt(l + (r - l)/2);
      if(arr[mid] === target)
        return mid;
      if(target < arr[mid]){
          //在arr[l...mid-1]之中查找target
          r = mid - 1;
      }else{//target > arr[mid]
          // 在arr[mid+1...r]之中查找target
          l = mid + 1;
      }  
    }
}

function binarySearch2(arr, target, start, end){
    let l = start || 0;
    let r = end || arr.length - 1;
    let mid = Math.floor((r + l)/2);
    console.log(mid)
    console.log(arr[mid])
    if(arr[mid] === target){
        return mid;
    }
    if(target > arr[mid]){
        return binarySearch2(arr, target, mid + 1, r);
    }else{
       return binarySearch2(arr, target, l, mid - 1);
    }
}


var arr = [99, 55, 86, 78,69, 4, 66, 47, 12, 2, 48, 11];
arr.sort(function(a, b){
    return a - b;
});
console.log(arr)
console.log("此元素的位置为"+ binarySearch( arr, 11))
console.log("此元素的位置为"+ binarySearch2(arr, 11))
console.log(Math.min(...arr))