//邻接矩阵表示的图类

import dataStructure.linearList.SeqList;              //顺序表类
//public class AdjMatrixGraph<E> implements GGraph<E>      //邻接矩阵表示的图类，实现图接口 
public class AdjMatrixGraph<E> extends AbstractGraph<E>    //邻接矩阵表示的图类，继承抽象图类 
{
    protected SeqList<E> vertexlist;                  //顺序表存储图的顶点集合
    protected int[][] adjmatrix;                      //图的邻接矩阵
    private final int MAX_WEIGHT = Integer.MAX_VALUE; //最大权值（表示无穷大∞）
//    private int edgeCount;                            //边数??
    
    public AdjMatrixGraph(int n)                      //构造方法，n指定最多顶点数
    {
        this.vertexlist = new SeqList<E>(n);          //构造指定容量的空顺序表
        this.adjmatrix = new int[n][n];               //构造n行n列空矩阵
        for (int i=0; i<n; i++)                       //初始化图的邻接矩阵
            for (int j=0; j<n; j++)
                this.adjmatrix[i][j]= (i==j) ? 0 : MAX_WEIGHT;  //边的权值为0或最大权值
//        this.edgeCount=0;
    }

    public AdjMatrixGraph(E[] vertices, Edge[] edges) //以顶点集合和边集合构造一个图
    {
        this(vertices.length);
        for (int i=0; i<vertices.length; i++)
            insertVertex(vertices[i]);                //插入一个顶点
        for (int j=0; j<edges.length; j++)
            insertEdge(edges[j]);                     //插入一条边        
    }

    public AdjMatrixGraph(SeqList<E> list, Edge[] edges) //以顶点集合和边集合构造一个图
    {
        this(list.length());
        this.vertexlist = list;
        for (int j=0; j<edges.length; j++)
            insertEdge(edges[j]);                     //插入一条边        
    }

    public int vertexCount()                          //返回顶点数
    {
        return this.vertexlist.length();              //返回顶点顺序表的元素个数
    }

    public E get(int i)                               //返回顶点vi的数据元素
    {
        return this.vertexlist.get(i);
    }

    public boolean insertVertex(E vertex)             //插入一个顶点，若插入成功，返回true
    {
        return this.vertexlist.add(vertex);           //在顺序表最后插入一个元素
    }
    
