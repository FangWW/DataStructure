//�ڽӾ����ʾ��ͼ��

import dataStructure.linearList.SeqList;              //˳�����
//public class AdjMatrixGraph<E> implements GGraph<E>      //�ڽӾ����ʾ��ͼ�࣬ʵ��ͼ�ӿ� 
public class AdjMatrixGraph<E> extends AbstractGraph<E>    //�ڽӾ����ʾ��ͼ�࣬�̳г���ͼ�� 
{
    protected SeqList<E> vertexlist;                  //˳���洢ͼ�Ķ��㼯��
    protected int[][] adjmatrix;                      //ͼ���ڽӾ���
    private final int MAX_WEIGHT = Integer.MAX_VALUE; //���Ȩֵ����ʾ�����ޣ�
//    private int edgeCount;                            //����??
    
    public AdjMatrixGraph(int n)                      //���췽����nָ����ඥ����
    {
        this.vertexlist = new SeqList<E>(n);          //����ָ�������Ŀ�˳���
        this.adjmatrix = new int[n][n];               //����n��n�пվ���
        for (int i=0; i<n; i++)                       //��ʼ��ͼ���ڽӾ���
            for (int j=0; j<n; j++)
                this.adjmatrix[i][j]= (i==j) ? 0 : MAX_WEIGHT;  //�ߵ�ȨֵΪ0�����Ȩֵ
//        this.edgeCount=0;
    }

    public AdjMatrixGraph(E[] vertices, Edge[] edges) //�Զ��㼯�Ϻͱ߼��Ϲ���һ��ͼ
    {
        this(vertices.length);
        for (int i=0; i<vertices.length; i++)
            insertVertex(vertices[i]);                //����һ������
        for (int j=0; j<edges.length; j++)
            insertEdge(edges[j]);                     //����һ����        
    }

    public AdjMatrixGraph(SeqList<E> list, Edge[] edges) //�Զ��㼯�Ϻͱ߼��Ϲ���һ��ͼ
    {
        this(list.length());
        this.vertexlist = list;
        for (int j=0; j<edges.length; j++)
            insertEdge(edges[j]);                     //����һ����        
    }

    public int vertexCount()                          //���ض�����
    {
        return this.vertexlist.length();              //���ض���˳����Ԫ�ظ���
    }

    public E get(int i)                               //���ض���vi������Ԫ��
    {
        return this.vertexlist.get(i);
    }

    public boolean insertVertex(E vertex)             //����һ�����㣬������ɹ�������true
    {
        return this.vertexlist.add(vertex);           //��˳���������һ��Ԫ��
    }
    
