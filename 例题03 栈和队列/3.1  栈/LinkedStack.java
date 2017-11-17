package dataStructure.linearList;
import dataStructure.linearList.SStack;
import dataStructure.linearList.Node;                 //导入单链表结点类

public class LinkedStack<E> implements SStack<E>      //链式栈类
{
    private Node<E> top;
    
    public LinkedStack()                              //构造空栈
    {
        this.top=null;                                //栈顶结点
    }
    public boolean isEmpty()                          //判断是否空栈
    {
        return this.top==null;
    }

    public boolean push(E element)                    //元素element入栈，若操作成功返回true
    {
        if (element==null)
            return false;                             //空对象（null）不能入栈

        this.top = new Node(element, this.top);       //在原栈顶之前插入结点作为新的栈顶结点
        return true;
    } 

    public E pop()                                    //出栈，返回当前栈顶元素，若栈空返回null
    {
        if (!isEmpty())
        {
            E temp = this.top.data;                   //取得栈顶结点值
            this.top = this.top.next;                 //删除栈顶结点
            return temp;
        }
        return null;
    }

    public E get()                                    //取栈顶元素值，未出栈，若栈空返回null
    {
        if (!isEmpty())
            return this.top.data;
        return null;
    }

    public String toString()                          //返回栈中各元素的字符串描述
    {
         String str="{";
         Node<E> p = this.top;
         while (p!=null)
         {
             str += p.data.toString();
             p = p.next;
             if (p!=null)
                 str += ", ";
         }
         return str+"} ";
    }

    public static void main(String args[])
    {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        System.out.print("Push: ");
        for (int i=1; i<=5; i++)
        {
            Integer intobj = new Integer(i);
            stack.push(intobj);
            System.out.print(intobj+"  ");
        }    
        System.out.println("\n"+stack.toString());

        System.out.print("Pop : ");
        while (!stack.isEmpty())
            System.out.print(stack.pop().toString()+"  ");
        System.out.println();
    }
}

/*
Push: 1  2  3  4  5  
dataStructure.linearList.LinkedStack: {5, 4, 3, 2, 1}
Pop : 5  4  3  2  1  


*/