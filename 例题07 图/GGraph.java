//图接口

public interface GGraph<E>                            //图接口
{
    int vertexCount();                                //返回顶点数
    E get(int i);                                     //返回顶点vi的数据元素
    boolean insertVertex(E vertex);                   //插入一个顶点
    boolean insertEdge(int i, int j, int weight);     //插入一条权值为weight的边〈vi,vj〉
    boolean removeVertex(int v);                      //删除序号为v的顶点及其关联的边
    boolean removeEdge(int i, int j);                 //删除边〈vi,vj〉
    int getFirstNeighbor(int v);                      //返回顶点v的第一个邻接顶点的序号
    int getNextNeighbor(int v, int w);                //返回v在w后的下一个邻接顶点的序号 
}
