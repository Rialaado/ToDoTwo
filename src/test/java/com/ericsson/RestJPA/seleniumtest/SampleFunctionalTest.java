/*package com.ericsson.RestJPA.seleniumtest;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;


// RIGHT CLICK SampleFunctionTest and run the test suite, or run each method individually.
public class SampleFunctionalTest {
	private FirefoxDriver driver;
	private String baseUrl;

	@Before
	   public void setUp() throws Exception {
	      driver = new FirefoxDriver();
	      baseUrl = "http://localhost:8080/Loki";
	      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	   }
	   
	   @Test
public void loginTest(){
		   //sets the URL
		   		driver.get(baseUrl);
		   	// waits for page to load
		   		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		   	// clicks the input area
		   		driver.findElement(By.id("userName")).click();
		   	// puts "user" in the input area
		   		driver.findElement(By.id("userName")).sendKeys("user");
		   	// clicks the input area
		   		driver.findElement(By.id("password")).click();
		   	// puts "pass" in the input area
		   		driver.findElement(By.id("password")).sendKeys("pass");
		   	// clicks the login button
		   		driver.findElement(By.id("loginSubmit")).click();	  
	   }
}
*/