//����3.3��  String�Ĳ��롢ɾ��������

public class InsertString 
{
    public static String insert(String source, int index, String str) //��source����index������str
    {                                                                 //���ز����Ĵ���source������
        return source.substring(0,index)+str+source.substring(index);
    }
    
    public static String delete(String source, int begin, int end)    //ɾ��source���д�begin��end-1�����Ӵ�
    {                                                                 //����ɾ����Ĵ���source������
        return source.substring(0,begin)+source.substring(end);
    }

    public static void main(String args[])
    {
        String str="abcd";
        System.out.println("insert(\""+str+"\",1,\""+"xyz"+"\")=\""+insert(str,1,"xyz")+"\"");
        System.out.println("delete(\""+str+"\",1,2)=\""+delete(str,1,2)+"\"");
    }
}

/*
insert("abcd",1,"xyz")="axyzbcd"
delete("abcd",1,2)="acd"
*/