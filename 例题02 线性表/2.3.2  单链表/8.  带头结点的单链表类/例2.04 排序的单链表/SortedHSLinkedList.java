//【例2.4】  建立按升序排序的单链表。

package dataStructure.linearList;
import dataStructure.linearList.Node;
import dataStructure.linearList.HSLinkedList;

public class SortedHSLinkedList<E> extends HSLinkedList<E>//按升序排序的单链表类
{
    public SortedHSLinkedList() 
    {
        super();
    }
    
    public boolean add(E element)                          //根据指定对象的大小插入在合适位置
    {                                                      //若操作成功返回true，O(n)
        if (element==null || !(element instanceof Comparable))
           return false;                                   //不能插入null或非Comparable对象
                 
        Comparable cmp = (Comparable)element;
        Node<E> front=this.head, p=front.next;             //front是p的前驱结点
        while (p!=null && cmp.compareTo(p.data)>0)         //寻找插入位置
        {
            front = p;
            p = p.next;
        }
        front.next = new Node<E>(element, p);               //插入
        if (p==null)
            this.rear=front.next;                           //尾插入要修改尾指针
        this.n++;
        return true;
    }

    public boolean remove(E element)                       //删除指定对象
    {                                                      //若操作成功返回true，O(n)
        if (element==null || !(element instanceof Comparable))
           return false;
                 
        Comparable cmp = (Comparable)element;
        Node<E> front=this.head, p=front.next;             //front是p的前驱结点
        while (p!=null && cmp.compareTo(p.data)>0)         //寻找待删除的结点
        {
            front = p;
            p = p.next;
        }
        if (p==null)
           return false;                                   //未找到指定结点，删除不成功
        
        front.next = p.next;                               //删除p结点
        if (p==this.rear)
            this.rear=front;
        this.n--;
        return true;
    }

    public static void main(String args[])
    {
        SortedHSLinkedList<Integer> lista = new SortedHSLinkedList<Integer>();
        int n=10;
        System.out.print("insert： ");
        for (int i=0; i<n; i++)
        {
           int k = (int) (Math.random()*100);              //产生随机数
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
    
//第9章习题
    public void merge(SortedHSLinkedList<E> list)//归并两条排序的单链表     
    {
        if (list==null || list.head==null)
            return;
        if (this.head==null)
        {
            this.head = list.head;
            return;
        }
        
        Node<E> front=this.head, p=front.next;   //front是p的前驱结点，p指向this单链表的第一个结点
        Node<E> q=list.head.next;                //q指向list单链表的第一个结点
        while (p!=null && q!=null)
        {
            if (((Comparable)p.data).compareTo((Comparable)q.data)<0)  //比较两个链表当前结点值
            {
                front = p;
                p = p.next;
            }
            else
            {                                    //将q结点插入到front结点之后
                front.next = q;
                q = q.next;
                front = front.next;
                front.next = p;
            }
        }
        if (q!=null)                              //list链表中剩余结点并入当前链表尾
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
程序运行结果如下：
insert： 84  29  99  10  7  8  44  73  8  58  
list: (7, 8, 8, 10, 29, 44, 58, 73, 84, 99)

insert： 49  0  7  75  27  10  50  71  16  77  
list: (0, 7, 10, 16, 27, 49, 50, 71, 75, 77)

insert： 7  92  54  99  86  56  60  70  98  79  
list: (7, 54, 56, 60, 70, 79, 86, 92, 98, 99)


/*
程序运行结果如下：
insert： 80  65  19  91  26  58  55  62  26  26  
list: {19, 26, 26, 26, 55, 58, 62, 65, 80, 91}
mergelist( 14): {1, 19, 26, 26, 26, 50, 55, 58, 62, 65, 80, 91, 100, 101}

*/

