//替换子串

public class ReplaceString
{
    public static String replace(String source, String sub, String replacement)
    {                                            //将source串中第一个与sub匹配的子串替换成replacement串
        int i=BF.indexOf(source,sub,0);          //返回匹配子串的序号，从0开始查找  
        if(i!=-1)
            return source.substring(0,i)+replacement+source.substring(i+sub.length());//连接3个串
        return source;                           //匹配失败时返回主串
    } 
    public static String replaceAll(String source, String sub, String replacement)
    {                                            //将source串中所有与sub匹配的子串全部替换成replacement串
        int i=BF.indexOf(source,sub,0);
        while (i!=-1)
        {
            source = source.substring(0,i)+replacement+source.substring(i+sub.length());
            i=BF.indexOf(source,sub,i+1);        //从下一个字符开始再次查找匹配子串
        }
        return source;
    } 

    public static String delete(String source, String sub)     //删除source串中第一个与sub匹配的子串
    {
        int i=BF.indexOf(source,sub,0);
        if (i!=-1)
            return source.substring(0,i)+source.substring(i+sub.length());
        return source;
    }
    public static String deleteAll(String source, String sub)   //删除source串中所有与sub匹配的子串
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
