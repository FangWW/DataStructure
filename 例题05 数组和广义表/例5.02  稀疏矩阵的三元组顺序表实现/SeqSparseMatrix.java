//ϡ�������Ԫ��˳�����

import dataStructure.linearList.SeqList;         //˳���

public class SeqSparseMatrix
{
    private int rowCount, columnCount;           //����������
    private SeqList<Element> list;               //��Ԫ��˳���

    public SeqSparseMatrix(int rowCount, int columnCount, int count)
    {                                            //ָ��ϡ����������������������Ԫ�ظ���
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.list = new SeqList<Element>(count);
    }

    public SeqSparseMatrix()
    {
        this(0, 0, 10);
    }

    public SeqSparseMatrix(int rowCount, int columnCount, Element[] item)
    {                                            //ָ��ϡ��������������������Ԫ������
        this(rowCount, columnCount, item.length);
        for (int i=0; i<item.length; i++)
            this.add(item[i]);
    }

    public SeqSparseMatrix(Element[] item)
    {
        this(0, 0, item);
    }

    public boolean add(Element item)             //�����������һ��Ԫ�ص���Ԫ��
    {                                            //
        if (this.rowCount<=item.row)
            this.rowCount = item.row+1;
        if (this.columnCount<=item.column)
            this.columnCount = item.column+1;
        
        int i=0;
        Element temp = this.list.get(i);
        while (i<this.list.length() && item.compareTo(temp)>0) //item����ʱ�����
        {
            i++;
            temp = this.list.get(i);
        }
        return this.list.add(i, item);
    }

    public String toString()                     //ϡ���������
    {
        String str = "ϡ����� "+rowCount+"��,"+columnCount+"��, "+this.list.length()+"������Ԫ��\n";
        str += "��Ԫ�����Ա�"+ this.list.toString()+"\n";

        str += "ϡ�����\n";
        int k=0;
        Element temp = this.list.get(k++);
        for (int i=0; i<this.rowCount; i++)
        {
            for (int j=0; j<this.columnCount; j++)
               if (temp!=null && i==temp.row && j==temp.column)
               {
                   str += temp.value+" ";
                   temp = this.list.get(k++);
               }               
               else
                   str += 0+"  ";
            str += "\n";
        }   
        return str;
    }

    public SeqSparseMatrix transpose()                     //����ת�þ���
    {
        SeqSparseMatrix trans = new SeqSparseMatrix(columnCount, rowCount, this.list.length());
        for (int i=0; i<this.list.length(); i++)
            trans.add(this.list.get(i).toSymmetry());      //�������Գ�λ��Ԫ�ص���Ԫ��
        return trans;
    }

    public SeqSparseMatrix add(SeqSparseMatrix matb)       //����this��matb��ӵľ��󣬹鲢�㷨
    {                                                      //���ı䵱ǰ����
        SeqSparseMatrix matc = new SeqSparseMatrix(rowCount, columnCount, this.list.length());
        int i=0, j=0;
        Element tempa = this.list.get(i++);
        Element tempb = matb.list.get(j++);
        while (tempa!=null && tempb!=null)
        {
            if (tempa.compareTo(tempb)==0)//tempa.row==tempb.row && tempa.column==tempb.column)
            {
                matc.list.add(new Element(tempa.row,tempa.column,tempa.value+tempb.value));
                tempa = this.list.get(i++);
                tempb = matb.list.get(j++);
            }
            else
                if (tempa.compareTo(tempb)<0)//tempa.row<tempb.row || tempa.row==tempb.row && tempa.column<tempb.column)
                {
                    matc.list.add(new Element(tempa));
                    tempa = this.list.get(i++);
                }
                else
                {
                    matc.list.add(new Element(tempb));
                    tempb = matb.list.get(j++);
                }
        }

        while (tempa!=null)                      //a�ж�����ʱ��ȫ���Ƹ�c
        {
             matc.list.add(new Element(tempa));
             tempa = this.list.get(i++);
        }
        while (tempb!=null)                      //b�ж�����ʱ��ȫ���Ƹ�c
        {
             matc.list.add(new Element(tempb));
             tempb = matb.list.get(j++);
        }
        return matc;
    }

    public static void main(String args[])
    {
        Element[] item = {new Element(0,2,11),new Element(0,4,17),new Element(1,1,20),new Element(3,0,19),new Element(3,5,28),new Element(4,4,50)};
        SeqSparseMatrix smata = new SeqSparseMatrix(item);
        System.out.println("A "+smata.toString());
        System.out.println("����Aת�� "+smata.transpose().toString());

        SeqSparseMatrix smatb = new SeqSparseMatrix(5,6,3);
        smatb.add(new Element(0,2,10));
        smatb.add(new Element(1,4,9));
        smatb.add(new Element(4,5,20));
        System.out.println("B "+smatb.toString());
        System.out.println("����A+BΪ "+smata.add(smatb).toString());
    }
}

/*
A ϡ����� 5��,6��, 6������Ԫ
��Ԫ�����Ա�[(0,2,11), (0,4,17), (1,1,20), (3,0,19), (3,5,28), (4,4,50)]
ϡ�����
0  0  11 0  17 0  
0  20 0  0  0  0  
0  0  0  0  0  0  
19 0  0  0  0  28 
0  0  0  0  50 0  

����Aת�� ϡ����� 6��,5��, 6������Ԫ
��Ԫ�����Ա�((0,3,19), (1,1,20), (2,0,11), (4,0,17), (4,4,50), (5,3,28))
ϡ�����
0  0  0  19 0  
0  20 0  0  0  
11 0  0  0  0  
0  0  0  0  0  
17 0  0  0  50 
0  0  0  28 0  

B ϡ����� 5��,6��, 3������Ԫ
��Ԫ�����Ա�((0,2,10), (1,4,9), (4,5,20))
ϡ�����
0  0  10 0  0  0  
0  0  0  0  9 0  
0  0  0  0  0  0  
0  0  0  0  0  0  
0  0  0  0  0  20 

����A+BΪ ϡ����� 5��,6��, 8������Ԫ
��Ԫ�����Ա�((0,2,21), (0,4,17), (1,1,20), (1,4,9), (3,0,19), (3,5,28), (4,4,50), (4,5,20))
ϡ�����
0  0  21 0  17 0  
0  20 0  0  9 0  
0  0  0  0  0  0  
19 0  0  0  0  28 
0  0  0  0  50 20 



??
    public int get(int i,int j)                  //��þ����i�е�j�е�Ԫ�أ�O(1)
    {
        return value[i][j];
    }
    public void set(int i,int j,int k)           //���þ����i�е�j�е�Ԫ�أ�O(1)
    {
        value[i][j]=k;
    }



*/