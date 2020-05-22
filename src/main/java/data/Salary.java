package data;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

	public Salary(String payment_id,String name, Date date, String salary, String Commision, String Deduction,String total, String des)
	{
		// converting id to name using database.
		this.name = name;
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
