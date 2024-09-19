package org.test.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	
	public static WebDriver driver = null;
	public static JavascriptExecutor js = null;
	public static Wait<WebDriver> wait  = null;

}
