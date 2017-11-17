import dataStructure.linearList.SeqStack;
import dataStructure.linearList.LinkedStack;

public class Exp_bracket
{
    public static String isValid(String expstr)            //判断expstr表达式中的圆括号是否匹配
    {                                                      //返回错误信息
//        SeqStack<String> stack = new SeqStack<String>(expstr.length()); //创建空栈
        LinkedStack<String> stack = new LinkedStack<String>(); //创建空栈
        int i=0;
        while(i<expstr.length())
        {    
            char ch=expstr.charAt(i);
            i++;           
            switch(ch)
            {
                case '(': stack.push(ch+"");               //左括号入栈
                          break;
                case ')': if (stack.isEmpty() || !stack.pop().equals("("))  //遇见右括号时，出栈
                              return "期望(";              //判断出栈字符是否为左括号
            }    
        }

        if(stack.isEmpty())
            return "";                                     //返回空串表示没有错误
        else 
            return "期望)";
    }    

    public static void main(String args[])
    {
        String expstr="((1+2)*3+4))("; 
        System.out.println(expstr+"  "+isValid(expstr));
    }
}

/*
((1+2)*3+4) 
((1+2)*3+4  期望)
((1+2)*3+4))(  期望(

*/