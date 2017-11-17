//第9章   排序

public class Array
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

    public static void insertSort(int[] table)             //直接插入排序
    {                                                      //数组是引用类型，元素值将被改变
        System.out.println("直接插入排序");
        for (int i=1; i<table.length; i++)                  //n-1趟扫描
        {
            int temp=table[i], j;                          //每趟将table[i]插入到前面已排序的序列中
//          System.out.print("移动");
            for (j=i-1; j>-1 && temp<table[j]; j--)        //将前面较大元素向后移动
            {
//                System.out.print(table[j]+", ");
                table[j+1] = table[j];
            }
            table[j+1] = temp;                             //temp值到达插入位置
            System.out.print("第"+i+"趟: ");
            print(table);
        }
    }

    public static void shellSort(int[] table)              //希尔排序
    {
        System.out.println("希尔排序");
        for (int delta=table.length/2; delta>0; delta/=2)  //控制增量，增量减半，若干趟扫描
        {
            for (int i=delta; i<table.length; i++)         //一趟中若干组，每个元素在自己所属组内进行直接插入排序
            {
                int temp = table[i];                       //当前待插入元素
                int j=i-delta;                             //相距delta远
                while (j>=0 && temp<table[j])              //一组中前面较大的元素向后移动
                {
                    table[j+delta] = table[j];
                    j-=delta;                              //继续与前面的元素比较
                } 
                table[j+delta] = temp;                     //插入元素位置
            }
            System.out.print("delta="+delta+"  ");
            print(table);
        }
    }

    private static void swap(int[] table, int i, int j)     //交换数组中下标为i、j的元素
    { 
        if (i>=0 && i<table.length && j>=0 && j<table.length && i!=j)   //判断i、j是否越界
        {
            int temp = table[j];
            table[j] = table[i];
            table[i] = temp;
        }
    }

    public static void bubbleSort(int[] table)             //冒泡排序
    {
        System.out.println("冒泡排序");
        boolean exchange=true;                             //是否交换的标记
        for (int i=1; i<table.length && exchange; i++)     //有交换时再进行下一趟，最多n-1趟
        {
            exchange=false;                                //假定元素未交换 
            for (int j=0; j<table.length-i; j++)           //一次比较、交换
                if (table[j]>table[j+1])                   //反序时，交换
                {
                    int temp = table[j];
                    table[j] = table[j+1];
                    table[j+1] = temp;
                    exchange=true;                         //有交换 
                }
            System.out.print("第"+i+"趟: ");
            print(table);
        }
    }

    public static void quickSort(int[] table)              //快速排序
    {
        quickSort(table, 0, table.length-1);
    }

    private static void quickSort(int[] table, int low, int high) //一趟快速排序，递归算法
    {                                                      //low、high指定序列的下界和上界
        if (low<high)                                      //序列有效
        {
             int i=low, j=high;
             int vot=table[i];                             //第一个值作为基准值
             while (i!=j)                                  //一趟排序
             {
                 while (i<j && vot<=table[j])              //从后向前寻找较小值
                     j--;
                 if (i<j)
                 {
                     table[i]=table[j];                    //较小元素向前移动
                     i++;
                 }     
                 while (i<j && table[i]<vot)               //从前向后寻找较大值
                     i++;
                 if (i<j)
                 {
                     table[j]=table[i];                    //较大元素向后移动
                     j--;
                 }     
             }
            table[i]=vot;                                  //基准值的最终位置
            System.out.print(low+".."+high+",  vot="+vot+"  ");
            print(table);
            quickSort(table, low, j-1);                    //前端子序列再排序
            quickSort(table, i+1, high);                   //后端子序列再排序
        }
    }

    public static void selectSort(int[] table)             //直接选择排序
    {
        System.out.println("直接选择排序");
        for (int i=0; i<table.length-1; i++)               //n-1趟排序
        {                                                  //每趟在从table[i]开始的子序列中寻找最小元素
            int min=i;                                     //设第i个数据元素最小
            for (int j=i+1; j<table.length; j++)           //在子序列中查找最小值
                if (table[j]<table[min])
                     min = j;                              //记住最小元素下标

            if (min!=i)                                    //将本趟最小元素交换到前边
            {
                int temp = table[i];
                table[i] = table[min];
                table[min] = temp;
            }
            System.out.print("第"+i+"趟: ");
            print(table);
        }
    }

    private static void sift(int[] table, int low, int high) //将以low为根的子树调整成最小堆
    {                                                      //low、high是序列下界和上界
        int i=low;                                         //子树的根
        int j=2*i+1;                                       //j为i结点的左孩子
        int temp=table[i];                                 //获得第i个元素的值
        while (j<=high)                                    //沿较小值孩子结点向下筛选
        {                                                  
            if (j<high && table[j]>table[j+1])             //数组元素比较（改成<为最大堆）
                j++;                                       //j为左右孩子的较小者
            if (temp>table[j])                             //若父母结点值较大（改成<为最大堆）
            {
                table[i]=table[j];                         //孩子结点中的较小值上移
                i=j;                                       //i、j向下一层
                j=2*i+1;
            }
            else
                j=high+1;                                  //退出循环
        }
        table[i]=temp;                                     //当前子树的原根值调整后的位置
        System.out.print("sift  "+low+".."+high+"  ");
        print(table);
    }

    public static void heapSort(int[] table)
    {
        System.out.println("堆排序");
        int n=table.length;
        for (int j=n/2-1; j>=0; j--)                       //创建最小堆
            sift(table, j, n-1);
//        System.out.println("最小堆？ "+isMinHeap(table));
            
        for (int j=n-1; j>0; j--)                          //每趟将最小值交换到后面，再调整成堆
        {
            int temp = table[0];
            table[0] = table[j];
            table[j] = temp;
            sift(table, 0, j-1);
        }
    }

    public static void mergeSort(int[] X)                  //归并排序
    {
        System.out.println("归并排序");
        int n=1;                                           //已排序的子序列长度，初值为1
        int[] Y = new int[X.length];                       //Y数组长度同X数组
        do
        {
            mergepass(X, Y, n);                            //一趟归并，将X数组中各子序列归并到Y中
            print(Y);
            n*=2;                                          //子序列长度加倍

            if (n<X.length)
            {
                mergepass(Y, X, n);                        //将Y数组中各子序列再归并到X中
                print(X);
                n*=2;
            }
        } while (n<X.length);
    }
    
    private static void mergepass(int[] X, int[] Y, int n) //一趟归并
    {
        System.out.print("子序列长度n="+n+"  ");
        int i=0;
        while (i<X.length-2*n+1)
        {
            merge(X,Y,i,i+n,n);
            i += 2*n;
        }
        if (i+n<X.length)
            merge(X,Y,i,i+n,n);                            //再一次归并
        else  
            for (int j=i; j<X.length; j++)                 //将X剩余元素复制到Y中
                Y[j]=X[j];
    }

    private static void merge(int[] X, int[] Y, int m, int r, int n)   //一次归并
    {
        int i=m, j=r, k=m;
        while (i<r && j<r+n && j<X.length)                 //将X中两个相邻子序列归并到Y中
            if (X[i]<X[j])                                 //较小值复制到Y中
                Y[k++]=X[i++];
            else
                Y[k++]=X[j++];

        while (i<r)                                        //将前一个子序列剩余元素复制到Y中
            Y[k++]=X[i++];
        while (j<r+n && j<X.length)                        //将后一个子序列剩余元素复制到Y中
            Y[k++]=X[j++];
    }
    
    public static void main(String[] args)
    {
//        int[] table = {52,26,97,19,66,8,49};//Array.random(9);{49,65,13,81,76,97,38,49};////{85,12,36,24,47,30,53,91,76};//;//{4,5,8,1,2,7,3,6};// {32,26,87,72,26,17};//
        int[] table = {13,27,38,49,97,76,49,81};        //最小堆
        System.out.print("关键字序列: ");
        Array.print(table);
//        Array.insertSort(table);
//        Array.shellSort(table);
//        Array.bubbleSort(table);
//        Array.quickSort(table);
//        Array.selectSort(table);
//        Array.heapSort(table);
//        Array.mergeSort(table);

        System.out.println("最小堆序列? "+Array.isMinHeap(table));
    }
    
