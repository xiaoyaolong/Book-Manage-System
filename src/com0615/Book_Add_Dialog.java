package com0615;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Book_Add_Dialog extends JDialog implements ActionListener
{
    // Define the window container.
    JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
    JButton jb1,jb2;
    JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
    JPanel jp1,jp2,jp3;
    Toolkit tk;
    JComboBox jcb,jcb2;

    public Book_Add_Dialog(Frame owner,String title,boolean modal) // Construct function for window. d
    {
        super(owner,title,modal);

        jcb = new JComboBox();
        jcb.addItem("文学");
        jcb.addItem("数学");
        jcb.addItem("计算机");
        jcb.addItem("通信电子");
        jcb.addItem("金融经济");
        jcb.addItem("其他");
        jcb.addActionListener(this);

        jcb2 = new JComboBox();
        jcb2.addItem("望府");
        jcb2.addItem("鲍家汇");
        jcb2.addItem("长丰");
        jcb2.addActionListener(this);

        jl1 = new JLabel(" ISBN   :  ");
        jl2 = new JLabel(" 书    名:  ");
        jl3 = new JLabel(" 作    者:  ");
        jl4 = new JLabel(" 出版时间:  ");
        jl5 = new JLabel(" 出 版 社:  ");
        jl6 = new JLabel(" 所在位置:  ");
        jl7 = new JLabel(" 图书类别:  ");

        jtf1 = new JTextField(); jtf2 = new JTextField();
        jtf3 = new JTextField(); jtf4 = new JTextField();
        jtf5 = new JTextField();

        jb1 = new JButton("确定"); jb2 = new JButton("取消");

        jp1 = new JPanel(); jp2 = new JPanel(); jp3 = new JPanel();

        jp1.setLayout(new GridLayout(7,1));
        jp2.setLayout(new GridLayout(7,1));

        jp1.add(jl1); jp1.add(jl2);
        jp1.add(jl3); jp1.add(jl4);
        jp1.add(jl5); jp1.add(jl6);jp1.add(jl7);

        jp2.add(jtf1); jp2.add(jtf2);
        jp2.add(jtf3); jp2.add(jtf4);
        jp2.add(jtf5); jp2.add(jcb2);jp2.add(jcb);

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
        if(e.getSource() == jcb || e.getSource() == jcb2);
        else if(e.getSource() == jb1)  // Confirm button to add a record.
        {
            int item = this.jcb.getSelectedIndex();
            String book_cal = null;
            switch (item)
            {
                case 0:
                    book_cal = "文学"; break;
                case 1:
                    book_cal = "数学"; break;
                case 2:
                    book_cal = "计算机"; break;
                case 3:
                    book_cal = "通信电子"; break;
                case 4:
                    book_cal = "金融经济"; break;
                case 5:
                    book_cal = "其他"; break;
            }
            int item2 = this.jcb2.getSelectedIndex();
            String book_loc = null;
            switch (item2)
            {
                case 0:
                    book_loc = "望府"; break;
                case 1:
                    book_loc = "鲍家汇"; break;
                case 2:
                    book_loc = "长丰"; break;
            }
            String sql_insert = "insert into dbo.books values(?,?,?,?,?,?,?)";
            String []paras = {jtf1.getText(),jtf2.getText(),jtf3.getText(),
                    jtf4.getText(),jtf5.getText(),book_loc,book_cal};
            Book_Model temp = new Book_Model();  // Temp model for updating database.
            if(temp.Book_update(sql_insert,paras))
            {
                JOptionPane.showMessageDialog(this,
                        "添加成功","恭喜",JOptionPane.INFORMATION_MESSAGE);
            }else
            {
                JOptionPane.showMessageDialog(this,
                        "添加失败","遗憾",JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }
        else if(e.getSource() == jb2)  // Cancel the operation.
        {
            this.dispose();
        }
    }
}