//���ݽṹ�ӿ�

public interface Structure<E>                    //���ݽṹ�ӿ� 
{                                                //E�Ƿ��Ͳ�����ָ��Ԫ������
    boolean isEmpty();                           //�ж��Ƿ�Ϊ�գ����շ���true
    int length();                                //����Ԫ�ظ���
    boolean add(E element);                      //����Ԫ��
    void clear();                                //���
    boolean contain(Object obj);                 //�ж��Ƿ����obj��������������true
    boolean replace(Object obj, E element);      //���״γ��ֵ�obj�����滻Ϊelement����
    boolean remove(Object obj);                  //��ȥ�״γ��ֵ�obj����
}
