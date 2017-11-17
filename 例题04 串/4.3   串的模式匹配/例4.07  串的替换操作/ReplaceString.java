//�滻�Ӵ�

public class ReplaceString
{
    public static String replace(String source, String sub, String replacement)
    {                                            //��source���е�һ����subƥ����Ӵ��滻��replacement��
        int i=BF.indexOf(source,sub,0);          //����ƥ���Ӵ�����ţ���0��ʼ����  
        if(i!=-1)
            return source.substring(0,i)+replacement+source.substring(i+sub.length());//����3����
        return source;                           //ƥ��ʧ��ʱ��������
    } 
    public static String replaceAll(String source, String sub, String replacement)
    {                                            //��source����������subƥ����Ӵ�ȫ���滻��replacement��
        int i=BF.indexOf(source,sub,0);
        while (i!=-1)
        {
            source = source.substring(0,i)+replacement+source.substring(i+sub.length());
            i=BF.indexOf(source,sub,i+1);        //����һ���ַ���ʼ�ٴβ���ƥ���Ӵ�
        }
        return source;
    } 

    public static String delete(String source, String sub)     //ɾ��source���е�һ����subƥ����Ӵ�
    {
        int i=BF.indexOf(source,sub,0);
        if (i!=-1)
            return source.substring(0,i)+source.substring(i+sub.length());
        return source;
    }
    public static String deleteAll(String source, String sub)   //ɾ��source����������subƥ����Ӵ�
    {
        int i=BF.indexOf(source,sub,0);
        while (i!=-1)
        {
            source = source.substring(0,i)+source.substring(i+sub.length());
            i=BF.indexOf(source,sub,i);
        }
        return source;
    }

    public static void main(String args[]) 
    {
//        String source="abbabaaba", pattern="aba", replacement="xy";
        String source="abab", pattern="a", replacement="ab";
        System.out.println("replace(\""+source+"\", \""+pattern+"\", \""+replacement+"\")="+replace(source, pattern,replacement));
        System.out.println("replaceAll(\""+source+"\", \""+pattern+"\", \""+replacement+"\")="+replaceAll(source, pattern,replacement));
        System.out.println("delete(\""+source+"\", \""+pattern+"\")="+delete(source, pattern));
        System.out.println("deleteAll(\""+source+"\", \""+pattern+"\")="+deleteAll(source, pattern));
    }
}

/*
replace("abbabaaba", "aba", "xy")=abbxyaba
replaceAll("abbabaaba", "aba", "xy")=abbxyxy
delete("abbabaaba", "aba")=abbaba
deleteAll("abbabaaba", "aba")=abb

replace("abab", "a", "ab")=abbab
replaceAll("abab", "a", "ab")=abbabb
delete("abab", "a")=bab
deleteAll("abab", "a")=bb
*/
