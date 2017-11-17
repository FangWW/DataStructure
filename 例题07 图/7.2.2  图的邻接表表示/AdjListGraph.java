//ͼ���ڽӱ�

import dataStructure.linearList.SeqList;              //˳�����
import dataStructure.linearList.SortedHSLinkedList;   //����Ĵ�ͷ���ĵ�������
import java.util.Iterator;                       //����������ӿ�

//public class AdjListGraph<E> implements GGraph<E>     //�ڽӱ��ʾ��ͼ�� 
public class AdjListGraph<E> extends AbstractGraph<E> //�ڽӱ��ʾ��ͼ�� 
{
    protected SeqList<Vertex<E>> vertexlist;          //�����
//    private int edgeCount;                            //����??
    
    public AdjListGraph(int n)                        //���췽����nָ��������
    {
        this.vertexlist = new SeqList<Vertex<E>>(n);
//        this.edgeCount=0;
    }

    public AdjListGraph(E[] vertices, Edge[] edges)   //�Զ��㼯�Ϻͱ߼��Ϲ���һ��ͼ
    {
        this(vertices.length);
        for (int i=0; i<vertices.length; i++)
            insertVertex(vertices[i]);                //����һ������
        for (int j=0; j<edges.length; j++)
            insertEdge(edges[j]);                     //����һ����        
    }

    public int vertexCount()                          //���ض�����
    {
        return this.vertexlist.length();
    }

    public E get(int i)                               //���ض���vi������Ԫ��
    {
        return this.vertexlist.get(i).data;
    }

    public boolean insertVertex(E vertex)             //����һ�����㣬������ɹ�������true
    {
        return this.vertexlist.add(new Vertex<E>(vertex)); //��˳���������һ��Ԫ��
    }

    public boolean insertEdge(int i, int j, int weight)    //����һ��ȨֵΪweight�ıߡ�vi,vj��
    { 
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j)
        {
            SortedHSLinkedList slink = this.vertexlist.get(i).adjlink;
            return slink.add(new Edge(i,j,weight));        //�ڵ�i��������������ӱ߽��
//                this.edgeCount++;
        }
        return false;
    }

    public boolean insertEdge(Edge edge)              //����һ����
    {        
        if (edge!=null)
            return insertEdge(edge.start, edge.dest, edge.weight);
        return false;
    }

    public String toString()                          //���ͼ�Ķ��㼯�Ϻ��ڽӱ�
    {
        String str= "���㼯�ϣ�"+vertexlist.toString()+"\n";
        str += "���߱�\n ";                  //+edgeCount+"���� \n";
        for (int i=0; i<vertexCount(); i++)
            str += this.vertexlist.get(i).adjlink.toString()+"\n";   //������i��������
        return str;
    }

    public boolean removeEdge(int i, int j)           //ɾ���ߡ�vi,vj����i��jָ���������
    {
        if (i>=0 && i<vertexCount() && j>=0 && j<vertexCount() && i!=j)
        {
            SortedHSLinkedList<Edge> slink = this.vertexlist.get(i).adjlink;  //��õ�i���ߵ�����
            return slink.remove(new Edge(i,j,1));
        }
        return false;
    }

//��    public boolean removeEdge(Edge edge)           //ɾ���ߡ�vi,vj����i��jָ���������

    public boolean removeVertex(int v)                //ɾ�����Ϊv�Ķ��㼰������ı�
    {                                                 //��ɾ���ɹ�������true
        int n=vertexCount();                          //ɾ��֮ǰ�Ķ�����
        if (v>=0 && v<n)
        {
            SortedHSLinkedList<Edge> slink = this.vertexlist.get(v).adjlink;  //�����ɾ���ĵ�v���ߵ�����
            int i=0;
            Edge edge = slink.get(i);
            while (edge!=null)
            {
                this.removeEdge(edge.dest, edge.start);    //ɾ���ԳƵı�
                i++;
                edge = slink.get(i);
            }
            this.vertexlist.remove(v);                //ɾ��˳���ĵ�i��Ԫ�أ��������Ѽ�һ

            for (i=0; i<n-1; i++)                     //δɾ���ı߽�����ĳЩ�������
            {
                slink = this.vertexlist.get(i).adjlink;    //��õ�i���ߵ�����
                int j=0;
                edge = slink.get(j);
                while (edge!=null)
                {
                    if (edge.start>v)
                        edge.start--;                      //������ż�һ
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

    public int getFirstNeighbor(int v)                //���ض���v�ĵ�һ���ڽӶ�������
    {                                                 //�������ڵ�һ���ڽӶ��㣬�򷵻�-1
        return getNextNeighbor(v, -1);         
    }
    
     
    public int getNextNeighbor(int v, int w)          //����v��w�����һ���ڽӶ������� 
    {                                                 //����������һ���ڽӶ��㣬�򷵻�-1
        if (v>=0 && v<vertexCount() && w>=-1 && w<vertexCount())
        {
            SortedHSLinkedList<Edge> slink = this.vertexlist.get(v).adjlink;  //��õ�v���ߵ�����
            Edge edge = slink.get(0);                 //���ص�����ĵ�һ������ʾ�ı�
            int i=0;
            while (edge!=null)                        //Ѱ����һ���ڽӶ���
            {
                if (edge.dest>w)
                    return edge.dest;                 //������һ���ڽӶ�������
                i++;
                edge = slink.get(i);                  //���ص�����ĵ�һ������ʾ�ı�
            }
        }
        return -1;
    }
}        

/*
    //���ڵ�����ĵ��������㷨���������⣬���ܻ������ĵ�һ����㣿��
    public int getNextNeighbor(int v, int w)          //����v��w�����һ���ڽӶ������� 
    {                                                 //����������һ���ڽӶ��㣬�򷵻�-1
        if (v>=0 && v<vertexCount() && w>=-1 && w<vertexCount())
        {
            SortedHSLinkedList<Edge> slink = this.vertexlist.get(v).adjlink;  //��õ�v���ߵ�����
            Iterator<Edge> it = slink.iterator();           //it�ǵ�����ĵ�����
            while (it.hasNext())                     	//���к��Ԫ��
            {
                Edge edge = it.next();               	//���ص�����ĵ�һ���ߣ��൱��slink.get(0)    
                System.out.print(edge.toString()+" ");                 //���ʸö���
                if (edge.dest>w)
            	    return edge.dest;                      //������һ���ڽӶ�������
            }  
        }
        return -1;
    }

*/