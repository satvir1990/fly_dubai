package org.page.factory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.test.util.Pnr_Locators;

public class PnrGenerationFactory {
	WebDriver driver = null;

	public PnrGenerationFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = Pnr_Locators.Search_Xpath)
	WebElement search;

	@FindBy(className = Pnr_Locators.Accept_All_Class)
	WebElement cookies;

	@FindBy(className = Pnr_Locators.Calendar_Windget_Inner_Class)
	WebElement calendarWidget;

	@FindBy(className = Pnr_Locators.Calendar_Days_Class)
	List<WebElement> calendarDays;

	@FindBy(id = Pnr_Locators.Origin_Id)
	WebElement origin;

	@FindBy(id = Pnr_Locators.Destination_Id)
	WebElement destination;

	@FindBy(className = Pnr_Locators.Flight_List_Container_Class)
	List<WebElement> flightListContainer;

	@FindBy(id = Pnr_Locators.First_Name_Id)
	WebElement firstName;

	@FindBy(id = Pnr_Locators.Last_Name_Id)
	WebElement lastName;

	@FindBy(id = Pnr_Locators.Email_Address_Id)
	WebElement emailAddress;

	@FindBy(xpath = Pnr_Locators.Gender_Male_Xpath)
	WebElement genderMale;

	@FindBy(className = Pnr_Locators.Country_Code_Drop_Down_Class)
	WebElement countryCodeDropdown;

	@FindBy(id = Pnr_Locators.Mobile_Number_Id)
	WebElement mobileNumber;

	@FindBy(className = Pnr_Locators.Next_Passenger_Class)
	WebElement nextPassenger;

	@FindBy(className = Pnr_Locators.Review_Page_Button_Class)
	WebElement reviewPagebutton;

	@FindBy(xpath = Pnr_Locators.Pay_Later_XPATH)
	WebElement payLater;
	
	@FindBy(className = "paylaterBtn")
	WebElement payLaterBtn;

	@FindBy(xpath = Pnr_Locators.Pay_Later_Continue_XPATH)
	WebElement payLaterContinue;

	public WebElement getSearchElement() {
		return this.search;
	}

	public WebElement getCookies() {
		return this.cookies;
	}

	public WebElement getOrigin() {
		return this.origin;
	}

	public WebElement getDestination() {
		return this.destination;
	}

	public void enterOrigin(String origin) {
		this.origin.sendKeys(origin);
	}

	public void enterDestination(String destination) {
		this.destination.sendKeys(destination);
	}

	public WebElement getFirstName() {
		return this.firstName;
	}

	public WebElement getLastName() {
		return this.lastName;
	}

	public WebElement getEmailAddress() {
		return this.emailAddress;
	}

	public WebElement getGenderMale() {
		return this.genderMale;
	}

	public WebElement getCountryCodeDropdown() {
		return this.countryCodeDropdown;
	}

	public WebElement getmobileNumber() {
		return this.mobileNumber;
	}

	public WebElement getNextPassenger() {
		return this.nextPassenger;
	}

	public WebElement getReviewPagebutton() {
		return this.reviewPagebutton;
	}

	public WebElement getCalendarWidget() {
		return this.calendarWidget;
	}

	public List<WebElement> getFlightListContainer() {
		return this.flightListContainer;
	}

	public List<WebElement> getCalendarDays() {
		return this.calendarDays;
	}
	
	public WebElement getPayLater() {
		return this.payLater;
	}

	public WebElement getPayLaterContinue() {
		return this.payLaterContinue;
	}

	public WebElement getBayLaterBtn() {
		return this.payLaterBtn;
	}


}
