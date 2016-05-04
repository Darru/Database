import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController
{
	private static final String DB_Driver = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/NBGardens";
	private static final String DB_User = "root";
	private static final String DB_Pass = "barry";
	
	private static Connection conn;
	
	
	//Try to connect to database. Catch errors.
	public static void accessNBGardens()
	{		
		try
		{
			Class.forName(DB_Driver);
			conn = DriverManager.getConnection(DB_URL, DB_User, DB_Pass);
			System.out.println("Connection Established");
			DatabaseMetaData metadata = conn.getMetaData();
			
			String customerDatabase = metadata.getDatabaseProductName();
			System.out.println(customerDatabase);
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	
	//Search for customer records.
	public static void searchDatabaseForCustomer()
	{
		ResultSet sDFCrs = null;
		
		try
		{
			Statement sFCStmt = conn.createStatement();
			String query[] = {"SELECT * FROM Customer WHERE DateOfBirth LIKE '" + NBOnlineStore.customerDOB 
					+ "' AND FirstName LIKE '" + NBOnlineStore.customerFirstName 
					+ "' AND LastName LIKE '" + NBOnlineStore.customerLastName + "'"};
			
			for(String q : query)
			{
				sDFCrs = sFCStmt.executeQuery(q);
				while(sDFCrs.next())
				{
					NBOnlineStore.returnCustomerID = sDFCrs.getInt("CustomerID");
					NBOnlineStore.returnCustomerFirstName = sDFCrs.getString("FirstName");
					NBOnlineStore.returnCustomerLastName = sDFCrs.getString("LastName");
					NBOnlineStore.returnCustomerDOB = sDFCrs.getString("DateOfBirth");
					NBOnlineStore.returnAccountStatus = sDFCrs.getString("AccountStatus");

					//System.out.println(firstName + " " + lastName); //Test Line
					Customer.checkAccountStatus();
				}
			}			
		}
		catch (SQLException e)
		{
			e.printStackTrace();			
		}
		finally
		{
			if (sDFCrs != null)
			{
				System.out.println("Your account was not found...\nPlease re-enter your details: ");
				//Reset input variables.
				NBOnlineStore.customerDOB = null;
				NBOnlineStore.customerFirstName = null;
				NBOnlineStore.customerLastName = null;
				//Restart search process
				NBOnlineStore.searchForCustomer();
			}
		}		
	}
	
	//Get all products from database.
	public static void displayAllProducts()
	{
		try
		{
			Statement dAPStmt = conn.createStatement();
			String query[] = {"SELECT * FROM Product"};
			
			for(String a : query)
			{
				ResultSet dAPrs = dAPStmt.executeQuery(a);
				
				while(dAPrs.next())
				{
					int quantity = dAPrs.getInt("Quantity");
					
					if (quantity > 0)
					{
						int productID = dAPrs.getInt("ProductID");
						String productName = dAPrs.getString ("ProductName");
						float rRP = dAPrs.getFloat ("RRP");
						String weight = dAPrs.getString ("Weight");
						String dimensions = dAPrs.getString("Dimensions");
						System.out.println("Product ID:"  + productID + " " + productName + " " + rRP + " " + 
											weight + "KG " + dimensions + " ");
						
						NBOnlineStore.tempProductList.add (new Product(productID, productName, quantity, 
																			rRP, weight, dimensions));
					}
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}
	
	//Search for product using keyword.
	public static void searchForProduct()
	{
		try
		{
			Statement sFPStmt = conn.createStatement();
			String query[] = {"SELECT * FROM Product WHERE ProductName LIKE '%" + Product.productKeyword + "%'"};
			
			for(String q : query)
			{
				ResultSet sFPrs = sFPStmt.executeQuery(q);
				
				while(sFPrs.next())
				{	
					int quantity = sFPrs.getInt("Quantity");
					
					if (quantity > 0)
					{
						int productID = sFPrs.getInt("ProductID");
						String productName = sFPrs.getString ("ProductName");						
						float rRP = sFPrs.getFloat ("RRP");
						String weight = sFPrs.getString ("Weight");
						String dimensions = sFPrs.getString("Dimensions");
						System.out.println("Product ID:"  + productID + " " + productName + " " + rRP + " " + 
											weight + "KG " + dimensions + " ");	
						
						NBOnlineStore.tempProductList.add (new Product(productID, productName, quantity, 
								rRP, weight, dimensions));
					}
				}
			}			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//Update product quantity database.
	public static void removeQuantity()
	{
		try
		{			
			String query = "UPDATE product set Quantity = " + (Customer.productQuantity - 1) + " where ProductID = " + Customer.productID;
			PreparedStatement preparedstmt = conn.prepareStatement(query);
			//preparedstmt.setInt(1, Customer.productID);			
			preparedstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void createDatabaseSalesOrder()
	{
		try
		{
			String query = "UPDATE product set Quantity = " + (Customer.productQuantity - 1) + 
							" where ProductID = " + Customer.productID;
			PreparedStatement preparedstmt = conn.prepareStatement(query);
			//preparedstmt.setInt(1, Customer.productID);			
			preparedstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		/*INSERT INTO `nbgardens`.`payment` (`AccountNumber`, `SortCode`, `ExpiryDate`, `CustomerID`, 
				`Address_Postcode`, `Address_DoorNumber`) VALUES 
				('195565', '015215', '2018-08-16', '2', 'NG5 9PD', '14');*/
	}
	
	//Terminate connection to database.
	public static void disconnectNBGardens()
	{
		System.out.println("Disconnecting from NB Gardens");
		try
		{
			conn.close();
			System.out.println("Disconnect Successful.");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Disconnect Not Successful.");
		}
	}
}
