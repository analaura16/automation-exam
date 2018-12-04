package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.PageBase;
import entities.Product;

public class ListingPage extends PageBase {

	
	// Filter results on UI by selecting a "value" in the "subCategory" specified
	public static void selectSubCategory(String subCategory, String value) throws InterruptedException {
		
		System.out.println("Subcategory to select: " + subCategory + ", Value: "+ value);
		
		/*String xpathCheckbox = "//ul[@class='x-refine__main__value']/li/div/a/span[text()='" + value + "']";
		waitForElementPresent(xpathCheckbox);
		driver.findElement(By.xpath(xpathCheckbox)).click();		
		*/
		// Old code - Brand used to have a search box to type in the brand to filter by
		if (subCategory.equals("Brand")) {
			
			String xpathSearchBox = "//input[@class='x-searchable-list__textbox__aspect-Brand']";
			
			driver.findElement(By.xpath(xpathSearchBox)).clear();
			driver.findElement(By.xpath(xpathSearchBox)).sendKeys(value);
			
			Thread.sleep(2000);
			
			String xpathCheckbox = "//li[@name='Brand']/div/a/span[text()='" + value + "']";
			waitForElementPresent(xpathCheckbox);
			driver.findElement(By.xpath(xpathCheckbox)).click();
			
		} else if (subCategory.equals("US Shoe Size (Men's)")) {
			
			String xpathCheckbox = "//ul[@class='x-refine__main__value']/li/div/a/span[text()='" + value + "']";
			driver.findElement(By.xpath(xpathCheckbox)).click();
			
		}
		
		
	}

	// Gets the total number of results found, according to the filters selected
	public static String getNumberOfResults() {
		
		String xpathResults = "//h1[@class='srp-controls__count-heading']";
		String results = driver.findElement(By.xpath(xpathResults)).getText().replace("results", "").trim();
		
		return results;
		
	}

 
	// Order the results in the UI by the criteria specified by "orderBy" parameter
	public static void orderResultsBy(String orderBy) {
		
		String xpathDropdown = "//button[@id='w4-w1_btn']"; // "//button[@id='srp-river-results-SEARCH_STATUS_MODEL_V2-w0-w1_btn']"; 
		driver.findElement(By.xpath(xpathDropdown)).click();
		driver.findElement(By.xpath(xpathDropdown)).click();
	
		String xpathOption = "//div[@class='srp-sort']/ul/li/a/span[text()='" + orderBy + "']";
		driver.findElement(By.xpath(xpathOption)).click();
		
		String xpathListOfPrices = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/div/div/span[@class='s-item__price']";
		String xpathListOfShippingPrices = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/div/div/span[@class='s-item__shipping s-item__logisticsCost']";
		
		List<WebElement> listItemPrices = driver.findElements(By.xpath(xpathListOfPrices));
		List<WebElement> listShippingPrices = driver.findElements(By.xpath(xpathListOfShippingPrices));
		
		List<Double> pricesSortedFound = new ArrayList<>();
		List<Double> pricesSortedExpected = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
		
			double shippingPrice = Double.parseDouble(getShippingPrice(listShippingPrices.get(i).getText()));
					
			double itemPrice = Double.parseDouble(listItemPrices.get(i).getText().replace("$", "").trim());
			double itemPriceWithShipping = itemPrice + shippingPrice;
			
			pricesSortedFound.add(itemPriceWithShipping);
			pricesSortedExpected.add(itemPriceWithShipping);
			
		}
		
		Collections.sort(pricesSortedFound);
	
		Assert.assertEquals(pricesSortedFound, pricesSortedExpected);
		System.out.println("Items were sorted by " + orderBy + " correctly");
		
	}

	// Get the value of the shipping price, and get rid off any non numeric value
	// E.g.: For "+$5.99 shipping", return just "5.99"
	// If "Free International Shipping" is found, then return "0"
	private static String getShippingPrice(String longShippingPrice) {
		
		String shippingPrice = longShippingPrice.replace("$", "").replace("+", "").replace("shipping", "").trim();
		
		if (longShippingPrice.equals("Free International Shipping"))
			shippingPrice = "0";
		
		return shippingPrice;
		
	}


	// Print products in console 
	public static void printItems(int amount) {
		
		List<Product> listItems = getItemsFromList(amount);
		
		System.out.println("\nThe first " + amount + " items are: ");
		
		for (int i = 0; i < amount; i++) {
			
			System.out.print("Item: " + listItems.get(i).getName());
			System.out.println(",  Price: " + listItems.get(i).getPrice());
			
		}
		
	}


	// Order items by "sortBy", in ascending/descending order 
	public static void orderAndPrintItems(String sortBy, boolean asc, int amount) {
		
		List<Product> listProducts = getItemsFromList(amount);
		
		if (sortBy.equals("Name") && asc == true ) {
			
			Collections.sort(listProducts, Product.nameComparatorAsc);			
			
		} else if (sortBy.equals("Name") && asc == false) {
			
			Collections.sort(listProducts, Product.nameComparatorDesc);
			
		} else if (sortBy.equals("Price") && asc == true) {
			
			Collections.sort(listProducts, Product.priceComparatorAsc);	
			
		} else if (sortBy.equals("Price") && asc == false) {
			
			Collections.sort(listProducts, Product.priceComparatorDesc);	
			
		} 
		
		String ascending = "ascending";
		if (!asc) ascending = "descending"; 
		
		System.out.println("\nProducts sorted by " + sortBy + " in " + ascending + " order\n");
		
		for (Product p: listProducts) {
			
			System.out.println("Product:  " + p.getName() + "  Price:  " + p.getPrice());
			
		}
		
		
		
	}
	
	// Get from UI the amount of items indicated by "amount" and put them in a list
	private static List<Product> getItemsFromList(int amount) {
		
		String xpathItems = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/a/h3[@class='s-item__title']";
		List<WebElement> listItemNames = driver.findElements(By.xpath(xpathItems));
		
		String xpathListOfPrices = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/div/div/span[@class='s-item__price']";
		List<WebElement> listItemPrices = driver.findElements(By.xpath(xpathListOfPrices));
		
		String xpathListOfShippingPrices = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/div/div/span[@class='s-item__shipping s-item__logisticsCost']";
		List<WebElement> listShippingPrices = driver.findElements(By.xpath(xpathListOfShippingPrices));
		
		List<Product> listProducts = new ArrayList<>();
		
		for (int i = 0; i < amount; i++) {
			
			Product p = new Product();

			String name = listItemNames.get(i).getText();
			double price = Double.parseDouble(listItemPrices.get(i).getText().replace("$", "").split("to")[0].trim());
			double shippingPrice = Double.parseDouble(getShippingPrice(listShippingPrices.get(i).getText()));
						
			p.setName(name);
			p.setPrice(price);
			p.setShippingPrice(shippingPrice);
			
			listProducts.add(p);
			
		}
		
		return listProducts;
		
	}
	
	
}
