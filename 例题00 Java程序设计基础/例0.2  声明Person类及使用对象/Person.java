//【例0.2】  声明Person类及使用对象。

public class Person
{
    String name;                                 //姓名，成员变量
    int age;                                     //年龄，成员变量

    public String toString()                     //返回对象的字符串信息，成员方法
    {
        return name+", "+age+"岁";
    } 
    
    public static void main(String args[])
    {
        Person p1 = new Person();                //创建对象，执行默认构造方法
        System.out.println(p1.toString());       //调用对象方法，对象已初始化为默认值
        p1.name = "王小明";                      //引用成员变量
        p1.age = 20;
        System.out.println(p1.toString());
    }
}

/* 
程序运行结果如下：
null, 0岁 
王小明, 20岁
*/
