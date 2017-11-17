//【例8.2】  二叉排序树的插入和查找操作。

package dataStructure.tree;
import dataStructure.tree.BinaryNode;            //二叉树的二叉链表结点类

public class BinarySortTree<E>                   //二叉排序树类
{
    protected BinaryNode<E> root;                //根结点

    public BinarySortTree()                      //构造空二叉排序树
    {
        root=null;
    }
    public boolean isEmpty()                     //判断是否空二叉树
    {
        return this.root==null;
    }

    public void inOrder()                        //中根次序遍历二叉排序树
    {
        System.out.print("\n中根次序遍历二叉排序树：  ");
        if (root!=null)
            inOrder(root);
        System.out.println();
    }    
    private void inOrder(BinaryNode<E> p)        //中根次序遍历以p结点为根的子二叉树
    {
        if (p!=null)
        {
            inOrder(p.left);
            System.out.print(p.data+" ");
            inOrder(p.right);
        }
    }

    public BinaryNode<E> search(E value)         //查找值为value的结点，非递归算法
    {                                            //若查找成功返回结点，否则返回null
        if (value==null || !(value instanceof Comparable))
            return null;                     
        Comparable cmpobj = (Comparable)value;
        BinaryNode<E> p=this.root;
        while (p!=null && cmpobj.compareTo(p.data)!=0)     //当没有相等者
        {
            System.out.print(p.data+"? ");       //显示查找经过的结点值，可省略
            if (cmpobj.compareTo(p.data)<0)      //若value较小
                p=p.left;                        //进入左子树
            else
                p=p.right;                       //进入右子树
        }
        return p;
    }

    public boolean insert(E value)               //插入结点，不插入关键字重复的结点
    {                                            //若插入成功返回true
        if (value==null || !(value instanceof Comparable)) //不能插入空对象或不可比较大小的对象
            return false;                     
        if (root==null)
        {
            root=new BinaryNode<E>(value);       //建立根结点
            return true;
        }
        return insert(value, root);              //插入value到以root为根的二叉排序树中
    }  

    private boolean insert(E value, BinaryNode<E> p)  //将value插入到以p为根的子树中
    {                                                 //递归算法
        Comparable cmpobj = (Comparable)value;
        if (cmpobj.compareTo(p.data)==0)
            return false;                             //不插入关键字重复的结点 

        if (cmpobj.compareTo(p.data)<0)
            if (p.left==null)
            {
               p.left = new BinaryNode<E>(value);     //建立叶子结点作为p的左孩子
               return true;
            }
            else
               return insert(value, p.left);          //将value插入到p的左子树中
        else
            if (p.right==null)
            {
               p.right = new BinaryNode<E>(value);    //建立叶子结点作为p的右孩子
               return true;
            }
            else
               return insert(value, p.right);         //将value插入到p的右子树中
    }

    public E remove(E value)                               //删除结点
    {                                                      //若删除成功返回删除结点值，否则返回null
        if (root==null || value==null || !(value instanceof Comparable))
            return null;                     
        return remove(value, root, null);                  //在以root为根的二叉排序树中删除value
    }  

    private E remove(E value, BinaryNode<E> p, BinaryNode<E> parent)   //在以p为根的子树中删除value
    {                                                      //parent是p的父母结点，递归算法
        Comparable cmpobj = (Comparable)value;
        if (p!=null)
        {
            if (cmpobj.compareTo(p.data)<0)
                return remove(value, p.left, p);
            else
                if (cmpobj.compareTo(p.data)>0)
                    return remove(value, p.right, p);
                else                                       //找到待删除结点p
                    if (p.left!=null && p.right!=null)     //p是2度结点
                    {
                        BinaryNode<E> innext = p.right;    //寻找p在中根次序下的后继结点innext
                        while (innext.left!=null)
                            innext = innext.left;
                        p.data = innext.data;              //以后继结点值替换
                        return remove(p.data, p.right, p);
                    }
                    else                                   //p是1度和叶子结点
                    {
                        if (parent==null)                  //删除根结点，即p==root
                        {
                            if (p.left!=null)
                                root = p.left;
                            else
                                root = p.right;
                            return p.data;                 //返回删除结点p值
                        }
                        
                        if (p==parent.left)                //p是parent的左孩子
                            if (p.left!=null)
                                parent.left = p.left;      //以p的左子树填补
                            else
                                parent.left = p.right;
                        else                               //p是parent的右孩子
                            if (p.left!=null)
                                parent.right = p.left;
                            else
                                parent.right = p.right;
                        return p.data;
                    }
        }
        return null;
    }

    public static void main(String args[])
    {
        BinarySortTree<Integer> bstree = new BinarySortTree<Integer>();
        System.out.print("插入： ");
        int[] key={54,18,66,87,36,12,54,81,15,76,57,6,40,99,85,99};
        for (int i=0; i<key.length; i++)
            if (bstree.insertNode(new Integer(key[i])))            //若插入对象成功
                System.out.print(key[i]+" ");
        bstree.inOrder();

        Integer element = new Integer(key[key.length-1]);
        System.out.println("查找"+element+", "+(bstree.search(element)!=null?"":"不")+"成功 ");
        element = new Integer(50);
        System.out.println("查找"+element+", "+(bstree.search(element)!=null?"":"不")+"成功 ");

        int value=66;
        System.out.print("删除"+value+", "+(bstree.remove(new Integer(value))!=null?"":"不")+"成功 ");
        bstree.inOrder();
    }


 //思考题答案
    public BinaryNode<E> searchNode(E value)     //查找值为value的结点
    {                                            //若查找成功返回结点，否则返回null
        if (value==null || !(value instanceof Comparable))
            return null;                     
        return searchNode(value, root);
    }
    private BinaryNode<E> searchNode(E value, BinaryNode<E> p) //查找值为value的结点，递归算法
    {
        if (p!=null)
        {
            Comparable cmpobj = (Comparable)value;
            if (cmpobj.compareTo(p.data)==0)          //若两个对象相等，查找成功
                return p; 
            System.out.print(p.data+"? ");
            if (cmpobj.compareTo(p.data)<0)
                return searchNode(value, p.left);     //在左子树中查找
            else
                return searchNode(value, p.right);    //在右子树中查找
        }
        return null;
    }

    public boolean insertNode(E value)                //插入结点，非递归算法
    {
        if (value==null || !(value instanceof Comparable))
            return false;                     
            
        if (root==null)
            root=new BinaryNode<E>(value);       //建立根结点
        else
        {
            BinaryNode<E> p=this.root, parent=null;
            Comparable cmpobj = (Comparable)value;
            while (p!=null)
            {
                parent = p;
                if (cmpobj.compareTo(p.data)==0)
                    return false;                //不插入重复关键字    

                if (cmpobj.compareTo(p.data)<0)
                    p=p.left;
                else
                    p=p.right;
            }
            p=new BinaryNode<E>(value);          //建立叶子结点p
            if(cmpobj.compareTo(parent.data)<0)
               parent.left = p;                  //p作为parent的左孩子
            else
               parent.right = p;                 //p作为parent的右孩子
        }
        return true;
   }  

}


/*
插入： 54 18 66 87 36 12 81 15 76 57 6 40 99 85 
中根次序遍历二叉排序树：  6 12 15 18 36 40 54 57 66 76 81 85 87 99 
54? 66? 87? 81? 查找85, 成功 
54? 18? 36? 40? 查找50, 不成功 
删除66, 成功 
中根次序遍历二叉排序树：  6 12 15 18 36 40 54 57 76 81 85 87 99 

*/