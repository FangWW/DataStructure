package dataStructure.tree;
import dataStructure.tree.BinaryNode;            //二叉树的二叉链表结点类
import dataStructure.linearList.LinkedQueue;     //链式队列
import dataStructure.linearList.LinkedStack;     //链式栈

public class BinaryTree<E>                       //二叉树类
{
    protected BinaryNode<E> root;                //根结点

    public BinaryTree()                          //构造空二叉树
    {
        root=null;
    }

    public BinaryTree(BinaryNode<E> root)        //构造指定根结点的二叉树
    {
        this.root=root;
    }

    public boolean isEmpty()                     //判断是否空二叉树
    {
        return this.root==null;
    }

    public BinaryNode<E> getRoot()               //返回二叉树的根结点
    {
        return this.root;
    }

//6.3.3   二叉树的遍历
    public void preOrder()                       //先根次序遍历二叉树
    {
        System.out.print("\n先根序列：  ");
        preOrder(root);
    }    

    private void preOrder(BinaryNode<E> p)       //先根次序遍历以p结点为根的子二叉树
    {
        if (p!=null)                             //若二叉树不空
        {
            System.out.print(p.data+" ");        //访问当前结点
            preOrder(p.left);                    //按先根次序遍历当前结点的左子树
            preOrder(p.right);                   //按先根次序遍历当前结点的右子树
        }
    }

    public void inOrder()                        //中根次序遍历二叉树
    {
        System.out.print("\n中根序列：  ");
        inOrder(root);
    }    

    private void inOrder(BinaryNode<E> p)        //中根次序遍历以p结点为根的子二叉树
    {
        if (p!=null)
        {
            inOrder(p.left);                     //中根次序遍历左子树
            System.out.print(p.data+" ");
            inOrder(p.right);                    //中根次序遍历右子树
        }
    }

    public void postOrder()                      //后根次序遍历二叉树
    {
        System.out.print("\n后根序列：  ");
        postOrder(root);
    }

    private void postOrder(BinaryNode<E> p)      //后根次序遍历以p结点为根的子二叉树
    {
        if (p!=null)
        {
            postOrder(p.left);
            postOrder(p.right);
            System.out.print(p.data+" ");
        }
    }

//【例6.1】  构造并遍历二叉树。

//3.  基于遍历的操作
    public int count()                           //求一棵二叉树中所有结点个数
    {
        return count(root);
    }
    public int count(BinaryNode<E> p)            //求以p结点为根的子树的结点个数
    {
        if (p!=null)
            return 1+count(p.left)+count(p.right);
        else
            return 0;
    }

    public int depth()                           //求二叉树的深度
    {
        return depth(root);
    }
    public int depth(BinaryNode<E> p)            //求以p结点为根的子树的深度，后根次序遍历
    {
        if (p!=null)
        {
            int ld = depth(p.left);              //求左子树的深度
            int rd = depth(p.right);
            return (ld>=rd) ? ld+1 : rd+1;
        }
        return 0;
    }

    public BinaryNode<E> search(E value)         //查找值为value的结点
    {
        return search(root, value);
    }
    public BinaryNode<E> search(BinaryNode<E> p, E value)  //在以p为根的子树中查找值为value的结点
    {                                            //先根次序遍历，返回查找到结点，若未找到返回null
        BinaryNode<E> find=null;                 //记载找到结点
        if (p!=null && value!=null)
        {
            if (p.data.equals(value)) 
               find = p;                         //查找成功
            else
            {
               find = search(p.left, value);     //在左子树中查找
               if (find==null)
                   find=search(p.right, value);  //若左子树中未找到，则继续在右子树中查找
            }
        }
        return find;                             //返回找到结点
    }

    public BinaryNode<E> getParent(BinaryNode<E> node)  //返回指定结点node的父母结点
    {                                            //若空树、未找到或node为根，返回null
        if (root==null || node==null || node==root)
            return null; 
        return getParent(root, node);
    }
    public BinaryNode<E> getParent(BinaryNode<E> p, BinaryNode<E> node)
    {                                            //在以p为根的子树中查找并返回node结点的父母结点
        BinaryNode<E> find=null;                 //记载找到结点
        if (p!=null)
        {
            if (p.left==node || p.right==node) 
               find = p;                         //查找成功
            else
            {
               find = getParent(p.left, node);   //在左子树中查找
               if (find==null)
                   find = getParent(p.right, node);  //若左子树中未找到，则继续在右子树中查找
            }
        }
        return find;                             //返回找到的父母结点
    }


//6.3.4  构造二叉树
    public BinaryTree(E[] preorder)              //以标明空子树的先根序列构造一棵二叉树
    {
        root=create(preorder);
    }

