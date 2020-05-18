package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import data.Employ;

public class DatabaseHandler {
	
	private static DatabaseHandler handler = null;
	
	private static final String url = "jdbc:mysql://localhost:3306/employ";
	private static Connection con = null;
	private static Statement stat= null;
	private String user = "root";
	private String password = "Rushabhheena89";
	
	private DatabaseHandler()throws Exception
	{
		CreatConnection();
		CreatTable();
	}
	
	public static DatabaseHandler getHandler()
	{
		if(handler == null)
		{
			try {
				handler = new DatabaseHandler();
			} catch (Exception e) {
				System.out.print("problem in creating new instance...."+e.getLocalizedMessage());;
			}
		}
		return handler;
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
	
	public boolean deleteEmploy(Employ employ)
	{
		
		try {
			String deletestatement = "delete from Employtable where id = ?;";
			PreparedStatement stm = con.prepareStatement(deletestatement);
			stm.setString(1, employ.id);
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void CreatTable()throws Exception
	{
		String tableName = "employtable";
		stat = con.createStatement();
		DatabaseMetaData dbm = con.getMetaData();
		ResultSet tables = dbm.getTables(null, null, tableName.toUpperCase(),null);
		if(tables.next())
		{
			//System.out.println("Table "+ tableName+" already exists. ready to go!");
		}
		else
		{
			stat.execute("CREATE TABLE `"+ tableName +"`("
					+"`ID` varchar(50),"
					+"`Name` varchar(50),"
					+"`Address` TEXT(60000),"
					+"`Phone Number` varchar(50),"
					+"`Salary` varchar(50),"
					+ "`description` TEXT(6000),"
					+ "PRIMARY KEY (`ID`));");
		}
		
		
		
	}

}
