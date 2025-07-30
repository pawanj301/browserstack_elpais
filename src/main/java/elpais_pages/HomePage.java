package elpais_pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.TestContext;

public class HomePage {

	String device = TestContext.getDevice();

	// Web Elements
	@FindBy(id = "didomi-notice-agree-button")
	private WebElement acceptCookieButton;

	@FindBy(xpath = "//li[@id = 'edition_head']/a/span")
	private WebElement languageSelectionDropdownText;

	@FindBy(xpath = "//a[@href='https://elpais.com' and @cmp-ltrk = 'header_hamburguesa_edicion']")
	private WebElement languageSelectionDropdownText_Mobile;

	@FindBy(xpath = "//a[@href='https://elpais.com/opinion/' and @cmp-ltrk = 'portada_menu']")
	private WebElement opinionTabNavigation;

	@FindBy(xpath = "//a[@href='https://elpais.com/opinion/' and @cmp-ltrk = 'header_hamburguesa']")
	private WebElement opinionTabNavigation_Mobile;

	@FindBy(id = "btn_open_hamburger")
	private WebElement hamburgerMenu_Mobile;

	private WebDriver driver;

	// Constructors
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void acceptCookiesOnHomePage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
		} catch (Exception e) {
			System.out.println("Cookie banner not present or already accepted.");
		}
	}

	public String getHomePageLanguage() {
		if (device.contains("mobile")) {
			hamburgerMenu_Mobile.click();
			return languageSelectionDropdownText_Mobile.getText();
		} else {
			return languageSelectionDropdownText.getText();
		}
	}

	public void navigateToOpinionTab() {
		if (device.contains("mobile")) {
			opinionTabNavigation_Mobile.click();
		} else {
			opinionTabNavigation.click();
		}
	}
}
