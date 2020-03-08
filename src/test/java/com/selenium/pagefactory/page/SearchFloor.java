package com.selenium.pagefactory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchFloor extends PageObject {

	@FindBy(css = "input[type=search]")
	WebElement searchBox;

	@FindBy(css = "img[alt='Search']")
	WebElement searchIcon;

	@FindBy(className = "result-info")
	WebElement resultInfo;

	@FindBy(xpath = "//button[normalize-space(@class) = 'btn btn-default long-title ng-binding ng-scope active' and contains(text(),'5')]")
	WebElement floorFive;

	public SearchFloor(WebDriver driver) {
		super(driver);
	}

	/**
	 * 
	 * @param floorNumber The floor to be searched
	 * @return The floor number that is reached
	 */
	public String performSearch(String floorNumber) {
		this.searchBox.sendKeys(floorNumber);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(searchIcon));
		this.searchIcon.click();
		this.resultInfo.click();

		return getWebElementText(floorNumber);
	}

	private String getWebElementText(String floorNumber) {
		String webElementText;
		switch (floorNumber) {
		case "IV":
			webElementText = floorFive.getText();
			break;

		default:
			webElementText = "Not supported for the case: " + floorNumber;
			break;
		}
		return webElementText;
	}
}
