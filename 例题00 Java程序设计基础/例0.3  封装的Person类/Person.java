//����0.3��  ��װ��Person�ࡣ

package dataStructure;

public class Person                      //Դ�����ļ�������Ϊpublic����ֻ��һ�����ұ������ļ�ͬ��
{
    protected String name;                       //������ʵ����Ա������������Ա
    protected int age;                           //����

    public Person(String name, int age)          //���췽��
    {
        this.set(name, age);
    } 

    public Person()                              //���췽������
    {
        this("", 0);
    }

    public Person(Person p1)                     //���췽�����أ����ƶ���
    {
        this(p1.name, p1.age);
    }

    public void set(String name)                 //���ó�Ա����ֵ
    {
        if (name==null)
            this.name = "";
        else
            this.name = name;
    }

    public void set(int age)                     //set()��������
    {
        if (age>0 && age<100)
            this.age = age;
        else
            this.age = 0;
    }
    public void set(String name, int age)        //set()��������
    {
        this.set(name);
        this.set(age);
    }

    public void set(Person p1)
    {
        this.set(p1.name, p1.age);
    }

    public String getName()                      //��ó�Ա����ֵ
    {
        return this.name;
    }

    public int getAge()
    {
        return this.age;
    }

    public String toString()                     //����Object���toString()����
    {
        return this.name+","+this.age+"��";
    }

    public int olderThen(Person p2)              //�Ƚ������˵�����
    {
        return this.age - p2.age;
    }

    public boolean equals(Person p2)             //����Object���з���
    {
        if (this == p2)                          //thisָ�����õ�ǰ�����Ķ���
            return true;

        if (p2!=null)
            return this.name.equals(p2.name) && this.age==p2.age;
        return false;
    }

    public boolean equals(Object obj)            //����Object���equals()����
    {
        if (this == obj)                         //thisָ�����õ�ǰ�����Ķ���
            return true;

        if (obj instanceof Person)               //�жϵ�ǰ�����Ƿ�����Person��
        {
            Person p1 = (Person)obj;             //����ǿ��ת��
            return this.name.equals(p1.name) && this.age==p1.age;
        }
        return false;
    }
}

class Person_ex                        //�������Ȩ��ΪĬ�ϣ����ж�����ṩmain���������ִ��
{
    public static void main(String args[])       //main����Ҳ�����Ա����
    {
        Person p1 = new Person("��С��",21);
        System.out.println(p1.toString());
        Person p2 = p1;                          //������������ø�ֵ
        System.out.println("p1==p2? "+(p1==p2)); //��==��������Ƚ϶��������
        p2.set(80);                              //ͬʱ�ı�p1���õ�ʵ��ֵ
        System.out.println(p1.toString());
        
        p2 = new Person(p1);                     //������һ������ֵͬp1
        System.out.println(p2.toString());

        System.out.println("p1==p2? "+(p1==p2));
        System.out.println("p1.equals(p2)? "+p1.equals(p2)); //�Ƚ���������ֵ�Ƿ����

        p2 = new Person("����ΰ",19);
        System.out.println(p1.toString());
        System.out.println(p1.getName()+" �� "+p2.getName()+" �� "+p1.olderThen(p2)+" ��");
                                         
        Object obj = new Person("��С��",21);
        System.out.println("p1.equals(obj)? "+p1.equals(obj));
    }
}

/* 
�������н�����£�
Person.count=0
Person.count=1  (��С��,21��)
p1==p2? true
Person.count=1  (��С��,80��)
Person.count=2  (��С��,80��)
p1==p2? false
p1.equals(p2)? true
�ͷŶ��� (��С��,80��)
Person.count=1  
Person.count=2  (����ΰ,19��)
��С�� �� ����ΰ �� 61 ��
p1.equals(obj)? false

*/
/*
������ȷ:
    public void print()
    {
        System.out.println("������="+this.getClass().getName()+"  "+
             "������="+this.getClass().getSuperclass().getName()+"  ");
    } 
        System.out.println(this.getClass().getName()+"��  "+this.toString());  //���Ե���ʵ������

�������:
    public static int howMany()
    {
        Person1 objp=this;             //�����,�෽���в���ʹ��this����, non-static variable this cannot be referenced from a static context    
        return count;
    }

        public String str="";                   //�����,�ֲ�����������ʹ�����η�

        static String str="";                   //�ֲ�����������ʹ�����η�
    
*/
