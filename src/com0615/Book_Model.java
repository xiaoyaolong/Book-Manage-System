package com0615;

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

    // SQL Server Deriver.
    String DB_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Address (URL) for SQL Server and name for database (Different from the video!).
    String DB_url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Book_Manage";
    // UserName for the database.
    String DB_user = "sa";
    // PassWord for the database.
    String DB_password = "BdH123";

    public boolean Book_update(String sql_update,String []paras)
    {
        boolean b = true;
        try
        {
            Class.forName(DB_driver);
            ct = DriverManager.getConnection(DB_url,DB_user,DB_password);
            ps = ct.prepareStatement(sql_update);
            for(int i=0;i<paras.length;i++)
            {
                ps.setString(i+1, paras[i]);
            }
            if(ps.executeUpdate()!=1)
            {
                b = false;
            }
        } catch (Exception e)
        {
            b = false;
            JOptionPane.showMessageDialog(null,
                    "连接数据库出错", "错误：", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }finally
        {
            try
            {
                if(rs!=null) rs.close();
                if(ps!=null) ps.close();
                if(ct!=null) ct.close();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        return b;
    }

    public void Book_Model_Init(String sql_command, String []name)
    {
        if(sql_command.equals(""))
        {
            sql_command = "select * from dbo.books";
        }
        columnNames = new Vector();  // Define the name of columns.
        columnNames.add("条 形 码");
        columnNames.add("书    名");
        columnNames.add("作    者");
        columnNames.add("出版时间");
        columnNames.add("出 版 社");
        columnNames.add("所在位置");


        rowData = new Vector();   // Define the data in each row.

        try
        {
            // Load the SQL DB Driver.
            Class.forName(DB_driver);
            // Connect to the SQL Server. Here is important!
            ct = DriverManager.getConnection(DB_url,DB_user,DB_password);
            // Input the SQL Command.
            ps = ct.prepareStatement(sql_command);
            for(int i=0;i<name.length;i++)
            {
                ps.setString(i+1, name[i]);
            }
            // Execute the SQL Command and get the results.
            rs = ps.executeQuery();

            while(rs.next()) // Receive and output the Results.
            {
                Vector row_temp = new Vector();  // Temp data for each row in table.
                row_temp.add(rs.getString(1));
                row_temp.add(rs.getString(2));
                row_temp.add(rs.getString(3));
                row_temp.add(rs.getString(4));
                row_temp.add(rs.getString(5));
                row_temp.add(rs.getString(6));
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