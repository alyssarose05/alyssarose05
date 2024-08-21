/* Alyssa Ayala
 * Period 7
 * Started on: November 2, 2018
 * Finished on: November 16, 2018
 * Extra Credit Assignment: Combo Menu
 */
import java.util.Scanner;

public class ComboMenu 
{
	public static void main(String[]args)
	{
		//declare and initialize variable for total
		double total = 0.00;
		//declare and initialize boolean variables for whether or not a user selects a sandwich, selected a beverage, selected a side
		boolean selectsSandwich = true;
		boolean selectsBeverage = true;
		boolean selectsFrenchFries = true;
		
		//declare and initialize string variable for sandwich, 
		//frenchFries, frenchFrySize, beverage, beverage size, int for number of ketchup packets
		String sandwich = "";
		String frenchFries = "";
		String frenchFrySize = "";
		String beverage = "";
		String beverageSize = "";
		int numKetchupPackets = 0;
		
		//to display specific costs in the order summary at the end
		double sandwichCost = 0.00; 
		double frenchFriesCost = 0.00;
		double beverageCost = 0.00;
		double ketchupCost = 0.00;
		
	
		Scanner input1 = new Scanner(System.in); 
		Scanner input2 = new Scanner(System.in);
		Scanner input3 = new Scanner(System.in);
		Scanner input4 = new Scanner(System.in);
		Scanner input5 = new Scanner(System.in);
		Scanner input6 = new Scanner(System.in);
		
		System.out.println("Note: Make all your responses lowercase please.");
		System.out.println("Welcome to New York's Best Sandwiches!");
		//SOP for user input of what type of sandwich they would like?
		System.out.println("What type of sandwich would you like?");
		System.out.println("bologna: $5.00");
		System.out.println("chicken: $5.25");
		System.out.println("ham: $5.45");
		System.out.println("cheese: $5.45");
		System.out.println("beef: $5.75");
		System.out.println("tofu: $5.75");
		
		sandwich = input1.nextLine();

		if (sandwich.indexOf("bologna") >= 0)
		{
			sandwichCost = 5.00;
			total = total + sandwichCost;
			System.out.println("Since you selected a " + sandwich + " sandwich, $" + sandwichCost + " has been added to your total. Your total is now $" + total + ".");
		}
		//Conditional for if sandwich input equals chicken add $5.25 to total
		else if (sandwich.indexOf("chicken") >= 0)
		{
			sandwichCost = 5.25;
			total = total + sandwichCost;
			System.out.println("Since you selected a " + sandwich + " sandwich, $" + sandwichCost + " has been added to your total. Your total is now $" + total + ".");
		}
		else if (sandwich.indexOf("ham") >=0 || sandwich.indexOf("cheese") >=0)
		{
			sandwichCost = 5.45;
			total = total + sandwichCost;
			System.out.println("Since you selected a " + sandwich + " sandwich, $" + sandwichCost + " has been added to your total. Your total is now $" + total + ".");
		}
    	//if it's beef 6.25 and tofu 5.75. You are welcome to have a bigger selection of sandwiches. 
		else if (sandwich.indexOf("beef") >= 0 || sandwich.indexOf("tofu") >= 0)
		{
			sandwichCost = 5.75;
			total = total + sandwichCost;
			System.out.println("Since you selected a " + sandwich + " sandwich, $" + sandwichCost + " has been added to your total. Your total is now $" + total + ".");
		}
		//if user doesn't select a valid sandwich you should let them know and selected a sandwich should be false
		else
		{
			selectsSandwich = false;
			System.out.println("You did not select a valid sandwich. We assume you don't want a sandwich.");
			System.out.println("Since you did not select a sandwich, $" + sandwichCost + " has been added to your total. Your total is now $" + total + ".");
		}
										
	
    
		//SOP for french fries
		System.out.println("Would you like french fries?");
		frenchFries = input2.nextLine();
	
		//conditional if the user wants fries 
		if (frenchFries.indexOf("yes") >= 0)
		{ 
			//if they do want fries ask them what size they would like (different prices for each size and should be added to total)
			System.out.println(("What size would you like?"));
			System.out.println("small: $1.00");
			System.out.println("medium: $1.50");
			System.out.println("large: $2.00");
			frenchFrySize = input3.nextLine();
			
			if (frenchFrySize.indexOf("small") >= 0)
			{
				frenchFriesCost = 1.00;
				total = total + frenchFriesCost;
				System.out.println("Since you ordered french fries with the size " + frenchFrySize + ", $" + frenchFriesCost + " has been added to your total. Your total is now $" + total + ".");
			}
			else if (frenchFrySize.indexOf("medium") >= 0)
			{
				frenchFriesCost = 1.50;
				total = total + frenchFriesCost;
				System.out.println("Since you ordered french fries with the size " + frenchFrySize + ", $" + frenchFriesCost + " has been added to your total. Your total is now $" + total + ".");
			}
			else if (frenchFrySize.indexOf("large") >= 0)
			{
				frenchFriesCost = 2.00;
				total = total + frenchFriesCost;
				System.out.println("Since you ordered french fries with the size " + frenchFrySize + ", $" + frenchFriesCost + " has been added to your total. Your total is now $" + total + ".");
			}
			else
			{
				selectsFrenchFries = false;
				System.out.println("You did not enter a valid french fries size. We assume you don't want french fries.");
				System.out.println("Since you did not select french fries, $" + frenchFriesCost + " has been added to your total. Your total is now $" + total + ".");
			}
		}
		//if they do not want fries selected french fies should be false
		else
		{
			selectsFrenchFries = false;
			System.out.println("Since you did not select french fries, $" + frenchFriesCost + " has been added to your total. Your total is now $" + total + ".");
		}
    	
		//SOP for beverage 
		System.out.println("What beverage would you like?");
		System.out.println("(The price depends on the size of your beverage, which you will choose after you choose your beverage.)");
		System.out.println("water");
		System.out.println("coca-cola");
		System.out.println("pepsi");
		System.out.println("sprite");
		System.out.println("ginger ale");
		System.out.println("iced tea");
		beverage = input4.nextLine();
		
		//conditional for beverage size (include different prices for each size and what would be added to the total)
		if (beverage.indexOf("water") >=0 || beverage.indexOf("coca-cola") >=0 || beverage.indexOf("pepsi") >=0 || beverage.indexOf("sprite") >=0 || beverage.indexOf("ginger ale") >= 0 || beverage.indexOf("iced tea") >=0)
		{
			System.out.println("What size would you like?");
			System.out.println("small: $1.00");
			System.out.println("medium: $1.50");
			System.out.println("large: $2.00");
			beverageSize = input5.nextLine();
			
			if (beverageSize.indexOf("small") >= 0)
			{
				beverageCost = 1.00;
				total = total + beverageCost;
				System.out.println("Since you select the beverage " + beverage + " with the size " + beverageSize + ", $" + beverageCost + " has been added to your total. Your total is now $" + total + ".");
			}
			else if (beverageSize.indexOf("medium") >=0)
			{
				beverageCost = 1.50;
				total = total + beverageCost;
				System.out.println("Since you select the beverage " + beverage + " with the size " + beverageSize + ", $" + beverageCost + " has been added to your total. Your total is now $" + total + ".");
			}
			else if (beverageSize.indexOf("large") >= 0)
			{
				beverageCost = 2.00;
				total = total + beverageCost;
				System.out.println("Since you select the beverage " + beverage + " with the size " + beverageSize + ", $" + beverageCost + " has been added to your total. Your total is now $" + total + ".");
			}
			else
			{
				selectsBeverage = false;
				System.out.println("You did not enter a valid beverage size. We assume you don't want a beverage.");
				System.out.println("Since you did not select a beverage, $" + beverageCost + " has been added to your total. Your total is now $" + total + ".");
			}
		//if no beverage is selected, selected a beverage should be false
		}
		else
		{
			selectsBeverage = false;
			System.out.println("You did not enter a valid beverage. We assume you don't want a beverage.");
			System.out.println("Since you did not select a beverage, $" + beverageCost + " has been added to your total. Your total is now $" + total + ".");
		}
		
		//conditional for is selected a beverage, french fries and sandwhich then a discount is given
		if (selectsSandwich == true)
		{
			total = total - 1.00;
			System.out.println("Since you selected a sandwich, $1.00 has been taken off the original price!");
			System.out.println("Your total is now $" + total + ".");
		}
		else
		{
			System.out.println("Since you did not select a sandwich, there are no discounts available for sandwiches.");
			System.out.println("Your total is now $" + total + ".");
		}

		if (selectsFrenchFries == true)
		{
			total = total - 0.75;
			System.out.println("Since you selected french fries, $0.75 has been taken off the original price!");
			System.out.println("Your total is now $" + total + ".");
		}
		else
		{
			System.out.println("Since you did not select french fries, there are no discounts available for french fries.");
			System.out.println("Your total is now $" + total + ".");
		}
		
		if (selectsBeverage == true)
		{
			total = total - 0.50;
			System.out.println("Since you selected a beverage, $0.50 has been taken off the original price!");
			System.out.println("Your total is now $" + total + ".");
		}
		else
		{
			System.out.println("Since you did not select a beverage, there are no discounts available for beverages.");
			System.out.println("Your total is now $" + total + ".");
		}
		
    	//SOP for how many ketchup packets they want
		System.out.println("How many ketchup packets do you want?");
		System.out.println("Each ketchup packet costs $0.25.");
		numKetchupPackets = input6.nextInt();
		//ketchup packets are $0.25, calculate total cost of ketchup and add to total
		ketchupCost = numKetchupPackets * 0.25;
		total = total + ketchupCost;
		System.out.println("Since you bought " + numKetchupPackets + " ketchup packet(s), your total is now $" + total + ".");
		
		//SOP summarizing order (should incoroporate the use of selected_a_sandwich, beverage, fries..)
		System.out.println("Order Summary:");
		
		if (selectsSandwich == true)
		{
			System.out.println("Sandwich: " + sandwich + ", Cost: $" + sandwichCost);
			System.out.println("Discount: $1.00 off!");
		}
		else
		{
			System.out.println("You did not select a sandwich.");
		}
		
		if (selectsFrenchFries == true)
		{
			System.out.println("French Fries Size: " + frenchFrySize + ", Cost: $" + frenchFriesCost);
			System.out.println("Discount: $0.75 off!");
		}
		else
		{
			System.out.println("You did not select french fries.");
		}
		
		if (selectsBeverage == true)
		{
			System.out.println("Beverage: " + beverage + ", Size: " + beverageSize + ", Cost: $" + beverageCost);
			System.out.println("Discount: $0.50 off!");
		}
		else
		{
			System.out.println("You did not select a beverage.");
		}
		
		System.out.println("Number of Ketchup Packets: " + numKetchupPackets + ", Cost: $" + ketchupCost);
		System.out.println("Total: $" + total);
		System.out.println("Thank you, and have a good day/night!");
	
	}
}