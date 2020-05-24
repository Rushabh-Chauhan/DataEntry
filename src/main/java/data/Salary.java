package data;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Salary extends RecursiveTreeObject<Salary>{

	public Date date;
	public double commission;
	public double salary;
	public double deduction;
	public double total;
	public String salaryDescription;
	public String name;
	public String paymentID;
	//public  ObservableList<Salary> salaryOList;

	public Salary(String payment_id,String id, Date date, String salary, String Commision, String Deduction,String total, String des)
	{
		// converting id to name using database.
		String sql = "SELECT * FROM EMPLOYTABLE";
		DatabaseHandler database = DatabaseHandler.getHandler();
		ResultSet rs = database.executeQueri(sql);
		try {
			while(rs.next())
			{
				if(rs.getString("ID").equals(id))
				{
					this.name = rs.getString("Name");
				}
			}
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		
		// checks in deleted employ data if name is not in main employ data.
		if(name == null)
		{
			sql = "SELECT * FROM DELETEDEMPLOYTABLE";
			rs = database.executeQueri(sql);
			try {
				while(rs.next())
				{
					if(rs.getString("ID").equals(id))
					{
						this.name = rs.getString("Name");
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.print("error...."+e1.getLocalizedMessage());
			}
		}
		this.date = date;
		this.salary = Double.parseDouble(salary);
		this.commission = Double.parseDouble(Commision);
		this.deduction = Double.parseDouble(Deduction);
		this.total = Double.parseDouble(total);
		this.salaryDescription = des;
		if(payment_id.startsWith("@"))
		{
			this.paymentID = "CASH";
		}
		else
		{
			this.paymentID = payment_id;
		}
	}


	public StringProperty getName() {
		return new SimpleStringProperty(this.name.toString());
	}
	public StringProperty getPaymentID() {
		return new SimpleStringProperty(this.paymentID.toString());
	}
	public StringProperty getDate() {
		return new SimpleStringProperty(this.date.toString());
	}
	public StringProperty getCommision() {
		return new SimpleStringProperty(this.commission+"");
	}
	public StringProperty getDeduction() {
		return new SimpleStringProperty(this.deduction+"");
	}
	public StringProperty getSalaryDescription() {
		return new SimpleStringProperty(this.salaryDescription+"");
	}
	public StringProperty gettotal() {
		return new SimpleStringProperty(this.total+"");
	}
	public StringProperty getSalay() {
		return new SimpleStringProperty(this.salary+"");
	}



}
