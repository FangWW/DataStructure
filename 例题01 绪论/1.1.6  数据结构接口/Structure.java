//数据结构接口

public interface Structure<E>                    //数据结构接口 
{                                                //E是泛型参数，指定元素类型
    boolean isEmpty();                           //判断是否为空，若空返回true
    int length();                                //返回元素个数
    boolean add(E element);                      //插入元素
    void clear();                                //清空
    boolean contain(Object obj);                 //判断是否包含obj对象，若包含返回true
    boolean replace(Object obj, E element);      //将首次出现的obj对象替换为element对象
    boolean remove(Object obj);                  //移去首次出现的obj对象
}
