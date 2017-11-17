//采用链地址法的散列表类，包括插入、删除、查找、遍历等操作。

import dataStructure.linearList.Node;                 //导入单链表结点类
import dataStructure.linearList.SinglyLinkedList;     //导入单链表类

public class HashTable<E>                             //采用链地址法的散列表类
{
    private SinglyLinkedList<E>[] table;              //散列表的对象数组

    public HashTable(int capacity)                    //构造指定容量的散列表
    {
        this.table = new SinglyLinkedList[Math.abs(capacity)];
        for (int i=0; i<table.length; i++)            //初始化
            table[i] = new SinglyLinkedList<E>();     //构造空单链表
    }

    public HashTable()                                //构造默认容量的散列表
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
        int i = hash(key);                            //散列地址
        table[i].add(0, element);
    } 

    public String toString()                          //输出散列表的各同义词单链表中元素
    {
        String str="";
        for (int i=0; i<table.length; i++)
            str += "table["+i+"]= "+table[i].toString()+"\n";  //遍历单链表并输出元素值
        return str;
    }

    public Node<E> search(E element)                  //在散列表中查找指定对象
    {                                                 //若查找成功返回结点，否则返回null
        int key = element.hashCode();
        int i = hash(key);
        return table[i].search(element);              //返回在单链表中的查找结果
    }

    public boolean contain(E element)                 //以查找结果判断散列表是否包含指定对象
    {                                                 //若包含返回true，否则返回false
        return this.search(element)!=null;
    }

    public boolean remove(E element)                  //删除指定对象
    {                                                 //若删除成功返回true，否则返回false
        int key = element.hashCode();
        int i = hash(key);
        return table[i].remove(element);              //在同义词单链表中删除对象，并返回删除结果
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
            hashtable.insert(element);                //散列表中插入整数类型对象
            System.out.print(key+" ");
        }
        System.out.println("\n散列表："+hashtable.toString());
        System.out.println("查找 "+element+", "+(hashtable.contain(element)?"":"不")+"成功");
        key = 50;
        System.out.println("查找 "+key+", "+(hashtable.contain(new Integer(key))?"":"不")+"成功");
        System.out.println("删除 "+element+", "+(hashtable.remove(element)?"":"不")+"成功");
        System.out.println("删除 "+key+", "+(hashtable.remove(new Integer(key))?"":"不")+"成功");
        System.out.println("散列表："+hashtable.toString());
    }
}
/*
程序运行结果如下：
插入关键字： 68 32 43 60 70 53 99 
散列表：
table[0]= {70}
table[1]= {99, 43}
table[2]= {}
table[3]= {}
table[4]= {53, 60, 32}
table[5]= {68}
table[6]= {}
查找 99, 成功
99? 43? 查找 50, 不成功
删除 99, 成功
删除 50, 不成功
散列表：
table[0]= {70}
table[1]= {43}
table[2]= {}
table[3]= {}
table[4]= {53, 60, 32}
table[5]= {68}
table[6]= {}


*/
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
 