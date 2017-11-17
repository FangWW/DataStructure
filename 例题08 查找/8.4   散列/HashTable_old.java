//采用链地址法的散列表类，包括插入、删除、查找、遍历等操作。
import dataStructure.linearList.Node;                 //导入单链表结点类
import dataStructure.linearList.SinglyLinkedList;     //导入单链表类

public class HashTable<E>                             //采用链地址法的散列表类
{
    private Object[] table;                           //散列表的对象数组

    public HashTable(int capacity)                    //构造指定容量的空表
    {
        this.table = new Object[Math.abs(capacity)];
    }
    public HashTable()                                //构造默认容量的空表
    {
        this(97);                                     //97是100以内的最大素数
    }

    public int hash(int key)                          //散列函数，确定关键字为key元素的散列地址
    {
        return key % table.length;                    //除留余数法，除数是散列表长度
    }
    
    public void insert(E element)                     //在散列表中插入指定元素
    {
        int key = element.hashCode();                 //每个对象的hashCode()方法返回整数值
        System.out.print(key+" ");
        int i = hash(key);                            //散列地址
        if (table[i]==null)                           //散列表中的指定位置空
            table[i]=new Node<E>(element);            //存放给定元素
        else
        {                                             //产生冲突，插入同义词冲突单链表
            Node<E> q = new Node<E>(element);
            Node<E> head = (Node<E>)table[i];         //散列表的一项相当于单链表的头结点
            q.next = head.next;                       //单链表头插入
            head.next=q;
        }
    } 

    public void print()                               //遍历散列表及冲突单链表并输出其中全部元素
    {
        for (int i=0; i<table.length; i++)
        {
            SinglyLinkedList list = new SinglyLinkedList((Node<E>)table[i]); //创建单链表
            System.out.println("table["+i+"]= "+list.toString());  //遍历单链表并输出元素值
        }
    }

    public Node<E> search(E element)                  //在散列表及冲突单链表中查找
    {                                                 //若查找成功返回结点，否则返回null
        int key = element.hashCode();
        int i = hash(key);
        if(table[i]==null)
            return null;
        else
        {
            Node<E> head = (Node<E>)table[i];
            SinglyLinkedList list = new SinglyLinkedList(head);
            return list.search(element);              //返回在单链表中的查找结果
        }
    }

    public boolean contain(E element)                 //以查找结果判断单链表是否包含指定对象
    {                                                 //若包含返回true，否则返回false
        return this.search(element)!=null;
    }

    public boolean remove(E element)                  //删除指定对象
    {                                                 //若删除成功返回true，否则返回false
        int key = element.hashCode();
        int i = hash(key);
        if (table[i]==null)                           //散列表中没有该对象
            return false;
        else
        {
            Node<E> head = (Node<E>)table[i];
            if (element.equals(head.data))            //欲删除散列表中当前对象
            {
                if (head.next==null)                  //若冲突单链表空，则删除散列表中对象
                    table[i]=null;
                else
                {                                     //否则，将冲突单链表的第1个结点移动到散列表中
                    Node<E> p = head.next;
                    head.data = p.data;
                    head.next = p.next;
                }
                return true;                          //删除成功
            }
            else
            {                                         //在冲突单链表中删除对象，并返回删除结果
                SinglyLinkedList list = new SinglyLinkedList(head);
                return list.remove(element);
            }
        }
    }

    public static void main(String args[]) 
    {
        int n=7;
        HashTable<Integer> hashtable = new HashTable<Integer>(n);
        System.out.print("插入关键字： ");
        int key;
        Integer element=null;
        for (int i=0; i<n; i++)
        {
            key=(int)(Math.random()*100);             //产生n个随机数作为关键字
            element = new Integer(key);
            hashtable.insert(element);                //散列表插入整数类型对象
        }
        System.out.println("\n散列表：");
        hashtable.print();

        System.out.println("查找 "+element+", "+(hashtable.contain(element)?"":"不")+"成功");
        key = 50;
        System.out.println("查找 "+key+", "+(hashtable.contain(new Integer(key))?"":"不")+"成功");

        System.out.println("删除 "+element+", "+(hashtable.remove(element)?"":"不")+"成功");


        System.out.println("删除 "+key+", "+(hashtable.remove(new Integer(key))?"":"不")+"成功");
        System.out.println("散列表：");
        hashtable.print();        
    }
}

/*
程序运行结果如下：
插入关键字： 28 11 55 17 31 75 80 
散列表：
table[0]= {28}
table[1]= {}
table[2]= {}
table[3]= {17, 80, 31}
table[4]= {11}
table[5]= {75}
table[6]= {55}
17? 80? 查找 80, 成功
查找 50, 不成功
删除 80, 成功
删除 50, 不成功
散列表：
table[0]= {28}
table[1]= {}
table[2]= {}
table[3]= {17, 31}
table[4]= {11}
table[5]= {75}
table[6]= {55}



public final class Integer
    public int hashCode()                //覆盖Object类中方法
    {
        return value;
    }
} 
 **/
 