package mcjccurrencyconvert;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Optional;

public class Client {

	private Conversion conversion;
	private BigDecimal conversionRate;
	private BigDecimal originalAmount;
	private int euroCount = 0;
	
	public static void main(String[] args) {
		Client client = new Client();
		CommandLineInterface currencyConverter = new CommandLineInterface();
		ConversionRates rates = new ConversionRates();
		Optional<InputStream> input = rates.makeXMLDocument("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		rates.parseXMLDocument(input.get());
		rates.readConversionRates();
		Map<String, BigDecimal> checkRates = rates.getConversionRates();
		
		BigDecimal amount = currencyConverter.getAmount(System.in).get();
		client.setOriginalAmount(amount);
		String currencyFrom = currencyConverter.getCurrencyFrom(System.in, checkRates).get();
		String currencyTo = currencyConverter.getCurrencyTo(System.in, checkRates).get();
		if (currencyFrom.equals("EUR")) {
			client.addEuroCount();
			client.setConversion(new FromEuroConversion());
			client.setConversionRate(rates.getConversionRate(currencyTo));
		} else if (currencyTo.equals("EUR")) {
			client.addEuroCount();
			client.setConversion(new ToEuroConversion());
			client.setConversionRate(rates.getConversionRate(currencyFrom));
		} else {
			System.out.println("This transaction is not available please input Euro as one of the options!");
			//TODO: add a list currencies function
		}
		if (client.getEuroCount() == 1) {
			BigDecimal convertedAmount = client.getConversion().convert(amount, client.getConversionRate());
			System.out.println("\nConverted from " + currencyFrom + " to " + currencyTo +
						   "\nAmount given: " + client.getOriginalAmount() + " " + currencyFrom + 
						   "\nAmount recieved: " + convertedAmount + " " + currencyTo);
		} else if (client.getEuroCount() > 1) {
			System.out.println("Cannont convert a euro into euro!");
		}
	}
	
	public int getEuroCount() {
		return this.euroCount;
	}
	public void addEuroCount() {
		this.euroCount++;
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
