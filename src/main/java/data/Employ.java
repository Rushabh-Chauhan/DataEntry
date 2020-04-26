package data;


public class Employ extends Data{
	
	
	public String address;
	public int salary;
	public int tSalary;
	
	public Employ(String name, String address, String phone, String salary, String des)
	{
		super(name, phone,des);
		this.address = address;
		this.salary = Integer.parseInt(salary);
		this.tSalary = 0;
	}
	
	public String getAddress() {
		return address;
	}
	public int getSalary() {
		return salary;
	}
	public void GivenMoney(int money)
	{
		this.tSalary += money;
	}

}
