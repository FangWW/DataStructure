package dataStructure.tree;
import dataStructure.tree.BinaryNode;            //�������Ķ�����������
import dataStructure.linearList.LinkedQueue;     //��ʽ����
import dataStructure.linearList.LinkedStack;     //��ʽջ

public class BinaryTree<E>                       //��������
{
    protected BinaryNode<E> root;                //�����

    public BinaryTree()                          //����ն�����
    {
        root=null;
    }

    public BinaryTree(BinaryNode<E> root)        //����ָ�������Ķ�����
    {
        this.root=root;
    }

    public boolean isEmpty()                     //�ж��Ƿ�ն�����
    {
        return this.root==null;
    }

    public BinaryNode<E> getRoot()               //���ض������ĸ����
    {
        return this.root;
    }

//6.3.3   �������ı���
    public void preOrder()                       //�ȸ��������������
    {
        System.out.print("\n�ȸ����У�  ");
        preOrder(root);
    }    

    private void preOrder(BinaryNode<E> p)       //�ȸ����������p���Ϊ�����Ӷ�����
    {
        if (p!=null)                             //������������
        {
            System.out.print(p.data+" ");        //���ʵ�ǰ���
            preOrder(p.left);                    //���ȸ����������ǰ����������
            preOrder(p.right);                   //���ȸ����������ǰ����������
        }
    }

    public void inOrder()                        //�и��������������
    {
        System.out.print("\n�и����У�  ");
        inOrder(root);
    }    

    private void inOrder(BinaryNode<E> p)        //�и����������p���Ϊ�����Ӷ�����
    {
        if (p!=null)
        {
            inOrder(p.left);                     //�и��������������
            System.out.print(p.data+" ");
            inOrder(p.right);                    //�и��������������
        }
    }

    public void postOrder()                      //����������������
    {
        System.out.print("\n������У�  ");
        postOrder(root);
    }

    private void postOrder(BinaryNode<E> p)      //������������p���Ϊ�����Ӷ�����
    {
        if (p!=null)
        {
            postOrder(p.left);
            postOrder(p.right);
            System.out.print(p.data+" ");
        }
    }

//����6.1��  ���첢������������

//3.  ���ڱ����Ĳ���
    public int count()                           //��һ�ö����������н�����
    {
        return count(root);
    }
    public int count(BinaryNode<E> p)            //����p���Ϊ���������Ľ�����
    {
        if (p!=null)
            return 1+count(p.left)+count(p.right);
        else
            return 0;
    }

    public int depth()                           //������������
    {
        return depth(root);
    }
    public int depth(BinaryNode<E> p)            //����p���Ϊ������������ȣ�����������
    {
        if (p!=null)
        {
            int ld = depth(p.left);              //�������������
            int rd = depth(p.right);
            return (ld>=rd) ? ld+1 : rd+1;
        }
        return 0;
    }

    public BinaryNode<E> search(E value)         //����ֵΪvalue�Ľ��
    {
        return search(root, value);
    }
    public BinaryNode<E> search(BinaryNode<E> p, E value)  //����pΪ���������в���ֵΪvalue�Ľ��
    {                                            //�ȸ�������������ز��ҵ���㣬��δ�ҵ�����null
        BinaryNode<E> find=null;                 //�����ҵ����
        if (p!=null && value!=null)
        {
            if (p.data.equals(value)) 
               find = p;                         //���ҳɹ�
            else
            {
               find = search(p.left, value);     //���������в���
               if (find==null)
                   find=search(p.right, value);  //����������δ�ҵ�����������������в���
            }
        }
        return find;                             //�����ҵ����
    }

    public BinaryNode<E> getParent(BinaryNode<E> node)  //����ָ�����node�ĸ�ĸ���
    {                                            //��������δ�ҵ���nodeΪ��������null
        if (root==null || node==null || node==root)
            return null; 
        return getParent(root, node);
    }
    public BinaryNode<E> getParent(BinaryNode<E> p, BinaryNode<E> node)
    {                                            //����pΪ���������в��Ҳ�����node���ĸ�ĸ���
        BinaryNode<E> find=null;                 //�����ҵ����
        if (p!=null)
        {
            if (p.left==node || p.right==node) 
               find = p;                         //���ҳɹ�
            else
            {
               find = getParent(p.left, node);   //���������в���
               if (find==null)
                   find = getParent(p.right, node);  //����������δ�ҵ�����������������в���
            }
        }
        return find;                             //�����ҵ��ĸ�ĸ���
    }


//6.3.4  ���������
    public BinaryTree(E[] preorder)              //�Ա������������ȸ����й���һ�ö�����
    {
        root=create(preorder);
    }

