//��Ȩͼ�ı���

public class Edge implements Comparable<Edge>    //��Ȩֵ�ı���
{
    public int start;                            //�ߵ�������
    public int dest;                             //�ߵ��յ����
    public int weight;                           //�ߵ�Ȩֵ
    
    public Edge(int start, int dest, int weight)
    {
        this.start = start;
        this.dest = dest;
        this.weight = weight;
    }

    public String toString() 
    {
        return "("+start+","+dest+","+weight+")";
    }

    public int compareTo(Edge e)                 //Լ�������߱Ƚϴ�С�Ĺ���
    {
        if (this.start!=e.start)
            return this.start - e.start;
        else
            return this.dest - e.dest;
    }
    
}   
 