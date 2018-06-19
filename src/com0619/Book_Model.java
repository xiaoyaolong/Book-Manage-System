package com0619;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.Vector;

public class Book_Model extends AbstractTableModel
{

    Vector rowData,columnNames;  // Vectors for containing the results.

    // for SQL Server Link and Process.
    PreparedStatement ps = null;
    Connection ct = null;
    ResultSet rs = null;


    public boolean Book_update(String sql_update,String []paras)
    {
        SQLHelper sql_helper = new SQLHelper();
        return sql_helper.SQL_Update(sql_update,paras);
    }

    public void Book_Model_Init(String sql_command, String []name)
    {

        columnNames = new Vector();  // Define the name of columns.
        columnNames.add("  ISBN  ");
        columnNames.add("书    名");
        columnNames.add("作    者");
        columnNames.add("出版时间");
        columnNames.add("出 版 社");
        columnNames.add("所在位置");
        columnNames.add("图书类别");


        rowData = new Vector();   // Define the data in each row.

        try
        {
            SQLHelper sql_helper = new SQLHelper();
            rs = sql_helper.SQL_Search(sql_command,name);

            while(rs.next()) // Receive and output the Results.
            {
                Vector row_temp = new Vector();  // Temp data for each row in table.
                row_temp.add(rs.getString(1));
                row_temp.add(rs.getString(2));
                row_temp.add(rs.getString(3));
                row_temp.add(rs.getString(4));
                row_temp.add(rs.getString(5));
                row_temp.add(rs.getString(6));
                row_temp.add(rs.getString(7));
                rowData.add(row_temp);
            }
        }catch (Exception e)
        {
            e.printStackTrace();  // print the exception.
        }finally  // Close the resource of SQL Server!!!
        {
            try
            {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (ct != null) ct.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public Book_Model(String sql_search, String []name)
    {
        this.Book_Model_Init(sql_search, name);
    }

    public Book_Model()  // Construction function for initial model.
    {
        String [] name = {};
        this.Book_Model_Init("",name);
    }

    public int getRowCount()  // Get the number of rows.
    {
        return this.rowData.size();
    }

    public int getColumnCount()  // Get the number of columns.
    {
        return this.columnNames.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) // Get the entry at (rowIndex,columnIndex).
    {
        return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
    }

    public String getColumnName(int column)
    {
        return (String)this.columnNames.get(column);
    }
}