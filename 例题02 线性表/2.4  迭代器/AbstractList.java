//�������Ա���

package dataStructure.linearList;
import java.util.Iterator;                       //����������ӿ�

public abstract class AbstractList<E> implements Iterable<E> 
{
    public abstract Iterator<E> iterator();      //��õ��������󣬳��󷽷�

    public String toString()                     //������ʾ���Ա�����Ԫ��ֵ���ַ���
    {
        Iterator<E> it = iterator();             //it��һ������������
        String str="(";
        while (it.hasNext())                     //���к��Ԫ��
        {
            E element = it.next();               //��ú��Ԫ��            
            str += element.toString();
            if (it.hasNext())
                str += ", ";
        } 
        return str+")";
    }

    public boolean contain(Object obj)          //�ж����Ա��Ƿ����ָ��Ԫ��
    {                                            //�������򷵻�true
        if (obj!=null)
        {
            Iterator<E> it = iterator();
            while (it.hasNext())
                if (obj.equals(it.next()))
                    return true;
        }
        return false;
    }

    //���У�������Ե�����̫���ʣ�java.util.LinkedList������
    public int indexOf(Object obj)                 //����ָ�������״γ���λ�ã�δ�ҵ�ʱ����-1
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
??    public boolean equals(Object obj)                  //�Ƚ��������Ա�����Ƿ����
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


 
 

java.util.LinkedList����ʵ��

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
