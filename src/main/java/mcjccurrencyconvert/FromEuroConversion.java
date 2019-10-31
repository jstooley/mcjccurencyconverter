package mcjccurrencyconvert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FromEuroConversion implements Conversion {

	public BigDecimal convert(BigDecimal amount, BigDecimal conversionRate) {
		return amount.multiply(conversionRate).setScale(2,RoundingMode.HALF_UP);
	}

}
