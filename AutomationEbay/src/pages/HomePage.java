package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.PageBase;

public class HomePage extends PageBase {

	public static void changeLanguage(String lang) {
		
		String currentLanguage = driver.findElement(By.xpath("//*[@id='gh-eb-Geo-a-default']/span[2]")).getText();
				
		// If the current language is not 'lang' (e.g.:'English'), then select lang from dropdown list
		if (!currentLanguage.equals(lang)) {
			
			String xpathDropdown = "//*[@id='gh-eb-Geo-a-default']";
			driver.findElement(By.xpath(xpathDropdown)).click();
			
			String xpathLang = "//*[@id='gh-eb-Geo-o']/ul/li/a/span[text()='" + lang + "']";
			
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLang)));
			
			driver.findElement(By.xpath(xpathLang)).click();
			
		}
		
	}
	
	
	public static void searchBy(String value) {
		
		String xpathInput = "//input[@id='gh-ac']";
		
		driver.findElement(By.xpath(xpathInput)).clear();
		driver.findElement(By.xpath(xpathInput)).sendKeys(value);
		
		// Click Search button
		String xpathButton = "//input[@value='Search']";
		
		driver.findElement(By.xpath(xpathButton)).submit();
		
	}
	
}
