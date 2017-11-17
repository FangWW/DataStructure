//����ͨͼ������������������͹��������������

import dataStructure.linearList.SeqQueue;                  //˳��ѭ��������

public abstract class AbstractGraph<E> implements GGraph<E>//����ͼ�� 
{
    public abstract int vertexCount();                     //���ض�����������������ʵ��
    public abstract E get(int i);                          //���ض���vi��������
    public abstract int getFirstNeighbor(int i);           //���ض���vi�ĵ�һ���ڽӶ�������
    public abstract int getNextNeighbor(int i, int j);     //����vi��vj�����һ���ڽӶ�������  
//    public abstract AbstractGraph prim();


    public void DFSTraverse(int v)                         //�Ӷ���v�����Է���ͨͼ��һ�����������������
    {
        boolean[] visited = new boolean[vertexCount()];    //���ʱ�����飬Ԫ�س�ֵΪfalse����ʾδ������
        int i=v;
        do
        {
            if (!visited[i])                               //������viδ������
            {
                System.out.print("{ ");
                depthfs(i, visited);                       //�Ӷ���vi������һ�����������������
                System.out.print("} ");
            }
            i = (i+1) % vertexCount();                     //��������ͨ������Ѱ��δ�����ʶ���
        } while (i!=v);
        System.out.println();
    }

    private void depthfs(int v, boolean[] visited)         //�Ӷ���v��ʼ����һ�����������������
    {                                                      //����һ����ͨ����
        System.out.print(this.get(v)+" ");                 //���ʸö���
        visited[v] = true;                                 //���ѷ��ʱ��
        int w = getFirstNeighbor(v);                       //��õ�һ���ڽӶ���
        while (w!=-1)                                      //�������ڽӶ���
        {
            if(!visited[w])                                //���ڽӶ���wδ������
                depthfs(w, visited);                       //��w������������������������ݹ����
            w = getNextNeighbor(v, w);                     //����v��w�����һ���ڽӶ�������
        }
    }

    public void BFSTraverse(int v)                         //�Ӷ���v�����Է���ͨͼ����һ�ι��������������
    {
        boolean[] visited = new boolean[vertexCount()];    //���ʱ������
        int i=v;
        do
        {
            if (!visited[i])                               //������viδ������
            {
                System.out.print("{ ");
                breadthfs(i, visited);                     //�Ӷ���vi�����Ĺ��������������
                System.out.print("} ");
            }
            i = (i+1) % vertexCount();                     //��������ͨ������Ѱ��δ�����ʶ���
        } while (i!=v);
        System.out.println();
    }
        
