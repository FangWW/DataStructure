//����1.5��  ��n����

public class Factorial
{
    public static int fact(int n)                //��׳ˣ��ݹ鷽��
    {
        if (n>=0)
        {
            if (n==0 || n==1)
                return 1;
            else
            {
//                System.out.println(n+"!="+n+"*"+(n-1)+"!");
                return n*fact(n-1);
            }
        }
        return -1;                               //n<0ʱn!�޶���
    }

    public static void main(String args[])
    {
        int i=5;
        if (args.length>0)
            i=Integer.parseInt(args[0]);         //��args[0]ת��Ϊintֵ
        System.out.println(i+"!="+fact(i));
    }
}
/*
�������н�����£�
5!=5*4!
4!=4*3!
3!=3*2!
2!=2*1!
5!=120
*/