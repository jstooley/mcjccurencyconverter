package mcjccurrencyconvert.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mcjccurrencyconvert.CommandLineInterface;
import mcjccurrencyconvert.ConversionRates;
import mcjccurrencyconvert.UserInput;

public class CommandLineInterfaceTest {

	CommandLineInterface classUnderTest;
	String simulateUser;
	ConversionRates rates;
	Optional<InputStream> input;
	Map<String, BigDecimal> checkRates;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		rates = new ConversionRates();
		input = rates.makeXMLDocument("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		rates.parseXMLDocument(input.get());
		rates.readConversionRates();
		Map<String, BigDecimal> checkRates = rates.getConversionRates();
		classUnderTest = new CommandLineInterface(checkRates);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCommandLineInterfaceGetsAndStoresAmount() {
		// arrange
		simulateUser = "150";
		BigDecimal expected = new BigDecimal("150");
		// act
		Optional<BigDecimal> actual = classUnderTest.getAmount(new ByteArrayInputStream(simulateUser.getBytes()));
		// assert
		assertEquals(expected, actual.get());
	}

	@Test
	public void testCommandLineInterfaceGetsAndStoresCurrencyFrom() {
		// arrange
		simulateUser = "USD";
		String expected = "USD";
		// act
		String actual = classUnderTest.getCurrencyFrom(new ByteArrayInputStream(simulateUser.getBytes())).get();
		// assert
		assertEquals(expected, actual);
	}

	@Test
	public void testCommandLineInterfaceGetsAndStoresCurrencyTo() {
		// arrange
		simulateUser = "USD";
		String expected = "USD";
		// act
		String actual = classUnderTest.getCurrencyTo(new ByteArrayInputStream(simulateUser.getBytes())).get();
		// assert
		assertEquals(expected, actual);
	}

}
