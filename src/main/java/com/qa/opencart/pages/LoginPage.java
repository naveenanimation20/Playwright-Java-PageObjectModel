package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;

	// 1. String Locators - OR
	private String emailId = "//input[@id='input-email']";
	private String password = "//input[@id='input-password']";
	private String loginBtn = "//input[@value='Login']";
	private String forgotPwdLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password11']";
	private String logoutLink = "//a[@class='list-group-item'][normalize-space()='Logout']";

	// 2. page constructor:
	public LoginPage(Page page) {
		this.page = page;
	}
	
	// 3. page actions/methods:
	public String getLoginPageTitle() {
		return page.title();
	}
	
	public boolean isForgotPwdLinkExist() {
		return page.isVisible(forgotPwdLink);
	}
	
	public boolean doLogin(String appUserName, String appPassword) {
		System.out.println("App creds: " + appUserName + ":" + appPassword);
		page.fill(emailId, appUserName);
		page.fill(password, appPassword);
		page.click(loginBtn);
		page.locator(logoutLink).waitFor();
		if(page.locator(logoutLink).isVisible()) {
			System.out.println("user is logged in successfully....");
			return true;
		}else {
			System.out.println("user is not logged in successfully....");
			return false;
		}
	}
}
