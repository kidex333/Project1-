import java.util.*;

/**
 * An object containing 3 different Data Type class variables so we can read 3 different Data Types with the same method.
 */
public class DataType 
{
	Scanner read = new Scanner(System.in);
	
	int intVal;
	double doubleVal;
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
	public DataType readValue(String prompt, MainClass.Types type)
	{
		DataType foo = new DataType();
		System.out.print(prompt + " ");
		switch (type){
			case INTEGER: foo.intVal = read.nextInt();
			break;
			case DOUBLE: foo.doubleVal = read.nextDouble();
			break;
			case STRING: foo.litValue = read.next();
			break;				
		}		
		return foo;
	}
}
