package data;

import java.time.LocalDate;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Salary extends RecursiveTreeObject<Salary>{
	
	public LocalDate date;
	public double commission;
	public double salary;
	public double deduction;
	public double total;
	public String salaryDescription;
	public String id;
	//public  ObservableList<Salary> salaryOList;
	
	public Salary(String id, LocalDate date, String salary, String Commision, String Deduction,String total, String des)
	{
		this.date = date;
		this.salary = Double.parseDouble(salary);
		this.commission = Double.parseDouble(Commision);
		this.deduction = Double.parseDouble(Deduction);
		this.total = Double.parseDouble(total);
		this.salaryDescription = des;
		this.id = id;
		//this.salaryOList = FXCollections.observableArrayList();
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
