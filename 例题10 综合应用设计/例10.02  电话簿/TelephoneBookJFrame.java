//【例10.2】  电话簿。
//②	TelephoneBookJFrame类实现电话簿的存储、管理和图形用户界面。

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;

public class TelephoneBookJFrame extends JFrame implements ListSelectionListener, WindowListener
{
    private String filename;                               //文件名
    private TreeSet<Friend> tbook;                         //电话簿，使用一个树集合存储多个Friend对象
    
    private JList list;                                    //列表框
    private DefaultListModel listModel;                    //默认列表框模型

    private JTable table;                                  //表格组件
    private DefaultTableModel tableModel;                  //默认表格模型

    public TelephoneBookJFrame(String filename)            //构造图形用户界面
    {
        super("电话簿");
        Dimension dim = getToolkit().getScreenSize();      //获得屏幕分辨率
        this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);     //窗口居中
        this.addWindowListener(this);                      //注册窗口事件监听器

        this.filename = filename;
        tbook = new TreeSet<Friend>();
        this.readFromFile();                               //从指定文件中读取已有对象信息
        
        JSplitPane splitter_h = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);   //分割窗格，水平分割，左右
        splitter_h.setDividerLocation(60);                 //设置分隔条的位置
        this.getContentPane().add(splitter_h);
        JSplitPane splitter_v = new JSplitPane(JSplitPane.VERTICAL_SPLIT);     //分割窗格，垂直分割，上下
        splitter_v.setDividerLocation(dim.height/4);       //设置分隔条的位置，水平中间分割

        this.listModel = new DefaultListModel();           //默认列表框模式
        this.listModel.addElement("全部"); 
        this.getFamilyName();                              //列表框添加电话簿中的所有姓氏
        this.list = new JList(listModel);                  //创建列表框
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //设置单选模式，默认为多选

        this.list.addListSelectionListener(this);          //列表框注册选择事件监听器
        splitter_h.add(new JScrollPane(this.list));        //添加在滚动窗格中
        splitter_h.add(splitter_v);
        
        String[] columnNames = {"姓名","电话号码"};        //表格各列的中文标题
        this.tableModel = new DefaultTableModel(columnNames,0);  //默认表格模式，指定列标题，0行
        this.table = new JTable(tableModel);               //创建空表格，有列标题
        this.list.setSelectedIndex(0);                     //列表框选中第一项，自动执行列表框的选择事件处理方法valueChanged()
        splitter_v.add(new JScrollPane(table));
        splitter_v.add(new FriendJPanel());
        this.setVisible(true);
    }
    public TelephoneBookJFrame() 
    {
        this("friends.dat");                               //指定默认文件名
    }
    
    public void valueChanged(ListSelectionEvent e)         //选择事件处理方法
    {
        if (e.getSource()==this.list)                      //选中列表框的数据项时触发
        {
            String selected = (String)list.getSelectedValue(); //返回列表框选中数据项对象
//        System.out.println(" "+selected);  //一次选择，执行两次该事件？？为什么
            this.searchByName(selected);                   //返回选中姓氏的对象并更新表格
        }
    }

    private void getFamilyName()                           //JList添加电话簿中的所有姓氏
    {
        if (!tbook.isEmpty())
        {
            Iterator it = tbook.iterator();                //返回一个迭代器对象
            while (it.hasNext())                           //若有后继元素，使用迭代器遍历一个集合
            {
                Friend f = (Friend)it.next();              //返回后继元素
                String familyname = f.getName().charAt(0)+"";    //获得姓氏
                if (!this.listModel.contains(familyname))
                    this.listModel.addElement(familyname); //列表框模型添加数据项
            }
        }
    }

    private void searchByName(String familyname)           //更新表格，参数指定姓氏
    {
        if (!tbook.isEmpty() && familyname!=null && familyname!="")
        {
            for (int i=this.tableModel.getRowCount()-1; i>=0; i--)   //清空表格
                this.tableModel.removeRow(i);              

            Iterator it = tbook.iterator();
            while (it.hasNext())
            {
                Friend f = (Friend)it.next();
                if (familyname=="全部" || f.getName().charAt(0)==familyname.charAt(0))
                    this.tableModel.addRow(f.toArray());   //表格添加一行，参数数组指定各列值
            }
        }
    }

    //私有内部类，构造一个面板，包括2个文本行和添加、删除、查找等按钮
    private class FriendJPanel extends JPanel implements ActionListener
    {
        private JTextField text_name;                      //文本行，输入姓名
        private JTextField text_code;                      //文本行，输入电话号码

        public FriendJPanel()
        {
            this.setLayout(new GridLayout(3,1));
            JPanel p1 = new JPanel();
            this.add(p1);
            p1.add(new JLabel("姓　　名"));
            this.text_name = new JTextField("姓名非空",20);
            p1.add(text_name);
       
            JPanel p2 = new JPanel();
            this.add(p2);
            p2.add(new JLabel("电话号码"));
            this.text_code = new JTextField("12345678901", 20);
            p2.add(text_code);
        
            JPanel p3 = new JPanel();
            this.add(p3);
            JButton button_add = new JButton("添加");
            p3.add(button_add);
            button_add.addActionListener(this);
        
            JButton button_delete = new JButton("删除");
            p3.add(button_delete);
            button_delete.addActionListener(this);

            JButton button_search = new JButton("按电话号码查找");
            p3.add(button_search);
            button_search.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)         //单击事件处理程序
        {
            if (e.getActionCommand().equals("添加"))       //单击添加按钮
            {
                String name=text_name.getText();
                String code=text_code.getText();
                if (name!="")
                {
                    Friend f = new Friend(name, code);
                    if (!tbook.contains(f))                    //电话簿不能插入重复对象
                    {
                        tbook.add(f);                          //添加对象，TreeSet元素重复时不插入，没提示也不抛出异常
                        String familyname = name.charAt(0)+""; //返回姓名的第一个字符
                        if (list.getSelectedValue().equals(familyname))
                            tableModel.addRow(f.toArray());     
                        else
                        {
                            if (!listModel.contains(familyname))          //列表框中添加不重复元素
                                listModel.addElement(familyname);
                            list.setSelectedValue(familyname,true);       //设置列表框选中项
                        }
                    }
                    else                                      //使用标准对话框显示提示信息
                        JOptionPane.showMessageDialog(null, "不能添加空对象或重复对象"+f.toString(), "提示", JOptionPane.DEFAULT_OPTION);
                }
                else
                    JOptionPane.showMessageDialog(null, "请输入姓名", "提示", JOptionPane.DEFAULT_OPTION);
            }

            if (e.getActionCommand().equals("按电话号码查找"))
            {
                searchByCode(text_code.getText());         //查找电话号码
            }
 
            if (e.getActionCommand().equals("删除"))
            {
                int i = table.getSelectedRow();            //表格当前选中行号
                int yes=JOptionPane.showConfirmDialog(null, "删除"+i+"行？", "询问", JOptionPane.YES_NO_OPTION);//询问对话框包括Yes和No按钮
                if (yes==0)                                //单击Yes按钮
                {
                    String name = (String)table.getValueAt(i,0);
                    String code = (String)table.getValueAt(i,1);
                    tbook.remove(new Friend(name, code));  //电话簿中删除对象
                    tableModel.removeRow(i);               //表格中删除一行
                    listModel.removeElement(name.charAt(0)+""); //列表框中删除指定姓氏 
                }
            }
       }
    }//FriendJPanel内部类结束

    private void searchByCode(String code)                 //查找电话号码，结果显示在表格中
    {
        if (!tbook.isEmpty())
        {
            Friend find = null;
            Iterator it = tbook.iterator();
            while (find==null && it.hasNext())
            {
                Friend f = (Friend)it.next();
                if (f.getPhonecode().equals(code))         //比较电话号码字符串
                    find = f;
            }
            
            if (find!=null)                                //若查找成功
            {
                for (int i=tableModel.getRowCount()-1; i>=0; i--) //清空表格
                    this.tableModel.removeRow(i);                    
                this.tableModel.addRow(find.toArray());  
            }
            else 
                JOptionPane.showMessageDialog(this, "未查找到满足条件的数据！", "提示", JOptionPane.DEFAULT_OPTION);
        }
        else 
            JOptionPane.showMessageDialog(this, "不能查询，电话簿空！", "提示", JOptionPane.DEFAULT_OPTION);
    }

    private void readFromFile()                            //从指定文件中读取已有对象
    {
        try
        {
            FileInputStream fin = new FileInputStream(this.filename);  //文件字节输入流
            ObjectInputStream objin = new ObjectInputStream(fin);      //对象字节输入流
            while (true)                                   //输入流未结束时
            try
            {
                Friend f = (Friend)objin.readObject();     //读取一个对象，输入流结束时抛出ClassNotFoundException异常
                tbook.add(f);                              //添加到电话簿
            }
            catch (ClassNotFoundException e)
            {
                break;
            }
            objin.close();                                 //先关闭对象流
            fin.close();                                   //再关闭文件流
        }
        catch (IOException e)                              //若文件不存在，则不读取
        {}
    }

    private void writeToFile()                             //向指定文件写入电话簿中的所有对象
    {
        try
        {
            FileOutputStream fout = new FileOutputStream(this.filename);  //文件字节输出流
            ObjectOutputStream objout = new ObjectOutputStream(fout);     //对象字节输出流
            if (!tbook.isEmpty())
            {
                Iterator it = tbook.iterator();
                while (it.hasNext())
                    objout.writeObject((Friend)it.next()); //写入一个对象
            }
            objout.close();
            fout.close();
        }
        catch (IOException ex)
        {}
    }
    
    public void windowClosing(WindowEvent e)               //关闭窗口事件处理方法
    {
        this.writeToFile();                                //将电话簿中所有对象写入指定文件
        System.exit(0);                                    //应用程序终止执行
    }

    public void windowOpened(WindowEvent e)         {  }
    public void windowActivated(WindowEvent e)      {  }
    public void windowDeactivated(WindowEvent e)    {  }
    public void windowClosed(WindowEvent e)         {  }
    public void windowIconified(WindowEvent e)      {  }
    public void windowDeiconified(WindowEvent e)    {  }

    public static void main(String args[]) throws IOException
    {
        new TelephoneBookJFrame();                         //默认文件名为"frindes.dat"
    }    
}
    
/*
//        this.table_friends.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
思考题：增加以下功能。
?	检查电话号码的位数。
?	增加电话簿的修改功能。
选中表格一列,能够修改,但修改过内容没有存住?
            JButton button_update = new JButton("更新");
            p3.add(button_update);
            button_update.addActionListener(this);
        
    private DefaultTableColumnModel tableColumn;
    private DefaultListSelectionModel listselect; 
        tableColumn = new DefaultTableColumnModel();

        this.listselect = new DefaultListSelectionModel(); 
        this.listselect.addListSelectionListener(this); 

        this.table = new JTable(tableModel, tableColumn, listselect);               //创建空表，有列标题
        if (e.getSource()==this.listselect)                //选中表格一行时触发
        {
        System.out.println(" this.listselect");  

            }

*/
