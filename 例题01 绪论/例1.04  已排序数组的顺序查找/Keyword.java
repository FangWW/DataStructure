//����1.4��  �����������˳����ҡ�
//�ж�ָ���ַ����Ƿ�ΪJava�ؼ��֡�

public class Keyword
{
    static String[] keywords={"abstract","boolean","break","byte","case","catch","char","class",
        "continue","default","do","double","else","extends","false","final","finally","float","for","if",
        "implements","import","instanceof","int","interface","long","native","new","null","package",
        "private","protected","public","return","short","static","super","switch","synchronized","this",
        "throw","throws","transient","true","try","void","volatile","while"};

    public static void main(String[] args) 
    {
        if (args.length>0)                                 //�������в���ʱ
            for (int i=0; i<args.length; i++)
            {
                int find = SortedArray.indexOf(keywords, args[i]);
                System.out.println(args[i]+((find!=-1)?"":"��")+"�ǹؼ���");
            }
        else
        {
            String key="const";
            int find = SortedArray.indexOf(keywords, key);
            System.out.println(key+((find!=-1)?"":"��")+"�ǹؼ���");
        }
    }
}
/*
�������н�����£�
abstract? boolean? break? byte? case? catch? char? class? const���ǹؼ���

�����в���ΪWelcome publicʱ��
Welcome ���ǹؼ���
public �ǹؼ���

*/

