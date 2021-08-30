package tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import pages.FormPage;
import utilities.BrowserUtils;

public class UICheckTest extends BaseTest {
	private static Logger console = LoggerFactory.getLogger(UICheckTest.class);
	FormPage formPage = new FormPage();

	@Test( description = "Verify the UI of form Page")
	public void verifyFormPageUI() {
		try {
			console.info("Verification of form Page UI validation begins");
			 boolean uiCheck = true;
		     HashMap<String, String> uiMap = new HashMap<>();
		     uiMap.put("dropdown Section", "Create a script that selects \"Water\" from the dropdown");
		     uiMap.put("city Check section", "Create a script that randomly selects an checkbox and mandatory selects \"Los Angeles\" checkbox");
		     uiMap.put("date pick section", "Create a script that picks the date 07/04/1776 and the time 05:55 PM");
		     uiMap.put("score section", "Create a script that selects the score \"4\"");
		     uiMap.put("Enegery Combination Check Box Section", "Create a script that selects the following combination: \"Water:Yes,No\", \"Energy:Maybe,Yes\", \"Waste:Yes,No,Maybe\"");
		     uiMap.put("Energy Combination Radio Btn Section", "Create a script that selects the following combination: \"Water:Yes\", \"Energy:Maybe\", \"Waste:No\"");
		     uiMap.put("QA Automation Header", "QA Take Home Test");
		     
		     Iterator<String> uiMapItr = uiMap.keySet().iterator();
		     String uiDisplayedtext = "";
		     uiCheck = uiCheck && BrowserUtils.elementDisplayed(formPage.logo);
		     if (uiCheck) 
		    	 uiDisplayedtext = uiDisplayedtext+" Logo is displayed \n";
		     else
		    	 uiDisplayedtext = uiDisplayedtext+" Logo is not displayed \n";
		     
		     uiCheck = uiCheck && BrowserUtils.elementDisplayed(formPage.automationtext);
		     
		     if (uiCheck) 
		    	 uiDisplayedtext = uiDisplayedtext+" Automation text is displayed \n";
		     else
		    	 uiDisplayedtext = uiDisplayedtext+" Automation text is not displayed \n";
		     
		     while (uiMapItr.hasNext()) {
				String uiSection = uiMapItr.next();
				String text = uiMap.get(uiSection);
				if (BrowserUtils.elementDisplayed(String.format(formPage.formPageSection, text))) {
					uiDisplayedtext = uiDisplayedtext +uiSection+" is displayed\n";
					uiCheck = uiCheck && true;
				}else {
					uiDisplayedtext = uiDisplayedtext +uiSection+" is not displayed\n";
					uiCheck = uiCheck && false;
				}
			}
		    console.info("Element UI check for form Page \n {} ",uiDisplayedtext);
		    assertEquals(uiCheck, true);
		} catch (Exception e) {
			console.error("Error occured while validation of UI for form page due to {}", e.getMessage());
		} finally {
			console.info("Verification of form Page UI validation ends");
		}
	}
}
