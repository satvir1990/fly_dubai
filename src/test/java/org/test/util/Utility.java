package org.test.util;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class Utility {

	static WebDriver driver = null;

	public static WebDriver launchDriver(String browserType) {
		if (browserType.equalsIgnoreCase(GenericConstants.CHROME_BROWSER)) {
			System.getProperty(GenericConstants.CHROME_DRIVER, GenericConstants.CHROME_DRIVER_PATH);
			driver = new ChromeDriver();
		}
		return driver;

	}

	public static String getCurrentDayPlus(int days) {
		Calendar cal = Calendar.getInstance();
		if (days > 0) {
			cal.add(Calendar.DATE, days);
		}
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
	}

	public static void selectdate(WebDriver driver, String days, List<WebElement> dateList)
			throws InterruptedException {
		for (int i = 0; i < dateList.size(); i++) {
			WebElement webElement = dateList.get(i);
			if (webElement.getText().equalsIgnoreCase(days) && webElement.getAttribute("class").contains("is-available")) {
				Thread.sleep(3000);
				webElement.click();
				break;
			}
		}
	}

	public static WebElement fluentWait(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(org.openqa.selenium.NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});

		return element;
	};

	public static List<WebElement> fluentWaitForListLocators(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(org.openqa.selenium.NoSuchElementException.class);

		List<WebElement> element = wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(locator);
			}
		});

		return element;
	};

}
