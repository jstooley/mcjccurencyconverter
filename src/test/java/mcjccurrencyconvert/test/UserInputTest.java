package mcjccurrencyconvert.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mcjccurrencyconvert.UserInput;

public class UserInputTest {
	UserInput classUnderTest;
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
	public void testGetInputReturnsCorrectValue() {
		classUnderTest = new UserInput();
		InputStream inputStream = new ByteArrayInputStream("10000".getBytes());
		String expected = "10000";
		assertTrue(expected.compareTo(classUnderTest.getInput(inputStream)) == 0);
	}
	
}
