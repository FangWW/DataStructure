package dataStructure.linearList;
public interface QQueue<E>             //���нӿ�
{
    boolean isEmpty();                 //�ж϶����Ƿ�Ϊ�գ����շ���true
    boolean enqueue(E element);        //Ԫ��element��ӣ��������ɹ�����true
    E dequeue();                       //���ӣ����ص�ǰ��ͷԪ�أ������пշ���null 
}
