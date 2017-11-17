//带头结点的单链表类
//建议，不声明成员变量rear和n，不安全，维护困难，子类需要同时修改3个成员变量，易出错。

package dataStructure.linearList;
import dataStructure.linearList.Node;            //导入单链表结点类
import java.util.Iterator;                       //导入迭代器接口

public class HSLinkedList<E> extends AbstractList<E> implements LList<E> //带头结点的单链表类，实现线性表接口
{
    protected Node<E> head;                      //头指针，指向单链表的头结点
    protected Node<E> rear;                  	 //尾指针，指向单链表最后一个结点
    protected int n;                             //单链表长度

    public HSLinkedList()                        //构造空链表
    {
        this.head = new Node<E>();               //创建头结点，值为null
        this.rear = this.head;
        this.n=0;
    }
    public boolean isEmpty()                     //判断单链表是否为空，O(1)
    {
        return this.head.next==null;
    }
    public int length()                          //返回单链表长度，O(1)
    {
        return this.n;
    }

    public E get(int index)                      //返回序号为index的对象，index初值为0
    {                                            //若单链表空或序号错误返回null，O(n)
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
   
    public E set(int index, E element)           //设置序号为index的对象为element，O(n)
    {                                            //若操作成功返回原对象，否则返回null
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
                return old;                      //若操作成功返回原对象
            }
        }
        return null;                             //操作不成功
    }

    public boolean add(E element)                //在单链表最后添加对象，O(1)
    {
        if (element==null)
            return false;
                                         
        this.rear.next = new Node<E>(element);   //尾插入
        this.rear = this.rear.next;              //移动尾指针
        this.n++;
        return true;
    }

    public boolean add(int index, E element)     //在指定位置插入非空的指定对象
    {                                            //若操作成功返回true，O(n)
        if (element==null)
            return false;

        if (index>=this.n)
            return this.add(element);            //插入在最后
        else
        {
            int j=0;
            Node<E> p = this.head;
            while (p.next!=null && j<index)      //若index<=0，插入在头结点之后
            {
                j++;
                p = p.next;
            }
            p.next=new Node<E>(element, p.next); //插入在p结点之后，包括头插入、中间插入
            this.n++;
            return true;
        }
    }

    public E remove(int index)                   //移去index位置的对象，O(n)
    {                                            //若操作成功返回被移去对象，否则返回null
        E old = null;
        if (index>=0)                            //头删除、中间/尾删除
        {
            int j=0; 
            Node<E> p=this.head;
            while (p.next!=null && j<index)      //定位到待删除结点的前驱结点
            {
                j++;
                p = p.next;
            }
            if (p.next!=null)
            {
                old = (E)p.next.data;
                if (p.next==this.rear)
                    this.rear=p;
                p.next = p.next.next;            //删除p的后继结点
                this.n--;
            }
        }
        return old;
    }

    public void clear()                          //清空单链表，O(1)
    {
        this.head.next = null;
        this.rear = this.head;
        this.n=0;
    }

    public String toString()                     //返回显示单链表所有元素值对应的字符串
    {                                            //单链表遍历算法，O(n)
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
    //以上实现LList接口

//以下2.4 迭代器内容
    public Iterator<E> iterator()                //返回一个迭代器对象
    {
        return new Itr();
    }

    private class Itr implements Iterator<E>     //私有内部类，实现迭代器接口
    {
        private Node<E> cursor = head.next;

        public boolean hasNext()                 //若有后继元素，返回true
        {
            return cursor!=null && cursor.next!=null;
        }

        public E next()                          //返回后继元素
        {
            if (cursor != null && cursor.next!=null)
            {
                E element = cursor.next.data;
                cursor = cursor.next;
                return element;
            }
            return null;
        }

        public void remove()                     //不支持该操作
        {
            throw new UnsupportedOperationException();
        }
    }//内部类Itr结束

    public static void main(String args[])
    {
        HSLinkedList<String> list = new HSLinkedList<String>();
        for (int i=5; i>=0; i--)
            list.add(0,new String((char)('A'+i)+""));
        System.out.println(list.toString());
    }
}
/*
程序运行结果如下：    
(A, B, C, D, E, F)


*/
