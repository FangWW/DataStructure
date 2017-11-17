//【例0.3】  封装的Person类。

package dataStructure;

public class Person                      //源程序文件中声明为public的类只有一个，且必须与文件同名
{
    protected String name;                       //姓名，实例成员变量，保护成员
    protected int age;                           //年龄

    public Person(String name, int age)          //构造方法
    {
        this.set(name, age);
    } 

    public Person()                              //构造方法重载
    {
        this("", 0);
    }

    public Person(Person p1)                     //构造方法重载，复制对象
    {
        this(p1.name, p1.age);
    }

    public void set(String name)                 //设置成员变量值
    {
        if (name==null)
            this.name = "";
        else
            this.name = name;
    }

    public void set(int age)                     //set()方法重载
    {
        if (age>0 && age<100)
            this.age = age;
        else
            this.age = 0;
    }
    public void set(String name, int age)        //set()方法重载
    {
        this.set(name);
        this.set(age);
    }

    public void set(Person p1)
    {
        this.set(p1.name, p1.age);
    }

    public String getName()                      //获得成员变量值
    {
        return this.name;
    }

    public int getAge()
    {
        return this.age;
    }

    public String toString()                     //覆盖Object类的toString()方法
    {
        return this.name+","+this.age+"岁";
    }

    public int olderThen(Person p2)              //比较两个人的年龄
    {
        return this.age - p2.age;
    }

    public boolean equals(Person p2)             //重载Object类中方法
    {
        if (this == p2)                          //this指代调用当前方法的对象
            return true;

        if (p2!=null)
            return this.name.equals(p2.name) && this.age==p2.age;
        return false;
    }

    public boolean equals(Object obj)            //覆盖Object类的equals()方法
    {
        if (this == obj)                         //this指代调用当前方法的对象
            return true;

        if (obj instanceof Person)               //判断当前对象是否属于Person类
        {
            Person p1 = (Person)obj;             //类型强制转换
            return this.name.equals(p1.name) && this.age==p1.age;
        }
        return false;
    }
}

class Person_ex                        //其他类的权限为默认，可有多个，提供main方法的类可执行
{
    public static void main(String args[])       //main方法也是类成员方法
    {
        Person p1 = new Person("李小明",21);
        System.out.println(p1.toString());
        Person p2 = p1;                          //两个对象间引用赋值
        System.out.println("p1==p2? "+(p1==p2)); //“==”运算符比较对象的引用
        p2.set(80);                              //同时改变p1引用的实例值
        System.out.println(p1.toString());
        
        p2 = new Person(p1);                     //创建另一个对象，值同p1
        System.out.println(p2.toString());

        System.out.println("p1==p2? "+(p1==p2));
        System.out.println("p1.equals(p2)? "+p1.equals(p2)); //比较两个对象值是否相等

        p2 = new Person("王大伟",19);
        System.out.println(p1.toString());
        System.out.println(p1.getName()+" 比 "+p2.getName()+" 大 "+p1.olderThen(p2)+" 岁");
                                         
        Object obj = new Person("李小明",21);
        System.out.println("p1.equals(obj)? "+p1.equals(obj));
    }
}

/* 
程序运行结果如下：
Person.count=0
Person.count=1  (李小明,21岁)
p1==p2? true
Person.count=1  (李小明,80岁)
Person.count=2  (李小明,80岁)
p1==p2? false
p1.equals(p2)? true
释放对象 (李小明,80岁)
Person.count=1  
Person.count=2  (王大伟,19岁)
李小明 比 王大伟 大 61 岁
p1.equals(obj)? false

*/
/*
程序正确:
    public void print()
    {
        System.out.println("本类名="+this.getClass().getName()+"  "+
             "超类名="+this.getClass().getSuperclass().getName()+"  ");
    } 
        System.out.println(this.getClass().getName()+"类  "+this.toString());  //可以调用实例方法

程序错误:
    public static int howMany()
    {
        Person1 objp=this;             //编译错,类方法中不能使用this引用, non-static variable this cannot be referenced from a static context    
        return count;
    }

        public String str="";                   //编译错,局部变量，不能使用修饰符

        static String str="";                   //局部变量，不能使用修饰符
    
*/
