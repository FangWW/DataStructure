//����2.4��  ��������������ĵ�����

package dataStructure.linearList;
import dataStructure.linearList.Node;
import dataStructure.linearList.HSLinkedList;

public class SortedHSLinkedList<E> extends HSLinkedList<E>//����������ĵ�������
{
    public SortedHSLinkedList() 
    {
        super();
    }
    
    public boolean add(E element)                          //����ָ������Ĵ�С�����ں���λ��
    {                                                      //�������ɹ�����true��O(n)
        if (element==null || !(element instanceof Comparable))
           return false;                                   //���ܲ���null���Comparable����
                 
        Comparable cmp = (Comparable)element;
        Node<E> front=this.head, p=front.next;             //front��p��ǰ�����
        while (p!=null && cmp.compareTo(p.data)>0)         //Ѱ�Ҳ���λ��
        {
            front = p;
            p = p.next;
        }
        front.next = new Node<E>(element, p);               //����
        if (p==null)
            this.rear=front.next;                           //β����Ҫ�޸�βָ��
        this.n++;
        return true;
    }

    public boolean remove(E element)                       //ɾ��ָ������
    {                                                      //�������ɹ�����true��O(n)
        if (element==null || !(element instanceof Comparable))
           return false;
                 
        Comparable cmp = (Comparable)element;
        Node<E> front=this.head, p=front.next;             //front��p��ǰ�����
        while (p!=null && cmp.compareTo(p.data)>0)         //Ѱ�Ҵ�ɾ���Ľ��
        {
            front = p;
            p = p.next;
        }
        if (p==null)
           return false;                                   //δ�ҵ�ָ����㣬ɾ�����ɹ�
        
        front.next = p.next;                               //ɾ��p���
        if (p==this.rear)
            this.rear=front;
        this.n--;
        return true;
    }

    public static void main(String args[])
    {
        SortedHSLinkedList<Integer> lista = new SortedHSLinkedList<Integer>();
        int n=10;
        System.out.print("insert�� ");
        for (int i=0; i<n; i++)
        {
           int k = (int) (Math.random()*100);              //���������
           if (lista.add(new Integer(k)))
               System.out.print(k+"  ");
        }
        System.out.println("\nlist: "+lista.toString());
        
        SortedHSLinkedList<Integer> listb = new SortedHSLinkedList<Integer>();
        listb.add(new Integer(1)); 
        listb.add(new Integer(50)); 
        listb.add(new Integer(100)); 
        	
        lista.merge(listb);
        lista.add(new Integer(101)); 
        System.out.println("mergelist( "+lista.length()+"): "+lista.toString());
    }
    
//��9��ϰ��
    public void merge(SortedHSLinkedList<E> list)//�鲢��������ĵ�����     
    {
        if (list==null || list.head==null)
            return;
        if (this.head==null)
        {
            this.head = list.head;
            return;
        }
        
        Node<E> front=this.head, p=front.next;   //front��p��ǰ����㣬pָ��this������ĵ�һ�����
        Node<E> q=list.head.next;                //qָ��list������ĵ�һ�����
        while (p!=null && q!=null)
        {
            if (((Comparable)p.data).compareTo((Comparable)q.data)<0)  //�Ƚ���������ǰ���ֵ
            {
                front = p;
                p = p.next;
            }
            else
            {                                    //��q�����뵽front���֮��
                front.next = q;
                q = q.next;
                front = front.next;
                front.next = p;
            }
        }
        if (q!=null)                              //list������ʣ���㲢�뵱ǰ����β
        {
           front.next=q;
           while (q.next!=null)
               q = q.next;
           this.rear = q;
        }
        this.n += list.n;   
    }
    
}

/*
�������н�����£�
insert�� 84  29  99  10  7  8  44  73  8  58  
list: (7, 8, 8, 10, 29, 44, 58, 73, 84, 99)

insert�� 49  0  7  75  27  10  50  71  16  77  
list: (0, 7, 10, 16, 27, 49, 50, 71, 75, 77)

insert�� 7  92  54  99  86  56  60  70  98  79  
list: (7, 54, 56, 60, 70, 79, 86, 92, 98, 99)


/*
�������н�����£�
insert�� 80  65  19  91  26  58  55  62  26  26  
list: {19, 26, 26, 26, 55, 58, 62, 65, 80, 91}
mergelist( 14): {1, 19, 26, 26, 26, 50, 55, 58, 62, 65, 80, 91, 100, 101}

*/

