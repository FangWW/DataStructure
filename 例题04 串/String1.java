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

/*
    public boolean equals(Object obj)       	//�Ƚ��ַ����Ƿ���ȣ�����Object���з���
    {
        if (this == obj) 
            return true;
        
        if (obj instanceof String1) 
        {
            String1 str = (String1)obj;
            if (this.length() == str.length())  //�������ʱ�Ƚ�Ԫ��
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

    public boolean equalsIgnoreCase (String s)   	//�Ƚ��ַ����Ƿ���ȣ�������ĸ��Сд
    public boolean equalsIgnoreCase(String anotherString) {
        return (this == anotherString) ? true :
               (anotherString != null) && (anotherString.count == count) &&
               regionMatches(true, 0, anotherString, 0, count);
    }

    public int compareTo(String anotherString)   	//�Ƚ��ַ����Ĵ�С��ʵ��Comparable�ӿڷ���
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
    public int compareToIgnoreCase(String str)   	//�Ƚ��ַ����Ĵ�С��������ĸ��Сд
    //ȡ�ַ����Ӵ�����
    //ת������
    public String toLowerCase()           	//ȫ��ת����Сд��ĸ
    public String toUpperCase()           	//ȫ��ת���ɴ�д��ĸ
    public String trim()                  	//ɾ���ַ��������пո�
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
    public String replace(char oldChar,char newChar)  //��newChar�ַ��滻��������oldChar�ַ�
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
    public int indexof(String1 sub)      //�����Ӵ�sub���Ӵ������
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
            return i+1;                  //���Ϊ�±�i��1
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
 