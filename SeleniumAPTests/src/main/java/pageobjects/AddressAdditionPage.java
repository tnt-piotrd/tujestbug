package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.TimeOuts._30_SECONDS;

public class AddressAdditionPage extends BasePage{

    private static final String SAVE_ADDRESS_BUTTON_XPATH ="//button[@id='submitAddress']";

    @FindBy(xpath = SAVE_ADDRESS_BUTTON_XPATH)
    private WebElement saveButton;

    @FindBy(id = "firstname")
    private WebElement firstNameInput;

    @FindBy(id = "lastname")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement addressLineInput;

    @FindBy(id = "address2")
    private WebElement addressLine2Input;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "id_state")
    private WebElement stateSelect;

    @FindBy(id = "postcode")
    private WebElement zipPostalCodeInput;

    @FindBy(id = "phone")
    private WebElement homePhoneInput;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneInput;

    @FindBy(id = "other")
    private WebElement additionalInfoInput;

    @FindBy(id = "alias")
    private WebElement addressAliasNameInput;

    public AddressAdditionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToBeLoaded() {
        new WebDriverWait(driver, _30_SECONDS.getDuration()).until(ExpectedConditions.elementToBeClickable(By.xpath(SAVE_ADDRESS_BUTTON_XPATH)));
    }

    public MyAddressesPage addNewAddress(String firstName, String lastName, String company, String address, String addressLine2, String city,
                              String state, String zipPostalCode, String homePhone, String mobilePhone, String additionalInfo,
                              String addressAliasName){
        enterTextIfNotEmpty(firstNameInput, firstName);
        enterTextIfNotEmpty(lastNameInput, lastName);
        enterTextIfNotEmpty(companyInput, company);
        enterTextIfNotEmpty(addressLineInput, address);
        enterTextIfNotEmpty(addressLine2Input, addressLine2);
        enterTextIfNotEmpty(cityInput, city);
        selectStateIfNotEmpty(state);
        enterTextIfNotEmpty(zipPostalCodeInput, zipPostalCode);
        enterTextIfNotEmpty(homePhoneInput, homePhone);
        enterTextIfNotEmpty(mobilePhoneInput, mobilePhone);
        enterTextIfNotEmpty(additionalInfoInput, additionalInfo);
        enterTextIfNotEmpty(addressAliasNameInput, addressAliasName);
        saveButton.click();
        return new MyAddressesPage(driver);
    }

    private void enterTextIfNotEmpty(WebElement element, String text) {
        if (text != null && !text.isEmpty()) {
            element.clear();
            element.sendKeys(text);
        }
    }

    private void selectStateIfNotEmpty(String state) {
        if (state == null || state.isEmpty()) {
            return;
        }else {
            Select select = new Select(stateSelect);
            select.selectByVisibleText(state);
        }
    }
}
