//【例3.1】  常量串的基本操作。//JDK 1.6

import java.io.Serializable;                     //序列化
import java.util.Arrays;                         //实用包中的数组类

public final class String1
    implements java.io.Serializable//, Comparable<String1>
{
    private final char[] value;                  //字符数组，私有最终变量，只能赋值一次

    public String1()                             //构造一个空串
    {
        this.value = new char[0];
    }

    public String1(char[] value)                 //以字符数组构造串对象
    {
        this.value = new char[value.length];     //当value==null时，Java抛出空对象异常
        for (int i=0; i<value.length; i++)       //复制数组
            this.value[i] = value[i];
//        java.lang.System.arraycopy(value,0,this.value,0,value.length);  //复制数组，功能for语句
//        this.value = java.util.Arrays.copyOf(value,value.length);       //复制数组，包括申请数组空间
    }

    public String1(String1 str)                  //拷贝构造方法，复制对象
    {
        this(str.value);
    }

    public int length()                          //返回字符串的长度
    {
        return this.value.length;
    }

    public char charAt(int index)                //返回串中序号为index的字符
    {
        if (index<0 || index >= this.value.length)
            throw new StringIndexOutOfBoundsException(index);  //抛出字符串索引越界异常
        return this.value[index];
    }

    public String toString() 
    {
        return new String(this.value);           //java.lang.String实现为 return this;
    }

    public String1 concat(String1 str)           //返回当前串与指定串str连接生成的新串
    {                                     	     //不改变当前串
        if (str==null || str.length()==0)        //欲连接的串为空时，返回当前串
            return this;
        
        char[] buffer = new char[this.value.length + str.length()];
        int i;
        for (i=0; i<this.value.length; i++)      //复制当前串
            buffer[i] = this.value[i];
        for (int j=0; j<str.value.length; j++)   //复制指定串str
            buffer[i+j] = str.value[j];
        return new String1(buffer);
    }

    public String1 substring(int begin, int end) //返回串中序号从begin至end-1的子串
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
            for (int i=0; i< buffer.length; i++) //复制子串
                buffer[i] = this.value[i+begin];
            return new String1(buffer);
        }
    }

    public String1 substring(int begin)          //返回串中序号从begin至串尾的子串
    {
        return substring(begin, value.length);
    }
    
    public static void main(String args[])
    {
        char[] chars={'a','b','c','d'};          //字符数组，只能在声明时赋值，不能用"abcd"
        String1 str = new String1(chars);        //以字符数组构造串对象
        chars[0]='y';                            //数组元素改了，对串没影响
        str = str.concat(str.substring(1,3));
        System.out.println("str=\""+str.toString()+"\"");
   }
}
/*
str="abcdbc"
*/

/*
可用
    public String1(java.lang.String original)      	//由字符串常量构造串对象
    {
        this.value = original.toCharArray();      	//获得字符串中的字符数组
    }

 */

 