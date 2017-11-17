//单链表逆转

import dataStructure.linearList.Node;                      //导入单链表结点类
import dataStructure.linearList.SinglyLinkedList;          //导入单链表类

public class ReverseLinkedList<E> extends SinglyLinkedList<E>
{
    public ReverseLinkedList() 
    {
        super();                                           //调用父类同参数的构造方法
    }
    
    public void reverse()                                  //将单链表逆转
    {
        Node<E> p=this.head, q=null, front=null;
        while (p!=null)
        {
            q = p.next;                                    //设置q是p结点的后继结点
            p.next = front;                                //使p.next指向p结点的前趋结点
            front = p;
            p = q;                                         //p向后走一步
        }
        this.head = front;
    }

    public static void main(String args[])
    {
        ReverseLinkedList<Integer> list = new ReverseLinkedList<Integer>();
        for (int i=1; i<6; i++)
            list.add(0, new Integer(i));
        System.out.println("单链表 "+list.toString());
        list.reverse();
        System.out.println("逆转后 "+list.toString());
    }
}

/*
程序运行结果如下：
单链表 (5, 4, 3, 2, 1)
逆转后 (1, 2, 3, 4, 5)
*/

/*
以下方法未通过,
public class ReverseLinkedList  
{
    public static void reverse(SinglyLinkedList<E> list)   //static方法不能带泛型参数？？
    {
        Node<E> p=list.head, q=null, front=null;          //head要声明为public 
        while(p!=null)
        {
            q = p.next;
            p.next = front;
            front = p;
            p = q; 
        }
        list.head = front;
    }
}


*/
