//【例3.3】  String的插入、删除操作。

public class InsertString 
{
    public static String insert(String source, int index, String str) //在source串的index处插入str
    {                                                                 //返回插入后的串，source串不变
        return source.substring(0,index)+str+source.substring(index);
    }
    
    public static String delete(String source, int begin, int end)    //删除source串中从begin到end-1处的子串
    {                                                                 //返回删除后的串，source串不变
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