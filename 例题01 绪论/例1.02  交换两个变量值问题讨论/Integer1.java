//�����������������ۡ�

public final class Integer1
{
//    private final int value;                   //java.lang.Integer���е�ֵ�ǳ���
    private int value;
    
    public Integer1(int value)                   //���췽��
    {
        this.value = value;
    }
    
    public int intValue()                        //Integer���еķ���
    {
        return value;
    }

    public void setValue(int value)              //�����ӵķ���
    {
        this.value = value;
    }


    public static void swap1(Integer x, Integer y) //����
    {
        Integer temp=x;                          //��������֮��ĸ�ֵ�����ø�ֵ�����ݵ������ã��������ĵ�ַ
        x=y;                                     //�ı���ʽ����x�����ã���δ�ܸı�ʵ�ʲ�����ֵ��
        y=temp;                                  //�൱�ڸı���ָ���ֵ����δ�ı�ͨ��ָ��ָ��ı���ֵ
        System.out.println("x = "+x.intValue());
        System.out.println("y = "+y.intValue());
    }                                            //�����ˣ����ı��ֵû�д���

    public static void swap2(Integer1 x, Integer1 y) //����
    {
        int temp = x.intValue();                 //ȡֵ
        x.setValue(y.intValue());                //ͨ�����øı�ʵ�ʲ��������ֵ
        y.setValue(temp);
    }

    public static void main(String args[]) 
    {
        Integer intobj_a = new Integer(1);
        Integer intobj_b = new Integer(2);
        swap1(intobj_a,intobj_b);
        System.out.println("intobj_a = "+intobj_a.toString());//.intValue());
        System.out.println("intobj_b = "+intobj_b.intValue());
        
        Integer1 intobj_i = new Integer1(1);
        Integer1 intobj_j = new Integer1(2);
        swap2(intobj_i,intobj_j);
        System.out.println("intobj_i = "+intobj_i.intValue());
        System.out.println("intobj_j = "+intobj_j.intValue());
        
    }
}

/*
�������н�����£�
x = 2
y = 1
intobj_a = 1
intobj_b = 2
intobj_i = 2
intobj_j = 1

*/
