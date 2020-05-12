package data;


import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employ extends RecursiveTreeObject<Employ>{


	public String address;
	public int salary;
	public int tSalary;
	public String name;
	public String description;
	public String phone;
	public ObservableList<Salary> salaryOList;
	

	public Employ(String name, String address, String phone, String salary, String des)
	{
		this.name = name;
		this.phone = phone;
		this.description = des;
		this.address = address;
		this.salary = Integer.parseInt(salary);
		this.tSalary = 0;
		this.salaryOList = FXCollections.observableArrayList();
	}

	public StringProperty getAddress() {
		return new SimpleStringProperty(this.address);
	}
	public StringProperty getSalary() {
		return new SimpleStringProperty(this.salary+"");
	}
	public StringProperty getName() {
		return new SimpleStringProperty(this.name);
	}
	public StringProperty getDescription() {
		return new SimpleStringProperty(this.description);
	}
	public StringProperty getPhone() {
		return new SimpleStringProperty(this.phone);
	}
	public StringProperty getGivenSalary() {
		return new SimpleStringProperty(this.tSalary+"");
	}
	public void GivenMoney(int money)
	{
		this.tSalary += money;
	}
	public int gettSalary() {
		return tSalary;
	}
	
	public void addSalary(ObservableList<Salary> salaryOList)
	{
		this.salaryOList.addAll(salaryOList);
	}
	
	public void addSalary(Salary salary)
	{
		this.salaryOList.add(salary);
	}
	
	public ObservableList<Salary> getSalaryList()
	{
		return this.salaryOList;
	}
	
	
	
	
	
	


}
