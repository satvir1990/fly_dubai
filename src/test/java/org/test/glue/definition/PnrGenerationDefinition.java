package org.test.glue.definition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.page.factory.PnrGenerationFactory;
import org.test.util.BaseClass;
import org.test.util.GenericConstants;
import org.test.util.Pnr_Locators;
import org.test.util.Utility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PnrGenerationDefinition extends BaseClass {

	
	PnrGenerationFactory element = null;
	Map<String, String> dataMap = new HashMap<String, String>();
	
	

	@Given("Home page of fly dubai Book flight and route information to search route")
	public void home_page_of_fly_dubai_book_flight_and_route_information_to_search_route() {
		 System.out.format("Thread ID - %2d  feature file.\n",
			        Thread.currentThread().getId());
			driver.navigate().to(GenericConstants.HOME_URL);
			element = new PnrGenerationFactory(driver);
			wait.until(ExpectedConditions.elementToBeClickable(element.getCookies()));
			element.getCookies().click();

			wait.until(ExpectedConditions.elementToBeClickable(element.getOrigin()));

			element.enterOrigin(GenericConstants.ORIGIN);
			element.getOrigin().sendKeys(Keys.TAB);

			element.enterDestination(GenericConstants.DESTINATION);
			element.getDestination().sendKeys(Keys.TAB);

			wait.until(ExpectedConditions.elementToBeClickable(element.getCalendarWidget()));
			
			selectDate(8);
			
			selectDate(9);

			element.getSearchElement().click();

	}

	@Given("select departure flight tab and chose fare band")
	public void select_departure_flight_tab_and_chose_fare_band() {
		try {
			if (element.getCookies().isDisplayed()) {
				element.getCookies().click();
			}
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(By.cssSelector("div[class='avail-list-desk']") ,By.cssSelector("div[class ='flightlistItems']")));
			if(driver.findElement(By.cssSelector("div[class='avail-list-desk']")).findElements(By.cssSelector("div[class ='flightlistItems']")).get(0).isEnabled()) {
				 driver.findElement(By.cssSelector("div[class='avail-list-desk']")).findElements(By.cssSelector("div[class ='flightlistItems']")).get(0).click();
			

			// Filling input parameters for future validation
			WebElement departureFlightElement = driver.findElement(By.cssSelector("div[class='avail-list-desk']")).findElements(By.cssSelector("div[class ='flightlistItems']")).get(0);

			List<WebElement> flightData = departureFlightElement.findElements(By.className(Pnr_Locators.Station_Code_And_Time_Class));
			for (int i = 0; i < flightData.size(); i++) {
				WebElement fData = flightData.get(i);
				if (i == 0) {
					dataMap.put("dep_departureTime", fData.findElement(By.id(Pnr_Locators.DivDateId)).getText());
					dataMap.put("dep_departureLocation", fData.findElement(By.id(Pnr_Locators.Label_Station_Code_Id)).getText());
				} else if (i == 1) {
					dataMap.put("dep_arrivalTime", fData.findElement(By.id(Pnr_Locators.DivDateId)).getText());
					dataMap.put("dep_arrivalLocation", fData.findElement(By.id(Pnr_Locators.Label_Station_Code_Id)).getText());
				}
			}

			dataMap.put("departureDate",
					driver.findElements(By.className(Pnr_Locators.Trip_Header_Date_Class)).get(0).findElement(By.id(Pnr_Locators.DivDateId)).getText());
			System.out.println(dataMap);
			
			wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(departureFlightElement ,By.className(Pnr_Locators.Flight_Aircraft_Type_Class)));
			dataMap.put("dep_aircraft_Name", departureFlightElement.findElement(By.className(Pnr_Locators.Flight_Aircraft_Type_Class)).getText());

			wait.until(ExpectedConditions.elementToBeClickable(By.className(Pnr_Locators.Fare_Brand_Btn_Class)));
			List<WebElement> fareOptions = driver.findElements(By.className(Pnr_Locators.Fare_Brand_Btn_Class));

			for (int i = 1; i < fareOptions.size();) {
				WebElement webElement = fareOptions.get(i);
				System.out.println(webElement.getAttribute("class"));
				Thread.sleep(4000);
				js.executeScript("arguments[0].scrollIntoView();", webElement);
				webElement.click();
				break;
			}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Given("select return flight and chose fare band")
	public void select_return_flight_and_chose_fare_band() {
		try {
			//Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(Pnr_Locators.Arrival_Flight_Class)));
			WebElement availableDepartureFlight = driver.findElement(By.cssSelector(Pnr_Locators.Arrival_Flight_Class));
			js.executeScript("arguments[0].scrollIntoView();", availableDepartureFlight);
			availableDepartureFlight.click();

			List<WebElement> flightData = availableDepartureFlight.findElements(By.className(Pnr_Locators.Station_Code_And_Time_Class));
			for (int i = 0; i < flightData.size(); i++) {
				WebElement fData = flightData.get(i);
				if (i == 0) {
					dataMap.put("return_departureTime", fData.findElement(By.id(Pnr_Locators.DivDateId)).getText());
					dataMap.put("return_departureLocation", fData.findElement(By.id(Pnr_Locators.Label_Station_Code_Id)).getText());
				} else if (i == 1) {
					dataMap.put("return_arrivalTime", fData.findElement(By.id(Pnr_Locators.DivDateId)).getText());
					dataMap.put("return_arrivalLocation", fData.findElement(By.id(Pnr_Locators.Label_Station_Code_Id)).getText());
				}
			}
			dataMap.put("returnDate",
					driver.findElements(By.className(Pnr_Locators.Trip_Header_Date_Class)).get(1).findElement(By.id(Pnr_Locators.DivDateId)).getText());

			dataMap.put("return_aircraft_Name", availableDepartureFlight.findElement(By.className(Pnr_Locators.Aircraft_Title_Class))
					.findElement(By.className(Pnr_Locators.Flight_Aircraft_Type_Class)).getText());

			Thread.sleep(4000);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Pnr_Locators.Fare_Band_Css)));
			WebElement fareBand = driver.findElement(By.cssSelector(Pnr_Locators.Fare_Band_Css));
			js.executeScript("arguments[0].scrollIntoView();", fareBand);
			fareBand.click();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@When("baggae information displayed select Extra baggage")
	public void baggae_information_displayed_select_extra_baggage() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pnr_Locators.Extra_Luggage_XPATH)));
			WebElement extrabaggage = driver.findElement(By.xpath(Pnr_Locators.Extra_Luggage_XPATH));
			js.executeScript("arguments[0].scrollIntoView();", extrabaggage);
			extrabaggage.click();

			Thread.sleep(4000);
		//	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Pnr_Locators.Extra_Luggage_XPATH)));
			WebElement extrabaggage1 = driver.findElement(By.xpath(Pnr_Locators.Extra_Luggage_XPATH));
			js.executeScript("arguments[0].scrollIntoView();", extrabaggage1);
			extrabaggage1.click();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@When("click continue to passenger details")
	public void click_continue_to_passenger_details() {
		// Write code here that turns the phrase above into concrete actions
		try {
			//Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Pnr_Locators.Navigate_To_Passenger_Details_Class)));
			WebElement passengerDetails = driver.findElement(By.className(Pnr_Locators.Navigate_To_Passenger_Details_Class));
			js.executeScript("arguments[0].scrollIntoView();", passengerDetails);
			passengerDetails.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("passenger details page display fill passgenger details")
	public void passenger_details_page_display_fill_passgenger_details() {
		System.out.println("1");
			//Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pnr_Locators.First_Name_Id)));
			element.getFirstName().sendKeys("Satvir");
			element.getLastName().sendKeys("Singh");
			element.getEmailAddress().sendKeys("me.satvirsingh@gmail.com");
			element.getGenderMale().click();
			element.getCountryCodeDropdown().click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pnr_Locators.Country_Value_Code_XPATH)));
			List<WebElement> countryCodeList = driver.findElements(By.xpath(Pnr_Locators.Country_Value_Code_XPATH));
			for (WebElement countryElement : countryCodeList) {
				if (countryElement.getText().equalsIgnoreCase("India")) {
					countryElement.click();
					break;
				}
			}

			dataMap.put("passenger_count", driver.findElement(By.className(Pnr_Locators.Passenger_Count_Class)).getText());
			element.getmobileNumber().sendKeys("9540238887");

			driver.findElement(By.id(Pnr_Locators.Flight_Details_Accordion_Id)).click();
			int count = 0;
			for (WebElement fareDetails : driver.findElement(By.className(Pnr_Locators.Desktop_Trip_Summary_Cart_Class))
					.findElements(By.className(Pnr_Locators.Depart_Journey_Class))) {
				for (int amountIndex = 0; amountIndex < fareDetails.findElements(By.id(Pnr_Locators.Label_Amount_Id))
						.size(); amountIndex++) {
					WebElement fareAmount = fareDetails.findElements(By.id(Pnr_Locators.Label_Amount_Id)).get(amountIndex);
					if (count == 0) {
						if (amountIndex == 0) {
							dataMap.put("departure_fare", fareAmount.getText());
						} else if (amountIndex == 1) {
							dataMap.put("departure_tax", fareAmount.getText());
						} else if (amountIndex == 2) {
							dataMap.put("departure_total", fareAmount.getText());
						}
					} else if (count == 1) {
						if (amountIndex == 0) {
							dataMap.put("return_fare", fareAmount.getText());
						} else if (amountIndex == 1) {
							dataMap.put("return_tax", fareAmount.getText());
						} else if (amountIndex == 2) {
							dataMap.put("return_total", fareAmount.getText());
						}
					}
				}
				count++;
			}

			dataMap.put("total_fare", driver.findElement(By.id("fareLbl")).findElement(By.id(Pnr_Locators.Label_Amount_Id)).getText());
			wait.until(ExpectedConditions.elementToBeClickable(By.className(Pnr_Locators.Next_Passenger_Class)));
			element.getNextPassenger().click();
	}

	@Then("Reveiw booking")
	public void reveiw_booking() {
		try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(Pnr_Locators.Flight_Info_CLass)));
		for (int flightIndex = 0; flightIndex < driver.findElements(By.className(Pnr_Locators.Flight_Info_CLass))
				.size(); flightIndex++) {
			if(flightIndex == 0) {
				Assert.assertEquals(dataMap.get("dep_aircraft_Name"), driver.findElements(By.className(Pnr_Locators.Flight_Info_CLass)).get(flightIndex).findElement(By.id(Pnr_Locators.Inter_Line_Msg_Id)).getText());
			}else if(flightIndex == 1) {
				Assert.assertEquals(dataMap.get("return_aircraft_Name"),  driver.findElements(By.className(Pnr_Locators.Flight_Info_CLass)).get(flightIndex).findElement(By.id(Pnr_Locators.Inter_Line_Msg_Id)).getText());
			}

			for (int scheduleIndex = 0; scheduleIndex < driver.findElements(By.className(Pnr_Locators.Flight_Info_CLass))
					.get(flightIndex).findElements(By.className(Pnr_Locators.Station_Code_And_Time_Class)).size(); scheduleIndex++) {
				WebElement fData = driver.findElements(By.className(Pnr_Locators.Flight_Info_CLass)).get(flightIndex)
						.findElements(By.className(Pnr_Locators.Station_Code_And_Time_Class)).get(scheduleIndex);
				if (flightIndex == 0) {
					if (scheduleIndex == 0) {
						Assert.assertEquals(dataMap.get("dep_departureTime"),
								fData.findElement(By.id(Pnr_Locators.DivDateId)).getText());
						
					} else if (scheduleIndex == 1) {
						Assert.assertEquals(dataMap.get("dep_arrivalTime"),
								fData.findElement(By.id(Pnr_Locators.DivDateId)).getText());
					
					}
				} else if (flightIndex == 1) {
					if (scheduleIndex == 0) {
						Assert.assertEquals(dataMap.get("return_departureTime"),
								fData.findElement(By.id(Pnr_Locators.DivDateId)).getText());
						
					} else if (scheduleIndex == 1) {	
						Assert.assertEquals(dataMap.get("return_arrivalTime"),
								fData.findElement(By.id(Pnr_Locators.DivDateId)).getText());
					
					}
				}

			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Pnr_Locators.Flight_Type_Label_Class)));
		if (driver.findElement(By.className(Pnr_Locators.Flight_Type_Label_Class)).getText().contains("Departing flight")) {
			driver.findElement(By.className(Pnr_Locators.Flight_Type_Label_Class)).click();
		}

		for (int fareIndex = 1; fareIndex < driver.findElement(By.className(Pnr_Locators.Fare_Breakdown_Summary_Class))
				.findElements(By.className(Pnr_Locators.Fare_Breakdown_Summary_Class)).size() - 1; fareIndex++) {
			WebElement webElement = driver.findElement(By.className(Pnr_Locators.Fare_Breakdown_Summary_Class))
			.findElements(By.className(Pnr_Locators.Fare_In_Breakdown_Class)).get(fareIndex);
			if (fareIndex == 1) {
				Assert.assertEquals(dataMap.get("departure_fare"), webElement.findElement(By.id(Pnr_Locators.Label_Amount_Id)).getText());
			} else if (fareIndex == 2) {
				Assert.assertEquals(dataMap.get("departure_tax"), webElement.findElement(By.id(Pnr_Locators.Label_Amount_Id)).getText());
			}
		}
		
		if (driver.findElement(By.className(Pnr_Locators.Tab_List_Class)).findElements(By.className(Pnr_Locators.Tab_Label_Content_Class)).get(1).findElement(By.className(Pnr_Locators.Flight_Type_Label_Class)).getText().contains("Returning flight")) {
			driver.findElement(By.className(Pnr_Locators.Tab_List_Class)).findElements(By.className(Pnr_Locators.Tab_Label_Content_Class)).get(1).findElement(By.className(Pnr_Locators.Flight_Type_Label_Class)).click();
		}
		Thread.sleep(2000);
		
		for (int fareIndex = 1; fareIndex < driver.findElement(By.className(Pnr_Locators.Fare_Breakdown_Summary_Class))
				.findElements(By.className(Pnr_Locators.Fare_In_Breakdown_Class)).size() - 1; fareIndex++) {
			WebElement webElement = driver.findElement(By.className(Pnr_Locators.Fare_Breakdown_Summary_Class))
					.findElements(By.className(Pnr_Locators.Fare_In_Breakdown_Class)).get(fareIndex);
			if (fareIndex == 1) {
				Assert.assertEquals(dataMap.get("return_fare"), webElement.findElement(By.id(Pnr_Locators.Label_Amount_Id)).getText());
			} else if (fareIndex == 2) {
				Assert.assertEquals(dataMap.get("return_tax"), webElement.findElement(By.id(Pnr_Locators.Label_Amount_Id)).getText());
			}
		}
		
		element.getReviewPagebutton().click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	@Then("Generate Pnr with Pay Later Option")
	public void generate_Pnr_With_Pay_Later_Option() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pnr_Locators.Pay_Later_XPATH)));
		element.getPayLater().click();
		
		
	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pnr_Locators.Pay_Later_Continue_XPATH)));
		js.executeScript("arguments[0].click();", element.getPayLaterContinue());
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Pnr_Locators.Fare_Modal_Class)));
		driver.findElement(By.className(Pnr_Locators.Fare_Modal_Class)).findElement(By.className(Pnr_Locators.Button_Orange_Class)).click();
	}
	
	
	public void selectDate(int days) {
		try {
			Utility.selectdate(driver, Utility.getCurrentDayPlus(days), element.getCalendarDays());
		} catch (StaleElementReferenceException e) {
			selectDate(days);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	 
	
	
	  
	  @Given("Say Hello to the world")
	  public void helloWorld() {
		  System.out.println("hello"); 
	  }
	 
	

}
