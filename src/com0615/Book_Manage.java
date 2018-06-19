/**
 * The books management system for our own liberary
 * Designer: Donghai Bao
 * Date: 2018-06-15.
 */
package com0615;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Book_Manage extends JFrame implements ActionListener
{
    // Define the container.
    private JPanel jp1,jp2;
    private JScrollPane jsp;
    private JTable jt;
    private JTextField jtf;
    private JButton jb1,jb2,jb3,jb4;
    private JComboBox jcb;
    Book_Model book_model;  // An abstract model for JTable.
    private Toolkit tk;

    public static void main(String[] args)
    {
        try   // Design the window according to Operation System.
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new Book_Manage();   // Implement an instance.
    }

    // Design the construct function: Mainly for Design Window and Add ActionListener!
    public Book_Manage()
    {
        jp1 = new JPanel();
        // jp1.setLayout(new FlowLayout());  // Default is the FlowLayout.
        jcb = new JComboBox();
        jcb.addItem("书    名:");
        jcb.addItem("  ISBN:  ");
        jcb.addItem("所在位置:");
        jcb.addItem("作    者:");
        jcb.addItem("类    别:");
        jcb.addActionListener(this);
        jtf = new JTextField(15);
        jb1 = new JButton("搜索");
        jb1.addActionListener(this);  // Add the action listener for Search Button.
        jp1.add(jcb);jp1.add(jtf);jp1.add(jb1);

        jp2 = new JPanel();
        // jp2.setLayout(new FlowLayout());
        jb2 = new JButton("添加");
        jb2.addActionListener(this);  // Add the action listener for Add Record Button.
        jb3 = new JButton("更改");
        jb3.addActionListener(this);  // Add the action listener for Update Record Button.
        jb4 = new JButton("删除");
        jb4.addActionListener(this);  // Add the action listener for Delete Record Button.
        jp2.add(jb2);jp2.add(jb3);jp2.add(jb4);

        // Design the Abstract Model for Database Results !!!
        book_model = new Book_Model();
        jt = new JTable(book_model);
        jsp = new JScrollPane(jt);

        // this.setLayout(new BorderLayout()); // Default is the BorderLayout.
        this.add(jp1,"North");  // Put the jp1 at north border.
        this.add(jsp);  // Put the jsp at the center.
        this.add(jp2,"South");  // Put the jp2 at the south border.

        // Set the appearance of Window.
        this.setSize(800,400);
        tk = getToolkit();
        Dimension dim = tk.getScreenSize();
        this.setLocation((dim.width - getWidth())/2, (dim.height -getHeight())/2);
        this.setTitle("旭日东升图书管理系统");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0)
    {
        if(arg0.getSource() == jcb);
        else if(arg0.getSource() == jb1)  // Take action when click Button jb1 (Search Record).
        {
            int item = this.jcb.getSelectedIndex();
            String search = this.jtf.getText().trim();  // Get the name to search.
            String[] paras = {"%"+search+"%"};
            String sql_search = null;
            switch (item)
            {
                case 0:
                    sql_search = "Select * from dbo.books where book_name like ?"; break;
                case 1:
                    sql_search = "Select * from dbo.books where book_id like ?"; break;
                case 2:
                    sql_search = "Select * from dbo.books where book_loc like ?"; break;
                case 3:
                    sql_search = "Select * from dbo.books where book_author like ?"; break;
                case 4:
                    sql_search = "Select * from dbo.books where book_cal like ?"; break;
            }
            if (search.equals(""))
            {
                book_model = new Book_Model();
                jt.setModel(book_model);   // setModel() update the JTable.
            }
            else
            {
                book_model = new Book_Model(sql_search, paras);
                jt.setModel(book_model);
            }
        }
        else if(arg0.getSource() == jb2)   // Take action when click Button jb2 (Add Record).
        {
            // Design the Dialog to add record.
            new Book_Add_Dialog(this,"添加图书",true); // Owner: the resource window.
            book_model = new Book_Model();  // Show the results.
            jt.setModel(book_model);
        }
        else if(arg0.getSource() == jb3)  // Take action when click Button jb3 (Update Record).
        {
            int row_num = this.jt.getSelectedRow();  // Get the row number selected.
            if(row_num == -1)  //  If none record is selected.
            {
                JOptionPane.showMessageDialog(this,
                        "未选择图书记录", "提示:", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // Design the Dialog to update record.
            new Book_Update_Dialog(this,"更新图书",true,book_model,row_num);
            book_model = new Book_Model(); // Show the results.
            jt.setModel(book_model);
        }
        else if(arg0.getSource() == jb4)  // Take action when click Button jb4 (Delete Record).
        {
            int row_num = this.jt.getSelectedRow();  // Get the row number selected.
            if(row_num == -1)  //  If none record is selected.
            {
                JOptionPane.showMessageDialog(this,
                        "未选择图书记录", "提示:", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            new Book_Delete_Confirm(this,"提示:",true,book_model,row_num);
            book_model = new Book_Model(); // Show the results.
            jt.setModel(book_model);
        }
    }
}
