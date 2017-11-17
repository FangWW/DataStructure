package dataStructure.linearList;
import dataStructure.linearList.QQueue;
import dataStructure.linearList.Node;            //���뵥��������

public class LinkedQueue<E> implements QQueue<E> //��ʽ������
{
    private Node<E> front, rear;                 //front��rear�ֱ�ָ���ͷ�Ͷ�β���

    public LinkedQueue()                         //����ն���
    {
        this.front=this.rear=null;
    }
    public boolean isEmpty()                     //�ж϶����Ƿ�գ����շ���true
    {
        return this.front==null && this.rear==null;
    }

    public boolean enqueue(E element)            //Ԫ��element��ӣ��������ɹ�����true
    {
        if (element==null)
            return false;                        //�ն���null���������

        Node<E> q = new Node(element);
        if (!isEmpty())                          //���в���ʱ
            this.rear.next=q;                    //q�����Ϊ�µĶ�β���
        else 
            this.front=q;      
        this.rear=q;
        return true;
    } 

    public E dequeue()                           //���ӣ����ص�ǰ��ͷԪ�أ������пշ���null 
    {
        if (!isEmpty())
        {
            E temp = this.front.data;            //ȡ�ö�ͷԪ��
            this.front = this.front.next;        //ɾ����ͷ���
            if (this.front==null)
                this.rear=null;
            return temp;
        }
        return null;
    } 

    public String toString()                     //����ջ�и�Ԫ�ص��ַ�������
    {
         String str=" {";
         Node<E> p = this.front;
         while (p!=null)
         {
             str += p.data.toString();
             p = p.next;
             if (p!=null)
                 str += ", ";
         }
         return str+"} ";
    }

    public static void main(String args[])
    {
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();
        System.out.print("enqueue: ");
        for (int i=1; i<=5; i++)
        {
            Integer intobj = new Integer(i);
            q.enqueue(intobj);
            System.out.print(intobj+"  ");
        }    
        System.out.println("\n"+q.toString());

        System.out.print("dequeue : ");
        while (!q.isEmpty())
            System.out.print(q.dequeue().toString()+"  ");
        System.out.println();
    }
}

/*
enqueue: 1  2  3  4  5  
 {1, 2, 3, 4, 5} 
dequeue : 1  2  3  4  5  
*/