    private int i=0;
    private BinaryNode<E> create(E[] preorder)   //����һ����������ǰ���ֵ��preorder[i]
    {                                            //���������������ĸ����
        BinaryNode<E> p = null;
        if (i<preorder.length)
        {
            E elem=preorder[i];
            i++;
            if (elem!=null)
            {
                p = new BinaryNode<E>(elem);     //����p���
                p.left = create(preorder);       //����p��������
                p.right = create(preorder);      //����p��������
            }
        }
        return p;
    }
//����6.2��  �����������ָ�������������Ƚ�㡣

//6.3.5 �������Ĳ����ɾ������
    public void insert(BinaryNode<E> p, E element, boolean leftChild) //����Ԫ��element��Ϊp���ĺ���
    {                                            //��leftChildΪtrue����������Ϊ���ӣ�������Ϊ�Һ���
        if (p!=null)
        {
            BinaryNode<E> q = new BinaryNode<E>(element);
            if (leftChild)
            {
                q.left = p.left;                 //p����ԭ���ӳ�Ϊq��������
                p.left = q;                      //q�����Ϊp��������
            }
            else
            {
                q.right = p.right;               //p����ԭ�Һ��ӳ�Ϊq�����Һ���
                p.right = q;                     //q�����Ϊp�����Һ���
            }
        }
    }    
    public void insert(BinaryNode<E> p, E element)   //����Ԫ��element��Ϊp��������
    {
        insert(p, element, true);
    }

    public void remove(BinaryNode<E> p, boolean leftChild)  //ɾ��p������/������
    {                                            //��leftChildΪtrue��ɾ��������������ɾ��������
        if (p!=null)
        {
            if (leftChild)
                p.left = null;
            else
                p.right = null;
        }
    }
    public void remove(BinaryNode<E> p)          //ɾ��p����������
    {
        remove(p, true);
    }

//6.3.6   �����������ķǵݹ��㷨
    public void preOrderTraverse()               //�ȸ���������������ķǵݹ��㷨
    {
        System.out.print("�ȸ�����������ǵݹ飩��  ");
        LinkedStack<BinaryNode<E>> stack = new LinkedStack<BinaryNode<E>>();   //����һ����ջ
        BinaryNode<E> p = this.root;
        while(p!=null || !stack.isEmpty())       //p�ǿջ�ջ�ǿ�ʱ
            if(p!=null)
            {
                System.out.print(p.data+" ");    //���ʽ��
                stack.push(p);                   //p�����ջ
                p=p.left;                        //����������
            }
            else                                 //pΪ����ջ�ǿ�ʱ
            {
                p=stack.pop();                   //pָ���ջ���
                p=p.right;                       //����������
            }
        System.out.println();
    }    

    public void inOrderTraverse()                //�и���������������ķǵݹ��㷨
    {
        System.out.print("�и�����������ǵݹ飩��  ");
        LinkedStack<BinaryNode<E>> stack = new LinkedStack<BinaryNode<E>>();   //����һ����ջ
        BinaryNode<E> p = this.root;
        while(p!=null || !stack.isEmpty())       //p�ǿջ�ջ�ǿ�ʱ
            if(p!=null)
            {
                stack.push(p);                   //p�����ջ
                p=p.left;                        //����������
            }
            else                                 //pΪ����ջ�ǿ�ʱ
            {
                p=stack.pop();                   //pָ���ջ���
                System.out.print(p.data+" ");    //���ʽ��
                p=p.right;                       //����������
            }
        System.out.println();
    }    
//�������δд

//6.3.7   �������Ĳ�α���
    public void levelOrder()                     //����α���������
    {                                     
        LinkedQueue<BinaryNode<E>> que=new LinkedQueue<BinaryNode<E>>(); //����һ���ն���
        BinaryNode<E> p=this.root;
        System.out.print("��α�����  ");
        while(p!=null)
        {
            System.out.print(p.data+ " ");
            if(p.left!=null)    
                que.enqueue(p.left);             //p�����ӽ�����
            if(p.right!=null)
                que.enqueue(p.right);            //p���Һ��ӽ�����
            p = que.dequeue();                   //pָ����ӽ��
        }
        System.out.println();
    }

//��6��ϰ��
    public void leaf()                           //�������Ҷ�ӽ��
    {
        leaf(root);
    }
    private void leaf(BinaryNode<E> p)           //�ȸ�������������Ҷ�ӽ�㣬3�ֱ���������һ��
    {
        if(p!=null)
        {
            if (p.isLeaf())
                System.out.print(p.data+" ");
            leaf(p.left);
            leaf(p.right);
        }
    }

