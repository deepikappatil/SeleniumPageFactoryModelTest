package com.selenium.pagefactory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

	private static final int TIMEOUT_IN_SECS = 20;
	protected WebDriver webDriver;
	protected WebDriverWait webDriverWait;

	public PageObject(WebDriver driver) {
		webDriver = driver;
		webDriverWait = new WebDriverWait(driver, TIMEOUT_IN_SECS);
		PageFactory.initElements(driver, this);
	}
}
