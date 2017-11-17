//��������ַ����ɢ�б��࣬�������롢ɾ�������ҡ������Ȳ�����
import dataStructure.linearList.Node;                 //���뵥��������
import dataStructure.linearList.SinglyLinkedList;     //���뵥������

public class HashTable<E>                             //��������ַ����ɢ�б���
{
    private Object[] table;                           //ɢ�б�Ķ�������

    public HashTable(int capacity)                    //����ָ�������Ŀձ�
    {
        this.table = new Object[Math.abs(capacity)];
    }
    public HashTable()                                //����Ĭ�������Ŀձ�
    {
        this(97);                                     //97��100���ڵ��������
    }

    public int hash(int key)                          //ɢ�к�����ȷ���ؼ���ΪkeyԪ�ص�ɢ�е�ַ
    {
        return key % table.length;                    //������������������ɢ�б���
    }
    
    public void insert(E element)                     //��ɢ�б��в���ָ��Ԫ��
    {
        int key = element.hashCode();                 //ÿ�������hashCode()������������ֵ
        System.out.print(key+" ");
        int i = hash(key);                            //ɢ�е�ַ
        if (table[i]==null)                           //ɢ�б��е�ָ��λ�ÿ�
            table[i]=new Node<E>(element);            //��Ÿ���Ԫ��
        else
        {                                             //������ͻ������ͬ��ʳ�ͻ������
            Node<E> q = new Node<E>(element);
            Node<E> head = (Node<E>)table[i];         //ɢ�б��һ���൱�ڵ������ͷ���
            q.next = head.next;                       //������ͷ����
            head.next=q;
        }
    } 

    public void print()                               //����ɢ�б���ͻ�������������ȫ��Ԫ��
    {
        for (int i=0; i<table.length; i++)
        {
            SinglyLinkedList list = new SinglyLinkedList((Node<E>)table[i]); //����������
            System.out.println("table["+i+"]= "+list.toString());  //�������������Ԫ��ֵ
        }
    }

    public Node<E> search(E element)                  //��ɢ�б���ͻ�������в���
    {                                                 //�����ҳɹ����ؽ�㣬���򷵻�null
        int key = element.hashCode();
        int i = hash(key);
        if(table[i]==null)
            return null;
        else
        {
            Node<E> head = (Node<E>)table[i];
            SinglyLinkedList list = new SinglyLinkedList(head);
            return list.search(element);              //�����ڵ������еĲ��ҽ��
        }
    }

    public boolean contain(E element)                 //�Բ��ҽ���жϵ������Ƿ����ָ������
    {                                                 //����������true�����򷵻�false
        return this.search(element)!=null;
    }

    public boolean remove(E element)                  //ɾ��ָ������
    {                                                 //��ɾ���ɹ�����true�����򷵻�false
        int key = element.hashCode();
        int i = hash(key);
        if (table[i]==null)                           //ɢ�б���û�иö���
            return false;
        else
        {
            Node<E> head = (Node<E>)table[i];
            if (element.equals(head.data))            //��ɾ��ɢ�б��е�ǰ����
            {
                if (head.next==null)                  //����ͻ������գ���ɾ��ɢ�б��ж���
                    table[i]=null;
                else
                {                                     //���򣬽���ͻ������ĵ�1������ƶ���ɢ�б���
                    Node<E> p = head.next;
                    head.data = p.data;
                    head.next = p.next;
                }
                return true;                          //ɾ���ɹ�
            }
            else
            {                                         //�ڳ�ͻ��������ɾ�����󣬲�����ɾ�����
                SinglyLinkedList list = new SinglyLinkedList(head);
                return list.remove(element);
            }
        }
    }

    public static void main(String args[]) 
    {
        int n=7;
        HashTable<Integer> hashtable = new HashTable<Integer>(n);
        System.out.print("����ؼ��֣� ");
        int key;
        Integer element=null;
        for (int i=0; i<n; i++)
        {
            key=(int)(Math.random()*100);             //����n���������Ϊ�ؼ���
            element = new Integer(key);
            hashtable.insert(element);                //ɢ�б�����������Ͷ���
        }
        System.out.println("\nɢ�б�");
        hashtable.print();

        System.out.println("���� "+element+", "+(hashtable.contain(element)?"":"��")+"�ɹ�");
        key = 50;
        System.out.println("���� "+key+", "+(hashtable.contain(new Integer(key))?"":"��")+"�ɹ�");

        System.out.println("ɾ�� "+element+", "+(hashtable.remove(element)?"":"��")+"�ɹ�");


        System.out.println("ɾ�� "+key+", "+(hashtable.remove(new Integer(key))?"":"��")+"�ɹ�");
        System.out.println("ɢ�б�");
        hashtable.print();        
    }
}

/*
�������н�����£�
����ؼ��֣� 28 11 55 17 31 75 80 
ɢ�б�
table[0]= {28}
table[1]= {}
table[2]= {}
table[3]= {17, 80, 31}
table[4]= {11}
table[5]= {75}
table[6]= {55}
17? 80? ���� 80, �ɹ�
���� 50, ���ɹ�
ɾ�� 80, �ɹ�
ɾ�� 50, ���ɹ�
ɢ�б�
table[0]= {28}
table[1]= {}
table[2]= {}
table[3]= {17, 31}
table[4]= {11}
table[5]= {75}
table[6]= {55}



public final class Integer
    public int hashCode()                //����Object���з���
    {
        return value;
    }
} 
 **/
 