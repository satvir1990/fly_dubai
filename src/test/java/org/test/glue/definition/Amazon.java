package org.test.glue.definition;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.test.util.BaseClass;

import io.cucumber.java.en.*;

public class Amazon extends BaseClass {
	
	@Given("Amazon welcome page.")
	public void amazon_welcome_page() {
	    driver.navigate().to("https://www.amazon.in");
	}

	@Then("Enter seach {string}")
	public void enter_seach(String item) {
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(item);
	    
	}

	@And("select from dropdown")
	public void select_from_dropdown() {
		int count = 0;
		try {  
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("s-heavy")));
	   
	  for(WebElement ele : driver.findElements(By.className("s-heavy"))){
		  System.out.println(ele.getText());
		  if(ele.getText().equals("15 pro")) {
			 ele.click();
			 break;
		  }
	  }
	}catch (StaleElementReferenceException e) {
		if(count < 3) {
			System.out.println(count);
		select_from_dropdown();
		count++;
		}
	}
		
	}

	

	@Then("Verify search items on next page")
	public void verify_search_items_on_next_page() throws InterruptedException {
		
		if(driver.getPageSource().contains("Apple iPhone 15 Pro (256 GB) - Black Titanium")) {
			System.out.println("verified");
			
			 Actions action = new Actions(driver);


			 action.moveToElement(driver.findElement(By.linkText("Apple iPhone 15 Pro (256 GB) - Black Titanium"))).click().perform();
//			driver.findElement(By.linkText("Apple iPhone 15 Pro (256 GB) - Black Titanium")).click();
			String parent=driver.getWindowHandle();
			Set<String> webDriver =(Set<String>) driver.getWindowHandles();
			webDriver.forEach(data -> {
				if(!data.equals(parent)) {
					System.out.println(data);
					driver.switchTo().window(data);
				}
			});
			
			Thread.sleep(5000);
		//	 driver.close();
			
		//	driver.switchTo().window(parent);
		}
	 
	}


}
