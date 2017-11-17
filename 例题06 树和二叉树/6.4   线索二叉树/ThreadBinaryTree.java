package dataStructure.tree;
import dataStructure.tree.ThreadBinaryNode;

public class ThreadBinaryTree<E>                           //��������������
{
    private ThreadBinaryNode<E> root;

    public ThreadBinaryTree()                              //���������������
    {
        root=null;
    }

    public ThreadBinaryTree(E[] preorder)                  //�Ա������������ȸ����й���һ�ö���������������������
    {
        root=create(preorder);
        inorderThread(root);                               //����������������
//        System.out.print("������������\r\n"+instr);
    }

    private int i=0;
    private ThreadBinaryNode<E> create(E[] preorder)       //����һ����������ǰ���ֵ��preorder[i]
    {                                                      //���������������ĸ����
        ThreadBinaryNode<E> p = null;
        if(i<preorder.length)
        {
            E elem=preorder[i];
            i++;
            if(elem!=null)
            {
                p = new ThreadBinaryNode<E>(elem);         //����p���
                p.left = create(preorder);                 //����p��������
                p.right = create(preorder);                //����p��������
            }
        }
        return p;
    }

    private ThreadBinaryNode<E> front=null;
//    private String instr="";
    private void inorderThread(ThreadBinaryNode<E> p)      //������������p���Ϊ����������p��ǰ�������front
    {
        if(p!=null)
        {
            inorderThread(p.left);                         //����������p��������
            if(p.left==null)                               //��p��������Ϊ��
            {
                p.ltag=1;                                  //�������������
                p.left=front;                              //����p.leftΪָ��ǰ��front������
            }
            if(p.right==null)                              //��p��������Ϊ��
                p.rtag=1;                                  //�������������
            if(front!=null && front.rtag==1) 
                front.right=p;                             //����ǰ��front.rightΪָ����p������
/*
            if(front!=null)                                //��ʾ�м���
                instr += "front="+front.data+"\t";
            else
                instr += "front=null\t";
            instr += "p="+p.data+"\r\n";
*/
            front=p;
            inorderThread(p.right);                        //����������p��������
        }
    }

    public ThreadBinaryNode<E> inNext(ThreadBinaryNode<E> p) //����p���и������µĺ�̽��
    {
        if(p.rtag==1)                                      //��������Ϊ��
            p=p.right;                                     //p.right����ָ���̽�������
        else
        {
            p=p.right;                                     //����������
            while(p.ltag==0)                               //�ҵ�����ߵĺ�����
                p=p.left;
        }
        return p;
    }

    public void inOrder()                                  //�и�������������������������ǵݹ��㷨
    {
        ThreadBinaryNode<E> p=root;
        if(p!=null)
        {
            System.out.print("�и����������  ");
            while(p.ltag==0)
                p=p.left;                                  //�ҵ���������ߵĺ�����
            do
            {
                System.out.print(p.data+" ");
                p=inNext(p);                               //����p���и������µĺ�̽��
            } while(p!=null);
            System.out.println();
        }
    }    

    public ThreadBinaryNode<E> preNext(ThreadBinaryNode<E> p) //����p���ȸ������µĺ�̽��
    {
        if(p.ltag==0)                                      //���������ǿ�
            p=p.left;                                      //���Ӿ���p�ĺ�̽��
        else                                               //���򣬺�������ֵܻ�ĳ���������ȵ��Һ���
        {
            if(p.rtag==0)                                  //��������Ϊ�ն��������ǿ�
                p=p.right;                                 //�Һ�����p�ĺ�̽��
            else
            {
                while(p.rtag==1 && p.right!=null)          //Ҷ�ӽ��
                    p=p.right;                             //�������ĳ���������ȵ��Һ���
                p=p.right;
            }
        }
        return p;
    }

    public void preOrder()                                 //�ȸ����������������������
    {
        ThreadBinaryNode<E> p=root;
        if(p!=null)
        {
            System.out.print("�ȸ����������  ");
            do
            {
                System.out.print(p.data+" ");
                p=preNext(p);
            }while(p!=null);
            System.out.println();
        }
    }    

    public ThreadBinaryNode<E> inPrevious(ThreadBinaryNode<E> p) //����p���и������µ�ǰ�����
    {
        if(p.ltag==1)                                      //��������Ϊ��
            p=p.left;                                      //p.left����ָ���̽�������
        else                                               //���������ǿ�
        {
            p=p.left;                                      //����������
            while(p.rtag==0)                               //�ҵ����ұߵ�������
                p=p.right;
        }
        return p;
    }

    public void inOrder_previous()                         //�и�������������������������ǵݹ��㷨
    {
        ThreadBinaryNode<E> p=root;
        if(p!=null)
        {
            System.out.print("�и�������������򣩣�  ");
            while(p.rtag==0)
                p=p.right;                                 //�ҵ��������ұ�������
            do
            {
                System.out.print(p.data+" ");
                p=inPrevious(p);                           //����p��ǰ�����
            } while(p!=null);
            System.out.println();
        }
    }    

    public ThreadBinaryNode<E> postPrevious(ThreadBinaryNode<E> p)   //����p�ں�������µ�ǰ�����
    {
        if(p.rtag==0)                                      //�������ǿ�
            p=p.right;                                     //�Һ�����p��ǰ�����
        else                                               //����ǰ�������ֵܻ���ĳ���������ȵ�����
        {
            while(p.ltag==1 && p.left!=null)
                p=p.left;                                  //Ѱ����ĳ����������
            p=p.left;                                      //������p��ǰ�����
        }
        return p;
    }

    public void postOrder_previous()                       //���������������������������ǵݹ��㷨
    {
        ThreadBinaryNode<E> p=root;
        if(p!=null)
        {
            System.out.print("���������������򣩣�  ");
            do
            {
                System.out.print(p.data+" ");
                p=postPrevious(p);                         //����p�ں�������µ�ǰ�����
            } while(p!=null);
            System.out.println();
        }
    }

    public static void main(String args[])
    {
        String[] preorder = {"A","B","D",null,null,"E","G",null,null,null,"C","F",null,"H",null,null,"K"};
        ThreadBinaryTree<String> tbtree = new ThreadBinaryTree<String>(preorder);    //������������������
        tbtree.preOrder();                                 //�ȸ��������
        tbtree.inOrder();                                  //�и��������
        tbtree.inOrder_previous();                         //�и������������ǰ����
        tbtree.postOrder_previous();                       //��������������ǰ����

    }
}

/*
�ȸ����������  A B D E G C F H K 
�и����������  D B G E A F H C K 
�и�������������򣩣�  K C H F A E G B D 
���������������򣩣�  A C K F H B E G D 
*/