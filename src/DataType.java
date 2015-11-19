import java.util.*;

/**
 * An object containing 3 different Data Type class variables so we can read 3 different Data Types with the same method.
 */
public class DataType 
{	
	Integer intVal;
	Double doubleVal;
	String litValue;
	/**
	 * Vanilla constructor of the class.
	 */
	public DataType()
	{
		intVal = 0;
		doubleVal = 0.0;
		litValue = "";
	}
	/**
	 * Method to read a value and store it in a specified Data Type.
	 * @param prompt The text to be printed when asking for the value.
	 * @param selection The Data Type desired, referred to the enumeration Types.
	 * @return A DataType object containing the value readed.
	 */
	public Object readValue(String prompt, MainClass.Types type)
	{
		Object result = null;
		Scanner read = new Scanner(System.in);
		DataType foo = new DataType();
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
