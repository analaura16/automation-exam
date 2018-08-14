package testSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.PageBase;
import pages.HomePage;
import pages.ListingPage;

import javax.swing.JOptionPane;

import org.testng.annotations.AfterClass;

public class TestCase extends PageBase {

	@BeforeClass
	public void setUp() throws Exception {
		
		setUpDriver();
		
	}
	
	@Test
	public void runTest() {
		
		// 1- Enter to Ebay
		// * Set language to English --DONE
		HomePage.changeLanguage("English");
		
		// 2- Search for shoes
		HomePage.searchBy("Shoes");
			
		// 3- Select brand PUMA
		String subCategoryBrand = "Brand";
		String brand = "PUMA";
		ListingPage.selectSubCategory(subCategoryBrand, brand); // it's failing
	
		// 4- Select size 10
		String subCategorySize = "US Shoe Size (Men's)"; // see how to solve the apostrophe issue
		String size = "10";
		ListingPage.selectSubCategory(subCategorySize, size);
		
		
		// 5- Print the number of results
		// 6- Order by price ascendant
		// 7- Assert the order taking the first 5 results
		// 8- Take the first 5 products with their prices and print them in console.
		// 9- Order and print the products by name (ascendant)
		// 10- Order and print the products by price in descendant mode
		// 11- Repository must be created in any git place (github, bitbucket, etc)
		// 12- Code must run in any CI tool.
		// 13- Report should be sent by mail.

		
	}
	
	@AfterClass
	public void finishTest() {
		
		System.out.println("Close Browser.");		
	    JOptionPane.showMessageDialog(frame, "Select OK to stop the webdriver and browser.");
		driver.close();
		driver.quit();
		
	}
	
}
