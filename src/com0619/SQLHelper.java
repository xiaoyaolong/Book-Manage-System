package com0619;
import javax.swing.*;
import java.sql.*;

public class SQLHelper
{
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

    public boolean SQL_Update(String sql_update,String [] paras)
    {
        boolean b = true;
        try
        {
            Class.forName(DB_driver);
            ct = DriverManager.getConnection(DB_url, DB_user, DB_password);
            ps = ct.prepareStatement(sql_update);
            for (int i = 0; i < paras.length; i++)
            {
                ps.setString(i + 1, paras[i]);
            }
            if (ps.executeUpdate() != 1)
            {
                b = false;
            }
        } catch (Exception e)
        {
            b = false;
            JOptionPane.showMessageDialog(null,
                    "连接数据库出错", "错误：", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally
        {
            try
            {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (ct != null) ct.close();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        return b;
    }

    public ResultSet SQL_Search(String sql_command,String [] paras)
    {
        if(sql_command.equals(""))
        {
            sql_command = "select * from dbo.books";
        }
        try {
            // Load the SQL DB Driver.
            Class.forName(DB_driver);
            // Connect to the SQL Server. Here is important!
            ct = DriverManager.getConnection(DB_url, DB_user, DB_password);
            // Input the SQL Command.
            ps = ct.prepareStatement(sql_command);
            for (int i = 0; i < paras.length; i++) {
                ps.setString(i + 1, paras[i]);
            }
            // Execute the SQL Command and get the results.
            rs = ps.executeQuery();
        }catch (Exception e)
        {
            e.printStackTrace();  // print the exception.
        }
        finally {

        }
        return rs;
    }
}
