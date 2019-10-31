package mcjccurrencyconvert.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mcjccurrencyconvert.FromEuroConversion;

public class FromEuroConversionTest {
	FromEuroConversion classUnderTest;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFromEuroConvertsCorrectly() {
		classUnderTest = new FromEuroConversion();
		BigDecimal amount = new BigDecimal(1000);
		BigDecimal rate = new BigDecimal(1.110234);
		BigDecimal expected = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
		assertTrue(expected.equals(classUnderTest.convert(amount,rate)));
		 
	}

}
