package mcjccurrencyconvert.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mcjccurrencyconvert.ToEuroConversion;

public class ToEuroConversionTest {
	ToEuroConversion classUnderTest;
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
	public void testToEuroConvertsCorrectly() {
		classUnderTest = new ToEuroConversion();
		BigDecimal amount = new BigDecimal(1000);
		BigDecimal rate = new BigDecimal(1.1102);
		BigDecimal expected = amount.divide(rate, RoundingMode.).setScale(2);
		System.out.println(expected);
		assertTrue(expected.equals(classUnderTest.convert(amount,rate)));
		 
	}

}
