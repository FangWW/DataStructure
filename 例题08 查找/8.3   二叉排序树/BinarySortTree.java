//����8.2��  �����������Ĳ���Ͳ��Ҳ�����

package dataStructure.tree;
import dataStructure.tree.BinaryNode;            //�������Ķ�����������

public class BinarySortTree<E>                   //������������
{
    protected BinaryNode<E> root;                //�����

    public BinarySortTree()                      //����ն���������
    {
        root=null;
    }
    public boolean isEmpty()                     //�ж��Ƿ�ն�����
    {
        return this.root==null;
    }

    public void inOrder()                        //�и������������������
    {
        System.out.print("\n�и��������������������  ");
        if (root!=null)
            inOrder(root);
        System.out.println();
    }    
    private void inOrder(BinaryNode<E> p)        //�и����������p���Ϊ�����Ӷ�����
    {
        if (p!=null)
        {
            inOrder(p.left);
            System.out.print(p.data+" ");
            inOrder(p.right);
        }
    }

    public BinaryNode<E> search(E value)         //����ֵΪvalue�Ľ�㣬�ǵݹ��㷨
    {                                            //�����ҳɹ����ؽ�㣬���򷵻�null
        if (value==null || !(value instanceof Comparable))
            return null;                     
        Comparable cmpobj = (Comparable)value;
        BinaryNode<E> p=this.root;
        while (p!=null && cmpobj.compareTo(p.data)!=0)     //��û�������
        {
            System.out.print(p.data+"? ");       //��ʾ���Ҿ����Ľ��ֵ����ʡ��
            if (cmpobj.compareTo(p.data)<0)      //��value��С
                p=p.left;                        //����������
            else
                p=p.right;                       //����������
        }
        return p;
    }

    public boolean insert(E value)               //�����㣬������ؼ����ظ��Ľ��
    {                                            //������ɹ�����true
        if (value==null || !(value instanceof Comparable)) //���ܲ���ն���򲻿ɱȽϴ�С�Ķ���
            return false;                     
        if (root==null)
        {
            root=new BinaryNode<E>(value);       //���������
            return true;
        }
        return insert(value, root);              //����value����rootΪ���Ķ�����������
    }  

    private boolean insert(E value, BinaryNode<E> p)  //��value���뵽��pΪ����������
    {                                                 //�ݹ��㷨
        Comparable cmpobj = (Comparable)value;
        if (cmpobj.compareTo(p.data)==0)
            return false;                             //������ؼ����ظ��Ľ�� 

        if (cmpobj.compareTo(p.data)<0)
            if (p.left==null)
            {
               p.left = new BinaryNode<E>(value);     //����Ҷ�ӽ����Ϊp������
               return true;
            }
            else
               return insert(value, p.left);          //��value���뵽p����������
        else
            if (p.right==null)
            {
               p.right = new BinaryNode<E>(value);    //����Ҷ�ӽ����Ϊp���Һ���
               return true;
            }
            else
               return insert(value, p.right);         //��value���뵽p����������
    }

    public E remove(E value)                               //ɾ�����
    {                                                      //��ɾ���ɹ�����ɾ�����ֵ�����򷵻�null
        if (root==null || value==null || !(value instanceof Comparable))
            return null;                     
        return remove(value, root, null);                  //����rootΪ���Ķ�����������ɾ��value
    }  

