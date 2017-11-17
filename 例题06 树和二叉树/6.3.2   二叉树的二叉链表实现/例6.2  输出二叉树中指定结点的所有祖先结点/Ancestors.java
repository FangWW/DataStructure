//����6.2��  �����������ָ�������������Ƚ�㡣

import dataStructure.tree.BinaryNode;                      //�������Ķ�����������
import dataStructure.tree.BinaryTree;                      //��������

public class Ancestors 
{
    public static void main(String args[])
    {
        String[] preorder = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"}; //�������������ȸ�����
        BinaryTree<String> bitree = new BinaryTree<String>(preorder);
        bitree.preOrder();

        String value="H";
        BinaryNode<String> find = bitree.search(value);    //����
        if (find==null)
            System.out.println("\nδ�ҵ�"+value);
        else
        {
            BinaryNode<String> parent = bitree.getParent(find);
            System.out.print("\n"+find.data+"�����Ƚ����");
            while (parent!=null)
            {
                System.out.print(parent.data+"  ");
                parent = bitree.getParent(parent);
            }
            System.out.println();
        }
    }
}
/*
�������н�����£�
�ȸ����У�  A B D G C E F H 
δ�ҵ�Z

D�����Ƚ����B  A 

H�����Ƚ����F  C  A  

*/


