//����1.3��  �����˳����ҡ�
public class ArraySeqSearch
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

    public static int indexOf(int[] table, int value)      //�����������в��Ҹ���ֵ
    {                                                      //�����ҳɹ�����Ԫ���±꣬���򷵻�-1
        if (table!=null)
            for(int i=0; i<table.length; i++)
            {
                System.out.print(table[i]+"? ");
                if (table[i]==value)                       //�����������Ͳ���==��!=��������бȽ�
                    return i;
            }
        return -1;
    }  

    public static int indexOf(Object[] table, Object obj)  //�ڶ��������в��Ҹ���ֵ
    {                                                      //�����ҳɹ�����Ԫ���±꣬���򷵻�-1
        if (table!=null && obj!=null)
            for(int i=0; i<table.length; i++)
                if (obj.equals(table[i]))                  //�������equals()�����Ƚ��Ƿ����
                    return i;
        return -1;
    }
    
    public static void main(String[] args)
    {
        int[] table = random(10);                          //����
        System.out.print("����Ĺؼ�������: ");
        print(table);
        int key=19;
        System.out.println("˳����� "+key+", "+((indexOf(table,key)==-1)?"��":"")+"�ɹ�");
        key=25;
        System.out.println("˳����� "+key+", "+((indexOf(table,key)==-1)?"��":"")+"�ɹ�");
    }
}

/* 
�������н�����£�
����Ĺؼ�������:  52 26 97 19 66 8 49
52? 26? 97? 19? ˳����� 19, �ɹ�
52? 26? 97? 19? 66? 8? 49? ˳����� 25, ���ɹ�

*/
