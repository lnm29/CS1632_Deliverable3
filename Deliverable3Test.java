import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.concurrent.TimeUnit;


//HibersenseTest is testing the way that our website 
//https://cs1632ex.herokuapp.com/ interacts with a user.


public class Deliverable3Test {
	
	static WebDriver driver = new HtmlUnitDriver();
	
	//Starts at the home page for each test
	@Before
	public void setUp() throws Exception {
		driver.get("https://cs1632ex.herokuapp.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//Since I will be set up at the main page,
	// When I view the first title, the word
	// "Welcome" is there
	@Test
	public void testShowsCorrectTitle1() {
		String title = driver.getTitle();
		assertTrue(title.contains("Welcome"));
	}
	
	//Since I will be set up at the main page,
	// When I view the second title, the word
	// "Quality" is there
	@Test
	public void testShowsCorrectTitle2() {
		String title = driver.getTitle();
		assertTrue(title.contains("Quality"));
	}

	// Given that I am on the main page
	// When I view the header
	// Then I see that it contains "CS1632 D3 Home", "Fibonacci", "Factorial" 
	//"Hello", and "Cathedral Pics" links.
	@Test
	public void testHasCorrectHeaderLinks() {
		
		// Check for the home page, factorial, fibonacci, hello, cathedral pics links - if any of
		// these is not found, fail the test
		
		try {
			driver.findElement(By.linkText("CS1632 D3 Home"));
			driver.findElement(By.linkText("Factorial"));
			driver.findElement(By.linkText("Fibonacci"));
			driver.findElement(By.linkText("Hello"));
			driver.findElement(By.linkText("Cathedral Pics"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that I am on the main page
	// When I click on the "Factorial" link
	// Then I should be redirected to the "Factorial" page
	@Test
	public void testSeeFactorialLinks() {
		
		// find the "Factorial" link and click on it
		// The page you go to should include "calculate factorial"
		// in the title
		
		driver.findElement(By.linkText("Factorial")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("calculate factorial"));
	}
	
	// Given that I am on the main page
	// When I click on the "Fibonacci" link
	// Then I should be redirected to the "Fibonacci" page
	@Test
	public void testSeeFibonacciLinks() {
		
		// find the "new" link and click on it
		// The page you go to should include "newest submissions"
		// in the title
		
		driver.findElement(By.linkText("Fibonacci")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("calculate Fibonacci"));
	}
	
	// Given that I am on the main page
	// When I click on the "Hello" link
	// Then I should be redirected to the "Hello" page
	@Test
	public void testSeeHelloLinks() {
		
		// find the "Hello" link and click on it
		// The page you go to should include "Hello"
		// in the title
		
		driver.findElement(By.linkText("Hello")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("Hello CS1632"));
	}
	
	// Given that I am on the main page
	// When I click on the "new" link
	// Then I should be redirected to the "new" page
	@Test
	public void testSeeCathyLinks() {
		
		// find the "cathy" link and click on it
		// The page you go to should include "Cathedral Pictures"
		// in the title
		
		driver.findElement(By.linkText("Cathedral Pics")).click();
		String newPageTitle = driver.getTitle();
		assertTrue(newPageTitle.contains("Cathedral Pictures"));
	}
	
	//Once the user is on the factorial link,
	//they may type any number from 1 to 100 and get the factorial
	//for these numbers.
	@Test
	public void testFactorialLinkPass() {
		
		// Go to the the Factorial Link and click it
		driver.findElement(By.linkText("Factorial")).click();
		
		//Click the textbox 
		WebElement inputNum = driver.findElement(By.cssSelector("input[type=factorial]"));
		inputNum.sendKeys("5");
		WebElement submitButton = inputNum.findElement(By.className("btn"));
		submitButton.click();
		
		// Check that the calculation is right!
		
		try {
			String currPageTitle = driver.getTitle();
			assertTrue(currPageTitle.contains("Factorial of 5 is 120!"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Once the user is on the factorial link,
	//they may type any number less than 1 and get the factorial
	//for these numbers, which should be -1
	@Test
	public void testFactorialLinkInvalid() {
		
		// Go to the the Factorial Link and click it
		driver.findElement(By.linkText("Factorial")).click();
		
		//Click the textbox 
		WebElement inputNum = driver.findElement(By.cssSelector("input[type=factorial]"));
		inputNum.sendKeys("-100");
		WebElement submitButton = inputNum.findElement(By.className("btn"));
		submitButton.click();
		
		// Check that the calculation is right!
		
		try {
			String currPageTitle = driver.getTitle();
			assertTrue(currPageTitle.contains("Factorial of -100 is 1!"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Once the user is on the fibonacci link,
	//they may type any number from 1 to 100 and get the fibonacci
	//for these numbers.
	//THIS SHOULD FAIL (this tests the edge case 100).
	@Test
	public void testFibonacciLinkFail() {
		
		// Look for the submit button (in the login div) and click
		// to attempt to login 
		
		WebElement inputNum = driver.findElement(By.cssSelector("input[type=fibonacci]"));
		inputNum.sendKeys("100");
		WebElement submitButton = inputNum.findElement(By.className("btn"));
		submitButton.click();
		
		// Check that calculation is right!
		
		try {
			String currPageTitle = driver.getTitle();
			assertTrue(currPageTitle.contains("Fibonacci of 100 is 1!"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	//Once the user is on the fibonacci link,
	//they may type any number from 1 to 100 and get the fibonacci
	//for these numbers.
	//THIS SHOULD PASS (valid case).
	@Test
	public void testFibonacciLinkPass() {
		
		// Look for the submit button (in the login div) and click
		// to attempt to login 
		
		WebElement inputNum = driver.findElement(By.cssSelector("input[type=fibonacci]"));
		inputNum.sendKeys("10");
		WebElement submitButton = inputNum.findElement(By.className("btn"));
		submitButton.click();
		
		// Check that calculation is right!
		
		try {
			String currPageTitle = driver.getTitle();
			assertTrue(currPageTitle.contains("Fibonacci of 10 is 89!"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	/* // Given that I am on the main page
	// When I click on the "Hello" link
	// Then I should be redirected to the "Hello" page
	// Once the user is there, they could type the trailing values
	// that fail specifically.
	// 	THIS SHOULD FAIL (corner case).
	@Test
	public void testHelloLinksFail() {
		
		// find the "Hello" link and click on it
		// The page you go to should include "Hello"
		// in the title
		
		driver.findElement(By.linkText("Hello")).click();
		
		input.sendKeys("/]");
		driver.findElement(By.id("elementid")).sendKeys(Keys.ENTER);
	}
	
	// Given that I am on the main page
	// When I click on the "Hello" link
	// Then I should be redirected to the "Hello" page
	// Once the user is there, they could type the trailing values
	// that fail specifically.
	// Should pass! (Valid trailing value)
	@Test
	public void testHelloLinksPass() {
		
		// find the "Hello" link and click on it
		// The page you go to should include "Hello"
		// in the title
		
		driver.findElement(By.linkText("Hello")).click();
		
		input.sendKeys("/Leela");
		driver.findElement(By.id("elementid")).sendKeys(Keys.ENTER);
	}*/
	
	//This test checks to see if the cathy page is numbering
	//of the pictures. This test checks the third picture.
	//The test should pass!
	@Test
	public void testNumberedPictures1()
	{
		// find the "cathy" link and click on it
		// The page you go to should include "Cathedral Pictures"
		// in the title
		
		driver.findElement(By.linkText("Cathedral Pics")).click();
		String numberText = driver.findElement(By.partialLinkText("1.")).getText();
		//assertTrue(numberText.contains("1."));
	}
	
	//This test checks to see if the cathy page is numbereing
	//the pictures. This test checks the second picture's number.
	//The test should pass!
	@Test
	public void testNumberedPictures2()
	{
		// find the "cathy" link and click on it
		// The page you go to should include "Cathedral Pictures"
		// in the title
		
		driver.findElement(By.linkText("Cathedral Pics")).click();
		String numberText = driver.findElement(By.partialLinkText("2.")).getText();
		//assertTrue(numberText.contains("2."));
	}
	
	//This test checks to see if the cathy page is numbering
	//the pictures. This test checks the third picture's number.
	//The test should pass!
	@Test
	public void testNumberedPictures3()
	{
		// find the "cathy" link and click on it
		// The page you go to should include "Cathedral Pictures"
		// in the title
		
		driver.findElement(By.linkText("Cathedral Pics")).click();
		String numberText = driver.findElement(By.partialLinkText("3.")).getText();
		//assertTrue(numberText.contains("3."));
	}
	
	/*@Test
	public void ()
	{
		
	}
	
	@Test
	public void ()
	{
		
	}
	
	@Test
	public void ()
	{
		
	}
	
	@Test
	public void ()
	{
		
	}*/
}