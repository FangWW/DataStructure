//【例0.5】  类的多态性，Student类重定义父类成员。

import dataStructure.Person;

public class Student extends Person                        //Student是Person类的子类
{
    private String speciality;                             //专业，子类私有成员变量

    public Student(String name, int age, String spec)
    {
        super(name, age);                                  //调用父类的构造方法
        this.speciality = spec;
    } 

    public Student()
    {
        this("", 0, "");
    }   

    public void setSpeciality(String spec)                 //子类增加的成员方法
    {
        if (spec==null)
            this.speciality = "";
        else
            this.speciality = spec;
    }

    public void set(String name, int age, String spec)     //重载父类同名成员方法
    {
        super.set(name, age);
        this.setSpeciality(spec);
    }
            
    public String getSpeciality()
    {
        return this.speciality;
    }

    public String toString()                               //覆盖父类的同名方法
    {
        return super.toString() +"," + speciality+"专业";  //调用父类的同名方法
    }

    public boolean equals(Object obj)                      //覆盖Person类中方法
    {
        if (obj instanceof Student) 
        {
            Student stu = (Student)obj;
            return super.equals(stu) && this.speciality.equals(stu.speciality);
        }
        return false;
    }

    public static void main(String args[])
    {
        Student s2= new Student("张小莉",18,"计算机"); //子类对象
        System.out.println(s2.toString());
    }
}

/*
程序运行结果如下：
张小莉,18岁,计算机专业
*/

/*
程序错误：

        p2.set("张小莉",20,"计算机");               //编译错，Person1类中没有定义3个参数的set()方法
        p2.setSpeciality("电子信息");               //编译错，
cannot find symbol
symbol  : method setSpeciality(java.lang.String)

    public Student(Person p1, String spec)
    {
        super(p1);                                         //这个构造方法不行，子类对象没有计数
        this.setSpeciality(spec);

    	//        this(p1.name, p1.age, spec);              //如果父类成员变量为private，则p1.name和p1.age不可视
    }

*/