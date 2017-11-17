//带权无向图
 
public class WeightedUndiGraph3                            //带权无向图
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //带权无向图G3的顶点集合
        Edge edges[]={ new Edge(0,1,5), new Edge(0,3,2),   //G3的边集合
                       new Edge(1,0,5), new Edge(1,2,7), new Edge(1,3,6),
                       new Edge(2,1,7), new Edge(2,3,8), new Edge(2,4,3),
                       new Edge(3,0,2), new Edge(3,1,6), new Edge(3,2,8), new Edge(3,4,9), 
                       new Edge(4,2,3), new Edge(4,3,9)};
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
//        AdjListGraph<String> graph = new AdjListGraph<String>(vertices, edges);
        System.out.println("带权无向图G3，"+graph.toString());

//        System.out.println("删除顶点v2，"+graph.removeVertex(2)+" ");
//        System.out.println("删除边(v2,v3)，"+(graph.removeEdge(2,3) && graph.removeEdge(3,2))+" ");
//        System.out.println(graph.toString());
/*
        System.out.println("深度优先遍历非连通图：");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.DFSTraverse(i);

        System.out.println("广度优先遍历非连通图：");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.BFSTraverse(i);
*/
        graph.shortestPath(1);                  //最短路径

    }
}


/*
程序运行结果如下：
带权无向图G3，顶点集合：{A, B, C, D, E} 
邻接矩阵:  
  0  5  ∞  2  ∞
  5  0  7  6  ∞
  ∞  7  0  8  3
  2  6  8  0  9
  ∞  ∞  3  9  0

深度优先遍历非连通图：
{ A B C D E } 
{ B A D C E } 
{ C B A D E } 
{ D A B C E } 
{ E C B A D } 
广度优先遍历非连通图：
{ A B D C E } 
{ B A C D E } 
{ C B D E A } 
{ D A B C E } 
{ E C D B A } 

从顶点B到其他顶点的最短路径如下：
(B,C)，路径长度为7
(B,D)，路径长度为6
(B,C,E)，路径长度为10
(B,A)，路径长度为5

删除顶点v2，true 
删除边(v2,v3)，true
顶点集合：{A, B, D, E} 
邻接矩阵:  
  0  5  2  ∞
  5  0  6  ∞
  2  6  0  ∞
  ∞  ∞  ∞  0

深度优先遍历非连通图：
{ A B D } { E } 
{ B A D } { E } 
{ D A B } { E } 
{ E } { A B D } 
广度优先遍历非连通图：
{ A B D } { E } 
{ B A D } { E } 
{ D A B } { E } 
{ E } { A B D } 

*/

/*
程序运行结果如下：
带权无向图G3，顶点集合：{A, B, C, D, E} 
出边表：
 {(0,1,5), (0,3,2)}
{(1,0,5), (1,2,7), (1,3,6)}
{(2,1,7), (2,3,8), (2,4,3)}
{(3,0,2), (3,1,6), (3,2,8), (3,4,9)}
{(4,2,3), (4,3,9)}

深度优先遍历非连通图：
{ A B C D E } 
{ B A D C E } 
{ C B A D E } 
{ D A B C E } 
{ E C B A D } 
广度优先遍历非连通图：



删除顶点v2，true 
删除边(v2,v3)，true 
顶点集合：{A, B, D, E} 
出边表：
 {(0,1,5), (0,2,2)}
{(1,0,5), (1,2,6)}
{(2,0,2), (2,1,6)}
{}

深度优先遍历非连通图：

广度优先遍历非连通图：


*/ 

