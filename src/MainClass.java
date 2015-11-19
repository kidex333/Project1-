import java.util.Scanner;

public class MainClass {
	/**
	 * An enum to select between data types
	 */
	public enum Types
	{
		INTEGER, DOUBLE, STRING;
	}
	public static void main(String[] args) {
		int numberOfRows = (Integer) readValue("How many rows are there in the plane?", Types.INTEGER);
		boolean[][] seats = new boolean[numberOfRows][4];
		double priceOfTicket = (Double) readValue("How much does the one-way ticket costs?", Types.DOUBLE);
		boolean stop = false;
		do
		{
			printSArray(new String[] {"What do you want to do?", "1: Buy tickets", "2: Check",
					"3: Cancel tickets", "4: Exit the program"});			
			int action = (Integer) readValue("", Types.INTEGER);
			switch (action){
				case 1: 
					buyTicket(seats, priceOfTicket);
					break;
				case 2: 
					int[] foo = askSeat(numberOfRows);	
					checkSeats(foo[0],foo[1],seats);
					break;
				case 3: 
					cancelSeat(seats);
					break;
				case 4:
					if (askConfirmation("Are you sure you want to exit the program?"))
						stop = true;
					break;
			}
		}while (!stop);
	}
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
				if (inSeats[i][j])
				{
					System.out.print("X" + " ");
				}
				else
				{
					char foo = (char) (j+65);
					System.out.print( (i+1) + Character.toString(foo) + " ");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
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
			String seat = ((String) readValue("Insert the number of the seat (1 - " + inNumberOfRows + ") (A - D)", Types.STRING)).toUpperCase();
			for (char bar : seat.toCharArray())
			{
				if ((int) bar > 47 && (int) bar < 58)
				{
					digits += Character.toString(bar);
					error = false;
				}
				else if ((int) bar > 64 && (int) bar < 69)
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
			{
				System.out.println("The seat entered is not valid.");
			}
			else
			{
				row = Integer.parseInt(digits) - 1;
				column = letters.charAt(0) - 65;
			}
			
		}while (error);
		return new int[] {row,column};
	}	
	/**
	 * A method to cancel a given seat.
	 * @param inSeats The array containing the seats busy.
	 */
	static void cancelSeat(boolean[][] inSeats)
	{
		if (countArray(inSeats, true) == 0)
		{
			System.out.println("All the seats are empty.");
		}
		else
		{
			printArray(inSeats);
			System.out.print("Which seat do you want to cancel?" + "\n");
			int[] seat = askSeat(inSeats.length);
			if (!inSeats[seat[0]][seat[1]])
			{
				System.out.println("Error, that seat is empty." + "\n");
			}
			else
			{
				if (askConfirmation("Are you sure you want to cancel that seat?"))
					inSeats[seat[0]][seat[1]] = false;
				System.out.println("Seat cancelled succesfully.");
			}
		}
	}
	/**
	 * A method to buy tickets in the plane.
	 * @param inSeats The array containing the seats busy.
	 * @param inPriceOfTicket The price of a single one-way ticket.
	 */
	static void buyTicket(boolean[][] inSeats, double inPriceOfTicket)
	{
		if (countArray(inSeats, false) == 0)
		{
			System.out.println("All the seats are busy." + "\n");
		}
		else
		{
			double finalprice = 0;
			int tickets = 0;
			int numberOfSeats = countArray(inSeats, false);
			do
			{			
				tickets = (Integer) readValue("How many tickets do you want to buy?", Types.INTEGER);			
				if (tickets > 10 || tickets < 0 || tickets > numberOfSeats)
				{
					System.out.println("The number of tickets entered is not valid");
				}
				else
				{
					boolean discount = tickets >= 6;
					int count = tickets;
					while (count > 0)
					{						
						printArray(inSeats);
						int[] seat = askSeat(inSeats.length);
						if (inSeats[seat[0]][seat[1]])
						{
							System.out.println("Sorry, that seat is already bought.");
						}
						else
						{
							double price = askConfirmation("Do you want to buy a return ticket?") ? inPriceOfTicket*1.5 : inPriceOfTicket;
							int luggage = 0;
							if (askConfirmation("Will you carry any luggage?"))
								luggage = (Integer) readValue("How many will you carry?", Types.INTEGER);
							finalprice += price + (luggage*15);
							inSeats[seat[0]][seat[1]] = true;
							count--;
						}
						
					}
					finalprice = discount ? finalprice * 0.92 : finalprice;
					System.out.println("\n" + "The final price is " + finalprice + "\n");
				}			
			}while (tickets < 0 || tickets > 10 || tickets > numberOfSeats);
		}
	}
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
		String confirmation = ((String) readValue(prompt, Types.STRING)).toLowerCase();
		return confirmation.equals("yes") || confirmation.equals("y") ? true : false;		
	}
	/**
	 * A method to count how many occurences there are in an array.
	 * @param inSeats The array to check.
	 * @param flag Which value will be considered one and which zero.
	 * @return The number of occurences in the array of the value passed.
	 */
	static int countArray(boolean[][] inSeats, boolean flag)
	{
		int count = 0;
		for (boolean[] foo : inSeats)
		{
			for (boolean bar : foo)
			{
				count += flag == bar ? 1 : 0;
			}
		}
		return count;
	}	
	/**
	 * A method to read a value of aspecific data type.
	 * @param prompt The text to be printed before reading the data.
	 * @param type The type of data to be readed.
	 * @return The object containing the data readed.
	 */
	public static Object readValue(String prompt, MainClass.Types type)
	{
		Object result = null;
		Scanner read = new Scanner(System.in);
		System.out.print(prompt + " ");
		switch (type){
			case INTEGER: result = read.nextInt();
			break;
			case DOUBLE: result = read.nextDouble();
			break;
			case STRING: result = read.next();
			break;				
		}		
		return result;
	}
}