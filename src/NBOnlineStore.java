import java.util.ArrayList;
import java.util.Scanner;

public class NBOnlineStore
{
	public static Scanner userInput = new Scanner(System.in);
	
	//Re-use able variables
	protected static String yesInput = null;
	protected static String nextMenu = null;
	
	//Search for customer variables
	protected static String customerFirstName = null;
	protected static String customerLastName = null;
	protected static String customerDOB = null;
	//Database returns customer variables
	public static int returnCustomerID;
	public static String returnAccountStatus;
	public static String returnCustomerFirstName;
	public static String returnCustomerLastName;
	public static String returnCustomerDOB;
		
	//Temporary array list to store products.
	public static ArrayList <Product> tempProductList = new ArrayList<>();
	
	public static void searchForCustomer()
	{	
		//Ask for customer information.
		do
		{
			System.out.println("Please enter your date of birth");
			customerDOB = userInput.next();
			System.out.println("Please enter you first name: ");
			customerFirstName = userInput.next();
			System.out.println("Please enter your last name: ");
			customerLastName = userInput.next();
			DatabaseController.searchDatabaseForCustomer();
		}
		while (customerFirstName == null);
	}
					
	public static void nextMenu()
	{
		//Ask where the customer would like to go.
		System.out.println("Where would you like to go next? cart, browse, checkout, exit");
		nextMenu = userInput.next();
		
		if (nextMenu.equals("exit"))
		{
			System.out.println("Exiting application.");
			System.out.println(Customer.cart.size());
			terminateApplication();
		}
		else if (nextMenu.equals("cart"))
		{
			nextMenu = null;
			Customer.viewCart();
		}
		else if (nextMenu.equals("checkout"))
		{
			nextMenu = null;
			Customer.createSalesOrder();
		}
		else
		{
			nextMenu = null;
			Product.searchForProduct();
		}	
	}
	
	public static void terminateApplication ()
	{
		System.out.println("terminate. Cart size =" + Customer.cart.size());
		
		if (Customer.cart.size() > 0)
		{
			Customer.removeFromCart();
		}
		else
		{
		//Close user input, close database connection and terminate message.
		userInput.close();
		DatabaseController.disconnectNBGardens();
		}		
	}
}