    private int i=0;
    private BinaryNode<E> create(E[] preorder)   //创建一棵子树，当前结点值是preorder[i]
    {                                            //返回所创建子树的根结点
        BinaryNode<E> p = null;
        if (i<preorder.length)
        {
            E elem=preorder[i];
            i++;
            if (elem!=null)
            {
                p = new BinaryNode<E>(elem);     //建立p结点
                p.left = create(preorder);       //建立p的左子树
                p.right = create(preorder);      //建立p的右子树
            }
        }
        return p;
    }
//【例6.2】  输出二叉树中指定结点的所有祖先结点。

//6.3.5 二叉树的插入和删除操作
    public void insert(BinaryNode<E> p, E element, boolean leftChild) //插入元素element作为p结点的孩子
    {                                            //若leftChild为true，插入结点作为左孩子，否则作为右孩子
        if (p!=null)
        {
            BinaryNode<E> q = new BinaryNode<E>(element);
            if (leftChild)
            {
                q.left = p.left;                 //p结点的原左孩子成为q结点的左孩子
                p.left = q;                      //q结点作为p结点的左孩子
            }
            else
            {
                q.right = p.right;               //p结点的原右孩子成为q结点的右孩子
                p.right = q;                     //q结点作为p结点的右孩子
            }
        }
    }    
    public void insert(BinaryNode<E> p, E element)   //插入元素element作为p结点的左孩子
    {
        insert(p, element, true);
    }

    public void remove(BinaryNode<E> p, boolean leftChild)  //删除p结点的左/右子树
    {                                            //若leftChild为true，删除左子树，否则删除右子树
        if (p!=null)
        {
            if (leftChild)
                p.left = null;
            else
                p.right = null;
        }
    }
    public void remove(BinaryNode<E> p)          //删除p结点的左子树
    {
        remove(p, true);
    }

//6.3.6   二叉树遍历的非递归算法
    public void preOrderTraverse()               //先根次序遍历二叉树的非递归算法
    {
        System.out.print("先根次序遍历（非递归）：  ");
        LinkedStack<BinaryNode<E>> stack = new LinkedStack<BinaryNode<E>>();   //创建一个空栈
        BinaryNode<E> p = this.root;
        while(p!=null || !stack.isEmpty())       //p非空或栈非空时
            if(p!=null)
            {
                System.out.print(p.data+" ");    //访问结点
                stack.push(p);                   //p结点入栈
                p=p.left;                        //进入左子树
            }
            else                                 //p为空且栈非空时
            {
                p=stack.pop();                   //p指向出栈结点
                p=p.right;                       //进入右子树
            }
        System.out.println();
    }    

    public void inOrderTraverse()                //中根次序遍历二叉树的非递归算法
    {
        System.out.print("中根次序遍历（非递归）：  ");
        LinkedStack<BinaryNode<E>> stack = new LinkedStack<BinaryNode<E>>();   //创建一个空栈
        BinaryNode<E> p = this.root;
        while(p!=null || !stack.isEmpty())       //p非空或栈非空时
            if(p!=null)
            {
                stack.push(p);                   //p结点入栈
                p=p.left;                        //进入左子树
            }
            else                                 //p为空且栈非空时
            {
                p=stack.pop();                   //p指向出栈结点
                System.out.print(p.data+" ");    //访问结点
                p=p.right;                       //进入右子树
            }
        System.out.println();
    }    
//后根次序未写

//6.3.7   二叉树的层次遍历
    public void levelOrder()                     //按层次遍历二叉树
    {                                     
        LinkedQueue<BinaryNode<E>> que=new LinkedQueue<BinaryNode<E>>(); //创建一个空队列
        BinaryNode<E> p=this.root;
        System.out.print("层次遍历：  ");
        while(p!=null)
        {
            System.out.print(p.data+ " ");
            if(p.left!=null)    
                que.enqueue(p.left);             //p的左孩子结点入队
            if(p.right!=null)
                que.enqueue(p.right);            //p的右孩子结点入队
            p = que.dequeue();                   //p指向出队结点
        }
        System.out.println();
    }

//第6章习题
    public void leaf()                           //遍历输出叶子结点
    {
        leaf(root);
    }
    private void leaf(BinaryNode<E> p)           //先根次序遍历，输出叶子结点，3种遍历次序结果一样
    {
        if(p!=null)
        {
            if (p.isLeaf())
                System.out.print(p.data+" ");
            leaf(p.left);
            leaf(p.right);
        }
    }

    public int countLeaf()                       //求一棵二叉树中所有叶子结点个数
    {
        return countLeaf(root);
    }
    private int countLeaf(BinaryNode<E> p)       //求以p结点为根的子树的叶子结点个数
    {
        if (p==null)
            return 0;
        if (p.isLeaf())
            return 1;
        return countLeaf(p.left)+countLeaf(p.right);
    }

    public BinaryTree(BinaryTree<E> bitree)      //以已知的bitree构造二叉树
    {
        this.root = copy(bitree.root);
    }

    private BinaryNode<E> copy(BinaryNode<E> p)   //复制以p根的子二叉树
    {
        BinaryNode<E> q = null;
        if(p!=null)
        {
            q = new BinaryNode<E>(p.data);
            q.left = copy(p.left);               //复制左子树
            q.right = copy(p.right);             //复制右子树
        }
        return q;                                //返回建立子树的根结点
    }

