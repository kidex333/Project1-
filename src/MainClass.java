import java.util.*;

public class MainClass {
	static Scanner read = new Scanner(System.in);
	public static void main(String[] args) {		
		int numberOfRows = 0;
		double priceOfTicket = 0;		
		System.out.print("How many rows are there in the plane? ");
		numberOfRows = read.nextInt();
		int lengthRows = (int) Math.log10(numberOfRows) + 1;
		boolean[][] seats = new boolean[numberOfRows][4];
		System.out.print("How much does the one-way ticket costs? ");
		priceOfTicket = read.nextDouble();
		int action = 0;
		do
		{
			System.out.println("What do you want to do?");
			System.out.println("1: Buy tickets");
			System.out.println("2: Check");
			System.out.println("3: Cancel tickets");
			System.out.println("4: Exit the program");
			action = read.nextInt();
			if (action > 4 || action < 1)
			{
				System.out.println("The action selected is not valid");
			}
			else if (action == 1)
			{
				// true if one-way
				
				
			}
			else if (action == 2)
			{
				int[] foo = askSeat(numberOfRows);
				if (checkSeats(foo[0],foo[1],seats))
				{
					System.out.println("That seat is busy");
				}
				else
				{
					System.out.println("That seat is empty");
				}
			}
			else if (action == 3)
			{
				int[] 
			}
			else
			{
				String confirmation;
				System.out.print("Are you sure you want to exit the program? ");
				confirmation = read.nextLine().toLowerCase();
				if (confirmation.equals("yes") || confirmation.equals("y"))
				{
					System.exit(0);
				}
			}
		}while (true);
	}//main
	
	/**
	 * Method to print a array.
	 * @param inSeats The array to print.
	 */
	
	static void printArray(boolean[][] inSeats)
	{
		for (int i = 0; i < inSeats.length; i++)
		{
			for (int j = 0; j < inSeats[0].length; j++)
			{
				char foo = (char) (j+65);
				System.out.print( (i+1) + Character.toString(foo) + " ");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Method to ask for a seat.
	 * @param inNumberOfRows The number of rows of the airplane.
	 * @return The array containing the row and the column of the seat.
	 */
	static int[] askSeat(int inNumberOfRows)
	{	
		String digits = "";
		String letters = "";
		boolean error = false;
		int row = 0;
		int column = 0;
		do
		{
			digits = "";
			letters = "";
			System.out.println("Insert the number of the seat (1 - " + inNumberOfRows + ") (A - D)");
			String seat = read.next().toUpperCase();
			char[] foo = seat.toCharArray();								
			for (char bar : foo)
			{
				if ((int) bar > 47 && (int) bar < 57)
				{
					digits += Character.toString(bar);
					error = false;
				}
				else if ((int) bar > 64 && (int) bar < 68)
				{
					letters += Character.toString(bar);
					error = false;
				}
				else
				{
					error = true;
					break;
				}
			}
			if (letters.length() != 1 || Integer.parseInt(digits) > inNumberOfRows)
			{
				error = true;
			}
			
			if (error)
			{									
				System.out.println("The seat entered is not valid.");
			}
			}while(error);
			return new int[] {row,column};
	}
	
	static boolean[][] buyTicket(boolean[][] inSeats, double inPriceOfTicket)
	{
		boolean[][] seats = inSeats;
		double price;
		int tickets = 0;
		do
		{
			System.out.print("How many tickets do you want to buy? ");
			tickets = read.nextInt();
			if (tickets < 0 || tickets > 10)
				{
					System.out.println("The number of tickets entered is not valid.");
				}
				else
				{
					boolean discount = tickets > 6 ? true : false;
					int count = tickets;
					while (count > 0)
					{
						String digits = "";
						String letters = "";
						boolean error = false;
						int row = 0;
						int column = 0;
						do
						{
							digits = "";
							letters = "";
							System.out.println("Insert the number of the seat (1 - " + numberOfRows + ") (A - D)");
							String seat = read.next().toUpperCase();
							char[] foo = seat.toCharArray();								
							for (char bar : foo)
							{
								if ((int) bar > 47 && (int) bar < 57)
								{
									digits += Character.toString(bar);
									error = false;
								}
								else if ((int) bar > 64 && (int) bar < 68)
								{
									letters += Character.toString(bar);
									error = false;
								}
								else
								{
									error = true;
									break;
								}
							}
							if (letters.length() != 1 || Integer.parseInt(digits) > numberOfRows)
							{
								error = true;
							}
							
							if (error)
							{									
								System.out.println("The seat entered is not valid.");
							}
							else
							{
								row = Integer.parseInt(digits) - 1;
								column = (int) letters.charAt(0) - 65;
								if (seats[row][column])
								{										
									System.out.println("That seat is busy.");
								}									
							}
						} while(error || seats[row][column]);
						
						//do
						//{
						//	System
						//}while
						
						count--;
					}
				}
			}while (tickets < 0 || tickets > 10);
	}//methodbuy
	/**
	 * A method to check if a seat is busy.
	 * @param row The row of the seat.
	 * @param column The column of the seat.
	 * @param inSeats The array containing the seats busy.
	 * @return If the seat is busy or not.
	 */
	static boolean checkSeats(int row, int column, boolean[][] inSeats)
	{
		return inSeats[row][column];
	}
	
}//class
