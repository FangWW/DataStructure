package dataStructure.linearList;
public interface QQueue<E>             //队列接口
{
    boolean isEmpty();                 //判断队列是否为空，若空返回true
    boolean enqueue(E element);        //元素element入队，若操作成功返回true
    E dequeue();                       //出队，返回当前队头元素，若队列空返回null 
}
