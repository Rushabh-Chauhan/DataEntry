package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import data.Dealer;
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
		creatDealerTable();
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
	
	
	public boolean updateDealer(Dealer dealer, String oldAccountNumber)
	{
		try {
					
			String update = "UPDATE dealertable SET Company_Name = ?, phone = ?,Bank_Name = ?,Account_Number = ?,IFC_code = ?,Bank_Address = ?,Policy = ? WHERE Account_Number = ?;";
			PreparedStatement stmt = con.prepareStatement(update);
			stmt.setString(1, dealer.companyname);
			stmt.setString(2, dealer.phone);
			stmt.setString(3, dealer.bank.bankName);
			stmt.setString(4, dealer.bank.accountNumber);
			stmt.setString(5, dealer.bank.IFCCode);
			stmt.setString(6, dealer.bank.address);
			stmt.setString(7, dealer.policy);
			stmt.setString(8, oldAccountNumber);
			int result = stmt.executeUpdate();	
			
			return (result >0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("problem in DatabaseHandler/updateDealer....."+e.getLocalizedMessage());
			return false;
		}
	}
	
	
	
	
	

	public void creatEmployTable()throws Exception
	{
		String tableName = "employtable";
		stat = con.createStatement();
		DatabaseMetaData dbm = con.getMetaData();
		ResultSet tables = dbm.getTables(null, null, tableName.toUpperCase(),null);
		if(!tables.next())
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
		if(!tables.next())
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
		if(!tables.next())
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
	
	
	
	public void creatDealerTable()throws Exception
	{
		String tableName = "dealertable";
		stat = con.createStatement();
		DatabaseMetaData dbm = con.getMetaData();
		ResultSet tables = dbm.getTables(null, null, tableName.toUpperCase(),null);
		if(!tables.next())
		{
			stat.execute("CREATE TABLE `"+ tableName +"`("
					+"`Company_Name` varchar(50),"
					+"`phone` varchar(50),"
					+"`Bank_Name` varchar(50),"
					+"`Account_Number` varchar(50),"
					+"`IFC_code` varchar(50),"
					+"`Bank_Address` varchar(50),"
					+"`Policy` TEXT(6000),"
					+ "PRIMARY KEY (`Account_Number`));");
		}
	}
}


