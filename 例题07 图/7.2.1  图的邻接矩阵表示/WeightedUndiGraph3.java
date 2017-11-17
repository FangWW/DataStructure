//��Ȩ����ͼ
 
public class WeightedUndiGraph3                            //��Ȩ����ͼ
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //��Ȩ����ͼG3�Ķ��㼯��
        Edge edges[]={ new Edge(0,1,5), new Edge(0,3,2),   //G3�ı߼���
                       new Edge(1,0,5), new Edge(1,2,7), new Edge(1,3,6),
                       new Edge(2,1,7), new Edge(2,3,8), new Edge(2,4,3),
                       new Edge(3,0,2), new Edge(3,1,6), new Edge(3,2,8), new Edge(3,4,9), 
                       new Edge(4,2,3), new Edge(4,3,9)};
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
//        AdjListGraph<String> graph = new AdjListGraph<String>(vertices, edges);
        System.out.println("��Ȩ����ͼG3��"+graph.toString());

//        System.out.println("ɾ������v2��"+graph.removeVertex(2)+" ");
//        System.out.println("ɾ����(v2,v3)��"+(graph.removeEdge(2,3) && graph.removeEdge(3,2))+" ");
//        System.out.println(graph.toString());
/*
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
��Ȩ����ͼG3�����㼯�ϣ�{A, B, C, D, E} 
�ڽӾ���:  
  0  5  ��  2  ��
  5  0  7  6  ��
  ��  7  0  8  3
  2  6  8  0  9
  ��  ��  3  9  0

������ȱ�������ͨͼ��
{ A B C D E } 
{ B A D C E } 
{ C B A D E } 
{ D A B C E } 
{ E C B A D } 
������ȱ�������ͨͼ��
{ A B D C E } 
{ B A C D E } 
{ C B D E A } 
{ D A B C E } 
{ E C D B A } 

�Ӷ���B��������������·�����£�
(B,C)��·������Ϊ7
(B,D)��·������Ϊ6
(B,C,E)��·������Ϊ10
(B,A)��·������Ϊ5

ɾ������v2��true 
ɾ����(v2,v3)��true
���㼯�ϣ�{A, B, D, E} 
�ڽӾ���:  
  0  5  2  ��
  5  0  6  ��
  2  6  0  ��
  ��  ��  ��  0

������ȱ�������ͨͼ��
{ A B D } { E } 
{ B A D } { E } 
{ D A B } { E } 
{ E } { A B D } 
������ȱ�������ͨͼ��
{ A B D } { E } 
{ B A D } { E } 
{ D A B } { E } 
{ E } { A B D } 

*/

/*
�������н�����£�
��Ȩ����ͼG3�����㼯�ϣ�{A, B, C, D, E} 
���߱�
 {(0,1,5), (0,3,2)}
{(1,0,5), (1,2,7), (1,3,6)}
{(2,1,7), (2,3,8), (2,4,3)}
{(3,0,2), (3,1,6), (3,2,8), (3,4,9)}
{(4,2,3), (4,3,9)}

������ȱ�������ͨͼ��
{ A B C D E } 
{ B A D C E } 
{ C B A D E } 
{ D A B C E } 
{ E C B A D } 
������ȱ�������ͨͼ��



ɾ������v2��true 
ɾ����(v2,v3)��true 
���㼯�ϣ�{A, B, D, E} 
���߱�
 {(0,1,5), (0,2,2)}
{(1,0,5), (1,2,6)}
{(2,0,2), (2,1,6)}
{}

������ȱ�������ͨͼ��

������ȱ�������ͨͼ��


*/ 

