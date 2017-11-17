package dataStructure.linearList;
import dataStructure.linearList.QQueue;

public class SeqQueue<E> implements QQueue<E>    //˳��ѭ��������
{
    private Object value[];                      //�洢���е�����Ԫ��
    private int front, rear;                     //front��rearΪ����ͷβ�±�

    public SeqQueue(int capacity)                //����ָ�������Ŀն���
    {
        this.value = new Object[Math.abs(capacity)];
        this.front = this.rear = 0;
    }
    public SeqQueue()                            //����Ĭ�������Ŀն���
    {
        this(10);
    }

    public boolean isEmpty()                     //�ж϶����Ƿ�գ����շ���true
    {
        return this.front==this.rear;
    } 

    public boolean enqueue(E element)            //Ԫ��element��ӣ��������ɹ�����true
    {
        if (element==null)
           return false;                         //�ն���null���������

        if (this.front==(this.rear+1) % this.value.length) //��������������������
        {
            Object[] temp = this.value;         
            this.value = new Object[temp.length*2];
            int i=this.front, j=0;
            while (i!=this.rear)                 //������Ԫ�ش��򣬸�������Ԫ��
            {
                this.value[j] = temp[i];
                i = (i+1) % temp.length;
                j++;
            }
            this.front = 0;
            this.rear = j;
        }

        this.value[this.rear] = element;
        this.rear = (this.rear+1) % this.value.length;
        return true;
    } 

    public E dequeue()                           //���ӣ����ص�ǰ��ͷԪ�أ������пշ���null 
    {
        if(!isEmpty())                           //���в���
        {
            E temp = (E)this.value[this.front];  //ȡ�ö�ͷԪ��
            this.front = (this.front+1) % this.value.length;
            return temp;
        }
        return null;
    }

    public String toString()                     //���ض����и�Ԫ�ص��ַ�������
    {
        String str=" {";
        if (!isEmpty())
        {
            str += this.value[this.front].toString();
            int i=(this.front+1) % this.value.length;
            while(i!=this.rear)
            {
                str += ", "+this.value[i].toString();
                i=(i+1) % this.value.length;
            }
        }
        return str+"} ";
    }

    public static void main(String args[])
    {
        SeqQueue<Integer> que = new SeqQueue<Integer>(5);
        que.enqueue(new Integer(10)); 
        que.enqueue(new Integer(20)); 
        System.out.println("dequeue : "+que.dequeue().toString()+"  "+que.dequeue().toString()+"  ");
        System.out.println(que.toString());
        que.enqueue(new Integer(30)); 
        que.enqueue(new Integer(40)); 
        que.enqueue(new Integer(50)); 
        que.enqueue(new Integer(60)); 
        System.out.println(que.toString());
            
        que.enqueue(new Integer(70)); 
        System.out.println(que.toString());
    }
}

/*
dequeue : 10  20  
 []
 [30, 40, 50, 60]
 [30, 40, 50, 60, 70]

*/
