
public class Inventory
{
	protected int productID;
	protected String productName;
	protected int quantity;
	
	public Inventory (int productid, String productNme, int itemQuantity)
	{
		productID = productid;
		productName = productNme;
		quantity = itemQuantity;
	}

	public int getProductID()
	{
		return productID;
	}
	public void setProductID(int productID)
	{
		this.productID = productID;
	}

	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	@Override
	public String toString ()
	{
		return "Product ID: " + productID + "\n" + 
				"Product Name: " + productName + "\n";
	}
}
