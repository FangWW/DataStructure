//���Ա��˳��洢�ṹ
//���飬����Ա����n��Ϊlen

package dataStructure.linearList;
import dataStructure.linearList.LList;
import java.util.Iterator;                       //����������ӿ�

public class SeqList<E> extends AbstractList<E> implements LList<E>    //˳����࣬ʵ�����Ա�ӿ�
{
    private Object[] table;                      //�������飬˽�г�Ա
    private int n;                               //˳�����
    
    public SeqList(int capacity)                 //���췽��������ָ�������Ŀձ�
    {
        this.table = new Object[Math.abs(capacity)];  //Math.abs(i)���ز����ľ���ֵ
        this.n = 0;
    }
    public SeqList()                             //ָ���ձ��Ĭ������
    {
        this(16);
    }

    public boolean isEmpty()                     //�ж�˳����Ƿ�Ϊ�գ����շ���true
    {                                            //O(1)
        return this.n==0;
    }

    public int length()                          //����˳����ȣ�O(1)
    {
        return this.n;
    }

    public E get(int index)                      //����indexλ�õĶ���index��ֵΪ0
    {                                            //O(1)����indexָ�������Ч�򷵻�null
        if (index>=0 && index<this.n)
            return (E)this.table[index];         //ʹ����δ�����򲻰�ȫ�Ĳ���
//            return table[index];               //����,����Object
        return null;
    }

    public E set(int index, E element)           //����indexλ�õĶ���Ϊelement��O(1)
    {                                            //�������ɹ�����ԭ���󣬷��򷵻�null
        if (index>=0 && index<this.n && element!=null)
        {
            E old = (E)this.table[index];
            this.table[index] = element;
            return old;
        }
        return null;
    }

    public boolean add(int index, E element)     //��indexλ�ò���element����O(n)
    {                                            //���ܲ���null���������ɹ�����true
        if (element==null)
           return false;                         //�������null
    
        if (this.n==table.length)                //��������������Ҫ����˳�������
        {
            Object[] temp = this.table;         
            this.table = new Object[temp.length*2];    //��������һ���������������
            for (int i=0; i<temp.length; i++)    //��������Ԫ�أ�O(n)
                this.table[i] = temp[i];
        }
             
        if (index<0)                             //�±��ݴ�
            index=0;
        if (index>this.n)
            index = this.n;

        for (int j=this.n-1; j>=index; j--)      //Ԫ�غ��ƣ�ƽ���ƶ�n/2
            this.table[j+1] = this.table[j];
        this.table[index] = element;
        this.n++;
        return true;
    }

    public boolean add(E element)                //��˳���������element����
    {
        return add(this.n, element);
    }
    
    public E remove(int index)                   //��ȥindexλ�õĶ���O(n)
    {                                            //�������ɹ����ر���ȥ���󣬷��򷵻�null
        if (this.n!=0 && index>=0 && index<this.n) 
        {
            E old = (E)this.table[index];
            for (int j=index; j<this.n-1; j++)   //Ԫ��ǰ�ƣ�ƽ���ƶ�n/2
                this.table[j] = this.table[j+1];
            this.table[this.n-1]=null;
            this.n--;
            return old;                          //�������ɹ����ر���ȥ����
        }
        return null;                             //δ�ҵ�ɾ�����󣬲������ɹ�
    }
/*
    public void clear()                          //������Ա�
    {
        if (this.n!=0) 
        {
            for (int i=0; i<this.n; i++)
                this.table[i] = null;
            this.n=0;
        }
    }
*/
    public void clear()                          //������Ա�
    {
        this.n=0;
    }

