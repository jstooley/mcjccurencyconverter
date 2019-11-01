package mcjccurrencyconvert;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class CommandLineInterface {


	private BigDecimal num;
	private String toInput;
	private String fromInput;
	private UserInput userinput;
	private Map<String, BigDecimal> conversionRates;

	public CommandLineInterface(Map<String, BigDecimal> conversionRates) {
		super();
		this.conversionRates = conversionRates;
		
	}

	public CommandLineInterface(UserInput userinput) {
		super();
		this.userinput = userinput;
	}

	public BigDecimal getNum() {
		return num;
	}

	public String getToInput() {
		return toInput;
	}

	public String getFromInput() {
		return fromInput;
	}
	
	/**
	 * Get input from an input stream and return the amount of money the user wants to convert.
	 * @param input
	 * Input stream to get input from.
	 * @return
	 * Amount of money user wants to convert.
	 */
	public Optional<BigDecimal> getAmount(InputStream input) {
		Scanner kb = new Scanner(input);
		System.out.println("Please enter the amount of money you would like to convert: ");
		String amount = kb.nextLine();
		
		// checks to see if the input given was a digit.
		if (!amount.matches("(^)?\\d+(\\.\\d+)?")) {
			System.out.println("Invalid input please enter an amount of moeny!");
			return Optional.empty();
		}
		// creates a big decimal out of the given amount
		num = new BigDecimal(amount);
		return Optional.ofNullable(num);
	}
	
	/**
	 * Get input from an input stream and return a type of currency we are converting from.
	 * @param input
	 * Input stream to get input from.
	 * @param conversionRates
	 * List of currency types to confirm user gave valid input.
	 * @return
	 * A type of currency or null if input is incorrect.
	 */
	public Optional<String> getCurrencyFrom(InputStream input) {
		Scanner scan = new Scanner(input);
		System.out.println("What would you like to convert from?");
		fromInput = scan.next();
		
		if ((conversionRates.containsKey(fromInput)) || fromInput.equals("EUR")) {
			return Optional.ofNullable(fromInput);
		}
		System.out.println("Invalid input please enter currency type!");
		printRates();
		return Optional.empty();
	}

	/**
	 * Get input from an input stream and return the type of currency we are converting to.
	 * @param input
	 * Input stream to get input from.
	 * @param conversionRates
	 * List of currency types to confirm user gave valid input.
	 * @return
	 * A type of currency or null if input is incorrect.
	 */
	public Optional<String> getCurrencyTo(InputStream input) {
		Scanner scan = new Scanner(input);
		System.out.println("What would you like to convert to?");
		toInput = scan.next();
		if (conversionRates.containsKey(toInput) || toInput.equals("EUR")) {
			scan.close();
			return Optional.ofNullable(toInput);
		}
		scan.close();
		System.out.println("Invalid input please enter currency type!");
		printRates();
		return Optional.empty();
	}
	
	public void printRates() {
		int count = 1;
		System.out.println();
		for (String currency : conversionRates.keySet()) {
			if (count == 1) {
				System.out.print(currency + " ");
				count++;
			} else if (count % 4 == 0) {
				System.out.println(currency + " ");
				count++;
			} else {
				System.out.print(currency + " ");
				count++;
			}
		}
		System.out.println();
	}

}
