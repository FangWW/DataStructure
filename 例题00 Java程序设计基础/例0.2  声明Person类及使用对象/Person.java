//����0.2��  ����Person�༰ʹ�ö���

public class Person
{
    String name;                                 //��������Ա����
    int age;                                     //���䣬��Ա����

    public String toString()                     //���ض�����ַ�����Ϣ����Ա����
    {
        return name+", "+age+"��";
    } 
    
    public static void main(String args[])
    {
        Person p1 = new Person();                //��������ִ��Ĭ�Ϲ��췽��
        System.out.println(p1.toString());       //���ö��󷽷��������ѳ�ʼ��ΪĬ��ֵ
        p1.name = "��С��";                      //���ó�Ա����
        p1.age = 20;
        System.out.println(p1.toString());
    }
}

/* 
�������н�����£�
null, 0�� 
��С��, 20��
*/
