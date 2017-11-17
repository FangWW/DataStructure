//����10.2��  �绰����
//��	Friend���ʾ�绰���еĶ���

import java.io.Serializable;                               //���л��ӿڣ������޷���

public class Friend implements java.lang.Comparable<Friend>, java.io.Serializable  // ʵ�ֿɱȽϺ����л��ӿ�
{
    private String name;                                   //����
    private String phonecode;                              //�绰����

    public Friend(String name, String phonecode)           //���췽��
    {
        this.set(name, phonecode);
    } 

    public String getName()                                //�������
    {
        return this.name;
    }
    public String getPhonecode()                           //��õ绰����
    {
        return this.phonecode;
    }
    public void set(String name, String phonecode)         //���ó�Ա����ֵ
    {
        this.name = name;
        this.phonecode = phonecode;
    }

    public String toString() 
    {
        return "("+this.name+", �绰"+this.phonecode+")";
    }

    public int compareTo(Friend f)                         //�Ƚ����������С��ʵ��Comparable�ӿ�
    {                                                      //ָ���������Ȼ�����Լ��Ƚ���������ȵĹ���
        if (!this.name.equals(f.name))     
            return this.name.compareTo(f.name);
        else
            return this.phonecode.compareTo(f.phonecode);
    }

    public Object[] toArray()                              //�������������Ա���������ڶ���������
    {
        Object[] vars = new Object[2];
        vars[0] = this.name;
        vars[1] = this.phonecode;
        return vars;
    }
}

/*
�������˵�����¡�


 *����������Ƚ��������������£���绰���в������һ���˵������绰����
    public int compareTo(Friend f)                         //�Ƚ���������
    {                                                      //ָ���������Ȼ����
        return this.name.compareTo(f.name);
    }


//���·���δ��
    public Friend(String name)                             //���췽������
    {
        this(name, "");                                    //���ñ���Ĺ��췽��
    } 
    public Friend()
    {
        this("", "");
    }
    public Friend(Friend f)
    {
        this(f.name, f.phonecode);
    }


    public void setName(String name)
    {
        if (name==null)
            this.name = "";
        else
            this.name = name;
    }
    public void setPhonecode(String phonecode)
    {
        if (phonecode==null)
            this.phonecode = "";
        else
            this.phonecode = phonecode;
    }
    public void set(String name, String phonecode)         //���ó�Ա����ֵ
    {
        this.setName(name);
        this.setPhonecode(phonecode);
    }
    public void set(Friend f)
    {
        this.set(f.name, f.phonecode);
    }

    public boolean equals(Object obj)                      //����Object���з���
    {
        if (this==obj)
        	return true;
        	
        if (obj instanceof Friend)                         //�жϵ�ǰ�����Ƿ�����Friend��
        {
            Friend f = (Friend)obj;
            return this.name.equals(f.name) && this.phonecode.equals(f.phonecode);
        }
        return false;
    }


*/