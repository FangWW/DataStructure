//����9.1��  ��׼���������

import java.io.*;
public class KeyboardInput
{
    public static void main(String args[]) throws IOException  //�׳��쳣����Java���������
    {  
        System.out.print("Input: ");
        byte buffer[] = new byte[512];           //���ֽ�������Ϊ������
        int count = System.in.read(buffer);      //�ӱ�׼�������ж�ȡ�����ֽڵ�ָ��������������ʵ�ʶ�ȡ���ֽ���
        
        System.out.print("Output: ");
        for (int i=0;i<count;i++)                //���ֽڷ�ʽ���bufferԪ��ֵ
            System.out.print(" "+buffer[i]);
        System.out.println();
        
        for (int i=0;i<count;i++)                //���ַ���ʽ���bufferԪ��ֵ
            System.out.print((char) buffer[i]);
        System.out.println("count = "+ count);   //ʵ�ʶ�ȡ���ֽ���
    }
}

