package mcjccurrencyconvert.test;

import mcjccurrencyconvert.UserInput;
import org.junit.*;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertTrue;

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
		String expected = "10000";
		assertTrue(expected.compareTo((classUnderTest.getInput(new ByteArrayInputStream("10000".getBytes()))).get()) == 0);
	}
	
}
