//����2.1��ʹ��˳�����SeqList���Լɪ�����⡣
//����2.2��ʹ�õ��������Լɪ�����⡣

import dataStructure.linearList.LList;
import dataStructure.linearList.SeqList;                   //˳�����
import dataStructure.linearList.SinglyLinkedList;          //������
import dataStructure.linearList.HSLinkedList;              //��ͷ���ĵ�������
 
public class Josephus                                      //���Լɪ������
{
    private LList<String> list;                            //�洢Լɪ���ж������
    
    public Josephus(int number, int start, int distance)   //����Լɪ�򻷲����
    {                                                      //����ָ�������ȡ���ʼλ�á�����
        this.list = new SeqList<String>(number);           //˳���Ԫ���������ַ�����ָ��������
//        this.list = new SinglyLinkedList<String>();        //������Ԫ���������ַ���
//        this.list = new HSLinkedList<String>();
        for (int i=0; i<number; i++)
            this.list.add(0, new String((char)('A'+i)+""));   //����ַ�������
        System.out.print("Լɪ��("+number+","+start+","+distance+")��");
        System.out.println(this.list.toString());          //��ʾ˳������ж�����ַ�����ʾ

        int index = start-1;                               //������ʼλ��
        while (this.list.length()>1)                       //����һ������ʱѭ�� 
        {
            index = (index+distance-1) % this.list.length();
            System.out.print("ɾ��"+this.list.remove(index).toString()+"��");  //ɾ��ָ��λ�ö���
            System.out.println(this.list.toString());
        }
        System.out.println("����������"+list.get(0).toString());

        System.out.println(this.list.add("Y")+this.list.toString());
    }
    
    public static void main(String args[])
    {
        new Josephus(5,1,2);
    }
}
    
/*
�������н�����£�
Լɪ��(5,1,2)��(A, B, C, D, E) 
ɾ��B��(A, C, D, E)
ɾ��D��(A, C, E)
ɾ��A��(C, E)
ɾ��E��(C)
����������C 

*/