import dataStructure.linearList.SeqStack;                  //˳��ջ��
import dataStructure.linearList.LinkedStack;               //��ʽջ��

public class Expression 
{
    public static String toPostfix(String expstr)          //����expstr�ĺ�׺���ʽ
    {
        SeqStack<String> stack = new SeqStack<String>(expstr.length());   //���������ջ
        String postfix="";
        int i=0;
        while (i<expstr.length())
        {    
            char ch=expstr.charAt(i);
            switch (ch)
            {
                case '+':                                  //����������
                case '-': while (!stack.isEmpty() && !stack.get().equals("("))
                              postfix += stack.pop();
                          stack.push(ch+"");
                          i++;
                          break;
                case '*':                                  //����*��/
                case '/': while (!stack.isEmpty() && (stack.get().equals("*") || stack.get().equals("/")))
                              postfix += stack.pop();
                          stack.push(ch+"");
                          i++;
                          break;
                case '(': stack.push(ch+"");               //���������ţ���ջ
                          i++; 
                          break;
                case ')': String out = stack.pop();        //���������ţ���ջ
                          while (out!=null && !out.equals("("))  //��out==null����ʾջ��
                          {
                              postfix += out;
                              out = stack.pop();
                          }
                          i++;
                          break;
                default:  while (ch>='0' && ch<='9')        //��������
                          {
                              postfix += ch;
                              i++;
                              if (i<expstr.length())
                                  ch=expstr.charAt(i);
                              else
                                  ch='=';
                          }
                          postfix += " ";
//                          break;
            }
        }
        while (!stack.isEmpty())
            postfix += stack.pop();
        return postfix;
    }

    public static int value(String postfix)                //�����׺���ʽ��ֵ
    {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();     //����������ջ
        int i=0, result=0;
        while (i<postfix.length())                          //�������׺���ʽ�е��ַ�
        {    
            char ch=postfix.charAt(i);
            if (ch>='0' && ch<='9')                         //���������ַ�
            {
                result=0;
                while (ch!=' ')                             //�ַ���ת��Ϊ��ֵ
                {
                    result = result*10 + Integer.parseInt(ch+"");
                    i++;
                    ch = postfix.charAt(i);
                }
                i++;
                stack.push(new Integer(result));           //��������ջ
            }
            else
            {
                int y=stack.pop().intValue();
                int x=stack.pop().intValue();
                switch (ch)                                 //����������ֱ����
                {
                    case '+': result=x+y; break;
                    case '-': result=x-y; break;
                    case '*': result=x*y; break;
                    case '/': result=x/y; break;           //����
                }
                stack.push(new Integer(result));           //��������ջ
                i++;
            }
        }
        return stack.pop().intValue();                     //����������
    }
    public static void main(String args[])
    {
        String expstr="121+10*(53-49+20)/((35-25)*2+10)";
        String postfix = toPostfix(expstr);
        System.out.println("expstr=  "+expstr);
        System.out.println("postfix= "+postfix);
        System.out.println("value= "+value(postfix));
    }    
}
/*
expstr=  121+10*(53-49+20)/((35-25)*2+10)
postfix= 121 10 53 49 -20 +*35 25 -2 *10 +/+
value= 129
*/