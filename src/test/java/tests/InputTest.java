package tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import pages.FormPage;
import utilities.BrowserUtils;

public class InputTest extends BaseTest {

	private static final Logger console = LoggerFactory.getLogger(InputTest.class);
	FormPage formPage = new FormPage();

	@Test(description = "Verify whether user is able to enter ascii charcter or not")
	public void verifyAsciiCharacterInput() {
		String asciiText = BrowserUtils.getRandomASCIICharacter(20);
		console.info("Verification for to enter ascii character {} or not begins", asciiText);
		try {
			assertEquals(formPage.enterAsciiCharacter(asciiText).equalsIgnoreCase(asciiText), true);
		} catch (Exception e) {
			console.error("Error occurred while validating entering data in ascii character due to {}", e.getMessage());
		} finally {
			console.info("Verification for to enter ascii character or not ends");
		}
	}

	@Test(description = "verify whether user is able to enter date or not in date input")
	public void verifyDateInput() {
		String date = "07/04/1776";
		String time = "05:55 PM";
		console.info("Verification for entering date  {} and time {} begins", date, time);
		try {
			WebElement wb = BrowserUtils.findElement("//input[@type='date']");
			wb.sendKeys("07041776");
			wb.sendKeys(Keys.TAB);
			console.info("date {} is typed into date text box with locator {}", date, wb);
			BrowserUtils.clickAndSend("//input[@aria-label='Hour']", "05");
			BrowserUtils.clickAndSend("//input[@aria-label='Minute']", "55");
			console.info("time is {} is typed into time text box with locator", date);
		} catch (Exception e) {
			console.error("Error occurred while entering date {} & time {} due to {}", date, time, e.getMessage());
		} finally {
			console.info("Verification for entering date  {} and time {} ends", date, time);
		}
	}
}
