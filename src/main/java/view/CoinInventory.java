package view;

import java.util.List;
import java.util.Scanner;

import controller.CoinController;
import model.CoinItem;

public class CoinInventory {
	
	//create instances for entire program to use
	static Scanner innn = new Scanner(System.in);
	static CoinController controler = new CoinController();
	
	public static void main(String[] args) {
		
		//determines if program should continue to run
		boolean runMenu = true;
				
		System.out.println("Welcome to the coin Inventory Program");

			//main menu
			while(runMenu) {
				System.out.println("a. Add coin");
				System.out.println("b. View Current Coins");
				System.out.println("c. Update Coins");
				System.out.println("d. Delete Coin");
				System.out.println("e. Exit Program");
				
				System.out.println("Please select item:");
				Scanner inputchoice = new Scanner(System.in);
				String choice = inputchoice.nextLine();	
												
				if(choice.equals("a")) {
					add();
				}else if(choice.equals("b")) {
					view();
				}else if(choice.equals("c")) {
					update();
					inputchoice.close();
				}else if(choice.equals("d")) {
					delete();
				}else if(choice.equals("e")) {
					runMenu = false;
				}else {
					System.out.print("Invalid input");
				}
			}
			
			System.out.println("Good Bye!!");
	}
	
	//add coin to database
	private static void add() {
		//get user input
		System.out.println("Enter Coin Type");
		String coin = innn.nextLine();
		System.out.println("Input price");
		String price = innn.nextLine();
		double convertedPrice = Double.parseDouble(price);
		CoinItem itemToAd = new CoinItem(coin, convertedPrice);
		//call controller to add to database
		controler.insert(itemToAd);
	}
	
	//delete coin from database
	private static void delete() {
		System.out.println("*****Current items in database*****");
		view();
		System.out.println("***********************************");
		System.out.println("Input type to delete from list above");
		System.out.println("Input type");
		//get user input
		String inputtype = innn.nextLine();
		System.out.println("Input the price");
		String inputpricestring =  innn.nextLine();
		double inputprice = Double.parseDouble(inputpricestring);
		//add input to new CoinItem instance
		CoinItem itemToDelete = new CoinItem(inputtype, inputprice);
		//delete from database
		controler.delete(itemToDelete);
	}
	
	//update record
	private static void update() {
		//list to hold results
		List<CoinItem> resultlist;
		//get input
		System.out.println("How would you like to search?");
		System.out.println("1. Search by type");
		System.out.println("2. Search by price");
		String selectionstring = innn.nextLine();
		int selection = Integer.parseInt(selectionstring);
		//innn.nextLine();
		
		//figure out how to search
		switch(selection) {
		case 1:
			System.out.println("Enter Type of coin: ");
			String coinType = innn.nextLine();
			resultlist = controler.searchByType(coinType);
			break;
		case 2:
			System.out.println("Enter price of coin");
			double price = innn.nextDouble();
			resultlist = controler.searchByPrice(price);
			break;
		default:
			System.out.println("Invalid input...We will just search by type of coin");
			System.out.println("Enter Type of coin: ");
			String coinTypedefault = innn.nextLine();
			resultlist = controler.searchByType(coinTypedefault);
		}
		
		if(resultlist.isEmpty() == true) {
			System.out.println("Oops..We did not find that :( ");
		}else {
			System.out.println("here is what we found:");
			
			System.out.println("***************");
			for(CoinItem i : resultlist) {
				System.out.println("*****************");
				System.out.println("Coin ID:" + i.getId() + "     Detail:   " + i.returnDetailNoId());
				System.out.println("********************");
			}
			//get id number of found items
			System.out.println("Input id number to select item:");
			String idnumberstring = innn.nextLine();
			int inputnumber = Integer.parseInt(idnumberstring);
			
			//get item by id
			CoinItem edit = controler.searchById(inputnumber);
			System.out.println("Item to edit: " +"["+ "Coin:  "  + edit.getType() + "   Price: " + edit.getPrice() + "]");
			System.out.println("What do you want to edit?");
			System.out.println("1 - Type");
			System.out.println("2 - price");
			
			//get what part of coin object to update
			String whatToUpdateString = innn.nextLine();
			int whatToUpdate = Integer.parseInt(whatToUpdateString);
			if(whatToUpdate == 1) {
				System.out.println("Editing type");
				Scanner editType = new Scanner(System.in);
				String newType = editType.nextLine();
				edit.setType(newType);
				editType.close();
			}else {
				System.out.println("Edit price");
				Scanner editPrice = new Scanner(System.in);
				String newPrice = editPrice.nextLine();
				double convertedPrice = Double.parseDouble(newPrice);
				edit.setPrice(convertedPrice);
				editPrice.close();
			}
			
			//update database
			controler.updateCoin(edit);
		
		}		
				
	}
	
	//view coins in database
	private static void view() {
		//get everything from database
		List<CoinItem> everything = controler.showCoins();
		//print out info
		System.out.println("*****************");
		for(CoinItem singleitem : everything) {
			System.out.println("*******************");
			System.out.println(singleitem.returnDetails());
			System.out.println("********************");
		}
		System.out.println("****************");
	}

}
