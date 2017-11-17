//线性表的顺序存储结构
//建议，将成员变量n改为len

package dataStructure.linearList;
import dataStructure.linearList.LList;
import java.util.Iterator;                       //导入迭代器接口

public class SeqList<E> extends AbstractList<E> implements LList<E>    //顺序表类，实现线性表接口
{
    private Object[] table;                      //对象数组，私有成员
    private int n;                               //顺序表长度
    
    public SeqList(int capacity)                 //构造方法，创建指定容量的空表
    {
        this.table = new Object[Math.abs(capacity)];  //Math.abs(i)返回参数的绝对值
        this.n = 0;
    }
    public SeqList()                             //指定空表的默认容量
    {
        this(16);
    }

    public boolean isEmpty()                     //判断顺序表是否为空，若空返回true
    {                                            //O(1)
        return this.n==0;
    }

    public int length()                          //返回顺序表长度，O(1)
    {
        return this.n;
    }

    public E get(int index)                      //返回index位置的对象，index初值为0
    {                                            //O(1)，若index指定序号无效则返回null
        if (index>=0 && index<this.n)
            return (E)this.table[index];         //使用了未经检查或不安全的操作
//            return table[index];               //可行,返回Object
        return null;
    }

    public E set(int index, E element)           //设置index位置的对象为element，O(1)
    {                                            //若操作成功返回原对象，否则返回null
        if (index>=0 && index<this.n && element!=null)
        {
            E old = (E)this.table[index];
            this.table[index] = element;
            return old;
        }
        return null;
    }

    public boolean add(int index, E element)     //在index位置插入element对象，O(n)
    {                                            //不能插入null，若操作成功返回true
        if (element==null)
           return false;                         //不能添加null
    
        if (this.n==table.length)                //若数组满，则需要扩充顺序表容量
        {
            Object[] temp = this.table;         
            this.table = new Object[temp.length*2];    //重新申请一个容量更大的数组
            for (int i=0; i<temp.length; i++)    //复制数组元素，O(n)
                this.table[i] = temp[i];
        }
             
        if (index<0)                             //下标容错
            index=0;
        if (index>this.n)
            index = this.n;

        for (int j=this.n-1; j>=index; j--)      //元素后移，平均移动n/2
            this.table[j+1] = this.table[j];
        this.table[index] = element;
        this.n++;
        return true;
    }

    public boolean add(E element)                //在顺序表最后插入element对象
    {
        return add(this.n, element);
    }
    
    public E remove(int index)                   //移去index位置的对象，O(n)
    {                                            //若操作成功返回被移去对象，否则返回null
        if (this.n!=0 && index>=0 && index<this.n) 
        {
            E old = (E)this.table[index];
            for (int j=index; j<this.n-1; j++)   //元素前移，平均移动n/2
                this.table[j] = this.table[j+1];
            this.table[this.n-1]=null;
            this.n--;
            return old;                          //若操作成功返回被移去对象
        }
        return null;                             //未找到删除对象，操作不成功
    }
/*
    public void clear()                          //清空线性表
    {
        if (this.n!=0) 
        {
            for (int i=0; i<this.n; i++)
                this.table[i] = null;
            this.n=0;
        }
    }
*/
    public void clear()                          //清空线性表
    {
        this.n=0;
    }

    public String toString()                     //返回显示线性表所有元素值的字符串，形式为{,}
    {
        String str="(";
        if (this.n!=0)
        {
            for (int i=0; i<this.n-1; i++)
                str += this.table[i].toString()+", ";
            str += this.table[this.n-1].toString();
        }
        return str+") ";
    }
//以上实现LList接口，第2章内容
/*
可行，效率同上
    public String toString()                     //返回显示线性表所有元素值的字符串，形式为[,] 
    {
        String str="(";
        if (this.n()!=0)
        {
            for(int i=0; i<this.n()-1; i++)
                str += this.get(i).toString()+", ";
            str += this.get(this.n()-1).toString();
        }
        return str+")";
    }
*/

//以下2.4 迭代器内容
    public Iterator<E> iterator()                //返回一个迭代器对象
    {
        return new Itr();
    }

    private class Itr implements Iterator<E>     //私有内部类，实现迭代器接口
    {
        int cursor = 0;                          //元素索引

        public boolean hasNext()                 //若有后继元素，返回true
        {
            return cursor != n;                  //n是外部类的成员变量，表示顺序表长度
        }

        public E next()                          //返回后继元素
        {
            if (cursor != n)
            {
                E next = get(cursor);
                cursor++;
                return next;
            }
            return null;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();     //不支持该操作，抛出异常
        }
    }//内部类Itr结束


//以下第8章 8.2.1 顺序查找

    public int indexOf(E element)               //顺序查找指定对象
    {                                           //若查找成功返回首次出现位置，否则返回-1
        if (element!=null)
            for (int i=0; i<this.n; i++)
                if (this.table[i].equals(element))//对象采用equals()方法比较是否相等
                    return i;
        return -1;
    }

    public boolean contain(E element)           //以查找结果判断线性表是否包含指定对象
    {                                           //若包含返回true，否则返回false
        return this.indexOf(element)>=0;
    }


//以下是第8章 8.2.1 顺序查找习题
    public int lastIndexOf(E element)           //返回obj对象最后出现位置，若未找到返回-1
    {
        if (obj!=null)
            for (int i=this.n-1; i>=0; i--)
                if (obj.equals(this.table[i]))
                    return i;
        return -1;
    }

    public boolean remove(E element)            //移去首次出现的obj对象，若操作成功返回true
    {
        if (this.n!=0 && obj!=null)
            return this.remove(this.indexOf(obj))!=null;
        return false;
    }
    
    public boolean removeAll(E element)         //移去线性表中所有obj对象
    {
        boolean done=false;
        if (this.n!=0 && obj!=null)
        {
            int i=0;
            while (i<this.n)
                if (obj.equals(this.table[i]))
                {
                    this.remove(i);
                    done = true;
                }
                else
                    i++;
        }
        return done;
    }

    public boolean replace(Object obj, E element)//将首次出现的obj对象替换为element对象
    {                                            //若操作成功返回true，O(n/2)
        if (obj!=null && element!=null)
        {
            int i = this.indexOf(obj);           //查找obj对象首次出现位置
            if (i!=-1)
            {
                this.table[i] = element;
                return true;
            }
        }
        return false;
    }

    public boolean replaceAll(Object obj, E element)   //将线性表中所有obj对象替换为element对象
    {
        boolean done=false;
        if (obj!=null && element!=null)
            for (int i=0; i<this.n; i++)
                if (obj.equals(this.table[i]))
                {
                    this.table[i] = element;
                    done = true;
                }
        return done;
    }

    public boolean equals(Object obj)            //比较两个顺序表对象是否相等
    {                                            //O(n)
        if (this==obj)
            return true;
        
        if (obj instanceof SeqList)
        {
            SeqList<E> list = (SeqList<E>)obj;
            for (int i=0; i<this.length(); i++)
                if (!(this.get(i).equals(list.get(i))))
                    return false; 
            return true;
        }
        return false;
    }
}
