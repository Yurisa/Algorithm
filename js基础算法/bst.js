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
}

class Node{
    constructor(key, value){
      this.key = key;
      this.value = value;
      this.right = undefined;
      this.left = this.right;
    }
}