    public boolean equals(Object obj)            //比较两棵二叉树是否相等 
    {
        if (obj == this)
            return true;
        if (obj instanceof BinaryTree)
        {
            BinaryTree<E> bitree = (BinaryTree)obj;
            return equals(this.root, bitree.root);
        }
        return false;
    }
    private boolean equals(BinaryNode<E> p, BinaryNode<E> q)  //判断以p和q结点为根的两棵子树是否相等
    {                                            //递归方法
        if(p==null && q==null)
            return true;
        if(p!=null && q!=null)
            return (p.data.equals(q.data)) && equals(p.left, q.left) && equals(p.right, q.right);
        return false;
    }

    public boolean replace(E old, E value)       //将首次出现的值为old结点值替换为value
    {
        BinaryNode<E> find=search(old);          //查找值为old的结点
        if(find!=null)
            find.data = value;                   //替换结点元素值
        return find!=null;    
    }

    public void replaceAll(E old, E value)       //将值为old的结点全部替换为value
    {
        replaceAll(root, old, value);
    }
    private void replaceAll(BinaryNode<E> p, E old, E value)     //在以p为根的子树中实现全部替换
    {
        if(p!=null)
        {
            if(p.data.equals(old)) 
                p.data = value;
            replaceAll(p.left, old, value);
            replaceAll(p.right, old, value);
        }
    }
      
    public static void main(String args[])
    {
        String[] preorder = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
        BinaryTree<String> bitree = new BinaryTree<String>(preorder);
        preorder[0]="Z";
        bitree.preOrder();
        bitree.inOrder();
        bitree.postOrder();
        System.out.println("\n结点个数：  "+bitree.count());
        System.out.println("高度：  "+bitree.depth());
        System.out.print("叶子结点：  ");
        bitree.leaf();
        System.out.println("  , 共"+bitree.countLeaf()+"个");

        BinaryTree<String> bitree2 = new BinaryTree<String>(bitree);
        System.out.println("两棵二叉树相等?  "+bitree.equals(bitree2));
        System.out.println("第2棵二叉树替换(\"D\",\"F\")：  "+bitree2.replace("D","F"));
        
        System.out.println("两棵二叉树相等?  "+bitree.equals(bitree2));
        System.out.println("第2棵二叉树全部替换(\"F\",\"Y\")  ");
        bitree2.replaceAll("F","Y");
        bitree2.preOrder();

        BinaryNode<String> find = bitree.search("D");      //查找
        bitree.insert(find, "Z");
        System.out.println("插入Z作为 "+find.data+" 的左孩子\n");
        bitree.levelOrder();
        bitree.preOrderTraverse();
        bitree.inOrderTraverse();

        String[] preorder2 = {"A","B",null,null,"C"};      //标明空子树的先根序列
        BinaryTree<String> bitree3 = new BinaryTree<String>(preorder2);
        bitree3.preOrder();
        bitree3.inOrder();
        bitree3.postOrder();
/*
        BinaryTree<String> bitree4 = new BinaryTree<String>(preorder2);
        bitree4.root = bitree4.create(preorder2);           //错，i越界，私有化可避免问题
        bitree4.preOrder();
*/
        String[] preorder3 = {"D","B","A",null,null,"C",null,null,"E"}; //二叉排序树
        BinaryTree<String> bitree5 = new BinaryTree<String>(preorder3);
        bitree5.inOrder();
        System.out.println("\n二叉排序树? "+bitree5.isSorted());

    }

//第8章习题
    public boolean isSorted()                    //判断一棵二叉树是否为二叉排序树
    {
        return isSorted(this.root);
    }
    public boolean isSorted(BinaryNode<E> p)
    {
        boolean yes = true;
        if (p!=null)
        {
            if (!(p.data instanceof Comparable))
               return false;                     
            Comparable cmpobj = (Comparable)p.data;
            if ((p.left==null || p.left!=null && cmpobj.compareTo(p.left.data)>0) &&
                (p.right==null || p.right!=null && cmpobj.compareTo(p.right.data)<0))
            { 
                yes = isSorted(p.left);
                if (yes)
                    yes = isSorted(p.right);
            }
            else
                yes = false;
        }
        return yes;
    }

}

/*
程序运行结果如下：
先根序列：  A B D G C E F H 
中根序列：  D G B A E C H F 
后根序列：  G D B E H F C A 
结点个数：  8
高度：  4
叶子结点：  G E H   , 共3个
两棵二叉树相等?  true
第2棵二叉树替换("D","F")：  true
两棵二叉树相等?  false
第2棵二叉树全部替换("F","Y")  

先根序列：  A B Y G C E Y H 
第1棵二叉树查找：  D
层次遍历：  A B C D E F Z G H 
先根次序遍历（非递归）：  A B D Z G C E F H 
中根次序遍历（非递归）：  Z D G B A E C H F 

先根序列：  A B D G C E F H 
中根序列：  D G B A E C H F 
后根序列：  G D B E H F C A 

中根序列：  A B C D E 
二叉排序树? true

*/
