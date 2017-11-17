 //【例1.4】  已排序数组的顺序查找。

package dataStructure;                                     //声明当前文件中的类或接口在指定包或子包中
public class SortedArray
{
    public static void print(int[] table)                  //输出数组元素
    {
        if (table!=null)
            for (int i=0; i<table.length; i++)
                System.out.print(" "+table[i]);
        System.out.println();
    }

    public static boolean isSorted(int[] table)            //判断整数数组是否已按升序排序
    {                                                      //若已排序返回true，否则返回false
        if (table==null)
            return false;

        for (int i=0; i<table.length-1; i++)
            if (table[i]>table[i+1])
                return false;
        return true;
    }

    public static boolean isSorted(Comparable[] table)     //判断对象数组是否已按升序排序
    {                                                      //若已排序返回true，否则返回false
        if (table==null)
            return false;

        for (int i=0; i<table.length-1; i++)
            if (table[i].compareTo(table[i+1])>0)
                return false;
        return true;
    }

    public static int indexOf(int[] table, int value)      //顺序查找已排序（升序）的整数数组
    {                                                      //若查找成功返回元素下标，否则返回-1
        if (table!=null && isSorted(table))
            for(int i=0; i<table.length && table[i]<=value; i++)
            {
                System.out.print(table[i]+"? ");
                if (table[i]==value)
                    return i;
            }
        return -1;
    }  

    public static int indexOf(Comparable[] table, Comparable cobj)    //顺序查找已排序（升序）对象数组
    {                                                      //若查找成功返回元素下标，否则返回-1
        if (table!=null && cobj!=null && isSorted(table))
            for(int i=0; i<table.length && cobj.compareTo(table[i])>=0; i++)
            {
                System.out.print(table[i]+"? ");
                if (cobj.compareTo(table[i])==0)           //也可cobj.equals(table[i])
                    return i;
            }
        return -1;
    }  
}
/*    
    public static void main(String[] args)
    {
        int[] table ={52,26,97,19,66,8,49};                //无序
        System.out.print("无序的关键字序列: ");
        print(table);
        System.out.println("已排序? "+isSorted(table));

        int[] table_sorted ={8,17,26,32,40,72,87,99};      //已按升序排序
        System.out.print("\n已按升序排序的关键字序列: ");
        print(table_sorted);
        System.out.println("已排序? "+isSorted(table_sorted));
        int key=25;
        System.out.println("顺序查找 "+key+", "+((indexOf(table_sorted, key)==-1)?"不":"")+"成功");
    }
}
*/

/* 
程序运行结果如下：
无序的关键字序列:  52 26 97 19 66 8 49
已排序? false

已按升序排序的关键字序列:  8 17 26 32 40 72 87 99
已排序? true
8? 17? 顺序查找 25, 不成功

*/
