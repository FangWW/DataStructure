//�����������������ۡ�

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

    public static void swap(int[] table, int i, int j)     //�����������±�Ϊi��j��Ԫ��
    { 
        if(table!=null && i>=0 && i<table.length && j>=0 && j<table.length && i!=j)   //�ж�i��j�Ƿ�Խ��
        {
            int temp = table[j];
            table[j] = table[i];
            table[i] = temp;
        }
    }

    public static void swap(Object[] table, int i, int j)     //�����������±�Ϊi��j��Ԫ��
    { 
        if(table!=null && i>=0 && i<table.length && j>=0 && j<table.length && i!=j)   //�ж�i��j�Ƿ�Խ��
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
�������н�����£�
a=1; b=2

*/
