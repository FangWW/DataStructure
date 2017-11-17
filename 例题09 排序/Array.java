//��9��   ����

public class Array
{
    public static int[] random(int n)                      //����n���������������������
    {
        if (n>0)
        {
            int table[] = new int[n];
            for (int i=0; i<table.length; i++)
                table[i] = (int)(Math.random()*100);       //����һ��0��100֮��������
            return table;                                  //����һ������
        }
        return null;
    }

    public static void print(int[] table)                  //�������Ԫ��
    {
        if (table!=null)
            for (int i=0; i<table.length; i++)
                System.out.print(" "+table[i]);
        System.out.println();
    }

    public static void insertSort(int[] table)             //ֱ�Ӳ�������
    {                                                      //�������������ͣ�Ԫ��ֵ�����ı�
        System.out.println("ֱ�Ӳ�������");
        for (int i=1; i<table.length; i++)                  //n-1��ɨ��
        {
            int temp=table[i], j;                          //ÿ�˽�table[i]���뵽ǰ���������������
//          System.out.print("�ƶ�");
            for (j=i-1; j>-1 && temp<table[j]; j--)        //��ǰ��ϴ�Ԫ������ƶ�
            {
//                System.out.print(table[j]+", ");
                table[j+1] = table[j];
            }
            table[j+1] = temp;                             //tempֵ�������λ��
            System.out.print("��"+i+"��: ");
            print(table);
        }
    }

    public static void shellSort(int[] table)              //ϣ������
    {
        System.out.println("ϣ������");
        for (int delta=table.length/2; delta>0; delta/=2)  //�����������������룬������ɨ��
        {
            for (int i=delta; i<table.length; i++)         //һ���������飬ÿ��Ԫ�����Լ��������ڽ���ֱ�Ӳ�������
            {
                int temp = table[i];                       //��ǰ������Ԫ��
                int j=i-delta;                             //���deltaԶ
                while (j>=0 && temp<table[j])              //һ����ǰ��ϴ��Ԫ������ƶ�
                {
                    table[j+delta] = table[j];
                    j-=delta;                              //������ǰ���Ԫ�رȽ�
                } 
                table[j+delta] = temp;                     //����Ԫ��λ��
            }
            System.out.print("delta="+delta+"  ");
            print(table);
        }
    }

    private static void swap(int[] table, int i, int j)     //�����������±�Ϊi��j��Ԫ��
    { 
        if (i>=0 && i<table.length && j>=0 && j<table.length && i!=j)   //�ж�i��j�Ƿ�Խ��
        {
            int temp = table[j];
            table[j] = table[i];
            table[i] = temp;
        }
    }

    public static void bubbleSort(int[] table)             //ð������
    {
        System.out.println("ð������");
        boolean exchange=true;                             //�Ƿ񽻻��ı��
        for (int i=1; i<table.length && exchange; i++)     //�н���ʱ�ٽ�����һ�ˣ����n-1��
        {
            exchange=false;                                //�ٶ�Ԫ��δ���� 
            for (int j=0; j<table.length-i; j++)           //һ�αȽϡ�����
                if (table[j]>table[j+1])                   //����ʱ������
                {
                    int temp = table[j];
                    table[j] = table[j+1];
                    table[j+1] = temp;
                    exchange=true;                         //�н��� 
                }
            System.out.print("��"+i+"��: ");
            print(table);
        }
    }

    public static void quickSort(int[] table)              //��������
    {
        quickSort(table, 0, table.length-1);
    }

    private static void quickSort(int[] table, int low, int high) //һ�˿������򣬵ݹ��㷨
    {                                                      //low��highָ�����е��½���Ͻ�
        if (low<high)                                      //������Ч
        {
             int i=low, j=high;
             int vot=table[i];                             //��һ��ֵ��Ϊ��׼ֵ
             while (i!=j)                                  //һ������
             {
                 while (i<j && vot<=table[j])              //�Ӻ���ǰѰ�ҽ�Сֵ
                     j--;
                 if (i<j)
                 {
                     table[i]=table[j];                    //��СԪ����ǰ�ƶ�
                     i++;
                 }     
                 while (i<j && table[i]<vot)               //��ǰ���Ѱ�ҽϴ�ֵ
                     i++;
                 if (i<j)
                 {
                     table[j]=table[i];                    //�ϴ�Ԫ������ƶ�
                     j--;
                 }     
             }
            table[i]=vot;                                  //��׼ֵ������λ��
            System.out.print(low+".."+high+",  vot="+vot+"  ");
            print(table);
            quickSort(table, low, j-1);                    //ǰ��������������
            quickSort(table, i+1, high);                   //���������������
        }
    }

