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

	public static void selectSubCategory(String subCategory, String value) throws InterruptedException {
		
		System.out.println("Subcategory: " + subCategory + ", Value: "+ value);
		
		if (subCategory.equals("Brand")) {
			
			String xpathSearchBox = "//input[@class='x-searchable-list__textbox__aspect-Brand']";
			driver.findElement(By.xpath(xpathSearchBox)).clear();
			driver.findElement(By.xpath(xpathSearchBox)).sendKeys(value);
			
			Thread.sleep(2000);
			
			String xpathCheckbox = "//li[@name='Brand']/div/a/span[text()='" + value + "']";
			waitForElementPresent(xpathCheckbox);
			driver.findElement(By.xpath(xpathCheckbox)).click();
			
		} else if (subCategory.equals("US Shoe Size (Men's)")) {
			
			String xpathCheckBox = "//ul[@class='x-refine__main__value']/li/div/a/span[text()='" + value + "']";
			driver.findElement(By.xpath(xpathCheckBox)).click();
			
		}
		
		
	}

	
	public static String getNumberOfResults() {
		
		String xpathResults = "//h1[@class='srp-controls__count-heading']";
		String results = driver.findElement(By.xpath(xpathResults)).getText().replace("results", "").trim();
		
		return results;
		
	}


	public static void orderResultsBy(String orderBy) {
		
		String xpathDropdown = "//button[@id='srp-river-results-SEARCH_STATUS_MODEL_V2-w0-w1_btn']"; 
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
		
			double shippingPrice = Double.parseDouble(listShippingPrices.get(i).getText().replace("$", "").replace("+", "").replace("shipping", "").trim());
					
			double itemPrice = Double.parseDouble(listItemPrices.get(i).getText().replace("$", "").trim());
			double itemPriceWithShipping = itemPrice + shippingPrice;
			
			pricesSortedFound.add(itemPriceWithShipping);
			pricesSortedExpected.add(itemPriceWithShipping);
			
		}
		
		System.out.println("Prices found:");
		for (int i = 0; i < 5; i++) {
			
			System.out.println(pricesSortedFound.get(i));
						
		}
		
		System.out.println("Prices expected:");
		for (int i = 0; i < 5; i++) {
			
			System.out.println(pricesSortedExpected.get(i));
						
		}
		
		Collections.sort(pricesSortedFound);
	
		Assert.assertEquals(pricesSortedFound, pricesSortedExpected);
		System.out.println("Items were sorted by price correctly");
	}


	public static void printItems(int amount) {
		/*
		String xpathItems = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/a/h3[@class='s-item__title']";
		String xpathListOfPrices = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/div/div/span[@class='s-item__price']";
		
		List<WebElement> listItems = driver.findElements(By.xpath(xpathItems));
		List<WebElement> listItemPrices = driver.findElements(By.xpath(xpathListOfPrices));
		*/
		
		List<Product> listItems = getItemsFromList();
		//List<WebElement> listItemPrices = getPricesFromList();
		
		System.out.println("The first " + amount + " items are: ");
		
		for (int i = 0; i < amount; i++) {
			
			System.out.print("Item: " + listItems.get(i).getName()); //.getText());
			System.out.println(",  Price: " + listItems.get(i).getPrice()); // Prices.get(i).getText());
			
		}
		
	}


	public static void orderAndPrintItems(String sortBy, boolean asc, int amount) {
		
		//List<WebElement> listItems = new ArrayList<>();
		List<Product> listProducts = getItemsFromList();
		
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
		
		System.out.println("Products sorted by " + sortBy + " in " + ascending + " order");
		
		for (Product p: listProducts) {
			
			System.out.println("Product:  " + p.getName() + "  Price:  " + p.getPrice());
			
		}
		
		
		
		
		
		
		
		
		
		/*
		List<String> itemsToBeSorted = new ArrayList<>();
		
		if (orderBy.equals("Name")) {
			
		} else if (orderBy.equals("Price")) {
		
			
		}
		
		
		for (WebElement e: listItems) {
			
			itemsToBeSorted.add(e.getText());
			
		}
		
		Collections.sort(itemsToBeSorted);
		String ascending = "ascending";
		
		if (asc == false) {
			Collections.reverse(itemsToBeSorted);
			ascending = "descending";
		}
			
		
		System.out.println("Items sorted by " + orderBy + " in " + ascending + " order");
		
		for (String s: itemsToBeSorted) {
			
			System.out.println(s);
			
		}
		*/
	}
	
	
	private static List<Product> getItemsFromList() {
		
		String xpathItems = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/a/h3[@class='s-item__title']";
		List<WebElement> listItemNames = driver.findElements(By.xpath(xpathItems));
		
		String xpathListOfPrices = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/div/div/span[@class='s-item__price']";
		List<WebElement> listItemPrices = driver.findElements(By.xpath(xpathListOfPrices));
		
		String xpathListOfShippingPrices = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/div/div/span[@class='s-item__shipping s-item__logisticsCost']";
		List<WebElement> listShippingPrices = driver.findElements(By.xpath(xpathListOfShippingPrices));
		
		List<Product> listProducts = new ArrayList<>();
		
		for (int i = 0; i < listItemNames.size(); i++) {
			
			Product p = new Product();

			String name = listItemNames.get(i).getText();
			double price = Double.parseDouble(listItemPrices.get(i).getText().replace("$", "").split("to")[0].trim());
			double shippingPrice = 0;
			
			try {
				shippingPrice = Double.parseDouble(listShippingPrices.get(i).getText().replace("$", "").replace("+", "").replace("shipping", "").trim());
			} catch (NumberFormatException e) {
				shippingPrice = 0;
			}
			
			p.setName(name);
			p.setPrice(price);
			p.setShippingPrice(shippingPrice);
			
			listProducts.add(p);
			
		}
		
		return listProducts;
		
	}
	
	// To be removed - won't be used 
	private static List<WebElement> getPricesFromList() {
		
		String xpathListOfPrices = "//ul/li/div[@class='s-item__wrapper clearfix']/div[2]/div/div/span[@class='s-item__price']";
		List<WebElement> listItemPrices = driver.findElements(By.xpath(xpathListOfPrices));
		
		return listItemPrices;
		
	}

	
	
}
