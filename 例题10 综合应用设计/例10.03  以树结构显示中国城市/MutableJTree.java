//����10.3��  �����ṹ��ʾ�й����С�
//��	MutableJTree�����һ���Զ���������������һ����ݲ˵����ṩ�������ɾ���������ܣ�
//����������и�������ֵ�����ĺ������ʾ��������ָ���ı��ļ��С�

import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;

public class MutableJTree extends JTree implements MouseListener, ActionListener
{
    private DefaultTreeModel treeModel;                    //��ģ��
    private DefaultMutableTreeNode root;                   //����� 
    private String filename;                               //�ļ���
    private JPopupMenu popupmenu;                          //��ݲ˵�
    private JMenuItem menuitem_insert, menuitem_remove;    //�˵���

    public MutableJTree(String filename)                   //����ָ�������������ļ���
    {
        super();
        this.filename = filename;
//        this.setEditable(true);                            //�ɱ༭�����޸�����û��ס
        addPopupMenu();                                    //��ӿ�ݲ˵�
        treeModel = (DefaultTreeModel)this.getModel();     //���Ĭ����ģ��
        this.root = null;
        this.readFromFile();                               //��ָ���ļ��ж�ȡ���ж�����Ϣ
    }

    private void addPopupMenu()                            //��ӿ�ݲ˵�
    {
        popupmenu = new JPopupMenu();                      //��ݲ˵�����
        JMenuItem menuitem_addChild = new JMenuItem("���뺢�ӽ��");
        popupmenu.add(menuitem_addChild);                  //����˵���
        menuitem_addChild.addActionListener(this);         //Ϊ�˵���ע�ᵥ���¼�������
        
        menuitem_insert = new JMenuItem("����ǰһ���ֵܽ��");
        popupmenu.add(menuitem_insert);
        menuitem_insert.addActionListener(this);
        popupmenu.addSeparator();
        
        menuitem_remove = new JMenuItem("ɾ��");
        popupmenu.add(menuitem_remove);
        menuitem_remove.addActionListener(this);
        
        this.add(popupmenu);                               //�������ӿ�ݲ˵�
        this.addMouseListener(this);                       //�����ע������¼�������
    }

    public void mouseClicked(MouseEvent mec)               //�������ʱ����
    {                                                      //ʵ��MouseListener�ӿ��еķ���
        menuitem_insert.setEnabled(true);                  //�˵�����Ч
        menuitem_remove.setEnabled(true);

        if (mec.getModifiers()==mec.BUTTON3_MASK)          //������������Ҽ�
        {
            
            if (this.root==null)                           //����
                menuitem_remove.setEnabled(false);         //ɾ���˵�����Ч
                
            if ((DefaultMutableTreeNode)this.getLastSelectedPathComponent()==root)  //ѡ�и����
                menuitem_insert.setEnabled(false);         //����ǰһ�ֵܲ˵�����Ч
            popupmenu.show(this, mec.getX(),mec.getY());   //����굥������ʾ��ݲ˵�
        }
    }

    public void mousePressed(MouseEvent mep)    {    }
    public void mouseReleased(MouseEvent mer)   {    }
    public void mouseEntered(MouseEvent mee)    {    } 
    public void mouseExited(MouseEvent mex)     {    }
    public void mouseDragged(MouseEvent med)    {    }
    
