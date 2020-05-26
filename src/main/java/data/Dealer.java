package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dealer {

	private String companyname;
	private String phone;
	public BankDetails bank;

	public Dealer(String companyname, String phone, String lastAmountPaid, String amountPending,BankDetails bank) {

		this.companyname = companyname;
		this.phone = phone;
		this.bank = bank;
	}
	
	public StringProperty getCompanyName() {
		return new SimpleStringProperty(this.companyname);
	}
	public StringProperty getPhone() {
		return new SimpleStringProperty(this.phone);
	}
//	public StringProperty get() {
//		return new SimpleStringProperty(this.companyname);
//	}



}
