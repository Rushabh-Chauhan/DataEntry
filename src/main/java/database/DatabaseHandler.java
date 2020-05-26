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
		creatEmployTable();
		creatSalaryTable();
		creatDeletedEmployTable();
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

	public boolean deleteEmploy(Employ employ, String table)
	{
		try {
			if(table.equals("Deletedemploytable"))
			{
				copyEmployRow(employ,"employtable");
			}
			else
			{
				copyEmployRow(employ,"Deletedemploytable");
			}
			String deletestatement = "delete from "+table+" where id = ?;";
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
	// just to use in delete employ creating a copy in deleted table.
	private void copyEmployRow(Employ employ, String table)
	{
		String sql = "INSERT INTO "+table+" VALUES("
				+"'"+employ.id+"',"
				+"'"+employ.name+"',"
				+"'"+employ.address+"',"
				+"'"+employ.phone+"',"
				+"'"+employ.salary+"',"
				+"'"+employ.description+"');";
		this.executeAction(sql);
	}

	public boolean updateEmploy(Employ employ)
	{
		try {
					
			String update = "UPDATE employtable SET Name = ?, Address = ?,Phone = ?,Salary = ?,description = ? WHERE ID = ?;";
			PreparedStatement stmt = con.prepareStatement(update);
			stmt.setString(1, employ.name);
			stmt.setString(2, employ.address);
			stmt.setString(3, employ.phone);
			stmt.setString(4, employ.salary+"");
			stmt.setString(5, employ.description);
			stmt.setString(6, employ.id);
			int result = stmt.executeUpdate();	
			
			return (result >0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("problem in DatabaseHandler/updateEmploy....."+e.getLocalizedMessage());
			return false;
		}
	}

	public void creatEmployTable()throws Exception
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
					+"`Phone` varchar(50),"
					+"`Salary` varchar(50),"
					+ "`description` TEXT(6000),"
					+ "PRIMARY KEY (`ID`));");
		}
	}
	
	public void creatDeletedEmployTable()throws Exception
	{
		String tableName = "Deletedemploytable";
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
					+"`Phone` varchar(50),"
					+"`Salary` varchar(50),"
					+ "`description` TEXT(6000),"
					+ "PRIMARY KEY (`ID`));");
		}
	}

	public void creatSalaryTable()throws Exception
	{
		String tableName = "salarytable";
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
					+"`payment_id` varchar(700),"
					+"`ID` varchar(50),"
					+"`Date` DATE,"
					+"`salary` varchar(50),"
					+"`commission` varchar(50),"
					+"`deduction` varchar(50),"
					+"`Total` varchar(50),"
					+ "`description` TEXT(6000),"
					+ "PRIMARY KEY (`payment_id`));");
		}
	}
}


