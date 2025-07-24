package pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import other.LoggerSingleton;

import java.util.List;

import static utils.TimeOuts._30_SECONDS;

public class MyAddressesPage extends BasePage{
    private static final String ADD_NEW_ADDRESS_BUTTON_XPATH = "//a[@title='Add an address']";

    @FindBy(xpath = "//p[@class='alert alert-warning'][contains(text(), 'No addresses are available')]")
    private WebElement noAddressesAvailableLabel;

    @FindBy(xpath = ADD_NEW_ADDRESS_BUTTON_XPATH)
    private WebElement addNewAddressButton;

    @FindBy(xpath = "//div[@class='addresses']//h3")
    private List<WebElement> addressesList;

    public MyAddressesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageToBeLoaded() {
        new WebDriverWait(driver, _30_SECONDS.getDuration()).until(ExpectedConditions.elementToBeClickable(By.xpath(ADD_NEW_ADDRESS_BUTTON_XPATH)));
    }

    public boolean isLabelForNoAddressesDisplayed(){
        LoggerSingleton.getInstance().log("Checking if 'No addresses are available' label is displayed");
        return noAddressesAvailableLabel.isDisplayed();
    }

    public AddressAdditionPage openNewAddressAddition() {
        LoggerSingleton.getInstance().log("Opening new address addition page");
        addNewAddressButton.click();
        return new AddressAdditionPage(driver);
    }

    public List<String> getAllDisplayedAddresses(){
        LoggerSingleton.getInstance().log("Retrieving all displayed addresses");
        return addressesList.stream().map(WebElement::getText).toList();
    }

    public MyAddressesPage deleteAddress(String originalAddress) {
        WebElement addressToDelete = findMatchingAddress(originalAddress);
        LoggerSingleton.getInstance().log("Deleting address: " + originalAddress);
        addressToDelete.findElement(By.xpath("../..//a[@title='Delete']")).click();
        Alert alert = driver.switchTo().alert();
        LoggerSingleton.getInstance().log("Confirming deletion: " + alert.getText());
        alert.accept();
        return this;
    }

    private WebElement findMatchingAddress(String addressAliasName){
        for (WebElement displayedAddress : addressesList) {
            if (displayedAddress.getText().equals(addressAliasName)) {
                return displayedAddress;
            }
        }
        throw new RuntimeException("Unable to find address with name: " + addressAliasName);
    }
}