//第9章习题
    public static boolean isMinHeap(int[] table)           //判断一个数据序列是否为最小堆
    {
        if (table==null)
            return false; 

        int i = table.length/2 -1;                         //最深一棵子树的根结点
        while (i>=0)
        {
            int j=2*i+1;                                   //左孩子
            if (j<table.length) 
                if (table[i]>table[j])
                    return false;
                else 
                    if (j+1<table.length && table[i]>table[j+1])       //右孩子
                        return false; 
            i--;
        }
        return true;
    }

}


/* 
程序运行结果如下：
关键字序列:  32 26 87 72 26 17 8 40
直接插入排序
第1趟排序:  26 32 87 72 26 17 8 40
第2趟排序:  26 32 87 72 26 17 8 40
第3趟排序:  26 32 72 87 26 17 8 40
第4趟排序:  26 26 32 72 87 17 8 40                   //排序算法稳定
第5趟排序:  17 26 26 32 72 87 8 40
第6趟排序:  8 17 26 26 32 72 87 40
第7趟排序:  8 17 26 26 32 40 72 87

关键字序列:  42 1 74 25 45 29 87 53
直接插入排序
第1趟排序:  1 42 74 25 45 29 87 53
第2趟排序:  1 42 74 25 45 29 87 53
第3趟排序:  1 25 42 74 45 29 87 53
第4趟排序:  1 25 42 45 74 29 87 53
第5趟排序:  1 25 29 42 45 74 87 53
第6趟排序:  1 25 29 42 45 74 87 53
第7趟排序:  1 25 29 42 45 53 74 87

关键字序列:  21 12 2 40 99 97 68 57
直接插入排序
第1趟排序:  12 21 2 40 99 97 68 57
第2趟排序:  2 12 21 40 99 97 68 57
第3趟排序:  2 12 21 40 99 97 68 57
第4趟排序:  2 12 21 40 99 97 68 57
第5趟排序:  2 12 21 40 97 99 68 57
第6趟排序:  2 12 21 40 68 97 99 57
第7趟排序:  2 12 21 40 57 68 97 99

关键字序列:  27 38 65 97 76 13 27 49 55 4
希尔排序
delta=5   13 27 49 55 4 27 38 65 97 76
delta=2   4 27 13 27 38 55 49 65 97 76
delta=1   4 13 27 27 38 49 55 65 76 97


关键字序列:  49 38 65 97 76 13 27 49 55 4  //严书
希尔排序
delta=5   13 27 49 55 4 49 38 65 97 76
delta=2   4 27 13 49 38 55 49 65 97 76          //与严书不同
delta=1   4 13 27 38 49 49 55 65 76 97

关键字序列:  65 34 25 87 12 38 56 46 14 77 92 23
希尔排序
delta=6   56 34 14 77 12 23 65 46 25 87 92 38
delta=3   56 12 14 65 34 23 77 46 25 87 92 38
delta=1   12 14 23 25 34 38 46 56 65 77 87 92

关键字序列:  84 12 43 62 86 7 90 91
希尔排序
delta=4   84 7 43 62 86 12 90 91
delta=2   43 7 84 12 86 62 90 91
delta=1   7 12 43 62 84 86 90 91

关键字序列:  32 26 87 72 26 17
冒泡排序
第1趟排序:  26 32 72 26 17 87
第2趟排序:  26 32 26 17 72 87
第3趟排序:  26 26 17 32 72 87
第4趟排序:  26 17 26 32 72 87
第5趟排序:  17 26 26 32 72 87

关键字序列:  1 2 3 4 5 6 7 8
冒泡排序
第1趟排序:  1 2 3 4 5 6 7 8

关键字序列:  1 3 2 4 5 8 6 7
冒泡排序
第1趟排序:  1 2 3 4 5 6 7 8
第2趟排序:  1 2 3 4 5 6 7 8

关键字序列:  4 5 8 1 2 7 3 6
冒泡排序
第1趟排序:  4 5 1 2 7 3 6 8
第2趟排序:  4 1 2 5 3 6 7 8
第3趟排序:  1 2 4 3 5 6 7 8
第4趟排序:  1 2 3 4 5 6 7 8
第5趟排序:  1 2 3 4 5 6 7 8

关键字序列:  38 26 97 19 66 1 5 49
0..7,  vot=38   5 26 1 19 38 66 97 49
0..3,  vot=5   1 5 26 19 38 66 97 49
2..3,  vot=26   1 5 19 26 38 66 97 49
5..7,  vot=66   1 5 19 26 38 49 66 97

关键字序列:  38 5 49 26 19 97 1 66
0..7,  vot=38   1 5 19 26 38 97 49 66
0..3,  vot=1   1 5 19 26 38 97 49 66
1..3,  vot=5   1 5 19 26 38 97 49 66
2..3,  vot=19   1 5 19 26 38 97 49 66
5..7,  vot=97   1 5 19 26 38 66 49 97
5..6,  vot=66   1 5 19 26 38 49 66 97


关键字序列:  49 38 65 97 76 13 27 49
0..7,  vot=49   49 38 27 13 49 76 97 65
0..3,  vot=49   13 38 27 49 49 76 97 65
0..2,  vot=13   13 38 27 49 49 76 97 65
1..2,  vot=38   13 27 38 49 49 76 97 65
5..7,  vot=76   13 27 38 49 49 65 76 97



关键字序列:  27 38 65 97 76 13 27 49 55 4
low=0  high=9  vot=27   4 27 13 27 76 97 65 49 55 38
low=0  high=2  vot=4   4 27 13 27 76 97 65 49 55 38
low=1  high=2  vot=27   4 13 27 27 76 97 65 49 55 38
low=4  high=9  vot=76   4 13 27 27 38 55 65 49 76 97
low=4  high=7  vot=38   4 13 27 27 38 55 65 49 76 97
low=5  high=7  vot=55   4 13 27 27 38 49 55 65 76 97

关键字序列:  38 26 97 19 66 1 5 49
直接选择排序
第0趟排序:  1 26 97 19 66 38 5 49
第1趟排序:  1 5 97 19 66 38 26 49
第2趟排序:  1 5 19 97 66 38 26 49
第3趟排序:  1 5 19 26 66 38 97 49
第4趟排序:  1 5 19 26 38 66 97 49
第5趟排序:  1 5 19 26 38 49 97 66
第6趟排序:  1 5 19 26 38 49 66 97    

最小堆
关键字序列:  81 49 76 27 97 38 49 13 65
sift  3..8   81 49 76 13 97 38 49 27 65
sift  2..8   81 49 38 13 97 76 49 27 65
sift  1..8   81 13 38 27 97 76 49 49 65
sift  0..8   13 27 38 49 97 76 49 81 65
 13 27 38 49 97 76 49 81 65
sift  0..7   27 49 38 65 97 76 49 81 13
sift  0..6   38 49 49 65 97 76 81 27 13
sift  0..5   49 65 49 81 97 76 38 27 13
sift  0..4   49 65 76 81 97 49 38 27 13
sift  0..3   65 81 76 97 49 49 38 27 13
sift  0..2   76 81 97 65 49 49 38 27 13
sift  0..1   81 97 76 65 49 49 38 27 13
sift  0..0   97 81 76 65 49 49 38 27 13

最大堆
关键字序列:  49 65 13 81 76 27 97 38 49
sift  3..8   49 65 13 81 76 27 97 38 49
sift  2..8   49 65 97 81 76 27 13 38 49
sift  1..8   49 81 97 65 76 27 13 38 49
sift  0..8   97 81 49 65 76 27 13 38 49
 97 81 49 65 76 27 13 38 49
sift  0..7   81 76 49 65 49 27 13 38 97
sift  0..6   76 65 49 38 49 27 13 81 97
sift  0..5   65 49 49 38 13 27 76 81 97
sift  0..4   49 38 49 27 13 65 76 81 97
sift  0..3   49 38 13 27 49 65 76 81 97
sift  0..2   38 27 13 49 49 65 76 81 97
sift  0..1   27 13 38 49 49 65 76 81 97
sift  0..0   13 27 38 49 49 65 76 81 97

关键字序列:  52 26 97 19 66 8 49
归并排序
子序列长度n=1   26 52 19 97 8 66 49
子序列长度n=2   19 26 52 97 8 49 66
子序列长度n=4   8 19 26 49 52 66 97

关键字序列:  13 27 38 49 97 76 49 81 65
最小堆序列? true

*/
