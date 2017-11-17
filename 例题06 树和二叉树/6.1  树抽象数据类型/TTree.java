//���ӿ�

package dataStructure.tree;
public interface TTree<E>                        //���ӿ�
{
    boolean isEmpty();                           //�ж��Ƿ����
    E getRoot();                                 //���ظ����Ԫ��
    E getParent(E child);                        //����child�ĸ�ĸ���
    int getChildCount(E parent);                 //����parent�ĺ��ӽ����
    E getFirstChild(E parent);                   //����parent��һ�����ӽ��
    E getNextSibling(E element);                 //����element����һ���ֵܽ��

    void traverse();                             //������
    void insert(E parent, E element);            //����element��Ϊparent�ĺ��ӽ��
    void remove(E parent);                       //ɾ����parentΪ����������
    void clear();                                //���
}
