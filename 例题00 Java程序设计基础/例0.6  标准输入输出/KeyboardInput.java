//【例9.1】  标准输入输出。

import java.io.*;
public class KeyboardInput
{
    public static void main(String args[]) throws IOException  //抛出异常交由Java虚拟机处理
    {  
        System.out.print("Input: ");
        byte buffer[] = new byte[512];           //以字节数组作为缓冲区
        int count = System.in.read(buffer);      //从标准输入流中读取若干字节到指定缓冲区，返回实际读取的字节数
        
        System.out.print("Output: ");
        for (int i=0;i<count;i++)                //按字节方式输出buffer元素值
            System.out.print(" "+buffer[i]);
        System.out.println();
        
        for (int i=0;i<count;i++)                //按字符方式输出buffer元素值
            System.out.print((char) buffer[i]);
        System.out.println("count = "+ count);   //实际读取的字节数
    }
}

