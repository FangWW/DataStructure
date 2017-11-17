//【例3.6】  Brute-Force模式匹配算法实现。

public class BF
{
    private static int count=0;                            //记载比较次数

    public static int indexOf(String target, String pattern, int start)
    {                                                      //返回模式串pattern在目标串target中从start开始的第一次匹配位置，匹配失败时返回－1。
        if (target!=null && pattern!=null && pattern.length()>0 && target.length()>=pattern.length())
        {                                                  //当目标串比模式串长时进行比较
            int i=start, j=0;                              //i表示目标串中某个子串的序号
            count=0;   
            while (i<=target.length()-pattern.length())    //i变化范围是0～两串长度之差
            {
                if (target.charAt(i+j)==pattern.charAt(j)) //j为模式串当前字符的下标
                    j++;                                   //继续比较后续字符
                else
                {
                    i++;                                   //目标串继续比较下一个子串
                    j=0;                                   //模式串下标退回到0
                }
                count++;
                if (j==pattern.length())                   //一次匹配结束，匹配成功
                    return i;                              //返回子串序号
            }
        }
        return -1;                                         //匹配失败时返回-1
    }

    public static int indexOf(String target, String pattern)
    {
        return indexOf(target, pattern, 0);
    }

    public static void main(String args[]) 
    {
//        String target="abbabaaba", pattern="aba";
        String target="aabaaa", pattern="aab";    //最坏情况
        System.out.println("BF.indexOf(\""+target+"\", \""+pattern+"\")="+BF.indexOf(target, pattern));
        System.out.println("BF.count="+BF.count);
    }
}

/*
indexOf("abbaba", "aba")=3
count=8

indexOf("aaaaab", "aab")=3
count=12

indexOf("aaaaaa", "aab")=-1                      //最坏情况
count=12                                         //比较m*(n-m+1)次，O(n*m)

indexOf("aaaaaa", "aaa")=0                       //最好情况
count=3

indexOf("aabaaa", "aab")=0
count=3

 **/