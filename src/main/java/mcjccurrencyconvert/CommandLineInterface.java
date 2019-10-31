package mcjccurrencyconvert;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class CommandLineInterface {


	private BigDecimal num;
	private String Toinput;
	private String Frominput;

	private UserInput userinput;

	public CommandLineInterface() {
		super();
	}

	public CommandLineInterface(UserInput userinput) {
		super();
		this.userinput = userinput;
	}

	public BigDecimal getNum() {
		return num;
	}

	public String getToInput() {
		return Toinput;
	}

	public String getFromInput() {
		return Frominput;
	}

	public BigDecimal getAmount(InputStream input) {
		Scanner kb = new Scanner(input);
		
		System.out.println("Please enter the amount of money you have: ");
		String amount = kb.nextLine();
		if (!amount.matches("(^)?\\d+(\\.\\d+)?")) {
			System.out.println("invalid");
			System.exit(0);
		}
		num = new BigDecimal(amount);
        
		return num;
	}

	public String getCurrencyFrom(InputStream input) {
		Scanner scan = new Scanner(input);

		System.out.println("What would you like to convert from?");
		Frominput = scan.next();
		

		return Frominput;

	}

	public String getCurrencyTo(InputStream input) {
		Scanner scan = new Scanner(input);

		System.out.println("What would you like to convert to?");
		Toinput = scan.next();

		scan.close();

		return Toinput;

	}

}
