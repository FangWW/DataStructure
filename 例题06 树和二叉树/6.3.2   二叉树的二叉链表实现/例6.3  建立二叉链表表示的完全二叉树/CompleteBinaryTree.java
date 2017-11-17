 //【例6.3】  建立二叉链表表示的完全二叉树。
 
import dataStructure.tree.BinaryNode;
import dataStructure.tree.BinaryTree;

public class CompleteBinaryTree<E> extends BinaryTree<E>   //建立二叉链表表示的完全二叉树
{
    public CompleteBinaryTree()                            //构造空二叉树
    {
        super();
    }

    public CompleteBinaryTree(E[] levelorder)              //构造完全二叉树
    {                                                      //levelorder指定完全二叉树的层次序列
        this.root = create(levelorder, 0);                 //创建以levelorder[0]为根的一棵二叉树
    }

    public BinaryNode<E> create(E[] levelorder, int i)     //创建以levelorder[i]为根的一棵子树
    {
        BinaryNode<E> p = null;
        if (i<levelorder.length)
        {
            p = new BinaryNode<E>(levelorder[i]);          //建立结点p
            p.left = create(levelorder, 2*i+1);            //建立p的左子树
            p.right = create(levelorder, 2*i+2);           //建立p的右子树
        }
        return p;
    }

    public static void main(String args[])
    {
        String[] levelorder = {"A","B","C","D","E","F","G","H"};     //完全二叉树的层次遍历序列
        CompleteBinaryTree<String> cbtree = new CompleteBinaryTree<String>(levelorder);
        cbtree.preOrder();
        cbtree.inOrder();
        cbtree.postOrder();
        System.out.println("\n结点个数：  "+cbtree.count());
        System.out.println("深度：  "+cbtree.depth());
    }
}
/*
先根序列：  A B D H E C F G 
中根序列：  H D B E A F C G 
后根序列：  H D E B F G C A 
结点个数：  8
深度：  4
*/