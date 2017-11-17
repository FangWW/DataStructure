//����3.4��  �������Ĳ���ʵ�֡�

import java.io.Serializable;                     //���л�

public final class StringBuffer1 //implements java.io.Serializable
{
    private char[] value;                        //�ַ����飬˽�г�Ա����
    private int n;                               //���ĳ���

    public StringBuffer1(int capacity)           //����ָ�������Ŀմ�
    {
        value = new char[capacity];
        n = 0;
    }
    public StringBuffer1()                       //��Ĭ����������մ�
    {
        this(16);
    }

    public StringBuffer1(String str)             //���ַ����������촮����
    {
        this(str.length() + 16);
        append(str);
    }
    
    public int length()                          //�����ַ�������
    {
         return n;                               //����value.length����������
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

    void expandCapacity(int minimumCapacity)               //��������������ָ����С����
    {
        if (minimumCapacity > value.length) 
        {
            int newCapacity = (value.length + 1) * 2;
            if (newCapacity < 0)                           //�������
                newCapacity = Integer.MAX_VALUE;           //�����������������int���ֵ
            else if (minimumCapacity > newCapacity) 
                    newCapacity = minimumCapacity;

            char[] temp = value;                           //��������
            value = new char[newCapacity];
            for(int i=0; i<temp.length; i++)
                value[i] = temp[i];
        }
    }

    public synchronized StringBuffer1 insert(int index, String str)  //��index�����봮
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
            value[len+i] = value[i];                       //��index��ʼ����ƶ�len���ַ�
        for (int i=0; i<len; i++)                           //�����ַ���str
            value[index+i] = str.charAt(i);
        n = newCount;
        return this;
    }
    public synchronized StringBuffer1 insert(int index, boolean b)   //��index���������ֵת���ɵĴ�
    {
        return b ? insert(index,"true") : insert(index,"false");
    }
    public synchronized StringBuffer1 append(String str)             //synchronized ���ָ����
    {
        return insert(n,str);
    }

    public synchronized StringBuffer1 delete(int begin, int end)     //ɾ����begin��end-1���Ӵ�
    {
        if (begin < 0)                                               //�ݴ�
            begin = 0;
        if (end > n)
            end = n;
        if (begin > end)
            throw new StringIndexOutOfBoundsException(end - begin);

        for(int i=0; i<n-end; i++)                     //��end��ʼ����β���Ӵ���ǰ�ƶ�
            value[begin+i] = value[end+i];
        n -= end-begin;
        return this;
    }

    public synchronized String toString()  
    {
        return new String(value, 0, n);                    //���ַ�����value���촮
    }

    public static void main(String args[])
    {
        StringBuffer1 strb = new StringBuffer1("abcd");    //���ַ����������촮����
        strb.insert(1,"xy");                               //���룬�ı�����strb
        strb.append("z");                                  //�������� 
        System.out.println("\""+strb.toString()+"\".delete(1,3)=\""+strb.delete(1,3).toString()+"\"");
   }
}
/*
"axybcdz".delete(1,3)="abcdz"
  
*/