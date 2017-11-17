 //����1.4��  �����������˳����ҡ�

package dataStructure;                                     //������ǰ�ļ��е����ӿ���ָ�������Ӱ���
public class SortedArray
{
    public static void print(int[] table)                  //�������Ԫ��
    {
        if (table!=null)
            for (int i=0; i<table.length; i++)
                System.out.print(" "+table[i]);
        System.out.println();
    }

    public static boolean isSorted(int[] table)            //�ж����������Ƿ��Ѱ���������
    {                                                      //�������򷵻�true�����򷵻�false
        if (table==null)
            return false;

        for (int i=0; i<table.length-1; i++)
            if (table[i]>table[i+1])
                return false;
        return true;
    }

    public static boolean isSorted(Comparable[] table)     //�ж϶��������Ƿ��Ѱ���������
    {                                                      //�������򷵻�true�����򷵻�false
        if (table==null)
            return false;

        for (int i=0; i<table.length-1; i++)
            if (table[i].compareTo(table[i+1])>0)
                return false;
        return true;
    }

    public static int indexOf(int[] table, int value)      //˳��������������򣩵���������
    {                                                      //�����ҳɹ�����Ԫ���±꣬���򷵻�-1
        if (table!=null && isSorted(table))
            for(int i=0; i<table.length && table[i]<=value; i++)
            {
                System.out.print(table[i]+"? ");
                if (table[i]==value)
                    return i;
            }
        return -1;
    }  

    public static int indexOf(Comparable[] table, Comparable cobj)    //˳��������������򣩶�������
    {                                                      //�����ҳɹ�����Ԫ���±꣬���򷵻�-1
        if (table!=null && cobj!=null && isSorted(table))
            for(int i=0; i<table.length && cobj.compareTo(table[i])>=0; i++)
            {
                System.out.print(table[i]+"? ");
                if (cobj.compareTo(table[i])==0)           //Ҳ��cobj.equals(table[i])
                    return i;
            }
        return -1;
    }  
}
/*    
    public static void main(String[] args)
    {
        int[] table ={52,26,97,19,66,8,49};                //����
        System.out.print("����Ĺؼ�������: ");
        print(table);
        System.out.println("������? "+isSorted(table));

        int[] table_sorted ={8,17,26,32,40,72,87,99};      //�Ѱ���������
        System.out.print("\n�Ѱ���������Ĺؼ�������: ");
        print(table_sorted);
        System.out.println("������? "+isSorted(table_sorted));
        int key=25;
        System.out.println("˳����� "+key+", "+((indexOf(table_sorted, key)==-1)?"��":"")+"�ɹ�");
    }
}
*/

/* 
�������н�����£�
����Ĺؼ�������:  52 26 97 19 66 8 49
������? false

�Ѱ���������Ĺؼ�������:  8 17 26 32 40 72 87 99
������? true
8? 17? ˳����� 25, ���ɹ�

*/
