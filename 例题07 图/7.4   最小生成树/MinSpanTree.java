//【例7.3】  构造带权无向图G6的最小生成树。
 
public class MinSpanTree                                   //构造带权无向图的最小生成树
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //带权无向图G6的顶点集合
        Edge edges[]={ new Edge(0,1,25), new Edge(0,2,4), new Edge(0,3,22),       //G6的边集合
                       new Edge(1,0,25), new Edge(1,2,16), new Edge(1,4,3),
                       new Edge(2,0,4), new Edge(2,1,16), new Edge(2,3,18), new Edge(2,4,7),
                       new Edge(3,0,22), new Edge(3,2,18), new Edge(3,4,9), 
                       new Edge(4,1,3), new Edge(4,2,7), new Edge(4,3,9)};
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
        System.out.println("带权无向图G6，"+graph.toString());

        AbstractGraph<String> minspantree = graph.minSpanTree_prim();
        System.out.println("\n最小生成树，"+minspantree.toString());
    }
}
/*
程序运行结果如下：
带权无向图G6，顶点集合：{A, B, C, D, E} 
邻接矩阵:  
  0  25  4  22  ∞
  25  0  16  ∞  3
  4  16  0  18  7
  22  ∞  18  0  9
  ∞  3  7  9  0

mst数组初值：(0,1,25)(0,2,4)(0,3,22)(0,4,2147483647)
mst数组：(0,2,4)(2,1,16)(2,3,18)(2,4,7)
mst数组：(0,2,4)(2,4,7)(4,3,9)(4,1,3)
mst数组：(0,2,4)(2,4,7)(4,1,3)(4,3,9)
mst数组：(0,2,4)(2,4,7)(4,1,3)(4,3,9)
最小生成树，顶点集合：{A, B, C, D, E} 
邻接矩阵:  
  0  ∞  4  ∞  ∞
  ∞  0  ∞  ∞  ∞
  ∞  ∞  0  ∞  7
  ∞  ∞  ∞  0  ∞
  ∞  3  ∞  9  0


*/

/*
程序运行结果如下：
带权无向图G6，顶点集合：{A, B, C, D, E} 
邻接矩阵:  
  0  5  4  12  ∞
  5  0  6  ∞  7
  4  6  0  8  3
  12  ∞  8  0  9
  ∞  7  3  9  0

mst数组：(0,1,5)(0,2,4)(0,3,12)(0,4,2147483647)
mst数组：(0,2,4)(0,1,5)(2,3,8)(2,4,3)
mst数组：(0,2,4)(2,4,3)(2,3,8)(0,1,5)
mst数组：(0,2,4)(2,4,3)(0,1,5)(2,3,8)

最小生成树，顶点集合：{A, B, C, D, E} 
邻接矩阵:  
  0  5  4  ∞  ∞
  ∞  0  ∞  ∞  ∞
  ∞  ∞  0  8  3
  ∞  ∞  ∞  0  ∞
  ∞  ∞  ∞  ∞  0

*/ 

