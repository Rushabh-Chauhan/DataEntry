package data;

public class BankDetails {
	
	public String IFCCode;
	public String accountNumber;
	public String address;
	public String bankName;
	
	public BankDetails(String bankName, String accountNumber, String IFCCode, String address)
	{
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.IFCCode = IFCCode;
		this.address = address;
	}

}
