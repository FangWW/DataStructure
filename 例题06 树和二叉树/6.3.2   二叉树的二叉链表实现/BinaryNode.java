package dataStructure.tree;

public class BinaryNode<E>                       //�������Ķ�����������
{
    public E data;                               //����Ԫ��
    public BinaryNode<E> left, right;            //�ֱ�ָ�����Һ��ӽ��

    public BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right)
    {                                            //�����㣬ָ��Ԫ�غ����Һ��ӽ��
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(E data)                    //������ֵ��Ҷ�ӽ��
    {
        this(data, null, null);
    }

    public BinaryNode()
    {
        this(null, null, null);
    }

    public boolean isLeaf()                      //�ж��Ƿ�Ҷ�ӽ��
    {
        return this.left==null && this.right==null;
    }

    public String toString()
    {
        return this.data.toString();
    }
}
