//抽象线性表类

package dataStructure.linearList;
import java.util.Iterator;                       //导入迭代器接口

public abstract class AbstractList<E> implements Iterable<E> 
{
    public abstract Iterator<E> iterator();      //获得迭代器对象，抽象方法

    public String toString()                     //返回显示线性表所有元素值的字符串
    {
        Iterator<E> it = iterator();             //it是一个迭代器对象
        String str="(";
        while (it.hasNext())                     //若有后继元素
        {
            E element = it.next();               //获得后继元素            
            str += element.toString();
            if (it.hasNext())
                str += ", ";
        } 
        return str+")";
    }

    public boolean contain(Object obj)          //判断线性表是否包含指定元素
    {                                            //若包含则返回true
        if (obj!=null)
        {
            Iterator<E> it = iterator();
            while (it.hasNext())
                if (obj.equals(it.next()))
                    return true;
        }
        return false;
    }

    //可行，但题意对单链表不太合适，java.util.LinkedList类中有
    public int indexOf(Object obj)                 //返回指定对象首次出现位置，未找到时返回-1
    {
        int index=-1;
        if (obj!=null) 
        {
            Iterator<E> it = iterator();
            while (it.hasNext())
            {
            	index++;
            	it.next();
            }
        }
        return index;
    }
}

/*
??    public boolean equals(Object obj)                  //比较两个线性表对象是否相等
    {
        if (obj == this)
            return true;
        if (!(obj instanceof LList))
            return false;

        Iterator<E> e1 = iterator();
        Iterator e2 = ((LList) obj)iterator();
        while(e1.hasNext() && e2.hasNext()) 
        {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }


 
 

java.util.LinkedList类中实现

    public boolean equals(Object o) 
    {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        ListIterator<E> e1 = listIterator();
        ListIterator e2 = ((List) o).listIterator();
        while(e1.hasNext() && e2.hasNext()) 
        {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }


 */
