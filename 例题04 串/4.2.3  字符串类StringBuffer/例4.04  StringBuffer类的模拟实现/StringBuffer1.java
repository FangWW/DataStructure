//【例3.4】  变量串的操作实现。

import java.io.Serializable;                     //序列化

public final class StringBuffer1 //implements java.io.Serializable
{
    private char[] value;                        //字符数组，私有成员变量
    private int n;                               //串的长度

    public StringBuffer1(int capacity)           //构造指定容量的空串
    {
        value = new char[capacity];
        n = 0;
    }
    public StringBuffer1()                       //以默认容量构造空串
    {
        this(16);
    }

    public StringBuffer1(String str)             //以字符串常量构造串对象
    {
        this(str.length() + 16);
        append(str);
    }
    
    public int length()                          //返回字符串长度
    {
         return n;                               //区别，value.length是数组容量
    }
    
    public synchronized char charAt(int index)
    {
        if ((index < 0) || (index >= n))
            throw new StringIndexOutOfBoundsException(index);
        return value[index];
    }
    public void setCharAt(int index, char ch) 
    {
        if ((index < 0) || (index >= n))
            throw new StringIndexOutOfBoundsException(index);
        value[index] = ch;
    }

    void expandCapacity(int minimumCapacity)               //扩充容量，参数指定最小容量
    {
        if (minimumCapacity > value.length) 
        {
            int newCapacity = (value.length + 1) * 2;
            if (newCapacity < 0)                           //整数溢出
                newCapacity = Integer.MAX_VALUE;           //数组最大容量是整型int最大值
            else if (minimumCapacity > newCapacity) 
                    newCapacity = minimumCapacity;

            char[] temp = value;                           //复制数组
            value = new char[newCapacity];
            for(int i=0; i<temp.length; i++)
                value[i] = temp[i];
        }
    }

    public synchronized StringBuffer1 insert(int index, String str)  //在index处插入串
    {
        if ((index < 0) || (index > length()))
            throw new StringIndexOutOfBoundsException(index);
        if (str == null)
            str = "null";
        int len = str.length();
        int newCount = n + len;
        if (newCount > value.length)
            expandCapacity(newCount);

        for (int i=n-1; i>=index; i--)
            value[len+i] = value[i];                       //从index开始向后移动len个字符
        for (int i=0; i<len; i++)                           //复制字符串str
            value[index+i] = str.charAt(i);
        n = newCount;
        return this;
    }
    public synchronized StringBuffer1 insert(int index, boolean b)   //在index处插入变量值转换成的串
    {
        return b ? insert(index,"true") : insert(index,"false");
    }
    public synchronized StringBuffer1 append(String str)             //synchronized 添加指定串
    {
        return insert(n,str);
    }

    public synchronized StringBuffer1 delete(int begin, int end)     //删除从begin到end-1的子串
    {
        if (begin < 0)                                               //容错
            begin = 0;
        if (end > n)
            end = n;
        if (begin > end)
            throw new StringIndexOutOfBoundsException(end - begin);

        for(int i=0; i<n-end; i++)                     //从end开始至串尾的子串向前移动
            value[begin+i] = value[end+i];
        n -= end-begin;
        return this;
    }

    public synchronized String toString()  
    {
        return new String(value, 0, n);                    //以字符数组value构造串
    }

    public static void main(String args[])
    {
        StringBuffer1 strb = new StringBuffer1("abcd");    //以字符串常量构造串对象
        strb.insert(1,"xy");                               //插入，改变主串strb
        strb.append("z");                                  //添加在最后 
        System.out.println("\""+strb.toString()+"\".delete(1,3)=\""+strb.delete(1,3).toString()+"\"");
   }
}
/*
"axybcdz".delete(1,3)="abcdz"
  
*/