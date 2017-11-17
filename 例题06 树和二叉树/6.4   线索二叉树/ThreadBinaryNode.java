
package dataStructure.tree;

public class ThreadBinaryNode<E>                 //线索二叉树的二叉链表结点类
{
    public E data;                               //数据元素
    public ThreadBinaryNode<E> left, right;      //分别指向左、右孩子结点
    public int ltag, rtag;                       //左、右线索标记

    public ThreadBinaryNode(E data, ThreadBinaryNode<E> left, ThreadBinaryNode<E> right)
    {                                            //构造结点，指定元素和左、右孩子结点
        this.data = data;
        this.left = left;
        this.right = right;
        this.ltag = this.rtag = 0;
    }

    public ThreadBinaryNode(E data)              //构造有值结点
    {
        this(data, null, null);
    }

    public ThreadBinaryNode()
    {
        this(null, null, null);
    }

    public String toString()
    {
        return "("+this.data+","+this.ltag+","+this.rtag+")";
    }
}

