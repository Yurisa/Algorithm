#include <cstdio>  
#include <cstdlib>  

#define vNum 7

int dfn;
void findArticul(int Gragh[][vNum], int visit[], int low[]);
void DFSArticul(int Gragh[][vNum], int v, int visit[], int low[]);

int main(){
	
	//构造无向连通图的邻接表数据结构 
	int Gragh[vNum][vNum] = {  
        {0, 1, 3, -1},  
        {1, 0, 2, 3, 4, -1},  
        {2, 1, 3, 4, 5, -1},  
        {3, 0, 1, 2, 4, -1},  
        {4, 1, 2, 3, 5, -1},  
        {5, 4, 6, -1},  
        {6, 5, -1}        
    };
	
	int low[vNum];
	int visit[vNum];
	for(int i = 0; i < vNum; i++){
		visit[vNum] = 0;
	}
	  
    findArticul(Gragh, visit, low);
	
	system("pause");  
    return 0;   
    
}

void findArticul(int Gragh[][vNum], int visit[], int low[]){
	dfn = 1;
	visit[Gragh[0][0]] = 1;
	
	for(int i = 1; i < vNum; i++){
		visit[i] = 0;    //0 初始值为0，表示没有访问的节点 
	}
	
	int v = Gragh[0][1];
	DFSArticul(Gragh, v, visit, low);
	
	if(dfn < vNum){     //生成树的根至少有两棵子树 
		printf("%d ", v);   // 
		for(int j = 1; Gragh[0][j] != -1; j++){
			if(visit[Gragh[0][j]] == 0){
			   DFSArticul(Gragh, Gragh[0][j], visit, low);	
			}
		}
	}
	
}

void DFSArticul(int Gragh[][vNum], int v, int visit[], int low[]){
	dfn++;
	int min = dfn;
	visit[v] = dfn;
	for (int j = 1; Gragh[v][j] != -1; j++){
		int w = Gragh[v][j];
		if(visit[w] == 0){
			DFSArticul(Gragh, w, visit, low);
			if(low[w] < min)
			   min = low[w];
			if(low[w] >= visit[v])
			   printf("%d ", v);
		}else if(visit[w] < min){
			min = visit[w];
		}
		
	}
	
	low[v] = min;
}

