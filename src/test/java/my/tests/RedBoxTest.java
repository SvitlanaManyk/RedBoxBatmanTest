// Copyright, Svitlana Manyk 2016
package my.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import my.pages.RedBoxFrontPage;
import util.PropertiesLoader;

public class RedBoxTest {
	WebDriver driver = null;

	@Before
	public void setup() {
		System.out.println("Test starting...");
		startDriver();
	}

	@After
	public void byeByeDriver() {
		System.out.println("Closing the driver");
		driver.close();
		System.out.println("==========================================");
	}

	@Test
	public void pageObjectCheeseTest() {
		String searchPhrase = "Batman";

		RedBoxFrontPage frontPage = new RedBoxFrontPage(driver);
		frontPage.OpenPage();
		frontPage.SearchQuery(searchPhrase);
		frontPage.RowsValidator();

	}

	public void startDriver() {
		PropertiesLoader props = new PropertiesLoader();
		String browser = props.getProperty("browser");

		System.out.println("Starting driver...");

		switch (browser) {
		case "firefox":
			System.out.println("Firefox driver requested");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		case "chrome":
			System.out.println("Chrome driver requested");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		default:
			String message = "Unknown browser requested " + browser;
			System.out.println(message);
			throw new RuntimeException(message);

		}
	}
}
