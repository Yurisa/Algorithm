module.exports = class BST{
    constructor(){
       this.count = 0;
       this.root = undefined;
    }

    size(){
        return this.count;
    }

    isEmpty(){
        return this.count === 0;
    }

    insert(key, value){
        this.root = this.__insert(root, key, value)
    }
    
    contain(key){
        return this.__contain(this.root, key);
    }

    search(key){
        return this.__search(this.root, key);
    }

    // 前序遍历
    preOrder(){
       this.__preOrder(this.root);
    }

    //中序遍历
    inOrder(){
        this.__inOrder(this.root);
    }

    //后续遍历

    postOrder(){
        this.__postOrder(this.root);
    }

    // 释放空间
    destroy(){
        this.__destroy(this.root);
    }

    //层序遍历
    levelOrder(){
        this.__levelOrder();
    }


    //寻找最小值
    minimum(){
        let minNode = this.__minimum(this.root);
        return minNode.key;
    }

    //寻找最大值
    maxmum(){
        let maxNode = this.__maxmum(this.root);
        return maxNode.key;
    }

    //从二叉树中删除最小值所在节点
    removeMin(){
        if(this.root){
            this.root = this.__removeMin(root);
        }
    }

     //从二叉树中删除最大值所在节点
    removeMax(){
        if(this.root){
            this.root = this.__removeMax(root);
        }
    }

    //从二叉搜索树中删除键值为key的节点
    remove(key){
        this.root = this.__remove(node, key);
    }
    //向以node为根的二叉搜索树中, 插入节点(key, value)
    //返回插入新节点后二叉搜索树的根
    __insert(node, key, value){
        if(!node){
            this.count++;
            return new Node(key, value);
        }
        if(key === node.key){
            node.value = value;
        }else if(key < node.key){
            node.left = this.__insert(node.left, key, value);
        }else{
            node.right = this.__insert(node.right, key, value)
        }
    }
    
    //查看以node为根的二叉搜索树中是否包含键值为key的节点
    __contain(node, key){
        if(!node){
            return false;
        }
        if(key === node.key){
            return true;
        }else if(key < node.key){
            return this.__contain(node.left, key);
        }else{
            return this.__contain(node.right, key);
        }
    }

    //查看以node为根的二叉搜索树中查找键值为key的节点所对应的value
    __search(node, key){
        if(!node){
            return null;
        }
        if(key === node.key){
            return node.value;
        }else if(key < node.key){
            return this.__search(node.left, key);
        }else{
            return this.__search(node.right, key)
        }
    }

    //以node为根的二叉搜索树进行前序遍历
    __preOrder(node){
        if(node){
            console.log(node.key);
            this.__preOrder(node.left);
            this.__preOrder(node.right);
        }
    }

    //以node为根的二叉搜索树进行中序遍历
    __inOrder(node){
        if(node){
            this.__preOrder(node.left);
            console.log(node.key);
            this.__preOrder(node.right);
        }
    }

    //以node为根的二叉搜索树进行后序遍历
    __postOrder(){
        if(node){
            this.__preOrder(node.left);
            this.__preOrder(node.right);
            console.log(node.key);
        }
    }

    //释放以node为根的二叉搜索树
    __destroy(node){
        if(node){
            this.destroy(node.left);
            this.destroy(node.right);

            node = null;
            this.count--;
        }
    }

    __levelOrder(){
        let queue = [];
        queue.push(this.root);
        while(queue.length > 0){
            let node = queue.shift();
            console.log(node.key);
            if(node.left){
                queue.push(node.left);
            }
            if(node.right){
                queue.push(node.right);
            }
        }
    }

    //以node为根的二叉搜索树中, 返回最小键值的节点
    __minimum(node){
        if(!node.left){
            return node;
        }
        return this.__minimum(node.left)
        //非递归写法
        // let currentNode;
        // while(node.left){
        //     currentNode = node.left;
        // }
        // return currentNode; 
    }

    __maxmum(node){
        if(!node.right){
            return node;
        }
        return this.__maxmum(node.right);
    }
    
    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    __removeMin(node){
        if(!node.left){
            let rightNode = node.right;
            node = null;
            this.count--;
            return rightNode;
        }
        node.left = this.__removeMin(node.left)
        return node; 
    }

    //删除掉以node为根的二分搜索树中的最大节点
    //返回删除节点后新的二分搜索树的根
    __removeMax(node){
        if(!node.right){
            let leftNode = node.left;
            node = null;
            this.count--;
            return leftNode;
        }
        node.right = this.__removeMin(node.right)
        return node; 
    }

    //删除掉以node为根的二分搜索树中键值为key的节点
    //返回删除节点后新的二分搜索树的根
    __remove(node, key){
        if(!node){
            return null;
        }
        if(key < node.key){
            node.left = this.__remove(node.left, key);
            return node;
        }else if(key > node.key){
            node.right = this.__remove(node.right, key);
            return node;
        }else{
            if(!node.left){
                let rightNode = node.right;
                node = null;
                this.count--;
                return rightNode;
            }
            if(!node.right){
                let leftNode = node.left;
                node = null;
                this.count--;
                return leftNode;
            }

           let successor = this.__minimum(node.right);
           successor = JSON.parse(JSON.stringify(successor));
           successor.right = this.__removeMin(node.right);
           successor.left = node.left;
           node = null;
           this.count --;
           return successor;
        }
    }

}

class Node{
    constructor(key, value){
      this.key = key;
      this.value = value;
      this.right = undefined;
      this.left = this.right;
    }
}
