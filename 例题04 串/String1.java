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

/*
    public boolean equals(Object obj)       	//比较字符串是否相等，覆盖Object类中方法
    {
        if (this == obj) 
            return true;
        
        if (obj instanceof String1) 
        {
            String1 str = (String1)obj;
            if (this.length() == str.length())  //长度相等时比较元素
            {
                int i = this.value.length;
                while (i>=0) 
                {
                    if (this.value[i] != str.value[i])
                        return false;
                    else i++;
                }
                return true;
            }
        }
        return false;
    }

    public boolean equalsIgnoreCase (String s)   	//比较字符串是否相等，忽略字母大小写
    public boolean equalsIgnoreCase(String anotherString) {
        return (this == anotherString) ? true :
               (anotherString != null) && (anotherString.count == count) &&
               regionMatches(true, 0, anotherString, 0, count);
    }

    public int compareTo(String anotherString)   	//比较字符串的大小，实现Comparable接口方法
    {
        int len1 = count;
        int len2 = anotherString.count;
        int n = Math.min(len1, len2);
        char v1[] = value;
        char v2[] = anotherString.value;
        int i = offset;
        int j = anotherString.offset;

        if (i == j) {
            int k = i;
            int lim = n + i;
            while (k < lim) {
                char c1 = v1[k];
                char c2 = v2[k];
                if (c1 != c2) {
                    return c1 - c2;
                }
                k++;
            }
        } else {
            while (n-- != 0) {
                char c1 = v1[i++];
                char c2 = v2[j++];
                if (c1 != c2) {
                    return c1 - c2;
                }
            }
        }
        return len1 - len2;
    }
    public int compareToIgnoreCase(String str)   	//比较字符串的大小，忽略字母大小写
    //取字符或子串方法
    //转换方法
    public String toLowerCase()           	//全部转换成小写字母
    public String toUpperCase()           	//全部转换成大写字母
    public String trim()                  	//删除字符串中所有空格
    public String trim() {
        int len = count;
        int st = 0;
        int off = offset;      
        char[] val = value;   

        while ((st < len) && (val[off + st] <= ' ')) {
            st++;
        }
        while ((st < len) && (val[off + len - 1] <= ' ')) {
            len--;
        }
        return ((st > 0) || (len < count)) ? substring(st, len) : this;
    }
    public String replace(char oldChar,char newChar)  //以newChar字符替换串中所有oldChar字符
    {
        if (oldChar != newChar) {
            int len = count;
            int i = -1;
            char[] val = value; 
            int off = offset;   

            while (++i < len) {
                if (val[off + i] == oldChar) {
                    break;
                }
            }
            if (i < len) {
                char buf[] = new char[len];
                for (int j = 0 ; j < i ; j++) {
                    buf[j] = val[off+j];
                }
                while (i < len) {
                    char c = val[off + i];
                    buf[i] = (c == oldChar) ? newChar : c;
                    i++;
                }
                return new String(0, len, buf);
            }
        }
        return this;
    }
    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    public int indexOf(String str, int fromIndex) {
        return indexOf(value, offset, count,
                       str.value, str.offset, str.count, fromIndex);
    }
    public int indexof(String1 sub)      //返回子串sub，子串的序号
    {
        int i=0,j=0;
        boolean yes=false;
        while(sub.length1()>0 && i<number && !yes)
        {
            j=0;
            while(j<sub.length1() && this.table[i+j]==sub.table[j])
                j++;
            if(j>=sub.length1())
                yes=true;
            else
                i++;
        }
        if(yes)
            return i+1;                  //序号为下标i加1
        else
            return 0; 
    } 
    static int indexOf(char[] source, int sourceOffset, int sourceCount,
                       char[] target, int targetOffset, int targetCount,
                       int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
            if (fromIndex < 0) {
                fromIndex = 0;
            }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first  = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            //* Look for first character. 
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            //* Found first character, now look at the rest of v2 
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j] ==
                         target[k]; j++, k++);

                if (j == end) {
                    //* Found whole string. 
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }
*/
 