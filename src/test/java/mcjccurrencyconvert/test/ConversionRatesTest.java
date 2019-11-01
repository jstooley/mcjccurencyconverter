package mcjccurrencyconvert.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mcjccurrencyconvert.ConversionRates;

public class ConversionRatesTest {
	
	ConversionRates classUnderTest;
	
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
	public void test_ConversionRates_MakeConversionRates_ReturnsNullIfNoURLProvided() {
		classUnderTest = new ConversionRates();
		Optional<InputStream> expected = Optional.empty();
		Optional<InputStream> actual = classUnderTest.makeXMLDocument("");
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_ConversionRates_MakeConversionRates_InvalidInput() {
		classUnderTest = new ConversionRates();
		Optional<InputStream> input = classUnderTest.makeXMLDocument("invalid");
	}
	
	@Test 
	public void test_ConversionRates_MakeXMLDocument_ReturnsInputStream() {
		classUnderTest = new ConversionRates();
		Optional<InputStream> outcome = classUnderTest.makeXMLDocument("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		assertTrue(outcome.isPresent());
	}
	
	@Test 
	public void test_ConversionRates_MakeXMLDocument_ReturnsFileInputStream() {
		classUnderTest = new ConversionRates();
		Optional<InputStream> outcome = classUnderTest.makeXMLDocument("src/test/resources/testRates.xml");
		assertTrue(outcome.get() instanceof FileInputStream);
	}
	
	@Test 
	public void test_ConversionRates_MakeXMLDocument_ReturnsURLInputStream() {
		classUnderTest = new ConversionRates();
		Optional<InputStream> outcome = classUnderTest.makeXMLDocument("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		InputStream expected = null;
		assertTrue(outcome.get() instanceof InputStream);
	}	
	
	@Test 
	public void test_ConversionRates_ParseXMLDocument_ReturnsDocumentGivenInputStream() {
		classUnderTest = new ConversionRates();
		Optional<InputStream> input = classUnderTest.makeXMLDocument("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		Optional<Document> outcome = classUnderTest.parseXMLDocument(input.get());
		assertTrue(outcome.get() instanceof Document);
	}
	
	@Test
	public void test_ConversionRates_ReadConversionRates_CallsSetConversionRates() {
		classUnderTest = new ConversionRates();
		String key = "USD";
		BigDecimal value = new BigDecimal("1.1139");
		Map<String, BigDecimal> mockMap = mock(Map.class);
		Optional<InputStream> input = classUnderTest.makeXMLDocument("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		Optional<Document> outcome = classUnderTest.parseXMLDocument(input.get());
		classUnderTest.setConversionRates(mockMap);
		classUnderTest.readConversionRates();
		verify(mockMap, times(1)).put(key, value);
		
		
	}

	
}
