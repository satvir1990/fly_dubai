package org.test.glue.definition;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.test.util.BaseClass;

import io.cucumber.java.en.Given;

public class IplTableDefinition extends BaseClass{
	
	@Given("Home page of ipl and fetch data")
	public void fetchMaximumIplWinningStreak() throws InterruptedException {
		 System.out.format("Thread ID - %2d feature file.\n",
			        Thread.currentThread().getId());
			SortedMap<String , Integer> teamWinningMap = new TreeMap<String, Integer>();
			driver.navigate().to("https://www.iplt20.com/points-table/men/2023");
			wait.until(ExpectedConditions.elementToBeClickable(By.className("cookie__accept")));
			driver.findElement(By.className("cookie__accept")).click();
			List<WebElement> pointTable = driver.findElements(By.className("team0"));
			for(int i=0; i<pointTable.size(); i++) {
				String teamName = pointTable.get(i).findElement(By.className("ih-pt-cont")).getText();
				List<WebElement> winStreak =  pointTable.get(i).findElement(By.className("ih-pt-fb")).findElements(By.tagName("span"));
				teamWinningMap.put(teamName, 0);
					for(WebElement element : winStreak) {
						if(element.getText().equals("W")) {
						Integer count = teamWinningMap.get(teamName);
						teamWinningMap.put(teamName, count +1);
						}else {
							break;
						}
					}
			}
			int highestWining = 0;
			String teamName = "";
			for(Entry<String , Integer>entry : teamWinningMap.entrySet() ) {
				if(highestWining < entry.getValue()) {
					highestWining = entry.getValue();
					teamName = entry.getKey();
				}
			}
			System.out.println(teamWinningMap);
			System.out.println(teamName);
	}
	
	
	@Given("Step from {string} in {string} feature file")
    public void step(String scenario, String file) {
        System.out.format("Thread ID - %2d  feature file.\n",
        Thread.currentThread().getId(), scenario,file);
    }

}
