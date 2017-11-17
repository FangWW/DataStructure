//����3.2��  ʶ���ʶ����

public class Identifier
{
    public static boolean isIdentifier(String str)         //�ж�ָ���ַ����Ƿ�Ϊ��ʶ��
    {
        if (str!=null && str.length()>0)                   //��Ч�ַ���
        {
            char ch = str.charAt(0);                       //��1���ַ�
            if (ch>='A' && ch<='Z' || ch>='a' && ch<='z' || ch=='_' || ch=='$')  //��ĸ��ͷ
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
�������н�����£�
"_name1"  isIdentifier? true
"$_name1"  isIdentifier? true

"1name2age"  isIdentifier? false
"name1+"  isIdentifier? false

*/
