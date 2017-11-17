//��ͷ���ĵ�������
//���飬��������Ա����rear��n������ȫ��ά�����ѣ�������Ҫͬʱ�޸�3����Ա�������׳���

package dataStructure.linearList;
import dataStructure.linearList.Node;            //���뵥��������
import java.util.Iterator;                       //����������ӿ�

public class HSLinkedList<E> extends AbstractList<E> implements LList<E> //��ͷ���ĵ������࣬ʵ�����Ա�ӿ�
{
    protected Node<E> head;                      //ͷָ�룬ָ�������ͷ���
    protected Node<E> rear;                  	 //βָ�룬ָ���������һ�����
    protected int n;                             //��������

    public HSLinkedList()                        //���������
    {
        this.head = new Node<E>();               //����ͷ��㣬ֵΪnull
        this.rear = this.head;
        this.n=0;
    }
    public boolean isEmpty()                     //�жϵ������Ƿ�Ϊ�գ�O(1)
    {
        return this.head.next==null;
    }
    public int length()                          //���ص������ȣ�O(1)
    {
        return this.n;
    }

    public E get(int index)                      //�������Ϊindex�Ķ���index��ֵΪ0
    {                                            //��������ջ���Ŵ��󷵻�null��O(n)
        if (index>=0)
        {
            int j=0; 
            Node<E> p=this.head.next;
            while (p!=null && j<index)
            {
                j++;
                p = p.next;
            }
            if (p!=null)
                return (E)p.data;
        }
        return null;
    }
   
    public E set(int index, E element)           //�������Ϊindex�Ķ���Ϊelement��O(n)
    {                                            //�������ɹ�����ԭ���󣬷��򷵻�null
        if (index>=0 && element!=null)
        {
            int j=0; 
            Node<E> p=this.head.next;
            while (p!=null && j<index)
            {
                j++;
                p = p.next;
            }
            if (p!=null)
            {
                E old = (E)p.data;
                p.data = element;
                return old;                      //�������ɹ�����ԭ����
            }
        }
        return null;                             //�������ɹ�
    }

    public boolean add(E element)                //�ڵ����������Ӷ���O(1)
    {
        if (element==null)
            return false;
                                         
        this.rear.next = new Node<E>(element);   //β����
        this.rear = this.rear.next;              //�ƶ�βָ��
        this.n++;
        return true;
    }

    public boolean add(int index, E element)     //��ָ��λ�ò���ǿյ�ָ������
    {                                            //�������ɹ�����true��O(n)
        if (element==null)
            return false;

        if (index>=this.n)
            return this.add(element);            //���������
        else
        {
            int j=0;
            Node<E> p = this.head;
            while (p.next!=null && j<index)      //��index<=0��������ͷ���֮��
            {
                j++;
                p = p.next;
            }
            p.next=new Node<E>(element, p.next); //������p���֮�󣬰���ͷ���롢�м����
            this.n++;
            return true;
        }
    }

    public E remove(int index)                   //��ȥindexλ�õĶ���O(n)
    {                                            //�������ɹ����ر���ȥ���󣬷��򷵻�null
        E old = null;
        if (index>=0)                            //ͷɾ�����м�/βɾ��
        {
            int j=0; 
            Node<E> p=this.head;
            while (p.next!=null && j<index)      //��λ����ɾ������ǰ�����
            {
                j++;
                p = p.next;
            }
            if (p.next!=null)
            {
                old = (E)p.next.data;
                if (p.next==this.rear)
                    this.rear=p;
                p.next = p.next.next;            //ɾ��p�ĺ�̽��
                this.n--;
            }
        }
        return old;
    }

    public void clear()                          //��յ�����O(1)
    {
        this.head.next = null;
        this.rear = this.head;
        this.n=0;
    }

    public String toString()                     //������ʾ����������Ԫ��ֵ��Ӧ���ַ���
    {                                            //����������㷨��O(n)
        String str="(";
        Node<E> p = this.head.next;
        while (p!=null) 
        {
           str += p.data.toString();
           p = p.next;
           if (p!=null) 
              str += ", ";
        }
        return str+")";
    }
    //����ʵ��LList�ӿ�

//����2.4 ����������
    public Iterator<E> iterator()                //����һ������������
    {
        return new Itr();
    }

    private class Itr implements Iterator<E>     //˽���ڲ��࣬ʵ�ֵ������ӿ�
    {
        private Node<E> cursor = head.next;

        public boolean hasNext()                 //���к��Ԫ�أ�����true
        {
            return cursor!=null && cursor.next!=null;
        }

        public E next()                          //���غ��Ԫ��
        {
            if (cursor != null && cursor.next!=null)
            {
                E element = cursor.next.data;
                cursor = cursor.next;
                return element;
            }
            return null;
        }

        public void remove()                     //��֧�ָò���
        {
            throw new UnsupportedOperationException();
        }
    }//�ڲ���Itr����

    public static void main(String args[])
    {
        HSLinkedList<String> list = new HSLinkedList<String>();
        for (int i=5; i>=0; i--)
            list.add(0,new String((char)('A'+i)+""));
        System.out.println(list.toString());
    }
}
/*
�������н�����£�    
(A, B, C, D, E, F)


*/
