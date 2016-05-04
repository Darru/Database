
public class Cart extends Customer
{
	private String productName;
	private int orderQuantity;
	
	public float totalCost;
	
	public Cart(int custID, String dOB, String firstNme, String lastNme, String prdName, int ordQuantity)
	{
		super(custID, dOB, firstNme, lastNme);
		productName = prdName;
		orderQuantity = ordQuantity;
	}
}
