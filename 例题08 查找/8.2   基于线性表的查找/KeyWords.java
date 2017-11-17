//判断给定字符串是否为Java关键字。
//8.2.3  基于索引顺序表的分块查找算法。

public class KeyWords  
{
    private static String[] keywords={"abstract","boolean","break","byte","case","catch","char",
        "class","continue","default","do","double","else","extends","false","final","finally","float",
        "for","if","implements","import","instanceof","int","interface","long","native","new","null",
        "package","private","protected","public","return","short","static","super","switch",
        "synchronized","this","throw","throws","transient","true","try","void","volatile","while"};    //关键字表

    private static IndexItem index[]={new IndexItem("a",0), new IndexItem("b",1),
        new IndexItem("c",4), new IndexItem("d",9), new IndexItem("e",12), new IndexItem("f",14),
        new IndexItem("i",19), new IndexItem("l",25), new IndexItem("n",26),
        new IndexItem("p",29), new IndexItem("r",33),new IndexItem("s",34),
        new IndexItem("t",39), new IndexItem("v",45), new IndexItem("w",46)}; //索引表

    private static class IndexItem implements Comparable<IndexItem>  //内部类，私有成员
    {
        String first;                                      //关键字的首字母
        int start;                                         //首字母相同的关键字块在主表中的起始下标
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
        public int compareTo(IndexItem item)               //约定两个索引项比较大小的规则，实现Comparable接口
        {
            return this.first.compareTo(item.first);       //按首字母比较大小
        }
    }//内部类结束

    public static boolean isKeyword(String str)            //判断str是否为Java关键字
    {
        if (str!=null)
        {
            IndexItem item = new IndexItem(str.substring(0,1));      //首字母对应的索引项
            int pos=BSArray.binarySearch(index, item);           //折半查找索引表，获得索引项位置
            int left=index[pos].start;                               //获得主表查找范围的下界
            int high=index[pos+1].start-1;                          //获得主表查找范围的上界
            int find=BSArray.binarySearch(keywords,str,left,high);  //折半查找主表指定范围
            return find>=0;
        }
        return false;
    }
        
    public static void main(String[] args) 
    {
        String str="final";
        System.out.println(str+(isKeyword(str)?"":"不")+"是关键字");
        str ="length";
        System.out.println(str+(isKeyword(str)?"":"不")+"是关键字");
    }
}

/*
程序运行结果如下：
l? d? f? finally? false? final? final是关键字
l? long? length不是关键字


*/

