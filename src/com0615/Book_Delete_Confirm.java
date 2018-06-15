package com0615;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Book_Delete_Confirm extends JDialog implements ActionListener
{
    JLabel jl1;
    JButton jb1,jb2;
    JPanel jp1,jp2;
    String row_num;

    public Book_Delete_Confirm(Frame owner, String title, boolean modal, Book_Model stu_model, int row_num)
    {
        super(owner,title,modal);
        this.row_num = stu_model.getValueAt(row_num,0).toString();
        jl1 = new JLabel("是否删除本条记录?");
        jb1 = new JButton("是");
        jb2 = new JButton("否");
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp1.add(jl1);
        jp2.add(jb1); jp2.add(jb2);

        this.add(jp1,BorderLayout.NORTH);
        this.add(jp2,BorderLayout.CENTER);

        this.setSize(300,90);
        this.setResizable(false);
        this.setLocation(400,300);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == jb1)
        {
            String sql_delete = "delete from dbo.books where book_id=?";
            String []paras = {row_num};
            Book_Model temp = new Book_Model();  // Temp model for updating the database.
            if(temp.Book_update(sql_delete,paras))
            {
                JOptionPane.showMessageDialog(this,
                        "删除成功",
                        "恭喜",JOptionPane.INFORMATION_MESSAGE);
            }else
            {
                JOptionPane.showMessageDialog(this,
                        "删除失败","遗憾",JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }
        else if(e.getSource() == jb2)  // Cancel the Operation.
        {
            this.dispose();
        }
    }
}