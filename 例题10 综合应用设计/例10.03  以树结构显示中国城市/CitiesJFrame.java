//【例10.3】  以树结构显示中国城市。
//①	CitiesJFrame类设计应用程序的窗口

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class CitiesJFrame extends JFrame implements TreeSelectionListener, WindowListener
{
    private MutableJTree tree;                             //自定义的树组件，支持插入和删除操作
    private DefaultListModel listModel;                    //默认列表框模型

    public CitiesJFrame(String filename)                   //参数指定保存树结点的文件名
    {
        super("城市");
        Dimension dim = getToolkit().getScreenSize();      //获得屏幕分辨率
        this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);     //窗口居中
        this.getContentPane().setLayout(new GridLayout(1,2));   //网格布局

        this.tree = new MutableJTree(filename);            //文件名
        this.tree.addTreeSelectionListener(this);          //树组件注册选择事件监听器
        this.getContentPane().add(new JScrollPane(this.tree));

        this.listModel = new DefaultListModel();           //默认列表框模型
        this.getContentPane().add(new JScrollPane(new JList(this.listModel)));
        this.tree.addSelectionRow(0);                      //选中树的根结点，将执行valueChanged()方法

        this.addWindowListener(this);                      //注册窗口事件监听器
        this.setVisible(true);
    }
    public CitiesJFrame()
    {
        this("cities.txt");                                //指定默认文件名
    }

    public void valueChanged(TreeSelectionEvent e)         //选中结点时
    {
        DefaultMutableTreeNode selectnode = (DefaultMutableTreeNode)this.tree.getLastSelectedPathComponent();  //当前选中结点
        this.listModel.clear();                            //清空列表框
        if (selectnode==null)
            return;
        int n=selectnode.getChildCount();                  //获得当前结点的孩子结点数
        for (int i=0; i<n; i++)
            this.listModel.addElement(selectnode.getChildAt(i).toString());//列表框模型添加数据项
        this.tree.expandPath(e.getPath());                 //展开当前选中结点
    }

    public void windowClosing(WindowEvent e)               //关闭窗口事件处理方法
    {
        this.tree.writeToFile();                           //将树中所有结点以树的横向凹入表示法写入指定文本文件
        System.exit(0);                                    //应用程序终止执行
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