//【例0.4】  Student类继承Person类。

import dataStructure.Person;

public class Student extends Person 
{
    private String speciality;                   //专业，子类增加的成员变量

    public static void main(String args[])
    {
        Person p1 = new Person("李小明",21);
        Student s1 = new Student();              //默认构造方法
        s1.set("王大伟",19);
        System.out.println(s1.toString());
        System.out.println(p1.getName()+" 比 "+s1.getName()+" 大 "+ p1.olderThen(s1)+" 岁");
                                                 //通过对象调用父类的实例成员方法
    }
}

/* 
程序运行结果如下：
王大伟,19岁
李小明 比 王大伟 大 2 岁


//②	子类没有声明构造方法时，有默认的构造方法。
//③	子类能够继承父类的私有成员（包括最终变量），但不具有访问权限。
//④	子类不能继承父类的构造方法。

public class Student extends Person
{
  public static void main(String args[])
  {
      Student s1 = new Student();                  //正确，默认构造方法，执行父类的无参数的构造方法Person()
      s1.print();                                    //子类继承了父类的私有成员
//      System.out.println(s1.name+", "+s1.age+"岁");  //编译错误，对父类的私有成员不具有访问权限

//      Student s1 = new Student("李小明",21);       //编译错
  }
}


/*
如果父类Person没有声明构造函数Person()，则下列语句不能通过编译：
      Student s1 = new Student();                  //编译错，找不到构造方法Person()
*/
