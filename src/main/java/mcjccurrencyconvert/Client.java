package mcjccurrencyconvert;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class Client {

	private Conversion conversion;
	private BigDecimal conversionRate;
	private BigDecimal originalAmount;
	private String euro = "EURO";
	
	public static void main(String[] args) {
		Client client = new Client();
		ConversionRates rates = new ConversionRates();
		Optional<InputStream> input = rates.makeXMLDocument("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		rates.parseXMLDocument(input.get());
		rates.readConversionRates();
		Map<String, BigDecimal> checkRates = rates.getConversionRates();
		CommandLineInterface currencyConverter = new CommandLineInterface(checkRates);
		Optional<BigDecimal> amount;
		
		do{
			 amount = currencyConverter.getAmount(System.in);
		} while(!amount.isPresent());
		Optional<String> currencyFrom;
		do{
			currencyFrom = currencyConverter.getCurrencyFrom(System.in);
		} while(!currencyFrom.isPresent());
		client.setOriginalAmount(amount.get());
		Optional<String> currencyTo;
		do {
			currencyTo = currencyConverter.getCurrencyTo(System.in);
		} while(!currencyTo.isPresent());
		
		if (currencyFrom.get().equals(client.getEuro())) {
			if (currencyTo.get().equals(client.getEuro())) {
				System.out.println("Cannont convert a euro into euro!");
				System.exit(1);
			}
			client.setConversion(new FromEuroConversion());
			client.setConversionRate(rates.getConversionRate(currencyTo.get()));
		} else if (currencyTo.get().equals(client.getEuro())) {
			client.setConversion(new ToEuroConversion());
			client.setConversionRate(rates.getConversionRate(currencyFrom.get()));
		} else {
			System.out.println("This transaction is not available please input Euro as one of the options!");
			//TODO: add a list currencies function
		}
		BigDecimal convertedAmount = client.getConversion().convert(amount.get(), client.getConversionRate());
		System.out.println("\nConverted from " + currencyFrom.get() + " to " + currencyTo.get() +
						   "\nAmount given: " + client.getOriginalAmount() + " " + currencyFrom.get() + 
						   "\nAmount recieved: " + convertedAmount + " " + currencyTo.get());
	}
	
	public String getEuro() {
		return euro;
	}

	public Conversion getConversion() {
		return conversion;
	}

	public void setConversion(Conversion conversion) {
		this.conversion = conversion;
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}
	
	
}
