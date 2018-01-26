#include <stdio.h>

typedef struct Node
{
	char *url;
	struct Node *next;
}Node,*pNode;

	/**
	 *
	 * 判断是否有环 
	 * 
	 **/
 
 int isLoop(pNode head)
 {
  	pNode fast = head;
 	pNode slow = head;
 	//如果无环，fast先走到终点
	//判断链表长度奇偶如果长度为奇数，fast->next为空，如果为偶数，fast->next为空 
	while(fast != NULL && fast->next != NULL)
	{
		//fast 每次移动两个节点
	    //slow 每次移动一个节点 
		fast = fast->next->next;
		slow = slow->next;
		if(fast == slow)
		{
			break;
		 } 
	 }
	 
	 if(fast == NULL || fast->next == NULL)
	     return 0;
	 else
	     return 1;  
	
 }
  
  
    /**
     *
	 * 得到环的入口点前一个环上的节点 
	 * 从头结点到入口的距离，等于转了n-1圈后起点到入口的距离 
	 **/
	 
	 pNode getLoopPreEntrance(pNode head){
	 	pNode fast = head;
	 	pNode slow = head;
	 	while(fast != NULL && slow != NULL)
	    {
	 	   fast = fast->next->next;
		   slow = slow->next;
		   if(fast == slow)
		   {
		     break;	
		   } 
	    }
	    if(fast == NULL || fast->next == NULL)
		   return NULL;
		slow = head;
		while(slow->next != fast->next)
		{
			slow = slow->next;
			fast = fast->next;
		}
		
		return fast;
	 } 
	 pNode getLastNode(pNode head)
	 {
	 	pNode p = head;
	 	while(p->next != NULL)
	 	{
	 		p = p->next;
		 }
		 return p;
	 }
	 void print(pNode head)
	 {
	 	pNode p;
	 	p = head;
		if(head != NULL)
		do
		{
			printf("%s , %d\n",p->url,p->next);
			p = p->next;
		 }while(p != NULL);
	 }
	 
	 int main(){
        
	 	Node web1[] = {{"www.baidu.com",web1+1},{"www.imooc.com",web1+2},{"www.google.com",web1+1}};
	 	Node web2[] = {{"www.taobao.com",web2+1},{"www..com",web2+2},{"www.google.com",web2+1}};
	 	pNode p1 = web1;
	 	pNode p2 = web2;
//	 	printf("%d", isLoop(web1));
	 	int isLoop1 = isLoop(web1);
	 	int isLoop2 = isLoop(web2);
	 	if(!isLoop1 && !isLoop2)
	 	{
	 		printf("两链表均不成环\n");
            pNode lastnode1 = getLastNode(web1);
            pNode lastnode2 = getLastNode(web2);
            if(lastnode1 == lastnode2)
               printf("两链表交叉\n");
			else
			   printf("两链表无交叉\n");	
		 }
		 else if(isLoop1 && isLoop2)
		 {
		 	printf("两链表均成环\n");
		    pNode prenode = getLoopPreEntrance(web1);
		    prenode->next = NULL;
		    int isLoop3 = isLoop(web2);
		    if(isLoop3)
		    	printf("两链表无交叉\n");
			else
			    printf("两链表交叉\n"); 
		 }
		 else
		 {
		    printf("两链表无交叉\n"); 
		 } 
	 	return 0;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
