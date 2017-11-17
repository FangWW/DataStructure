//����7.2��  ��Ȩ����ͼ�Ĺ��졢���뼰ɾ��������

public class WeightedDirectedGraph4                        //��Ȩ����ͼ
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //��Ȩ����ͼG4�Ķ��㼯��
        Edge edges[]={ new Edge(0,1,5), new Edge(0,3,2),   //G4�ĳ��߼���
                       new Edge(1,0,6), new Edge(1,2,7),
                       new Edge(2,4,3),
                       new Edge(3,2,8), new Edge(3,4,9)};
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
//        AdjListGraph<String> graph = new AdjListGraph<String>(vertices, edges);
        System.out.println("��Ȩ����ͼG4��"+graph.toString());
/*
        System.out.println("ɾ������v2��"+graph.removeVertex(2)+" ");
        System.out.println("ɾ����(v2,v3)��"+graph.removeEdge(2,3)+" ");
        System.out.println("��Ȩ����ͼG4��"+graph.toString());

        System.out.println("������ȱ�������ͨͼ��");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.DFSTraverse(i);

        System.out.println("������ȱ�������ͨͼ��");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.BFSTraverse(i);
*/
        graph.shortestPath(1);                  //���·��
    }
}

/*
�������н�����£�
��Ȩ����ͼG4�����㼯�ϣ�{A, B, C, D, E} 
�ڽӾ���:  
  0  5  ��  2  ��
  6  0  7  ��  ��
  ��  ��  0  ��  3
  ��  ��  8  0  9
  ��  ��  ��  ��  0

������ȱ�������ͨͼ��
{ A B C E D } 
{ B A D C E } 
{ C E } { D } { A B } 
{ D C E } { A B } 
{ E } { A B C D } 
������ȱ�������ͨͼ��
{ A B D C E } 
{ B A C D E } 
{ C E } { D } { A B } 
{ D C E } { A B } 
{ E } { A B D C }       

s����{0,0,0,0,0}  path����{-1,0,-1,0,-1}    dist����{0,5,2147483647,2,2147483647}
s����{1,0,0,1,0}  path����{-1,0,3,0,3}  dist����{0,5,10,2,11}
s����{1,1,0,1,0}  path����{-1,0,3,0,3}  dist����{0,5,10,2,11}
s����{1,1,1,1,0}  path����{-1,0,3,0,3}  dist����{0,5,10,2,11}
s����{1,1,1,1,1}  path����{-1,0,3,0,3}  dist����{0,5,10,2,11}
�Ӷ���A��������������·�����£�
(A,B)��·������Ϊ5
(A,D,C)��·������Ϊ10
(A,D)��·������Ϊ2
(A,D,E)��·������Ϊ11

s����{0,0,0,0,0}  path����{1,-1,1,-1,-1}    dist����{6,0,7,2147483647,2147483647}
s����{1,1,0,0,0}  path����{1,-1,1,0,-1} dist����{6,0,7,8,2147483647}
s����{1,1,1,0,0}  path����{1,-1,1,0,2}  dist����{6,0,7,8,10}
s����{1,1,1,1,0}  path����{1,-1,1,0,2}  dist����{6,0,7,8,10}
s����{1,1,1,1,1}  path����{1,-1,1,0,2}  dist����{6,0,7,8,10}
�Ӷ���B��������������·�����£�
(B,C)��·������Ϊ7
(B,A,D)��·������Ϊ8
(B,C,E)��·������Ϊ10
(B,A)��·������Ϊ6

ɾ������v2��true 
ɾ����(v2,v3)��true 
��Ȩ����ͼG4�����㼯�ϣ�{A, B, D, E} 
�ڽӾ���:  
  0  5  2  ��
  6  0  ��  ��
  ��  ��  0  ��
  ��  ��  ��  0

������ȱ�������ͨͼ��
{ A B D } { E } 
{ B A D } { E } 
{ D } { E } { A B } 
{ E } { A B D } 
������ȱ�������ͨͼ��
{ A B D } { E } 
{ B A D } { E } 
{ D } { E } { A B } 
{ E } { A B D } 

*/

/*
�������н�����£�
��Ȩ����ͼG4�����㼯�ϣ�{A, B, C, D, E} 
���߱�
 {(0,1,5), (0,3,2)}
{(1,0,6), (1,2,7)}
{(2,4,3)}
{(3,2,8), (3,4,9)}
{}

������ȱ�������ͨͼ��
{ A B C E D } 
{ B A D C E } 
{ C E } { D } { A B } 
{ D C E } { A B } 
{ E } { A B C D } 
������ȱ�������ͨͼ��
{ A B D C E } 
{ B A C D E } 
{ C E } { D } { A B } 
{ D C E } { A B } 
{ E } { A B D C } 

ɾ������v2��true 
ɾ����(v2,v3)��true 
��Ȩ����ͼG4�����㼯�ϣ�{A, B, D, E} 
���߱�
 {(0,1,5), (0,2,2)}
{(1,0,6), (1,2,7)}
{(2,2,8)}
{}

������ȱ�������ͨͼ��
{ A B D } { E } 
{ B A D } { E } 
{ D } { E } { A B } 
{ E } { A B D } 
������ȱ�������ͨͼ��
{ A B D } { E } 
{ B A D } { E } 
{ D } { E } { A B } 
{ E } { A B D } 

*/