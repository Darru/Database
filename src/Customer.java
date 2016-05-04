import java.util.ArrayList;
import java.util.InputMismatchException;

public class Customer
{	
	private int customerID;
	private String dateOfBirth;
	private String firstName;
	private String lastName;
	
	public static int productID;
	public static int productQuantity;
	
	//Add product to cart variables
	protected static int productSelection = -10;
	protected static int tempListSize = NBOnlineStore.tempProductList.size();
	
	public static ArrayList <Product> cart = new ArrayList <>();
	
	public Customer (int custID, String dOB, String firstNme, String lastNme)
	{
		customerID = custID;
		dateOfBirth = dOB;
		firstName = firstNme;
		lastName = lastNme;
	}	
	
	public int getCustomerID()
	{
		return customerID;
	}
	public void setCustomerID(int customerID)
	{
		this.customerID = customerID;
	}
	
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public static void checkAccountStatus()
	{
		//Check account status.
		if (NBOnlineStore.returnAccountStatus.equals("Active"))
		{
			Product.searchForProduct();
		}
		else
		{
			System.out.println("Please contact the sales team on 01159563605 to re-activate your account.");
		}
	}
	
	public static void addToCart()
	{		
		while (productSelection == -10)
		{
			try
			{	
				System.out.println("Select product: \n" + NBOnlineStore.tempProductList);
				productSelection = (NBOnlineStore.userInput.nextInt() - 1);
				
				if (productSelection >= 0 && productSelection <= tempListSize)
				{
					Product product = NBOnlineStore.tempProductList.get(productSelection);
					int iD = product.getProductID();
					String productName = product.getProductName();
					int quantity = product.getQuantity();
					float rRP = product.getrRP();
					String weight = product.getWeight();
					String dimensions = product.getDimensions();
					cart.add (new Product(iD, productName, quantity, rRP, weight, dimensions));
					System.out.println("Added: " + product);
					
					productID = product.getProductID();
					productQuantity = product.getQuantity();
					
					//Clear temporary array list
					productSelection = -11;
					NBOnlineStore.tempProductList.clear();
					DatabaseController.removeQuantity();
				}
			} 
			catch (InputMismatchException exc) 
			{
				System.out.println("Please enter a valid product");
				productSelection = -10;
			}
		}
		productSelection = -10;
		NBOnlineStore.nextMenu();
	}
	
	public static void removeFromCart()
	{
		
		NBOnlineStore.nextMenu();
	}
	
	public static void viewCart()
	{
		System.out.println("You have " + cart.size() + " item/s in your cart.");
		System.out.println(cart);
		NBOnlineStore.nextMenu();
	}
	
	public static void createSalesOrder()
	{
		
	}
	
	public static void cancelSalesOrder()
	{
		
	}
	
	@Override
	public String toString ()
	{
		return "Customer ID: " + customerID + "\nCustomer Name: " + firstName + " " + lastName + 
				"\n Customer D.O.B: " + dateOfBirth;
	}
}