    public static void selectSort(int[] table)             //ֱ��ѡ������
    {
        System.out.println("ֱ��ѡ������");
        for (int i=0; i<table.length-1; i++)               //n-1������
        {                                                  //ÿ���ڴ�table[i]��ʼ����������Ѱ����СԪ��
            int min=i;                                     //���i������Ԫ����С
            for (int j=i+1; j<table.length; j++)           //���������в�����Сֵ
                if (table[j]<table[min])
                     min = j;                              //��ס��СԪ���±�

            if (min!=i)                                    //��������СԪ�ؽ�����ǰ��
            {
                int temp = table[i];
                table[i] = table[min];
                table[min] = temp;
            }
            System.out.print("��"+i+"��: ");
            print(table);
        }
    }

    private static void sift(int[] table, int low, int high) //����lowΪ����������������С��
    {                                                      //low��high�������½���Ͻ�
        int i=low;                                         //�����ĸ�
        int j=2*i+1;                                       //jΪi��������
        int temp=table[i];                                 //��õ�i��Ԫ�ص�ֵ
        while (j<=high)                                    //�ؽ�Сֵ���ӽ������ɸѡ
        {                                                  
            if (j<high && table[j]>table[j+1])             //����Ԫ�رȽϣ��ĳ�<Ϊ���ѣ�
                j++;                                       //jΪ���Һ��ӵĽ�С��
            if (temp>table[j])                             //����ĸ���ֵ�ϴ󣨸ĳ�<Ϊ���ѣ�
            {
                table[i]=table[j];                         //���ӽ���еĽ�Сֵ����
                i=j;                                       //i��j����һ��
                j=2*i+1;
            }
            else
                j=high+1;                                  //�˳�ѭ��
        }
        table[i]=temp;                                     //��ǰ������ԭ��ֵ�������λ��
        System.out.print("sift  "+low+".."+high+"  ");
        print(table);
    }

    public static void heapSort(int[] table)
    {
        System.out.println("������");
        int n=table.length;
        for (int j=n/2-1; j>=0; j--)                       //������С��
            sift(table, j, n-1);
//        System.out.println("��С�ѣ� "+isMinHeap(table));
            
        for (int j=n-1; j>0; j--)                          //ÿ�˽���Сֵ���������棬�ٵ����ɶ�
        {
            int temp = table[0];
            table[0] = table[j];
            table[j] = temp;
            sift(table, 0, j-1);
        }
    }

    public static void mergeSort(int[] X)                  //�鲢����
    {
        System.out.println("�鲢����");
        int n=1;                                           //������������г��ȣ���ֵΪ1
        int[] Y = new int[X.length];                       //Y���鳤��ͬX����
        do
        {
            mergepass(X, Y, n);                            //һ�˹鲢����X�����и������й鲢��Y��
            print(Y);
            n*=2;                                          //�����г��ȼӱ�

            if (n<X.length)
            {
                mergepass(Y, X, n);                        //��Y�����и��������ٹ鲢��X��
                print(X);
                n*=2;
            }
        } while (n<X.length);
    }
    
    private static void mergepass(int[] X, int[] Y, int n) //һ�˹鲢
    {
        System.out.print("�����г���n="+n+"  ");
        int i=0;
        while (i<X.length-2*n+1)
        {
            merge(X,Y,i,i+n,n);
            i += 2*n;
        }
        if (i+n<X.length)
            merge(X,Y,i,i+n,n);                            //��һ�ι鲢
        else  
            for (int j=i; j<X.length; j++)                 //��Xʣ��Ԫ�ظ��Ƶ�Y��
                Y[j]=X[j];
    }

