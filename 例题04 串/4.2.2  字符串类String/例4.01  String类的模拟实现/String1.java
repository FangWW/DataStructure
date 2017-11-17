//����3.1��  �������Ļ���������//JDK 1.6

import java.io.Serializable;                     //���л�
import java.util.Arrays;                         //ʵ�ð��е�������

public final class String1
    implements java.io.Serializable//, Comparable<String1>
{
    private final char[] value;                  //�ַ����飬˽�����ձ�����ֻ�ܸ�ֵһ��

    public String1()                             //����һ���մ�
    {
        this.value = new char[0];
    }

    public String1(char[] value)                 //���ַ����鹹�촮����
    {
        this.value = new char[value.length];     //��value==nullʱ��Java�׳��ն����쳣
        for (int i=0; i<value.length; i++)       //��������
            this.value[i] = value[i];
//        java.lang.System.arraycopy(value,0,this.value,0,value.length);  //�������飬����for���
//        this.value = java.util.Arrays.copyOf(value,value.length);       //�������飬������������ռ�
    }

    public String1(String1 str)                  //�������췽�������ƶ���
    {
        this(str.value);
    }

    public int length()                          //�����ַ����ĳ���
    {
        return this.value.length;
    }

    public char charAt(int index)                //���ش������Ϊindex���ַ�
    {
        if (index<0 || index >= this.value.length)
            throw new StringIndexOutOfBoundsException(index);  //�׳��ַ�������Խ���쳣
        return this.value[index];
    }

    public String toString() 
    {
        return new String(this.value);           //java.lang.Stringʵ��Ϊ return this;
    }

    public String1 concat(String1 str)           //���ص�ǰ����ָ����str�������ɵ��´�
    {                                     	     //���ı䵱ǰ��
        if (str==null || str.length()==0)        //�����ӵĴ�Ϊ��ʱ�����ص�ǰ��
            return this;
        
        char[] buffer = new char[this.value.length + str.length()];
        int i;
        for (i=0; i<this.value.length; i++)      //���Ƶ�ǰ��
            buffer[i] = this.value[i];
        for (int j=0; j<str.value.length; j++)   //����ָ����str
            buffer[i+j] = str.value[j];
        return new String1(buffer);
    }

    public String1 substring(int begin, int end) //���ش�����Ŵ�begin��end-1���Ӵ�
    {
        if (begin < 0) 
            throw new StringIndexOutOfBoundsException(begin);
        if (end > value.length) 
            throw new StringIndexOutOfBoundsException(end);
        if (begin > end) 
            throw new StringIndexOutOfBoundsException(end - begin);
        
        if (begin==0 && end == value.length) 
            return this;
        else
        {
            char[] buffer = new char[end - begin];
            for (int i=0; i< buffer.length; i++) //�����Ӵ�
                buffer[i] = this.value[i+begin];
            return new String1(buffer);
        }
    }

    public String1 substring(int begin)          //���ش�����Ŵ�begin����β���Ӵ�
    {
        return substring(begin, value.length);
    }
    
    public static void main(String args[])
    {
        char[] chars={'a','b','c','d'};          //�ַ����飬ֻ��������ʱ��ֵ��������"abcd"
        String1 str = new String1(chars);        //���ַ����鹹�촮����
        chars[0]='y';                            //����Ԫ�ظ��ˣ��Դ�ûӰ��
        str = str.concat(str.substring(1,3));
        System.out.println("str=\""+str.toString()+"\"");
   }
}
/*
str="abcdbc"
*/

/*
����
    public String1(java.lang.String original)      	//���ַ����������촮����
    {
        this.value = original.toCharArray();      	//����ַ����е��ַ�����
    }

 */

 