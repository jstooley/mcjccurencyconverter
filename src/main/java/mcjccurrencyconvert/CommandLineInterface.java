package mcjccurrencyconvert;

import java.math.BigDecimal;
import java.util.Scanner;

public class CommandLineInterface {

	BigDecimal num;
	String input;
	String input2;

	public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
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
		Scanner scan = new Scanner(System.in);

		System.out.println("Amount?");
		num = scan.nextBigDecimal();

		scan.close();

		return num;

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