    public boolean insertEdge(int i, int j, int weight)    //插入一条权值为weight的边〈vi,vj〉
    {                                                 //若该边已存在，则不插入
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j && adjmatrix[i][j]==MAX_WEIGHT)
        {
            this.adjmatrix[i][j]=weight;
//            this.edgeCount++;
            return true;
        }
        return false;
    }
    public boolean insertEdge(Edge edge)              //插入一条边
    {
        if (edge!=null)
            return insertEdge(edge.start, edge.dest, edge.weight);
        return false;
    }

    public String toString()                          //获得图的顶点集合和邻接矩阵
    {
        String str= "顶点集合："+vertexlist.toString()+"\n";
        str += "邻接矩阵:  \n";              //edgeCount+"条边 \n";
        int n = vertexCount();                        //顶点数
        for (int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
                if (adjmatrix[i][j]==MAX_WEIGHT)
                    str += "  ∞";
                else
                    str += "  "+adjmatrix[i][j];
            str += "\n";
        }
        return str;
    }

    public boolean removeEdge(int i, int j)           //删除边〈vi,vj〉，i、j指定顶点序号
    {                                                 //若删除成功，返回true
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j && 
            this.adjmatrix[i][j]!=MAX_WEIGHT)  
        {
            this.adjmatrix[i][j]=MAX_WEIGHT;          //设置该边的权值为无穷大
//            this.edgeCount--;
            return true;
        }
        return false;
    }

    public boolean removeVertex(int v)                //删除序号为v的顶点及其关联的边
    {                                                 //若删除成功，返回true
        int n=vertexCount();                          //删除之前的顶点数
        if (v>=0 && v<n)
        {
            this.vertexlist.remove(v);                //删除顺序表的第i个元素，顶点数已减一
            for (int i=v; i<n-1; i++)
                for (int j=0; j<n; j++)
                    this.adjmatrix[i][j] = this.adjmatrix[i+1][j];  //元素向前一行移动

            for (int j=v; j<n-1; j++)
                for (int i=0; i<n-1; i++)
                    this.adjmatrix[i][j] = this.adjmatrix[i][j+1];  //元素向前一列移动
            return true;
        }
        return false;
    }    
    
    public int getFirstNeighbor(int v)                //返回顶点v的第一个邻接顶点的序号
    {                                                 //若不存在第一个邻接顶点，则返回-1
        return getNextNeighbor(v, -1);         
    }
    
    public int getNextNeighbor(int v, int w)          //返回v在w后的下一个邻接顶点的序号 
    {                                                 //若不存在下一个邻接顶点，则返回-1
        if (v>=0 && v<vertexCount() && w>=-1 && w<vertexCount() && v!=w)
            for (int j=w+1; j<vertexCount(); j++)     //w=-1时，j从0开始寻找下一个邻接顶点
                if (adjmatrix[v][j]>0 && adjmatrix[v][j]<MAX_WEIGHT)
                    return j;
        return -1;         
    }
    
    public AdjMatrixGraph minSpanTree_prim()          //构造带权图最小生成树的普里姆算法 
    {                                                 //返回最小生成树相应的图对象
        Edge[] mst = new Edge[vertexCount()-1];       //n个顶点最小生成树有n-1条边
        for (int i=0; i<mst.length; i++)              //初始化mst数组，从顶点v0出发构造最小生成树
            mst[i] = new Edge(0, i+1, adjmatrix[0][i+1]);  //保存从顶点v0到其他各顶点的边的权

        System.out.print("mst数组初值：");
        for(int j=0; j<mst.length; j++)               //显示mst数组的变化过程
            System.out.print(mst[j].toString());

        for (int i=0; i<mst.length; i++)              //共选出n-1条边
        {
            int minweight = MAX_WEIGHT;               //求最小权值
            int min = i;
            for (int j=i; j<mst.length; j++)          //寻找当前最小权值的边的顶点
                if (mst[j].weight<minweight)
                {
                    minweight = mst[j].weight;        //更新最小权值
                    min = j;                          //保存当前最小权值边的终点序号
                }
            
            Edge temp = mst[i];                       //交换最小权值的边
            mst[i] = mst[min];
            mst[min] = temp;

            int u = mst[i].dest;                      //刚并入U的顶点
            for (int j=i+1; j<mst.length; j++)        //调整mst[i+1]及其后元素为权值最小的边
            {
                int v = mst[j].dest;                  //原边在V-U中的终点
                if (adjmatrix[u][v]<mst[j].weight)    //若有权值更小的边(u,v)，则用(u,v)边替换原边
                {
                    mst[j].weight = adjmatrix[u][v];
                    mst[j].start = u;
                }
            }
            System.out.print("\nmst数组：");
            for(int j=0; j<mst.length; j++)           //显示mst数组的变化过程
                System.out.print(mst[j].toString());
        }        
        return new AdjMatrixGraph(this.vertexlist, mst);   //构造最小生成树相应的图对象
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
    
    public void shortestPath(int v)                   //以Dijkstra算法求带权图中顶点v的单源最短路径
    {
        int n = this.vertexCount();                   //顶点个数
        int[] dist = new int[n];                      //最短路径长度
        int path[] = new int[n];                      //最短路径的终点的前一个顶点
        int[] s = new int[n];                         //已求出最短路径的顶点集合，初值全为0
        
        s[v] = 1;                                     //源点在集合S中的标记
        for (int i=0; i<n; i++)                       //初始化dist和path数组
        {
            dist[i] = this.adjmatrix[v][i];
            if (i!=v && dist[i]<MAX_WEIGHT)
                path[i] = v;
            else
                path[i] = -1;    
        }
        System.out.print("\ns数组"+toString(s));
        System.out.print("\tpath数组"+toString(path));
        System.out.print("\tdist数组"+toString(dist));
        
        for (int i=1; i<n; i++)                       //寻找从v到顶点u的最短路径，u在V-S集合中
        {
            int mindist=MAX_WEIGHT, u=0;
            for (int j=0; j<n; j++)
                if (s[j]==0 && dist[j]<mindist)
                {
                    u = j;
                    mindist = dist[j];
                }
//            if (mindist==MAX_WEIGHT)                  //若没有其他最短路径则算法结束；此语句对非连通图是必须的
//                return; 

            s[u] = 1;                                 //确定一条最短路径的终点u并入集合S
            for (int j=0; j<n; j++)                   //调整从v到V-S中其他顶点的最短路径及长度
                if(s[j]==0 && this.adjmatrix[u][j]<MAX_WEIGHT && dist[u]+this.adjmatrix[u][j]<dist[j])
                {
                    dist[j] = dist[u] + this.adjmatrix[u][j];
                    path[j] = u;
                }    

            System.out.print("\ns数组"+toString(s));
            System.out.print("\tpath数组"+toString(path));
            System.out.print("\tdist数组"+toString(dist));
        }

        System.out.println("\n从顶点"+get(v)+"到其他顶点的最短路径如下：");
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
            pathstr = "("+get(v)+pathstr+")，路径长度为"+dist[i];
            System.out.println(pathstr);
            i = (i+1)%n;
        }
    }
}
/*
未实现
     public boolean removeVertex(E vertex)             //删除顶点vertex及其关联的边
    {
        return this.vertexlist.remove(vertex);        //删除顺序表中值为vertex的元素
    }
    
*/
