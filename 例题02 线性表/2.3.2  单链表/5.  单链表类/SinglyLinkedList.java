//��������

package dataStructure.linearList;
import dataStructure.linearList.Node;            //���뵥��������
import java.util.Iterator;                       //����������ӿ�

public class SinglyLinkedList<E> extends AbstractList<E> implements LList<E>  //�������࣬ʵ�����Ա�ӿ�
{
    protected Node<E> head;                      //ͷָ�룬ָ�������1�����

    public SinglyLinkedList()                    //����յ�����
    {
        this.head = null;
    }

    public SinglyLinkedList(Node<E> head)        //����ָ��ͷָ��ĵ�����
    {
        this.head = head;
    }

    public boolean isEmpty()                     //�жϵ������Ƿ�Ϊ�գ�O(1)
    {
        return this.head==null;
    }

    public int length()                          //���ص�������
    {                                            //����������㷨��O(n)
        int i=0; 
        Node<E> p=this.head;
        while (p!=null)
        {
            i++;
            p = p.next;
        }
        return i;
    }

    public E get(int index)                      //�������Ϊindex�Ķ���index��ֵΪ0
    {                                            //��������ջ���Ŵ��󷵻�null��O(n)
        if (this.head!=null && index>=0)
        {
            int j=0; 
            Node<E> p=this.head;
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
        if (this.head!=null && index>=0 && element!=null)
        {
            int j=0; 
            Node<E> p=this.head;
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

    public boolean add(int index, E element)     //����element���󣬲����������Ϊindex
    {                                            //�������ɹ�����true��O(n)
        if (element==null)
            return false;                        //������ӿն���null��

        if (this.head==null || index<=0)         //ͷ����
            this.head = new Node<E>(element, this.head);
        else                                     //����������index>=1
        { 
            int j=0; 
            Node<E> p=this.head;
            while (p.next!=null && j<index-1)    //Ѱ�Ҳ���λ��
            {
                j++;
                p = p.next;
            }
            p.next = new Node<E>(element, p.next);//�м�/β����
        }
        return true;
    }

    public boolean add(E element)                //�ڵ����������Ӷ������أ�O(n)
    {
        return add(Integer.MAX_VALUE, element);
    }

    public E remove(int index)                   //��ȥ���Ϊindex�Ķ���O(n)
    {                                            //�������ɹ����ر���ȥ���󣬷��򷵻�null
        E old = null;
        if (this.head!=null && index>=0)
            if (index==0)                        //ͷɾ��
            {
                old = (E)this.head.data;
                this.head = this.head.next;
            }
            else                                 //�м�/βɾ��
            {
                int j=0; 
                Node<E> p=this.head;
                while (p.next!=null && j<index-1) //��λ����ɾ������ǰ�����
                {
                    j++;
                    p = p.next;
                }
                if (p.next!=null)
                {
                    old = (E)p.next.data;        //�����ɹ�������ԭ����
                    p.next = p.next.next;        //ɾ��p�ĺ�̽��
                }
            }
        return old;
    }

    public void clear()                          //��յ�����O(1)
    {
        this.head = null;
    }

    public String toString()                     //������ʾ����������Ԫ��ֵ��Ӧ���ַ���
    {                                            //����������㷨��O(n)
        String str="(";
        Node<E> p = this.head;
        while (p!=null) 
        {
           str += p.data.toString();
           p = p.next;
           if (p!=null) 
              str += ", ";
        }
        return str+")";
    }
//����ʵ��LList�ӿڣ���2��

//����2.4 ����������
    public Iterator<E> iterator()                //����һ������������
    {
        return new Itr();
    }

    private class Itr implements Iterator<E>     //˽���ڲ��࣬ʵ�ֵ������ӿ�
    {
        private Node<E> cursor = head;

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

//���µ�8�� 8.2.1 ˳����ң�ɢ�б�����
    public Node<E> search(E element, Node<E> start)   //�ӵ�������start��ʼ˳�����ָ������
    {                                                 //�����ҳɹ����ؽ�㣬���򷵻�null
        if (this.head==null || element==null)
            return null;

        Node<E> p=start;
        while (p!=null && !element.equals(p.data))
        {
            System.out.print(p.data+"? ");
            p = p.next;
        }
        return p;
    }
  
    public Node<E> search(E element)             //˳�����ָ������
    {
        return search(element, head); 
    }
/*
    public boolean contain(E element)            //�Բ��ҽ���жϵ������Ƿ����ָ������
    {                                            //����������true�����򷵻�false
        return this.search(element)!=null;
    }
*/
    public boolean remove(E element)             //��ȥ�״γ��ֵ�ָ������O(n)
    {                                            //�������ɹ�����true
        if (this.head==null || element==null)
            return false;
        
        if (element.equals(this.head.data))
        {
            this.head = this.head.next;          //ͷɾ��
            return true;
        }
        
        Node<E> front=this.head, p=front.next;   //�м�/βɾ��
        while (p!=null && !element.equals(p.data))
        {
            front = p;
            p=p.next;
        }
        if (p!=null)
        {
            front.next = p.next;
            return true;
        }
        return false;
    }
    
//�����ǵ�2��ϰ��
    public SinglyLinkedList(E[] element)         //��ָ�������еĶ�������쵥����
    {
        this.head = null;
        if (element!=null && element.length>0)
        {
            this.head = new Node(element[0]);
            Node<E> rear=this.head;
            int i=1;
            while (i<element.length)
            {
                rear.next = new Node(element[i++]);
                rear = rear.next; 
            }
        }
    }        

    public void concat(SinglyLinkedList list)    //��ָ��������list�����ڵ�ǰ������֮��
    {
        if (this.head==null)
            this.head = list.head;
        else
        {
            Node<E> p=this.head;
            while (p.next!=null)
                p = p.next;
            p.next = list.head;
        }
    }
    
    public SinglyLinkedList(SinglyLinkedList<E> list) //�Ե�����list�����µĵ�����
    {                                                 //���Ƶ�����
        this.head = null;
        if (list!=null && list.head!=null)
        {
            this.head = new Node(list.head.data);
            Node<E> p = list.head.next;
            Node<E> rear = this.head;
            while (p!=null)
            {
                rear.next = new Node<E>(p.data);
                rear = rear.next; 
                p = p.next;
            }
        }
    }

    //�ݹ鷽��
//    public SinglyLinkedList(SinglyLinkedList<E> list) //�Ե�����list�����µĵ�����
    public void copy(SinglyLinkedList<E> list)   //���Ƶ����� 
    {
        this.head = copy(list.head);
    }
    private Node<E> copy(Node<E> p)                 //���Ƶ������ݹ鷽��
    {
        Node<E> q=null;
        if (p!=null)
        {
            q = new Node(p.data);
            q.next = copy(p.next); 
        }
        return q;
    }


/*//�ݹ鷽��

    public String toString()
    {
        return "("+ this.toString(this.head) +")";
    }
    public String toString(Node<E> p)            //�ݹ鷽��
    {
         if (p!=null)
             return p.data.toString() + ", " + this.toString(p.next);   //�ݹ����
         return "";
    }        

    public SinglyLinkedList(E[] element)         //��ָ�������еĶ�������쵥����
    {
        this.head = null;
        if (element!=null)
            this.head = create(element,0);
    }

    private Node<E> create(E[] element, int i)      //��ָ�����鹹�쵥����
    {                                            //�ݹ鷽��
        Node<E> p=null;
        if (i<element.length)
        {
            p = new Node(element[i]);
            p.next = create(element, i+1); 
        }
        return p;
    }
*/    	
    public boolean equals(Object obj)            //�Ƚ������������Ƿ���� 
    {
        if (obj == this)
            return true;
        if (obj instanceof SinglyLinkedList)
        {
            SinglyLinkedList list = (SinglyLinkedList)obj;
            return equals(this.head, list.head);
        }
        return false;
    }
    private boolean equals(Node<E> p, Node<E> q) //�Ƚ������������Ƿ���ȣ��ݹ鷽��
    {
        if (p==null && q==null)
            return true;
        if (p!=null && q!=null)
            return p.data.equals(q.data) && equals(p.next, q.next);
        return false;
    }
    
//�����ǵ�8��ϰ��
    public boolean replace(Object obj, E element)//��Ԫ��ֵΪobj�Ľ��ֵ�滻Ϊelement��O(n)
    {                                            //���滻�ɹ�����true�����򷵻�false
        if (obj==null || element==null)
            return false;

        Node<E> p=this.head;
        while (p!=null)
        {
            if (obj.equals(p.data))
            {
                p.data = element;
                return true;
            }
            p = p.next;
        }
        return false;
    }
 
    public boolean replaceAll(Object obj, E element) //������Ԫ��ֵΪobj�Ľ��ֵ�滻Ϊelement��O(n)
    {                                            //���滻�ɹ�����true�����򷵻�false
        boolean done=false;
        if (obj!=null && element!=null)
        {
            Node<E> p=this.head;
            while (p!=null)
            {
                if (obj.equals(p.data))
                {
                    p.data = element;
                    done = true;
                }
                p = p.next;
            }
        }
        return done;
    }
    
    public boolean removeAll(Object obj)         //������Ԫ��ֵΪobj�Ľ��ɾ��
    {
        if (this.head==null || obj==null)
            return false;
        
        boolean done=false;
        while (this.head!=null && obj.equals(this.head.data))
        {
            this.head = this.head.next;          //ͷɾ��
            done = true;
        }
        Node<E> front=this.head, p=front.next;
        while(p!=null)
        {
            if (obj.equals(p.data))
            {
                front.next = p.next;             //ɾ��p���
                p = front.next;
                done = true;
            }
            else
            {
                front = p;
                p = p.next;
            }
        }
        return done;
    }

    public static void main(String[] args)
    {
        String[] letters={"A","B","C","D","E","F"};
        SinglyLinkedList<String> list1 = new SinglyLinkedList<String>(letters);
        SinglyLinkedList<String> list2 = new SinglyLinkedList<String>(list1);
        list2.copy(list1);
        System.out.println(list2.toString());
        System.out.println("equals()�� "+list2.equals(list1));
    }
    
}
/*
�������н�����£�    
(A, B, C, D, E, F)


*/

/* ��2��    //���У���Ч�ʵͣ�ʱ�临�Ӷ���O(n*n)��
    public String toString()
    {
        String str="{";
        if (this.length()!=0)
        {
            for(int i=0; i<this.length()-1; i++)
                str += this.get(i).toString()+", ";
            str += this.get(this.length()-1).toString();
        }
        return str+"}";
    }
*/