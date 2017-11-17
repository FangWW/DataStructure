//�������ӿ�

package dataStructure.tree;
public interface BinaryTTree<E>                            //�������ӿ�
{
    boolean isEmpty();                                     //�ж��Ƿ�ն�����
    BinaryNode<E> getRoot();                               //���ض������ĸ����
    BinaryNode<E> getParent(BinaryNode<E> node);           //����node�ĸ�ĸ���
    BinaryNode<E> getLeftChild(BinaryNode<E> node);        //����node�����ӽ��
    BinaryNode<E> getRightChild(BinaryNode<E> node);       //����node���Һ��ӽ��

    void preOrder();                                       //�ȸ��������������
    void inOrder();                                        //�и��������������
    void postOrder();                                      //����������������
    void levelOrder();                                     //����α���������

    BinaryNode<E> search(E element);                       //���Ҳ�����Ԫ��Ϊelement�Ľ��
    void insert(BinaryNode<E> p, E element, String child); //����elementԪ����Ϊp���ĺ��ӣ�childָ����/�Һ���
    void remove(BinaryNode<E> p, String child);            //ɾ��p������/������
    void clear();                                          //���

}
