package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;

public class HomePageTest {

	PlaywrightFactory pf;
	Page page;

	HomePage homePage;

	@BeforeTest
	public void setup() {
		pf = new PlaywrightFactory();
		page = pf.initBrowser("chromium");
		homePage = new HomePage(page);
	}

	@Test
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, "Your Store");
	}

	@Test
	public void homePageURLTest() {
		String actualURL = homePage.getHomePageURL();
		Assert.assertEquals(actualURL, "https://naveenautomationlabs.com/opencart/");
	}
	
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
			
		};
	}

	@Test(dataProvider = "getProductData")
	public void searchTest(String productName) {
		String actualSearchHeader = homePage.doSearch(productName);
		Assert.assertEquals(actualSearchHeader, "Search - "+productName);
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
