package mcjccurrencyconvert;

import java.math.BigDecimal;

public class FromEuroConversion implements Conversion {

	public BigDecimal convert(BigDecimal amount, BigDecimal conversionRate) {
		return amount.multiply(conversionRate);
	}

}
