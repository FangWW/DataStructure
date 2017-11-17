//【例2.1】使用顺序表类SeqList求解约瑟夫环问题。
//【例2.2】使用单链表求解约瑟夫环问题。

import dataStructure.linearList.LList;
import dataStructure.linearList.SeqList;                   //顺序表类
import dataStructure.linearList.SinglyLinkedList;          //单链表
import dataStructure.linearList.HSLinkedList;              //带头结点的单链表类
 
public class Josephus                                      //求解约瑟夫环问题
{
    private LList<String> list;                            //存储约瑟夫环中多个对象
    
    public Josephus(int number, int start, int distance)   //创建约瑟夫环并求解
    {                                                      //参数指定环长度、起始位置、计数
        this.list = new SeqList<String>(number);           //顺序表元素类型是字符串，指定表容量
//        this.list = new SinglyLinkedList<String>();        //单链表元素类型是字符串
//        this.list = new HSLinkedList<String>();
        for (int i=0; i<number; i++)
            this.list.add(0, new String((char)('A'+i)+""));   //添加字符串对象
        System.out.print("约瑟夫环("+number+","+start+","+distance+")，");
        System.out.println(this.list.toString());          //显示顺序表所有对象的字符串表示

        int index = start-1;                               //计数起始位置
        while (this.list.length()>1)                       //多于一个对象时循环 
        {
            index = (index+distance-1) % this.list.length();
            System.out.print("删除"+this.list.remove(index).toString()+"，");  //删除指定位置对象
            System.out.println(this.list.toString());
        }
        System.out.println("被赦免者是"+list.get(0).toString());

        System.out.println(this.list.add("Y")+this.list.toString());
    }
    
    public static void main(String args[])
    {
        new Josephus(5,1,2);
    }
}
    
/*
程序运行结果如下：
约瑟夫环(5,1,2)，(A, B, C, D, E) 
删除B，(A, C, D, E)
删除D，(A, C, E)
删除A，(C, E)
删除E，(C)
被赦免者是C 

*/