package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseHandler {
	
	public static final String url = "jdbc:mysql://localhost:3306/employ";
	public static Connection con = null;
	public static Statement stat= null;
	public String user = "root";
	public String password = "Rushabhheena89";
	
	public DatabaseHandler()throws Exception
	{
		CreatConnection();
		CreatTable();
	}
	
	public void CreatConnection()throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);
		
	}
	
	public ResultSet executeQueri(String sql)
	{
		ResultSet result = null;
		try {
			stat = con.createStatement();
			result = stat.executeQuery(sql);
		} catch (SQLException e) {
			System.out.print("Exception in executeQueri:DatabaseHandler......."+e.getLocalizedMessage());
			return null;
		}
		return result;
	}
	
	public boolean executeAction(String sql)
	{
		try {
			stat = con.createStatement();
			stat.execute(sql);
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error"+e.getMessage(),"Error Occured",JOptionPane.ERROR_MESSAGE);
			System.out.print("Exception in executeAction:DatabaseHandler......."+e.getLocalizedMessage());
			return false;
		}
		
	}
	
	public void CreatTable()throws Exception
	{
		String tableName = "employtable";
		stat = con.createStatement();
		DatabaseMetaData dbm = con.getMetaData();
		ResultSet tables = dbm.getTables(null, null, tableName.toUpperCase(),null);
		if(tables.next())
		{
			System.out.println("Table "+ tableName+" already exists. ready to go!");
		}
		else
		{
			stat.execute("CREATE TABLE `"+ tableName +"`("
					+"`Name` varchar(50),"
					+"`Address` varchar(50),"
					+"`Phone Number` varchar(50),"
					+"`Salary` varchar(50),"
					+ "`description` varchar(50));");
		}
		
	}

}
