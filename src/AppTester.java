// ----------------------------------------------------- 
// COMP 249 Assignment 1 
// Part II
// Due date 2020/01/31
// Written by: Xin Yuan Zhang 26373127 
// ----------------------------------------------------- 
import java.util.Scanner;

public class AppTester {

	public static void main(String[] args) {
		
		/**
		 * constant variable for password, and counter for failed password attempts
		 */
		final String password = "c249";

		/**
		 * Display welcome message
		 */
		System.out.println("Welcome to Applicance System! (By Xin Yuan Zhang) \n");
		
		/**
		 * Prompt user to input maxAppliances, and use it to create an empty appliance array to store appliance objects
		 */
		System.out.print("Please enter the max number of appliances the store can contain: ");
		Scanner keyIn = new Scanner(System.in);
		int maxAppliances = keyIn.nextInt();
		Appliance[] inventory = new Appliance[maxAppliances];
		
		/**
		 * Display a user menu, until user choose a function
		 */
		int menuOption = 0;
		int failedPassword1 = 0;	// for counting failed password attempts in option 1
		int failedPassword2 = 0;	// for counting failed password attempts in option 2
		int suspicious = 0;			// for option 1 logic, when suspicious behavior exists, exit program
		
		/**
		 * outer loop for main menu, with condition always be true
		 */
		do {
			/**
			 * display menu options
			 */
			System.out.println("\nWhat do you want to do?");
			System.out.println("\t1. Enter new appliances (password required)");
			System.out.println("\t2. Change information of an appliance (password required)");
			System.out.println("\t3. Display all appliances by a specific brand");
			System.out.println("\t4. Display all appliances under a certain a price.");
			System.out.println("\t5. Quit");
			System.out.print("Please enter your choice > ");
			
			/**
			 * accept user choice and make sure it's valid
			 */
			menuOption = keyIn.nextInt();
			if (menuOption > 5 || menuOption < 1) {
				System.out.println("Invalid option! Please choose again");
			} else {		//true working logic begins
				switch(menuOption) {
				case 1: 		// for menu option 1, using always true do-while loop
					do {
						System.out.print("Please enter the password: ");
						if (!keyIn.next().contentEquals(password)) {	// if wrong password, record it into counter, also update suspicious counter
							++failedPassword1;							// accordingly, if it detects suspicious behavior, exit program
							if (failedPassword1 == 3) {
								++suspicious;
								failedPassword1 = 0;
								if (suspicious == 4) {
								System.out.println("Program detected suspicious activities and will terminate immediately!");
								System.exit(0);
								}
								break;
							} else {
								System.out.println("Wrong password! Try again.\n");
							}
						} else {		// password correct, reset failed password counter
							failedPassword1 = 0;
							suspicious = 0;
							/**
							 * compute how many empty position in inventory
							 */
							int noOfCreated = Appliance.findNumberOfCreatedAppliances();
							int positionsLeft = maxAppliances - noOfCreated;
							if (positionsLeft == 0) {			// if it's full, exit option 1
								System.out.println("There are no more space for new appliances.");
								break;
							}
							System.out.print("How many appliances do you want to enter? ");
							int noOfApp = keyIn.nextInt();
							if (noOfApp > positionsLeft) {
								System.out.println("There are only " + positionsLeft + " positions left.");
								break;
							} else { // enough room for new appliances
								for (int i = 0; i < noOfApp; ++i) {
									System.out.println("Appliance " + (i + 1) + " : ");
									System.out.print("Enter type, brand, and price(double) in turn using space to seperate: ");
									String t = keyIn.next();
									String b = keyIn.next();
									double p = keyIn.nextDouble();
									inventory[noOfCreated + i] = new Appliance();
									inventory[noOfCreated + i].setType(t);
									inventory[noOfCreated + i].setPrice(p);
									inventory[noOfCreated + i].setBrand(b);
									System.out.println(inventory[noOfCreated + i].toString() + "\n");
								}
								break;	// finished, back to main menu
							}		
						}
					} while(true);
					break;
				case 2: 	// for modifying function
					do {
						System.out.print("Please enter the password: ");
						if (!keyIn.next().contentEquals(password)) {	// wrong password
							++failedPassword2;
							if (failedPassword2 == 3) {
								failedPassword2 = 0;
								break;
							} else {
								System.out.println("Wrong password! Try again.\n");
							}
						} else { // right password
							failedPassword2 = 0;
							System.out.print("Please enter the serial no of the appliance you want to change: ");
							long serialNo = keyIn.nextLong();
							int noOfCreated = Appliance.findNumberOfCreatedAppliances();
							int index = -1;
							for (int i = 0; i < noOfCreated; ++i) {
								if (inventory[i] != null && inventory[i].getSerialNum() == serialNo) {
									index = i;
									System.out.println("\nAppliance Serial # : " + inventory[i].getSerialNum());
									System.out.println("Brand : " + inventory[i].getBrand());
									System.out.println("Type : " + inventory[i].getType());
									System.out.println("Price : " + inventory[i].getPrice());
									break;	// find the appliance to output
								}
							}
							if (index == -1) {
								System.out.println("Appliance NOT found.");
								break;
							} else {
								do { // enter subMeau
									System.out.println("\nWhat information would you like to change?");
									System.out.println("\t1. Brand");
									System.out.println("\t2. Type");
									System.out.println("\t3. Price");
									System.out.println("\t4. Quit");
									System.out.print("Enter your choice > ");
									int subMenuOption = keyIn.nextInt();
									if (subMenuOption > 4 || subMenuOption < 1) {
										System.out.println("Invalid option! Please choose again");
									} else {
										int exit4 = 0;
										switch(subMenuOption) {
										case 1:
											System.out.print("Please enter new brand: ");
											String newBrand = keyIn.next();
											inventory[index].setBrand(newBrand);
											
											System.out.println("\nAppliance Serial # " + inventory[index].getSerialNum());
											System.out.println("Brand " + inventory[index].getBrand());
											System.out.println("Type " + inventory[index].getType());
											System.out.println("Price " + inventory[index].getPrice());
											break;
										case 2:
											System.out.print("Please enter new Type: ");
											String newType = keyIn.next();
											inventory[index].setType(newType);
											
											System.out.println("\nAppliance Serial # " + inventory[index].getSerialNum());
											System.out.println("Brand " + inventory[index].getBrand());
											System.out.println("Type " + inventory[index].getType());
											System.out.println("Price " + inventory[index].getPrice());
											break;
										case 3:
											System.out.print("Please enter new Price: ");
											Double newPrice = keyIn.nextDouble();
											inventory[index].setPrice(newPrice);
											
											System.out.println("Appliance Serial # " + inventory[index].getSerialNum());
											System.out.println("Brand " + inventory[index].getBrand());
											System.out.println("Type " + inventory[index].getType());
											System.out.println("Price " + inventory[index].getPrice());
											break;
										case 4:
											exit4 = 1;
											break;
										default:
											break;
										}
										if (exit4 == 1) {
											break;
										}
									}
								} while(true);
								break;
							}
						}
					} while(true);
					break;
				case 3: 		// for brand search
					System.out.print("Please enter a brand name to search: ");
					String b = keyIn.next();
					int brandCount = 0;
					for (int i = 0; i < inventory.length; ++i) {
						if (inventory[i] != null && inventory[i].getBrand().contentEquals(b)) {
							++brandCount;
							System.out.println(inventory[i].toString());
						}
					}
					if (brandCount == 0) {
						System.out.println("\nAppliance NOT found");
					}
					break;
				case 4: 	// for price search
					System.out.print("Please enter a price to compare: ");
					double d = keyIn.nextDouble();
					int priceCount = 0;
					for (int i = 0; i < maxAppliances; ++i) {
						if (inventory[i] != null && inventory[i].findCheaperThan(d)) {
							++priceCount;
							System.out.println(inventory[i].toString());
						}
					}
					if (priceCount == 0) {
						System.out.println("\nAppliance NOT found");
					}
					break;
				case 5: // exit program with proper message
					System.out.println("Thanks for using appliance system! Goodbye");
					keyIn.close();
					System.exit(0);
					break;
				default: 
					break;
				}
			}
		} while(true);
	}

}