    private static void merge(int[] X, int[] Y, int m, int r, int n)   //һ�ι鲢
    {
        int i=m, j=r, k=m;
        while (i<r && j<r+n && j<X.length)                 //��X���������������й鲢��Y��
            if (X[i]<X[j])                                 //��Сֵ���Ƶ�Y��
                Y[k++]=X[i++];
            else
                Y[k++]=X[j++];

        while (i<r)                                        //��ǰһ��������ʣ��Ԫ�ظ��Ƶ�Y��
            Y[k++]=X[i++];
        while (j<r+n && j<X.length)                        //����һ��������ʣ��Ԫ�ظ��Ƶ�Y��
            Y[k++]=X[j++];
    }
    
    public static void main(String[] args)
    {
//        int[] table = {52,26,97,19,66,8,49};//Array.random(9);{49,65,13,81,76,97,38,49};////{85,12,36,24,47,30,53,91,76};//;//{4,5,8,1,2,7,3,6};// {32,26,87,72,26,17};//
        int[] table = {13,27,38,49,97,76,49,81};        //��С��
        System.out.print("�ؼ�������: ");
        Array.print(table);
//        Array.insertSort(table);
//        Array.shellSort(table);
//        Array.bubbleSort(table);
//        Array.quickSort(table);
//        Array.selectSort(table);
//        Array.heapSort(table);
//        Array.mergeSort(table);

        System.out.println("��С������? "+Array.isMinHeap(table));
    }
    
//��9��ϰ��
    public static boolean isMinHeap(int[] table)           //�ж�һ�����������Ƿ�Ϊ��С��
    {
        if (table==null)
            return false; 

        int i = table.length/2 -1;                         //����һ�������ĸ����
        while (i>=0)
        {
            int j=2*i+1;                                   //����
            if (j<table.length) 
                if (table[i]>table[j])
                    return false;
                else 
                    if (j+1<table.length && table[i]>table[j+1])       //�Һ���
                        return false; 
            i--;
        }
        return true;
    }

}


