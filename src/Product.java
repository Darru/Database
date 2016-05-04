
public class Product extends Inventory
{
	private float rRP;
	private String weight;
	private String dimensions;
	
	//Search for product variables
	protected static final String browseListAll = "browse";
	protected static String productKeyword = null;
	protected static int quantity = 0;
	//Re-use able variables
	protected static String yesInput = null;
	protected static String nextMenu = null;
	
	public Product(int productid, String productNme, int itemQuantity, float recRetailPrice, 
					String productWeight, String productDimensions)
	{
		super(productid, productNme, itemQuantity);
		rRP = recRetailPrice;
		weight = productWeight;
		dimensions = productDimensions;
	}

	public float getrRP()
	{
		return rRP;
	}
	public void setrRP(float rRP)
	{
		this.rRP = rRP;
	}

	public String getWeight()
	{
		return weight;
	}
	public void setWeight(String weight)
	{
		this.weight = weight;
	}
	
	public String getDimensions()
	{
		return dimensions;
	}
	public void setDimensions(String dimensions)
	{
		this.dimensions = dimensions;
	}
	
	@Override
	public String toString ()
	{
		return "Product ID: " + productID + "\n" + "Product Name: " + productName + "\n" 
				+ "Price: " + rRP + "\nProduct weight: " + weight + "\nProduct dimensions: " + dimensions;
	}

	public static void searchForProduct()
	{
		//Search for product using keyword, show all similar products. Do not show if not in stock.		
		System.out.println("Please enter a keyword. To view all please enter: browse");
		productKeyword = NBOnlineStore.userInput.next();
				
		//Show all products or products with keyword
		if (productKeyword.equals(browseListAll))
		{
			//Reset productKeyword, get all products from database and print to console.
			productKeyword = null;
			DatabaseController.displayAllProducts();
			System.out.println("Products In Stock: " + NBOnlineStore.tempProductList);
			
			//Check if user wants to add product to cart.
			System.out.println("Would you like to add a product? y / n");
			yesInput = NBOnlineStore.userInput.next();			
			
			//Check for variations of user input.
			if (yesInput.equals("yes") || yesInput.equals("y") || yesInput.equals("Y") || yesInput.equals("Yes"))
			{
				yesInput = null;
				Customer.addToCart();
			}
			else 
			{
				NBOnlineStore.tempProductList.clear();
				NBOnlineStore.nextMenu();			
			}
		}
		else
		{
			//Search and display products that match key word from database.
			System.out.println("Items found that match you key word: ");
			DatabaseController.searchForProduct();
						
			//Check if user wants to add product to cart.
			System.out.println("Would you like to add a product? y / n");
			yesInput = NBOnlineStore.userInput.next();
			
			//Check for variations of user input.
			if (yesInput.equals("yes") || yesInput.equals("y") || yesInput.equals("Y") || yesInput.equals("Yes"))
			{
				//Clear temporary data
				yesInput = null;
				Customer.addToCart();
			}
			else
			{
				NBOnlineStore.tempProductList.clear();
				NBOnlineStore.nextMenu();
			}
		}
	}
	
	public static void addToCart()
	{
		
	}

}
