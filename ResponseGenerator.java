import java.util.*;
/**
 * ResponseGenerator takes user input into account in order to determine an appropriate response.
 * 
 * @author (Ishaque Khan) 
 * @version (12/1/2016)
 */
public class ResponseGenerator
{
    // Lines 11 - 389 implement requirement 2a
    public String getGreeting() // Starts the conversation
	{
		return "Hello, I am a chemistry chat bot that can use gas laws and talk to you about chemistry!";
	}
	// Lines 19 - 22 and 58 - 61 implement requirement 4ai
	// Lines 20 - 63 implement requirement 4aii
    public String getResponse(String statement) // Finds an appropriate response based on user input
	{
		String response = "";
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "no", 0) >= 0)
		{
			response = "Why so negative?";
		}

		else if (findKeyword(statement, "gas", 0) >= 0)
		{
			response = determineLaw();
		}
		
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		
		else if (findKeyword(statement, "chemistry", 0) >= 0)
		{
			response = "I love chemistry!";
		}
		
		else if (findKeyword(statement, "atom", 0) >= 0)
		{
			response = "Atoms are made of protons, neutrons, electrons.";
		}

		else
		{
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			else
			{
				response = getRandomResponse();
			}
		}
		return response;
	}
	// Lines 66 - 89 implement requirement 3
    private int findKeyword(String statement, String goal, int startPos) // keyword finder used throughout this chatbot
	{
		String phrase = statement.trim();
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
		while (psn >= 0) 
		{
			String before = " ", after = " "; 
			if (psn > 0)
			{
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}
			if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))
					&& ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
			{
				return psn;
			}
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
		}
		return -1;
	}
	// Lines 92 - 120 implement requirement 4aiii
	private String transformIWantToStatement(String statement) // transforms an "I want to" state ment into a question
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}
	
	private String transformYouMeStatement(String statement) // transforms a "you-me" statement into a question
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
	
	private String determineLaw() // determines which gas law to use based on user input
	{
	    System.out.println("Which gas law would you like to use? (Ideal, Boyle, Charles, Avogadro)");
	    Scanner in = new Scanner (System.in);
	    String statement = in.nextLine();
	    String response2 = "";
	    if (statement.equalsIgnoreCase("Ideal"))
	    {
	        response2 = idealLaw();
	    }
	    if (statement.equalsIgnoreCase("Boyle"))
	    {
	        response2 = boyleLaw();
	    }
	    if (statement.equalsIgnoreCase("Charles"))
	    {
	        response2 = charlesLaw();
	    }
	    if (statement.equalsIgnoreCase("avogadro"))
	    {
	        response2 = avogadroLaw();
	    }
	    return response2;
	}
	// Lines 147 - 390 implement requirement 4aiv
	private String avogadroLaw() // solves for a variable in Avogadro's Law
	{
	    String response3 = "";
	    double n1 = 0.0;
	    double n2 = 0.0;
	    double V1 = 0.0;
	    double V2 = 0.0;
	    System.out.println("Would you like to solve for n1, n2, V1, or V2?");
	    Scanner in = new Scanner(System.in);
	    String statement = in.nextLine();
	    if (statement.equalsIgnoreCase("n1"))
	    {
	        System.out.println("nlease input V1 in Liters");
	        V1 = in.nextDouble();
	        System.out.println("nlease input n2 in Moles");
	        n2 = in.nextDouble();
	        System.out.println("nlease input V2 in Liters");
	        V2 = in.nextDouble();
	        n1 = (n2 * V1) / V2;
	        response3 = "n1 = " + n1 + " Moles";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("n2"))
	    {
	        System.out.println("nlease input V1 in Liters");
	        V1 = in.nextDouble();
	        System.out.println("nlease input n1 in Moles");
	        n1 = in.nextDouble();
	        System.out.println("nlease input V2 in Liters");
	        V2 = in.nextDouble();
	        n2 = (n1 * V2) / V1;
	        response3 = "n2 = " + n2 + " Moles";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("v1"))
	    {
	        System.out.println("nlease input n1 in Moles");
	        n1 = in.nextDouble();
	        System.out.println("nlease input n2 in Moles");
	        n2 = in.nextDouble();
	        System.out.println("nlease input V2 in Liters");
	        V2 = in.nextDouble();
	        V1 = (n1 * V2) / n2;
	        response3 = "V1 = " + V1 + " Liters";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("v2"))
	    {
	        System.out.println("nlease input V1 in Liters");
	        V1 = in.nextDouble();
	        System.out.println("nlease input n1 in Moles");
	        n1 = in.nextDouble();
	        System.out.println("nlease input n2 in Liters");
	        n2 = in.nextDouble();
	        V2 = (n2 * V1) / n1;
	        response3 = "V2 = " + V2 + " atm";
	        return response3;
	    }
	    return "";
	}
	
	private String charlesLaw() // solves for a variable in Charles' Law
	{
	    String response3 = "";
	    double T1 = 0.0;
	    double T2 = 0.0;
	    double V1 = 0.0;
	    double V2 = 0.0;
	    System.out.println("Would you like to solve for T1, T2, V1, or V2?");
	    Scanner in = new Scanner(System.in);
	    String statement = in.nextLine();
	    if (statement.equalsIgnoreCase("t1"))
	    {
	        System.out.println("Please input V1 in Liters");
	        V1 = in.nextDouble();
	        System.out.println("Please input T2 in Kelvin");
	        T2 = in.nextDouble();
	        System.out.println("Please input V2 in Liters");
	        V2 = in.nextDouble();
	        T1 = (T2 * V1) / V2;
	        response3 = "T1 = " + T1 + " Kelvin";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("t2"))
	    {
	        System.out.println("Please input V1 in Liters");
	        V1 = in.nextDouble();
	        System.out.println("Please input T1 in Kelvin");
	        T1 = in.nextDouble();
	        System.out.println("Please input V2 in Liters");
	        V2 = in.nextDouble();
	        T2 = (T1 * V2) / V1;
	        response3 = "T2 = " + T2 + " Kelvin";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("v1"))
	    {
	        System.out.println("Please input T1 in Kelvin");
	        T1 = in.nextDouble();
	        System.out.println("Please input T2 in Kelvin");
	        T2 = in.nextDouble();
	        System.out.println("Please input V2 in Liters");
	        V2 = in.nextDouble();
	        V1 = (T1 * V2) / T2;
	        response3 = "V1 = " + V1 + " Liters";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("v2"))
	    {
	        System.out.println("Please input V1 in Liters");
	        V1 = in.nextDouble();
	        System.out.println("Please input T1 in Kelvin");
	        T1 = in.nextDouble();
	        System.out.println("Please input T2 in Kelvin");
	        T2 = in.nextDouble();
	        V2 = (T2 * V1) / T1;
	        response3 = "V2 = " + V2 + " Liters";
	        return response3;
	    }
	    return "";
	}
	
	private String boyleLaw() // solves for a variable in Boyle's Law
	{
	    String response3 = "";
	    double P1 = 0.0;
	    double P2 = 0.0;
	    double V1 = 0.0;
	    double V2 = 0.0;
	    System.out.println("Would you like to solve for P1, P2, V1, or V2?");
	    Scanner in = new Scanner(System.in);
	    String statement = in.nextLine();
	    if (statement.equalsIgnoreCase("p1"))
	    {
	        System.out.println("Please input V1 in Liters");
	        V1 = in.nextDouble();
	        System.out.println("Please input P2 in Atm");
	        P2 = in.nextDouble();
	        System.out.println("Please input V2 in Liters");
	        V2 = in.nextDouble();
	        P1 = (P2 * V2) / V1;
	        response3 = "P1 = " + P1 + " atm";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("p2"))
	    {
	        System.out.println("Please input V1 in Liters");
	        V1 = in.nextDouble();
	        System.out.println("Please input P1 in Atm");
	        P1 = in.nextDouble();
	        System.out.println("Please input V2 in Liters");
	        V2 = in.nextDouble();
	        P2 = (P1 * V1) / V2;
	        response3 = "P2 = " + P2 + " atm";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("v1"))
	    {
	        System.out.println("Please input P1 in Atm");
	        P1 = in.nextDouble();
	        System.out.println("Please input P2 in Atm");
	        P2 = in.nextDouble();
	        System.out.println("Please input V2 in Liters");
	        V2 = in.nextDouble();
	        V1 = (P2 * V2) / P1;
	        response3 = "V1 = " + V1 + " Liters";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("v2"))
	    {
	        System.out.println("Please input V1 in Liters");
	        V1 = in.nextDouble();
	        System.out.println("Please input P1 in Atm");
	        P1 = in.nextDouble();
	        System.out.println("Please input P2 in Liters");
	        P2 = in.nextDouble();
	        V2 = (P1 * V1) / P2;
	        response3 = "V2 = " + V2 + " Liters";
	        return response3;
	    }
	    return "";
	}
	
	private String idealLaw() // solves for a variable in the Ideal Gas Law
	{
	    String response3 = "";
	    double P = 0.0;
	    double V = 0.0;
	    double n = 0.0;
	    double T = 0.0;
	    double R = 0.08206;
	    System.out.println("Would you like to solve for P, V, n, or T?");
	    Scanner in = new Scanner(System.in);
	    String statement = in.nextLine();
	    if (statement.equalsIgnoreCase("p"))
	    {
	        System.out.println("Please input V in Liters");
	        V = in.nextDouble();
	        System.out.println("Please input n in Moles");
	        n = in.nextDouble();
	        System.out.println("Please input T in Kelvin");
	        T = in.nextDouble();
	        P = (n * R * T)/ V;
	        response3 = "P = " + P + " atm";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("v"))
	    {
	        System.out.println("Please input P in Atm");
	        P = in.nextDouble();
	        System.out.println("Please input n in Moles");
	        n = in.nextDouble();
	        System.out.println("Please input T in Kelvin");
	        T = in.nextDouble();
	        V = (n * R * T)/ P;
	        response3 = "V = " + V + " liters";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("n"))
	    {
	        System.out.println("Please input P in Atm");
	        P = in.nextDouble();
	        System.out.println("Please input V in Moles");
	        V = in.nextDouble();
	        System.out.println("Please input T in Kelvin");
	        T = in.nextDouble();
	        n = (P * V) / (R * T);
	        response3 = "n = " + n + " moles";
	        return response3;
	    }
	    if (statement.equalsIgnoreCase("t"))
	    {
	        System.out.println("Please input P in Atm");
	        P = in.nextDouble();
	        System.out.println("Please input V in Liters");
	        V = in.nextDouble();
	        System.out.println("Please input n in Moles");
	        n = in.nextDouble();
	        T = (P * V) / (n * R);
	        response3 = "T = " + T + " kelvin";
	        return response3;
	    }
	    return "";
	}
	// Lines 391 - 399 implement requirement 2b
	private String getRandomResponse() // generates a random response when necessary
	{
		final int NUMBER_OF_RESPONSES = 10;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String[] responses = {"Interesting, tell me more.", "Hmmm.", "Do you really think so?", "You don't say.", "Dang.", "Wow.", "Quite surprising.", "Thoroughly Amusing.", "Are you sure about that?", "Woah bro."};
		String response = responses[whichResponse];
		return response;
	}
}
