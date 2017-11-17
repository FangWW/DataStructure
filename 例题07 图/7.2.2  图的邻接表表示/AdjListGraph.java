//图的邻接表

import dataStructure.linearList.SeqList;              //顺序表类
import dataStructure.linearList.SortedHSLinkedList;   //排序的带头结点的单链表类
import java.util.Iterator;                       //导入迭代器接口

//public class AdjListGraph<E> implements GGraph<E>     //邻接表表示的图类 
public class AdjListGraph<E> extends AbstractGraph<E> //邻接表表示的图类 
{
    protected SeqList<Vertex<E>> vertexlist;          //顶点表
//    private int edgeCount;                            //边数??
    
    public AdjListGraph(int n)                        //构造方法，n指定顶点数
    {
        this.vertexlist = new SeqList<Vertex<E>>(n);
//        this.edgeCount=0;
    }

    public AdjListGraph(E[] vertices, Edge[] edges)   //以顶点集合和边集合构造一个图
    {
        this(vertices.length);
        for (int i=0; i<vertices.length; i++)
            insertVertex(vertices[i]);                //插入一个顶点
        for (int j=0; j<edges.length; j++)
            insertEdge(edges[j]);                     //插入一条边        
    }

    public int vertexCount()                          //返回顶点数
    {
        return this.vertexlist.length();
    }

    public E get(int i)                               //返回顶点vi的数据元素
    {
        return this.vertexlist.get(i).data;
    }

    public boolean insertVertex(E vertex)             //插入一个顶点，若插入成功，返回true
    {
        return this.vertexlist.add(new Vertex<E>(vertex)); //在顺序表最后插入一个元素
    }

    public boolean insertEdge(int i, int j, int weight)    //插入一条权值为weight的边〈vi,vj〉
    { 
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j)
        {
            SortedHSLinkedList slink = this.vertexlist.get(i).adjlink;
            return slink.add(new Edge(i,j,weight));        //在第i条单链表最后增加边结点
//                this.edgeCount++;
        }
        return false;
    }

    public boolean insertEdge(Edge edge)              //插入一条边
    {        
        if (edge!=null)
            return insertEdge(edge.start, edge.dest, edge.weight);
        return false;
    }

    public String toString()                          //获得图的顶点集合和邻接表
    {
        String str= "顶点集合："+vertexlist.toString()+"\n";
        str += "出边表：\n ";                  //+edgeCount+"条边 \n";
        for (int i=0; i<vertexCount(); i++)
            str += this.vertexlist.get(i).adjlink.toString()+"\n";   //遍历第i条单链表
        return str;
    }

    public boolean removeEdge(int i, int j)           //删除边〈vi,vj〉，i、j指定顶点序号
    {
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j)
        {
            SortedHSLinkedList<Edge> slink = this.vertexlist.get(i).adjlink;  //获得第i条边单链表
            return slink.remove(new Edge(i,j,1));
        }
        return false;
    }

//？    public boolean removeEdge(Edge edge)           //删除边〈vi,vj〉，i、j指定顶点序号

    public boolean removeVertex(int v)                //删除序号为v的顶点及其关联的边
    {                                                 //若删除成功，返回true
        int n=vertexCount();                          //删除之前的顶点数
        if (v>=0 && v<n)
        {
            SortedHSLinkedList<Edge> slink = this.vertexlist.get(v).adjlink;  //获得欲删除的第v条边单链表
            int i=0;
            Edge edge = slink.get(i);
            while (edge!=null)
            {
                this.removeEdge(edge.dest, edge.start);    //删除对称的边
                i++;
                edge = slink.get(i);
            }
            this.vertexlist.remove(v);                //删除顺序表的第i个元素，顶点数已减一

            for (i=0; i<n-1; i++)                     //未删除的边结点更改某些顶点序号
            {
                slink = this.vertexlist.get(i).adjlink;    //获得第i条边单链表
                int j=0;
                edge = slink.get(j);
                while (edge!=null)
                {
                    if (edge.start>v)
                        edge.start--;                      //顶点序号减一
                    if (edge.dest>v)
                     edge.dest--;                     
                    j++;
                    edge = slink.get(j);
                }
            } 
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
        if (v>=0 && v<vertexCount() && w>=-1 && w<vertexCount())
        {
            SortedHSLinkedList<Edge> slink = this.vertexlist.get(v).adjlink;  //获得第v条边单链表
            Edge edge = slink.get(0);                 //返回单链表的第一个结点表示的边
            int i=0;
            while (edge!=null)                        //寻找下一个邻接顶点
            {
                if (edge.dest>w)
                    return edge.dest;                 //返回下一个邻接顶点的序号
                i++;
                edge = slink.get(i);                  //返回单链表的第一个结点表示的边
            }
        }
        return -1;
    }
}        

/*
    //基于单链表的迭代器，算法好像有问题，不能获得链表的第一个结点？？
    public int getNextNeighbor(int v, int w)          //返回v在w后的下一个邻接顶点的序号 
    {                                                 //若不存在下一个邻接顶点，则返回-1
        if (v>=0 && v<vertexCount() && w>=-1 && w<vertexCount())
        {
            SortedHSLinkedList<Edge> slink = this.vertexlist.get(v).adjlink;  //获得第v条边单链表
            Iterator<Edge> it = slink.iterator();           //it是单链表的迭代器
            while (it.hasNext())                     	//若有后继元素
            {
                Edge edge = it.next();               	//返回单链表的第一条边，相当于slink.get(0)    
                System.out.print(edge.toString()+" ");                 //访问该顶点
                if (edge.dest>w)
            	    return edge.dest;                      //返回下一个邻接顶点的序号
            }  
        }
        return -1;
    }

*/