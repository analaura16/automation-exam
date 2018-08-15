package pages;

import org.openqa.selenium.By;

import common.PageBase;

public class ListingPage extends PageBase {

	public static void selectSubCategory(String subCategory, String value) {
		
		System.out.println("Subcategory: " + subCategory + ", Value: "+ value);
		
		if (subCategory.equals("Brand")) {
			
			String xpathSearchBox = "//input[@class='x-searchable-list__textbox__aspect-Brand']";
			driver.findElement(By.xpath(xpathSearchBox)).clear();
			driver.findElement(By.xpath(xpathSearchBox)).sendKeys(value);
			
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
		
	}

}
