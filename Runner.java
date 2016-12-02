import java.util.Scanner;
/**
 * Write a description of class Runner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Runner
{
    public static void main(String[] args)
	{
		ResponseGenerator generator = new ResponseGenerator();
		
		System.out.println (generator.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		
		while (!statement.equalsIgnoreCase("Bye"))
		{
			System.out.println (generator.getResponse(statement));
			statement = in.nextLine();
		}
	}
}