    public String toString()                     //������ʾ���Ա�����Ԫ��ֵ���ַ�������ʽΪ{,}
    {
        String str="(";
        if (this.n!=0)
        {
            for (int i=0; i<this.n-1; i++)
                str += this.table[i].toString()+", ";
            str += this.table[this.n-1].toString();
        }
        return str+") ";
    }
//����ʵ��LList�ӿڣ���2������
/*
���У�Ч��ͬ��
    public String toString()                     //������ʾ���Ա�����Ԫ��ֵ���ַ�������ʽΪ[,] 
    {
        String str="(";
        if (this.n()!=0)
        {
            for(int i=0; i<this.n()-1; i++)
                str += this.get(i).toString()+", ";
            str += this.get(this.n()-1).toString();
        }
        return str+")";
    }
*/

//����2.4 ����������
    public Iterator<E> iterator()                //����һ������������
    {
        return new Itr();
    }

    private class Itr implements Iterator<E>     //˽���ڲ��࣬ʵ�ֵ������ӿ�
    {
        int cursor = 0;                          //Ԫ������

        public boolean hasNext()                 //���к��Ԫ�أ�����true
        {
            return cursor != n;                  //n���ⲿ��ĳ�Ա��������ʾ˳�����
        }

        public E next()                          //���غ��Ԫ��
        {
            if (cursor != n)
            {
                E next = get(cursor);
                cursor++;
                return next;
            }
            return null;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();     //��֧�ָò������׳��쳣
        }
    }//�ڲ���Itr����


//���µ�8�� 8.2.1 ˳�����

    public int indexOf(E element)               //˳�����ָ������
    {                                           //�����ҳɹ������״γ���λ�ã����򷵻�-1
        if (element!=null)
            for (int i=0; i<this.n; i++)
                if (this.table[i].equals(element))//�������equals()�����Ƚ��Ƿ����
                    return i;
        return -1;
    }

    public boolean contain(E element)           //�Բ��ҽ���ж����Ա��Ƿ����ָ������
    {                                           //����������true�����򷵻�false
        return this.indexOf(element)>=0;
    }


//�����ǵ�8�� 8.2.1 ˳�����ϰ��
    public int lastIndexOf(E element)           //����obj����������λ�ã���δ�ҵ�����-1
    {
        if (obj!=null)
            for (int i=this.n-1; i>=0; i--)
                if (obj.equals(this.table[i]))
                    return i;
        return -1;
    }

    public boolean remove(E element)            //��ȥ�״γ��ֵ�obj�����������ɹ�����true
    {
        if (this.n!=0 && obj!=null)
            return this.remove(this.indexOf(obj))!=null;
        return false;
    }
    
    public boolean removeAll(E element)         //��ȥ���Ա�������obj����
    {
        boolean done=false;
        if (this.n!=0 && obj!=null)
        {
            int i=0;
            while (i<this.n)
                if (obj.equals(this.table[i]))
                {
                    this.remove(i);
                    done = true;
                }
                else
                    i++;
        }
        return done;
    }

    public boolean replace(Object obj, E element)//���״γ��ֵ�obj�����滻Ϊelement����
    {                                            //�������ɹ�����true��O(n/2)
        if (obj!=null && element!=null)
        {
            int i = this.indexOf(obj);           //����obj�����״γ���λ��
            if (i!=-1)
            {
                this.table[i] = element;
                return true;
            }
        }
        return false;
    }

    public boolean replaceAll(Object obj, E element)   //�����Ա�������obj�����滻Ϊelement����
    {
        boolean done=false;
        if (obj!=null && element!=null)
            for (int i=0; i<this.n; i++)
                if (obj.equals(this.table[i]))
                {
                    this.table[i] = element;
                    done = true;
                }
        return done;
    }

    public boolean equals(Object obj)            //�Ƚ�����˳�������Ƿ����
    {                                            //O(n)
        if (this==obj)
            return true;
        
        if (obj instanceof SeqList)
        {
            SeqList<E> list = (SeqList<E>)obj;
            for (int i=0; i<this.length(); i++)
                if (!(this.get(i).equals(list.get(i))))
                    return false; 
            return true;
        }
        return false;
    }
}
