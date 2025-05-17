package test;

import dao.MyAddressDAO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageobjects.TopNavigationBar;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddressTest extends BaseTest {
    private static final String ORIGINAL_ADDRESS = "NEW YORK ADDRESS";
    private boolean shouldDeleteAddress = false;

    @AfterMethod
    public void cleanUpAddresses() {
        if (shouldDeleteAddress) {
            new TopNavigationBar(driver).openUserAccountDetails()
                    .openMyAddresses().deleteAddress(ORIGINAL_ADDRESS);
        }
    }

    @Test
    public void byDefaultUserHasNoAddress() {
        assertTrue(new TopNavigationBar(driver).openUserAccountDetails()
                .openMyAddresses().isLabelForNoAddressesDisplayed());
    }

    @Test
    public void userIsAbleToAddAddress() {
        MyAddressDAO myAddressDAO = new MyAddressDAO.Builder()
                .setFirstName("John")
                .setLastName("Smith")
                .setAddressLine("WallStreet 1/2")
                .setAddressLine2("Manhattan")
                .setCity("New York")
                .setState("New York")
                .setZipPostalCode("11111")
                .setHomePhone("12345678")
                .setAddressAliasName(ORIGINAL_ADDRESS)
                .build();

        List<String> displayedAddresses = new TopNavigationBar(driver).openUserAccountDetails()
                .openMyAddresses().openNewAddressAddition()
                .addNewAddress(myAddressDAO)
                .getAllDisplayedAddresses();
        shouldDeleteAddress = true;
        assertEquals(displayedAddresses, List.of(ORIGINAL_ADDRESS));
    }
}
