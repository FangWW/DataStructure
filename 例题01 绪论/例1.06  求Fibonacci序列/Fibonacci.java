//����1.6��  �õݹ��㷨����Fibonacci���С�

public class Fibonacci
{
    public static int fib(int n)                 //�ݹ鷽��
    {
        if (n==0 || n==1)
            return n;
        else
            return fib(n-2)+fib(n-1);
    }

    public static void main(String args[])
    {
        for (int i=0; i<=20; i++)
            System.out.print(" "+fib(i));
        System.out.println();
    }
}
/*
�������н�����£�
 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765
*/