/* 
�������н�����£�
�ؼ�������:  32 26 87 72 26 17 8 40
ֱ�Ӳ�������
��1������:  26 32 87 72 26 17 8 40
��2������:  26 32 87 72 26 17 8 40
��3������:  26 32 72 87 26 17 8 40
��4������:  26 26 32 72 87 17 8 40                   //�����㷨�ȶ�
��5������:  17 26 26 32 72 87 8 40
��6������:  8 17 26 26 32 72 87 40
��7������:  8 17 26 26 32 40 72 87

�ؼ�������:  42 1 74 25 45 29 87 53
ֱ�Ӳ�������
��1������:  1 42 74 25 45 29 87 53
��2������:  1 42 74 25 45 29 87 53
��3������:  1 25 42 74 45 29 87 53
��4������:  1 25 42 45 74 29 87 53
��5������:  1 25 29 42 45 74 87 53
��6������:  1 25 29 42 45 74 87 53
��7������:  1 25 29 42 45 53 74 87

�ؼ�������:  21 12 2 40 99 97 68 57
ֱ�Ӳ�������
��1������:  12 21 2 40 99 97 68 57
��2������:  2 12 21 40 99 97 68 57
��3������:  2 12 21 40 99 97 68 57
��4������:  2 12 21 40 99 97 68 57
��5������:  2 12 21 40 97 99 68 57
��6������:  2 12 21 40 68 97 99 57
��7������:  2 12 21 40 57 68 97 99

�ؼ�������:  27 38 65 97 76 13 27 49 55 4
ϣ������
delta=5   13 27 49 55 4 27 38 65 97 76
delta=2   4 27 13 27 38 55 49 65 97 76
delta=1   4 13 27 27 38 49 55 65 76 97


�ؼ�������:  49 38 65 97 76 13 27 49 55 4  //����
ϣ������
delta=5   13 27 49 55 4 49 38 65 97 76
delta=2   4 27 13 49 38 55 49 65 97 76          //�����鲻ͬ
delta=1   4 13 27 38 49 49 55 65 76 97

�ؼ�������:  65 34 25 87 12 38 56 46 14 77 92 23
ϣ������
delta=6   56 34 14 77 12 23 65 46 25 87 92 38
delta=3   56 12 14 65 34 23 77 46 25 87 92 38
delta=1   12 14 23 25 34 38 46 56 65 77 87 92

�ؼ�������:  84 12 43 62 86 7 90 91
ϣ������
delta=4   84 7 43 62 86 12 90 91
delta=2   43 7 84 12 86 62 90 91
delta=1   7 12 43 62 84 86 90 91

�ؼ�������:  32 26 87 72 26 17
ð������
��1������:  26 32 72 26 17 87
��2������:  26 32 26 17 72 87
��3������:  26 26 17 32 72 87
��4������:  26 17 26 32 72 87
��5������:  17 26 26 32 72 87

�ؼ�������:  1 2 3 4 5 6 7 8
ð������
��1������:  1 2 3 4 5 6 7 8

�ؼ�������:  1 3 2 4 5 8 6 7
ð������
��1������:  1 2 3 4 5 6 7 8
��2������:  1 2 3 4 5 6 7 8

�ؼ�������:  4 5 8 1 2 7 3 6
ð������
��1������:  4 5 1 2 7 3 6 8
��2������:  4 1 2 5 3 6 7 8
��3������:  1 2 4 3 5 6 7 8
��4������:  1 2 3 4 5 6 7 8
��5������:  1 2 3 4 5 6 7 8

�ؼ�������:  38 26 97 19 66 1 5 49
0..7,  vot=38   5 26 1 19 38 66 97 49
0..3,  vot=5   1 5 26 19 38 66 97 49
2..3,  vot=26   1 5 19 26 38 66 97 49
5..7,  vot=66   1 5 19 26 38 49 66 97

�ؼ�������:  38 5 49 26 19 97 1 66
0..7,  vot=38   1 5 19 26 38 97 49 66
0..3,  vot=1   1 5 19 26 38 97 49 66
1..3,  vot=5   1 5 19 26 38 97 49 66
2..3,  vot=19   1 5 19 26 38 97 49 66
5..7,  vot=97   1 5 19 26 38 66 49 97
5..6,  vot=66   1 5 19 26 38 49 66 97


�ؼ�������:  49 38 65 97 76 13 27 49
0..7,  vot=49   49 38 27 13 49 76 97 65
0..3,  vot=49   13 38 27 49 49 76 97 65
0..2,  vot=13   13 38 27 49 49 76 97 65
1..2,  vot=38   13 27 38 49 49 76 97 65
5..7,  vot=76   13 27 38 49 49 65 76 97



�ؼ�������:  27 38 65 97 76 13 27 49 55 4
low=0  high=9  vot=27   4 27 13 27 76 97 65 49 55 38
low=0  high=2  vot=4   4 27 13 27 76 97 65 49 55 38
low=1  high=2  vot=27   4 13 27 27 76 97 65 49 55 38
low=4  high=9  vot=76   4 13 27 27 38 55 65 49 76 97
low=4  high=7  vot=38   4 13 27 27 38 55 65 49 76 97
low=5  high=7  vot=55   4 13 27 27 38 49 55 65 76 97

�ؼ�������:  38 26 97 19 66 1 5 49
ֱ��ѡ������
��0������:  1 26 97 19 66 38 5 49
��1������:  1 5 97 19 66 38 26 49
��2������:  1 5 19 97 66 38 26 49
��3������:  1 5 19 26 66 38 97 49
��4������:  1 5 19 26 38 66 97 49
��5������:  1 5 19 26 38 49 97 66
��6������:  1 5 19 26 38 49 66 97    

��С��
�ؼ�������:  81 49 76 27 97 38 49 13 65
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

����
�ؼ�������:  49 65 13 81 76 27 97 38 49
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

�ؼ�������:  52 26 97 19 66 8 49
�鲢����
�����г���n=1   26 52 19 97 8 66 49
�����г���n=2   19 26 52 97 8 49 66
�����г���n=4   8 19 26 49 52 66 97

�ؼ�������:  13 27 38 49 97 76 49 81 65
��С������? true

*/
