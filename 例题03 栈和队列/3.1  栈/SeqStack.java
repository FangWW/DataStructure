package dataStructure.linearList;
import dataStructure.linearList.SStack;

public class SeqStack<E> implements SStack<E>    //˳��ջ��
{
    private Object value[];                      //�洢ջ������Ԫ��
    private int top;                             //topΪջ��Ԫ���±�

    public SeqStack(int capacity)                //����ָ�������Ŀ�ջ
    {
        this.value = new Object[Math.abs(capacity)];
        this.top=-1;
    }
    public SeqStack()                            //����Ĭ�������Ŀ�ջ
    {
        this(10);
    }

    public boolean isEmpty()                     //�ж��Ƿ��ջ������ջ����true
    {
        return this.top==-1;
    } 

    public boolean push(E element)               //Ԫ��element��ջ���������ɹ�����true
    {
        if (element==null)
           return false;                         //�ն���null��������ջ

        if (this.top==value.length-1)            //��ջ��������������
        {
            Object[] temp = this.value;         
            this.value = new Object[temp.length*2];
            for (int i=0; i<temp.length; i++)
                this.value[i] = temp[i];
        }
        this.top++;
        this.value[this.top] = element;
        return true;
    } 

    public E pop()                               //��ջ�����ص�ǰջ��Ԫ�أ���ջ�շ���null
    {
        if (!isEmpty())
            return (E)this.value[this.top--];
        else
            return null;
    }

    public E get()                               //ȡջ��Ԫ��ֵ��δ��ջ��ջ��Ԫ��δ�ı�
    {
        if (!isEmpty())
            return (E)this.value[this.top];
        else
            return null;
    }

    public String toString()                     //����ջ�и�Ԫ�ص��ַ�������
    {
        String str="{";  
        if (this.top!=-1)
            str += this.value[this.top].toString();
        for (int i=this.top-1; i>=0; i--)
            str += ", "+this.value[i].toString();
        return str+"} ";
    }

    public static void main(String args[])
    {
        SeqStack<String> stack = new SeqStack<String>(20);
        System.out.print("Push: ");
        char ch='a';
        for(int i=0;i<5;i++)
        {
            String str = (char)(ch+i)+"";
            stack.push(str);
            System.out.print(str+"  ");
        }    
        System.out.println("\n"+stack.toString());

        System.out.print("Pop : ");
        while(!stack.isEmpty())                  //ȫ����ջ
            System.out.print(stack.pop().toString()+"  ");
        System.out.println();
    }
}

/*
Push: a  b  c  d  e  
dataStructure.linearList.SeqStack: {e, d, c, b, a}
Pop : e  d  c  b  a  

*/