    public int countLeaf()                       //��һ�ö�����������Ҷ�ӽ�����
    {
        return countLeaf(root);
    }
    private int countLeaf(BinaryNode<E> p)       //����p���Ϊ����������Ҷ�ӽ�����
    {
        if (p==null)
            return 0;
        if (p.isLeaf())
            return 1;
        return countLeaf(p.left)+countLeaf(p.right);
    }

    public BinaryTree(BinaryTree<E> bitree)      //����֪��bitree���������
    {
        this.root = copy(bitree.root);
    }

    private BinaryNode<E> copy(BinaryNode<E> p)   //������p�����Ӷ�����
    {
        BinaryNode<E> q = null;
        if(p!=null)
        {
            q = new BinaryNode<E>(p.data);
            q.left = copy(p.left);               //����������
            q.right = copy(p.right);             //����������
        }
        return q;                                //���ؽ��������ĸ����
    }

    public boolean equals(Object obj)            //�Ƚ����ö������Ƿ���� 
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
    private boolean equals(BinaryNode<E> p, BinaryNode<E> q)  //�ж���p��q���Ϊ�������������Ƿ����
    {                                            //�ݹ鷽��
        if(p==null && q==null)
            return true;
        if(p!=null && q!=null)
            return (p.data.equals(q.data)) && equals(p.left, q.left) && equals(p.right, q.right);
        return false;
    }

    public boolean replace(E old, E value)       //���״γ��ֵ�ֵΪold���ֵ�滻Ϊvalue
    {
        BinaryNode<E> find=search(old);          //����ֵΪold�Ľ��
        if(find!=null)
            find.data = value;                   //�滻���Ԫ��ֵ
        return find!=null;    
    }

    public void replaceAll(E old, E value)       //��ֵΪold�Ľ��ȫ���滻Ϊvalue
    {
        replaceAll(root, old, value);
    }
    private void replaceAll(BinaryNode<E> p, E old, E value)     //����pΪ����������ʵ��ȫ���滻
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
        System.out.println("\n��������  "+bitree.count());
        System.out.println("�߶ȣ�  "+bitree.depth());
        System.out.print("Ҷ�ӽ�㣺  ");
        bitree.leaf();
        System.out.println("  , ��"+bitree.countLeaf()+"��");

        BinaryTree<String> bitree2 = new BinaryTree<String>(bitree);
        System.out.println("���ö��������?  "+bitree.equals(bitree2));
        System.out.println("��2�ö������滻(\"D\",\"F\")��  "+bitree2.replace("D","F"));
        
        System.out.println("���ö��������?  "+bitree.equals(bitree2));
        System.out.println("��2�ö�����ȫ���滻(\"F\",\"Y\")  ");
        bitree2.replaceAll("F","Y");
        bitree2.preOrder();

        BinaryNode<String> find = bitree.search("D");      //����
        bitree.insert(find, "Z");
        System.out.println("����Z��Ϊ "+find.data+" ������\n");
        bitree.levelOrder();
        bitree.preOrderTraverse();
        bitree.inOrderTraverse();

        String[] preorder2 = {"A","B",null,null,"C"};      //�������������ȸ�����
        BinaryTree<String> bitree3 = new BinaryTree<String>(preorder2);
        bitree3.preOrder();
        bitree3.inOrder();
        bitree3.postOrder();
/*
        BinaryTree<String> bitree4 = new BinaryTree<String>(preorder2);
        bitree4.root = bitree4.create(preorder2);           //��iԽ�磬˽�л��ɱ�������
        bitree4.preOrder();
*/
        String[] preorder3 = {"D","B","A",null,null,"C",null,null,"E"}; //����������
        BinaryTree<String> bitree5 = new BinaryTree<String>(preorder3);
        bitree5.inOrder();
        System.out.println("\n����������? "+bitree5.isSorted());

    }

//��8��ϰ��
    public boolean isSorted()                    //�ж�һ�ö������Ƿ�Ϊ����������
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
�������н�����£�
�ȸ����У�  A B D G C E F H 
�и����У�  D G B A E C H F 
������У�  G D B E H F C A 
��������  8
�߶ȣ�  4
Ҷ�ӽ�㣺  G E H   , ��3��
���ö��������?  true
��2�ö������滻("D","F")��  true
���ö��������?  false
��2�ö�����ȫ���滻("F","Y")  

�ȸ����У�  A B Y G C E Y H 
��1�ö��������ң�  D
��α�����  A B C D E F Z G H 
�ȸ�����������ǵݹ飩��  A B D Z G C E F H 
�и�����������ǵݹ飩��  Z D G B A E C H F 

�ȸ����У�  A B D G C E F H 
�и����У�  D G B A E C H F 
������У�  G D B E H F C A 

�и����У�  A B C D E 
����������? true

*/