    public boolean insertEdge(int i, int j, int weight)    //����һ��ȨֵΪweight�ıߡ�vi,vj��
    {                                                 //���ñ��Ѵ��ڣ��򲻲���
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j && adjmatrix[i][j]==MAX_WEIGHT)
        {
            this.adjmatrix[i][j]=weight;
//            this.edgeCount++;
            return true;
        }
        return false;
    }
    public boolean insertEdge(Edge edge)              //����һ����
    {
        if (edge!=null)
            return insertEdge(edge.start, edge.dest, edge.weight);
        return false;
    }

    public String toString()                          //���ͼ�Ķ��㼯�Ϻ��ڽӾ���
    {
        String str= "���㼯�ϣ�"+vertexlist.toString()+"\n";
        str += "�ڽӾ���:  \n";              //edgeCount+"���� \n";
        int n = vertexCount();                        //������
        for (int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
                if (adjmatrix[i][j]==MAX_WEIGHT)
                    str += "  ��";
                else
                    str += "  "+adjmatrix[i][j];
            str += "\n";
        }
        return str;
    }

    public boolean removeEdge(int i, int j)           //ɾ���ߡ�vi,vj����i��jָ���������
    {                                                 //��ɾ���ɹ�������true
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j && 
            this.adjmatrix[i][j]!=MAX_WEIGHT)  
        {
            this.adjmatrix[i][j]=MAX_WEIGHT;          //���øñߵ�ȨֵΪ�����
//            this.edgeCount--;
            return true;
        }
        return false;
    }

    public boolean removeVertex(int v)                //ɾ�����Ϊv�Ķ��㼰������ı�
    {                                                 //��ɾ���ɹ�������true
        int n=vertexCount();                          //ɾ��֮ǰ�Ķ�����
        if (v>=0 && v<n)
        {
            this.vertexlist.remove(v);                //ɾ��˳���ĵ�i��Ԫ�أ��������Ѽ�һ
            for (int i=v; i<n-1; i++)
                for (int j=0; j<n; j++)
                    this.adjmatrix[i][j] = this.adjmatrix[i+1][j];  //Ԫ����ǰһ���ƶ�

            for (int j=v; j<n-1; j++)
                for (int i=0; i<n-1; i++)
                    this.adjmatrix[i][j] = this.adjmatrix[i][j+1];  //Ԫ����ǰһ���ƶ�
            return true;
        }
        return false;
    }    
    
    public int getFirstNeighbor(int v)                //���ض���v�ĵ�һ���ڽӶ�������
    {                                                 //�������ڵ�һ���ڽӶ��㣬�򷵻�-1
        return getNextNeighbor(v, -1);         
    }
    
    public int getNextNeighbor(int v, int w)          //����v��w�����һ���ڽӶ������� 
    {                                                 //����������һ���ڽӶ��㣬�򷵻�-1
        if (v>=0 && v<vertexCount() && w>=-1 && w<vertexCount() && v!=w)
            for (int j=w+1; j<vertexCount(); j++)     //w=-1ʱ��j��0��ʼѰ����һ���ڽӶ���
                if (adjmatrix[v][j]>0 && adjmatrix[v][j]<MAX_WEIGHT)
                    return j;
        return -1;         
    }
    
    public AdjMatrixGraph minSpanTree_prim()          //�����Ȩͼ��С������������ķ�㷨 
    {                                                 //������С��������Ӧ��ͼ����
        Edge[] mst = new Edge[vertexCount()-1];       //n��������С��������n-1����
        for (int i=0; i<mst.length; i++)              //��ʼ��mst���飬�Ӷ���v0����������С������
            mst[i] = new Edge(0, i+1, adjmatrix[0][i+1]);  //����Ӷ���v0������������ıߵ�Ȩ

        System.out.print("mst�����ֵ��");
        for(int j=0; j<mst.length; j++)               //��ʾmst����ı仯����
            System.out.print(mst[j].toString());

        for (int i=0; i<mst.length; i++)              //��ѡ��n-1����
        {
            int minweight = MAX_WEIGHT;               //����СȨֵ
            int min = i;
            for (int j=i; j<mst.length; j++)          //Ѱ�ҵ�ǰ��СȨֵ�ıߵĶ���
                if (mst[j].weight<minweight)
                {
                    minweight = mst[j].weight;        //������СȨֵ
                    min = j;                          //���浱ǰ��СȨֵ�ߵ��յ����
                }
            
            Edge temp = mst[i];                       //������СȨֵ�ı�
            mst[i] = mst[min];
            mst[min] = temp;

            int u = mst[i].dest;                      //�ղ���U�Ķ���
            for (int j=i+1; j<mst.length; j++)        //����mst[i+1]�����Ԫ��ΪȨֵ��С�ı�
            {
                int v = mst[j].dest;                  //ԭ����V-U�е��յ�
                if (adjmatrix[u][v]<mst[j].weight)    //����Ȩֵ��С�ı�(u,v)������(u,v)���滻ԭ��
                {
                    mst[j].weight = adjmatrix[u][v];
                    mst[j].start = u;
                }
            }
            System.out.print("\nmst���飺");
            for(int j=0; j<mst.length; j++)           //��ʾmst����ı仯����
                System.out.print(mst[j].toString());
        }        
        return new AdjMatrixGraph(this.vertexlist, mst);   //������С��������Ӧ��ͼ����
    }

    private String toString(int[] table)
    {
        if (table!=null && table.length>0)
        {
            String str="{";
            for(int i=0; i<table.length-1; i++)
                str += table[i]+",";
            return str+table[table.length-1]+"}";
        }
        return null;        
    }
    
    public void shortestPath(int v)                   //��Dijkstra�㷨���Ȩͼ�ж���v�ĵ�Դ���·��
    {
        int n = this.vertexCount();                   //�������
        int[] dist = new int[n];                      //���·������
        int path[] = new int[n];                      //���·�����յ��ǰһ������
        int[] s = new int[n];                         //��������·���Ķ��㼯�ϣ���ֵȫΪ0
        
        s[v] = 1;                                     //Դ���ڼ���S�еı��
        for (int i=0; i<n; i++)                       //��ʼ��dist��path����
        {
            dist[i] = this.adjmatrix[v][i];
            if (i!=v && dist[i]<MAX_WEIGHT)
                path[i] = v;
            else
                path[i] = -1;    
        }
        System.out.print("\ns����"+toString(s));
        System.out.print("\tpath����"+toString(path));
        System.out.print("\tdist����"+toString(dist));
        
        for (int i=1; i<n; i++)                       //Ѱ�Ҵ�v������u�����·����u��V-S������
        {
            int mindist=MAX_WEIGHT, u=0;
            for (int j=0; j<n; j++)
                if (s[j]==0 && dist[j]<mindist)
                {
                    u = j;
                    mindist = dist[j];
                }
//            if (mindist==MAX_WEIGHT)                  //��û���������·�����㷨�����������Է���ͨͼ�Ǳ����
//                return; 

            s[u] = 1;                                 //ȷ��һ�����·�����յ�u���뼯��S
            for (int j=0; j<n; j++)                   //������v��V-S��������������·��������
                if(s[j]==0 && this.adjmatrix[u][j]<MAX_WEIGHT && dist[u]+this.adjmatrix[u][j]<dist[j])
                {
                    dist[j] = dist[u] + this.adjmatrix[u][j];
                    path[j] = u;
                }    

            System.out.print("\ns����"+toString(s));
            System.out.print("\tpath����"+toString(path));
            System.out.print("\tdist����"+toString(dist));
        }

        System.out.println("\n�Ӷ���"+get(v)+"��������������·�����£�");
        int i=v+1;
        while (i!=v)
        {
            int j=i;            
            String pathstr="";
            while (path[j]!=-1)
            {
                pathstr = ","+get(j)+pathstr;
                j=path[j];
            }                            
            pathstr = "("+get(v)+pathstr+")��·������Ϊ"+dist[i];
            System.out.println(pathstr);
            i = (i+1)%n;
        }
    }
}
/*
δʵ��
     public boolean removeVertex(E vertex)             //ɾ������vertex��������ı�
    {
        return this.vertexlist.remove(vertex);        //ɾ��˳�����ֵΪvertex��Ԫ��
    }
    
*/
