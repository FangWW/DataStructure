//����10.2��  �绰����
//��	TelephoneBookJFrame��ʵ�ֵ绰���Ĵ洢�������ͼ���û����档

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;

public class TelephoneBookJFrame extends JFrame implements ListSelectionListener, WindowListener
{
    private String filename;                               //�ļ���
    private TreeSet<Friend> tbook;                         //�绰����ʹ��һ�������ϴ洢���Friend����
    
    private JList list;                                    //�б��
    private DefaultListModel listModel;                    //Ĭ���б��ģ��

    private JTable table;                                  //������
    private DefaultTableModel tableModel;                  //Ĭ�ϱ��ģ��

    public TelephoneBookJFrame(String filename)            //����ͼ���û�����
    {
        super("�绰��");
        Dimension dim = getToolkit().getScreenSize();      //�����Ļ�ֱ���
        this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);     //���ھ���
        this.addWindowListener(this);                      //ע�ᴰ���¼�������

        this.filename = filename;
        tbook = new TreeSet<Friend>();
        this.readFromFile();                               //��ָ���ļ��ж�ȡ���ж�����Ϣ
        
        JSplitPane splitter_h = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);   //�ָ��ˮƽ�ָ����
        splitter_h.setDividerLocation(60);                 //���÷ָ�����λ��
        this.getContentPane().add(splitter_h);
        JSplitPane splitter_v = new JSplitPane(JSplitPane.VERTICAL_SPLIT);     //�ָ�񣬴�ֱ�ָ����
        splitter_v.setDividerLocation(dim.height/4);       //���÷ָ�����λ�ã�ˮƽ�м�ָ�

        this.listModel = new DefaultListModel();           //Ĭ���б��ģʽ
        this.listModel.addElement("ȫ��"); 
        this.getFamilyName();                              //�б����ӵ绰���е���������
        this.list = new JList(listModel);                  //�����б��
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //���õ�ѡģʽ��Ĭ��Ϊ��ѡ

        this.list.addListSelectionListener(this);          //�б��ע��ѡ���¼�������
        splitter_h.add(new JScrollPane(this.list));        //����ڹ���������
        splitter_h.add(splitter_v);
        
        String[] columnNames = {"����","�绰����"};        //�����е����ı���
        this.tableModel = new DefaultTableModel(columnNames,0);  //Ĭ�ϱ��ģʽ��ָ���б��⣬0��
        this.table = new JTable(tableModel);               //�����ձ�����б���
        this.list.setSelectedIndex(0);                     //�б��ѡ�е�һ��Զ�ִ���б���ѡ���¼�������valueChanged()
        splitter_v.add(new JScrollPane(table));
        splitter_v.add(new FriendJPanel());
        this.setVisible(true);
    }
    public TelephoneBookJFrame() 
    {
        this("friends.dat");                               //ָ��Ĭ���ļ���
    }
    
    public void valueChanged(ListSelectionEvent e)         //ѡ���¼�������
    {
        if (e.getSource()==this.list)                      //ѡ���б���������ʱ����
        {
            String selected = (String)list.getSelectedValue(); //�����б��ѡ�����������
//        System.out.println(" "+selected);  //һ��ѡ��ִ�����θ��¼�����Ϊʲô
            this.searchByName(selected);                   //����ѡ�����ϵĶ��󲢸��±��
        }
    }

    private void getFamilyName()                           //JList��ӵ绰���е���������
    {
        if (!tbook.isEmpty())
        {
            Iterator it = tbook.iterator();                //����һ������������
            while (it.hasNext())                           //���к��Ԫ�أ�ʹ�õ���������һ������
            {
                Friend f = (Friend)it.next();              //���غ��Ԫ��
                String familyname = f.getName().charAt(0)+"";    //�������
                if (!this.listModel.contains(familyname))
                    this.listModel.addElement(familyname); //�б��ģ�����������
            }
        }
    }

    private void searchByName(String familyname)           //���±�񣬲���ָ������
    {
        if (!tbook.isEmpty() && familyname!=null && familyname!="")
        {
            for (int i=this.tableModel.getRowCount()-1; i>=0; i--)   //��ձ��
                this.tableModel.removeRow(i);              

            Iterator it = tbook.iterator();
            while (it.hasNext())
            {
                Friend f = (Friend)it.next();
                if (familyname=="ȫ��" || f.getName().charAt(0)==familyname.charAt(0))
                    this.tableModel.addRow(f.toArray());   //������һ�У���������ָ������ֵ
            }
        }
    }

    //˽���ڲ��࣬����һ����壬����2���ı��к���ӡ�ɾ�������ҵȰ�ť
    private class FriendJPanel extends JPanel implements ActionListener
    {
        private JTextField text_name;                      //�ı��У���������
        private JTextField text_code;                      //�ı��У�����绰����

        public FriendJPanel()
        {
            this.setLayout(new GridLayout(3,1));
            JPanel p1 = new JPanel();
            this.add(p1);
            p1.add(new JLabel("�ա�����"));
            this.text_name = new JTextField("�����ǿ�",20);
            p1.add(text_name);
       
            JPanel p2 = new JPanel();
            this.add(p2);
            p2.add(new JLabel("�绰����"));
            this.text_code = new JTextField("12345678901", 20);
            p2.add(text_code);
        
            JPanel p3 = new JPanel();
            this.add(p3);
            JButton button_add = new JButton("���");
            p3.add(button_add);
            button_add.addActionListener(this);
        
            JButton button_delete = new JButton("ɾ��");
            p3.add(button_delete);
            button_delete.addActionListener(this);

            JButton button_search = new JButton("���绰�������");
            p3.add(button_search);
            button_search.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)         //�����¼��������
        {
            if (e.getActionCommand().equals("���"))       //������Ӱ�ť
            {
                String name=text_name.getText();
                String code=text_code.getText();
                if (name!="")
                {
                    Friend f = new Friend(name, code);
                    if (!tbook.contains(f))                    //�绰�����ܲ����ظ�����
                    {
                        tbook.add(f);                          //��Ӷ���TreeSetԪ���ظ�ʱ�����룬û��ʾҲ���׳��쳣
                        String familyname = name.charAt(0)+""; //���������ĵ�һ���ַ�
                        if (list.getSelectedValue().equals(familyname))
                            tableModel.addRow(f.toArray());     
                        else
                        {
                            if (!listModel.contains(familyname))          //�б������Ӳ��ظ�Ԫ��
                                listModel.addElement(familyname);
                            list.setSelectedValue(familyname,true);       //�����б��ѡ����
                        }
                    }
                    else                                      //ʹ�ñ�׼�Ի�����ʾ��ʾ��Ϣ
                        JOptionPane.showMessageDialog(null, "������ӿն�����ظ�����"+f.toString(), "��ʾ", JOptionPane.DEFAULT_OPTION);
                }
                else
                    JOptionPane.showMessageDialog(null, "����������", "��ʾ", JOptionPane.DEFAULT_OPTION);
            }

            if (e.getActionCommand().equals("���绰�������"))
            {
                searchByCode(text_code.getText());         //���ҵ绰����
            }
 
            if (e.getActionCommand().equals("ɾ��"))
            {
                int i = table.getSelectedRow();            //���ǰѡ���к�
                int yes=JOptionPane.showConfirmDialog(null, "ɾ��"+i+"�У�", "ѯ��", JOptionPane.YES_NO_OPTION);//ѯ�ʶԻ������Yes��No��ť
                if (yes==0)                                //����Yes��ť
                {
                    String name = (String)table.getValueAt(i,0);
                    String code = (String)table.getValueAt(i,1);
                    tbook.remove(new Friend(name, code));  //�绰����ɾ������
                    tableModel.removeRow(i);               //�����ɾ��һ��
                    listModel.removeElement(name.charAt(0)+""); //�б����ɾ��ָ������ 
                }
            }
       }
    }//FriendJPanel�ڲ������

    private void searchByCode(String code)                 //���ҵ绰���룬�����ʾ�ڱ����
    {
        if (!tbook.isEmpty())
        {
            Friend find = null;
            Iterator it = tbook.iterator();
            while (find==null && it.hasNext())
            {
                Friend f = (Friend)it.next();
                if (f.getPhonecode().equals(code))         //�Ƚϵ绰�����ַ���
                    find = f;
            }
            
            if (find!=null)                                //�����ҳɹ�
            {
                for (int i=tableModel.getRowCount()-1; i>=0; i--) //��ձ��
                    this.tableModel.removeRow(i);                    
                this.tableModel.addRow(find.toArray());  
            }
            else 
                JOptionPane.showMessageDialog(this, "δ���ҵ��������������ݣ�", "��ʾ", JOptionPane.DEFAULT_OPTION);
        }
        else 
            JOptionPane.showMessageDialog(this, "���ܲ�ѯ���绰���գ�", "��ʾ", JOptionPane.DEFAULT_OPTION);
    }

    private void readFromFile()                            //��ָ���ļ��ж�ȡ���ж���
    {
        try
        {
            FileInputStream fin = new FileInputStream(this.filename);  //�ļ��ֽ�������
            ObjectInputStream objin = new ObjectInputStream(fin);      //�����ֽ�������
            while (true)                                   //������δ����ʱ
            try
            {
                Friend f = (Friend)objin.readObject();     //��ȡһ����������������ʱ�׳�ClassNotFoundException�쳣
                tbook.add(f);                              //��ӵ��绰��
            }
            catch (ClassNotFoundException e)
            {
                break;
            }
            objin.close();                                 //�ȹرն�����
            fin.close();                                   //�ٹر��ļ���
        }
        catch (IOException e)                              //���ļ������ڣ��򲻶�ȡ
        {}
    }

    private void writeToFile()                             //��ָ���ļ�д��绰���е����ж���
    {
        try
        {
            FileOutputStream fout = new FileOutputStream(this.filename);  //�ļ��ֽ������
            ObjectOutputStream objout = new ObjectOutputStream(fout);     //�����ֽ������
            if (!tbook.isEmpty())
            {
                Iterator it = tbook.iterator();
                while (it.hasNext())
                    objout.writeObject((Friend)it.next()); //д��һ������
            }
            objout.close();
            fout.close();
        }
        catch (IOException ex)
        {}
    }
    
    public void windowClosing(WindowEvent e)               //�رմ����¼�������
    {
        this.writeToFile();                                //���绰�������ж���д��ָ���ļ�
        System.exit(0);                                    //Ӧ�ó�����ִֹ��
    }

    public void windowOpened(WindowEvent e)         {  }
    public void windowActivated(WindowEvent e)      {  }
    public void windowDeactivated(WindowEvent e)    {  }
    public void windowClosed(WindowEvent e)         {  }
    public void windowIconified(WindowEvent e)      {  }
    public void windowDeiconified(WindowEvent e)    {  }

    public static void main(String args[]) throws IOException
    {
        new TelephoneBookJFrame();                         //Ĭ���ļ���Ϊ"frindes.dat"
    }    
}
    
/*
//        this.table_friends.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
˼���⣺�������¹��ܡ�
?	���绰�����λ����
?	���ӵ绰�����޸Ĺ��ܡ�
ѡ�б��һ��,�ܹ��޸�,���޸Ĺ�����û�д�ס?
            JButton button_update = new JButton("����");
            p3.add(button_update);
            button_update.addActionListener(this);
        
    private DefaultTableColumnModel tableColumn;
    private DefaultListSelectionModel listselect; 
        tableColumn = new DefaultTableColumnModel();

        this.listselect = new DefaultListSelectionModel(); 
        this.listselect.addListSelectionListener(this); 

        this.table = new JTable(tableModel, tableColumn, listselect);               //�����ձ����б���
        if (e.getSource()==this.listselect)                //ѡ�б��һ��ʱ����
        {
        System.out.println(" this.listselect");  

            }

*/
