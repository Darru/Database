
public class Address extends Customer
{
	private String postCode;
	private int doorNumber;
	private String addressLine1;
	private String addressLine2;
	private String city;

	public Address(int custID, String dOB, String firstNme, String lastNme, 
			String postCde, int doorNum, String addressLne1, String addressLne2, String cityNme)
	{
		super(custID, dOB, firstNme, lastNme);
		postCode = postCde;
		doorNumber = doorNum;
		addressLine1 = addressLne1;
		addressLine2 = addressLne2;
		city = cityNme;
	}

	public String getPostCode()
	{
		return postCode;
	}
	public void setPostCode(String postCode)
	{
		this.postCode = postCode;
	}

	public int getDoorNumber()
	{
		return doorNumber;
	}
	public void setDoorNumber(int doorNumber)
	{
		this.doorNumber = doorNumber;
	}

	public String getAddressLine1()
	{
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2()
	{
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}

	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	
	@Override
	public String toString ()
	{
		return "Address:\n" + doorNumber + " " + addressLine1 + "\n" + addressLine2+ "\n" + city + "\n" + postCode;
	}
	
}
