//���Ա�ӿ�

package dataStructure.linearList;                //�������ڵİ�

public interface LList<E>                        //���Ա�ӿ�
{
    boolean isEmpty();                           //�ж����Ա��Ƿ�Ϊ�գ����շ���true
    int length();                                //�������Ա���
    E get(int index);                            //�������Ϊindex�Ķ���index��ֵΪ0
    E set(int index, E element);                 //�������Ϊindex�Ķ���Ϊelement������ԭ����
    boolean add(int index, E element);           //����element���󣬲����������Ϊindex
    boolean add(E element);                      //����element���󣬲���λ��û��Լ��
    E remove(int index);                         //��ȥ���Ϊindex�Ķ��󣬷��ر���ȥ����
    void clear();                                //������Ա�
}
