import java.util.*;

public class MainClass {
	static Scanner read = new Scanner(System.in);
	public static void main(String[] args) {
		int numberOfRows = readValue("How many rows are there in the plane?", 1);
		boolean[][] seats = new boolean[numberOfRows][4];
		double priceOfTicket = readValue("How much does the one-way ticket costs?", 1.0);
		boolean var = false;
		do
		{
/*ADDREADV*/printSArray(new String[] {"What do you want to do?", "1: Buy tickets", "2: Check",
					"3: Cancel tickets", "4: Exit the program"});			
			int action = readValue("", 1);
			if (action > 4 || action < 1)
			{
				System.out.println("The action selected is not valid");
			}
			else if (action == 1)
			{
				buyTicket(seats, priceOfTicket);
			}
			else if (action == 2)
			{
				int[] foo = askSeat(numberOfRows);
				checkSeats(foo[0],foo[1],seats);	
			}
			else if (action == 3)
			{
				cancelSeat(seats);
			}
			else
			{
				if (askConfirmation("Are you sure you want to exit the program?"))
					var = true;
			}
		}while (!var);
	}//main	
	/**
	 * Method to print the boolean array.
	 * @param inSeats The array to print.
	 */	
	static void printArray(boolean[][] inSeats)
	{
		System.out.println("This is the layout of the plane." + "\n");
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
		boolean error = false;
		int row = 0, column = 0;
		do
		{
			String digits = "", letters = "";
			String seat = readValue("Insert the number of the seat (1 - " + inNumberOfRows + ") (A - D)", " ").toUpperCase();
			for (char bar : seat.toCharArray())
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
				error = true;
			
			if (error)										
				System.out.println("The seat entered is not valid.");
			
		}while(error);
		return new int[] {row,column};
	}	
	/**
	 * A method to cancel a given seat.
	 * @param inSeats The array containing the seats busy.
	 */
	static void cancelSeat(boolean[][] inSeats)
	{
		printArray(inSeats);
		System.out.print("Which seat do you want to cancel?");
		int[] seat = askSeat(inSeats.length);
		if (!inSeats[seat[0]][seat[1]])
		{
			System.out.println("Error, that seat is empty.");
		}
		else
		{
			if (askConfirmation("Are you sure you want to cancel that seat?"))
				inSeats[seat[0]][seat[1]] = false;
			System.out.println("Seat cancelled succesfully.");
		}		
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
							//System.out.println("Insert the number of the seat (1 - " + numberOfRows + ") (A - D)");
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
							if (letters.length() != 1 || Integer.parseInt(digits) > 1/*numberOfRows*/)
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
		return null;
	}//methodbuy
	/**
	 * A method to check if a seat is busy.
	 * @param row The row of the seat.
	 * @param column The column of the seat.
	 * @param inSeats The array containing the seats busy.
	 */
	static void checkSeats(int row, int column, boolean[][] inSeats)
	{
		if (inSeats[row][column])
		{
			System.out.println("That seat is busy");
		}
		else
		{
			System.out.println("That seat is empty");
		}
	}	
	/**
	 * A method to ask for a int.
	 * @param prompt The string to be printed when asking for the value.
	 * @param flag The type of this variable is the data type we are going to ask.
	 * @return The value entered
	 */
	static int readValue(String prompt, int flag)
	{
		System.out.print(prompt + " ");
		return read.nextInt();
	}
	/**
	 * A method to ask for a double.
	 * @param prompt The string to be printed when asking for the value.
	 * @param flag The type of this variable is the data type we are going to ask.
	 * @return The value entered.
	 */
	static double readValue(String prompt, double flag)
	{
		System.out.print(prompt + " ");
		return read.nextDouble();
	}
	/**
	 * A method to ask for a double.
	 * @param prompt The string to be printed when asking for the value.
	 * @param flag The type of this variable is the data type we are going to ask.
	 * @return The value entered.
	 */
	static String readValue(String prompt, String flag)
	{
		System.out.print(prompt + " ");
		return read.next();
	}
	/**
	 * A method to print a String array.
	 * @param text The string array containing the text to be printed.
	 */
	static void printSArray(String[] text)
	{		
		for (String foo : text)
		{
			System.out.println(foo);
		}
	}
	/**
	 * A method to ask for confirmation.
	 * @param prompt The text to be printed before asking for confirmation.
	 * @return True if yes, false any other value.
	 */
	static boolean askConfirmation(String prompt)
	{		
		String confirmation = readValue(prompt, " ").toLowerCase();
		return confirmation.equals("yes") || confirmation.equals("y") ? true : false;		
	}
	
}//class
