package mcjccurrencyconvert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ToEuroConversion implements Conversion {
	public BigDecimal convert(BigDecimal amount, BigDecimal conversionRate) {
		return amount.divide(conversionRate, RoundingMode.CEILING).setScale(2,RoundingMode.CEILING);
	}
}
