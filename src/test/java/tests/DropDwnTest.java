package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.FormPage;
import utilities.BrowserUtils;

public class DropDwnTest extends BaseTest {
	private static Logger console = LoggerFactory.getLogger(DropDwnTest.class);
	FormPage formPage = new FormPage();

	@Test(dataProvider = "dropdownOptions", description = "verify whether user is able to select dropdown options or not")
	public void verifyDropDownTest(String option) {
		try {
			console.info("Verification for selecting {} option from energy dropdown begins", option);
			String selectedOption = "";
			BrowserUtils.clickElement(formPage.selectEnergyDropdown, "Select Energy Dropdown");
			BrowserUtils.clickElement(formPage.getOptionWB(option), "Option " + option);
			selectedOption = BrowserUtils.getElementText(BrowserUtils.findElement(formPage.selectedOptionWB));
			console.info("Selected option is {} after performing selection for energy", selectedOption);
			assertEquals(selectedOption, option);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
			console.error("Error occured while selecting {} option due to {}", option, e.getMessage());
		} finally {
			console.info("Verification for selecting {} option from energy dropdown ends", option);
		}
	}

	@DataProvider(name = "dropdownOptions")
	private Object[][] getEnergyOptions() {
		return new Object[][] { 
			{ "Water" }, 
			{ "Energy" }, 
			{ "Carbon" } 
			};
	}
}
