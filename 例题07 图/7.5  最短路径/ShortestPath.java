//����7.4��  ���Ȩͼ�ĵ�Դ���·����
 
public class ShortestPath                                  //���Ȩͼ�ĵ�Դ���·��
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //��Ȩ����ͼG7�Ķ��㼯��
        Edge edges[]={ new Edge(0,1,10), new Edge(0,3,30), new Edge(0,4,99),       //G7�ı߼���
                       new Edge(1,2,50), 
                       new Edge(2,4,10), 
                       new Edge(3,2,20), new Edge(3,4,60)};
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
        System.out.println("��Ȩ����ͼG7��"+graph.toString());
        graph.shortestPath(0);                             //�Ӷ���A��������������·��
//        graph.shortestPath(1);                               //�Ӷ���B��������������·��??
    }
}
/*
�������н�����£�
��Ȩ����ͼG7�����㼯�ϣ�{A, B, C, D, E} 
�ڽӾ���:  
  0  10  ��  30  99
  ��  0  50  ��  ��
  ��  ��  0  ��  10
  ��  ��  20  0  60
  ��  ��  ��  ��  0


s����{1,0,0,0,0}  path����{-1,0,-1,0,0} dist����{0,10,2147483647,30,99}
s����{1,1,0,0,0}  path����{-1,0,1,0,0}  dist����{0,10,60,30,99}
s����{1,1,0,1,0}  path����{-1,0,3,0,3}  dist����{0,10,50,30,90}
s����{1,1,1,1,0}  path����{-1,0,3,0,2}  dist����{0,10,50,30,60}
s����{1,1,1,1,1}  path����{-1,0,3,0,2}  dist����{0,10,50,30,60}
�Ӷ���A��������������·�����£�
(A,B)��·������Ϊ10
(A,D,C)��·������Ϊ50
(A,D)��·������Ϊ30
(A,D,C,E)��·������Ϊ60


*/

/*
�������н�����£�

*/ 

