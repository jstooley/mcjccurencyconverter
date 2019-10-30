package mcjccurrencyconvert;

import java.math.BigDecimal;

public interface Conversion {
	BigDecimal convert(BigDecimal amount,BigDecimal conversionRate);
}
