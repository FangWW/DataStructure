//����3.8��  KMPģʽƥ���㷨ʵ�֡�

public class KMP 
{
    private static int count=0;                            //���رȽϴ���
    private static int[] next;
    private static int[] nextk;

    public static int indexOf(String target, String pattern, int start)        //KMPģʽƥ���㷨ʵ��
    {
        if (target!=null && pattern!=null && pattern.length()>0 && target.length()>=pattern.length())
        {                                                  //��Ŀ�괮�ϳ�ʱ���бȽ�
            int i=start, j=0;                              //i��j�ֱ�ΪĿ�괮��ģʽ����ǰ�Ƚ��ַ������
            count=0;   
            nextk = getNextk(pattern);
            next = getNext(pattern);

            while (i<target.length())
            {
//                System.out.println("i="+i+"  j="+j);
                if (j==-1 || target.charAt(i)==pattern.charAt(j))
                {
                    i++;                                   //�����ȽϺ����ַ�
                    j++;
                }
                else
                    j=next[j];                             //���ģʽ����һ�˱Ƚ����ַ������

                if(j!=-1) count++;
                if (j==pattern.length())                   //һ�˱ȽϽ�����ƥ��ɹ�
                    return i-j;                            //����ƥ����Ӵ����
            }
        }
        return -1;                                         //ƥ��ʧ��ʱ����-1
    }

    public static int indexOf(String target, String pattern)
    {
        return indexOf(target, pattern, 0);
    }

    private static int[] getNextk(String pattern)          //����ģʽ��pattern��next����
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
                next[j]=k;                                 //�д��Ľ�
            }
            else
                k=next[k];
        }
        return next;
    }

    private static int[] getNext(String pattern)               //����ģʽ��pattern�Ľ���next����
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

    private static String toString(int[] next)        //��ģʽ��str��next[j]����ֵ�������鷵��
    {
        String temp="";
        for (int i=0; i<next.length; i++)
            temp += next[i]+" ";
        return temp;
    }

    public static void main(String args[]) 
    {
//        String target="acabbabbabc", pattern="abbabc";    //ͼ3.11
//        String target="aabcbabcaabcaababc", pattern="abcaababc"; //����Т��
//        String target="ababaab", pattern="aab";                //��ε��
//        String target="aaaaab", pattern="aab";         //����
//        String target="abbabaaba", pattern="aba";             //BF����

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

KMP.indexOf("aabcbabcaabcaababc", "abcaababc")=9   //����Т��
nextk[]: -1 0 0 0 1 1 2 1 2 
next[]:  -1 0 0 -1 1 0 2 0 0 
KMP.count=20

KMP.indexOf("aaabaaaab", "aaaab")=4                //��ε��
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
KMP.count=9                                       //������O(n+m)

KMP.indexOf("abbabaaba", "aba")=3                 //BF����
nextk[]: -1 0 0 
next[]:  -1 0 -1 
KMP.count=6

*/