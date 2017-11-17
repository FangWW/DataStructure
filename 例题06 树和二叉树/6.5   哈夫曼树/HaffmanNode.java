//哈夫曼树的结点类

public class HaffmanNode
{
	int weight; 							//权值
	int flag; 								//标记
	int parent; 							//双亲结点下标
	int left; 					       //左孩子下标
	int right; 						   //右孩子下标
	
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
	public String toString()	//构造权值为weight的哈夫曼树haffTree
	{
		return 	this.weight+", "+this.flag+", "+this.parent+", "+this.left+", "+this.right;
	}
}
