import java.util.*;

public class MainClass {
	public static void main(String[] args) {		
		int numberOfRows = 0;
		double priceOfTicket = 0;		
		Scanner read = new Scanner(System.in);
		System.out.print("How many rows are there in the plane? ");
		numberOfRows = read.nextInt();
		String[][] seats = new String[numberOfRows][4];
		System.out.print("How much does the one-way ticket costs? ");
		priceOfTicket = read.nextDouble();
		byte action = 0;
		do
		{
			System.out.println("What do you want to do?");
			System.out.println("1: Buy tickets");
			System.out.println("2: Check");
			System.out.println("3: Cancel tickets");
			action = read.nextByte();
			if (action > 3 || action < 1)
			{
				System.out.println("The action selected is not valid");
			}
			else if (action == 1)
			{
				// true if one-way
				int typeofticket = 0;
				do
				{
					boolean typeOfTicket = true;
					System.out.println("What type of ticket do you want to buy?");
					System.out.println("1: One-Way ticket");
					System.out.println("2: Return ticket");
					typeofticket = read.nextInt();
					
					if (typeofticket == 1)
					{
						typeOfTicket = true;
					}
					else if (typeofticket == 2)
					{
						typeOfTicket = false;		
					}
					else
					{
						System.out.println("That type of ticket doesn't exists.");
					}
					}while (typeofticket > 2 || typeofticket < 1);
					int numberOfTickets = 0;
					do
					{
						System.out.print("How many tickets do you want? ");
						numberOfTickets = read.nextInt();
						if (numberOfTickets > 0 && numberOfTickets < 11)
						{
							System.out.print("Do you want an especific seat? ");
						}
						else
						{
							System.out.println("The number of tickets inserted is not valid.");
						}
					} while (numberOfTickets < 0 || numberOfTickets > 11);
			}
		}while (action > 3 || action < 1);
	}//main
	
}//class
