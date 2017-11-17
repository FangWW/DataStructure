//【例10.3】  以树结构显示中国城市。
//②	MutableJTree类设计一个自定义的树组件，带有一个快捷菜单，提供插入结点和删除子树功能；
//该组件将树中各结点对象值以树的横向凹入表示法保存在指定文本文件中。

import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;

public class MutableJTree extends JTree implements MouseListener, ActionListener
{
    private DefaultTreeModel treeModel;                    //树模型
    private DefaultMutableTreeNode root;                   //根结点 
    private String filename;                               //文件名
    private JPopupMenu popupmenu;                          //快捷菜单
    private JMenuItem menuitem_insert, menuitem_remove;    //菜单项

    public MutableJTree(String filename)                   //参数指定保存树结点的文件名
    {
        super();
        this.filename = filename;
//        this.setEditable(true);                            //可编辑，但修改内容没记住
        addPopupMenu();                                    //添加快捷菜单
        treeModel = (DefaultTreeModel)this.getModel();     //获得默认树模型
        this.root = null;
        this.readFromFile();                               //从指定文件中读取已有对象信息
    }

    private void addPopupMenu()                            //添加快捷菜单
    {
        popupmenu = new JPopupMenu();                      //快捷菜单对象
        JMenuItem menuitem_addChild = new JMenuItem("插入孩子结点");
        popupmenu.add(menuitem_addChild);                  //加入菜单项
        menuitem_addChild.addActionListener(this);         //为菜单项注册单击事件监听器
        
        menuitem_insert = new JMenuItem("插入前一个兄弟结点");
        popupmenu.add(menuitem_insert);
        menuitem_insert.addActionListener(this);
        popupmenu.addSeparator();
        
        menuitem_remove = new JMenuItem("删除");
        popupmenu.add(menuitem_remove);
        menuitem_remove.addActionListener(this);
        
        this.add(popupmenu);                               //树组件添加快捷菜单
        this.addMouseListener(this);                       //树组件注册鼠标事件监听器
    }

    public void mouseClicked(MouseEvent mec)               //单击鼠标时触发
    {                                                      //实现MouseListener接口中的方法
        menuitem_insert.setEnabled(true);                  //菜单项有效
        menuitem_remove.setEnabled(true);

        if (mec.getModifiers()==mec.BUTTON3_MASK)          //单击的是鼠标右键
        {
            
            if (this.root==null)                           //空树
                menuitem_remove.setEnabled(false);         //删除菜单项无效
                
            if ((DefaultMutableTreeNode)this.getLastSelectedPathComponent()==root)  //选中根结点
                menuitem_insert.setEnabled(false);         //插入前一兄弟菜单项无效
            popupmenu.show(this, mec.getX(),mec.getY());   //在鼠标单击处显示快捷菜单
        }
    }

    public void mousePressed(MouseEvent mep)    {    }
    public void mouseReleased(MouseEvent mer)   {    }
    public void mouseEntered(MouseEvent mee)    {    } 
    public void mouseExited(MouseEvent mex)     {    }
    public void mouseDragged(MouseEvent med)    {    }
    
