package entities;

import java.util.Comparator;

public class Product {

	private String name;
	private double price;
	private double shippingPrice;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	/* Comparator - Sort list by Name Ascending */
	public static Comparator<Product> nameComparatorAsc = new Comparator<Product>() {

		public int compare(Product p1, Product p2) {
			
		   String name1 = p1.getName();
		   String name2 = p2.getName();
		   
		   //asc order
		   return name1.compareTo(name2);
	    }
	};
	
	/* Comparator - Sort list by Name Descending */
	public static Comparator<Product> nameComparatorDesc = new Comparator<Product>() {

		public int compare(Product p1, Product p2) {
			
		   String name1 = p1.getName();
		   String name2 = p2.getName();
		   
		   //desc order
		   return name2.compareTo(name1);
	    }
	};

	/* Comparator - Sort list by Price Ascending */
	public static Comparator<Product> priceComparatorAsc = new Comparator<Product>() {

		public int compare(Product p1, Product p2) {
			
		   Double price1 = p1.getPrice();
		   Double price2 = p2.getPrice();
		   
		   //asc order
		   return price1.compareTo(price2);
	    }
	};
	
	/* Comparator - Sort list by Price Descending */
	public static Comparator<Product> priceComparatorDesc = new Comparator<Product>() {

		public int compare(Product p1, Product p2) {
			
			Double price1 = p1.getPrice();
			Double price2 = p2.getPrice();
			   
			//desc order
			return price2.compareTo(price1);
	    }
	};
	
	
}
