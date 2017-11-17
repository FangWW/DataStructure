//图的邻接表表示，顶点表元素类

import dataStructure.linearList.SortedHSLinkedList;   //排序的带头结点的单链表类

public class Vertex<E>                                //顶点表元素
{
    public E data;                                    //顶点数据域
    public SortedHSLinkedList<Edge> adjlink;          //该顶点的边单链表
    
    public Vertex(E data, SortedHSLinkedList<Edge> adjlink)
    {
        this.data = data;
        this.adjlink = adjlink;
    }

    public Vertex(E data)
    {
        this(data, new SortedHSLinkedList<Edge>());   //构造结点时创建空单链表
    }

    public String toString() 
    {
        return this.data.toString();
    }
}   
