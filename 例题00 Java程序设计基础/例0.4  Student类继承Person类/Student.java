//����0.4��  Student��̳�Person�ࡣ

import dataStructure.Person;

public class Student extends Person 
{
    private String speciality;                   //רҵ���������ӵĳ�Ա����

    public static void main(String args[])
    {
        Person p1 = new Person("��С��",21);
        Student s1 = new Student();              //Ĭ�Ϲ��췽��
        s1.set("����ΰ",19);
        System.out.println(s1.toString());
        System.out.println(p1.getName()+" �� "+s1.getName()+" �� "+ p1.olderThen(s1)+" ��");
                                                 //ͨ��������ø����ʵ����Ա����
    }
}

/* 
�������н�����£�
����ΰ,19��
��С�� �� ����ΰ �� 2 ��


//��	����û���������췽��ʱ����Ĭ�ϵĹ��췽����
//��	�����ܹ��̳и����˽�г�Ա���������ձ��������������з���Ȩ�ޡ�
//��	���಻�ܼ̳и���Ĺ��췽����

public class Student extends Person
{
  public static void main(String args[])
  {
      Student s1 = new Student();                  //��ȷ��Ĭ�Ϲ��췽����ִ�и�����޲����Ĺ��췽��Person()
      s1.print();                                    //����̳��˸����˽�г�Ա
//      System.out.println(s1.name+", "+s1.age+"��");  //������󣬶Ը����˽�г�Ա�����з���Ȩ��

//      Student s1 = new Student("��С��",21);       //�����
  }
}


/*
�������Personû���������캯��Person()����������䲻��ͨ�����룺
      Student s1 = new Student();                  //������Ҳ������췽��Person()
*/
