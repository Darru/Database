
public class NBStoreSystem
{	
	public static void main(String[] args)
	{	
		//Connect to database
		DatabaseController.accessNBGardens();
		
		//Welcome message.		
		System.out.println("Welcome to the NB Gardens store.");
		
		//Search for customer account.
		NBOnlineStore.searchForCustomer();
		
		//End application.
		NBOnlineStore.terminateApplication();
		
		//Goodbye message.		
		System.out.println("Thankyou for using the NB Gardens store.");
	}
}
