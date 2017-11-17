//8.2.2   ��������˳�����۰����

public class BSArray
{
    public static void print(int[] table)                  //�������Ԫ��
    {
        if (table!=null)
            for (int i=0; i<table.length; i++)
                System.out.print(" "+table[i]);
        System.out.println();
    }
/*
    public static int binarySearch(int[] table, int value) //�۰�����㷨������Ԫ���Ѱ���������
    {                                                      //�����ҳɹ�����Ԫ���±꣬���򷵻�-1
        if (table!=null)
        {
            int low=0, high=table.length-1;                //���ҷ�Χ���½���Ͻ�
            while (low<=high)                              //�߽���Ч
            {
                int mid = (low+high)/2;                    //�м�λ�ã���ǰ�Ƚ�Ԫ��λ��
                System.out.print(table[mid]+"? ");
                if (table[mid]==value) 
                    return mid;                            //���ҳɹ�
                else
                    if (table[mid]>value)                  //����ֵС
                        high = mid-1;                      //���ҷ�Χ��С��ǰ���
                    else
                        low = mid+1;                       //���ҷ�Χ��С������
            }
        }
        return -1;                                         //���Ҳ��ɹ�
    }  
*/

    public static int binarySearch(Comparable[] table, Comparable cobj)   //�۰�����㷨������Ԫ���Ѱ���������
    {                                  //��table�����в���cobj�������ҳɹ�����Ԫ���±꣬���򷵻�-1
        return binarySearch(table, cobj, 0, table.length-1);
    }  

    public static int binarySearch(Comparable[] table, Comparable cobj, int low, int high)  //�۰�����㷨������Ԫ���Ѱ���������
    {                                                      //low��highָ�����ҷ�Χ���½���Ͻ�
        if (table!=null && cobj!=null)
        {
            while (low<=high)                              //�߽���Ч
            {
                int mid = (low+high)/2;                    //�м�λ�ã���ǰ�Ƚ�Ԫ��λ��
                System.out.print(table[mid]+"? ");
                if (cobj.compareTo(table[mid])==0)         //����Ƚϴ�С
                    return mid;                            //���ҳɹ�
                else
                    if (cobj.compareTo(table[mid])<0)      //��������С
                        high = mid-1;                      //���ҷ�Χ��С��ǰ���
                    else
                        low = mid+1;                       //���ҷ�Χ��С������
            }
        }
        return -1;                                         //���Ҳ��ɹ�
    }  

    
    public static void main(String[] args)
    {
        int[] table ={8,17,26,32,40,72,87,99};             //�Ѱ���������
        System.out.print("\n�Ѱ���������Ĺؼ�������: ");
        print(table);
        int key=99;
        System.out.println("�۰���� "+key+", "+((binarySearch(table,key)==-1)?"��":"")+"�ɹ�");
        key=25;
        System.out.println("�۰���� "+key+", "+((binarySearch(table,key)==-1)?"��":"")+"�ɹ�");
    }
    
//��8��ϰ��
//�ݹ��㷨
    public static int binarySearch(int[] table, int value) //�۰�����㷨������Ԫ���Ѱ���������
    {                                                      //�����ҳɹ�����Ԫ���±꣬���򷵻�-1
        if (table!=null)
            return binarySearch(table, value, 0, table.length-1);
        return -1;
    }  
    private static int binarySearch(int[] table, int value, int low, int high)  //�۰�����㷨������Ԫ���Ѱ���������
    {                                                      //low��highָ�����ҷ�Χ���½���Ͻ�
        if (low<=high)                                     //�߽���Ч
        {
            int mid = (low+high)/2;                        //�м�λ�ã���ǰ�Ƚ�Ԫ��λ��
            System.out.print(table[mid]+"? ");
            if (table[mid]==value) 
                return mid;                                //���ҳɹ�
            else
                if (table[mid]>value)                      //����ֵС
                    return binarySearch(table, value, low, mid-1);   //���ҷ�Χ��С��ǰ���
                else
                    return binarySearch(table, value, mid+1, high);  //���ҷ�Χ��С������
        }
        return -1;
    }
}


/* 
�������н�����£�
�Ѱ���������Ĺؼ�������:  8 17 26 32 40 72 87 99 
32? 72? 87? 99? �۰���� 99, �ɹ� 
32? 17? 26? �۰���� 25, ���ɹ� 

*/
