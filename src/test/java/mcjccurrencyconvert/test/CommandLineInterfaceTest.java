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
	String simulateUser;
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
		simulateUser = "150";
		classUnderTest = new CommandLineInterface();
		BigDecimal expected = new BigDecimal("150");
		// act
		BigDecimal actual = classUnderTest.getAmount(new ByteArrayInputStream(simulateUser.getBytes()));
		assertEquals(expected, actual);
		// assure
		
	}

	@Test
	public void testCommandLineInterfaceGetsAndStoresCurrencyFrom() {
		// arrange
		simulateUser = "USD";
		classUnderTest = new CommandLineInterface();
		String expected = "USD";
		String actual = classUnderTest.getCurrencyFrom(new ByteArrayInputStream(simulateUser.getBytes()));
	// assure
		assertEquals(expected, actual);
	}

	@Test
	public void testCommandLineInterfaceGetsAndStoresCurrencyTo() {
		// arrange
		simulateUser = "USD";
		classUnderTest = new CommandLineInterface();
		String expected = "USD";
		// act
		String actual = classUnderTest.getCurrencyTo(new ByteArrayInputStream(simulateUser.getBytes()));
		// assure
		assertEquals(expected, actual);
	}

}
