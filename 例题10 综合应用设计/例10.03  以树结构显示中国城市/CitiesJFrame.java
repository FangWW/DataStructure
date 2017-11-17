//����10.3��  �����ṹ��ʾ�й����С�
//��	CitiesJFrame�����Ӧ�ó���Ĵ���

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class CitiesJFrame extends JFrame implements TreeSelectionListener, WindowListener
{
    private MutableJTree tree;                             //�Զ�����������֧�ֲ����ɾ������
    private DefaultListModel listModel;                    //Ĭ���б��ģ��

    public CitiesJFrame(String filename)                   //����ָ�������������ļ���
    {
        super("����");
        Dimension dim = getToolkit().getScreenSize();      //�����Ļ�ֱ���
        this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);     //���ھ���
        this.getContentPane().setLayout(new GridLayout(1,2));   //���񲼾�

        this.tree = new MutableJTree(filename);            //�ļ���
        this.tree.addTreeSelectionListener(this);          //�����ע��ѡ���¼�������
        this.getContentPane().add(new JScrollPane(this.tree));

        this.listModel = new DefaultListModel();           //Ĭ���б��ģ��
        this.getContentPane().add(new JScrollPane(new JList(this.listModel)));
        this.tree.addSelectionRow(0);                      //ѡ�����ĸ���㣬��ִ��valueChanged()����

        this.addWindowListener(this);                      //ע�ᴰ���¼�������
        this.setVisible(true);
    }
    public CitiesJFrame()
    {
        this("cities.txt");                                //ָ��Ĭ���ļ���
    }

    public void valueChanged(TreeSelectionEvent e)         //ѡ�н��ʱ
    {
        DefaultMutableTreeNode selectnode = (DefaultMutableTreeNode)this.tree.getLastSelectedPathComponent();  //��ǰѡ�н��
        this.listModel.clear();                            //����б��
        if (selectnode==null)
            return;
        int n=selectnode.getChildCount();                  //��õ�ǰ���ĺ��ӽ����
        for (int i=0; i<n; i++)
            this.listModel.addElement(selectnode.getChildAt(i).toString());//�б��ģ�����������
        this.tree.expandPath(e.getPath());                 //չ����ǰѡ�н��
    }

    public void windowClosing(WindowEvent e)               //�رմ����¼�������
    {
        this.tree.writeToFile();                           //���������н�������ĺ������ʾ��д��ָ���ı��ļ�
        System.exit(0);                                    //Ӧ�ó�����ִֹ��
    }

    public void windowOpened(WindowEvent e)         {  }
    public void windowActivated(WindowEvent e)      {  }
    public void windowDeactivated(WindowEvent e)    {  }
    public void windowClosed(WindowEvent e)         {  }
    public void windowIconified(WindowEvent e)      {  }
    public void windowDeiconified(WindowEvent e)    {  }
    
    public static void main(String[] args) 
    {
        new CitiesJFrame();
    }
 }

/*

*/