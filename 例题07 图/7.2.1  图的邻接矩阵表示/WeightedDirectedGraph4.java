//【例7.2】  带权有向图的构造、插入及删除操作。

public class WeightedDirectedGraph4                        //带权有向图
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //带权有向图G4的顶点集合
        Edge edges[]={ new Edge(0,1,5), new Edge(0,3,2),   //G4的出边集合
                       new Edge(1,0,6), new Edge(1,2,7),
                       new Edge(2,4,3),
                       new Edge(3,2,8), new Edge(3,4,9)};
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
//        AdjListGraph<String> graph = new AdjListGraph<String>(vertices, edges);
        System.out.println("带权有向图G4，"+graph.toString());
/*
        System.out.println("删除顶点v2，"+graph.removeVertex(2)+" ");
        System.out.println("删除边(v2,v3)，"+graph.removeEdge(2,3)+" ");
        System.out.println("带权有向图G4，"+graph.toString());

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
带权有向图G4，顶点集合：{A, B, C, D, E} 
邻接矩阵:  
  0  5  ∞  2  ∞
  6  0  7  ∞  ∞
  ∞  ∞  0  ∞  3
  ∞  ∞  8  0  9
  ∞  ∞  ∞  ∞  0

深度优先遍历非连通图：
{ A B C E D } 
{ B A D C E } 
{ C E } { D } { A B } 
{ D C E } { A B } 
{ E } { A B C D } 
广度优先遍历非连通图：
{ A B D C E } 
{ B A C D E } 
{ C E } { D } { A B } 
{ D C E } { A B } 
{ E } { A B D C }       

s数组{0,0,0,0,0}  path数组{-1,0,-1,0,-1}    dist数组{0,5,2147483647,2,2147483647}
s数组{1,0,0,1,0}  path数组{-1,0,3,0,3}  dist数组{0,5,10,2,11}
s数组{1,1,0,1,0}  path数组{-1,0,3,0,3}  dist数组{0,5,10,2,11}
s数组{1,1,1,1,0}  path数组{-1,0,3,0,3}  dist数组{0,5,10,2,11}
s数组{1,1,1,1,1}  path数组{-1,0,3,0,3}  dist数组{0,5,10,2,11}
从顶点A到其他顶点的最短路径如下：
(A,B)，路径长度为5
(A,D,C)，路径长度为10
(A,D)，路径长度为2
(A,D,E)，路径长度为11

s数组{0,0,0,0,0}  path数组{1,-1,1,-1,-1}    dist数组{6,0,7,2147483647,2147483647}
s数组{1,1,0,0,0}  path数组{1,-1,1,0,-1} dist数组{6,0,7,8,2147483647}
s数组{1,1,1,0,0}  path数组{1,-1,1,0,2}  dist数组{6,0,7,8,10}
s数组{1,1,1,1,0}  path数组{1,-1,1,0,2}  dist数组{6,0,7,8,10}
s数组{1,1,1,1,1}  path数组{1,-1,1,0,2}  dist数组{6,0,7,8,10}
从顶点B到其他顶点的最短路径如下：
(B,C)，路径长度为7
(B,A,D)，路径长度为8
(B,C,E)，路径长度为10
(B,A)，路径长度为6

删除顶点v2，true 
删除边(v2,v3)，true 
带权有向图G4，顶点集合：{A, B, D, E} 
邻接矩阵:  
  0  5  2  ∞
  6  0  ∞  ∞
  ∞  ∞  0  ∞
  ∞  ∞  ∞  0

深度优先遍历非连通图：
{ A B D } { E } 
{ B A D } { E } 
{ D } { E } { A B } 
{ E } { A B D } 
广度优先遍历非连通图：
{ A B D } { E } 
{ B A D } { E } 
{ D } { E } { A B } 
{ E } { A B D } 

*/

/*
程序运行结果如下：
带权有向图G4，顶点集合：{A, B, C, D, E} 
出边表：
 {(0,1,5), (0,3,2)}
{(1,0,6), (1,2,7)}
{(2,4,3)}
{(3,2,8), (3,4,9)}
{}

深度优先遍历非连通图：
{ A B C E D } 
{ B A D C E } 
{ C E } { D } { A B } 
{ D C E } { A B } 
{ E } { A B C D } 
广度优先遍历非连通图：
{ A B D C E } 
{ B A C D E } 
{ C E } { D } { A B } 
{ D C E } { A B } 
{ E } { A B D C } 

删除顶点v2，true 
删除边(v2,v3)，true 
带权有向图G4，顶点集合：{A, B, D, E} 
出边表：
 {(0,1,5), (0,2,2)}
{(1,0,6), (1,2,7)}
{(2,2,8)}
{}

深度优先遍历非连通图：
{ A B D } { E } 
{ B A D } { E } 
{ D } { E } { A B } 
{ E } { A B D } 
广度优先遍历非连通图：
{ A B D } { E } 
{ B A D } { E } 
{ D } { E } { A B } 
{ E } { A B D } 

*/