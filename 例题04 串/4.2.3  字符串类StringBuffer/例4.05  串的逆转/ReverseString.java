//【例3.5】  串的逆转。

public class ReverseString
{
    public static StringBuffer reverse(String str)         //将串str逆转后返回
    {
        StringBuffer temp = new StringBuffer(str.length());//构造指定容量的空串
        for (int i=str.length()-1; i>=0; i--)
            temp.append(str.substring(i,i+1));             //追加单字符子串
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
