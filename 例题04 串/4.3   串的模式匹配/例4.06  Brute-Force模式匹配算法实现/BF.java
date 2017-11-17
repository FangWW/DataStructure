//����3.6��  Brute-Forceģʽƥ���㷨ʵ�֡�

public class BF
{
    private static int count=0;                            //���رȽϴ���

    public static int indexOf(String target, String pattern, int start)
    {                                                      //����ģʽ��pattern��Ŀ�괮target�д�start��ʼ�ĵ�һ��ƥ��λ�ã�ƥ��ʧ��ʱ���أ�1��
        if (target!=null && pattern!=null && pattern.length()>0 && target.length()>=pattern.length())
        {                                                  //��Ŀ�괮��ģʽ����ʱ���бȽ�
            int i=start, j=0;                              //i��ʾĿ�괮��ĳ���Ӵ������
            count=0;   
            while (i<=target.length()-pattern.length())    //i�仯��Χ��0����������֮��
            {
                if (target.charAt(i+j)==pattern.charAt(j)) //jΪģʽ����ǰ�ַ����±�
                    j++;                                   //�����ȽϺ����ַ�
                else
                {
                    i++;                                   //Ŀ�괮�����Ƚ���һ���Ӵ�
                    j=0;                                   //ģʽ���±��˻ص�0
                }
                count++;
                if (j==pattern.length())                   //һ��ƥ�������ƥ��ɹ�
                    return i;                              //�����Ӵ����
            }
        }
        return -1;                                         //ƥ��ʧ��ʱ����-1
    }

    public static int indexOf(String target, String pattern)
    {
        return indexOf(target, pattern, 0);
    }

    public static void main(String args[]) 
    {
//        String target="abbabaaba", pattern="aba";
        String target="aabaaa", pattern="aab";    //����
        System.out.println("BF.indexOf(\""+target+"\", \""+pattern+"\")="+BF.indexOf(target, pattern));
        System.out.println("BF.count="+BF.count);
    }
}

/*
indexOf("abbaba", "aba")=3
count=8

indexOf("aaaaab", "aab")=3
count=12

indexOf("aaaaaa", "aab")=-1                      //����
count=12                                         //�Ƚ�m*(n-m+1)�Σ�O(n*m)

indexOf("aaaaaa", "aaa")=0                       //������
count=3

indexOf("aabaaa", "aab")=0
count=3

 **/