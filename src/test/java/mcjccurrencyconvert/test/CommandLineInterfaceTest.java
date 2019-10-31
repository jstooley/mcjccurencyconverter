package mcjccurrencyconvert.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mcjccurrencyconvert.CommandLineInterface;

public class CommandLineInterfaceTest {
	
	CommandLineInterface classUnderTest;
	
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
	public void testCommandLineInterfaceGetsAndStoresAmount() {
		//arrange
//		Scanner scan = mock(Scanner.class);
		Scanner scan = new Scanner();
		BigDecimal expected = scan.nextBigDecimal();
		scan.close();
		
		//act
		BigDecimal actual = classUnderTest.getAmount();
		
		//assure
		assertEquals(expected,actual);
	}
	
	@Test
	public void testCommandLineInterfaceGetsAndStoresCurrencyFrom() {
		//arrange
		String input = "USD";
		String input2 = "Euro";
		BigDecimal num = new BigDecimal(0);
		BigDecimal expected = new BigDecimal(0);
		
		//act
		String actual = classUnderTest.getCurrencyFrom();
		
		//assure
		assertEquals(expected,actual);
	}
	
	@Test
	public void testCommandLineInterfaceGetsAndStoresCurrencyTo() {
		//arrange
		String input = "USD";
		String input2 = "Euro";
		BigDecimal num = new BigDecimal(0);
		BigDecimal expected = new BigDecimal(0);
		
		//act
		String actual = classUnderTest.getCurrencyTo();
		
		//assure
		assertEquals(expected,actual);
	}

}
