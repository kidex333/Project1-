public class MainClass {
	/**
	 * An enum to select between data types
	 */
	public enum Types 
	{
		INTEGER, DOUBLE, STRING;
	}
	public static void main(String[] args) {
		int numberOfRows = new DataType().readValue("How many rows are there in the plane?", Types.INTEGER).intVal;
		boolean[][] seats = new boolean[numberOfRows][4];
		double priceOfTicket = new DataType().readValue("How much does the one-way ticket costs?", Types.DOUBLE).doubleVal;
		boolean var = false;
		do
		{
			printSArray(new String[] {"What do you want to do?", "1: Buy tickets", "2: Check",
					"3: Cancel tickets", "4: Exit the program"});			
			int action = new DataType().readValue("", Types.INTEGER).intVal;
			switch (action){
				case 1: buyTicket(seats, priceOfTicket);
					break;
				case 2: int[] foo = askSeat(numberOfRows);	
					checkSeats(foo[0],foo[1],seats);
					break;
				case 3: cancelSeat(seats);
					break;
				case 4:
					if (askConfirmation("Are you sure you want to exit the program?"))
									var = true;
					break;
			}
		}while (!var);
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
			String seat = new DataType().readValue("Insert the number of the seat (1 - " + inNumberOfRows + ") (A - D)", Types.STRING).litValue.toUpperCase();
			for (char bar : seat.toCharArray())
			{
				if ((int) bar > 47 && (int) bar < 57)
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
				System.out.println("The seat entered is not valid.");
			row = Integer.parseInt(digits) - 1;
			column = letters.charAt(0) - 65;
			
		}while (error);
		return new int[] {row,column};
	}	
	/**
	 * A method to cancel a given seat.
	 * @param inSeats The array containing the seats busy.
	 */
	static void cancelSeat(boolean[][] inSeats)
	{
		printArray(inSeats);
		System.out.print("Which seat do you want to cancel?" + "\n");
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
	/**
	 * A method to buy tickets in the plane.
	 * @param inSeats The array containing the seats busy.
	 * @param inPriceOfTicket The price of a single one-way ticket.
	 */
	static void buyTicket(boolean[][] inSeats, double inPriceOfTicket)
	{
		double finalprice = 0;
		int tickets = 0;
		int numberOfTickets = countArray(inSeats, false);
		do
		{			
			tickets = new DataType().readValue("How many tickets do you want to buy?", Types.INTEGER).intVal;			
			if (tickets > 10 || tickets < 0 || tickets > numberOfTickets)
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
							luggage = new DataType().readValue("How many will you carry?", Types.INTEGER).intVal;
						finalprice += price + (luggage*15);
						inSeats[seat[0]][seat[1]] = true;
						count--;
					}
					
				}
				finalprice = discount ? finalprice * 0.92 : finalprice;
				System.out.println("\n" + "The final price is " + finalprice + "\n");
			}			
		}while (tickets < 0 || tickets > 10 || tickets > numberOfTickets);		
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
		String confirmation = new DataType().readValue(prompt, Types.STRING).litValue.toLowerCase();
		return confirmation.equals("yes") || confirmation.equals("y") ? true : false;		
	}
	/**
	 * A method to count how many occurences are there in an array.
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
}