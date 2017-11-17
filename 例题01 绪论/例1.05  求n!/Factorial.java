//【例1.5】  求n！。

public class Factorial
{
    public static int fact(int n)                //求阶乘，递归方法
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
        return -1;                               //n<0时n!无定义
    }

    public static void main(String args[])
    {
        int i=5;
        if (args.length>0)
            i=Integer.parseInt(args[0]);         //将args[0]转换为int值
        System.out.println(i+"!="+fact(i));
    }
}
/*
程序运行结果如下：
5!=5*4!
4!=4*3!
3!=3*2!
2!=2*1!
5!=120
*/