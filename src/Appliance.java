// ----------------------------------------------------- 
// COMP 249 Assignment 1 
// Part I
// Due date 2020/01/31
// Written by: Xin Yuan Zhang 26373127 
// ----------------------------------------------------- 

/**
 * Appliance Class
 * @author Xin Yuan Zhang
 *
 */
public class Appliance {
	/**
	 * private variables for attributes type, brand, serial number, and price
	 * a static long variable as a counter increasing 1 whenever a new appliance object created
	 */
	private static long counter = 1000000;
	private String type;
	private String brand;
	private long serialNum;
	private double price;
	private final String[] appType = {"Fridge", "AirConditioner", "Washer", "Dryer", "Freezer", "Stove", "Dishwasher", "WaterHeaters", "Microwave"};
	
	/**
	 * default Constructor
	 */
	public Appliance() {
		type = "";
		brand = "";
		price = 1.0;
		serialNum = counter++;
	}
	
	/**
	 * copy constructor
	 * @param a for appliance
	 */
	public Appliance(Appliance a) {
		type = a.type;
		brand = a.brand;
		price = a.price;
		serialNum = counter++;
	}
	
	/**
	 * modify Type with validation
	 * @param t  for type
	 */
	public void setType(String t) {
		for(int i = 0; i < appType.length; ++i) {
			if (appType[i].toLowerCase().contentEquals((t.toLowerCase()))) {
				type = t;
				return;
			}
		}
		System.out.println("INVALID TYPE! Please modify it later, the correspoding appliance id is " + serialNum + "\n");
	}
	
	/**
	 * setter for Brand
	 * @param s for brand
	 */
	public void setBrand(String s) {
		brand = s;
	}
	
	/**
	 * modify price with validation
	 * @param p for price
	 */
	public void setPrice(double p) {
		if (p < 1.0) {
			System.out.println("INVALID PRICE! Please modify it later, the correspoding appliance id is " + serialNum + "\n");
			return;
		}
		price = p;
	}
	
	/**
	 * getter for price
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * getter for brand
	 * @return brand
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * getter for type
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * getter for serial number
	 * @return long
	 */
	public long getSerialNum() {
		return serialNum;
	}
	
	/**
	 * Override toString() method
	 */
	@Override
	public String toString() {
		return "Type: " + type + ", Brand: " + brand + ", S/N: " + serialNum + ", Price: " + price;
	}
	
	/**
	 * static method to compute total number of objects of Appliance class
	 * @return int
	 */
	public static int findNumberOfCreatedAppliances() {
		return (int)(counter - 1000000);
	}
	
	/**
	 * Override equals method
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (getClass() != o.getClass()) {
			return false;
		} else {
			Appliance a = (Appliance) o;
			return (type.contentEquals(a.type) && brand.contentEquals(a.brand) && price == a.price);
		}
	}
	
	/**
	 * method for testing price see if lower price exists
	 * 
	 */
	public boolean findCheaperThan(double d) {
		return (price < d);
	}
}
