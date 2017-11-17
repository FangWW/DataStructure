//����7.3��  �����Ȩ����ͼG6����С��������
 
public class MinSpanTree                                   //�����Ȩ����ͼ����С������
{
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};           //��Ȩ����ͼG6�Ķ��㼯��
        Edge edges[]={ new Edge(0,1,25), new Edge(0,2,4), new Edge(0,3,22),       //G6�ı߼���
                       new Edge(1,0,25), new Edge(1,2,16), new Edge(1,4,3),
                       new Edge(2,0,4), new Edge(2,1,16), new Edge(2,3,18), new Edge(2,4,7),
                       new Edge(3,0,22), new Edge(3,2,18), new Edge(3,4,9), 
                       new Edge(4,1,3), new Edge(4,2,7), new Edge(4,3,9)};
        AdjMatrixGraph<String> graph = new AdjMatrixGraph<String>(vertices, edges);
        System.out.println("��Ȩ����ͼG6��"+graph.toString());

        AbstractGraph<String> minspantree = graph.minSpanTree_prim();
        System.out.println("\n��С��������"+minspantree.toString());
    }
}
/*
�������н�����£�
��Ȩ����ͼG6�����㼯�ϣ�{A, B, C, D, E} 
�ڽӾ���:  
  0  25  4  22  ��
  25  0  16  ��  3
  4  16  0  18  7
  22  ��  18  0  9
  ��  3  7  9  0

mst�����ֵ��(0,1,25)(0,2,4)(0,3,22)(0,4,2147483647)
mst���飺(0,2,4)(2,1,16)(2,3,18)(2,4,7)
mst���飺(0,2,4)(2,4,7)(4,3,9)(4,1,3)
mst���飺(0,2,4)(2,4,7)(4,1,3)(4,3,9)
mst���飺(0,2,4)(2,4,7)(4,1,3)(4,3,9)
��С�����������㼯�ϣ�{A, B, C, D, E} 
�ڽӾ���:  
  0  ��  4  ��  ��
  ��  0  ��  ��  ��
  ��  ��  0  ��  7
  ��  ��  ��  0  ��
  ��  3  ��  9  0


*/

/*
�������н�����£�
��Ȩ����ͼG6�����㼯�ϣ�{A, B, C, D, E} 
�ڽӾ���:  
  0  5  4  12  ��
  5  0  6  ��  7
  4  6  0  8  3
  12  ��  8  0  9
  ��  7  3  9  0

mst���飺(0,1,5)(0,2,4)(0,3,12)(0,4,2147483647)
mst���飺(0,2,4)(0,1,5)(2,3,8)(2,4,3)
mst���飺(0,2,4)(2,4,3)(2,3,8)(0,1,5)
mst���飺(0,2,4)(2,4,3)(0,1,5)(2,3,8)

��С�����������㼯�ϣ�{A, B, C, D, E} 
�ڽӾ���:  
  0  5  4  ��  ��
  ��  0  ��  ��  ��
  ��  ��  0  8  3
  ��  ��  ��  0  ��
  ��  ��  ��  ��  0

*/ 

