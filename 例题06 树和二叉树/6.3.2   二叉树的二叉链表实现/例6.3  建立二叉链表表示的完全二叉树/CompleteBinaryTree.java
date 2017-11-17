 //����6.3��  �������������ʾ����ȫ��������
 
import dataStructure.tree.BinaryNode;
import dataStructure.tree.BinaryTree;

public class CompleteBinaryTree<E> extends BinaryTree<E>   //�������������ʾ����ȫ������
{
    public CompleteBinaryTree()                            //����ն�����
    {
        super();
    }

    public CompleteBinaryTree(E[] levelorder)              //������ȫ������
    {                                                      //levelorderָ����ȫ�������Ĳ������
        this.root = create(levelorder, 0);                 //������levelorder[0]Ϊ����һ�ö�����
    }

    public BinaryNode<E> create(E[] levelorder, int i)     //������levelorder[i]Ϊ����һ������
    {
        BinaryNode<E> p = null;
        if (i<levelorder.length)
        {
            p = new BinaryNode<E>(levelorder[i]);          //�������p
            p.left = create(levelorder, 2*i+1);            //����p��������
            p.right = create(levelorder, 2*i+2);           //����p��������
        }
        return p;
    }

    public static void main(String args[])
    {
        String[] levelorder = {"A","B","C","D","E","F","G","H"};     //��ȫ�������Ĳ�α�������
        CompleteBinaryTree<String> cbtree = new CompleteBinaryTree<String>(levelorder);
        cbtree.preOrder();
        cbtree.inOrder();
        cbtree.postOrder();
        System.out.println("\n��������  "+cbtree.count());
        System.out.println("��ȣ�  "+cbtree.depth());
    }
}
/*
�ȸ����У�  A B D H E C F G 
�и����У�  H D B E A F C G 
������У�  H D E B F G C A 
��������  8
��ȣ�  4
*/