//两变量交换问题讨论。

public class Swap
{
    public static void swap(int x, int y)
    {
        int temp=x;
        x=y;
        y=temp;
    }

    public static void swap(Integer x, Integer y)
    {
        Integer temp=x;
        x=y;
        y=temp;
    }

    public static void swap(int[] table, int i, int j)     //交换数组中下标为i、j的元素
    { 
        if(table!=null && i>=0 && i<table.length && j>=0 && j<table.length && i!=j)   //判断i、j是否越界
        {
            int temp = table[j];
            table[j] = table[i];
            table[i] = temp;
        }
    }

    public static void swap(Object[] table, int i, int j)     //交换数组中下标为i、j的元素
    { 
        if(table!=null && i>=0 && i<table.length && j>=0 && j<table.length && i!=j)   //判断i、j是否越界
        {
            Object temp = table[j];
            table[j] = table[i];
            table[i] = temp;
        }
    }

    public static void main(String args[]) 
    {
        Integer a = new Integer(1);
        Integer b = new Integer(2);
        swap(a,b);
        System.out.println("a="+a.toString()+"; b="+b.toString());
    }
}

/*
程序运行结果如下：
a=1; b=2

*/
