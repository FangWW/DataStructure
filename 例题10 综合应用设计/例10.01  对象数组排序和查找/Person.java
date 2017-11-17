//【例10.1】  对象数组排序和查找。

import java.util.Arrays;                                   //数组类
import java.util.Comparator;                               //比较器接口

public class Person implements java.lang.Comparable<Person>//实现可比较接口
{
    protected String name;                                 //姓名
    protected int age;                                     //年龄

    public Person(String name,int age)
    {
        this.name = name;
        this.age = age;
    } 
    public Person(String name)
    {
        this(name,0);
    } 
    public Person(int age)
    {
        this("",age);
    } 
    public int getAge()
    {
        return this.age;
    }
    public String toString()
    {
        return this.name+","+this.age+"岁";
    }

    public boolean equals(Object obj)                      //比较两个对象是否相等
    {                                                      //覆盖Object类的equals()方法
        if (this == obj)                                   //this指代调用当前方法的对象
            return true;

        if (obj instanceof Person)                         //判断当前对象是否属于Person类
        {
            Person p1 = (Person)obj;                       //类型强制转换
            return this.name.equals(p1.name) && this.age==p1.age;
        }
        return false;
    }

    public int compareTo(Person p1)                        //比较两个对象的大小
    {                                                      //实现Comparable接口
        return this.name.compareTo(p1.name);               //按姓名比较对象大小
    }
}

class CompareByAge implements java.util.Comparator<Person> //按年龄比较对象大小，实现比较器接口
{
    public int compare(Person p1,Person p2)                //定义比较规则
    {
        return p1.getAge() - p2.getAge();                  //按年龄比较对象大小
    }
}

class Person_ex
{
    public static void print(Person[] people)              //输出对象数组
    {
        for(int i=0;i<people.length;i++)
             System.out.println(people[i].toString());
        System.out.println();
    }
    
    public static void main(String args[])
    {
        Person[] people = {new Person("李明",19),new Person("王伟",20),new Person("张东",20)}; //对象数组
        System.out.println("原始次序排列如下：");
        print(people);
                
        Arrays.sort(people);                               //默认按Person类compareTo()方法约定的比较规则排序
        System.out.println("按姓名次序排序如下：");
        print(people);
        
        Person key= new Person("李明");
        System.out.print("按姓名查找 ("+key.toString()+"), ");
        int find=Arrays.binarySearch(people, key);          //按姓名查找，默认按Person类compareTo()方法比较
        if (find>=0 && find<people.length)                  //返回有效下标时
            System.out.println("查找成功, ("+people[find]+")");
        else
            System.out.println("查找不成功");
        
        key= new Person("王明");
        System.out.print("按姓名查找 ("+key.toString()+"), ");
        find=Arrays.binarySearch(people, key);
        if (find>=0 && find<people.length)
            System.out.println("查找成功, ("+people[find]+")");
        else
            System.out.println("查找不成功");
        
        Arrays.sort(people,new CompareByAge());            //按CompareByAge类compare()方法约定的比较规则排序
        System.out.println("按年龄次序排序如下：");
        print(people);

        key= new Person(20);
        System.out.print("按年龄查找 ("+key.toString()+"), ");
        find=Arrays.binarySearch(people,key,new CompareByAge());     //按CompareByAge类compare()方法比较
        if (find>=0 && find<people.length)
            System.out.println("查找成功, ("+people[find]+")");
        else
            System.out.println("查找不成功");
 
        key= new Person(25);
        System.out.print("按年龄查找 ("+key.toString()+"), ");
        find=Arrays.binarySearch(people, key,new CompareByAge()); 
        if (find>=0 && find<people.length)
            System.out.println("查找成功, ("+people[find]+")");
        else
            System.out.println("查找不成功");
    }
}

/* 
程序运行结果如下：
原始次序排列如下：
李明,19岁
王伟,20岁
张东,20岁

按姓名次序排序如下：
张东,20岁
李明,19岁
王伟,20岁

按姓名查找 (李明,0岁), 查找成功, (李明,19岁)
按姓名查找 (王明,0岁), 查找不成功

按年龄次序排序如下：
李明,19岁
张东,20岁
王伟,20岁

按年龄查找 (,20岁), 查找成功, (张东,20岁)
按年龄查找 (,25岁), 查找不成功        
*/
