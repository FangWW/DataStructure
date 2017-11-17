//【例7.4】  求带权图的单源最短路径。
 
public class ShortestPath                                  //求带权图的单源最短路径
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //带权有向图G7的顶点集合
        Edge edges[]={ new Edge(0,1,10), new Edge(0,3,30), new Edge(0,4,99),       //G7的边集合
                       new Edge(1,2,50), 
                       new Edge(2,4,10), 
                       new Edge(3,2,20), new Edge(3,4,60)};
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
        System.out.println("带权有向图G7，"+graph.toString());
        graph.shortestPath(0);                             //从顶点A到其他顶点的最短路径
//        graph.shortestPath(1);                               //从顶点B到其他顶点的最短路径??
    }
}
/*
程序运行结果如下：
带权有向图G7，顶点集合：{A, B, C, D, E} 
邻接矩阵:  
  0  10  ∞  30  99
  ∞  0  50  ∞  ∞
  ∞  ∞  0  ∞  10
  ∞  ∞  20  0  60
  ∞  ∞  ∞  ∞  0


s数组{1,0,0,0,0}  path数组{-1,0,-1,0,0} dist数组{0,10,2147483647,30,99}
s数组{1,1,0,0,0}  path数组{-1,0,1,0,0}  dist数组{0,10,60,30,99}
s数组{1,1,0,1,0}  path数组{-1,0,3,0,3}  dist数组{0,10,50,30,90}
s数组{1,1,1,1,0}  path数组{-1,0,3,0,2}  dist数组{0,10,50,30,60}
s数组{1,1,1,1,1}  path数组{-1,0,3,0,2}  dist数组{0,10,50,30,60}
从顶点A到其他顶点的最短路径如下：
(A,B)，路径长度为10
(A,D,C)，路径长度为50
(A,D)，路径长度为30
(A,D,C,E)，路径长度为60


*/

/*
程序运行结果如下：

*/ 

