package pages;

import org.openqa.selenium.By;

import common.PageBase;

public class ListingPage extends PageBase {

	public static void selectSubCategory(String subCategory, String value) {
		
		System.out.println("Subcategory: " + subCategory + ", Value: "+ value);
		
		String xpathCheckbox = "//div[@class='x-refine__left__nav']/li/div/h3[text()='" + subCategory + "']"
				+ "/../following-sibling::ul/li/div/a/span[text()='" + value + "']";
		
		driver.findElement(By.xpath(xpathCheckbox)).click();
		
		
		
	}

}
