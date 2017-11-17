//单链表类

package dataStructure.linearList;
import dataStructure.linearList.Node;            //导入单链表结点类
import java.util.Iterator;                       //导入迭代器接口

public class SinglyLinkedList<E> extends AbstractList<E> implements LList<E>  //单链表类，实现线性表接口
{
    protected Node<E> head;                      //头指针，指向单链表第1个结点

    public SinglyLinkedList()                    //构造空单链表
    {
        this.head = null;
    }

    public SinglyLinkedList(Node<E> head)        //构造指定头指针的单链表
    {
        this.head = head;
    }

    public boolean isEmpty()                     //判断单链表是否为空，O(1)
    {
        return this.head==null;
    }

    public int length()                          //返回单链表长度
    {                                            //单链表遍历算法，O(n)
        int i=0; 
        Node<E> p=this.head;
        while (p!=null)
        {
            i++;
            p = p.next;
        }
        return i;
    }

    public E get(int index)                      //返回序号为index的对象，index初值为0
    {                                            //若单链表空或序号错误返回null，O(n)
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

    public E set(int index, E element)           //设置序号为index的对象为element，O(n)
    {                                            //若操作成功返回原对象，否则返回null
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
                return old;                      //若操作成功返回原对象
            }
        }
        return null;                             //操作不成功
    }

    public boolean add(int index, E element)     //插入element对象，插入后对象序号为index
    {                                            //若操作成功返回true，O(n)
        if (element==null)
            return false;                        //不能添加空对象（null）

        if (this.head==null || index<=0)         //头插入
            this.head = new Node<E>(element, this.head);
        else                                     //单链表不空且index>=1
        { 
            int j=0; 
            Node<E> p=this.head;
            while (p.next!=null && j<index-1)    //寻找插入位置
            {
                j++;
                p = p.next;
            }
            p.next = new Node<E>(element, p.next);//中间/尾插入
        }
        return true;
    }

    public boolean add(E element)                //在单链表最后添加对象，重载，O(n)
    {
        return add(Integer.MAX_VALUE, element);
    }

    public E remove(int index)                   //移去序号为index的对象，O(n)
    {                                            //若操作成功返回被移去对象，否则返回null
        E old = null;
        if (this.head!=null && index>=0)
            if (index==0)                        //头删除
            {
                old = (E)this.head.data;
                this.head = this.head.next;
            }
            else                                 //中间/尾删除
            {
                int j=0; 
                Node<E> p=this.head;
                while (p.next!=null && j<index-1) //定位到待删除结点的前驱结点
                {
                    j++;
                    p = p.next;
                }
                if (p.next!=null)
                {
                    old = (E)p.next.data;        //操作成功，返回原对象
                    p.next = p.next.next;        //删除p的后继结点
                }
            }
        return old;
    }

    public void clear()                          //清空单链表，O(1)
    {
        this.head = null;
    }

    public String toString()                     //返回显示单链表所有元素值对应的字符串
    {                                            //单链表遍历算法，O(n)
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
//以上实现LList接口，第2章

//以下2.4 迭代器内容
    public Iterator<E> iterator()                //返回一个迭代器对象
    {
        return new Itr();
    }

    private class Itr implements Iterator<E>     //私有内部类，实现迭代器接口
    {
        private Node<E> cursor = head;

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

//以下第8章 8.2.1 顺序查找，散列表中用
    public Node<E> search(E element, Node<E> start)   //从单链表结点start开始顺序查找指定对象
    {                                                 //若查找成功返回结点，否则返回null
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
  
    public Node<E> search(E element)             //顺序查找指定对象
    {
        return search(element, head); 
    }
/*
    public boolean contain(E element)            //以查找结果判断单链表是否包含指定对象
    {                                            //若包含返回true，否则返回false
        return this.search(element)!=null;
    }
*/
    public boolean remove(E element)             //移去首次出现的指定对象，O(n)
    {                                            //若操作成功返回true
        if (this.head==null || element==null)
            return false;
        
        if (element.equals(this.head.data))
        {
            this.head = this.head.next;          //头删除
            return true;
        }
        
        Node<E> front=this.head, p=front.next;   //中间/尾删除
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
    
//以下是第2章习题
    public SinglyLinkedList(E[] element)         //由指定数组中的多个对象构造单链表
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

    public void concat(SinglyLinkedList list)    //将指定单链表list链接在当前单链表之后
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
    
    public SinglyLinkedList(SinglyLinkedList<E> list) //以单链表list构造新的单链表
    {                                                 //复制单链表
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

    //递归方法
//    public SinglyLinkedList(SinglyLinkedList<E> list) //以单链表list构造新的单链表
    public void copy(SinglyLinkedList<E> list)   //复制单链表 
    {
        this.head = copy(list.head);
    }
    private Node<E> copy(Node<E> p)                 //复制单链表，递归方法
    {
        Node<E> q=null;
        if (p!=null)
        {
            q = new Node(p.data);
            q.next = copy(p.next); 
        }
        return q;
    }


/*//递归方法

    public String toString()
    {
        return "("+ this.toString(this.head) +")";
    }
    public String toString(Node<E> p)            //递归方法
    {
         if (p!=null)
             return p.data.toString() + ", " + this.toString(p.next);   //递归调用
         return "";
    }        

    public SinglyLinkedList(E[] element)         //由指定数组中的多个对象构造单链表
    {
        this.head = null;
        if (element!=null)
            this.head = create(element,0);
    }

    private Node<E> create(E[] element, int i)      //由指定数组构造单链表
    {                                            //递归方法
        Node<E> p=null;
        if (i<element.length)
        {
            p = new Node(element[i]);
            p.next = create(element, i+1); 
        }
        return p;
    }
*/    	
    public boolean equals(Object obj)            //比较两条单链表是否相等 
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
    private boolean equals(Node<E> p, Node<E> q) //比较两条单链表是否相等，递归方法
    {
        if (p==null && q==null)
            return true;
        if (p!=null && q!=null)
            return p.data.equals(q.data) && equals(p.next, q.next);
        return false;
    }
    
//以下是第8章习题
    public boolean replace(Object obj, E element)//将元素值为obj的结点值替换为element，O(n)
    {                                            //若替换成功返回true，否则返回false
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
 
    public boolean replaceAll(Object obj, E element) //将所有元素值为obj的结点值替换为element，O(n)
    {                                            //若替换成功返回true，否则返回false
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
    
    public boolean removeAll(Object obj)         //将所有元素值为obj的结点删除
    {
        if (this.head==null || obj==null)
            return false;
        
        boolean done=false;
        while (this.head!=null && obj.equals(this.head.data))
        {
            this.head = this.head.next;          //头删除
            done = true;
        }
        Node<E> front=this.head, p=front.next;
        while(p!=null)
        {
            if (obj.equals(p.data))
            {
                front.next = p.next;             //删除p结点
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
        System.out.println("equals()， "+list2.equals(list1));
    }
    
}
/*
程序运行结果如下：    
(A, B, C, D, E, F)


*/

/* 第2章    //可行，但效率低，时间复杂度是O(n*n)。
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