    private void breadthfs(int v, boolean[] visited)       //�Ӷ���v�����Ĺ��������������
    {                                                      //����һ����ͨ����
        System.out.print(this.get(v)+" ");
        visited[v] = true;
        SeqQueue<Integer> que = new SeqQueue<Integer>(vertexCount());   //����˳�����
        que.enqueue(new Integer(v));                       //���ʹ��Ķ���v��������
        while (!que.isEmpty())                             //�����в���ʱѭ��
        {
            v = que.dequeue().intValue();                  //����
            int w = getFirstNeighbor(v);                   //��ö���v�ĵ�һ���ڽӶ������
            while (w!=-1)                                  //���ڽӶ������ʱѭ��
            {
                if (!visited[w])                           //���ö���δ���ʹ�
                {
                    System.out.print(this.get(w)+" ");     //���ʶ���
                    visited[w] = true;
                    que.enqueue(new Integer(w));           //���ʹ��Ķ���w��������
                }
                w = getNextNeighbor(v, w);                 //����v��w�����һ���ڽӶ�������
            }
        }
    }
    
    
    
    
    
}
/*
	public Edge[] prim()                          //
	{
        Edge[] mst = Edge;
		int n = g.getNumOfVertices();
		int minCost;
		int[] lowCost = new int[n];
		int k = 0;
		
		for(int i = 1; i < n; i ++)
			lowCost[i] = g.getWeight(0, i);
		MinSpanTree temp = new MinSpanTree();
		temp.vertex = g.getValue(0);	
		closeVertex[0] = temp;
		lowCost[0] = - 1;
		
		for(int i = 1; i < n; i ++){
			minCost = maxWeight;
			for(int j = 1; j < n; j ++){
				if(lowCost[j] < minCost && lowCost[j] > 0){
					minCost = lowCost[j];
					k = j;
				}
			}
			
			MinSpanTree curr = new MinSpanTree();
			curr.vertex = g.getValue(k);
			curr.weight = minCost;
			closeVertex[i] = curr;
			lowCost[k] = -1;
			
			for(int j = 1; j < n; j ++){
				if(g.getWeight(k, j) < lowCost[j])
					lowCost[j] = g.getWeight(k, j);
			}
		}	
	}
}

public class Graph3 extends Graph1       //ͼ�ı��� 
{
    protected int tree[][];              //ͼ�����������ڽӾ��� 
    Graph3(int m1[][])                   //���ڽӾ����ʾͼ
    {
        super(m1);
        tree = new int [n][n];
    }
    Graph3()
    {    }   
    public void prim()
    {
        int i=0,j=0,min=10000,min_i=0,min_j=0;
        int n=0;
        while (n<this.mat.length-1)             //�ҳ�Ȩֵ��С�ı�
        {
                min=10000;
                min_i=0;
                min_j=0;
        for (i=0;i<visited.length;i++)
        {
            if (visited[i]!=0)
            {
                for (j=0;j<this.mat[i].length;j++)
                    if ((this.mat[i][j]!=0) && (this.mat[i][j]<min))
                    {
                        min = this.mat[i][j];
                        min_i = i;
                        min_j = j;
                    }
            }
        }
        System.out.println("min="+min+" min_i="+min_i+" min_j="+min_j);
        tree[min_i][min_j] = this.mat[min_i][min_j];
        this.mat[min_i][min_j] = 0;
        visited[min_i] = 1;
        n++;
        output(tree);
        }
    }
    public void kruskal()
    {
    }
    public static void main(String args[])
    {
        int n=5,k;
        int mat3[][] = {{0,2,4,5,0},         //�����Ȩͼ���ڽӾ��� 
                        {2,0,8,0,9},
                        {4,8,0,6,3},
                        {5,0,6,0,7},
                        {0,9,3,7,0}};
        Graph3 g3 = new Graph3(mat3);
        System.out.print("��������");
        g3.prim();
    }
}

/*
        for (i=0;i<this.mat.length;i++)
            for (j=0;j<this.mat[i].length;j++)
                if ((this.mat[i][j]!=0) && (this.mat[i][j]<min))
                {
                   min = this.mat[i][j];
                   min_i = i;
                   min_j = j;
                }
        System.out.println("min="+min+" min_i="+min_i+" min_j="+min_j);
        tree[min_i][min_j] = this.mat[min_i][min_j];
        this.mat[min_i][min_j] = 0;
        visited[min_i] = 1;
        n=1;
        output(tree);
        
        System.out.println("������ȱ���Depth first search:");
        for (k=0;k<n;k++)
        {
            g3.depthfs(k);
            System.out.println();
            g3.unvisited();
        }
        System.out.println("������ȱ���Breath first search:");
        for (k=0;k<n;k++)
        {
            g3.breadthfs(k);
            System.out.println();
            g3.unvisited();
        }

    public void output()                     //����ڽӱ�
    {
        super.output();                      //����ڽӾ���  
        int i=0;
        OnelinkNode p;
        System.out.println("�ڽӱ�table:");
        for (i=0;i<table.length;i++)
        {
            System.out.print("table["+i+"]= ");
            if (table[i] != null)
            {
                System.out.print(table[i].vertex);
                p = table[i].next;
                while (p!=null) 
                {
                    System.out.print(" ->  "+p.number+","+p.weight);
                    p = p.next;
                }
            }
            System.out.println("  null");
        }
    }
*/
//}
