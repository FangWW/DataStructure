//����6.1��  ���첢������������

import dataStructure.tree.BinaryNode;            //�������Ķ�����������
import dataStructure.tree.BinaryTree;            //��������

public class Traverse                            //���첢����������
{
    public static BinaryTree<String> create()    //����ָ��������
    {
        BinaryNode<String> child_f, child_d, child_b, child_c, child_a;
        child_d = new BinaryNode<String>("D", null, new BinaryNode("G"));
        child_b = new BinaryNode<String>("B", child_d, null);
        child_f = new BinaryNode<String>("F", new BinaryNode("H"), null);
        child_c = new BinaryNode<String>("C", new BinaryNode("E"), child_f);
        child_a = new BinaryNode("A", child_b, child_c);
        return new BinaryTree<String>(child_a);
    }

    public static void main(String args[])
    {
        BinaryTree<String> bitree = create();
        bitree.preOrder();
        bitree.inOrder();
        bitree.postOrder();
    }
}

/*
�������н�����£�
�ȸ����У�  A B D G C E F H 
�и����У�  D G B A E C H F 
������У�  G D B E H F C A 
*/

