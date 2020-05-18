package data;

import java.time.LocalDate;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Salary extends RecursiveTreeObject<Salary>{
	
	public LocalDate date;
	public int commission;
	public int salary;
	public int deduction;
	public String salaryDescription;
	public Employ employ;
	//public  ObservableList<Salary> salaryOList;
	
	public Salary(Employ employ, LocalDate date, String salary, String Commision, String Deduction, String des)
	{
		this.date = date;
		this.salary = Integer.parseInt(salary);
		this.commission = Integer.parseInt(Commision);
		this.deduction = Integer.parseInt(Deduction);
		this.salaryDescription = des;
		this.employ = employ;
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
	
	public StringProperty getSalay() {
		return new SimpleStringProperty(this.salary+"");
	}
	
	

}
