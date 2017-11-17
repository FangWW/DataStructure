//【例1.3】  数组的顺序查找。
public class ArraySeqSearch
{
    public static int[] random(int n)                      //产生n个随机数，返回整型数组
    {
        if (n>0)
        {
            int table[] = new int[n];
            for (int i=0; i<table.length; i++)
                table[i] = (int)(Math.random()*100);       //产生一个0～100之间的随机数
            return table;                                  //返回一个数组
        }
        return null;
    }

    public static void print(int[] table)                  //输出数组元素
    {
        if (table!=null)
            for (int i=0; i<table.length; i++)
                System.out.print(" "+table[i]);
        System.out.println();
    }

    public static int indexOf(int[] table, int value)      //在整数数组中查找给定值
    {                                                      //若查找成功返回元素下标，否则返回-1
        if (table!=null)
            for(int i=0; i<table.length; i++)
            {
                System.out.print(table[i]+"? ");
                if (table[i]==value)                       //基本数据类型采用==、!=运算符进行比较
                    return i;
            }
        return -1;
    }  

    public static int indexOf(Object[] table, Object obj)  //在对象数组中查找给定值
    {                                                      //若查找成功返回元素下标，否则返回-1
        if (table!=null && obj!=null)
            for(int i=0; i<table.length; i++)
                if (obj.equals(table[i]))                  //对象采用equals()方法比较是否相等
                    return i;
        return -1;
    }
    
    public static void main(String[] args)
    {
        int[] table = random(10);                          //无序
        System.out.print("无序的关键字序列: ");
        print(table);
        int key=19;
        System.out.println("顺序查找 "+key+", "+((indexOf(table,key)==-1)?"不":"")+"成功");
        key=25;
        System.out.println("顺序查找 "+key+", "+((indexOf(table,key)==-1)?"不":"")+"成功");
    }
}

/* 
程序运行结果如下：
无序的关键字序列:  52 26 97 19 66 8 49
52? 26? 97? 19? 顺序查找 19, 成功
52? 26? 97? 19? 66? 8? 49? 顺序查找 25, 不成功

*/
