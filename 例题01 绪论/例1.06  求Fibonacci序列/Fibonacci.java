//【例1.6】  用递归算法计算Fibonacci序列。

public class Fibonacci
{
    public static int fib(int n)                 //递归方法
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
程序运行结果如下：
 0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765
*/

