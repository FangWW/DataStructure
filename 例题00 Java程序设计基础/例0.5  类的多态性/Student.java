//����0.5��  ��Ķ�̬�ԣ�Student���ض��常���Ա��

import dataStructure.Person;

public class Student extends Person                        //Student��Person�������
{
    private String speciality;                             //רҵ������˽�г�Ա����

    public Student(String name, int age, String spec)
    {
        super(name, age);                                  //���ø���Ĺ��췽��
        this.speciality = spec;
    } 

    public Student()
    {
        this("", 0, "");
    }   

    public void setSpeciality(String spec)                 //�������ӵĳ�Ա����
    {
        if (spec==null)
            this.speciality = "";
        else
            this.speciality = spec;
    }

    public void set(String name, int age, String spec)     //���ظ���ͬ����Ա����
    {
        super.set(name, age);
        this.setSpeciality(spec);
    }
            
    public String getSpeciality()
    {
        return this.speciality;
    }

    public String toString()                               //���Ǹ����ͬ������
    {
        return super.toString() +"," + speciality+"רҵ";  //���ø����ͬ������
    }

    public boolean equals(Object obj)                      //����Person���з���
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
        Student s2= new Student("��С��",18,"�����"); //�������
        System.out.println(s2.toString());
    }
}

/*
�������н�����£�
��С��,18��,�����רҵ
*/

/*
�������

        p2.set("��С��",20,"�����");               //�����Person1����û�ж���3��������set()����
        p2.setSpeciality("������Ϣ");               //�����
cannot find symbol
symbol  : method setSpeciality(java.lang.String)

    public Student(Person p1, String spec)
    {
        super(p1);                                         //������췽�����У��������û�м���
        this.setSpeciality(spec);

    	//        this(p1.name, p1.age, spec);              //��������Ա����Ϊprivate����p1.name��p1.age������
    }

*/