    private E remove(E value, BinaryNode<E> p, BinaryNode<E> parent)   //����pΪ����������ɾ��value
    {                                                      //parent��p�ĸ�ĸ��㣬�ݹ��㷨
        Comparable cmpobj = (Comparable)value;
        if (p!=null)
        {
            if (cmpobj.compareTo(p.data)<0)
                return remove(value, p.left, p);
            else
                if (cmpobj.compareTo(p.data)>0)
                    return remove(value, p.right, p);
                else                                       //�ҵ���ɾ�����p
                    if (p.left!=null && p.right!=null)     //p��2�Ƚ��
                    {
                        BinaryNode<E> innext = p.right;    //Ѱ��p���и������µĺ�̽��innext
                        while (innext.left!=null)
                            innext = innext.left;
                        p.data = innext.data;              //�Ժ�̽��ֵ�滻
                        return remove(p.data, p.right, p);
                    }
                    else                                   //p��1�Ⱥ�Ҷ�ӽ��
                    {
                        if (parent==null)                  //ɾ������㣬��p==root
                        {
                            if (p.left!=null)
                                root = p.left;
                            else
                                root = p.right;
                            return p.data;                 //����ɾ�����pֵ
                        }
                        
                        if (p==parent.left)                //p��parent������
                            if (p.left!=null)
                                parent.left = p.left;      //��p���������
                            else
                                parent.left = p.right;
                        else                               //p��parent���Һ���
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
        System.out.print("���룺 ");
        int[] key={54,18,66,87,36,12,54,81,15,76,57,6,40,99,85,99};
        for (int i=0; i<key.length; i++)
            if (bstree.insertNode(new Integer(key[i])))            //���������ɹ�
                System.out.print(key[i]+" ");
        bstree.inOrder();

        Integer element = new Integer(key[key.length-1]);
        System.out.println("����"+element+", "+(bstree.search(element)!=null?"":"��")+"�ɹ� ");
        element = new Integer(50);
        System.out.println("����"+element+", "+(bstree.search(element)!=null?"":"��")+"�ɹ� ");

        int value=66;
        System.out.print("ɾ��"+value+", "+(bstree.remove(new Integer(value))!=null?"":"��")+"�ɹ� ");
        bstree.inOrder();
    }


 //˼�����
    public BinaryNode<E> searchNode(E value)     //����ֵΪvalue�Ľ��
    {                                            //�����ҳɹ����ؽ�㣬���򷵻�null
        if (value==null || !(value instanceof Comparable))
            return null;                     
        return searchNode(value, root);
    }
    private BinaryNode<E> searchNode(E value, BinaryNode<E> p) //����ֵΪvalue�Ľ�㣬�ݹ��㷨
    {
        if (p!=null)
        {
            Comparable cmpobj = (Comparable)value;
            if (cmpobj.compareTo(p.data)==0)          //������������ȣ����ҳɹ�
                return p; 
            System.out.print(p.data+"? ");
            if (cmpobj.compareTo(p.data)<0)
                return searchNode(value, p.left);     //���������в���
            else
                return searchNode(value, p.right);    //���������в���
        }
        return null;
    }

    public boolean insertNode(E value)                //�����㣬�ǵݹ��㷨
    {
        if (value==null || !(value instanceof Comparable))
            return false;                     
            
        if (root==null)
            root=new BinaryNode<E>(value);       //���������
        else
        {
            BinaryNode<E> p=this.root, parent=null;
            Comparable cmpobj = (Comparable)value;
            while (p!=null)
            {
                parent = p;
                if (cmpobj.compareTo(p.data)==0)
                    return false;                //�������ظ��ؼ���    

                if (cmpobj.compareTo(p.data)<0)
                    p=p.left;
                else
                    p=p.right;
            }
            p=new BinaryNode<E>(value);          //����Ҷ�ӽ��p
            if(cmpobj.compareTo(parent.data)<0)
               parent.left = p;                  //p��Ϊparent������
            else
               parent.right = p;                 //p��Ϊparent���Һ���
        }
        return true;
   }  

}


/*
���룺 54 18 66 87 36 12 81 15 76 57 6 40 99 85 
�и��������������������  6 12 15 18 36 40 54 57 66 76 81 85 87 99 
54? 66? 87? 81? ����85, �ɹ� 
54? 18? 36? 40? ����50, ���ɹ� 
ɾ��66, �ɹ� 
�и��������������������  6 12 15 18 36 40 54 57 76 81 85 87 99 

*/