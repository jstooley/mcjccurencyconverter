package mcjccurrencyconvert.test;

import mcjccurrencyconvert.CommandLineInterface;
import mcjccurrencyconvert.ConversionRates;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CommandLineInterfaceTest {

	CommandLineInterface classUnderTest;
	String simulateUser;
	ConversionRates rates;
	InputStream input;
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
		rates.parseXMLDocument(input);
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
	public void testCommandLineInterfaceKnowsInvalidAmount() {
		// arrange
		simulateUser = "0";
		// act
		Optional<BigDecimal> actual = classUnderTest.getAmount(new ByteArrayInputStream(simulateUser.getBytes()));
		// assert
		assertFalse(actual.isPresent());
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
	public void testCommandLineInterfaceKnowsInvalidFromInput() {
		// arrange
		simulateUser = "BARF";
		// act
		Optional<String> actual = classUnderTest.getCurrencyFrom(new ByteArrayInputStream(simulateUser.getBytes()));
		// assert
		assertFalse(actual.isPresent());
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
	
	@Test
	public void testCommandLineInterfaceKnowsInvalidToInput() {
		// arrange
		simulateUser = "BARF";
		// act
		Optional<String> actual = classUnderTest.getCurrencyTo(new ByteArrayInputStream(simulateUser.getBytes()));
		// assert
		assertFalse(actual.isPresent());
	}


}
