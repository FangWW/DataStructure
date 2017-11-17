//˫��������

package dataStructure.linearList;

public class DLinkNode<E>                        //˫��������
{
    public E data;                               //����Ԫ��
    public DLinkNode<E> prev, next;              //ǰ������̽��

    public DLinkNode(E data, DLinkNode<E> prev, DLinkNode<E> next)
    {                                            //�����㣬ָ������ǰ���ͺ�̽��
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public DLinkNode(E data)
    {
        this(data, null, null);
    }

    public DLinkNode()
    {
        this(null, null, null);
    }

    public String toString()                     //���ؽ��Ԫ��ֵ��Ӧ���ַ���
    {
        return this.data.toString();
    }
}
