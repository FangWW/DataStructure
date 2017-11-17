//稀疏矩阵元素的三元组类

public class Element implements java.lang.Comparable<Element>
{
    int row;                                     //行号，默认访问权限
    int column;                                  //列号
    int value;                                   //元素值

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
    public Element(Element item)                 //复制一个三元组
    {
        this(item.row, item.column, item.value);
    }
    public String toString()                     //三元组描述字符串
    {
        return "("+row+","+column+","+value+")";
    }

    public Element toSymmetry()                  //返回对称位置矩阵元素的三元组
    {
        return new Element(this.column, this.row, this.value);
    }

    public boolean equals(Object obj)            //两个三元组比较是否相等
    {
        if (!(obj instanceof Element))
        	return false;
        Element	temp = (Element)obj;
        return this.row==temp.row && this.column==temp.column && this.value==temp.value;
    }

    public int compareTo(Element item)           //两个三元组比较大小
    {
        if (this.row<item.row || this.row==item.row && this.column<item.column)
            return -1;                           //当前三元组对象小
        if (this.row==item.row && this.column==item.column)
            return 0;                            //相等，与equals()方法含义不同
        return 1;                                //当前三元组对象大
    }
    
}

/*
 */