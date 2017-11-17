//【例3.8】  KMP模式匹配算法实现。

public class KMP 
{
    private static int count=0;                            //记载比较次数
    private static int[] next;
    private static int[] nextk;

    public static int indexOf(String target, String pattern, int start)        //KMP模式匹配算法实现
    {
        if (target!=null && pattern!=null && pattern.length()>0 && target.length()>=pattern.length())
        {                                                  //当目标串较长时进行比较
            int i=start, j=0;                              //i、j分别为目标串、模式串当前比较字符的序号
            count=0;   
            nextk = getNextk(pattern);
            next = getNext(pattern);

            while (i<target.length())
            {
//                System.out.println("i="+i+"  j="+j);
                if (j==-1 || target.charAt(i)==pattern.charAt(j))
                {
                    i++;                                   //继续比较后续字符
                    j++;
                }
                else
                    j=next[j];                             //获得模式串下一趟比较首字符的序号

                if(j!=-1) count++;
                if (j==pattern.length())                   //一趟比较结束，匹配成功
                    return i-j;                            //返回匹配的子串序号
            }
        }
        return -1;                                         //匹配失败时返回-1
    }

    public static int indexOf(String target, String pattern)
    {
        return indexOf(target, pattern, 0);
    }

    private static int[] getNextk(String pattern)          //返回模式串pattern的next数组
    {
        int j=0, k=-1;
        int[] next=new int[pattern.length()];
        next[0]=-1;
        while (j<pattern.length()-1)
        {
            if (k==-1 || pattern.charAt(j)==pattern.charAt(k))
            {
                j++;
                k++;
                next[j]=k;                                 //有待改进
            }
            else
                k=next[k];
        }
        return next;
    }

    private static int[] getNext(String pattern)               //返回模式串pattern改进的next数组
    {
        int j=0, k=-1;
        int[] next=new int[pattern.length()];
        next[0]=-1;
        while (j<pattern.length()-1)
        {
            if (k==-1 || pattern.charAt(j)==pattern.charAt(k))
            {
                j++;
                k++;
                if (pattern.charAt(j)!=pattern.charAt(k))
                    next[j]=k;
                else
                    next[j]=next[k];
            }
            else
                k=next[k];
        }
        return next;
    }

    private static String toString(int[] next)        //求模式串str的next[j]函数值并用数组返回
    {
        String temp="";
        for (int i=0; i<next.length; i++)
            temp += next[i]+" ";
        return temp;
    }

    public static void main(String args[]) 
    {
//        String target="acabbabbabc", pattern="abbabc";    //图3.11
//        String target="aabcbabcaabcaababc", pattern="abcaababc"; //张乃孝书
//        String target="ababaab", pattern="aab";                //严蔚敏
//        String target="aaaaab", pattern="aab";         //最坏情况
//        String target="abbabaaba", pattern="aba";             //BF用例

        String target="acabcabbabcabc", pattern="abcabc";             //

        System.out.println("\nKMP.indexOf(\""+target+"\", \""+pattern+"\")="+KMP.indexOf(target, pattern));
        System.out.println("nextk[]: "+toString(nextk));
        System.out.println("next[]:  "+toString(next));
        System.out.println("KMP.count="+KMP.count);
    }
}

/*
KMP.indexOf("abbacabbabbabc", "abbabc")=8
nextk[]: -1 0 0 0 1 2 
next[]:  -1 0 0 -1 0 2 
KMP.count=16

KMP.indexOf("acabbabbabc", "abbabc")=5
nextk[]: -1 0 0 0 1 2 
next[]:  -1 0 0 -1 0 2 
KMP.count=13

KMP.indexOf("aabcbabcaabcaababc", "abcaababc")=9   //张乃孝书
nextk[]: -1 0 0 0 1 1 2 1 2 
next[]:  -1 0 0 -1 1 0 2 0 0 
KMP.count=20

KMP.indexOf("aaabaaaab", "aaaab")=4                //严蔚敏
nextk[]: -1 0 1 2 3 
next[]:  -1 -1 -1 -1 3 
KMP.count=9

KMP.indexOf("aabaaab", "aaab")=3
nextk[]: -1 0 1 2 
next[]:  -1 -1 -1 2 
KMP.count=7

KMP.indexOf("aaaaab", "aab")=3
nextk[]: -1 0 1 
next[]: -1 -1 1 
KMP.count=9                                       //最坏情况，O(n+m)

KMP.indexOf("abbabaaba", "aba")=3                 //BF用例
nextk[]: -1 0 0 
next[]:  -1 0 -1 
KMP.count=6

*/