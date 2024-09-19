package org.test.glue.definition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.test.util.BaseClass;
import org.test.util.GenericConstants;
import org.test.util.Utility;


public class Hooks extends BaseClass {
	
	@Before
	public void init() {
		driver = Utility.launchDriver(GenericConstants.CHROME_BROWSER);
		//wait = new WebDriverWait(driver, Duration.ofSeconds(GenericConstants.TWENTY_SECONDS));
		wait = new FluentWait<>(driver)
				  .withTimeout(Duration.ofSeconds(GenericConstants.TWENTY_SECONDS))
				  .pollingEvery(Duration.ofMillis(500));
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
	}

	@After(order = 1)
	public void takeScraenshotOnFailure(Scenario scenario) {
		if (scenario.isFailed()) {
		

			TakesScreenshot ts = (TakesScreenshot) driver;

			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(src, "image/png", "screenshot");
		}

	}

	@After(order = 0)
	public void tearDown() {
		
		//driver.close();  // only current browser
		
		//driver.quit(); // all the browser tabs and session

	}
}