    public void actionPerformed(ActionEvent e)                  //�����˵���ʱ����ִ��
    {
        DefaultMutableTreeNode selectnode=null, parent=null;
        selectnode = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();  //��ǰѡ�н��
//        System.out.println(selectnode.toString());            //ͬnode.getUserObject().toString()

        if(e.getActionCommand().startsWith("����"))             //����"������"�˵���
        {
            String aline = JOptionPane.showInputDialog("����"); //����Ի���
            if (aline!=null)                                    //���طǿմ���ʾ��������ȷ����ť
            {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(aline);
                if (selectnode==null)
                    this.root = node;                           //�����в�������
                else
                    if(e.getActionCommand()=="���뺢�ӽ��")
                        selectnode.add(node);                   //������һ�����ӽ��
                    else
                    {
                        parent = (DefaultMutableTreeNode)selectnode.getParent();  //��ø�ĸ���
                        if (parent!=null)                       //��ĸ��㲻�ձ�ʾ�Ǹ����
                            parent.insert(node, parent.getIndex(selectnode));   //��������Ϊ�丸ĸ����ָ����ŵĺ��ӽ��
                    }
                treeModel.setRoot(this.root);                   //����rootΪ��ǰ��ģ�͵ĸ����
                this.expandPath(new TreePath(node.getPath()));  //չ����ǰ������
            }
        }

        if(e.getActionCommand()=="ɾ��")
        {
            int yes=JOptionPane.showConfirmDialog(null, "ɾ��"+selectnode.toString()+"��㼰������?  ", "ѯ��", JOptionPane.YES_NO_OPTION);
            if (yes==0)                                         //����Yes��ť
                if (selectnode==root)
                {
                    this.root = null;                           //ɾ����rootΪ������
                    treeModel.setRoot(this.root);
                }
                else
                {
                    parent = (DefaultMutableTreeNode)selectnode.getParent();
                    selectnode.removeFromParent();
                    treeModel.setRoot(this.root);
                    this.expandPath(new TreePath(parent.getPath())); //չ����ǰɾ�����ĸ�ĸ���
                }
        }
    }               

    private void readFromFile()                            //��ָ���ļ��ж�ȡ���ж���
    { 
        try
        {
            FileReader fr = new FileReader(this.filename); //�ļ��ַ�������
            BufferedReader br = new BufferedReader(fr);    //�����ַ�������
            String aline = br.readLine();                  //��ȡһ���ַ���������������ʱ����null
            while (aline!=null)
            {
                insert(root, aline);                       //����һ�����
                aline = br.readLine();    
            } 
            br.close();                                    //�ȹرջ�����
            fr.close();                                    //�ٹر��ļ���
        }
        catch (IOException e)                              //���ļ������ڣ��򲻶�ȡ
        {}

        treeModel.setRoot(root);                           //����rootΪ��ǰ��ģ�͵ĸ����
    }

    private void insert(DefaultMutableTreeNode node, String aline)   //��aline���뵽��nodeΪ���������У��ݹ鷽��
    {
        if (this.root==null)
            this.root = new DefaultMutableTreeNode(aline);           //���������
        else
        {
            aline = aline.substring(1);                              //ȥ������һ��ǰ׺'\t'
            if (aline.charAt(0)!='\t')
                node.add(new DefaultMutableTreeNode(aline));         //������㲢������Ϊnode�����һ�����ӽ��
            else
                insert((DefaultMutableTreeNode)node.getLastChild(), aline);  //��aline���뵽��node�����һ�����ӽ��Ϊ����������
        }
    }

    public void writeToFile()                              //��ָ���ļ�д�����е����н�����
    {
        try
        {
            FileWriter fw = new FileWriter(this.filename); //�ļ��ַ������
            fw.write(toString(root,""));                   //��һ�����дӸ���ʼ������Ӧ���ַ���д��ָ���ı��ļ�
            fw.close();
        }
        catch (IOException ex)
        {}
    }
    
    private String toString(DefaultMutableTreeNode node, String tab)   //�ȸ����������nodeΪ�����������ݹ��㷨
    {                                                      //���ĺ������ʾ����tab����ָ��������
        String str="";
        if (node!=null)
        {
            str = tab+node.toString()+"\r\n";
            int n=node.getChildCount();                    //��ú��ӽ�����
            for (int i=0; i<n; i++)
                str += toString((DefaultMutableTreeNode)node.getChildAt(i), tab+"\t");    //�ݹ����
        }
        return str;
    }    
}

/*
����������д�ļ�������
    public void finalize()
    {
        writeToFile();
    }

//        tree.setCellEditor(TreeCellEditor cellEditor)
//        tree.setDragEnabled(true);
 
        System.out.println("tree.isEnabled() "+tree.isEnabled());
        System.out.println("tree.isEditing() "+tree.isEditing());
        System.out.println("tree.isEditable() "+tree.isEditable());
//    void updateDragEnabled(boolean dragEnabled)  //���û����
//        {
//        tree.setDragEnabled(dragEnabled);
//    }

}


*/