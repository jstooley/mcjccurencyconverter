package mcjccurrencyconvert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ToEuroConversion implements Conversion {
	@Override
	public BigDecimal convert(BigDecimal amount, BigDecimal conversionRate) {
		return amount.divide(conversionRate,2,RoundingMode.HALF_UP);
	}
}
