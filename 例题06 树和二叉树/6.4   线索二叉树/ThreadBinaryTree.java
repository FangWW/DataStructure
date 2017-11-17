package dataStructure.tree;
import dataStructure.tree.ThreadBinaryNode;

public class ThreadBinaryTree<E>                           //中序线索二叉树
{
    private ThreadBinaryNode<E> root;

    public ThreadBinaryTree()                              //构造空线索二叉树
    {
        root=null;
    }

    public ThreadBinaryTree(E[] preorder)                  //以标明空子树的先根序列构造一棵二叉树并进行中序线索化
    {
        root=create(preorder);
        inorderThread(root);                               //中序线索化二叉树
//        System.out.print("中序线索化：\r\n"+instr);
    }

    private int i=0;
    private ThreadBinaryNode<E> create(E[] preorder)       //创建一棵子树，当前结点值是preorder[i]
    {                                                      //返回所创建子树的根结点
        ThreadBinaryNode<E> p = null;
        if(i<preorder.length)
        {
            E elem=preorder[i];
            i++;
            if(elem!=null)
            {
                p = new ThreadBinaryNode<E>(elem);         //建立p结点
                p.left = create(preorder);                 //建立p的左子树
                p.right = create(preorder);                //建立p的右子树
            }
        }
        return p;
    }

    private ThreadBinaryNode<E> front=null;
//    private String instr="";
    private void inorderThread(ThreadBinaryNode<E> p)      //中序线索化以p结点为根的子树，p的前驱结点是front
    {
        if(p!=null)
        {
            inorderThread(p.left);                         //中序线索化p的左子树
            if(p.left==null)                               //若p的左子树为空
            {
                p.ltag=1;                                  //设置左线索标记
                p.left=front;                              //设置p.left为指向前驱front的线索
            }
            if(p.right==null)                              //若p的右子树为空
                p.rtag=1;                                  //设置右线索标记
            if(front!=null && front.rtag==1) 
                front.right=p;                             //设置前驱front.right为指向后继p的线索
/*
            if(front!=null)                                //显示中间结果
                instr += "front="+front.data+"\t";
            else
                instr += "front=null\t";
            instr += "p="+p.data+"\r\n";
*/
            front=p;
            inorderThread(p.right);                        //中序线索化p的右子树
        }
    }

    public ThreadBinaryNode<E> inNext(ThreadBinaryNode<E> p) //返回p在中根次序下的后继结点
    {
        if(p.rtag==1)                                      //若右子树为空
            p=p.right;                                     //p.right就是指向后继结点的线索
        else
        {
            p=p.right;                                     //进入右子树
            while(p.ltag==0)                               //找到最左边的后代结点
                p=p.left;
        }
        return p;
    }

    public void inOrder()                                  //中根次序遍历中序线索二叉树，非递归算法
    {
        ThreadBinaryNode<E> p=root;
        if(p!=null)
        {
            System.out.print("中根次序遍历：  ");
            while(p.ltag==0)
                p=p.left;                                  //找到根的最左边的后代结点
            do
            {
                System.out.print(p.data+" ");
                p=inNext(p);                               //返回p在中根次序下的后继结点
            } while(p!=null);
            System.out.println();
        }
    }    

    public ThreadBinaryNode<E> preNext(ThreadBinaryNode<E> p) //返回p在先根次序下的后继结点
    {
        if(p.ltag==0)                                      //若左子树非空
            p=p.left;                                      //左孩子就是p的后继结点
        else                                               //否则，后继是右兄弟或某个中序祖先的右孩子
        {
            if(p.rtag==0)                                  //若左子树为空而右子树非空
                p=p.right;                                 //右孩子是p的后继结点
            else
            {
                while(p.rtag==1 && p.right!=null)          //叶子结点
                    p=p.right;                             //后继是其某个中序祖先的右孩子
                p=p.right;
            }
        }
        return p;
    }

    public void preOrder()                                 //先根次序遍历中序线索二叉树
    {
        ThreadBinaryNode<E> p=root;
        if(p!=null)
        {
            System.out.print("先根次序遍历：  ");
            do
            {
                System.out.print(p.data+" ");
                p=preNext(p);
            }while(p!=null);
            System.out.println();
        }
    }    

    public ThreadBinaryNode<E> inPrevious(ThreadBinaryNode<E> p) //返回p在中根次序下的前驱结点
    {
        if(p.ltag==1)                                      //若左子树为空
            p=p.left;                                      //p.left就是指向后继结点的线索
        else                                               //若左子树非空
        {
            p=p.left;                                      //进入左子树
            while(p.rtag==0)                               //找到最右边的子孙结点
                p=p.right;
        }
        return p;
    }

    public void inOrder_previous()                         //中根次序遍历中序线索二叉树，非递归算法
    {
        ThreadBinaryNode<E> p=root;
        if(p!=null)
        {
            System.out.print("中根次序遍历（反序）：  ");
            while(p.rtag==0)
                p=p.right;                                 //找到根的最右边子孙结点
            do
            {
                System.out.print(p.data+" ");
                p=inPrevious(p);                           //返回p的前驱结点
            } while(p!=null);
            System.out.println();
        }
    }    

    public ThreadBinaryNode<E> postPrevious(ThreadBinaryNode<E> p)   //返回p在后根次序下的前驱结点
    {
        if(p.rtag==0)                                      //右子树非空
            p=p.right;                                     //右孩子是p的前驱结点
        else                                               //否则，前驱是左兄弟或其某个中序祖先的左孩子
        {
            while(p.ltag==1 && p.left!=null)
                p=p.left;                                  //寻找其某个中序祖先
            p=p.left;                                      //左孩子是p的前驱结点
        }
        return p;
    }

    public void postOrder_previous()                       //后根次序遍历中序线索二叉树，非递归算法
    {
        ThreadBinaryNode<E> p=root;
        if(p!=null)
        {
            System.out.print("后根次序遍历（反序）：  ");
            do
            {
                System.out.print(p.data+" ");
                p=postPrevious(p);                         //返回p在后根次序下的前驱结点
            } while(p!=null);
            System.out.println();
        }
    }

    public static void main(String args[])
    {
        String[] preorder = {"A","B","D",null,null,"E","G",null,null,null,"C","F",null,"H",null,null,"K"};
        ThreadBinaryTree<String> tbtree = new ThreadBinaryTree<String>(preorder);    //创建中序线索二叉树
        tbtree.preOrder();                                 //先根次序遍历
        tbtree.inOrder();                                  //中根次序遍历
        tbtree.inOrder_previous();                         //中根次序遍历（求前驱）
        tbtree.postOrder_previous();                       //后根次序遍历（求前驱）

    }
}

/*
先根次序遍历：  A B D E G C F H K 
中根次序遍历：  D B G E A F H C K 
中根次序遍历（反序）：  K C H F A E G B D 
后根次序遍历（反序）：  A C K F H B E G D 
*/