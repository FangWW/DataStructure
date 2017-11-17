//【例1.4】  已排序数组的顺序查找。
//判断指定字符串是否为Java关键字。

public class Keyword
{
    static String[] keywords={"abstract","boolean","break","byte","case","catch","char","class",
        "continue","default","do","double","else","extends","false","final","finally","float","for","if",
        "implements","import","instanceof","int","interface","long","native","new","null","package",
        "private","protected","public","return","short","static","super","switch","synchronized","this",
        "throw","throws","transient","true","try","void","volatile","while"};

    public static void main(String[] args) 
    {
        if (args.length>0)                                 //有命令行参数时
            for (int i=0; i<args.length; i++)
            {
                int find = SortedArray.indexOf(keywords, args[i]);
                System.out.println(args[i]+((find!=-1)?"":"不")+"是关键字");
            }
        else
        {
            String key="const";
            int find = SortedArray.indexOf(keywords, key);
            System.out.println(key+((find!=-1)?"":"不")+"是关键字");
        }
    }
}
/*
程序运行结果如下：
abstract? boolean? break? byte? case? catch? char? class? const不是关键字

命令行参数为Welcome public时，
Welcome 不是关键字
public 是关键字

*/

