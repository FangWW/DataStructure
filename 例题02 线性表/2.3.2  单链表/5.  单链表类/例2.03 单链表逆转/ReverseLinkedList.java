//��������ת

import dataStructure.linearList.Node;                      //���뵥��������
import dataStructure.linearList.SinglyLinkedList;          //���뵥������

public class ReverseLinkedList<E> extends SinglyLinkedList<E>
{
    public ReverseLinkedList() 
    {
        super();                                           //���ø���ͬ�����Ĺ��췽��
    }
    
    public void reverse()                                  //����������ת
    {
        Node<E> p=this.head, q=null, front=null;
        while (p!=null)
        {
            q = p.next;                                    //����q��p���ĺ�̽��
            p.next = front;                                //ʹp.nextָ��p����ǰ�����
            front = p;
            p = q;                                         //p�����һ��
        }
        this.head = front;
    }

    public static void main(String args[])
    {
        ReverseLinkedList<Integer> list = new ReverseLinkedList<Integer>();
        for (int i=1; i<6; i++)
            list.add(0, new Integer(i));
        System.out.println("������ "+list.toString());
        list.reverse();
        System.out.println("��ת�� "+list.toString());
    }
}

/*
�������н�����£�
������ (5, 4, 3, 2, 1)
��ת�� (1, 2, 3, 4, 5)
*/

/*
���·���δͨ��,
public class ReverseLinkedList  
{
    public static void reverse(SinglyLinkedList<E> list)   //static�������ܴ����Ͳ�������
    {
        Node<E> p=list.head, q=null, front=null;          //headҪ����Ϊpublic 
        while(p!=null)
        {
            q = p.next;
            p.next = front;
            front = p;
            p = q; 
        }
        list.head = front;
    }
}


*/
