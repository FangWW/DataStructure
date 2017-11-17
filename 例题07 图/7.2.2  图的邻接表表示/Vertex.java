//ͼ���ڽӱ��ʾ�������Ԫ����

import dataStructure.linearList.SortedHSLinkedList;   //����Ĵ�ͷ���ĵ�������

public class Vertex<E>                                //�����Ԫ��
{
    public E data;                                    //����������
    public SortedHSLinkedList<Edge> adjlink;          //�ö���ıߵ�����
    
    public Vertex(E data, SortedHSLinkedList<Edge> adjlink)
    {
        this.data = data;
        this.adjlink = adjlink;
    }

    public Vertex(E data)
    {
        this(data, new SortedHSLinkedList<Edge>());   //������ʱ�����յ�����
    }

    public String toString() 
    {
        return this.data.toString();
    }
}   
