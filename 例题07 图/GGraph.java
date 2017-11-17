//ͼ�ӿ�

public interface GGraph<E>                            //ͼ�ӿ�
{
    int vertexCount();                                //���ض�����
    E get(int i);                                     //���ض���vi������Ԫ��
    boolean insertVertex(E vertex);                   //����һ������
    boolean insertEdge(int i, int j, int weight);     //����һ��ȨֵΪweight�ıߡ�vi,vj��
    boolean removeVertex(int v);                      //ɾ�����Ϊv�Ķ��㼰������ı�
    boolean removeEdge(int i, int j);                 //ɾ���ߡ�vi,vj��
    int getFirstNeighbor(int v);                      //���ض���v�ĵ�һ���ڽӶ�������
    int getNextNeighbor(int v, int w);                //����v��w�����һ���ڽӶ������� 
}
