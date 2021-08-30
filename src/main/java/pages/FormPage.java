package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utilities.BrowserUtils;
import utilities.Driver;


public class FormPage {

	private static Logger console = LoggerFactory.getLogger(FormPage.class);

	public FormPage() {
		PageFactory.initElements(Driver.get(), this);
	}

	@FindBy(xpath = "//div[@role='heading'][contains(.,'Create a script that enters random ASCII')]/ancestor::div[@class='freebirdFormviewerViewNumberedItemContainer']//input")
	private WebElement asciiInputTxtBox;

	@FindBy(css = "div.freebirdHeaderCard")
	public WebElement logo;

	@FindBy(xpath = "//div[@class='freebirdFormviewerViewHeaderDescription'][normalize-space(.)='Automation all the things']")
	public WebElement automationtext;

	public String formPageSection = "//div[@role='heading'][normalize-space(.)='%s']";
	public String formPageAsciSection = "//div[@role='heading'][contains(.,'%s')]";
	public String selectEnergyDropdown= "//div[@role='listbox']//div[@role='presentation']";
	public String selectedOptionWB = "//div[@role='listbox']//div[@role='presentation']//div[contains(@class,'freebirdThemedSelectOptionDarkerDisabled')][contains(@class,'isSelected')]";
	

	public By radioBtn(String text, String label) {
		return By.xpath("//div[@role='heading'][normalize-space(.)='" + text
				+ "']/ancestor::div[@role='listitem']//label[normalize-space(.)='" + label
				+ "']//div[contains(@class,'freebirdThemedRadio')]");
	}

	public By energyCombinationRadioBtn(String label, String value) {
		return By.xpath(
				"//div[@role='heading'][normalize-space(.)='Create a script that selects the following combination: \"Water:Yes\", \"Energy:Maybe\", \"Waste:No\"']/ancestor::div[@role='listitem']//div[normalize-space(.)='"
						+ label
						+ "'][contains(@class,'GridRowHeader')]/..//div[contains(@class,'freebirdThemedRadio')]//div[@data-value='"
						+ value + "']");
	}

	public By chkBox(String text, String label) {
		return By.xpath("//div[@role='heading'][normalize-space(.)='" + text
				+ "']/ancestor::div[@role='listitem']//label[normalize-space(.)='" + label
				+ "']//div[contains(@class,'freebirdThemedCheckbox ')]");
	}

	public By energyCombinationchkBox(String label, String value) {
		return By.xpath(
				"//div[@role='heading'][normalize-space(.)='Create a script that selects the following combination: \"Water:Yes,No\", \"Energy:Maybe,Yes\", \"Waste:Yes,No,Maybe\"']/ancestor::div[@role='listitem']//div[normalize-space(.)='"
						+ label
						+ "'][contains(@class,'GridRowHeader')]/..//div[contains(@class,'freebirdThemedCheckbox')][@data-answer-value='"
						+ value + "']");
	}

	public WebElement getOptionWB(String option) {
		WebElement wb = null;
		try {
			wb = BrowserUtils
					.findElement("//div[contains(@class,'quantumWizMenuPaperselectPopup ')]//div[normalize-space(.)='"
							+ option + "']");
		} catch (Exception e) {
			console.error("error occured while getting option for {} due to {}", option, e.getMessage());
		}
		return wb;
	}

	public String enterAsciiCharacter(String character) {
		String value = "";
		BrowserUtils.clickAndSend(asciiInputTxtBox, character, "Ascii Character textbox");
		value = asciiInputTxtBox.getAttribute("value").trim();
		return value;
	}
}
