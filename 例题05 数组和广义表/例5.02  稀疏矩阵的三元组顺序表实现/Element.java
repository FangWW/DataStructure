//ϡ�����Ԫ�ص���Ԫ����

public class Element implements java.lang.Comparable<Element>
{
    int row;                                     //�кţ�Ĭ�Ϸ���Ȩ��
    int column;                                  //�к�
    int value;                                   //Ԫ��ֵ

    public Element(int row,int column,int value)
    {
        this.row = row;
        this.column = column;
        this.value = value;
    }
    public Element()
    {
        this(0,0,0);
    }
    public Element(Element item)                 //����һ����Ԫ��
    {
        this(item.row, item.column, item.value);
    }
    public String toString()                     //��Ԫ�������ַ���
    {
        return "("+row+","+column+","+value+")";
    }

    public Element toSymmetry()                  //���ضԳ�λ�þ���Ԫ�ص���Ԫ��
    {
        return new Element(this.column, this.row, this.value);
    }

    public boolean equals(Object obj)            //������Ԫ��Ƚ��Ƿ����
    {
        if (!(obj instanceof Element))
        	return false;
        Element	temp = (Element)obj;
        return this.row==temp.row && this.column==temp.column && this.value==temp.value;
    }

    public int compareTo(Element item)           //������Ԫ��Ƚϴ�С
    {
        if (this.row<item.row || this.row==item.row && this.column<item.column)
            return -1;                           //��ǰ��Ԫ�����С
        if (this.row==item.row && this.column==item.column)
            return 0;                            //��ȣ���equals()�������岻ͬ
        return 1;                                //��ǰ��Ԫ������
    }
    
}

/*
 */