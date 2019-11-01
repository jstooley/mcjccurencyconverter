package mcjccurrencyconvert;


import java.io.InputStream;
import java.util.Scanner;

public class UserInput {
	private Scanner scan;
	
	public String getInput(InputStream inputStream) {
		scan = new Scanner(inputStream);
		return scan.nextLine();
	}
}
