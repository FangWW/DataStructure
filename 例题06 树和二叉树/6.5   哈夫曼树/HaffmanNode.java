//���������Ľ����

public class HaffmanNode
{
	int weight; 							//Ȩֵ
	int flag; 								//���
	int parent; 							//˫�׽���±�
	int left; 					       //�����±�
	int right; 						   //�Һ����±�
	
	public HaffmanNode(int weight)
	{					
	    this.weight = weight;
	    this.flag = 0;
	    this.parent=-1;
	    this.left=-1;
	    this.right=-1;
	}
	public HaffmanNode()
	{					
	    this(0);
	}
	public String toString()	//����ȨֵΪweight�Ĺ�������haffTree
	{
		return 	this.weight+", "+this.flag+", "+this.parent+", "+this.left+", "+this.right;
	}
}