    public void actionPerformed(ActionEvent e)                  //单击菜单项时触发执行
    {
        DefaultMutableTreeNode selectnode=null, parent=null;
        selectnode = (DefaultMutableTreeNode)this.getLastSelectedPathComponent();  //当前选中结点
//        System.out.println(selectnode.toString());            //同node.getUserObject().toString()

        if(e.getActionCommand().startsWith("插入"))             //两个"插入结点"菜单项
        {
            String aline = JOptionPane.showInputDialog("输入"); //输入对话框
            if (aline!=null)                                    //返回非空串表示单击的是确定按钮
            {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(aline);
                if (selectnode==null)
                    this.root = node;                           //空树中插入根结点
                else
                    if(e.getActionCommand()=="插入孩子结点")
                        selectnode.add(node);                   //添加最后一个孩子结点
                    else
                    {
                        parent = (DefaultMutableTreeNode)selectnode.getParent();  //获得父母结点
                        if (parent!=null)                       //父母结点不空表示非根结点
                            parent.insert(node, parent.getIndex(selectnode));   //插入结点作为其父母结点的指定序号的孩子结点
                    }
                treeModel.setRoot(this.root);                   //设置root为当前树模型的根结点
                this.expandPath(new TreePath(node.getPath()));  //展开当前插入结点
            }
        }

        if(e.getActionCommand()=="删除")
        {
            int yes=JOptionPane.showConfirmDialog(null, "删除"+selectnode.toString()+"结点及其子树?  ", "询问", JOptionPane.YES_NO_OPTION);
            if (yes==0)                                         //单击Yes按钮
                if (selectnode==root)
                {
                    this.root = null;                           //删除以root为根的树
                    treeModel.setRoot(this.root);
                }
                else
                {
                    parent = (DefaultMutableTreeNode)selectnode.getParent();
                    selectnode.removeFromParent();
                    treeModel.setRoot(this.root);
                    this.expandPath(new TreePath(parent.getPath())); //展开当前删除结点的父母结点
                }
        }
    }               

    private void readFromFile()                            //从指定文件中读取已有对象
    { 
        try
        {
            FileReader fr = new FileReader(this.filename); //文件字符输入流
            BufferedReader br = new BufferedReader(fr);    //缓冲字符输入流
            String aline = br.readLine();                  //读取一行字符串，输入流结束时返回null
            while (aline!=null)
            {
                insert(root, aline);                       //插入一个结点
                aline = br.readLine();    
            } 
            br.close();                                    //先关闭缓冲流
            fr.close();                                    //再关闭文件流
        }
        catch (IOException e)                              //若文件不存在，则不读取
        {}

        treeModel.setRoot(root);                           //设置root为当前树模型的根结点
    }

    private void insert(DefaultMutableTreeNode node, String aline)   //将aline插入到以node为根的子树中，递归方法
    {
        if (this.root==null)
            this.root = new DefaultMutableTreeNode(aline);           //创建根结点
        else
        {
            aline = aline.substring(1);                              //去除串中一个前缀'\t'
            if (aline.charAt(0)!='\t')
                node.add(new DefaultMutableTreeNode(aline));         //创建结点并插入作为node的最后一个孩子结点
            else
                insert((DefaultMutableTreeNode)node.getLastChild(), aline);  //将aline插入到以node的最后一个孩子结点为根的子树中
        }
    }

    public void writeToFile()                              //向指定文件写入树中的所有结点对象
    {
        try
        {
            FileWriter fw = new FileWriter(this.filename); //文件字符输出流
            fw.write(toString(root,""));                   //将一棵树中从根开始各结点对应的字符串写入指定文本文件
            fw.close();
        }
        catch (IOException ex)
        {}
    }
    
    private String toString(DefaultMutableTreeNode node, String tab)   //先根次序遍历以node为根的子树，递归算法
    {                                                      //树的横向凹入表示法，tab参数指定缩进量
        String str="";
        if (node!=null)
        {
            str = tab+node.toString()+"\r\n";
            int n=node.getChildCount();                    //获得孩子结点个数
            for (int i=0; i<n; i++)
                str += toString((DefaultMutableTreeNode)node.getChildAt(i), tab+"\t");    //递归调用
        }
        return str;
    }    
}

/*
用析构方法写文件，不行
    public void finalize()
    {
        writeToFile();
    }

//        tree.setCellEditor(TreeCellEditor cellEditor)
//        tree.setDragEnabled(true);
 
        System.out.println("tree.isEnabled() "+tree.isEnabled());
        System.out.println("tree.isEditing() "+tree.isEditing());
        System.out.println("tree.isEditable() "+tree.isEditable());
//    void updateDragEnabled(boolean dragEnabled)  //这个没看懂
//        {
//        tree.setDragEnabled(dragEnabled);
//    }

}


*/