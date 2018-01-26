#include <stdio.h>

typedef struct Node
{
	char *url;
	struct Node *next;
}Node,*pNode;

	/**
	 *
	 * �ж��Ƿ��л� 
	 * 
	 **/
 
 int isLoop(pNode head)
 {
  	pNode fast = head;
 	pNode slow = head;
 	//����޻���fast���ߵ��յ�
	//�ж���������ż�������Ϊ������fast->nextΪ�գ����Ϊż����fast->nextΪ�� 
	while(fast != NULL && fast->next != NULL)
	{
		//fast ÿ���ƶ������ڵ�
	    //slow ÿ���ƶ�һ���ڵ� 
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
	 * �õ�������ڵ�ǰһ�����ϵĽڵ� 
	 * ��ͷ��㵽��ڵľ��룬����ת��n-1Ȧ����㵽��ڵľ��� 
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
	 		printf("����������ɻ�\n");
            pNode lastnode1 = getLastNode(web1);
            pNode lastnode2 = getLastNode(web2);
            if(lastnode1 == lastnode2)
               printf("��������\n");
			else
			   printf("�������޽���\n");	
		 }
		 else if(isLoop1 && isLoop2)
		 {
		 	printf("��������ɻ�\n");
		    pNode prenode = getLoopPreEntrance(web1);
		    prenode->next = NULL;
		    int isLoop3 = isLoop(web2);
		    if(isLoop3)
		    	printf("�������޽���\n");
			else
			    printf("��������\n"); 
		 }
		 else
		 {
		    printf("�������޽���\n"); 
		 } 
	 	return 0;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
