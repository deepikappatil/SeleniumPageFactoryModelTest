package com.selenium.pagefactory.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.selenium.pagefactory.page.HomePage;
import com.selenium.pagefactory.page.SearchFloor;

public class SearchTest {

	String driverPath = "src/main/resources/geckodriver.exe";
	private WebDriver webDriver;
	private String url = "https://hq.iv.navvis.com/";

	private static FirefoxProfile getProfile(String profileName) {
		if (profileName == null || profileName.trim().isEmpty()) {
			return new FirefoxProfile();
		}
		return new ProfilesIni().getProfile(profileName);
	}

	@BeforeTest
	public void invokeBrowser() {
		try {
			System.setProperty("webdriver.gecko.driver", driverPath);

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setProfile(getProfile("default"));
			firefoxOptions.setCapability("marionette", true);
			firefoxOptions.setAcceptInsecureCerts(true);

			// Capabilities capabilities = DesiredCapabilities.firefox();
			webDriver = new FirefoxDriver(firefoxOptions);
			webDriver.manage().deleteAllCookies();
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

			// launch Firefox and redirect it to the URL
			webDriver.get(url);
			System.out.println("Page opened");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 0)
	public void test_HomePage_FloorNum() {
		HomePage homePage = new HomePage(webDriver);
		String floorNumber = homePage.getFloorNumber();
		Assert.assertTrue(floorNumber.equals("0"));
	}

	@Test
	public void test_SearchFifthFloor() {
		SearchFloor searchFloor = new SearchFloor(webDriver);
		String floorNumber = searchFloor.performSearch("IV");
		Assert.assertTrue(floorNumber.equals("5"));
	}

	@AfterTest(alwaysRun = true)
	public void closeBrowser() {
		webDriver.close();
		System.out.println("Closed the page");
	}
}
