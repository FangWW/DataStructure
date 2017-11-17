//8.2.2   基于有序顺序表的折半查找

public class BSArray
{
    public static void print(int[] table)                  //输出数组元素
    {
        if (table!=null)
            for (int i=0; i<table.length; i++)
                System.out.print(" "+table[i]);
        System.out.println();
    }
/*
    public static int binarySearch(int[] table, int value) //折半查找算法，数组元素已按升序排列
    {                                                      //若查找成功返回元素下标，否则返回-1
        if (table!=null)
        {
            int low=0, high=table.length-1;                //查找范围的下界和上界
            while (low<=high)                              //边界有效
            {
                int mid = (low+high)/2;                    //中间位置，当前比较元素位置
                System.out.print(table[mid]+"? ");
                if (table[mid]==value) 
                    return mid;                            //查找成功
                else
                    if (table[mid]>value)                  //给定值小
                        high = mid-1;                      //查找范围缩小到前半段
                    else
                        low = mid+1;                       //查找范围缩小到后半段
            }
        }
        return -1;                                         //查找不成功
    }  
*/

    public static int binarySearch(Comparable[] table, Comparable cobj)   //折半查找算法，数组元素已按升序排列
    {                                  //在table数组中查找cobj，若查找成功返回元素下标，否则返回-1
        return binarySearch(table, cobj, 0, table.length-1);
    }  

    public static int binarySearch(Comparable[] table, Comparable cobj, int low, int high)  //折半查找算法，数组元素已按升序排列
    {                                                      //low、high指定查找范围的下界和上界
        if (table!=null && cobj!=null)
        {
            while (low<=high)                              //边界有效
            {
                int mid = (low+high)/2;                    //中间位置，当前比较元素位置
                System.out.print(table[mid]+"? ");
                if (cobj.compareTo(table[mid])==0)         //对象比较大小
                    return mid;                            //查找成功
                else
                    if (cobj.compareTo(table[mid])<0)      //给定对象小
                        high = mid-1;                      //查找范围缩小到前半段
                    else
                        low = mid+1;                       //查找范围缩小到后半段
            }
        }
        return -1;                                         //查找不成功
    }  

    
    public static void main(String[] args)
    {
        int[] table ={8,17,26,32,40,72,87,99};             //已按升序排序
        System.out.print("\n已按升序排序的关键字序列: ");
        print(table);
        int key=99;
        System.out.println("折半查找 "+key+", "+((binarySearch(table,key)==-1)?"不":"")+"成功");
        key=25;
        System.out.println("折半查找 "+key+", "+((binarySearch(table,key)==-1)?"不":"")+"成功");
    }
    
//第8章习题
//递归算法
    public static int binarySearch(int[] table, int value) //折半查找算法，数组元素已按升序排列
    {                                                      //若查找成功返回元素下标，否则返回-1
        if (table!=null)
            return binarySearch(table, value, 0, table.length-1);
        return -1;
    }  
    private static int binarySearch(int[] table, int value, int low, int high)  //折半查找算法，数组元素已按升序排列
    {                                                      //low、high指定查找范围的下界和上界
        if (low<=high)                                     //边界有效
        {
            int mid = (low+high)/2;                        //中间位置，当前比较元素位置
            System.out.print(table[mid]+"? ");
            if (table[mid]==value) 
                return mid;                                //查找成功
            else
                if (table[mid]>value)                      //给定值小
                    return binarySearch(table, value, low, mid-1);   //查找范围缩小到前半段
                else
                    return binarySearch(table, value, mid+1, high);  //查找范围缩小到后半段
        }
        return -1;
    }
}


/* 
程序运行结果如下：
已按升序排序的关键字序列:  8 17 26 32 40 72 87 99 
32? 72? 87? 99? 折半查找 99, 成功 
32? 17? 26? 折半查找 25, 不成功 

*/
