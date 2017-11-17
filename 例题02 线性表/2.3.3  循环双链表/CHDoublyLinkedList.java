//带头结点的循环双链表类

package dataStructure.linearList;
import dataStructure.linearList.DLinkNode;       //导入双链表结点类
import dataStructure.linearList.LList;           //导入线性表接口

public class CHDoublyLinkedList<E> implements LList<E>//带头结点的循环双链表类
{
    protected DLinkNode<E> head;                 //头指针

    public CHDoublyLinkedList()                  //构造空链表
    {
        this.head = new DLinkNode<E>();          //创建头结点，值为null
        this.head.prev = head;
        this.head.next = head;
    }

    public boolean isEmpty()                     //判断双链表是否为空
    {
        return head.next==head;
    }

    //以下算法同循环单链表，与单链表的差别在于，循环条件不同    
    public int length()                          //返回双链表长度
    {
        int i=0; 
        DLinkNode<E> p=this.head.next;                //此句与单链表不同
        while (p!=head)                          //循环条件与单链表不同
        {
            i++;
            p = p.next;
        }
        return i;
    }

    public E get(int index)                          //返回序号为index的对象
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

    public E set(int index, E element)               //设置index序号对象为element
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

    //双链表的插入、删除算法与单链表不同
    public boolean add(int index, E element)     //插入element对象，插入后对象序号为index
    {                                            //若操作成功返回true，O(n)
        if (element==null)
            return false;                        //不能添加空对象（null）

        int j=0;
        DLinkNode<E> front = this.head;
        while (front.next!=head && j<index)      //寻找插入位置，若i<=0，插入在头结点之后
        {
            j++;
            front = front.next;
        }
        DLinkNode<E> q = new DLinkNode<E>(element, front, front.next); //插入在front结点之后
        front.next.prev = q;
        front.next = q;
        return true;
    }

    public boolean add(E element)                //在单链表最后添加对象，O(1)
    {
        if (element==null)
            return false;                        //不能添加空对象（null）

        DLinkNode<E> q = new DLinkNode<E>(element, head.prev, head); 
        head.prev.next = q;                      //插入在头结点之前，相当于尾插入
        head.prev = q;
        return true;
    }

    public E remove(int index)                   //移除指定位置的对象，O(n)
    {                                            //返回被移除的原对象，指定位置序号错误时返回null
        E old = null;
        int j=0; 
        DLinkNode<E> p=this.head.next;
        while (p!=head && j<index)               //定位到待删除结点
        {
            j++;
            p = p.next;
        }
        if (p!=head)
        {
            old = (E)p.data;                     //操作成功，返回原对象
            p.prev.next = p.next;                //删除p结点自己
            p.next.prev = p.prev;
        }
        return old;
    }

    public void clear()                          //清空线性表
    {
        this.head.prev = head;
        this.head.next = head;
    }

//以上实现LList接口
  
    public static void main(String args[])
    {
        int i=0;
        CHDoublyLinkedList<String> list = new CHDoublyLinkedList<String>();
        System.out.println("删除第"+i+"个结点"+list.remove(0));
        System.out.println(list.toString());

        for (i=5; i>=0; i--)
            list.add(0, new String((char)('A'+i)+""));
        for (i=0; i<6; i++)
            list.add(new String((char)('A'+i)+""));
//            list.add(i, new String((char)('A'+i)+""));
        System.out.println(list.toString());

        System.out.println("删除第"+i+"个结点"+list.remove(i));
        System.out.println(list.toString());
        
    }
    
}
/*
程序运行结果如下：    
删除第0个结点null
()
(A, B, C, D, E, F, A, B, C, D, E, F)
删除第6个结点A
(A, B, C, D, E, F, B, C, D, E, F)


*/
