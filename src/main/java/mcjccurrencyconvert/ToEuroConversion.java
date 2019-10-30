package mcjccurrencyconvert;

import java.math.BigDecimal;

public class ToEuroConversion implements Conversion {
	public BigDecimal convert(BigDecimal amount, BigDecimal conversionRate) {
		return amount.divide(conversionRate);
	}
}
