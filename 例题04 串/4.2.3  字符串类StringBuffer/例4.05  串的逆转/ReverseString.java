//����3.5��  ������ת��

public class ReverseString
{
    public static StringBuffer reverse(String str)         //����str��ת�󷵻�
    {
        StringBuffer temp = new StringBuffer(str.length());//����ָ�������Ŀմ�
        for (int i=str.length()-1; i>=0; i--)
            temp.append(str.substring(i,i+1));             //׷�ӵ��ַ��Ӵ�
        return temp;
    }   
     
    public static void main(String args[])
    {
        String str = "abcd";
        System.out.println("reverse(\""+str+"\")=\""+reverse(str)+"\"");
    }
}

/*
reverse("abcd")="dcba"
*/
