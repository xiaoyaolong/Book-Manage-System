package com0615;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Book_Update_Dialog extends JDialog implements ActionListener {

    JLabel jl1,jl2,jl3,jl4,jl5,jl6;
    JButton jb1,jb2;
    JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
    JPanel jp1,jp2,jp3;
    Toolkit tk;

    // Construction Function for Window Outlook.
    public Book_Update_Dialog(Frame owner,String title,boolean modal,Book_Model stu_model,int row_num)
    {
        super(owner,title,modal);
        jl1 = new JLabel(" 条 形 码:  ");
        jl2 = new JLabel(" 书    名:  ");
        jl3 = new JLabel(" 作    者:  ");
        jl4 = new JLabel(" 出版时间:  ");
        jl5 = new JLabel(" 出 版 社:  ");
        jl6 = new JLabel(" 所在位置:  ");

        jtf1 = new JTextField();
        jtf1.setText((String)stu_model.getValueAt(row_num,0)); // Change Object to String.
        jtf1.setEditable(false);  // Primary Key can not be editable.
        jtf2 = new JTextField();
        jtf2.setText((String)stu_model.getValueAt(row_num,1));  // Change Object to String.
        jtf2.setEditable(true);  // Student Name can be editable.
        jtf3 = new JTextField();
        jtf3.setText((String)stu_model.getValueAt(row_num,2));  // Change Object to String.
        jtf3.setEditable(true);  // Student Sex can be editable.
        jtf4 = new JTextField();
        jtf4.setText((String)stu_model.getValueAt(row_num,3));  // Change Object to String.
        jtf4.setEditable(true);  // Student Major can be editable.
        jtf5 = new JTextField();
        jtf5.setText((String)stu_model.getValueAt(row_num,4));  // Change Object to String.
        jtf5.setEditable(true);  // Student Major can be editable.
        jtf6 = new JTextField();
        jtf6.setText((String)stu_model.getValueAt(row_num,5));  // Change Object to String.
        jtf6.setEditable(true);  // Student Major can be editable.

        jb1 = new JButton("确认"); jb2 = new JButton("取消");

        jp1 = new JPanel(); jp2 = new JPanel(); jp3 = new JPanel();

        jp1.setLayout(new GridLayout(6,1));
        jp2.setLayout(new GridLayout(6,1));

        jp1.add(jl1); jp1.add(jl2);
        jp1.add(jl3); jp1.add(jl4);
        jp1.add(jl5); jp1.add(jl6);

        jp2.add(jtf1); jp2.add(jtf2);
        jp2.add(jtf3); jp2.add(jtf4);
        jp2.add(jtf5); jp2.add(jtf6);

        jp3.add(jb1); jp3.add(jb2);

        this.add(jp1,BorderLayout.WEST);
        this.add(jp2,BorderLayout.CENTER);
        this.add(jp3,BorderLayout.SOUTH);

        jb1.addActionListener(this);
        jb2.addActionListener(this);

        this.setSize(400, 300);
        tk = getToolkit();
        Dimension dim = tk.getScreenSize();
        this.setLocation((dim.width - getWidth())/2, (dim.height -getHeight())/2);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == jb1)  // Confirm the Operation.
        {
            String sql_update = "update dbo.books set book_name=?,book_author=?," +
                    "book_date=?,book_from=?,book_loc=? where book_id=?";
            String []paras = {jtf2.getText(),jtf3.getText(),jtf4.getText(),
                    jtf5.getText(),jtf6.getText(),jtf1.getText()};
            Book_Model temp = new Book_Model();  // Temp model for updating the database.
            if(temp.Book_update(sql_update,paras))
            {
                JOptionPane.showMessageDialog(this,
                        "更新成功","恭喜",JOptionPane.INFORMATION_MESSAGE);
            }else
            {
                JOptionPane.showMessageDialog(this,
                        "更新失败","遗憾",JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }
        else if(e.getSource() == jb2)  // Cancel the Operation.
        {
            this.dispose();
        }
    }
}