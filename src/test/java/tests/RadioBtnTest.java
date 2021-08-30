package tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.FormPage;
import utilities.BrowserUtils;
import utilities.Driver;

public class RadioBtnTest extends BaseTest {

	private static final Logger console = LoggerFactory.getLogger(RadioBtnTest.class);
	FormPage formPage = new FormPage();

	@Test(dataProvider = "energylbl", description = "Verify whether user is able to select every radio button of energy form")
	public void verifyEnegrylblChk(String text, String label) {
		console.info("Energy label {} for text {} begins", text, label);
		try {
			WebElement wb = Driver.get().findElement(formPage.radioBtn(text, label));
			assertEquals(BrowserUtils.checkElement(wb, label + " radio Btn"), true,"not able to check on "+label+"  for "+text);

		} catch (Exception e) {
			console.error("Error occurred while validating Energy label {} for text {}  due to {}", text, label,
					e.getMessage());
		} finally {
			console.info("Energy label {} for text {} ends", text, label);
		}
	}

	@Test(dataProvider = "scorelbl", description = "Verify whether user is able to select every radio button of score form")
	public void verifyScoreChk(String text, String label) {
		console.info("Score label {} for text {} begins", text, label);
		try {
			WebElement wb = Driver.get().findElement(formPage.radioBtn(text, label));
			assertEquals(BrowserUtils.checkElement(wb, label + " radio Btn"), true,"not able to check on "+label+"  for "+text);

		} catch (Exception e) {
			console.error("Error occurred while validating score label {} for text {}  due to {}", text, label,
					e.getMessage());
		} finally {
			console.info("Score label {} for text {} ends", text, label);
		}
	}

	@Test(dataProvider = "energyCombination", description = "Verify whether user is able to select different set of energry label")
	public void verifyEngeryCombinationRadioBtnChk(HashMap<String, String> energyMap) {
		String water = energyMap.get("Water");
		String energy = energyMap.get("Energy");
		String waste = energyMap.get("Waste");
		console.info("Energy combination label check with water {}, energy {} and waste {} begins", water, energy,
				waste);
		try {
			Iterator<String> energyItr = energyMap.keySet().iterator();
			while (energyItr.hasNext()) {
				String label = energyItr.next();
				String value = energyMap.get(label);
				WebElement wb = Driver.get().findElement(formPage.energyCombinationRadioBtn(label, value));
				assertEquals(BrowserUtils.checkElement(wb, label + " radio Btn"), true,"not able to check on "+label+"  for "+value);
			}

		} catch (Exception e) {
			console.error(
					"Error occurred while validating selecting different energy with water {}, energy {} and waste {} begins  due to {}",
					water, energy, waste, e.getMessage());
		} finally {
			console.info("Energy combination label check with water {}, energy {} and waste {} ends", water, energy,
					waste);
		}
	}

	@DataProvider(name = "energylbl")
	public Object[][] getEnergylbl() {
		Object[][] energylbl = new Object[][] { { "Create a script that will select \"Energy\"", "Carbon" },
				{ "Create a script that will select \"Energy\"", "Water" },
				{ "Create a script that will select \"Energy\"", "Energy" },
				{ "Create a script that will select \"Energy\"", "Waste" } };
		return energylbl;
	}

	@DataProvider(name = "scorelbl")
	public Object[][] getScorelbl() {
		Object[][] energylbl = new Object[][] { { "Create a script that selects the score \"4\"", "1" },
				{ "Create a script that selects the score \"4\"", "2" },
				{ "Create a script that selects the score \"4\"", "3" },
				{ "Create a script that selects the score \"4\"", "4" } };
		return energylbl;
	}

	@DataProvider(name = "energyCombination")
	public Object[][] getEneryCombination() {
		Object[][] energylbl = new Object[][] { { new HashMap<String, String>() {
			{
				put("Water", "Yes");
				put("Energy", "Maybe");
				put("Waste", "No");
			}
		} }, { new HashMap<String, String>() {
			{
				put("Water", "Yes");
				put("Energy", "No");
				put("Waste", "Maybe");
			}
		} }, { new HashMap<String, String>() {
			{
				put("Water", "Maybe");
				put("Energy", "No");
				put("Waste", "Yes");
			}
		} } };
		return energylbl;
	}
}
