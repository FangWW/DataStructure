//单链表结点类

package dataStructure.linearList;

public class Node<E>                             //单链表结点类
{
    public E data;                               //数据域，保存数据元素
    public Node<E> next;                         //地址域，引用后继结点

    public Node(E data, Node<E> next)            //构造结点，指定数据元素和后继结点
    {
        this.data = data;
        this.next = next;
    }

    public Node(E data)
    {
        this(data, null);
    }

    public Node()
    {
        this(null, null);
    }

    public String toString()                     //返回结点元素值对应的字符串
    {
        return this.data.toString();
    }
}
