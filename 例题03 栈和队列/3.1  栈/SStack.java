package dataStructure.linearList;
public interface SStack<E>             //栈接口
{
    boolean isEmpty();                 //判断是否空栈，若空栈返回true
    boolean push(E element);           //元素element入栈，若操作成功返回true
    E pop();                           //出栈，返回当前栈顶元素，若栈空返回null 
    E get();                           //取栈顶元素值，未出栈，若栈空返回null
}
