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
	public void runTest() throws InterruptedException {
		
		// 1- Enter to Ebay
		// * Set language to English --> DONE
		HomePage.changeLanguage("English");
		
		// 2- Search for shoes --> DONE
		HomePage.searchBy("Shoes");
			
		// 3- Select brand PUMA --> DONE
		String subCategoryBrand = "Brand";
		String brand = "PUMA";
		ListingPage.selectSubCategory(subCategoryBrand, brand); 
	
		// 4- Select size 10 --> DONE
		String subCategorySize = "US Shoe Size (Men's)"; 
		String size = "10";
		ListingPage.selectSubCategory(subCategorySize, size);
		
		// 5- Print the number of results --> DONE
		String numberOfResults = ListingPage.getNumberOfResults();
		System.out.println("\nNumber of Results: " + numberOfResults + "\n");
		
		// 6- Order by price ascendant --> DONE
		// 7- Assert the order taking the first 5 results
		String orderBy = "Price + Shipping: lowest first";
		ListingPage.orderResultsBy(orderBy);
		
		// 8- Take the first 5 products with their prices and print them in console. --> DONE
		ListingPage.printItems(5);
		
		// 9- Order and print the products by name (ascendant) --> DONE
		ListingPage.orderAndPrintItems("Name", true, 5);
		
		// 10- Order and print the products by price in descendant mode --> DONE
		ListingPage.orderAndPrintItems("Price", false, 5);
		
		// 11- Repository must be created in any git place (github, bitbucket, etc) --> DONE
		
		// 12- Code must run in any CI tool. --
		
		// ********** CONTINUE HERE *******************
		// ADD XML TO RUN TEST CASE, RUN IT IN JENKINS
		// ******************************************
		
		// 13- Report should be sent by mail.
	
		
	}
	
	@AfterClass
	public void finishTest() {
		
		System.out.println("Test finished.");		
	    JOptionPane.showMessageDialog(frame, "Test finished.");
		driver.close();
		driver.quit();
		
	}
	
}
