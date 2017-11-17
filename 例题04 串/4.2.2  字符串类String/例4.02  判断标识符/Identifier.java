//【例3.2】  识别标识符。

public class Identifier
{
    public static boolean isIdentifier(String str)         //判断指定字符串是否为标识符
    {
        if (str!=null && str.length()>0)                   //有效字符串
        {
            char ch = str.charAt(0);                       //第1个字符
            if (ch>='A' && ch<='Z' || ch>='a' && ch<='z' || ch=='_' || ch=='$')  //字母开头
            {
                for (int i=1; i<str.length(); i++)
                {
                    ch = str.charAt(i);
                    if (!(ch>='A' && ch<='Z' || ch>='a' && ch<='z' || ch=='_' || ch=='$' || ch>='0' && ch<='9'))
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) 
    {
        String str = "_name1";
        System.out.println("\""+str+"\" isIdentifier? "+isIdentifier(str));
    }
}

/*
程序运行结果如下：
"_name1"  isIdentifier? true
"$_name1"  isIdentifier? true

"1name2age"  isIdentifier? false
"name1+"  isIdentifier? false

*/
