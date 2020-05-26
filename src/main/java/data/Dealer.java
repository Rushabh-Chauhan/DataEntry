package data;

public class Dealer {

	private String companyname;
	private String phone;
	private String lastAmountPaid;
	private String amountPending;
	private int totalAmountPaid;

	public Dealer(String companyname, String phone, String lastAmountPaid, String amountPending) {

		this.companyname = companyname;
		this.phone = phone;
		this.lastAmountPaid = lastAmountPaid;
		this.amountPending = amountPending;
		this.totalAmountPaid = 0;

	}



}
