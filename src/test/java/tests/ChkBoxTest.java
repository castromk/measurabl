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

public class ChkBoxTest extends BaseTest {

	private static final Logger console = LoggerFactory.getLogger(RadioBtnTest.class);
	FormPage formPage = new FormPage();

	@Test(dataProvider = "cityChk", description = "Verify whether user is able to select given city or not")
	public void verifyCityChkBoxSelection(String text, String label) {
		console.info("City label {} for text {} begins", text, label);
		try {
			WebElement wb = Driver.get().findElement(formPage.chkBox(text, label));
			//boolean bcheck = BrowserUtils.checkElement(wb, label + " checkbox");
			assertEquals(BrowserUtils.checkElement(wb, label + " checkbox"), true,"not able to check on "+label+"  for "+text);

		} catch (Exception e) {
			console.error("Error occurred while validating City label {} for text {}  due to {}", text, label,
					e.getMessage());
		} finally {
			console.info("City label {} for text {} ends", text, label);
		}
	}

	@Test(dataProvider = "energyCombinationCheckBox", description = "Verify whether user is able to check different set of energy label")
	public void verifyEnergyCombinationChkBox(HashMap<String, String> energyMap) {
		String water = energyMap.get("Water");
		String energy = energyMap.get("Energy");
		String waste = energyMap.get("Waste");
		console.info("Energy combination label checkbox with water {}, energy {} and waste {} begins", water, energy,
				waste);
		try {
			Iterator<String> energyItr = energyMap.keySet().iterator();
			while (energyItr.hasNext()) {
				String label = energyItr.next();
				String value = energyMap.get(label);
				WebElement wb = Driver.get().findElement(formPage.energyCombinationRadioBtn(label, value));
				assertEquals(BrowserUtils.checkElement(wb, label + " checkbox"), true,"not able to check on "+label+"  for "+value);
			}

		} catch (Exception e) {
			console.error(
					"Error occurred while validating selecting different energy with water {}, energy {} and waste {} begins  due to {}",
					water, energy, waste, e.getMessage());
		} finally {
			console.info("Energy combination label checkbox with water {}, energy {} and waste {} ends", water, energy,
					waste);
		}
	}

	@DataProvider(name = "cityChk")
	public Object[][] getCityChk() {
		Object[][] citylbl = new Object[][] { {
				"Create a script that randomly selects an checkbox and mandatory selects \"Los Angeles\" checkbox",
				"Los Angeles" },
				{ "Create a script that randomly selects an checkbox and mandatory selects \"Los Angeles\" checkbox",
						"London" },
				{ "Create a script that randomly selects an checkbox and mandatory selects \"Los Angeles\" checkbox",
						"New York" }, };
		return citylbl;
	}

	@DataProvider(name = "energyCombinationCheckBox")
	public Object[][] getEneryCombinationChkBox() {
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
