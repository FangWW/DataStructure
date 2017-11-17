package dataStructure.tree;

public class BinaryNode<E>                       //二叉树的二叉链表结点类
{
    public E data;                               //数据元素
    public BinaryNode<E> left, right;            //分别指向左、右孩子结点

    public BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right)
    {                                            //构造结点，指定元素和左、右孩子结点
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(E data)                    //构造有值的叶子结点
    {
        this(data, null, null);
    }

    public BinaryNode()
    {
        this(null, null, null);
    }

    public boolean isLeaf()                      //判断是否叶子结点
    {
        return this.left==null && this.right==null;
    }

    public String toString()
    {
        return this.data.toString();
    }
}
