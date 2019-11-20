package mcjccurrencyconvert;


import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class UserInput {
	private Scanner scan;
	
	public Optional<String> getInput(InputStream inputStream) {
		scan = new Scanner(inputStream);
		if (scan.hasNextLine()) {
			return Optional.ofNullable(scan.nextLine());
		}else{
			return Optional.empty();
		}
	}
}
