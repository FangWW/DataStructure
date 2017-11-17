//����5.1��  �����ࡣ

public class Matrix
{
    private int value[][];                       //�洢����Ԫ�صĶ�ά����

    public Matrix(int m, int n)                  //����m��n�еĿվ���
    {
        this.value=new int[m][n]; 
    }
    public Matrix(int n)                         //����n��n�еĿվ���
    {
        this(n,n); 
    }
    public Matrix()
    {
        this(10,10);
    }
    public Matrix(int mat[][])                   //�������������mat�ṩ����Ԫ��
    {
        this(mat.length,mat[0].length);
        for (int i=0; i<mat.length; i++)
            for (int j=0; j<mat[i].length; j++)
               this.value[i][j] = mat[i][j];
    }

    public int get(int i, int j)                 //��þ����i�е�j�е�Ԫ�أ�O(1)
    {
        return value[i][j];
    }
    public void set(int i, int j, int k)         //���þ����i�е�j�е�Ԫ�أ�O(1)
    {
        value[i][j]=k;
    }

    public String toString()                     //��������������ʾ���ȫ��Ԫ��
    {
        String str="";
        for (int i=0; i<value.length; i++)
        {
            for (int j=0; j<value[i].length; j++)
                str += "  "+value[i][j];
            str += "\n";
        }
        return str;
    }

    public void add(Matrix b)                    //this��b����������ӣ��ı䵱ǰ����
    {
        for (int i=0; i<this.value.length; i++)
            for (int j=0; j<this.value[i].length; j++)
               this.value[i][j] += b.value[i][j];
    }

    public Matrix transpose()                    //����ת�þ���
    {
        Matrix trans = new Matrix(value[0].length, value.length);
        for (int i=0; i<this.value.length; i++)
            for (int j=0; j<this.value[i].length; j++)
               trans.value[j][i]=this.value[i][j]; 
        return trans;
    }

}

class Matrix_ex
{
    public static void main(String args[])
    {
        int m1[][]={{1,2,3},
                    {4,5,6}};
        Matrix a=new Matrix(m1); 
        int m2[][]={{1,0,0},
                    {0,1,0}};
        Matrix b=new Matrix(m2);
        System.out.print("Matrix a:\n"+a.toString());
        System.out.print("Matrix b:\n"+b.toString());
        a.add(b);
        System.out.print("Matrix a:\n"+a.toString());
        System.out.println("a��ת�þ���\n"+a.transpose().toString());
    }
}

/*
Matrix a:
  1  2  3
  4  5  6
Matrix b:
  1  0  0
  0  1  0
Matrix a:
  2  2  3
  4  6  6
a��ת�þ���
  2  4
  2  6
  3  6

*/