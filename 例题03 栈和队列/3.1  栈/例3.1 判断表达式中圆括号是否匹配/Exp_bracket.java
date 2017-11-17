import dataStructure.linearList.SeqStack;
import dataStructure.linearList.LinkedStack;

public class Exp_bracket
{
    public static String isValid(String expstr)            //�ж�expstr���ʽ�е�Բ�����Ƿ�ƥ��
    {                                                      //���ش�����Ϣ
//        SeqStack<String> stack = new SeqStack<String>(expstr.length()); //������ջ
        LinkedStack<String> stack = new LinkedStack<String>(); //������ջ
        int i=0;
        while(i<expstr.length())
        {    
            char ch=expstr.charAt(i);
            i++;           
            switch(ch)
            {
                case '(': stack.push(ch+"");               //��������ջ
                          break;
                case ')': if (stack.isEmpty() || !stack.pop().equals("("))  //����������ʱ����ջ
                              return "����(";              //�жϳ�ջ�ַ��Ƿ�Ϊ������
            }    
        }

        if(stack.isEmpty())
            return "";                                     //���ؿմ���ʾû�д���
        else 
            return "����)";
    }    

    public static void main(String args[])
    {
        String expstr="((1+2)*3+4))("; 
        System.out.println(expstr+"  "+isValid(expstr));
    }
}

/*
((1+2)*3+4) 
((1+2)*3+4  ����)
((1+2)*3+4))(  ����(

*/