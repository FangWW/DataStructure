import dataStructure.linearList.SeqList;                   //顺序表
import dataStructure.linearList.SeqQueue;                  //顺序队列类
import dataStructure.linearList.LinkedQueue;               //链式队列类

public class PrimeRing 
{
    public PrimeRing(int n) 
    {
        SeqList<Integer> ring = new SeqList<Integer>(n);   //创建一个顺序表存储素数环
        ring.add(new Integer(1));                          //1添加到素数环中

//        SeqQueue<Integer> q = new SeqQueue<Integer>(n);   //创建一个队列q
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();   //创建一个队列q
        for (int i=2; i<=n; i++)                              //2～n全部入队
            q.enqueue(new Integer(i));
        System.out.println(q.toString());
 
        int i=0;
        while (!q.isEmpty()) 
        {
            int k = q.dequeue().intValue();                //出队
            System.out.print("dequeue: "+k+"\t");
            if (isPrime(ring.get(i)+k))                     //判断是否为素数
            {
                i++;
                ring.add(new Integer(k));                  //k添加到素数环中
            }
            else
                q.enqueue(new Integer(k));                 //k再次入队
            System.out.println("队列: "+q.toString());
        }
        System.out.println("素数环: "+ring.toString());
    }
    
    public boolean isPrime(int k)                          //判断k是否为素数
    {
        if (k==2)
            return true;

        if (k<2 || k>2 && k%2==0)
            return false;

        int j=(int)Math.sqrt(k);                       //Math.sqrt(k)返回k的平方根值
        if (j%2==0)
            j--;                                       //获得测试范围内的最大奇数
        while (j>2 && k%j!=0)
            j-=2;
        return j<2;
    }
    public static void main(String args[])
    {
         new PrimeRing(10);
    }
}

/*
 {2, 3, 4, 5, 6, 7, 8, 9, 10} 
dequeue: 2  队列:  {3, 4, 5, 6, 7, 8, 9, 10} 
dequeue: 3  队列:  {4, 5, 6, 7, 8, 9, 10} 
dequeue: 4  队列:  {5, 6, 7, 8, 9, 10} 
dequeue: 5  队列:  {6, 7, 8, 9, 10, 5} 
dequeue: 6  队列:  {7, 8, 9, 10, 5, 6} 
dequeue: 7  队列:  {8, 9, 10, 5, 6} 
dequeue: 8  队列:  {9, 10, 5, 6, 8} 
dequeue: 9  队列:  {10, 5, 6, 8, 9} 
dequeue: 10 队列:  {5, 6, 8, 9} 
dequeue: 5  队列:  {6, 8, 9, 5} 
dequeue: 6  队列:  {8, 9, 5, 6} 
dequeue: 8  队列:  {9, 5, 6, 8} 
dequeue: 9  队列:  {5, 6, 8} 
dequeue: 5  队列:  {6, 8, 5} 
dequeue: 6  队列:  {8, 5, 6} 
dequeue: 8  队列:  {5, 6} 
dequeue: 5  队列:  {6} 
dequeue: 6  队列:  {} 
素数环: (1, 2, 3, 4, 7, 10, 9, 8, 5, 6)


*/