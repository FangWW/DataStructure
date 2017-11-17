//��ͷ����ѭ��˫������

package dataStructure.linearList;
import dataStructure.linearList.DLinkNode;       //����˫��������
import dataStructure.linearList.LList;           //�������Ա�ӿ�

public class CHDoublyLinkedList<E> implements LList<E>//��ͷ����ѭ��˫������
{
    protected DLinkNode<E> head;                 //ͷָ��

    public CHDoublyLinkedList()                  //���������
    {
        this.head = new DLinkNode<E>();          //����ͷ��㣬ֵΪnull
        this.head.prev = head;
        this.head.next = head;
    }

    public boolean isEmpty()                     //�ж�˫�����Ƿ�Ϊ��
    {
        return head.next==head;
    }

    //�����㷨ͬѭ���������뵥����Ĳ�����ڣ�ѭ��������ͬ    
    public int length()                          //����˫������
    {
        int i=0; 
        DLinkNode<E> p=this.head.next;                //�˾��뵥����ͬ
        while (p!=head)                          //ѭ�������뵥����ͬ
        {
            i++;
            p = p.next;
        }
        return i;
    }

    public E get(int index)                          //�������Ϊindex�Ķ���
    {
        if (index>=0)
        {
            int j=0; 
            DLinkNode<E> p=this.head.next;
            while (p!=head && j<index)
            {
                j++;
                p=p.next;
            }
            if (p!=head)
                return (E)p.data;
        }
        return null;
    }

    public E set(int index, E element)               //����index��Ŷ���Ϊelement
    {
        if (index>=0 && element!=null)
        {
            int j=0; 
            DLinkNode<E> p=this.head.next;
            while (p!=head && j<index)
            {
                j++;
                p=p.next;
            }
            if (p!=head)
            {
                E old = (E)p.data;
                p.data = element;
                return old;
            }
        }
        return null;
    }
 
    public String toString()
    {
        String str="(";
        DLinkNode<E> p = this.head.next;
        while (p!=head) 
        {
           str += p.data.toString();
           p = p.next;
           if (p!=head) 
              str += ", ";
        }
        return str+")";
    }

    //˫����Ĳ��롢ɾ���㷨�뵥����ͬ
    public boolean add(int index, E element)     //����element���󣬲����������Ϊindex
    {                                            //�������ɹ�����true��O(n)
        if (element==null)
            return false;                        //������ӿն���null��

        int j=0;
        DLinkNode<E> front = this.head;
        while (front.next!=head && j<index)      //Ѱ�Ҳ���λ�ã���i<=0��������ͷ���֮��
        {
            j++;
            front = front.next;
        }
        DLinkNode<E> q = new DLinkNode<E>(element, front, front.next); //������front���֮��
        front.next.prev = q;
        front.next = q;
        return true;
    }

    public boolean add(E element)                //�ڵ����������Ӷ���O(1)
    {
        if (element==null)
            return false;                        //������ӿն���null��

        DLinkNode<E> q = new DLinkNode<E>(element, head.prev, head); 
        head.prev.next = q;                      //������ͷ���֮ǰ���൱��β����
        head.prev = q;
        return true;
    }

    public E remove(int index)                   //�Ƴ�ָ��λ�õĶ���O(n)
    {                                            //���ر��Ƴ���ԭ����ָ��λ����Ŵ���ʱ����null
        E old = null;
        int j=0; 
        DLinkNode<E> p=this.head.next;
        while (p!=head && j<index)               //��λ����ɾ�����
        {
            j++;
            p = p.next;
        }
        if (p!=head)
        {
            old = (E)p.data;                     //�����ɹ�������ԭ����
            p.prev.next = p.next;                //ɾ��p����Լ�
            p.next.prev = p.prev;
        }
        return old;
    }

    public void clear()                          //������Ա�
    {
        this.head.prev = head;
        this.head.next = head;
    }

//����ʵ��LList�ӿ�
  
    public static void main(String args[])
    {
        int i=0;
        CHDoublyLinkedList<String> list = new CHDoublyLinkedList<String>();
        System.out.println("ɾ����"+i+"�����"+list.remove(0));
        System.out.println(list.toString());

        for (i=5; i>=0; i--)
            list.add(0, new String((char)('A'+i)+""));
        for (i=0; i<6; i++)
            list.add(new String((char)('A'+i)+""));
//            list.add(i, new String((char)('A'+i)+""));
        System.out.println(list.toString());

        System.out.println("ɾ����"+i+"�����"+list.remove(i));
        System.out.println(list.toString());
        
    }
    
}
/*
�������н�����£�    
ɾ����0�����null
()
(A, B, C, D, E, F, A, B, C, D, E, F)
ɾ����6�����A
(A, B, C, D, E, F, B, C, D, E, F)


*/
