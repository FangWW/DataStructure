//两变量交换问题讨论。

public final class Integer1
{
//    private final int value;                   //java.lang.Integer类中的值是常量
    private int value;
    
    public Integer1(int value)                   //构造方法
    {
        this.value = value;
    }
    
    public int intValue()                        //Integer类中的方法
    {
        return value;
    }

    public void setValue(int value)              //新增加的方法
    {
        this.value = value;
    }


    public static void swap1(Integer x, Integer y) //不行
    {
        Integer temp=x;                          //两个对象之间的赋值是引用赋值，传递的是引用，即变量的地址
        x=y;                                     //改变形式参数x的引用，而未能改变实际参数的值，
        y=temp;                                  //相当于改变了指针的值，而未改变通过指针指向的变量值
        System.out.println("x = "+x.intValue());
        System.out.println("y = "+y.intValue());
    }                                            //交换了，但改变的值没有带回

    public static void swap2(Integer1 x, Integer1 y) //可行
    {
        int temp = x.intValue();                 //取值
        x.setValue(y.intValue());                //通过引用改变实际参数对象的值
        y.setValue(temp);
    }

    public static void main(String args[]) 
    {
        Integer intobj_a = new Integer(1);
        Integer intobj_b = new Integer(2);
        swap1(intobj_a,intobj_b);
        System.out.println("intobj_a = "+intobj_a.toString());//.intValue());
        System.out.println("intobj_b = "+intobj_b.intValue());
        
        Integer1 intobj_i = new Integer1(1);
        Integer1 intobj_j = new Integer1(2);
        swap2(intobj_i,intobj_j);
        System.out.println("intobj_i = "+intobj_i.intValue());
        System.out.println("intobj_j = "+intobj_j.intValue());
        
    }
}

/*
程序运行结果如下：
x = 2
y = 1
intobj_a = 1
intobj_b = 2
intobj_i = 2
intobj_j = 1

*/
