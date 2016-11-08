// Copyright, Svitlana Manyk 2016

package my.pages;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.PropertiesLoader;

public class RedBoxFrontPage {

	String url = "http://www.redbox.com/";
	WebDriver driver;
	WebDriverWait wdWait;
	By rowsXpath = By.xpath("//div[@class='row extra-padding ng-scope']");

	public RedBoxFrontPage(WebDriver driver) {
		this.driver = driver;
		wdWait = new WebDriverWait(driver, 5);
	}

	public void OpenPage() {
		System.out.println("Opening page " + url);
		driver.get(url);
	}

	public void SearchQuery(String searchQuery) {
		By searchIcon = new By.ByXPath("//a[@id='searchdigital0_SearchIcon']");
		By searchBar = new By.ByXPath("//input[@id='searchdigital0_SearchBox']");
		By videoResultRows = new By.ByXPath("//div[@class='row extra-padding ng-scope']");

		System.out.println("Clicking Search button");
		WebElement weSearchIcon = driver.findElement(searchIcon);
		weSearchIcon.click();
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));

		System.out.println("Locating search bar");
		WebElement weSearchBar = driver.findElement(searchBar);
		weSearchBar.sendKeys("Batman" + Keys.ENTER);
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(rowsXpath));
	}

	public void RowsValidator() {
		List<WebElement> allResultRows = driver.findElements(rowsXpath);
		PropertiesLoader props = new PropertiesLoader();
		int rowNumber = props.getPropertyAsInt("row.number");

		if (rowNumber > allResultRows.size()) {
			String message = "The row number " + rowNumber + " does not exist";
			System.out.println(message);
			Assert.fail(message);
		}
		WebElement weRow = allResultRows.get(rowNumber - 1);

		By articlesXpath = By.xpath("./div/div/a");
		List<WebElement> articleList = weRow.findElements(articlesXpath);
		System.out.println("The size of the row is " + articleList.size());
	}
}
