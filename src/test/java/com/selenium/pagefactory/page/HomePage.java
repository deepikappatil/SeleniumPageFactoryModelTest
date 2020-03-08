package com.selenium.pagefactory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends PageObject {

	@FindBy(xpath = "//button[normalize-space(@class) = 'btn btn-default long-title ng-binding ng-scope active']")
	WebElement floorNum;

	public HomePage(WebDriver webDriver) {
		super(webDriver);
	}

	/**
	 * 
	 * @return current floor number
	 */
	public String getFloorNumber() {
		webDriverWait.until(ExpectedConditions.visibilityOf(floorNum));
		return floorNum.getText();
	}
}
