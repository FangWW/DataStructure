//����10.1��  ������������Ͳ��ҡ�

import java.util.Arrays;                                   //������
import java.util.Comparator;                               //�Ƚ����ӿ�

public class Person implements java.lang.Comparable<Person>//ʵ�ֿɱȽϽӿ�
{
    protected String name;                                 //����
    protected int age;                                     //����

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
        return this.name+","+this.age+"��";
    }

    public boolean equals(Object obj)                      //�Ƚ����������Ƿ����
    {                                                      //����Object���equals()����
        if (this == obj)                                   //thisָ�����õ�ǰ�����Ķ���
            return true;

        if (obj instanceof Person)                         //�жϵ�ǰ�����Ƿ�����Person��
        {
            Person p1 = (Person)obj;                       //����ǿ��ת��
            return this.name.equals(p1.name) && this.age==p1.age;
        }
        return false;
    }

    public int compareTo(Person p1)                        //�Ƚ���������Ĵ�С
    {                                                      //ʵ��Comparable�ӿ�
        return this.name.compareTo(p1.name);               //�������Ƚ϶����С
    }
}

class CompareByAge implements java.util.Comparator<Person> //������Ƚ϶����С��ʵ�ֱȽ����ӿ�
{
    public int compare(Person p1,Person p2)                //����ȽϹ���
    {
        return p1.getAge() - p2.getAge();                  //������Ƚ϶����С
    }
}

class Person_ex
{
    public static void print(Person[] people)              //�����������
    {
        for(int i=0;i<people.length;i++)
             System.out.println(people[i].toString());
        System.out.println();
    }
    
    public static void main(String args[])
    {
        Person[] people = {new Person("����",19),new Person("��ΰ",20),new Person("�Ŷ�",20)}; //��������
        System.out.println("ԭʼ�����������£�");
        print(people);
                
        Arrays.sort(people);                               //Ĭ�ϰ�Person��compareTo()����Լ���ıȽϹ�������
        System.out.println("�����������������£�");
        print(people);
        
        Person key= new Person("����");
        System.out.print("���������� ("+key.toString()+"), ");
        int find=Arrays.binarySearch(people, key);          //���������ң�Ĭ�ϰ�Person��compareTo()�����Ƚ�
        if (find>=0 && find<people.length)                  //������Ч�±�ʱ
            System.out.println("���ҳɹ�, ("+people[find]+")");
        else
            System.out.println("���Ҳ��ɹ�");
        
        key= new Person("����");
        System.out.print("���������� ("+key.toString()+"), ");
        find=Arrays.binarySearch(people, key);
        if (find>=0 && find<people.length)
            System.out.println("���ҳɹ�, ("+people[find]+")");
        else
            System.out.println("���Ҳ��ɹ�");
        
        Arrays.sort(people,new CompareByAge());            //��CompareByAge��compare()����Լ���ıȽϹ�������
        System.out.println("����������������£�");
        print(people);

        key= new Person(20);
        System.out.print("��������� ("+key.toString()+"), ");
        find=Arrays.binarySearch(people,key,new CompareByAge());     //��CompareByAge��compare()�����Ƚ�
        if (find>=0 && find<people.length)
            System.out.println("���ҳɹ�, ("+people[find]+")");
        else
            System.out.println("���Ҳ��ɹ�");
 
        key= new Person(25);
        System.out.print("��������� ("+key.toString()+"), ");
        find=Arrays.binarySearch(people, key,new CompareByAge()); 
        if (find>=0 && find<people.length)
            System.out.println("���ҳɹ�, ("+people[find]+")");
        else
            System.out.println("���Ҳ��ɹ�");
    }
}

/* 
�������н�����£�
ԭʼ�����������£�
����,19��
��ΰ,20��
�Ŷ�,20��

�����������������£�
�Ŷ�,20��
����,19��
��ΰ,20��

���������� (����,0��), ���ҳɹ�, (����,19��)
���������� (����,0��), ���Ҳ��ɹ�

����������������£�
����,19��
�Ŷ�,20��
��ΰ,20��

��������� (,20��), ���ҳɹ�, (�Ŷ�,20��)
��������� (,25��), ���Ҳ��ɹ�        
*/
