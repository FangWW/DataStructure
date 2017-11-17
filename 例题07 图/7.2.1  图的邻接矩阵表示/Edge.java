//带权图的边类

public class Edge implements Comparable<Edge>    //带权值的边类
{
    public int start;                            //边的起点序号
    public int dest;                             //边的终点序号
    public int weight;                           //边的权值
    
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

    public int compareTo(Edge e)                 //约定两条边比较大小的规则
    {
        if (this.start!=e.start)
            return this.start - e.start;
        else
            return this.dest - e.dest;
    }
    
}   
 