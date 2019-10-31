package mcjccurrencyconvert.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mcjccurrencyconvert.CommandLineInterface;
import mcjccurrencyconvert.UserInput;

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
		// arrange
		UserInput mockUserInput = mock(UserInput.class);
		classUnderTest = new CommandLineInterface(mockUserInput);
		InputStream inputStream = new ByteArrayInputStream("10000".getBytes());

		// act
		classUnderTest.getAmount();

		// assure
		verify(mockUserInput).getInput(inputStream);
	}

	@Test
	public void testCommandLineInterfaceGetsAndStoresCurrencyFrom() {
		// arrange
		String input = "USD";
		String input2 = "Euro";
		BigDecimal num = new BigDecimal(0);
		BigDecimal expected = new BigDecimal(0);

		// act
		String actual = classUnderTest.getCurrencyFrom();

		// assure
		assertEquals(expected, actual);
	}

	@Test
	public void testCommandLineInterfaceGetsAndStoresCurrencyTo() {
		// arrange
		String input = "USD";
		String input2 = "Euro";
		BigDecimal num = new BigDecimal(0);
		BigDecimal expected = new BigDecimal(0);

		// act
		String actual = classUnderTest.getCurrencyTo();

		// assure
		assertEquals(expected, actual);
	}

}
