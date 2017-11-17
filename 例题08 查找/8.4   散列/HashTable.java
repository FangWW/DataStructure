//��������ַ����ɢ�б��࣬�������롢ɾ�������ҡ������Ȳ�����

import dataStructure.linearList.Node;                 //���뵥��������
import dataStructure.linearList.SinglyLinkedList;     //���뵥������

public class HashTable<E>                             //��������ַ����ɢ�б���
{
    private SinglyLinkedList<E>[] table;              //ɢ�б�Ķ�������

    public HashTable(int capacity)                    //����ָ��������ɢ�б�
    {
        this.table = new SinglyLinkedList[Math.abs(capacity)];
        for (int i=0; i<table.length; i++)            //��ʼ��
            table[i] = new SinglyLinkedList<E>();     //����յ�����
    }

    public HashTable()                                //����Ĭ��������ɢ�б�
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
        int i = hash(key);                            //ɢ�е�ַ
        table[i].add(0, element);
    } 

    public String toString()                          //���ɢ�б�ĸ�ͬ��ʵ�������Ԫ��
    {
        String str="";
        for (int i=0; i<table.length; i++)
            str += "table["+i+"]= "+table[i].toString()+"\n";  //�������������Ԫ��ֵ
        return str;
    }

    public Node<E> search(E element)                  //��ɢ�б��в���ָ������
    {                                                 //�����ҳɹ����ؽ�㣬���򷵻�null
        int key = element.hashCode();
        int i = hash(key);
        return table[i].search(element);              //�����ڵ������еĲ��ҽ��
    }

    public boolean contain(E element)                 //�Բ��ҽ���ж�ɢ�б��Ƿ����ָ������
    {                                                 //����������true�����򷵻�false
        return this.search(element)!=null;
    }

    public boolean remove(E element)                  //ɾ��ָ������
    {                                                 //��ɾ���ɹ�����true�����򷵻�false
        int key = element.hashCode();
        int i = hash(key);
        return table[i].remove(element);              //��ͬ��ʵ�������ɾ�����󣬲�����ɾ�����
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
            hashtable.insert(element);                //ɢ�б��в����������Ͷ���
            System.out.print(key+" ");
        }
        System.out.println("\nɢ�б�"+hashtable.toString());
        System.out.println("���� "+element+", "+(hashtable.contain(element)?"":"��")+"�ɹ�");
        key = 50;
        System.out.println("���� "+key+", "+(hashtable.contain(new Integer(key))?"":"��")+"�ɹ�");
        System.out.println("ɾ�� "+element+", "+(hashtable.remove(element)?"":"��")+"�ɹ�");
        System.out.println("ɾ�� "+key+", "+(hashtable.remove(new Integer(key))?"":"��")+"�ɹ�");
        System.out.println("ɢ�б�"+hashtable.toString());
    }
}
/*
�������н�����£�
����ؼ��֣� 68 32 43 60 70 53 99 
ɢ�б�
table[0]= {70}
table[1]= {99, 43}
table[2]= {}
table[3]= {}
table[4]= {53, 60, 32}
table[5]= {68}
table[6]= {}
���� 99, �ɹ�
99? 43? ���� 50, ���ɹ�
ɾ�� 99, �ɹ�
ɾ�� 50, ���ɹ�
ɢ�б�
table[0]= {70}
table[1]= {43}
table[2]= {}
table[3]= {}
table[4]= {53, 60, 32}
table[5]= {68}
table[6]= {}


*/
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
 