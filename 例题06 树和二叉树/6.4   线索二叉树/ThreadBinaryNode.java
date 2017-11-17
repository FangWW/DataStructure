
package dataStructure.tree;

public class ThreadBinaryNode<E>                 //�����������Ķ�����������
{
    public E data;                               //����Ԫ��
    public ThreadBinaryNode<E> left, right;      //�ֱ�ָ�����Һ��ӽ��
    public int ltag, rtag;                       //�����������

    public ThreadBinaryNode(E data, ThreadBinaryNode<E> left, ThreadBinaryNode<E> right)
    {                                            //�����㣬ָ��Ԫ�غ����Һ��ӽ��
        this.data = data;
        this.left = left;
        this.right = right;
        this.ltag = this.rtag = 0;
    }

    public ThreadBinaryNode(E data)              //������ֵ���
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

