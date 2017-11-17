//�жϸ����ַ����Ƿ�ΪJava�ؼ��֡�
//8.2.3  ��������˳���ķֿ�����㷨��

public class KeyWords  
{
    private static String[] keywords={"abstract","boolean","break","byte","case","catch","char",
        "class","continue","default","do","double","else","extends","false","final","finally","float",
        "for","if","implements","import","instanceof","int","interface","long","native","new","null",
        "package","private","protected","public","return","short","static","super","switch",
        "synchronized","this","throw","throws","transient","true","try","void","volatile","while"};    //�ؼ��ֱ�

    private static IndexItem index[]={new IndexItem("a",0), new IndexItem("b",1),
        new IndexItem("c",4), new IndexItem("d",9), new IndexItem("e",12), new IndexItem("f",14),
        new IndexItem("i",19), new IndexItem("l",25), new IndexItem("n",26),
        new IndexItem("p",29), new IndexItem("r",33),new IndexItem("s",34),
        new IndexItem("t",39), new IndexItem("v",45), new IndexItem("w",46)}; //������

    private static class IndexItem implements Comparable<IndexItem>  //�ڲ��࣬˽�г�Ա
    {
        String first;                                      //�ؼ��ֵ�����ĸ
        int start;                                         //����ĸ��ͬ�Ĺؼ��ֿ��������е���ʼ�±�
        public IndexItem(String first,int i)
        {
            this.first=first;
            start=i; 
        }
        public IndexItem(String first)
        {
            this(first,-1);
        }
        public String toString()
        {
            return this.first;
        }
        public int compareTo(IndexItem item)               //Լ������������Ƚϴ�С�Ĺ���ʵ��Comparable�ӿ�
        {
            return this.first.compareTo(item.first);       //������ĸ�Ƚϴ�С
        }
    }//�ڲ������

    public static boolean isKeyword(String str)            //�ж�str�Ƿ�ΪJava�ؼ���
    {
        if (str!=null)
        {
            IndexItem item = new IndexItem(str.substring(0,1));      //����ĸ��Ӧ��������
            int pos=BSArray.binarySearch(index, item);           //�۰�������������������λ��
            int left=index[pos].start;                               //���������ҷ�Χ���½�
            int high=index[pos+1].start-1;                          //���������ҷ�Χ���Ͻ�
            int find=BSArray.binarySearch(keywords,str,left,high);  //�۰��������ָ����Χ
            return find>=0;
        }
        return false;
    }
        
    public static void main(String[] args) 
    {
        String str="final";
        System.out.println(str+(isKeyword(str)?"":"��")+"�ǹؼ���");
        str ="length";
        System.out.println(str+(isKeyword(str)?"":"��")+"�ǹؼ���");
    }
}

/*
�������н�����£�
l? d? f? finally? false? final? final�ǹؼ���
l? long? length���ǹؼ���


*/

