package mcjccurrencyconvert;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class CommandLineInterface {

	// private Scanner scan;

	private String num;
	private String input;
	private String input2;

	private UserInput userinput;

	public CommandLineInterface() {
		super();
	}

	public CommandLineInterface(UserInput userinput) {
		super();
		this.userinput = userinput;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public BigDecimal getAmount() {
		num = userinput.getInput(System.in);
		System.out.println("Amount?");

		if (!num.matches("(^)?\\d+(\\.\\d+)?")) {
			System.out.println("invalid");
		}
		BigDecimal realnum = new BigDecimal(num);

		return realnum;
	}

	public String getCurrencyFrom() {
		Scanner scan = new Scanner(System.in);

		System.out.println("What would you like to convert from?");
		input2 = scan.nextLine();

		scan.close();

		return input2;

	}

	public String getCurrencyTo() {
		Scanner scan = new Scanner(System.in);

		System.out.println("What would you like to convert to?");
		input = scan.nextLine();

		scan.close();

		return input;

	}

}
