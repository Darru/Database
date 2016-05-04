import java.util.Date;

public class Payment extends Customer
{
	private int paymentID;
	private int accountNumber;
	private int sortCode;
	private Date expiryDate;
	
	public Payment(int custID, String dOB, String firstNme, String lastNme, int payID, int accountNum, int sortCde, Date expiry)
	{
		super(custID, dOB, firstNme, lastNme);
		paymentID = payID;
		accountNumber = accountNum;
		sortCode = sortCde;
		expiryDate = expiry;
	}

	public int getPaymentID()
	{
		return paymentID;
	}
	public void setPaymentID(int paymentID)
	{
		this.paymentID = paymentID;
	}
	
	public int getAccountNumber()
	{
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber)
	{
		this.accountNumber = accountNumber;
	}
	
	public int getSortCode()
	{
		return sortCode;
	}
	public void setSortCode(int sortCode)
	{
		this.sortCode = sortCode;
	}
	
	public Date getExpiryDate()
	{
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate)
	{
		this.expiryDate = expiryDate;
	}
	
	@Override
	public String toString ()
	{
		return "Payment ID: " + paymentID + "\n" + accountNumber + "\n" + sortCode + "\n" + expiryDate;
	}
	
}
