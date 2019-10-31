package mcjccurrencyconvert;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Optional;

public class Client {

	private Conversion conversion;
	private BigDecimal conversionRate;
	private BigDecimal originalAmount;
	
	public static void main(String[] args) {
		Client client = new Client();
		CommandLineInterface currencyConverter = new CommandLineInterface();
		ConversionRates rates = new ConversionRates();
		Optional<InputStream> input = rates.makeXMLDocument("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		rates.parseXMLDocument(input.get());
		rates.readConversionRates();
		
		BigDecimal amount = currencyConverter.getAmount(System.in);
		client.setOriginalAmount(amount);
		String currencyFrom = currencyConverter.getCurrencyFrom(System.in);
		String currencyTo = currencyConverter.getCurrencyTo(System.in);
		if (currencyFrom.equals("EUR")) {
			client.setConversion(new FromEuroConversion());
			client.setConversionRate(rates.getConversionRate(currencyTo));
		} else if (currencyTo.equals("EUR")) {
			client.setConversion(new ToEuroConversion());
			client.setConversionRate(rates.getConversionRate(currencyFrom));
		} else {
			System.out.println("This transaction is not available please input Euro as one of the options");
		}
		BigDecimal convertedAmount = client.getConversion().convert(amount, client.getConversionRate());
		System.out.println("\nConverted from " + currencyFrom + " to " + currencyTo +
						   "\nAmount given: " + client.getOriginalAmount() + " " + currencyFrom + 
						   "\nAmount recieved: " + convertedAmount + " " + currencyTo);
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
