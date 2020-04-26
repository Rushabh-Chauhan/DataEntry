package data;

public class Data {
	
	public String name;
	public String description;
	public String phone;
	
	public Data(String name, String phone,  String des )
	{
		this.name = name;
		this.phone = phone;
		this.description = des;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getPhone() {
		return phone;
	}
}
